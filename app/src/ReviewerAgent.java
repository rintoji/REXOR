public class ReviewerAgent extends Agent{

    ReviewerAgent(AIService aiService){
        super(aiService,"you are a reviewer agent.");
    }



    public void review(Task task,Review review ,String research){
        // give the review (feedback and score )
        if (review.score < 80){
        System.out.println("reviewing...");
    }else review.approved = true;
}
}
