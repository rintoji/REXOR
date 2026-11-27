
public class CollaborationManager {
    public  String result;
    public Review review = new Review();
    public  ReviewerAgent reviewerAgent = new ReviewerAgent(new AIService("11"));
    public  ResearcherAgent researcherAgent = new ResearcherAgent(new AIService("11"));


     void think(Task task, Review review){
        result = researcherAgent.research(task,review);
    }

     void update(Task task, Review review){
        reviewerAgent.review(task,review,result);
    }

     void startProcess(Task task){
        int index = 0;
        System.out.println("--------");
        while (index <= 3){
            System.out.println("TASK: " +task.task);
            think(task, review);
            System.out.println("RESULT: "+result);
            update(task, review);
            if(review.approved){
                break;
            }
            System.out.println("--------");
            index++;
        }
    }
}
