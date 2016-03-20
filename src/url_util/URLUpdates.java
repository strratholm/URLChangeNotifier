package url_util;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by strratholm on 20.03.2016.
 */

public class URLUpdates {
    ArrayList<URL> urlUpdated;
    ArrayList<URL> urlDeleted;
    ArrayList<URL> urlAppeared;

    URLUpdates() {
        urlUpdated = new ArrayList<URL>();
        urlDeleted = new ArrayList<URL>();
        urlAppeared = new ArrayList<URL>();
    }

    URLUpdates(URLUpdates updates) {
        this.urlDeleted = new ArrayList<URL>(updates.urlDeleted);
        this.urlUpdated = new ArrayList<URL>(updates.urlUpdated);
        this.urlAppeared = new ArrayList<URL>(updates.urlAppeared);
    }

    public String newURLsToString() {
        return urlsTuString(urlAppeared);
    }

    public String deletedURLsToString() {
        return urlsTuString(urlDeleted);
    }

    public String updatedURLsToString() {
        return urlsTuString(urlUpdated);
    }

    private String urlsTuString(ArrayList<URL> urls) {
        StringBuilder stringBuilder = new StringBuilder("");

        for (URL url : urls) {
            stringBuilder.append(url);
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
