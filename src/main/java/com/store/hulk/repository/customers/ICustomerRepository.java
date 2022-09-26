
package com.store.hulk.repository.customers;

import com.store.hulk.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c where " +
            "LOWER(c.name) LIKE LOWER(:query) OR " +
            "LOWER(c.lastName) LIKE LOWER(:query) OR " +
            "LOWER(c.surName) LIKE LOWER(:query)")
    Iterable<Customer> typeHeadSearch(@Param("query") String query);

    @Query("SELECT c FROM Customer c where " +
            "LOWER(c.name) LIKE LOWER(:query) OR " +
            "LOWER(c.lastName) LIKE LOWER(:query) OR " +
            "LOWER(c.surName) LIKE LOWER(:query)")
    Page<Customer> typeHeadSearchPage(@Param("query") String query, Pageable pageable);
}
