/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukDao.java
 * 
 *  Created on Nov 13, 2015, 8:46:44 AM
 */
package com.agung.template.springjpa.repository;

import com.agung.template.springjpa.entity.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agung
 */
@Repository
public interface ProdukDao extends PagingAndSortingRepository<Produk, String>{
    //contoh query method
    Produk findByKodeProduk(String kode);
    
    //contoh query creation
    @Query("select p from Produk p "
            + "where lower(p.kodeProduk) like lower(:search) "
            + "or lower(p.namaProduk) like lower(:search) "
            + "order by p.namaProduk asc")
    Page<Produk>search(
            @Param("search")String search,
            Pageable page);
}
