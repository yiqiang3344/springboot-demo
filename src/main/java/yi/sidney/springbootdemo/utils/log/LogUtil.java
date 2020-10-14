package yi.sidney.springbootdemo.utils.log;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public final class LogUtil {

    public void webclient(String message) {
        log.log(Level.forName("WEBCLIENT", 320), message);
    }
}
