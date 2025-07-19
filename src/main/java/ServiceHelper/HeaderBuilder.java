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
        headers.put("x-api-key","reqres-free-v1");
        return headers;
    }

}
