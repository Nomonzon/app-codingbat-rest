package com.example.appcodingbatrest.service;

import com.example.appcodingbatrest.entities.User;
import com.example.appcodingbatrest.payload.Message;
import com.example.appcodingbatrest.repository.UserRepository;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * TO GET USER LIST FROM DATABASE
     * @return USER LIST
     */
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * TO GET USER BY ID FROM DATABASE
     * @param id LONG
     * @return USER
     */
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    /**
     * TO ADD USER TO DATABASE
     * IF USER WAS EXITED EV
     */
    public Message addUser(User user){
        boolean existsByEmail =
                userRepository.existsByEmail(user.getEmail());

        if (existsByEmail){
            return new Message("This email already exists.", false);
        }
        userRepository.save(user);
        return new Message("Success email is added.", true);
    }

    /**
     * TO EDIT USER
     * @param user
     * @param id
     * @return
     */

    public Message editUser(User user, Long id){
        boolean emailAndIdNot = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        if (emailAndIdNot){
            return new Message("This email already exists.", false);
        }
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            return new Message("This email not found by id.", false);
        }
        User editedUser = userOptional.get();
        editedUser.setEmail(user.getEmail());
        editedUser.setPassword(user.getPassword());
        userRepository.save(editedUser);
        return new Message("Success edited.", true);
    }

    public Message deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return new Message("Success", true);
        }
        catch (Exception e){
            return  new Message("Error", false);
        }
    }
}
