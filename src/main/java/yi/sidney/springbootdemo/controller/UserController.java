package yi.sidney.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.AddUserRequest;
import yi.sidney.springbootdemo.dto.Request;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.dto.UserRequest;
import yi.sidney.springbootdemo.entity.User;
import yi.sidney.springbootdemo.service.UserService;

import java.util.List;

@Log4j2
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    @ResponseBody
    public Response addUser(@Validated AddUserRequest request) {
        User user = userService.save(new User(request.getName(), request.getMobile()));
        JSONObject data = new JSONObject();
        data.put("id", user.getId());
        return new Response().initSuccessRes(data);
    }

    @RequestMapping(value = "user/get", method = RequestMethod.GET)
    @ResponseBody
    public Response getUser(@Validated UserRequest request) {
        User user = userService.findById(Integer.parseInt(request.getId()));
        JSONObject data = new JSONObject();
        data.put("info", user.toString());
        return new Response().initSuccessRes(data);
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserList(@Validated Request request) {
        List<User> list = userService.findAll();
        JSONArray jsonArr = JSONArray.fromObject(list);
        JSONObject data = new JSONObject();
        data.put("list", jsonArr);
        return new Response().initSuccessRes(data);
    }

    @RequestMapping(value = "user/list/page/{page}/size/{page_size}", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserListByPage(@PathVariable Integer page, @PathVariable Integer page_size) {
        Page<User> users = userService.findAll(page - 1, page_size);
        JSONObject data = new JSONObject();
        data.put("pages", users.getTotalPages());
        data.put("total", users.getTotalElements());
        data.put("list", JSONArray.fromObject(users.getContent()));
        return new Response().initSuccessRes(data);
    }

    @RequestMapping(value = "user/delete", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteUser(@Validated UserRequest request) {
        userService.deleteById(Integer.parseInt(request.getId()));
        return new Response().initSuccessRes(null);
    }
}