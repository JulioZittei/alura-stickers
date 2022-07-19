import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public static List<Map<String, String>> parse(String jsonText) {
        Matcher matcher = REGEX_ITEMS.matcher(jsonText);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> attributesItem = new HashMap<>();

            Matcher matcherAttributeJson = REGEX_ATTRIBUTES_JSON.matcher(item);
            while (matcherAttributeJson.find()) {
                String attribute = matcherAttributeJson.group(1);
                String value = matcherAttributeJson.group(2);
                attributesItem.put(attribute, value);
            }

            data.add(attributesItem);
        }

        return data;
    }
}
