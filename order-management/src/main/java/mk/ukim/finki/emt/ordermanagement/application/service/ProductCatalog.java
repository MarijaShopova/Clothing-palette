package mk.ukim.finki.emt.ordermanagement.application.service;

import mk.ukim.finki.emt.ordermanagement.domain.model.Product;
import mk.ukim.finki.emt.ordermanagement.domain.model.ProductId;

import java.util.List;

public interface ProductCatalog {

    List<Product> findAll();

    Product findById(ProductId id);


}
