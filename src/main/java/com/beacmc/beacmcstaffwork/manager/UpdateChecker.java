package com.beacmc.beacmcstaffwork.manager;

import com.beacmc.beacmcstaffwork.BeacmcStaffWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

    public static String start() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=112394").openConnection();

            int timeout = 1750;
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            return version;
        } catch (Exception exception) {
            return "";
        }
    }
}
