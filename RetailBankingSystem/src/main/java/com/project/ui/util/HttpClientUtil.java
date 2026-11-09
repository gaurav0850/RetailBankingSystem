package com.project.ui.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for making HTTP requests to the backend
 */
public class HttpClientUtil {

    private static final String BASE_URL = "http://localhost:8080";
    private static final int TIMEOUT = 5000;

    /**
     * Makes a POST request with form parameters
     *
     * @param endpoint The endpoint path (e.g., "/employeeHome")
     * @param params Map of parameter key-value pairs
     * @return HTTP response code
     * @throws Exception if the request fails
     */
    public static int makePostRequest(String endpoint, Map<String, String> params) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);

        // Build query string
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (query.length() > 0) {
                query.append("&");
            }
            query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            query.append("=");
            query.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = query.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        return connection.getResponseCode();
    }

    /**
     * Makes a GET request
     *
     * @param endpoint The endpoint path (e.g., "/api/data")
     * @return HTTP response code
     * @throws Exception if the request fails
     */
    public static int makeGetRequest(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);

        return connection.getResponseCode();
    }

    /**
     * Checks if the backend server is available
     *
     * @return true if available, false otherwise
     */
    public static boolean isServerAvailable() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.disconnect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reads response from connection
     *
     * @param connection The HTTP connection
     * @return Response body as String
     * @throws Exception if reading fails
     */
    public static String readResponse(HttpURLConnection connection) throws Exception {
        try (InputStream is = connection.getInputStream()) {
            byte[] bytes = is.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}

