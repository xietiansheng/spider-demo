package util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static OkHttpClient okHttpClient;
    private static int num = 0;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.SECONDS)
                .connectTimeout(1, TimeUnit.SECONDS)
                .build();
    }

    public static String get(String path) {
        //创建连接客户端
        Request request = new Request.Builder()
                .url(path)
                .build();
        //创建"调用" 对象
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();//执行
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            System.out.println("链接格式有误:" + path);
        }
        return null;
    }
}
