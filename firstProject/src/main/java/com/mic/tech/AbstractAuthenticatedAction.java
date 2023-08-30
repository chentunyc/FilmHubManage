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
    public void run(){
        try{
            validate();
            perform();
        }
        catch(AuthenticationException exception){
            System.out.println("身份验证失败..."+exception.getMessage());
        }
    }
    abstract void perform();
    protected void validate() throws  AuthenticationException{
        if(!isAuthenticated)
            throw new AuthenticationException("您必须经过身份验证才能执行此操作");
    }
    protected Role getCurrentRole() {
        return currentRole;
    }
}
class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }
}