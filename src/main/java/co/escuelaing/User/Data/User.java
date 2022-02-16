package co.escuelaing.User.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import co.escuelaing.User.Dto.UserDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class User {
    @Id
    String id;
  
    String name;
 
    @Indexed( unique = true )
    String email;
 
    String lastName;
 
    Date createdAt;

    private String passwordHash;
    private List<RoleEnum> roles;
 
    public User()
    {
    }

    public User(String id , String name, String email, String lastName, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public User(UserDTO userDTO){
        this.passwordHash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.createdAt = Date.from(Instant.now());
        this.roles = new ArrayList<>() ;
        if(userDTO.getRole() == 0) {
            roles.add(RoleEnum.ADMIN);
        }else {
            roles.add(RoleEnum.USER);
        }
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAtString){
        this.createdAt = createdAtString;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
}
