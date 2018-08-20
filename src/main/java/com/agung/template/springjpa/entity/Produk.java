/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Produk.java
 * 
 *  Created on Nov 13, 2015, 8:47:05 AM
 */
package com.agung.template.springjpa.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "m_produk")
public class Produk {
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String id;
    
    
    @Column(nullable = false,unique = true,name = "kode_produk")
    private String kodeProduk;
    
    @Column(name = "nama_produk",nullable = false)
    private String namaProduk;
    
    @Column(nullable = false)
    private BigDecimal harga;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
}
