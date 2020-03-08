package vip.liuw.utils;

import vip.liuw.utils.http.HttpResponse;
import vip.liuw.utils.http.HttpUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtils {

    public static String getIP() {
        String data = null;
        try {
            HttpResponse httpResponse = HttpUtils.doGet("https://api.ip.sb/jsonip");
            try {
                data = httpResponse.getBody().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CheckUtils.isNotNull(data)) {
            Pattern pattern = Pattern.compile("[\\d.]+");
            Matcher matcher = pattern.matcher(data);
            if (matcher.find())
                return matcher.group();
        }
        return data;
    }


    public static void main(String[] args) {
        System.out.println(getIP());
    }
}
