package com.bookstore.bookstore.services;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.security.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordRestTokenForUser(final User user, final String token);

    public User createUser(User user, Set<UserRoles> userRolesSet);

    public User findByUsername( String username);

}
