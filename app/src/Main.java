import java.awt.*;

class Main{
    public static void main(String[] args){
        System.out.println("REXOR is alive !!!");


//        CollaborationManager.setTask("python");
        CollaborationManager  manager = new CollaborationManager();
        manager.setTask("java");
        manager.startProcess();

        manager.setTask("python");
        manager.startProcess();
    }
}
