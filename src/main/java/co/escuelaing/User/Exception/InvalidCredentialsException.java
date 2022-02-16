package co.escuelaing.User.Exception;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.http.HttpStatus;

import co.escuelaing.User.Data.ErrorCodeEnum;
import co.escuelaing.User.Dto.ServerErrorResponseDTO;

public class InvalidCredentialsException extends InternalServerErrorException {
    public InvalidCredentialsException() {

        super(new ServerErrorResponseDTO("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND).getMessage());

     }
}
