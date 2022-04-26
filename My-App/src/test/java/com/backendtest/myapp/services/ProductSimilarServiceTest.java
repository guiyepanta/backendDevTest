package com.backendtest.myapp.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendtest.myapp.clients.ProductSimilarClient;
import com.backendtest.myapp.dtos.ProductDTO;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductSimilarServiceTest {

    @Mock
    private ProductSimilarClient productSimilarClientMock;

    @InjectMocks
    private ProductSimilarService service = new ProductSimilarService();

    @DisplayName("Getting list of Details from product similars")
    @Test
    public void test01() throws Exception {
	// GIVEN
	String[] similarIdsMock = { "1" };
	ProductDTO prdMock = new ProductDTO("1", "Prueba UnitTest", Float.valueOf("2.33"), Boolean.TRUE);

	// WHEN
	when(productSimilarClientMock.getProductSimilarIds("1")).thenReturn(similarIdsMock);
	when(productSimilarClientMock.getProductDetail("1")).thenReturn(prdMock);

	// THEN
	List<ProductDTO> products = service.getSimilarsProductDetails("1");

	// ASSERTIONS
	Assertions.assertNotNull(products);
	Assertions.assertEquals(products.size(), 1);
	Assertions.assertEquals(products.get(0).getName(), "Prueba UnitTest");
    }

    @DisplayName("Not getting list of Details from product similars")
    @Test
    public void test02() throws Exception {
	// GIVEN
	String[] similarIdsMock = {};

	// WHEN
	when(productSimilarClientMock.getProductSimilarIds("404")).thenReturn(similarIdsMock);

	// THEN
	List<ProductDTO> products = service.getSimilarsProductDetails("404");

	// ASSERTIONS
	Assertions.assertNotNull(products);
	Assertions.assertEquals(products.size(), 0);
	verify(productSimilarClientMock, times(0)).getProductDetail("1");
    }
}