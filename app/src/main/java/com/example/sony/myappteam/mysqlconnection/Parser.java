package com.example.sony.myappteam.mysqlconnection;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Parser {

    private String ambilPostDataString(HashMap<String, String> param) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (first) {
                first = false;
            } else result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    public String kirimRequestGet(String requestURL) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL link = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String s;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


}
