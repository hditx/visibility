package com.stratosphere.visibility.shared;

import com.stratosphere.visibility.domain.Product;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ReadProduct {

    private final ResourceLoader resourceLoader;

    public ReadProduct(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    public List<Product> invoke() {
        List<Product> productList = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:product.csv");
        try (InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                int sequence = Integer.parseInt(fields[1].trim());
                productList.add(new Product(id, sequence));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
