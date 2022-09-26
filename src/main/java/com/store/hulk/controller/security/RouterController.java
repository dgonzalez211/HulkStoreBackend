
package com.store.hulk.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = {"/secure"})
@RestController
public class RouterController {

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public boolean home() {
        return true;
    }

    @RequestMapping(value = {"/home/documents"}, method = RequestMethod.GET)
    public boolean documents() {
        return true;
    }

    @RequestMapping(value = {"/home/product", "/home/product-manager", "/home/product-manager/{token}", "/home/time-line-product"}, method = RequestMethod.GET)
    public boolean product() {
        return true;
    }

    @RequestMapping(value = {"/home/customer", "/home/customer-manager", "/home/customer-manager/{token}"}, method = RequestMethod.GET)
    public boolean customer() {
        return true;
    }

    @RequestMapping(value = {"/home/user", "/home/user-manager", "/home/user-manager/{token}"}, method = RequestMethod.GET)
    public boolean user() {
        return true;
    }

    @RequestMapping(value = {"/home/type-document", "/home/type-document-manager", "/home/type-document-manager/{token}"}, method = RequestMethod.GET)
    public boolean typeDocument() {
        return true;
    }

    @RequestMapping(value = {"/home/generate"}, method = RequestMethod.GET)
    public boolean generate() {
        return true;
    }
}
