package generic.jpa.tasks;

import generic.jpa.repository.BlobRepository;
import java.util.UUID;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WriteBlobTask {

    protected static final Logger LOGGER = LoggerFactory.getLogger(WriteBlobTask.class);
    
    @Autowired
    private BlobRepository blobRepository;
    
    public void process() {
        String buffer = "Timestamp: " + LocalDate.now();
        String parentId = UUID.randomUUID().toString();
        String key = blobRepository.save("testobject", parentId, buffer);
        System.out.println("BLOB Key: " + key);
        
        String newBuffer = blobRepository.read(key);
        System.out.println("BLOB Buffer: " + newBuffer);
    }
}
