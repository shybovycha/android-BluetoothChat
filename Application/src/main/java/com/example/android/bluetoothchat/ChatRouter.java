package com.example.android.bluetoothchat;

import java.util.*;

/**
 * Created by shybovycha on 01.02.15.
 */
public class ChatRouter {
    protected static List<String> dws(String from, String to, Map<String, List<String>> graph, List<String> path) {
        if (from.compareTo(to) == 0) {
            path.add(to);
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

    public static Map<String, List<String>> mergeGraphs(Map<String, List<String>> existing, Map<String, List<String>> pending) {
        Map<String, List<String>> result = new TreeMap<String, List<String>>();

        result.putAll(existing);

        for (String key : pending.keySet()) {
            Set<String> values = new TreeSet<String>();
            values.addAll(pending.get(key));

            if (existing.containsKey(key)) {
                values.addAll(existing.get(key));
            }

            result.put(key, new ArrayList<String>(values));
        }

        return result;
    }

    public static boolean compareGraphs(Map<String, List<String>> graph1, Map<String, List<String>> graph2) {
        for (String k : graph1.keySet()) {
            if (!graph2.containsKey(k))
                return false;

            List<String> values1 = graph1.get(k);
            List<String> values2 = graph2.get(k);

            for (String s : values1) {
                if (!values2.contains(s))
                    return false;
            }
        }

        return true;
    }
}
