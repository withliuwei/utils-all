package vip.liuw.utils.logger;

import lombok.Getter;
import lombok.Setter;
import vip.liuw.utils.FileUtils;
import vip.liuw.utils.properties.PropertiesUtils;
import vip.liuw.utils.properties.PropertiesWrapper;

import java.io.*;

@Getter
@Setter
public class LoggerConfiguration {
    boolean init = false;
    boolean configFile = false;
    static final String CONFIG_PREFIX = "logger";
    String LOG_FILE_ABSOLUTE_PATH;
    /**
     * 配置文件
     */
    private String CONFIG_FILE = "logger.properties";
    /**
     * 日志文件
     */
    private String LOG_FILE = "logs/jdk.log";
    /**
     * 日志级别
     */
    private String LOG_LEVEL = "DEBUG";

    private File logFile;

    FileWriter writer;

    private static LoggerConfiguration instance;

    void init() {
        File file = FileUtils.getClassPathFile(CONFIG_FILE);
        if (file.exists()) {
            PropertiesWrapper wrapper = PropertiesUtils.load(file);
            LOG_FILE = wrapper.getValueOrDefault(CONFIG_PREFIX + ".file", LOG_FILE);
        }
        logFile = new File(LOG_FILE);
        if (!logFile.exists())
            logFile = FileUtils.getClassPathFile(LOG_FILE);
        if (!logFile.exists()) {
            try {
                File parent = logFile.getParentFile();
                if (!parent.exists())
                    configFile = parent.mkdirs();
                if (configFile)
                    configFile = logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            configFile = true;
        }
        if (configFile) {
            try {
                writer = new FileWriter(logFile, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOG_FILE_ABSOLUTE_PATH = logFile.getPath();
    }

    static LoggerConfiguration getInstance() {
        if (instance == null) {
            instance = new LoggerConfiguration();
            instance.init();
        }
        return instance;
    }

}
