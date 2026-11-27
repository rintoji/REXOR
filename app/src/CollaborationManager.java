public class CollaborationManager {
    private static final int MAX_ROUNDS = 3;

    private final ResearcherAgent researcher;
    private final ReviewerAgent reviewer;

    public CollaborationManager() {
        AIService ai = new AIService();
        this.researcher = new ResearcherAgent(ai);
        this.reviewer = new ReviewerAgent(ai);
    }

    /** Research, review, refine — and return the best answer. */
    public String startProcess(Task task) {
        Review review = new Review();
        String answer = "";

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            System.out.println("\n--- Round " + round + " ---");

            answer = researcher.research(task, review);
            System.out.println("RESEARCHER:\n" + answer);

            reviewer.review(task, review, answer);
            System.out.println("REVIEW: " + review.score + "/100, approved=" + review.approved);
            if (!review.approved) {
                System.out.println("FEEDBACK:\n" + review.feedback);
            } else {
                break;
            }
        }

        return answer;
    }
}
