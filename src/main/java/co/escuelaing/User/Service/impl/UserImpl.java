package co.escuelaing.User.Service.impl;

import java.util.List;

import co.escuelaing.User.Data.User;
//import co.escuelaing.User.Dto.UserDTO;
import co.escuelaing.User.Service.UserService;

import java.util.ArrayList;
//import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import org.springframework.stereotype.Service;


public class UserImpl implements UserService {

    private final ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();

    @Override
    public User create(User newUser) {
        User user = users.putIfAbsent(newUser.getId(),newUser);
        return user;
    }

    @Override
    public User findById(String id) {
        User userReturn = null;
        for (Map.Entry<String,User> entry : users.entrySet()){
            if (entry.getKey().equals(id)){
                userReturn = entry.getValue();
            }
        }
        return userReturn;
    }

    @Override
    public List<User> getAll() {
        List<User> usersReturn = new ArrayList<>();
        for (Map.Entry<String,User> entry : users.entrySet()){
            usersReturn.add(entry.getValue());
        }
        return usersReturn;
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }


    @Override
    public User update(User newUser, String userId) {
        User user = users.putIfAbsent(newUser.getId(),newUser);
        if (user!=null){
            users.replace(newUser.getId(),user,newUser);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
