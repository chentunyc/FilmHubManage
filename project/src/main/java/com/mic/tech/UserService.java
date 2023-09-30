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
        administor.setTelephoneNumber(null);
        administor.setPurchaseAmount(0);
        administor.setPurchaseNumber(0);
        addUser(administor);
        User manager=new User();
        manager.setId(list.size());
        manager.setRole(AbstractAuthenticatedAction.Role.MANAGER);
        manager.setPassword("ynuinfo#777");
        manager.setEmail("NULL");
        manager.setregistrationTime(0);
        manager.setUsername("manager");
        manager.setTelephoneNumber(null);
        manager.setPurchaseAmount(0);
        manager.setPurchaseNumber(0);
        addUser(manager);
        User receptionist=new User();
        receptionist.setId(list.size());
        receptionist.setRole(AbstractAuthenticatedAction.Role.RECEPTIONIST);
        receptionist.setPassword("ynuinfo#777");
        receptionist.setEmail("NULL");
        receptionist.setregistrationTime(0);
        receptionist.setUsername("receptionist");
        receptionist.setTelephoneNumber(null);
        receptionist.setPurchaseAmount(0);
        receptionist.setPurchaseNumber(0);
        addUser(receptionist);
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
    public User getUserByUserTelephoneNumber(String telephoneNumber){
        for(User user:list){
            if(user.getTelephoneNumber().equals(telephoneNumber)) {
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
