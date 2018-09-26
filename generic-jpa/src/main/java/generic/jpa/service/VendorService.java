package generic.jpa.service;

import generic.jpa.entity.VendorEntity;

public interface VendorService {
    public long count();
    public void save(VendorEntity vendorEntity);
}
