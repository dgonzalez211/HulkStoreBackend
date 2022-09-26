
package com.store.hulk.controller.document;

import com.store.hulk.model.document.CommercialDocument;
import com.store.hulk.model.document.TypeDocument;
import com.store.hulk.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = {"/document"})
public class DocumentController {

    @Autowired
    private DocumentService service;

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<TypeDocument> typeHeadSearch(@RequestParam("query") String query) throws ExecutionException, InterruptedException {
        Future< Iterable<TypeDocument> > future = service.typeHeadSearchTypeDocument(query);
        return future.get();
    }

    @RequestMapping(value = {"/save", "/guardar", "/s", "/g"}, method = RequestMethod.POST)
    public CommercialDocument save(@RequestBody CommercialDocument document) {
        return service.save(document);
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<CommercialDocument> typeHeadSearchPage(@RequestParam("page") int page,
                                            @RequestParam("size") int size, @RequestParam("query") String query,
                                            @RequestParam("sort") String sort, @RequestParam("order") String order) throws ExecutionException, InterruptedException {
        Sort.Direction d;
        if (order.equalsIgnoreCase("DESC")) {
            d = Sort.Direction.DESC;
        } else {
            d = Sort.Direction.ASC;
        }
        Future< Page<CommercialDocument> > future = service.typeHeadSearchPage(query, PageRequest.of(page, size, d, sort));
        return future.get();
    }
}
