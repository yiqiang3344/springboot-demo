package yi.sidney.springbootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MongoDemoGetRequest {
    @NotEmpty
    @JSONField(name = "id")
    private String id;
}
