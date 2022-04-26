package com.backendtest.myapp.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.backendtest.myapp.dtos.ProductDTO;
import com.backendtest.myapp.services.ProductSimilarService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductSimilarControllerTest {
    @Mock
    private ProductSimilarService serviceMock;

    @InjectMocks
    private ProductSimilarController controller = new ProductSimilarController();

    @DisplayName("When parameter productId is null")
    @Test
    public void test01() throws Exception {
	// GIVEN

	// WHEN

	// THEN
	ResponseEntity<List<ProductDTO>> resultado = controller.getProductSimilars(null);

	// ASSERTIONS
	Assertions.assertEquals(resultado.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @DisplayName("When parameter productId is valid: return 2 details")
    @Test
    public void test02() throws Exception {
	// GIVEN
	List<ProductDTO> resultadoMock = new ArrayList<ProductDTO>();
	ProductDTO prd1 = new ProductDTO("2", "Producto Mock 01", Float.valueOf("2.55"), Boolean.TRUE);
	ProductDTO prd2 = new ProductDTO("3", "Producto Mock 02", Float.valueOf("1.99"), Boolean.FALSE);
	resultadoMock.add(prd1);
	resultadoMock.add(prd2);

	// WHEN
	when(serviceMock.getSimilarsProductDetails("1")).thenReturn(resultadoMock);

	// THEN
	ResponseEntity<List<ProductDTO>> resultado = controller.getProductSimilars("1");

	// ASSERTIONS
	Assertions.assertEquals(resultado.getStatusCode(), HttpStatus.OK);
	Assertions.assertEquals(resultado.getBody().size(), 2);
	Assertions.assertEquals(resultado.getBody().get(0).getName(), "Producto Mock 01");
    }

    @DisplayName("When return list equals null")
    @Test
    public void test03() throws Exception {
	// GIVEN
	List<ProductDTO> resultadoMock = null;

	// WHEN
	when(serviceMock.getSimilarsProductDetails("1")).thenReturn(resultadoMock);

	// THEN
	ResponseEntity<List<ProductDTO>> resultado = controller.getProductSimilars("1");

	// ASSERTIONS
	Assertions.assertEquals(resultado.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @DisplayName("When return empty list of details")
    @Test
    public void test04() throws Exception {
	// GIVEN
	List<ProductDTO> resultadoMock = new ArrayList<ProductDTO>();

	// WHEN
	when(serviceMock.getSimilarsProductDetails("1")).thenReturn(resultadoMock);

	// THEN
	ResponseEntity<List<ProductDTO>> resultado = controller.getProductSimilars("1");

	// ASSERTIONS
	Assertions.assertEquals(resultado.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
