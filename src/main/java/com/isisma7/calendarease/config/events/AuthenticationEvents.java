package com.isisma7.calendarease.config.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class AuthenticationEvents {

    // Evento de autenticacion correcta
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent){
        // Crear variable de sesion
        RequestContextHolder.getRequestAttributes().setAttribute("DataUser", "Se ha autenticado", RequestAttributes.SCOPE_SESSION);
    }

    // Evento de autenticacion fallida
    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent){

    }

}
