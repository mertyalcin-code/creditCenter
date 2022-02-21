package com.mertyalcin.creditcenter.dataAccess;

import com.mertyalcin.creditcenter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Long > {
    Customer findByNationalityNo(String nationalityNo);
    Customer findByPhoneNumber(String phoneNumber);
}
