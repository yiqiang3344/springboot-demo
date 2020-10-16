package yi.sidney.springbootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MongoDemoRequest {
    @NotEmpty
    @JSONField(name = "name")
    private String name;

    @NotEmpty
    @JSONField(name = "mobile")
    private String mobile;
}
