package com.example.finalproject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyMailer extends Thread {
    String mailerApi = "https://script.google.com/macros/s/AKfycbyGeGfOv7wEJUNWdO1EEbkSaqzNnPbjUSk0DdK1koTcO7QpdDDzoYMibJFOtbbxUxN5Qg/exec";
    MailerListener listener;
    String to;
    String subject;
    String message;
    MyMailer(String to,String subject,String message,MailerListener mailerListener){
        this.listener = mailerListener;
        this.to = to;
        this.subject = subject;
        this.message  = message;
        this.start();

    }
    public interface MailerListener{
        void onSuccess(String response);
        void onFailed(String response);
    }
    @Override
    public void run() {
        super.run();
        try {
            //Todo : preparing connection
            URL url = new URL (mailerApi);
            HttpURLConnection con;
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            //Todo : Writing post request json data
            OutputStream outputStream = con.getOutputStream();
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(prepareJson());
            bufferedWriter.flush();
            //---------- todo : Now Read
            int statusCode = con.getResponseCode();
            System.out.println("Request Status Code : "+statusCode);
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                sb.append(result + "\n");
            }
            System.out.println("Response : \n"+sb.toString());
            if (listener!=null) listener.onSuccess(sb.toString());
            if(outputStream != null) outputStream.close();
            if(bufferedWriter!=null)  bufferedWriter.close();
            if(bufferedReader!=null)  bufferedReader.close();
            if(con!=null) con.disconnect();

        }catch (Exception e){
            if (listener!=null) listener.onFailed("Failed : "+e.getLocalizedMessage());
        }finally {
            this.stop();

        }
    }
    private String prepareJson(){
        String m = "{\n" +
                "  \"students\": {\n" +
                "    \"name\": \"Kamal\",\n" +
                "    \"Id\": 12345678\n" +
                "  }\n" +
                "}";

        String json = "{\"to\":\"" +to+
                "\",\"subject\":\"" +subject+
                "\",\"message\":\"" +message+
                "\"} ";
        return json;

    }
}
