import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Help student with task on job4j.ru
 */
public class SqlRuParse {
    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParse.class);

    private static final Map<String, String> MONTHS = Map.ofEntries(
            entry("янв", "янв."),
            entry("фев", "февр."),
            entry("мар", "мар."),
            entry("апр", "апр."),
            entry("май", "мая."),
            entry("июн", "июн."),
            entry("июл", "июл."),
            entry("авг", "авг."),
            entry("сен", "сент."),
            entry("окт", "окт."),
            entry("ноя", "нояб."),
            entry("дек", "дек.")
    );

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        Elements date = doc.select(".altCol");
        Iterator<Element> iterator = date.iterator();
        for (Element td : row) {
            Element href = td.child(0);
//            System.out.println(href.attr("href"));
//            System.out.println(href.text());
            if (iterator.hasNext()) {
                iterator.next();
                Element d = iterator.next();

                LOG.info("NEW");
                LOG.info("arg: {}", d.text());
//                LocalDate localDate = changeData(d.text());
                LocalDate localDate = convertDate(d.text());
                LOG.info("out: {}", localDate);

//                System.out.println(localDate);
            }
        }
    }

    /**
     * 2 options for arguments:
     * option 1:
     * argument: "12 ноя 20, 19:46"
     * splitDate[0] == "12"
     * splitDate[1] == "ноя"
     * splitDate[2] == "20,"
     * splitDate[3] == "19:46"
     * <p>
     * option 2:
     * argument: "сегодня, 18:11" || "вчера, 09:26"
     * splitDate[0] == "сегодня,"
     * splitDate[1] == "18:11"
     *
     * @param stringDate - date as {@link String}.
     * @return correct date as {@link LocalDate}.
     */
    public static LocalDate convertDate(String stringDate) {
        var splitDate = stringDate.split(" ");
        if (splitDate.length == 2) {
            return "сегодня,".equals(splitDate[0]) // "сегодня" || "вчера"
                    ? LocalDate.now() : LocalDate.now().minusDays(1);
        } else {
            String txt = stringDate.replace(splitDate[1], MONTHS.get(splitDate[1]));
            var formatter = DateTimeFormatter.ofPattern(
                    "d MMM yy, HH:mm", new Locale("ru"));
            return LocalDate.parse(txt, formatter);
        }
    }


    public static LocalDate changeData(String s) {
        String old = null, exchange = null;
        if (s.contains("сегодня") || s.contains("вчера")) {
            LocalDate today = LocalDate.now();
            return s.contains("сегодня") ? today : today.minusDays(1);
        } else {
            List<String> wrong = List.of("янв", "фев",
                    "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек");
            List<String> correct = List.of("янв.", "февр.",
                    "мар.", "апр.", "мая", "июн.", "июл.", "авг.", "сент.", "окт.", "нояб.", "дек.");
            for (int i = 0; i < wrong.size(); i++) {
                if (s.contains(wrong.get(i))) {
                    old = wrong.get(i);
                    exchange = correct.get(i);
                }
            }
        }

        LOG.info("old {}", old);
        LOG.info("exchange {}", exchange);
        String txt = s.replace(old, exchange);
        var formatter = DateTimeFormatter.ofPattern(
                "d MMM yy, HH:mm", new Locale("ru"));
        return LocalDate.parse(txt, formatter);
    }


}
