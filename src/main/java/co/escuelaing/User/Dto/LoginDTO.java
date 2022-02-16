package co.escuelaing.User.Dto;

public class LoginDTO {
    
    String email;
    String password;

    public LoginDTO( String email, String password ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
