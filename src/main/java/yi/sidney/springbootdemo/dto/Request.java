package yi.sidney.springbootdemo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Request {
    @JSONField(name = "page")
    private String page;

    @JSONField(name = "page_size")
    private String page_size;
}
