package com.backendtest.myapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendtest.myapp.clients.ProductSimilarClient;
import com.backendtest.myapp.dtos.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductSimilarService {

    @Autowired
    private ProductSimilarClient client;

    public List<ProductDTO> getSimilarsProductDetails(String productId) throws Exception {

	List<ProductDTO> resultado = new ArrayList<ProductDTO>();

	String[] similarIds = client.getProductSimilarIds(productId);

	if (similarIds.length > 0) {
	    for (int i = 0; i < similarIds.length; i++) {
		String idProduct = similarIds[i];

		ProductDTO prd = client.getProductDetail(idProduct);
		resultado.add(prd);
	    }
	}
	return resultado;

    }
}