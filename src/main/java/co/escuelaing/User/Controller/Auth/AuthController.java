package co.escuelaing.User.Controller.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.escuelaing.User.Data.User;
import co.escuelaing.User.Dto.LoginDTO;
import co.escuelaing.User.Dto.TokenDTO;
import co.escuelaing.User.Exception.InvalidCredentialsException;
import co.escuelaing.User.Service.UserService;
import static co.escuelaing.User.Data.Constants.CLAIMS_ROLES_KEY;
import static co.escuelaing.User.Data.Constants.TOKEN_DURATION_MINUTES;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping( "v1/auth" )

public class AuthController {

    @Value( "mina654" )
    String secret;

    private final UserService userService;

    public AuthController( @Autowired UserService userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDTO login( @RequestBody LoginDTO loginDto )
    {
        // TODO: Implement findByEmail method
        User user = userService.findByEmail( LoginDTO.getEmail() );
        if ( BCrypt.checkpw( LoginDTO.getPassword(), user.getPasswordHash() ) )
        {
            return generateTokenDto( user );
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken( User user, Date expirationDate )
    {
        return Jwts.builder()
            .setSubject( user.getId() )
            .claim( CLAIMS_ROLES_KEY, user.getRoles() )
            .setIssuedAt(new Date() )
            .setExpiration( expirationDate )
            .signWith( SignatureAlgorithm.HS256, secret )
            .compact();
    }

    private TokenDTO generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDTO( token, expirationDate.getTime() );
    }


}
