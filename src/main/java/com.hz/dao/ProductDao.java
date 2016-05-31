package com.hz.dao;

import br.com.caelum.vraptor.ioc.Component;
import com.hz.dataobject.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends HibernateGenericDAO<Product> implements  Repository<Product>{

    public ProductDao(Session session) {
        super(session);
    }

    public List<Product> listAll() {

        List<Product> lists = new ArrayList<Product>();
        lists.add(new Product("desc1","name1",345.23));
        lists.add(new Product("描述哈哈","name1",345.23));
        lists.add(new Product("desc1","我的名字哈哈",564.23234));
        return lists;
    }

    public void save1(Product product) {
        System.out.println("Save Product:" + product == null ? "null" : product);
        add(product);
    }
}