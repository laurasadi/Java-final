package com.rick.morty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSON {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray getJSONArray(JSONObject json) throws JSONException {
        // removing from JSON all unnecessary information and leaving only rickStats array
        int jsonLength = json.toString().length();
        String rickStats = "{" + json.toString().substring(96, jsonLength) + "}";

        // String to JSONObject
        JSONObject jsonObject = new JSONObject(rickStats);
        //JSONObject to JSONArray
        JSONArray jsonArray = jsonObject.getJSONArray("rickStats");

        return jsonArray;
    }

    public static ArrayList<Rick> getList(JSONArray jsonArray) throws JSONException {
        ArrayList<Rick> RickList = new ArrayList<Rick>();
        // Extract data from json and store into ArrayList as class objects
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_data = jsonArray.getJSONObject(i);
            Rick rick = new Rick(
                    json_data.getString("name"),
                    json_data.getString("gender"),
                    json_data.getString("image"),
                    json_data.getInt("id")
            );
            RickList.add(rick);
        }
        return RickList;
    }

    public static ArrayList<Rick> getRickListByName(ArrayList<Rick> rickList, String name) {
        ArrayList<Rick> RickListByName = new ArrayList<Rick>();
        for (Rick rick : rickList) {
            if (rick.getimage().contains(name)) {
                RickListByName.add(rick);
            }
        }
        return RickListByName;
    }

}
