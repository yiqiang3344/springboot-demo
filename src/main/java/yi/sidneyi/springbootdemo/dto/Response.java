package yi.sidneyi.springbootdemo.dto;

import com.alibaba.fastjson.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Response {
    private Integer code;
    private String message;

    public Response() {
        this.code = 1;
        this.message = "操作成功";
    }

    private Object data;

    public Response initSuccessRes(Object data) {
        this.code = 1;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    public Response initEmptyRes(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = new JSONPObject();
        return this;
    }
}
