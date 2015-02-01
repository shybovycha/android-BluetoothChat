package com.example.android.bluetoothchat;

import java.util.*;

/**
 * Created by shybovycha on 01.02.15.
 */
public class ChatRouter {
    protected static List<String> dws(String from, String to, Map<String, List<String>> graph, List<String> path) {
        if (from.compareTo(to) == 0) {
            return path;
        }

        if (!graph.keySet().contains(from)) {
            return null;
        }

        List<String> neighbours = graph.get(from);
        List<String> newPath = new ArrayList<String>();

        newPath.addAll(path);
        newPath.add(from);

        for (String n : neighbours) {
            List<String> res = dws(n, to, graph, newPath);

            if (res != null) {
                return res;
            }
        }

        return null;
    }

    public static String getRoute(String from, String to, Map<String, List<String>> graph) {
        String result = "";

        List<String> route = dws(from, to, graph, new ArrayList<String>());

        if (route == null) {
            return null;
        }

        for (int i = 0; i < route.size(); i++) {
            result += route.get(i);

            if (i < route.size() - 1) {
                result += ",";
            }
        }

        return result;
    }

//    public static Map<String, String> mergeGraphs(Map<String, List<String>> existing, Map<String, List<String>> pending) {
//        Map<String, String> result = new TreeMap<String, String>();
//
//        result.putAll(existing);
//
//        for (String key : pending.keySet()) {
//            if (result.containsKey(key)) {
//                continue;
//            }
//
//            result.put(key, pending.get(key));
//        }
//    }
}
