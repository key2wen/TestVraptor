package com.hz.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import com.hz.dao.ProductDao;
import com.hz.dataobject.Product;

import java.util.ArrayList;
import java.util.List;

@Resource
public class ProductsController {

    ProductDao productDao;
    Result result;
    Validator validator;

    public ProductsController(ProductDao productDao, Result result, Validator validator) {
        this.productDao = productDao;
        this.result = result;
        this.validator = validator;
    }

    public List<Product> list() {

        return productDao.listAll();

//        return new ArrayList<Product>();
    }

    //只是为了重定向到products/form.jsp
    public void form() {

    }

//    public void add(Product product) {
//        productDao.save(product);
//        result.redirectTo(ProductsController.class).list();
//    }

    public void add(final Product product) {
        validator.checking(new Validations() { {
            that(!product.getName().isEmpty(), "product.name", "nome.empty");
            that(product.getPrice() > 0, "product.price", "price.invalid");
        } });
        validator.onErrorUsePageOf(ProductsController.class).form();

        productDao.save(product);
        result.redirectTo(ProductsController.class).list();
    }
}