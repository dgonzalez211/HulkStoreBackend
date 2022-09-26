
package com.store.hulk.repository.documents;

import com.store.hulk.model.document.TypeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITypeDocumentRepository extends JpaRepository<TypeDocument,Long> {

    @Query("SELECT t FROM TypeDocument t where LOWER(t.name) LIKE LOWER(:query) ")
    Iterable<TypeDocument> typeHeadSearch(@Param("query") String query);

    @Query("SELECT t FROM TypeDocument t where LOWER(t.name) LIKE LOWER(:query) ")
    Page<TypeDocument> typeHeadSearchPage(@Param("query") String query, Pageable pageable);

    @Query("SELECT count(t) FROM TypeDocument t where t.id=:id ")
    Long count(@Param("id") Long id);
}
