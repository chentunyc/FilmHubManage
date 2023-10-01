package com.mic.tech;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user, String password, AbstractAuthenticatedAction.Role role, String email, String telephoneNumber, int purchaseNumber, int purchaseAmount);
    void deleteUser(String userName);
    User getUserByUserName(String userName);
    User getUserByUserId(int id);
    User getUserByUserTelephoneNumber(String telephoneNumber);
    List<User> getAllUsers();
}
