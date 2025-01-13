package org.example;

import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;

public class CoinDeskAPIValidation {


        public static void main(String[] args) {
            // Define the API endpoint
            String apiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

            // Create an HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create an HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            try {
                // Send the GET request and get the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Parse the response body
                JSONObject jsonResponse = new JSONObject(response.body());

                // Extract the "bpi" object
                JSONObject bpi = jsonResponse.getJSONObject("bpi");

                // Verify the presence of USD, GBP, and EUR keys
                if (bpi.has("USD") && bpi.has("GBP") && bpi.has("EUR")) {
                    System.out.println("All required BPIs are present: USD, GBP, EUR.");

                    // Verify the GBP 'description' equals 'British Pound Sterling'
                    JSONObject gbp = bpi.getJSONObject("GBP");
                    String description = gbp.getString("description");

                    if ("British Pound Sterling".equals(description)) {
                        System.out.println("GBP description matches: " + description);
                    } else {
                        System.out.println("GBP description does not match. Found: " + description);
                    }
                } else {
                    System.out.println("Required BPIs are missing.");
                }

            } catch (Exception e) {
                // Handle exceptions
                System.err.println("Error occurred: " + e.getMessage());
            }
        }
    }
