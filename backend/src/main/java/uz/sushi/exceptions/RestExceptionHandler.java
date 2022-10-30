package uz.sushi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.sushi.payload.ApiResult;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(RestException ex) {
        log.error("Exception: ", ex);
        ApiResult<Object> result =
                ApiResult.failResponse(ex.getMessage(),
                        ex.getStatus().value());
        return new ResponseEntity<>(result, ex.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(
            MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        ApiResult<Object> apiResult = ApiResult.failResponse("TODO", 465);
        return new ResponseEntity<>(apiResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(EmptyResultDataAccessException ex) {
        ApiResult<Object> result =
                ApiResult.failResponse(ex.getMessage(),
                        HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(AccessDeniedException ex) {
        ApiResult<Object> apiResult = ApiResult.failResponse(
                "You do not have access!",
                HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(apiResult, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InsufficientAuthenticationException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(InsufficientAuthenticationException ex) {
        ApiResult<Object> apiResult = ApiResult.failResponse(
                "Full authentication is required to access this resource",
                HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(apiResult, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(Exception ex) {
        System.out.println(Thread.currentThread().getName());
        log.error("Exception: ", ex);
        ApiResult<Object> apiResult = ApiResult.failResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = AccountStatusException.class)
    public ResponseEntity<ApiResult<Object>> exceptionHandle(AccountStatusException ex) {
        log.error("Exception: ", ex);
        ApiResult<Object> apiResult = ApiResult.failResponse(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(apiResult, HttpStatus.UNAUTHORIZED);
    }


}
