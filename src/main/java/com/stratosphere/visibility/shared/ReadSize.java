package com.stratosphere.visibility.shared;

import com.stratosphere.visibility.domain.Size;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadSize {
    private final ResourceLoader resourceLoader;

    public ReadSize(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<Integer, Size> invoke() {
        Map<Integer, Size> sizeMap = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:size.csv");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                int productId = Integer.parseInt(fields[1].trim());
                boolean backSoon = Boolean.parseBoolean(fields[2].trim());
                boolean special = Boolean.parseBoolean(fields[3].trim());
                sizeMap.put(id, new Size(id, productId, backSoon, special));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sizeMap;
    }
}
