import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        List<Map<String, String>> movieList = JsonParser.parse(body);

        movieList.stream().forEach((movie) -> {
            double rating = Double.parseDouble(movie.get("imDbRating"));
            long ratingRounded = Math.round(rating);
            String url = StringUtils.replaceBetween("^(.*)(\\..*.)(.jpg)$", "", movie.get("image"));

            try {
                InputStream inputStream = new URL(url).openStream();
                SitckerGenerator.generate(inputStream, movie.get("imDbRating"), movie.get("title"));
            } catch (IOException e) {
                e.getMessage();
            }

            System.out.println("Titulo: " + movie.get("fullTitle"));
            System.out.println("Poster: " + movie.get("image"));
            System.out.println("Classificação: " + movie.get("imDbRating"));
            for (int i = 0; i < ratingRounded; i++) {
                System.out.print("\u2B50");
            }
            System.out.println("\n");

        });
    }
}
