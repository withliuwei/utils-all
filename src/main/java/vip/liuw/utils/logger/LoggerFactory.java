package vip.liuw.utils.logger;

public class LoggerFactory {
    // 静态变量globleLog
    private static Logger GLOBAL_LOGGER;

    private static final LoggerConfiguration loggerConfiguration = LoggerConfiguration.getInstance();

    static {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("lovejava.work");
        GLOBAL_LOGGER = new Logger("GLOBAL");
    }

    private LoggerFactory() {
    }

    public static Logger getLogger() {
        return GLOBAL_LOGGER;
    }

    public static Logger getLogger(Class cls) {
        return getLogger(cls.getName());
    }

    public static Logger getLogger(String name) {
        return new Logger(name);
    }
}
