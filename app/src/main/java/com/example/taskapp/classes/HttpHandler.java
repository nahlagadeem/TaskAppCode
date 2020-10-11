package com.example.taskapp.classes;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();
    HttpURLConnection conn;

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());


            // Method 2 with access to the URLConnection object
            // (Method 1 would have been having the connection as HttpURLConnection from the beginning.)
            int responseCode = 0;
            Log.e("1","Trying method 2 to get status code");

            try {
                if(conn != null) {
                    responseCode = ((HttpURLConnection)conn).getResponseCode();
                } else {
                    Log.e("2","conn variable not set");
                }
            } catch(IOException ex2) {
                Log.e("3","getResponseCode threw: " + ex2);
            }
            Log.e("4","Status code from calling getResponseCode: " + responseCode);
            responseCode = 0;
            Log.e("5","Trying method 3 to get status code");
            Matcher exMsgStatusCodeMatcher = Pattern.compile("^Server returned HTTP response code: (\\d+)").matcher(e.getMessage());
            if(exMsgStatusCodeMatcher.find()) {
                responseCode = Integer.parseInt(exMsgStatusCodeMatcher.group(1));
            } else if(e.getClass().getSimpleName().equals("FileNotFoundException")) {
                Log.e("6","Got a FileNotFoundException");
                responseCode = 404;
            } else {
                Log.e("7","Exception (" + e.getClass().getSimpleName() + ") doesn't contain status code: " + e);
            }
            Log.e("8","Status code from parsing exception message: " + responseCode);


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
            Log.e("httpHandelr IOException", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("httpHandeler close Exc", e.getMessage());
            }
        }

        return sb.toString();
    }
}