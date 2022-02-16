package co.escuelaing.User.Controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.escuelaing.User.Data.User;
import co.escuelaing.User.Dto.UserDTO;
import co.escuelaing.User.Service.UserService;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {
    
    private final UserService userService;
    private final AtomicLong counter = new AtomicLong(0);

	 public UserController(@Autowired UserService userService) {
		 this.userService = userService;
	 }

     @GetMapping
     public ResponseEntity<List<User>> getAll() {
          return new ResponseEntity<List<User>>(userService.getAll() , HttpStatus.OK );
     }
    
     @GetMapping( "/{id}" )
     public ResponseEntity<User> findById( @PathVariable String id ) {
          return new ResponseEntity<User>(userService.findById(id) , HttpStatus.OK );
     }
    
    
     @PostMapping
     public ResponseEntity<User> create( @RequestBody UserDTO userDto ) {
          User userCreation = new User((Integer.toString((int) counter.incrementAndGet())), userDto.getName() , userDto.getEmail() ,
          userDto.getLastName() , Date.from(Instant.now()));
  return new ResponseEntity<User>(userService.create(userCreation), HttpStatus.OK );
     }
    
     @PutMapping( "/{id}" )
     public ResponseEntity<User> update( @RequestBody UserDTO userDto, @PathVariable String id ) {
          User user = userService.findById(id);
          user.setEmail(userDto.getEmail());
          user.setName(userDto.getName());
          user.setLastName(userDto.getLastName());
          return new ResponseEntity<User>(userService.update(user , id ), HttpStatus.OK );
     }

     @DeleteMapping( "/{id}" )
     public ResponseEntity<Boolean> delete( @PathVariable String id ) {
          try{
               userService.deleteById(id);
               return new ResponseEntity<Boolean>(true , HttpStatus.OK );
           }catch (Exception e){
               return new ResponseEntity<Boolean>(false , HttpStatus.OK );
           }     
     }
}
