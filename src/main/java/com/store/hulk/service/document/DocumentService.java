
package com.store.hulk.service.document;

import com.store.hulk.model.document.CommercialDocument;
import com.store.hulk.model.document.CommercialDocumentDetail;
import com.store.hulk.model.document.TypeDocument;
import com.store.hulk.repository.documents.ICommercialDocumentRepository;
import com.store.hulk.repository.documents.ITypeDocumentRepository;
import com.store.hulk.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class DocumentService {
    @Autowired
    private ICommercialDocumentRepository documentRepository;

    @Autowired
    private ITypeDocumentRepository typeDocumentRepository;

    @Autowired
    private ProductService productService;

    @Async("asyncExecutor")
    public CompletableFuture<Iterable<TypeDocument>> typeHeadSearchTypeDocument(String query) {
        if (query.equals("")) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(typeDocumentRepository.typeHeadSearch(query));
    }

    public void saveTypeDocument(TypeDocument typeDocument) {
        try {
            typeDocumentRepository.save(typeDocument);
        } catch (DataAccessException e) {
            log.error("Error type document:{}", e.getMessage());
        }
    }

    public CommercialDocument save(CommercialDocument document) {
        try {
            document.setConsecutive(documentRepository.count(document.getTypeDocument().getId()) + 1);
            for (CommercialDocumentDetail detail : document.getDetails()) {
                if (document.getTypeDocument().isInventoryOutput()) {
                    productService.updateStockItemQuantityMinus((long) detail.getQuantity(), detail.getProduct().getId());
                } else {
                    productService.updateStockItemQuantity((long) detail.getQuantity(), detail.getProduct().getId());
                }
            }
            return documentRepository.save(document);
        } catch (DataAccessException e) {
            log.error("Error document:{}", e.getMessage());
        }
        return new CommercialDocument();
    }

    @Async("asyncExecutor")
    public CompletableFuture<Page<CommercialDocument>> typeHeadSearchPage(String query, Pageable pageable) {
        query = "%" + query + "%";
        return CompletableFuture.completedFuture(documentRepository.typeHeadSearchPage(query, pageable));
    }
}
