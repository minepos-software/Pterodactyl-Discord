package net.minepos.pterobot.utils.web;

import net.minepos.pterobot.json.JsonParser;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MCAPIUtils {
    private static final String URL = "https://mcapi.us/server/status?ip=";

    public static boolean isServerReachable(String ip) {
        JsonParser json = new JsonParser(WebUtils.getStringEntity(appendToURL(ip)));
        String status = json.getString("status");

        return status != null && !status.equalsIgnoreCase("error");
    }

    private static String appendToURL(String ip) {
        return URL + (ip.contains(":") ? ip.split(":")[0] + "&port=" + ip.split(":")[1] : ip);
    }
}
