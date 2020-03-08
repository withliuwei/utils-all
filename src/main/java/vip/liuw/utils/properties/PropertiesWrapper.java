package vip.liuw.utils.properties;

import lombok.Getter;
import lombok.Setter;
import vip.liuw.utils.CheckUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesWrapper extends Properties {
    @Getter
    @Setter
    private File file;

    public String getValueOrDefault(String key, String defaultValue) {
        String value = super.getProperty(key);
        return CheckUtils.isNull(value) ? defaultValue : value;
    }

    public void store(String key, String value) {
        store(key, value, null);
    }

    public void store(String key, String value, String comments) {
        super.setProperty(key, value);
        try {
            super.store(new FileOutputStream(file), comments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
