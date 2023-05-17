package com.stratosphere.visibility.shared;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadStock {
    private final ResourceLoader resourceLoader;

    public ReadStock(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<Integer, Integer> invoke() {
        Map<Integer, Integer> stockMap = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:stock.csv");
        try (InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int sizeId = Integer.parseInt(fields[0].trim());
                int quantity = Integer.parseInt(fields[1].trim());
                stockMap.put(sizeId, quantity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockMap;
    }
}
