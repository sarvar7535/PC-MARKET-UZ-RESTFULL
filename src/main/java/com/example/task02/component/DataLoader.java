package com.example.task02.component;

import com.example.task02.entity.Category;
import com.example.task02.entity.Payment;
import com.example.task02.entity.Product;
import com.example.task02.repository.CategoryRepository;
import com.example.task02.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;

    @Value("${spring.sql.init.mode}")
    String mode;

    @Override
    public void run(String... args) throws Exception {

        if (ddl.equals("create") && mode.equals("always")){

            Category category1= new Category();
            category1.setName("PC");
            Category category2= new Category();
            category1.setName("Laptop");
            Category category3= new Category();
            category1.setName("Monoblocks");
            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);

            Product product1=new Product();
            product1.setPrice(12000000.0);
            product1.setName("Acer nitro 5");
            product1.setDescription("Acer nitro 5 intel core i-5 10300H ozu 8gb,ssd 256gb");
            Product product2=new Product();
            product2.setPrice(22000000.0);
            product2.setName("Acer nitro 7");
            product2.setDescription("Acer nitro 7 intel core i-7 10750H ozu 16gb,ssd 512gb");
            Product product3=new Product();
            product3.setPrice(32000000.0);
            product3.setName("Apple");
            product3.setDescription("Apple MackBook Pro M1");
            Product product4=new Product();
            product4.setPrice(42000000.0);
            product4.setName("Asus");
            product4.setDescription("Asus TUF gaming intel core i-5 11260H ozu 8gb,ssd 256gb");
            Product product5=new Product();
            product5.setPrice(52000000.0);
            product5.setName("Lenovo legion");
            product5.setDescription("Lenovo Legion gaming  intel core i-7 11800H ozu 32gb,ssd 1tb");
            Product product6=new Product();
            product6.setPrice(62000000.0);
            product6.setName("Simple");
            product6.setDescription("simple");
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
        }
    }
}
