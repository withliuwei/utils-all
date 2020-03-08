package vip.liuw.utils.properties;

import vip.liuw.utils.logger.Logger;
import vip.liuw.utils.logger.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertiesUtils {

    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    private PropertiesUtils() {
    }

    public static PropertiesWrapper load(File file) {
        PropertiesWrapper wrapper = new PropertiesWrapper();
        if (file.exists()) {
            try {
                wrapper.load(new FileInputStream(file));
                wrapper.setFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException(new FileNotFoundException(file.getPath()));
        }
        return wrapper;
    }
}
