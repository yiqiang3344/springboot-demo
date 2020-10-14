package yi.sidney.springbootdemo.utils.log;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LogUtil {
    public void access(String message) {

        log.log(Level.forName("ACCESS", 310), message);
    }

    public void webclient(String message) {

        log.log(Level.forName("WEBCLIENT", 320), message);
    }
}
