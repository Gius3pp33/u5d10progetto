package giuseppelongo.u5d10progetto.exceptions;

import giuseppelongo.u5d10progetto.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Stato 400
    public ErrorResponseDTO handleBadRequest(BadRequestException ex) {
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // Stato 401
    public ErrorResponseDTO handleUnauthorized(UnauthorizedException ex) {
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Stato 404
    public ErrorResponseDTO handleNotFound(NotFoundException ex) {
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Stato 500
    public ErrorResponseDTO handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponseDTO("Problema lato server, lo risolveremo presto!", LocalDateTime.now());
    }
}
