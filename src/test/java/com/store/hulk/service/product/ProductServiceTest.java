package com.store.hulk.service.product;

import com.store.hulk.model.product.Category;
import com.store.hulk.model.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    void saveCategory() {
        Category category2 = service.saveCategory( new Category(0,"Comics","Comics of your favorites superheroes"));
        category2 = service.saveCategory(category2);
        assertThat(category2.getId()).isGreaterThan(0);
    }

    @Test
    void save() {
        Category category2 = service.saveCategory( new Category(0,"Comics","Comics of your favorites superheroes"));
        Product product = new Product(0,"Amazing Spider-Man Omnibus, Vol. 1",
                "In 1962, Stan Lee and Steve Ditko gave birth to one of the most enduring icons of American popular media."
                ,(long)3,new BigDecimal(2_350_000) , Stream.of(category2).collect(Collectors.toList()));
        product = service.save(product);
        assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    void getAll() {
        Collection<Product> all = (Collection<Product>) service.getAll();
        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void typeHeadSearch() {
        Collection<Product> all = (Collection<Product>) service.typeHeadSearch("Amazing");
        assertEquals(all.size(),1);
    }
}
