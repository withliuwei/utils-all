package vip.liuw.utils.logger;

import lombok.Getter;
import lombok.Setter;
import vip.liuw.utils.FileUtils;
import vip.liuw.utils.DateUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Getter
@Setter
public class Logger {
    private String name;

    private LoggerLevel loggerLevel;

    private LoggerConfiguration loggerConfiguration;

    Logger(String name) {
        this.name = name;
        loggerConfiguration = LoggerConfiguration.getInstance();
    }

    Logger(Class cls) {
        this(cls.getName());
    }

    public void debug(String message) {
        log(message);
    }

    public void debug(String format, Object... args) {
        log(format, args);
    }

    public void info(String message) {
        log(message);
    }

    public void info(String format, Object... args) {
        log(format, args);
    }

    public void error(String message) {
        log(message);
    }

    public void error(String format, Object... args) {
        log(format, args);
    }

    public void log(String message) {
        message = format(message);
        System.out.println(message);
        writeToFile(message);
    }

    public void log(String format, Object... args) {
        StringBuilder sb = new StringBuilder(format);
        for (Object arg : args) {
            sb.append(arg.toString());
        }
        log(sb.toString());
    }

    void print(String massage, Throwable throwable) {

    }

    void print(String format, Object... args) {

    }


    private void writeToFile(String message) {
        if (loggerConfiguration.isConfigFile()) {
            try {
                FileWriter writer = loggerConfiguration.getWriter();
                synchronized (writer) {
                    writer.append(message);
                    writer.write(FileUtils.lineSeparator);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String format(String mesasge) {
        return DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")
                + "  " + loggerConfiguration.getLOG_LEVEL()
                + "  [" + Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + "]"
                + " " + name
                + " : " + mesasge;

    }


}
