import emaillib.Email;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by strratholm on 20.03.2016.
 */
public class ChangeNotifierTest {
    private String email;
    private String emailTo;
    private String password;
    private String mailHost;
    private String addressee;

    private HashMap<URL, String> prevData;
    private HashMap<URL, String> curData;

    private ArrayList<URL> urls;

    public ChangeNotifierTest() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter emaillib host: ");
        mailHost = scanner.nextLine();

        System.out.println("Enter your email: ");
        email = scanner.nextLine();
        if (!Email.checkEmail(email))
            throw new Exception("Definantly incorrect email address to send from");

        System.out.println("Enter email password: ");
        password = scanner.nextLine();

        System.out.println("Enter client's email: ");
        emailTo = scanner.nextLine();
        if (!Email.checkEmail(emailTo))
            throw new Exception("Definantly incorrect email address to send to");

        addressee = "Марь Иванна";

        prevData = new HashMap<>();
        curData = new HashMap<>();

        urls = new ArrayList<>();
        urls.add(new URL("http://kremlin.ru"));
        urls.add(new URL("http://helloworld.com"));
        urls.add(new URL("http://foo.bar"));
        urls.add(new URL("http://google.com"));

    }

    public void deletedOnly() throws MalformedURLException {
        prepareData();

        curData.remove(urls.get(0));

        doTest();
    }

    public void updatedOnly() throws MalformedURLException {
        prepareData();

        curData.put(urls.get(1), "22");
        curData.put(urls.get(2), "22");

        doTest();
    }

    public void mixedUpdate() throws MalformedURLException {
        prepareData();

        curData.put(urls.get(1), "22");
        curData.put(new URL("http://iam.fire/i/am/death"), "22");
        curData.remove(urls.get(3));

        doTest();
    }

    private void prepareData() {
        prevData.clear();
        curData.clear();

        prevData.put(urls.get(0), "11");
        prevData.put(urls.get(1), "11");
        prevData.put(urls.get(2), "11");
        prevData.put(urls.get(3), "11");

        curData.putAll(prevData);
    }

    private void doTest() {
        ChangeNotifier notifier = new ChangeNotifier(prevData, curData, mailHost, email, password);
        notifier.notifyClient(addressee, emailTo);
    }
}
