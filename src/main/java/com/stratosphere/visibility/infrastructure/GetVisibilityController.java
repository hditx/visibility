package com.stratosphere.visibility.infrastructure;

import com.stratosphere.visibility.applications.ProductIdentifiers;
import com.stratosphere.visibility.shared.ReadProduct;
import com.stratosphere.visibility.shared.ReadSize;
import com.stratosphere.visibility.shared.ReadStock;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visibility")
public class GetVisibilityController {
    private ProductIdentifiers productIdentifiers;
    private final ReadProduct readProduct;
    private final ReadSize readSize;
    private final ReadStock readStock;

    public GetVisibilityController(ResourceLoader resourceLoader) {
        this.readProduct = new ReadProduct(resourceLoader);
        this.readSize = new ReadSize(resourceLoader);
        this.readStock = new ReadStock(resourceLoader);
        this.productIdentifiers = new ProductIdentifiers(readProduct, readSize, readStock);
    }
    @GetMapping
    public ResponseEntity<?> invoke() {
        return ResponseEntity.ok(productIdentifiers.invoke());
    }
}
