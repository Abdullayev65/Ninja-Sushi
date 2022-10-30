package uz.sushi.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ApiResult<E> {

    private boolean success;

    private String message;

    private E data;

    private Integer code;

    private ApiResult(String message, E data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    private ApiResult(E data) {
        this.success = true;
        this.data = data;
    }

    private ApiResult() {
        this.success = true;
    }


    public static <T> ApiResult<T> successResponse(String message, T data) {
        return new ApiResult<>(message, data);
    }

    public static <T> ApiResult<T> successResponse(String message) {
        return new ApiResult<>(message, null);
    }

    public static <T> ApiResult<T> successResponse(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> successResponse() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> failResponse(String msg, Integer code) {
        ApiResult<T> result = new ApiResult<>(msg,null);
        result.code=code;
        result.success=false;
        return result;
    }


}
