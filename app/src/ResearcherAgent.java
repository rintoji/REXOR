public class ResearcherAgent extends Agent{

    ResearcherAgent(AIService aiService){
        super(aiService,"you are a research agent.");
    }

    public String research(String task, String points){
        if (points != null){
            return "researching with the points";
        }
        else return "researching ";
    }
}
