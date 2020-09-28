package yi.sidneyi.springbootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Request {
    @NotEmpty(message = "不能为空")
    @JSONField(name = "test")
    private String test;
}
