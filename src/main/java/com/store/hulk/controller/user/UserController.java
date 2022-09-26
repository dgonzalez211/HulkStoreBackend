
package com.store.hulk.controller.user;

import com.store.hulk.model.user.UserHulk;
import com.store.hulk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/user", "/u"})
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public UserHulk save(@RequestBody UserHulk userHulk) {
        return service.save(userHulk);
    }

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET)
    public Iterable<UserHulk> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = {"/typeHeadSearch"}, method = RequestMethod.GET)
    public Iterable<UserHulk> typeHeadSearch(@RequestParam("query") String query) {
        return service.typeHeadSearch(query);
    }

    @RequestMapping(value = {"/typeHeadSearchPage"}, method = RequestMethod.GET)
    public Page<UserHulk> typeHeadSearchPage(@RequestParam("page") int page,
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
    public Optional<UserHulk> findById(@RequestParam("id") Long id) {
        return service.findById(id);
    }
}
