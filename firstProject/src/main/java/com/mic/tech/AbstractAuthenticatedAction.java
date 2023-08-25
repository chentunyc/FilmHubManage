package com.mic.tech;

abstract class AbstractAuthenticatedAction extends AbstractAction{
    public enum Role {
        ADMINISTRATOR,
        MANAGER,
        RECEPTIONIST,
        CUSTOMER
    }
    protected static boolean isAuthenticated=false;
    protected static Role currentRole=null;
}
class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }
}