package com.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class ExchangeService {

    public static double getEurUsdRate() {
        try {

            URI uri = URI.create("https://api.exchangerate.host/latest?base=EUR&symbols=USD");
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();

            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            String json = response.toString();

            int pos = json.indexOf("\"USD\":");
            if (pos != -1) {
                int start = pos + 6;
                int end = json.indexOf("}", start);
                return Double.parseDouble(json.substring(start, end));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener tipo de cambio: " + e.getMessage());
        }

        return 1.0;
    }
}
