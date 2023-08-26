package com.mic.tech;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
    User getUserByUserName(String userName);
    User getUserByUserId(int id);
    List<User> getAllUsers();
}
