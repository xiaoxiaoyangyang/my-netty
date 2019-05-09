package com.baizhi.urlTest;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class My {

    public static void main(String[] args) throws Exception {
        uu();
    }

    public static void uu() throws Exception {
        URL url = new URL("http://10.5.6.3:10228/imda/alarm/_search");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.connect();

        String body = "{\n" +
                "  \"sort\": [\n" +
                "    {\n" +
                "      \"receivedTime\": {\n" +
                "        \"order\": \"desc\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"from\": 0,\n" +
                "  \"size\": 1\n" +
                "}";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        writer.write(body);
        writer.close();

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
//            DataInputStream dataInputStream = new DataInputStream(inputStream);
//            String s = dataInputStream.readLine();
//            System.out.println(s);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(inputStreamReader);
            StringBuilder sb=new StringBuilder();
            String line=null;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb);
        }
    }
}

