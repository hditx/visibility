package com.stratosphere.visibility;

import com.stratosphere.visibility.applications.ProductIdentifiers;
import com.stratosphere.visibility.domain.Product;
import com.stratosphere.visibility.domain.Size;
import com.stratosphere.visibility.shared.ReadProduct;
import com.stratosphere.visibility.shared.ReadSize;
import com.stratosphere.visibility.shared.ReadStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.util.*;

@SpringBootTest
class VisibilityApplicationTests {

	private ProductIdentifiers productIdentifiers;
	private	ReadProduct readProduct;
	private ReadSize readSize;
	private ReadStock readStock;


	@BeforeEach
	public void init() {
		this.readProduct = Mockito.mock(ReadProduct.class);
		this.readSize = Mockito.mock(ReadSize.class);
		this.readStock = Mockito.mock(ReadStock.class);
		this.productIdentifiers = new ProductIdentifiers(readProduct, readSize, readStock);
	}

	@Test
	public void testVisibleProductsWithEmptyData() {
		Mockito.when(readProduct.invoke()).thenReturn(Collections.emptyList());
		Mockito.when(readSize.invoke()).thenReturn(Collections.emptyMap());
		Mockito.when(readStock.invoke()).thenReturn(Collections.emptyMap());

		List<Integer> result = productIdentifiers.invoke();

		List<Integer> expected = Collections.emptyList();
		Assertions.assertEquals(expected, result);
	}

	@Test
	public void testVisibleProductsWithNoStock() {
		List<Product> productList = Arrays.asList(
				new Product(1, 10),
				new Product(2, 7),
				new Product(3, 15)
		);
		Map<Integer, Size> sizeMap = new HashMap<>();
		sizeMap.put(11, new Size(11, 1, false, false));
		sizeMap.put(21, new Size(21, 2, false, false));
		sizeMap.put(31, new Size(31, 3, false, false));
		Map<Integer, Integer> stockMap = new HashMap<>();
		stockMap.put(11, 0);
		stockMap.put(21, 0);
		stockMap.put(31, 0);

		Mockito.when(readProduct.invoke()).thenReturn(productList);
		Mockito.when(readSize.invoke()).thenReturn(sizeMap);
		Mockito.when(readStock.invoke()).thenReturn(stockMap);

		List<Integer> result = productIdentifiers.invoke();

		List<Integer> expected = Collections.emptyList();
		Assertions.assertEquals(expected, result);
	}

	@Test
	public void testVisibleProductsWithMixedData() {
		List<Product> productList = Arrays.asList(
				new Product(1, 10),
				new Product(2, 7),
				new Product(3, 15)
		);

		Map<Integer, Size> sizeMap = new HashMap<>();
		sizeMap.put(11, new Size(11, 1, false, false));
		sizeMap.put(12, new Size(12, 1, true, false));
		sizeMap.put(21, new Size(21, 2, false, false));
		sizeMap.put(22, new Size(22, 2, true, false));
		sizeMap.put(31, new Size(31, 3, false, false));
		sizeMap.put(32, new Size(32, 3, true, false));

		Map<Integer, Integer> stockMap = new HashMap<>();
		stockMap.put(11, 0);
		stockMap.put(12, 3);
		stockMap.put(21, 0);
		stockMap.put(22, 0);
		stockMap.put(31, 2);
		stockMap.put(32, 0);

		Mockito.when(readProduct.invoke()).thenReturn(productList);
		Mockito.when(readSize.invoke()).thenReturn(sizeMap);
		Mockito.when(readStock.invoke()).thenReturn(stockMap);

		List<Integer> result = productIdentifiers.invoke();

		List<Integer> expected = Arrays.asList(2, 1, 3);
		Assertions.assertEquals(expected, result);
	}
}
