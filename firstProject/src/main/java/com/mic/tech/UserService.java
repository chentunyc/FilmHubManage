package com.mic.tech;
import java.util.List;
import java.util.ArrayList;
public class UserService implements UserDAO{
    List <User> list=new ArrayList<>();
    public void addUser(User user) {
        list.add(user);
    }
    public User getUserByUserName(String userName){
        for(User user:list){
            if(user.getUsername().equals(userName)) {
                return user;
            }
        }
            return null;
    }
    public User getUserByUserId(int id){
        return list.get(id);
    }


    public void updateUser(User user){
        list.get(user.getId()).setRole(user.getRole());
        list.get(user.getId()).setEmail(user.getEmail());
        list.get(user.getId()).setTelephoneNumber(user.getTelephoneNumber());
    }
    public void deleteUser(String userName){
        for(User user:list){
            if(user.getUsername().equals(userName)) {
                list.remove(user.getId());
                }
            }
    }
    public List<User> getAllUsers(){
        return list;
    }
}
