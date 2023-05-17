package com.stratosphere.visibility.applications;

import com.stratosphere.visibility.domain.Product;
import com.stratosphere.visibility.domain.Size;
import com.stratosphere.visibility.shared.ReadProduct;
import com.stratosphere.visibility.shared.ReadSize;
import com.stratosphere.visibility.shared.ReadStock;

import java.util.*;
import java.util.stream.Collectors;

public class ProductIdentifiers {

    private final ReadProduct readProduct;
    private final ReadSize readSize;
    private final ReadStock readStock;

    public ProductIdentifiers(ReadProduct readProduct, ReadSize readSize, ReadStock readStock) {
        this.readProduct = readProduct;
        this.readSize = readSize;
        this.readStock = readStock;
    }
    public List<Integer> invoke() {
        List<Product> productList = readProduct.invoke()
                .stream()
                .sorted(Comparator.comparingInt(Product::getSequence))
                .collect(Collectors.toCollection(ArrayList::new));;
        Map<Integer, Size> sizeMap = readSize.invoke();
        Map<Integer, Integer> stockMap = readStock.invoke();
        return getVisibleProducts(productList, sizeMap, stockMap);
    }

    private List<Integer> getVisibleProducts(List<Product> productList, Map<Integer, Size> sizeMap, Map<Integer, Integer> stockMap) {
        List<Integer> visibleProductIds = new ArrayList<>();
        for (Product product : productList) {
            boolean common = false;
            boolean specialStock = false;
            boolean specialBackSoon = false;
            boolean commonBackSoon = false;
            for (Size size : sizeMap.values()) {
                if (size.getProductId() == product.getId()) {
                    int quantity = stockMap.getOrDefault(size.getId(), 0);
                    if (quantity > 0) {
                        if (size.isSpecial()) {
                            specialStock = true;
                        } else {
                            common = true;
                        }
                    } else if(size.isBackSoon()) {
                        if (size.isSpecial()) {
                            specialBackSoon = true;
                        } else {
                            commonBackSoon = true;
                        }
                    }
                }
            }
            if (specialStock && common) {
                visibleProductIds.add(product.getId());
            } else if ((common || commonBackSoon) && (!specialBackSoon && !specialStock)) {
                visibleProductIds.add(product.getId());
            } else if (commonBackSoon && specialBackSoon) {
                visibleProductIds.add(product.getId());
            }
        }

        return visibleProductIds;
    }


}
