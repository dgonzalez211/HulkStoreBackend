package com.store.hulk;

import com.store.hulk.model.customer.Customer;
import com.store.hulk.model.document.TypeDocument;
import com.store.hulk.model.product.Category;
import com.store.hulk.model.product.Product;
import com.store.hulk.model.user.UserHulk;
import com.store.hulk.service.customer.CustomerService;
import com.store.hulk.service.document.DocumentService;
import com.store.hulk.service.product.ProductService;
import com.store.hulk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class HulkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HulkApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(
            UserService userService,
            CustomerService customerService,
            ProductService productService,
            DocumentService documentService) {
        return args -> {
            userInit(userService, customerService);
            productsInit(productService);
            documentsInit(documentService);
        };
    }

    public void userInit(UserService userService, CustomerService customerService) {
        UserHulk adminUser = new UserHulk(
                0,
                "dgonzalez211",
                "Diego",
                "Gonzalez",
                "Marrugo",
                "kali");
        userService.save(adminUser);

        Customer customer = new Customer(0, "Jayden", "Smith", "Jackson", false);
        Customer provider = new Customer(0, "Scott", "Murphy", "McCarter", true);

        Customer customer1 = new Customer(0, "Steven", "Hopkins", "Loyd", false);
        Customer provider1 = new Customer(0, "Tanisha", "Henderson", "LeRoy", true);

        customerService.save(customer);
        customerService.save(provider);

        customerService.save(customer1);
        customerService.save(provider1);
    }

    public void productsInit(ProductService productService) {
        Category shirts = productService.saveCategory(new Category(0, "T-Shirts", "Colorful shirts"));
        Category glasses = productService.saveCategory(new Category(0, "Glasses", "Beautiful glasses with superheroes"));
        Category comics = productService.saveCategory(new Category(0, "Comics", "Favorites comics from Marvel and DC"));
        Category toys = productService.saveCategory(new Category(0, "Toys", "Pretty action figures"));


        Product product = new Product(0, "Amazing Spider-Man Omnibus, Vol. 1",
                "In 1962, Stan Lee and Steve Ditko gave birth to one of the most enduring icons of American popular media."
                , (long) 3, new BigDecimal(2_350_000), Stream.of(comics).collect(Collectors.toList()));

        Product product1 = new Product(0, "T-Shirt Batman vs Superman",
                "Batman vs Superman movie printed t-shirt"
                , (long) 200, new BigDecimal(250_000), Stream.of(comics, shirts).collect(Collectors.toList()));

        Product product2 = new Product(0, "Flash Glass The Revolution Of The Villains",
                "Captain Cold. Heat Wave. The Master of Mirrors. The Weather Wizard. Skater."
                , (long) 78, new BigDecimal(80_000), Stream.of(comics, glasses).collect(Collectors.toList()));

        Product product3 = new Product(0, "Iron Man action figure",
                "Collection figure based on the comic book Ultron Unlimited"
                , (long) 3, new BigDecimal(1_800_000), Stream.of(comics, toys).collect(Collectors.toList()));

        Product product4 = new Product(0, "Batman Silence",
                "Silence is a comic book story arc from 2002 to 2003 that was published in the Batman monthly series."
                , (long) 1, new BigDecimal(950_000), Stream.of(comics).collect(Collectors.toList()));


        productService.save(product);
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);

    }

    public void documentsInit(DocumentService documentService) {
        TypeDocument invoices = new TypeDocument(0, "Invoices", true,
                "Document for invoices and bills creation");
        TypeDocument purchases = new TypeDocument(0, "Purchases", false,
                "Document to elaborate purchases from store");

        documentService.saveTypeDocument(invoices);
        documentService.saveTypeDocument(purchases);
    }
}
