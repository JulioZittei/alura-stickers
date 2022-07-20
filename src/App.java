import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        ClientHttp http = new ClientHttp();
        // String jsonResponse = http.getData("https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060");
        String jsonResponse = http.getData("https://api.nasa.gov/planetary/apod?api_key=9CAQ76NnAohD9Ffl6PxLyYQR3wYijFMv8dk6LgRz&start_date=2022-07-17");

       ExtractorContent extractor = new ExtractorOfNasaContent();
    //    ExtractorContent extractor = new ExtractorOfIMDBContent();

       List<Content> contents = extractor.extractContents(jsonResponse);

       contents.stream().forEach((content) -> {
           
            try {
                InputStream inputStream = new URL(content.getUrlImage()).openStream();
                SitckerGenerator.generate(inputStream,content.getTitle(), content.getTitle());
            } catch (IOException e) {
                e.getMessage();
            }

            System.out.println("Titulo: " + content.getTitle());
            System.out.println("Imagem: " + content.getUrlImage());
            System.out.println("\n");

        });
    }
}
