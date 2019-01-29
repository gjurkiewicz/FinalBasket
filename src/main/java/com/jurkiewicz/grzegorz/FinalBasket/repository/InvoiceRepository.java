package com.jurkiewicz.grzegorz.FinalBasket.repository;

import com.jurkiewicz.grzegorz.FinalBasket.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository <Invoice, Long> {
}
