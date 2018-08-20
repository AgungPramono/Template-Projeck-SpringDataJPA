package com.agung.template.springjpa;

import com.agung.template.springjpa.service.ProdukService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main {

    private static ApplicationContext springContext;
    private static ProdukService produkService;

    private static void initContext() {
        springContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        produkService = (ProdukService) springContext.getBean("produkService");
    }

    public ProdukService getProdukService() {
        return produkService;
    }

    public static void main(String[] args) {
        initContext();        
    }

}
