
package com.store.hulk.repository.products;

import com.store.hulk.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p where LOWER(p.name) LIKE LOWER(:query) ")
    Iterable<Product> typeHeadSearch(@Param("query") String query);

    @Query("SELECT p FROM Product p where LOWER(p.name) LIKE LOWER(:query) ")
    Page<Product> typeHeadSearchPage(@Param("query") String query, Pageable pageable);

    @Modifying
    @Transactional(readOnly = false)
    @Query("UPDATE Product item "
            + "SET item.stock=(item.stock+:quantity) WHERE item.id = :id")
    void updateStockItemQuantity(@Param("quantity") Long quantity, @Param("id") Long id);

    @Modifying
    @Transactional(readOnly = false)
    @Query("UPDATE Product item "
            + "SET item.stock=(item.stock-:quantity) WHERE item.id = :id")
    void updateStockItemQuantityMinus(@Param("quantity") Long quantity, @Param("id") Long id);
}
