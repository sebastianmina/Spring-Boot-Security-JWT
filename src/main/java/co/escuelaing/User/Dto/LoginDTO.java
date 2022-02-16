package co.escuelaing.User.Dto;

public class LoginDTO {
    
    static String email;
    static String password;

    public LoginDTO( String email, String password ) {
        this.email = email;
        this.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }
}
