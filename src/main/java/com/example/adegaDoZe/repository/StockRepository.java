package com.example.adegaDoZe.repository;

import com.example.adegaDoZe.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, String> {
    Stock findById(long id);

    //Search?
    //Stock findByName(String productName);

    //for the search
//   @Query(value = "select u from Stock u where u.productName like %?1%")
//    List<Stock>findByNames(String name);
}
