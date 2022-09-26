package com.store.hulk.service.customer;

import com.store.hulk.model.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Test
    void save() throws ExecutionException, InterruptedException {

        Customer customer = new Customer(0,"Wandie","Bernot","Harbinson",false);
        Future< Customer > future = service.save(customer);
        Customer rps = future.get();

        assertThat(rps.getId()).isGreaterThan(0);


    }

    @Test
    void findById() throws ExecutionException, InterruptedException {
        Future< Optional<Customer> > future = service.findById(Long.valueOf(1));
        Optional<Customer> rps = future.get();
        assertThat(rps).isNotNull();
    }

    @Test
    void getAll() throws ExecutionException, InterruptedException {
        CompletableFuture<Iterable<Customer>> future = service.getAll();
        Collection<Customer> rps = (Collection<Customer>) future.get();
        assertThat(rps.size()).isGreaterThan(0);
    }

    @Test
    void typeHeadSearch() {
        Collection<Customer> all = (Collection<Customer>) service.typeHeadSearch("Wandie");
        assertEquals(all.size(),1);
    }
}