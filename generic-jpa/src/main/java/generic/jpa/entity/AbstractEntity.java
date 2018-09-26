package generic.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
abstract public class AbstractEntity implements Serializable {
    protected String createdByIpAddress;
    protected String createdByUserDN;
    protected String updatedByIpAddress;
    protected String updatedByUserDN;
    protected Date createdAtDate;
    protected Date updatedAtDate;
}
