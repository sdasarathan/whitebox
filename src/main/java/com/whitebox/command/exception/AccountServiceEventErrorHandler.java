package com.whitebox.command.exception;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class AccountServiceEventErrorHandler implements ListenerInvocationErrorHandler{

    @Override
    public void onError(Exception e, EventMessage<?> eventMessage, EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
