package com.bookstore.bookstore.services;


import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.PasswordResetTokenRepository;
import com.bookstore.bookstore.repositories.UserRepository;
import com.bookstore.bookstore.security.PasswordResetToken;

import com.bookstore.bookstore.security.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private RoleRepository roleRepository;

   // @Autowired
    //private UserPaymentRepository userPaymentRepository;

    //@Autowired
    //private UserShippingRepository userShippingRepository;

    private  PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public UserServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordRestTokenForUser(User user, String token) {

        final PasswordResetToken myToken=new PasswordResetToken(token, user);

        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    /*
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    */
    @Override
    @Transactional
    public User createUser(User user, Set<UserRoles> userRolesSet) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser != null){
            LOG.info("user {} already exists.", user.getUsername());
        } else {
            for (UserRoles ur : userRolesSet) {
             //   roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRolesSet);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            //user.setUserShippingList(new ArrayList<UserShipping>());
           // user.setUserPaymentList(new ArrayList<UserPayment>());

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    //@Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
