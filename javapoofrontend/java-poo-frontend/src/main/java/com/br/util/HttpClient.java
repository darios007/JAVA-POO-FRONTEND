package com.br.pdvfrontend.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static String sendRequest(String urlString, String method, String body) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (body != null && !body.isEmpty()) {
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = body.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
