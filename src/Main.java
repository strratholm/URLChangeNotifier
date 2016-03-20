/**
 * Created by strratholm on 20.03.2016.
 */

import java.net.URL;
import java.net.MalformedURLException;


public class Main {
    public static void main(String[] args) {
        try {
            ChangeNotifierTest test = new ChangeNotifierTest();
            test.deletedOnly();
            test.updatedOnly();
            test.mixedUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
