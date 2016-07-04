package com.andela.suada.currencyexchangerate.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by suadahaji.
 */
public class HttpUtils {
    private HttpURLConnection httpURLConnection;

    private BufferedReader bufferedReader;

    public String getData(String urlString) {
        String fetchedData = "";
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();


            InputStream stream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String newLine;


            while ((newLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(newLine)
                        .append("\n");
            }


            fetchedData = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpURLConnection.disconnect();
                bufferedReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return fetchedData;
    }

}
