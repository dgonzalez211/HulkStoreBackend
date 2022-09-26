
package com.store.hulk.repository.documents;

import com.store.hulk.model.document.CommercialDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICommercialDocumentRepository extends JpaRepository<CommercialDocument,Long> {

    @Query("SELECT c FROM CommercialDocument c where " +
            "LOWER(c.typeDocument.name) LIKE LOWER(:query) " +
            "OR LOWER(c.customer.name) LIKE LOWER(:query) ")
    Iterable<CommercialDocument> typeHeadSearch(@Param("query") String query);

    @Query("SELECT c FROM CommercialDocument c where " +
            "LOWER(c.typeDocument.name) LIKE LOWER(:query) " +
            "OR LOWER(c.customer.name) LIKE LOWER(:query) ")
    Page<CommercialDocument> typeHeadSearchPage(@Param("query") String query, Pageable pageable);

    @Query("SELECT count(c) FROM CommercialDocument c  where c.typeDocument.id=:id ")
    Long count(@Param("id") Long id);
}
