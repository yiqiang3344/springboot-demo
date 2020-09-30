package yi.sidney.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.Request;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.dto.TestRequest;
import yi.sidney.springbootdemo.utils.redis.RedisUtil;

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
    public Response test1(@Validated TestRequest request) {
        log.info(request.getTest());
        JSONObject data = new JSONObject();
        data.put("test", request.getTest());
        return new Response().initSuccessRes(data);
    }

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "redis/set", method = RequestMethod.GET)
    @ResponseBody
    public Response redisSet(@Validated Request request) {
        boolean ret = redisUtil.set("yjqtest", "yjq");
        return new Response().initSuccessRes(ret);
    }

    @RequestMapping(value = "redis/get/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Response redisSet(@PathVariable String key) {
        Object ret = redisUtil.get(key);
        return new Response().initSuccessRes(ret);
    }

    @RequestMapping(value = "redis/push/{key}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public Response redisPush(@PathVariable String key, @PathVariable String value) {
        boolean ret = redisUtil.lSet(key, value);
        return new Response().initSuccessRes(ret);
    }

    @RequestMapping(value = "redis/pop/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Response redisPop(@PathVariable String key) {
        Object ret = redisUtil.lPop(key);
        return new Response().initSuccessRes(ret);
    }
}