package com.backendtest.myapp.clients;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.backendtest.myapp.dtos.ProductDTO;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductSimilarClientTest {

    @Mock
    Environment env;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductSimilarClient clientTest = new ProductSimilarClient();

    @DisplayName("Getting list of productSimilar Ids")
    @Test
    public void test01() {
	// GIVEN
	String[] similarIdsMock = { "1", "2", "3" };

	// WHEN
	when(this.env.getProperty("api.product.similars.url")).thenReturn("http://service-mock");
	when(restTemplate.getForEntity("http://service-mock/product/1/similarids", String[].class))
		.thenReturn(new ResponseEntity<String[]>(similarIdsMock, HttpStatus.OK));

	// THEN
	String[] ids;
	try {
	    ids = clientTest.getProductSimilarIds("1");
	} catch (Exception e) {
	    ids = null;
	    e.printStackTrace();
	}

	// ASSERTIONS
	Assertions.assertNotNull(ids);
	Assertions.assertEquals(ids.length, 3);
    }

    @DisplayName("Getting Detail of similar product")
    @Test
    public void test02() {
	// GIVEN
	ProductDTO productoMock = new ProductDTO("1000", "Prueba Test", Float.valueOf("4.88"), Boolean.TRUE);

	// WHEN
	when(this.env.getProperty("api.product.similars.url")).thenReturn("http://service-mock");
	when(restTemplate.getForEntity("http://service-mock/product/1", ProductDTO.class))
		.thenReturn(new ResponseEntity<ProductDTO>(productoMock, HttpStatus.OK));

	// THEN
	ProductDTO resultado;
	try {
	    resultado = clientTest.getProductDetail("1");
	} catch (Exception e) {
	    resultado = null;
	    e.printStackTrace();
	}

	// ASSERTIONS
	Assertions.assertNotNull(resultado);
	Assertions.assertEquals(resultado.getId(), "1000");
    }
}
