
package com.store.hulk.controller.customer;

import com.store.hulk.model.customer.Customer;
import com.store.hulk.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = {"/customer", "/c"})
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public Customer save(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        Future<Customer> future = service.save(customer);
        return future.get();
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET)
    public Iterable<Customer> getAll() throws ExecutionException, InterruptedException {
        Future<Iterable<Customer>> future = service.getAll();
        return future.get();
    }

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<Customer> typeHeadSearch(@RequestParam("query") String query) throws ExecutionException, InterruptedException {
        Future<Iterable<Customer>> future = service.typeHeadSearch(query);
        return future.get();
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<Customer> typeHeadSearchPage(@RequestParam("page") int page,
                                             @RequestParam("size") int size, @RequestParam("query") String query,
                                             @RequestParam("sort") String sort, @RequestParam("order") String order) throws ExecutionException, InterruptedException {
        Sort.Direction d;
        if (order.equalsIgnoreCase("DESC")) {
            d = Sort.Direction.DESC;
        } else {
            d = Sort.Direction.ASC;
        }

        Future<Page<Customer>> future = service.typeHeadSearchPage(query, PageRequest.of(page, size, d, sort));
        return future.get();
    }

    @RequestMapping(value = {"/findById"}, method = RequestMethod.GET)
    public Optional<Customer> findById(@RequestParam("id") Long id) throws ExecutionException, InterruptedException {

        Future<Optional<Customer>> future = service.findById(id);
        return future.get();
    }
}
