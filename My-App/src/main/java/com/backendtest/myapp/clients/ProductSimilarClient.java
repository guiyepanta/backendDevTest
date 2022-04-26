package com.backendtest.myapp.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.backendtest.myapp.dtos.ProductDTO;
import com.backendtest.myapp.errors.ProductSimilarNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductSimilarClient {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    private static final String RECURSO_PRODUCT_SIMILAR = "%s/product/%s/similarids";
    private static final String RECURSO_PRODUCT_DETAIL = "%s/product/%s";

    public String[] getProductSimilarIds(String productId) throws Exception {
	String url = env.getProperty("api.product.similars.url");
	final String path = String.format(RECURSO_PRODUCT_SIMILAR, url, productId);

	try {
	    ResponseEntity<String[]> response = restTemplate.getForEntity(path, String[].class);

	    log.info("Successfully retrieved SimilarIds from api-mock");

	    return response.getBody();
	} catch (Exception e) {
	    throw new ProductSimilarNotFoundException();
	}
    }

    public ProductDTO getProductDetail(String productId) {
	String url = env.getProperty("api.product.similars.url");
	final String path = String.format(RECURSO_PRODUCT_DETAIL, url, productId);

	ProductDTO result = restTemplate.getForObject(path, ProductDTO.class);
	log.info("Successfully retrieved product detail from api-mock");
	return result;
    }
}
