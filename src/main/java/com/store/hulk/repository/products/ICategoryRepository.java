
package com.store.hulk.repository.products;

import com.store.hulk.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
