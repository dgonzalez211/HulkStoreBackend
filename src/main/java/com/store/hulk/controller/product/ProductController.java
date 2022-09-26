
package com.store.hulk.controller.product;

import com.store.hulk.model.product.Product;
import com.store.hulk.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/product", "/p"})
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET)
    public Iterable<Product> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<Product> typeHeadSearch(@RequestParam("query") String query) {
        return service.typeHeadSearch(query);
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<Product> typeHeadSearchPage(@RequestParam("page") int page,
                                            @RequestParam("size") int size, @RequestParam("query") String query,
                                            @RequestParam("sort") String sort, @RequestParam("order") String order) {
        Sort.Direction d;
        if (order.equalsIgnoreCase("DESC")) {
            d = Sort.Direction.DESC;
        } else {
            d = Sort.Direction.ASC;
        }
        return service.typeHeadSearchPage(query, PageRequest.of(page, size, d, sort));
    }

    @RequestMapping(value = {"/findById"}, method = RequestMethod.GET)
    public Optional<Product> findById(@RequestParam("id") Long id) {
        return service.findById(id);
    }
}
