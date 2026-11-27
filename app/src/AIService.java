import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * One small class that asks an AI a question.
 * We use OpenRouter's free model; change the two constants below to use another API.
 */
public class AIService {
    private static final String BASE_URL = "https://openrouter.ai/api/v1";
    private static final String MODEL = "openrouter/free";

    private static final Pattern CONTENT =
            Pattern.compile("\"content\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"");

    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();

    AIService() {
        this(readKey());
    }

    AIService(String apiKey) {
        this.apiKey = apiKey;
    }

    String generate(String systemPrompt, String userPrompt) {
        String body = "{\"model\":\"" + MODEL + "\",\"messages\":["
                + "{\"role\":\"system\",\"content\":\"" + escape(systemPrompt) + "\"},"
                + "{\"role\":\"user\",\"content\":\"" + escape(userPrompt) + "\"}]}";

        HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + "/chat/completions"))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IllegalStateException("AI API returned " + response.statusCode()
                        + ": " + response.body());
            }
            Matcher answer = CONTENT.matcher(response.body());
            if (!answer.find()) {
                throw new IllegalStateException("No answer in AI response: " + response.body());
            }
            return unescape(answer.group(1)).trim();
        } catch (IOException e) {
            throw new IllegalStateException("Could not reach the AI API: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("AI request was interrupted", e);
        }
    }

    private static String readKey() {
        try {
            String key = Files.readString(Path.of("api-key.txt")).trim();
            if (key.isEmpty()) {
                throw new IllegalStateException("api-key.txt is empty. Put your OpenRouter key in it.");
            }
            return key;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read api-key.txt", e);
        }
    }

    private static String escape(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private static String unescape(String text) {
        return text.replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }
}
