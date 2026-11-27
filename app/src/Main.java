import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("REXOR - Research, Review, Refine");

        Scanner scanner = new Scanner(System.in);
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
}
