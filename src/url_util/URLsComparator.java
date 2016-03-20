package url_util;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by strratholm on 20.03.2016.
 */

public class URLsComparator {
    private HashMap<URL, String> prevData;
    private HashMap<URL, String> curData;
    private URLUpdates updates;

    public URLsComparator(HashMap<URL, String> prevData, HashMap<URL, String> curData) {
        this.prevData = new HashMap<>(prevData);
        this.curData = new HashMap<>(curData);
        this.updates = new URLUpdates();

        addComparisonResult();
    }

    public URLUpdates getComparisonResult() {
        return new URLUpdates(updates);
    }

    private void addComparisonResult() {
        checkDeleted();
        checkAppeared();
        checkUpdates();
    }

    private void checkDeleted() {
        for (URL url : this.prevData.keySet()) {
            if (!this.curData.containsKey(url)) {
                this.updates.urlDeleted.add(url);
            }
        }
    }

    private void checkAppeared() {
        for (URL url : this.curData.keySet()) {
            if (!this.prevData.containsKey(url))
                this.updates.urlAppeared.add(url);
        }
    }

    private void checkUpdates() {
        Set<URL> intersectionURL = new HashSet<URL>(this.prevData.keySet());
        intersectionURL.retainAll(this.curData.keySet());
        for (URL url : intersectionURL) {
            if (!this.curData.get(url).equals(this.prevData.get(url)))
                this.updates.urlUpdated.add(url);
        }
    }
}
