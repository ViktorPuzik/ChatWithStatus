package academy.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ServiceMessage {
    public static int sendService() throws IOException {
       HttpURLConnection http = null;

        try {
            URL url = new URL(Utils.getURL() + "/users");
            http = (HttpURLConnection) url.openConnection();
            InputStream is = http.getInputStream();
            try {
                byte[] buf = responseBodyToArray(is);
                String strBuf = new String(buf, StandardCharsets.UTF_8);
                List <String> lst = new ArrayList<>();
                lst = new Gson().fromJson(strBuf, List.class);
                System.out.print("Users online:");
                for (String str: lst) {
                    System.out.println(str);
                }
                return http.getResponseCode();
            } finally {
                is.close();
                return http.getResponseCode();
            }
        } catch (Exception ex) {
                ex.printStackTrace();
            }
        return http.getResponseCode();
    }

    private static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();
    }
}
