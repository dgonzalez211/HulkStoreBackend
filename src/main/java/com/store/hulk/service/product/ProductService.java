
package com.store.hulk.service.product;

import com.store.hulk.model.product.Category;
import com.store.hulk.model.product.Product;
import com.store.hulk.repository.products.ICategoryRepository;
import com.store.hulk.repository.products.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service @Slf4j
public class ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ICategoryRepository categoryRepository;


    public Category saveCategory(Category category){
        try {
            return categoryRepository.save(category);
        }catch (DataAccessException e){
            log.error("Error category:{}",e.getMessage());
        }
        return  new Category();
    }

    public Product save(Product product){
        try {
            return repository.save(product);
        }catch (DataAccessException e){
            log.error("Error product:{}",e.getMessage());
        }
        return  new Product();
    }

    public Optional<Product> findById(Long id){
        return  repository.findById(id);
    }

    public Iterable<Product> getAll(){
        return repository.findAll();
    }

    public Iterable<Product> typeHeadSearch(String query) {
        if (query.equals("")) {
            return new ArrayList<>();
        }
        query = "%" + query + "%";
        return repository.typeHeadSearch(query);
    }

    public Page<Product> typeHeadSearchPage(String query, Pageable pageable) {
        query = "%" + query + "%";
        return repository.typeHeadSearchPage(query, pageable);
    }

    public void updateStockItemQuantity(Long quantity,Long id){
          repository.updateStockItemQuantity(quantity,id);
    }

    public void updateStockItemQuantityMinus(Long quantity,Long id){
          repository.updateStockItemQuantityMinus(quantity,id);
    }
}
