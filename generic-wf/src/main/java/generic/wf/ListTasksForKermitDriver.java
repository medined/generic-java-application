package generic.wf;

import generic.wf.tasks.ListTasksForKermitTask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListTasksForKermitDriver {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext("generic.wf");
        ListTasksForKermitTask task = ctx.getBean(ListTasksForKermitTask.class);
        task.process();
    }
    
}
