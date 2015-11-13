/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukServiceImpl.java
 * 
 *  Created on Nov 13, 2015, 8:47:38 AM
 */
package com.agung.template.springjpa.service.impl;

import com.agung.template.springjpa.dao.ProdukDao;
import com.agung.template.springjpa.entity.Produk;
import com.agung.template.springjpa.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author agung
 */
@Service("produkService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ProdukServiceImpl implements ProdukService{
    
    @Autowired private ProdukDao produDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Produk p) {
        if(p == null){
            return;
        }
        produDao.save(p);
    }

    @Override
    public Produk findByKode(String kode) {
       if(!StringUtils.hasText(kode)){
           return null;
       }
       
       return produDao.findByKodeProduk(kode);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Produk p) {
        if(p==null || p.getId() == null){
            return ;
        }
        
        produDao.delete(p);
    }

    @Override
    public Produk findProdukById(String id) {
        if(!StringUtils.hasText(id)){
            return null;
        }
        
        return produDao.findOne(id);
    }

    @Override
    public Long countAllProduk() {
        return produDao.count();
    }

    @Override
    public Page<Produk> findAllProduk(String search, Pageable page) {
        if(!StringUtils.hasText(search)){
            return findAllProduk(page);
        }
        
        if(page == null){
            page = new PageRequest(0, 20);
        }
        
        return produDao.search("%" + search + "%", page);
    }

    @Override
    public Page<Produk> findAllProduk(Pageable page) {
        if(page == null){
            page = new PageRequest(0, 20);
        }
        
        return produDao.findAll(page);
    }
}
