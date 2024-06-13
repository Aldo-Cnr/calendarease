package com.isisma7.calendarease.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
                        .requestMatchers("/css/**", "/js/**", "/bootstrap_5_3_3/**", "/jquery_3_7_1/**").permitAll()
                        .requestMatchers("/", "index", "/loggin").permitAll() // Primera vista para todos
                        /*.requestMatchers("/ayuda").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/basic/**").hasRole("USER")*/
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(form -> form
                        .loginPage("/loggin") // A donde se va el login
                        .loginProcessingUrl("/logginprocess") // Donde se deliega el procesamiento de la aplicacion
                        .successHandler(myAuthenticationSuccessHandler()) // A donde redireige el exito de logeo
                        .permitAll()

                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/loggin?logout") // A donde redirecciona el cierre de sesion
                        .permitAll()
                );

        return http.build();
    }

    // Redireccion despues del logeo
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/home");
    }
}
