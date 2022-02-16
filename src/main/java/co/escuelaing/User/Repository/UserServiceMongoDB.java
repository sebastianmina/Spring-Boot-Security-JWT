package co.escuelaing.User.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.escuelaing.User.Data.User;
import co.escuelaing.User.Service.UserService;

@Service
public class UserServiceMongoDB implements UserService {
    private final UserRepository userRepository;
   
       public UserServiceMongoDB(@Autowired UserRepository userRepository )
       {
           this.userRepository = userRepository;
       }

    @Override
    public User create(User user) {
        System.out.print("entra"); 
        return userRepository.save(user); 
    }

    @Override
    public User findById(String id) {

        if(userRepository.existsById(id))return userRepository.findById(id).get();
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);  
    }

    @Override
    public User update(User newUser, String userId) {
        if(userRepository.existsById(userId)){
            User actualUser = userRepository.findById(userId).get(); 
            actualUser.setCreatedAt(newUser.getCreatedAt());
            actualUser.setEmail(newUser.getEmail());
            actualUser.setLastName(newUser.getLastName());
            actualUser.setName(newUser.getName());
            userRepository.save(actualUser); 
            return actualUser; 
        } 
        return null; 
    }
}
