package yi.sidney.springbootdemo.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletResponse;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Response {
    private int code;
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

    public JSONObject buildErrorResult(int code, String message, int httpstatus, HttpServletResponse response){
        response.setStatus(httpstatus);
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("code", code)
                .fluentPut("message", message);
        if (200 == httpstatus) {
            return jsonObject.fluentPut("data", new JSONObject());
        }
        return jsonObject;

    }
}
