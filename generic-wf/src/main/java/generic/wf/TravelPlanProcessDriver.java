package generic.wf;

import generic.wf.tasks.TravelPlanProcessTask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TravelPlanProcessDriver {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext("generic.wf");
        TravelPlanProcessTask task = ctx.getBean(TravelPlanProcessTask.class);
        task.process();
    }
    
}
