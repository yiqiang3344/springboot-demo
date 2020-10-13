package yi.sidney.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.Request;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.utils.redis.RedisUtil;

@RestController
public class RedisController {
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