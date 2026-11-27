public abstract class Agent {
    protected final AIService service;
    protected final String duty;

    Agent(AIService service, String duty) {
        this.service = service;
        this.duty = duty;
    }

    /** Asks the AI with this agent's role as the system prompt. */
    protected String ask(String prompt) {
        return service.generate(duty, prompt);
    }
}
