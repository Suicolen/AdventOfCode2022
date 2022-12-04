package suic.util.io;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class InputDownloader {

    @SneakyThrows({URISyntaxException.class, InterruptedException.class, IOException.class})
    public void download(int day) {
        Path inputPath = Path.of("./inputs/Day%02dInput.txt".formatted(day));
        if (Files.exists(inputPath)) {
            System.out.println("Input already downloaded");
            return;
        }
        String token = System.getenv("aoctoken");
        CookieManager cookieManager = new CookieManager();
        HttpCookie cookie = new HttpCookie("session", token);
        cookie.setPath("/");
        cookie.setVersion(0);
        cookieManager.getCookieStore().add(new URI("https://adventofcode.com"), cookie);
        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .version(HttpClient.Version.HTTP_2)
                .build();
        URI uri = URI.create("https://adventofcode.com/%d/day/%d/input".formatted(2022, day));
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(uri)
                .GET()
                .build();
        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (InputStream in = response.body()) {
            Files.write(inputPath, in.readAllBytes());
        }

        // test input
        Path testInputPath = Path.of("./inputs/Day%02dTestInput.txt".formatted(day));
        Document doc = Jsoup.connect("https://adventofcode.com/2022/day/%d".formatted(day)).get();
        Element exampleTextElement = doc.getElementsByTag("p")
                .stream()
                .filter(e -> e.text().startsWith("For example, "))
                .findFirst()
                .orElseThrow();
        String example = Objects.requireNonNull(exampleTextElement.nextElementSibling()).text();
        Files.writeString(testInputPath, example);
    }
}
