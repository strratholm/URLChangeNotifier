import emaillib.EmailSender;
import url_util.URLUpdates;
import url_util.URLsComparator;

import java.net.URL;
import java.util.HashMap;

/**
 * Created by strratholm on 20.03.2016.
 */

public class ChangeNotifier {
    EmailSender sender;
    URLsComparator comparator;

    public ChangeNotifier(HashMap<URL, String> prevData, HashMap<URL, String> curData, String mailHost,
                          String email, String password) {
        sender = new EmailSender(mailHost, email, password);
        comparator = new URLsComparator(prevData, curData);
    }

    private String constructMessage(String addressee) {
        URLUpdates updates = comparator.getComparisonResult();
        String tempStr = "";
        StringBuilder stringBuilder = new StringBuilder("");

        stringBuilder.append("Здравствуйте, дорогая " + addressee + "\n\n");
        stringBuilder.append("За последний сутки во вверенных Вам сайтах произошли следующие изменения: \n");
        if (!(tempStr = updates.deletedURLsToString()).equals("")) {
            stringBuilder.append("Исчезли слующие страницы: \n");
            stringBuilder.append(tempStr);
        }
        if (!(tempStr = updates.newURLsToString()).equals("")) {
            stringBuilder.append("Появились следующие страницы: \n");
            stringBuilder.append(tempStr);
        }
        if (!(tempStr = updates.updatedURLsToString()).equals("")) {
            stringBuilder.append("Обновились следующие страницы: \n");
            stringBuilder.append(tempStr);
        }
        stringBuilder.append("С уважением, \n\n Автоматизированная система мониторинга");

        return stringBuilder.toString();
    }

    public void notifyClient(String addressee, String emailTo) {
        sender.send("Updates report", constructMessage(addressee), emailTo);
    }
}
