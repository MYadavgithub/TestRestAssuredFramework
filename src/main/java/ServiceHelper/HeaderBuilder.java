package ServiceHelper;

import java.util.HashMap;
import java.util.Map;

public class HeaderBuilder {

    public static Map<String,String> getHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("content-Type","application/json");
        headers.put("x-consumer-name","AG");
        headers.put("deviceId","OnePlus9");
        headers.put("platform","android");
        return headers;
    }

    public static Map<String,String> getIOSHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("content-Type","application/json");
        headers.put("x-consumer-name","AG");
        headers.put("deviceId","iphone-13");
        headers.put("platform","IOS");
        return headers;
    }
}
