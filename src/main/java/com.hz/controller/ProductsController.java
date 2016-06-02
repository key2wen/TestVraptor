package com.hz.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.validator.Validations;
import com.hz.dao.ProductDao;
import com.hz.dataobject.Client;
import com.hz.dataobject.Product;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

import static br.com.caelum.vraptor.view.Results.xml;

//@SessionScoped
@RequestScoped
//@ApplicationScoped
@Resource
public class ProductsController {

    ProductDao productDao;
    //返回结果
    Result result;
    //用作参数校验
    Validator validator;

    private final HttpServletRequest request;

    public ProductsController(ProductDao productDao, Result result, Validator validator, HttpServletRequest request) {
        this.productDao = productDao;
        this.result = result;
        this.validator = validator;
        this.request = request;
    }


    /**
     * url： /products/list
     *
     * @return
     */
    public List<Product> list() {

        //返回单一的集合可以不用result对象包装(在/products/list中可获取到list数据)
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
        validator.checking(new Validations() {
            {
                that(!product.getName().isEmpty(), "product.name", "nome.empty");
                that(product.getPrice() != null && product.getPrice() > 0, "product.price", "price.invalid");
                that(!product.getDescription().isEmpty() && product.getDescription().equals("desc"), "product.description", "desc.error", "param1", "param2");
            }
        });
        validator.onErrorUsePageOf(ProductsController.class).form();

        productDao.save1(product);
        result.redirectTo(ProductsController.class).list();
    }

    @Get
    //path可以自定义访问路径，但是不影响(controllerName/methodName.jsp访问规则) 下面这种还是会映射到products/show.jsp
    @Path("/get/products/{product.id}")
    public Product show(Product product) {
        System.out.println("test get...." + product.getId());

        return new Product("描述", "名称", 1.1123434535345);
    }

    @Get
    @Path({"/client/{client.id}/show/{section}", "/client/{client.id}/show/"})//支持多路径
    public Client show2(Client client, String section) {
        System.out.println(client.getId() + "...." + section + "....." + client.getSection());//client.getsection没有拿到值

        if (client.getSection() == null) {
            client.setSection(section);
        }

        return client;
    }

    @Get
    @Path("/convert/{value}")
    public void convert(Long value, String test) {
        System.out.println(value == 1);
        result.redirectTo(this.getClass()).list();
    }

    //ajax
    public void jsons() {
//        result.use(json()).from(new Product("描述","名称",1.2324)).serialize();
        //{"product": {"name": "名称","description": "描述","price": 1.2324}}

        result.use(xml()).from(new Product("描述", "名称", 1.2324)).serialize();
        /*<product>
        <name>名称</name>
        <description>描述</description>
        <price>1.2324</price>
        </product>*/
    }

    //下载
    public Download picture(String name) throws Exception {

        String path = request.getServletPath();

        File file = new File(request.getSession().getServletContext().getResource("/picture/" + name + ".jpg").getPath());
        String contentType = "image/jpg";
        String filename = name + ".jpg";

        return new FileDownload(file, contentType, filename);
    }


    public void error() {
        Map<String, Object> maps = result.included();
        if(maps !=null){
            for(String key : maps.keySet()){
                System.out.println(key + "___" + maps.get(key));
//                result.include(key, maps.get(key));
            }
        }
        result.include("myerror", "is my self defined error!");
    }

    //exception handler
    @Get
    public void testError(String msg) {

        result.on(Exception.class).forwardTo(this).error();

        if ("error".equals(msg)) {
            throw new NullPointerException("null..");
        }

        //正常的情况。。
        result.redirectTo(this.getClass()).form();
    }
}