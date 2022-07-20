import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String toKebabeCase(String text) {
        String[] textArray = text.split(" ", 0);
        return String.join("-", textArray);
    }

    public static String replaceBetween(String regex, String replacement, String text) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Não encontrou items.");
        }

        return matcher.group(1) + replacement + matcher.group(3);
    }
}
