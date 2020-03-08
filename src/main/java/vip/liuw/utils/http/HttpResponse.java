package vip.liuw.utils.http;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HttpResponse {
    public HttpResponse() {
        header = new HashMap<>();
        body = new Body();
    }

    private int status;

    private Body body;

    private Map<String, String> header;

    public String getHeader(String headerName) {
        return header.get(headerName);
    }

    public boolean isSuccessful() {
        return status == 200;
    }

    @Getter(value = AccessLevel.PACKAGE)
    @Setter(value = AccessLevel.PACKAGE)
    public class Body {

        byte[] data;

        public String toString() {
            return new String(data, StandardCharsets.UTF_8);
        }

    }
}
