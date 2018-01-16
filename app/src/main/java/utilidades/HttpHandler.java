package utilidades;

import android.util.Base64;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl, String user, String pass) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            Log.e(HttpHandler.class.getSimpleName(), "Requested URL: " + url);
            Log.e(HttpHandler.class.getSimpleName(), "Credentials: " + user + ", " + pass);
            Log.e(HttpHandler.class.getSimpleName(), "Opening connection...");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.e(HttpHandler.class.getSimpleName(), "Adding authorization request property");
            conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encode((user + ":" + pass ).getBytes(), Base64.NO_WRAP)));
            conn.setUseCaches(false);
            Log.e(HttpHandler.class.getSimpleName(), "Setting request method: GET");
            conn.setRequestMethod("GET");
            // read the response
            Log.e(HttpHandler.class.getSimpleName(), "Getting input stream");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Log.e(HttpHandler.class.getSimpleName(), "Building response");
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
