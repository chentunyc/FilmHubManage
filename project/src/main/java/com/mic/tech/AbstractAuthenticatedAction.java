package com.mic.tech;

public abstract class AbstractAuthenticatedAction extends AbstractAction{
    public enum Role {
        ADMINISTRATOR,
        MANAGER,
        RECEPTIONIST,
        GOLD_CUSTOMER,
        SILVER_CUSTOMER,
        BRONZE_CUSTOMER;
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
    public abstract void perform();
    protected void validate() throws  AuthenticationException{
        if(!isAuthenticated)
            throw new AuthenticationException("您必须经过身份验证才能执行此操作");
    }
}
class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }
}