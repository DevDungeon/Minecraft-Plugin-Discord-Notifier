package com.devdungeon.minecraft.discordnotifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Discord {

    private static String webhookUrl;

    public static void setDiscordWebhook(String newWebhook) {
        Discord.webhookUrl = newWebhook;
    }

    static void discordPost(String message) {

        try {
            URL remoteUrl = null;
            try {
                remoteUrl = new URL(Discord.webhookUrl);
            } catch (MalformedURLException ex) {
                System.out.println(ex);
            }
            HttpURLConnection conn = null;
            BufferedReader in = null;
            String inputLine;

            conn = (HttpURLConnection) remoteUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Java");
            conn.setDoOutput(true);
            String jsonMessage = "{\"content\": \"" + message + "\"}";
            conn.getOutputStream().write(jsonMessage.getBytes("UTF-8"));


            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}