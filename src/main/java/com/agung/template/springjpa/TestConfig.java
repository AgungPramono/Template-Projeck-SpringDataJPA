/*
 *  TestConfig.java
 *  Template-Projeck-SpringDataJpa 
 * 
 *  Created by Agung Pramono on 19/08/2018 
 *  Copyright (c) 2018 Java Development. All rights reserved.
 */
package com.agung.template.springjpa;

import com.agung.template.springjpa.entity.Produk;
import com.agung.template.springjpa.service.ProdukService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author agung
 */
public class TestConfig {

    private static ApplicationContext springContainer;
    private static ProdukService produkService;

    public static void init() {
        springContainer = new AnnotationConfigApplicationContext(Config.class);
    }

    public static void main(String[] args) {
        init();
        produkService = (ProdukService) springContainer.getBean("produkService");

        for (int i = 0; i <= 100; i++) {
            Produk p = new Produk();
            p.setKodeProduk("P0" + i);
            p.setNamaProduk("Produk-00" + i);
            p.setHarga(new BigDecimal(i + "0000"));

            produkService.save(p);
        }

        Pageable pr = PageRequest.of(0, 20);
        Page<Produk> result = produkService.findAllProduk(pr);

        List<Produk> listProduk = result.getContent();
        System.out.println("Jumlah Produk : " + listProduk.size());

        Produk px = produkService.findByKode("P01");
        System.out.println("Kode Produk : " + px.getKodeProduk());

        Optional<Produk> p1 = produkService.findProdukById("p-001");
        if (p1.isPresent()) {
            Produk pk = p1.get();
            System.out.println("Nama Produk : " + pk.getKodeProduk());
        }
    }
}
