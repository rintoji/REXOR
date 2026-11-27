public class ReviewerAgent extends Agent {
    private static final int PASS_SCORE = 80;

    ReviewerAgent(AIService service) {
        super(service, "You are a strict reviewer. Reply with exactly three lines, no other text:\n"
                + "SCORE: a number from 0 to 100\n"
                + "APPROVED: true or false\n"
                + "FEEDBACK: what to improve, or 'none' if approved");
    }

    public Review review(Task task, Review review, String answer) {
        String prompt = "Task: " + task.text
                + "\n\nAnswer to review:\n" + answer
                + "\n\nReview it using the three-line format.";

        String text = ask(prompt);
        String scoreText = value(text, "SCORE:");
        try {
            review.score = Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            review.score = 0;
        }
        review.approved = Boolean.parseBoolean(value(text, "APPROVED:")) && review.score >= PASS_SCORE;

        String feedback = value(text, "FEEDBACK:");
        if (feedback.equalsIgnoreCase("none")) feedback = "";
        review.feedback = feedback.isEmpty() && !review.approved ? text : feedback;
        return review;
    }

    /** Reads the value after a label like "SCORE:" from any line. */
    private static String value(String text, String label) {
        for (String line : text.split("\\R")) {
            String trimmed = line.trim().toUpperCase();
            if (trimmed.startsWith(label)) return line.trim().substring(label.length()).trim();
        }
        return "";
    }
}
