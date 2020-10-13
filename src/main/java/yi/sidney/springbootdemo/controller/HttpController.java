package yi.sidney.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;
import yi.sidney.springbootdemo.dto.Response;
import yi.sidney.springbootdemo.utils.httpClient.HttpClientUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HttpController {
    @RequestMapping(value = "http/get", method = RequestMethod.GET)
    @ResponseBody
    public Response httpGet(@RequestParam(name = "test") String test) throws IOException, URISyntaxException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("test", test);
        String ret = HttpClientUtil.doGet("http://localhost:8082/test1", map);
        return new Response().initSuccessRes(ret);
    }

    @RequestMapping(value = "http/post", method = RequestMethod.GET)
    @ResponseBody
    public Response httpPost(@RequestParam(name = "test") String test) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("test", test);
        String ret = HttpClientUtil.doPostJson("http://localhost:8082/test/post", net.sf.json.JSONObject.fromObject(map).toString(), 1000);
        return new Response().initSuccessRes(ret);
    }
}