package yi.sidneyi.springbootdemo;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

public class ApplicationStartedEventListener implements GenericApplicationListener {

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static final Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class,
            ContextClosedEvent.class, ApplicationFailedEvent.class};

    private static final Class<?>[] SOURCE_TYPES = {SpringApplication.class,
            ApplicationContext.class};

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment env = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            if (env == null) {
                return;
            }
            //设置log4j2的日志路径，但会在根目录留下一个多余的目录`${sys:logPath}`
            if (env.containsProperty("logging.path")) {
                StringBuilder logPath = new StringBuilder();
                logPath.append(env.getProperty("logging.path"));
                if (env.containsProperty("spring.application.name")) {
                    logPath
                            .append("/")
                            .append(env.getProperty("spring.application.name"));
                }
                System.setProperty("logPath", logPath.toString());
            }
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return DEFAULT_ORDER;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.context.event.GenericApplicationListener#
     * supportsEventType(org.springframework.core.ResolvableType)
     */
    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }
}
