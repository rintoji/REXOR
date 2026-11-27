public class ResearcherAgent extends Agent {

    ResearcherAgent(AIService service) {
        super(service, "You are a careful researcher. Give a clear, complete answer and nothing else.");
    }

    public String research(Task task, Review review) {
        String prompt = "Task: " + task.text + "\n\n";

        if (review.feedback.isEmpty()) {
            prompt += "Write the best answer you can.";
        } else {
            prompt += "Your last answer was rejected. Feedback:\n" + review.feedback
                    + "\n\nWrite a better answer that addresses the feedback.";
        }
        return ask(prompt);
    }
}
