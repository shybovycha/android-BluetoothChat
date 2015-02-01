package com.example.android.bluetoothchat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shybovycha on 01.02.15.
 */
public class ChatProtocol {
    public static Map<String, String> parseMessage(String message) {
        Map<String, String> result = new TreeMap<String, String>();

        Pattern r = Pattern.compile("ROUTE:(([a-zA-Z0-9\\-,]+)|([*]));TYPE:(TEXT|FILE|GRAPH);FROM_ADDR:(.+);FROM_NAME:([a-zA-Z0-9\\-]+);(.*)");
        Matcher m = r.matcher(message);

        if (!m.find()) {
            return result;
        }

        result.put("route", m.group(1));
        result.put("messageType", m.group(4));
        result.put("senderAddress", m.group(5));
        result.put("senderName", m.group(6));
        result.put("content", m.group(7));

        return result;
    }

    public static List<String> parseRoute(String route) {
        List<String> result = new ArrayList<String>();

        String[] entries = route.split(",");

        Collections.addAll(result, entries);

        return result;
    }

    public static Map<String, List<String>> parseGraph(String graph) {
        Map<String, List<String>> result = new TreeMap<String, List<String>>();
        String[] neighbours = graph.split(";");

        for (String pair : neighbours) {
            String[] parts = pair.split(",");

            if (!result.containsKey(parts[0])) {
                result.put(parts[0], new ArrayList<String>());
            }

            result.get(parts[0]).add(parts[1]);
        }

        return result;
    }
}
