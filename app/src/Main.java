import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("REXOR - Research, Review, Refine");

        Scanner scanner = new Scanner(System.in);
        if (!ensureApiKey(scanner)) return;

        CollaborationManager manager = new CollaborationManager();

        while (true) {
            System.out.print("\nTask (or 'x' to quit): ");
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("x")) {
                System.out.println("Done!");
                break;
            }

            try {
                System.out.println("\nFINAL RESULT:\n" + manager.startProcess(new Task(input)));
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /** Asks for the key only when api-key.txt is missing or empty, then saves it. */
    private static boolean ensureApiKey(Scanner scanner) {
        Path file = Path.of("api-key.txt");
        if (Files.exists(file)) {
            try {
                if (!Files.readString(file).trim().isEmpty()) return true;
            } catch (IOException ignored) {
                // Fall through and ask for the key.
            }
        }

        System.out.println("No API key found.");
        String key = "";
        while (key.isEmpty()) {
            System.out.print("Paste your OpenRouter key (sk-or-v1-...): ");
            if (!scanner.hasNextLine()) return false;
            key = scanner.nextLine().trim();
            if (key.isEmpty()) System.out.println("The key can't be empty. Try again.");
        }

        try {
            Files.writeString(file, key + "\n");
            System.out.println("Saved to api-key.txt - you won't be asked again.");
            return true;
        } catch (IOException e) {
            System.out.println("Could not save api-key.txt: " + e.getMessage());
            return false;
        }
    }
}
