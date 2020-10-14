package yi.sidney.springbootdemo.utils.log;

import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import yi.sidney.springbootdemo.utils.net.NetUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Order(5)
@Component
@Log4j2
public class AccessLogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private LogUtil logUtil;
    Map<String, String> logMap = new HashMap<String, String>();

    /**
     * 第一个*表示返回任何类型,yi.sidney.springbootdemo.controller下任何类,任何方法,任何参数
     * 也可以加入参数限定例如yi.sidney.springbootdemo.controller.*.*(..)&&args(name,..)
     * <p>
     * 下面那中表示方法也是对的,表示yi.sidney.springbootdemo.下面任何子包下任何方法,任何参数
     **/
    @Pointcut("execution(public * yi.sidney.springbootdemo.controller.*.*(..))")
    public void webLog() {
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfter(JoinPoint joinPoint, Object ret) {
        this.logMap.put("url", httpServletRequest.getRequestURL().toString());
        this.logMap.put("http_method", httpServletRequest.getMethod());
        this.logMap.put("remote_ip", httpServletRequest.getRemoteAddr());
        this.logMap.put("class_method", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if (httpServletRequest.getMethod().equals("GET")) {
            this.logMap.put("args", JSONObject.fromObject(httpServletRequest.getParameterMap()).toString());
        }
        if (httpServletRequest.getMethod().equals("POST")) {
            this.logMap.put("args", JSONObject.fromObject(joinPoint.getArgs()[0]).toString());
        }
        this.logMap.put("local_ip", NetUtil.getIpAddress());
        this.logMap.put("response", JSONObject.fromObject(ret).toString());

        logUtil.access(JSONObject.fromObject(this.logMap).toString());
    }
}
