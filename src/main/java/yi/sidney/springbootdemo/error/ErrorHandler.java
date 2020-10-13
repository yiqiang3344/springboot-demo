package yi.sidney.springbootdemo.error;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import yi.sidney.springbootdemo.dto.Response;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@ControllerAdvice
public class ErrorHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JSONObject error(Exception e, HttpServletResponse response) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = manvException.getBindingResult();
            StringBuffer errorString = new StringBuffer();
            errorString.append("参数不合法[");
            bindingResult.getAllErrors().forEach(
                    (error) -> {
                        FieldError fieldError = (FieldError) error;
                        errorString.append(fieldError.getField())
                                .append(":")
                                .append(fieldError.getDefaultMessage())
                                .append(",");
                    }
            );
            errorString.append("]");
            return new Response().buildErrorResult(-1, errorString.toString(), 500, response);
        }
        return new Response().buildErrorResult(-1, e.getClass().getSimpleName() + ":" + e.getLocalizedMessage(), 500, response);
    }
}
