package com.mertyalcin.creditcenter.dataAccess;

import com.mertyalcin.creditcenter.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditDao extends JpaRepository<Credit,Long> {
    List<Credit> findAllByCustomer_NationalityNoOrderByCreationTimeDesc(String nationalityNo);
}
