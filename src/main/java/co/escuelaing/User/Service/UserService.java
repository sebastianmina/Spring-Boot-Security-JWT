package co.escuelaing.User.Service;
import java.util.List;

import co.escuelaing.User.Data.User;
//import co.escuelaing.User.Dto.UserDTO;

public interface UserService {
    
    User create( User user );

     User findById( String id );
     
     List<User> getAll();

     void deleteById( String id );

     User update( User newUser, String userId );

    User findByEmail(String email);
}
