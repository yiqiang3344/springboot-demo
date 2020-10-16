package yi.sidney.springbootdemo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.MongoDemoGetRequest;
import yi.sidney.springbootdemo.dto.MongoDemoRequest;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.entity.MongoDemo;
import yi.sidney.springbootdemo.service.MongoDemoService;

@Log4j2
@RestController
public class MongoController {
    @Autowired
    MongoDemoService mongoDemoService;

    @RequestMapping(value = "/mongo/add", method = RequestMethod.GET)
    @ResponseBody
    public Response add(@Validated MongoDemoRequest request) {
        MongoDemo mongoDemo = new MongoDemo();
        mongoDemo.setName(request.getName());
        mongoDemo.setMobile(request.getMobile());
        mongoDemoService.saveDemo(mongoDemo);
        return new Response().initSuccessRes(net.sf.json.JSONObject.fromObject(request));
    }

    @RequestMapping(value = "/mongo/one", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@Validated MongoDemoGetRequest request) {
        return new Response().initSuccessRes(net.sf.json.JSONObject.fromObject(mongoDemoService.findDemoById(Long.valueOf(request.getId()))));
    }
}