package vip.liuw.utils;

import java.io.File;
import java.net.URL;

public class FileUtils {

    public static String lineSeparator = java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("line.separator"));

    public static File getClassPathFile(String relativePath) {
        if (relativePath == null)
            return null;
        if (relativePath.startsWith("\\") || relativePath.startsWith("/"))
            relativePath = relativePath.substring(1);

        URL root = FileUtils.class.getClassLoader().getResource("");
        if (root == null)
            root = FileUtils.class.getResource("/");
        return new File(root.getPath() + relativePath);
    }
}
