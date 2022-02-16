package co.escuelaing.User.Controller.Auth;

import java.util.Date;

public class TokenDTO {
    
    private String token;
   
       private Date expirationDate;
   
       public TokenDTO( String token, Date expirationDate )
       {
           this.token = token;
           this.expirationDate = expirationDate;
       }
   
       public String getToken()
       {
           return token;
       }
   
       public Date getExpirationDate()
       {
           return expirationDate;
       }
}
