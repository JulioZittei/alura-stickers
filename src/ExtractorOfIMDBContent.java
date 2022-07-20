import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorOfIMDBContent implements ExtractorContent {
    
    public List<Content> extractContents(String json) {
        List<Map<String, String>> items = JsonParser.parse(json);

        List<Content> contents = new ArrayList<>();

        items.stream().forEach((item)-> {
            String title = item.get("title");
            String urlImage = item.get("image").replaceAll("^(.*)(\\..*.jpg)$", "$1.jpg");
            Content newContent = new Content(title, urlImage);

            contents.add(newContent);
        });

        return contents;
    }
}
