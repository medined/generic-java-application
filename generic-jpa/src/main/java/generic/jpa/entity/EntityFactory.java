package generic.jpa.entity;

public class EntityFactory {

    public static VendorEntity makeVendor() {
        VendorEntity instance = new VendorEntity();
        instance.setName("Madhu Somenhalli");
        instance.setPhone("1-123-123-1234");
        return instance;
    }
    
}
