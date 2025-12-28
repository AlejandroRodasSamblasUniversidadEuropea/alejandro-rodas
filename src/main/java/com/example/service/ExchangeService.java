package com.example.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ExchangeService {

    public static double getEurUsdRate() {
        try {
            URI uri = URI.create("https://open.er-api.com/v6/latest/EUR");
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status != 200) {
                System.out.println("Error: HTTP status " + status);
                return 0;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) response.append(line);
            in.close();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.toString(), JsonObject.class);

            if (json.has("result") && "success".equalsIgnoreCase(json.get("result").getAsString())) {
                JsonObject rates = json.getAsJsonObject("rates");
                if (rates.has("USD")) {
                    double rate = rates.get("USD").getAsDouble();
                    System.out.println("Tipo de cambio EUR→USD real: " + rate);
                    return rate;
                }
            }

            System.out.println("No se encontró USD en rates o result != success");
        } catch (Exception e) {
            System.out.println("Error al obtener tipo de cambio: " + e.getMessage());
        }

        return 0; // 0 indica fallo
    }
}
