public abstract class Agent {
    protected AIService service ;
    protected final String agentDuty;

    Agent(AIService service, String agentDuty){
        this.service = service;
        this.agentDuty = agentDuty;
    }
}
