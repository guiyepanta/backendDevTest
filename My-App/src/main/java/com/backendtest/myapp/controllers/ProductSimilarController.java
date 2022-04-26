package com.backendtest.myapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendtest.myapp.dtos.ProductDTO;
import com.backendtest.myapp.services.ProductSimilarService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/product")
@Api(value = "Middleware: Product Similars", description = "Getting similar products to the one they are currently searching.")
public class ProductSimilarController {

    @Autowired
    private ProductSimilarService service;

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDTO>> getProductSimilars(@PathVariable("productId") String productId)
	    throws Exception {

	if (productId != null) {

	    List<ProductDTO> similarProductDetails = service.getSimilarsProductDetails(productId);

	    if (similarProductDetails != null && similarProductDetails.size() > 0) {
		return new ResponseEntity<>(similarProductDetails, HttpStatus.OK);
	    } else {
		return ResponseEntity.notFound().build();
	    }

	} else {
	    return ResponseEntity.badRequest().build();
	}
    }

}