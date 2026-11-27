public class ResearcherAgent extends Agent{

    ResearcherAgent(AIService aiService){
        super(aiService,"you are a research agent.");
    }

    public String research(Task task,Review review){
        if (!review.approved){
            return  "researching considering the feedback";

        }else return   "well done";
    }
}
