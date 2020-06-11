package generic.wf.tasks;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import lombok.Setter;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.engine.ProcessEngine;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GenerateTravelPlanBpmnXmlFileTask implements AbstractTask {

    @Autowired
    private ProcessEngine processEngine;
    
    @Setter
    private String bpmnFile;
    
    @Override
    public void process() {
        Assert.notNull(processEngine, "BPMN process engine mustn't be null.");
 
        Process process = new Process();
        process.setId("TravelPlanReview");

        StartEvent startEvent = new StartEvent();
        startEvent.setId("Start");
        process.addFlowElement(startEvent);

        UserTask task = new UserTask();
        task.setId("ReviewTravelPlan");
        task.setAssignee("kermit");
        process.addFlowElement(task);

        EndEvent endEvent = new EndEvent();
        endEvent.setId("End");
        process.addFlowElement(endEvent);

        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.getProcesses().add(process);

        byte[] xml = new BpmnXMLConverter().convertToXML(bpmnModel);
        try {
            new BpmnXMLConverter().validateModel(new InputStreamSource(new ByteArrayInputStream(xml)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        try (PrintWriter pw = new PrintWriter(bpmnFile)) {
            pw.println(new String(xml));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
   }

}