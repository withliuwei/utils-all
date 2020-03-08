package vip.liuw.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static HttpResponse doGet(String url) throws IOException {
        byte[] res = new byte[0];
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        HttpResponse httpResponse = getResponse(conn);
        conn.disconnect();
        return httpResponse;
    }

    private static HttpResponse getResponse(HttpURLConnection conn) throws IOException {
        HttpResponse response = new HttpResponse();
        response.setStatus(conn.getResponseCode());

        //add header
        conn.getHeaderFields().forEach((k, v) -> {
            if (v.size() > 1) {
                StringBuilder sb = new StringBuilder();
                v.forEach(value -> sb.append(value).append(";;"));
                response.getHeader().put(k, sb.toString());
            } else {
                response.getHeader().put(k, v.get(0));
            }
        });

        byte[] res = new byte[0];
        InputStream in;
        if (response.isSuccessful()) {
            in = conn.getInputStream();

        } else {
            in = conn.getErrorStream();
        }

        byte[] bs = new byte[1024];
        int length;
        while ((length = in.read(bs)) > -1) {
            byte[] temp = new byte[res.length + length];
            System.arraycopy(res, 0, temp, 0, res.length);
            System.arraycopy(bs, 0, temp, res.length, length);
            res = temp;
        }

        response.getBody().setData(res);

        String contentType = response.getHeader("Content-Type");
        return response;
    }
}
