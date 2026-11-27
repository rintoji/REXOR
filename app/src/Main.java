import java.awt.*;
import java.util.Scanner;

class Main{
    public static void main(String[] args){
        System.out.println("REXOR is alive !!!");


//        CollaborationManager.setTask("python");
        Scanner scanner = new Scanner(System.in);
        String input  = "";
        Task task ;
        CollaborationManager manager;

        while ( !(input.equals("x"))) {
            System.out.println("give your input ('x' if you want to leave )");
            input = scanner.nextLine();
            if (input.equals("x")){
                System.out.println("done!");
            }else {
                task = new Task(input);
                manager = new CollaborationManager();
                manager.startProcess(task);
            }
        }
    }
}
