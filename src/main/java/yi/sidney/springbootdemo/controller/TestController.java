package yi.sidney.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.dto.TestRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Log4j2
@RestController
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) {
        String retrunValue = "Hello, Angus! This is GET request!";
        System.out.println("=======GET Process=======");

        Map<String, String[]> requestMsg = request.getParameterMap();
        Enumeration<String> requestHeader = request.getHeaderNames();

        System.out.println("------- header -------");
        while (requestHeader.hasMoreElements()) {
            String headerKey = requestHeader.nextElement().toString();
            //打印所有Header值

            System.out.println("headerKey=" + headerKey + ";value=" + request.getHeader(headerKey));
        }

        System.out.println("------- parameter -------");
        for (String key : requestMsg.keySet()) {
            for (int i = 0; i < requestMsg.get(key).length; i++) {
                //打印所有请求参数值

                System.out.println("key=" + key + ";value=" + requestMsg.get(key)[i].toString());
            }
        }
        return retrunValue;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public Response testGet(@Validated TestRequest request) {
        log.info(request.getTest());
        JSONObject data = new JSONObject();
        data.put("test", request.getTest());
        return new Response().initSuccessRes(data);
    }

    @RequestMapping(value = "test/post", method = RequestMethod.POST)
    @ResponseBody
    public Response testPost(@RequestBody Map<String, String> request) throws InterruptedException {
        Thread.sleep(2000);
        return new Response().initSuccessRes(net.sf.json.JSONObject.fromObject(request));
    }
}