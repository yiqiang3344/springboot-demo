package yi.sidneyi.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;

import java.util.Set;

@EnableAutoConfiguration
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);

        Set<ApplicationListener<?>> ls = app.getListeners();

        ApplicationStartedEventListener asel = new ApplicationStartedEventListener();

        app.addListeners(asel);
        app.run(args);
    }
}
