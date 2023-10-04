package com.mic.tech;

import java.util.List;

public class UserService{
    private UserDAO userDao =null;
    UserService(UserDAO userDao){
        this.userDao = userDao;
        initialize();
    }
    public void addUser(User user) {
        userDao.addUser(user);
    }
    public User getUserByUserName(String userName){
        return userDao.getUserByUserName(userName);
    }
    public User getUserByUserTelephoneNumber(String telephoneNumber){
        return userDao.getUserByUserTelephoneNumber(telephoneNumber);
    }
    public User getUserByUserId(int Id){
        return userDao.getUserByUserId(Id);
    }
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    public void initialize(){
        User administor=new User("admin",User.hashPassword("ynuinfo#777"),AbstractAuthenticatedAction.Role.ADMINISTRATOR,0,null,0,null,0,0,"false");
        userDao.addUser(administor);
        User manager=new User("manager",User.hashPassword("ynuinfo#777"),AbstractAuthenticatedAction.Role.MANAGER,1,null,0,null,0,0,"false");
        userDao.addUser(manager);
        User receptionist=new User("receptionist",User.hashPassword("ynuinfo#777"),AbstractAuthenticatedAction.Role.RECEPTIONIST,2,null,0,null,0,0,"false");
        userDao.addUser(receptionist);
    }
}
