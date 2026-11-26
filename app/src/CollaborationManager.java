public class CollaborationManager {
    public  String task;
    public  String points;
    public  String result;
    public  ReviewerAgent reviewerAgent = new ReviewerAgent(new AIService("11"));
    public  ResearcherAgent researcherAgent = new ResearcherAgent(new AIService("11"));

     void setTask(String newTask){
        task = newTask;
    }

     void think(){
        result = researcherAgent.research(task,points);
    }

     void update(){
        points = reviewerAgent.review(task,result);
    }

     void startProcess(){
        int index = 0;
        System.out.println("--------");
        while (index <= 3){
            System.out.println("TASK: " +task);
            think();
            System.out.println("RESULT: "+result);
            update();
            System.out.println("POINTS: "+ points);
            System.out.println("--------");
            index++;
        }
    }
}
