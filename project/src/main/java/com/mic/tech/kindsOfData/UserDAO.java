package com.mic.tech.kindsOfData;

import com.mic.tech.kindsOfData.User;

import java.util.List;

public interface UserDAO {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(String userName);
    public User getUserByUserName(String userName);
    public User getUserByUserId(int id);
    public User getUserByUserTelephoneNumber(String telephoneNumber);
    public List<User> getAllUsers();
}
