/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukService.java
 * 
 *  Created on Nov 13, 2015, 8:47:16 AM
 */
package com.agung.template.springjpa.service;

import com.agung.template.springjpa.entity.Produk;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author agung
 */
public interface ProdukService {
    public void save(Produk p);
    public void delete(Produk p);
    public Produk findByKode(String kode);
    public Optional<Produk> findProdukById(String id);
    Long countAllProduk();
    Page<Produk> findAllProduk(String search,Pageable page);
    Page<Produk> findAllProduk(Pageable page);
}
