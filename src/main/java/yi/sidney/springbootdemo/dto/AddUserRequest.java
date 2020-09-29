package yi.sidney.springbootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddUserRequest {
    @NotEmpty
    @JSONField(name = "mobile")
    private String mobile;

    @NotEmpty
    @JSONField(name = "name")
    private String name;
}
