package com.isisma7.calendarease.config;

import com.isisma7.calendarease.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

// Indica que es una clase de configuracion
@Configuration
// Que es de configuracion de seguridad
@EnableWebSecurity
public class SecurityConf {
    // Reglas de seguridad, filtros de seguridad
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize // Zona de filtros
                        // Lectura de recursos estaticos (css, js)
                        .requestMatchers("/css/**", "/js/**",
                                "/bootstrap_5_3_3/**", "/jquery_3_7_1/**",
                                "/sweetalert2/**").permitAll()
                        .requestMatchers("/", "index", "/loggin").permitAll() // Primera vista para todos
                        .requestMatchers("/home").hasAnyRole("DEV", "DOC", "PAC")
                        /*.requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/basic/**").hasRole("USER")*/
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(form -> form
                        .loginPage("/loggin") // A donde se va el login
                        .loginProcessingUrl("/logginprocess") // Donde se deliega el procesamiento de la aplicacion
                        .successHandler(myAuthenticationSuccessHandler()) // A donde redireige el exito de logeo
                        .failureHandler(myAuthenticationFailureHandler()) // Redireccion en caso de fallo
                        .permitAll()

                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/loggin?logout=true") // A donde redirecciona el cierre de sesion
                        .permitAll()
                );

        return http.build();
    }

    // Redireccion despues del logeo
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/home");
    }

    // Redireccion en caso de fallo
    @Bean
    public AuthenticationFailureHandler myAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/loggin?error=true");
    }

    /*Un administrador de autenticacion (authenticationManager)*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*Proveedero de autenticacion, en este caso de una base de datos (Authentication Provider)*/
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Contraseña encriptada
        provider.setPasswordEncoder(passwordEncoder());
        // Usuario de Conexion
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    /*Contraseñas que se mandan al autenticador*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Encriptado
        return new BCryptPasswordEncoder();
        // NO encriptado
        // return NoOpPasswordEncoder.getInstance();
    }
}
