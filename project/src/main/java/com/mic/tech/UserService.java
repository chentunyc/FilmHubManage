package com.mic.tech;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class UserService implements UserDAO{
    List <User> list=new ArrayList<>();
    UserService(){
        User administor=new User();
        administor.setId(list.size());
        administor.setRole(AbstractAuthenticatedAction.Role.ADMINISTRATOR);
        administor.setPassword("ynuinfo#777");
        administor.setEmail("NULL");
        administor.setregistrationTime(0);
        administor.setUsername("admin");
        administor.setTelephoneNumber(0);
        administor.setPurchaseAmount(0);
        administor.setPurchaseNumber(0);
        addUser(administor);
    }
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
        list.get(user.getId()).setPassword(user.getPassword());
        list.get(user.getId()).setRole(user.getRole());
        list.get(user.getId()).setEmail(user.getEmail());
        list.get(user.getId()).setTelephoneNumber(user.getTelephoneNumber());
        list.get(user.getId()).setPurchaseNumber(user.getPurchaseNumber());
        list.get(user.getId()).setPurchaseAmount(user.getPurchaseAmount());
    }
    public void deleteUser(String userName) {
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(userName)) {
                iterator.remove();
            }
        }
    }
    public List<User> getAllUsers(){
        return list;
    }
}
