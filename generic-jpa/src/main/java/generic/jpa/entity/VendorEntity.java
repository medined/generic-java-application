package generic.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Version;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "vendors")
public class VendorEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String jpaId;
    
    @Version
    private Integer jpaVersion;
    
    private String name;
    private String phone;
    
    public VendorEntity duplicate() {
        VendorEntity instance = new VendorEntity();
        instance.setName(name);
        instance.setPhone(phone);
        return instance;
    }
}
