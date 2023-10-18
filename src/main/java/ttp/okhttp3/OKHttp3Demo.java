package ttp.okhttp3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.common.hash.Hashing;
import org.apache.commons.io.FileUtils;

public class OKHttp3Demo {
    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("http://mss.vip.sankuai.com/real_time_build_user_jar/0a3501733ee5f0ac3a8a3ffade1364fe?Signature=ZPjOQXsbYu5nWtKm3hpBO%2ByPwxY%3D&Expires=1632736799&AWSAccessKeyId=ab0ae5ff1db945889a77eded7a569985")
//                .method("GET", null)
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Upgrade-Insecure-Requests", "1")
//                .addHeader("DNT", "1")
//                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
//                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                .addHeader("Referer", "http://rt.sankuai.com/")
//                .addHeader("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,ja;q=0.6,ja-JP;q=0.5")
//                .addHeader("Cookie", "_lxsdk_cuid=17aad395763c8-0121b41b2ef8bf-34647600-1ea000-17aad395763c8; _lxsdk=17aad395763c8-0121b41b2ef8bf-34647600-1ea000-17aad395763c8; s_u_745896=\"jkFv2X/lv/7asMg/aHjWrg==\"; s_m_id_3299326472=\"AwMAAAA5AgAAAAEAAAE9AAAALAtnQwSP7BGX0oslc6iA9vQ6taZR9AF+qFATgZFIY3b/3QFuqVzxyxowDTVHAAAAIzNWguu0yVaYkJrHElbTcjjT6/p/YrpATeDLN4Gk+7afAzJV\"")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            byte[] bs = new byte[1024];
//            FileOutputStream os = new FileOutputStream("tmp.jar");
//            try (InputStream is = Objects.requireNonNull(response.body()).byteStream()) {
//                int size = is.read(bs);
//                while (size > 0) {
//                    System.out.println(new String(bs));
//                    os.write(bs, 0, size);
//                    size = is.read(bs);
//                }
//            }
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String address = "http://mss.vip.sankuai.com/real_time_build_user_jar/b607833be2246b2ec66ef8f9f84cde37?Signature" +
                "=ekM2TRK2oRizjpJGbniOBgkbmKk%3D&Expires=1642759082&AWSAccessKeyId=ab0ae5ff1db945889a77eded7a569985";
        URL url = new URL("http://mss.vip.sankuai.com/real_time_build_user_jar/b607833be2246b2ec66ef8f9f84cde37?Signature=ekM2TRK2oRizjpJGbniOBgkbmKk%3D&Expires=1642759082&AWSAccessKeyId=ab0ae5ff1db945889a77eded7a569985");
        // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
        // 此时cnnection只是为一个连接对象,待连接中
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置是否要从 URL连接读取数据,默认为true
        conn.setDoInput(true);
        // 建立连接
        // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
        conn.connect();
        // 连接发起请求,处理服务器响应 (从连接获取到输入流)
        InputStream iputstream = conn.getInputStream();
        byte[] buffer = new byte[4 * 1024];
        int byteRead = -1;
        //  循环读取流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((byteRead = (iputstream.read(buffer))) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }
        System.out.println(outputStream.toByteArray().length);

        File file = new File("./tmp.jar");
        FileUtils.copyURLToFile(new URL(address), file);
        System.out.println(file.length());
//        System.out.println(calculateMD5("/Users/djh/Downloads/0a3501733ee5f0ac3a8a3ffade1364fe.jar"));
//        System.out.println(calculateMD5("tmp.jar"));

    }

    public static String calculateMD5(String path) throws IOException {
        FileInputStream is = new FileInputStream(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int size = is.read(bytes);
        while (size > 0) {
            bos.write(bytes, 0, size);
            size = is.read(bytes);
        }
        return Hashing.md5().hashBytes(bos.toByteArray()).toString();
    }
}
