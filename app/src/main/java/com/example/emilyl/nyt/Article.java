package com.example.emilyl.nyt;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by emilyl on 6/21/16.
 */
@Parcel
public class Article {
    String webURL;
    String headline;
    String thumbNail;

    public Article() {}

    public Article(JSONObject jsonObject) {
        try {
            this.webURL = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");
            JSONArray multimediaArray = jsonObject.getJSONArray("multimedia");

            if (multimediaArray.length() > 0) {
                JSONObject multimediaJSON = multimediaArray.getJSONObject(0);
                this.thumbNail = "http://nytimes.com/" + multimediaJSON.getString("url");
            } else
                this.thumbNail = "";

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Article> fromJSONArray (JSONArray jsonArray) {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                articles.add(new Article(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }
}
