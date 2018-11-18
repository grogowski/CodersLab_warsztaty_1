package pl.coderslab;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopularWordsFinder {

    public static final String[] EXCLUDED = {"się", "i", "w", "nie", "na", "z", "do", "to",
            "że", "a", "o", "jak", "ale", "po", "co", "tak", "za", "od", "go", "już", "jego", "jej", "czy",
            "przez", "tylko", "tego", "sobie", "jeszcze", "może", "ze", "kiedy", "pan", "ich", "dla", "by",
            "gdy", "teraz", "ja", "ten", "który", "nawet", "bardzo", "przed", "tu", "jednak", "pod",
            "coś", "tam", "wszystko", "przy", "więc", "nic", "bo", "nim", "żeby", "miał", "on", "być", "potem",
            "też", "jeśli", "bez", "nad", "gdzie", "lecz", "siebie", "nigdy", "ani", "właśnie", "sam", "u",
            "dobrze", "niż", "jakby", "aby", "ty", "oczy", "zawsze", "raz", "były", "no", "albo", "gdyby",
            "aż", "wtedy", "przecież", "ona", "drzwi", "jako", "chyba", "nagle", "wszyscy", "jeden", "czym", "kto",
            "sposób", "czas", "kilka", "dlaczego", "razem", "także", "mój", "nikt", "choć", "wiele", "dwa",
            "ktoś", "lub", "trzeba", "niech", "ku", "twarz", "którego", "we", "znowu", "człowiek", "jakiś",
            "tutaj", "szybko", "tyle", "głos", "między", "wreszcie", "również", "życie", "oczywiście",
            "znów", "swoje", "dlatego", "zbyt", "ciebie", "zupełnie", "taki", "czego", "iż", "dopiero", "powiedzieć",
            "obok", "prawie", "poza", "zaś", "wciąż", "jeżeli", "moje", "prawda"};

    public static final String[] WEBSITES = {"http://www.onet.pl/"};
    //,"http://www.gazeta.pl/", "http://www.wp.pl/", "http://www.interia.pl/"};

    public static void main(String[] args) {
        findPopularWords();
    }

    static void findPopularWords() {
        List<String> list = new ArrayList<>();
        for (String s : WEBSITES) {
            Connection connect = Jsoup.connect(s);
            try {
                Document document = connect.get();
                Elements links = document.select("span.title");
                for (Element elem : links) {
                    String line = elem.text();
                    for (String part : line.split(" ")) {
                        part = part.trim();
                        if (part.startsWith("\"") || part.startsWith("\'") || part.startsWith(",") || part.startsWith(".")
                                || part.startsWith("(")||part.startsWith("]") || part.startsWith("„")){
                            part = part.substring(1, part.length());
                        }
                        if (part.endsWith(":") || part.endsWith("\'") || part.endsWith(",") || part.endsWith(".")
                                || part.endsWith(")") || part.endsWith("?") || part.endsWith("!") ||  part.endsWith("]")){
                            part = part.substring(part.length() - 1);
                        }
                        if (part.endsWith("\"")) {
                            part = part.substring(part.length() - 1);
                        }
                        if (part.length() >= 3 && !arrayContainsString(part.toLowerCase(), EXCLUDED)) {
                            list.add(part);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
    }

    static boolean arrayContainsString(String str, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

}
