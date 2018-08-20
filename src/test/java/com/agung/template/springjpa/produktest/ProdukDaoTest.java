/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  ProdukDaoTest.java
 * 
 *  Created on Sep 18, 2015, 10:09:27 PM
 */
package com.agung.template.springjpa.produktest;

import com.agung.template.springjpa.Config;
import com.agung.template.springjpa.entity.Produk;
import com.agung.template.springjpa.service.ProdukService;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author agung
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:applicationContext.xml")
@ContextConfiguration(classes = Config.class)
public class ProdukDaoTest {
    
    @Autowired private ProdukService pService;
    @Autowired private DataSource ds;

    @Before
    public void resetDatabase()throws Exception{
        
        IDataSet [] daftarDataSets = new IDataSet[]{
            new FlatXmlDataSetBuilder().build(new File("src/test/resources/data/data_produk.xml"))
        };
        
        Connection con = ds.getConnection();
        DatabaseOperation.CLEAN_INSERT
                .execute(new DatabaseConnection(con), 
                 new CompositeDataSet(daftarDataSets));
    }
    
    @Test
    public void testInsertProdukData()throws Exception{
        Produk p = new Produk();
        p.setId("pk-001");
        p.setKodeProduk("p-000");
        p.setNamaProduk("Produk-000");
        p.setHarga(new BigDecimal("175000"));
        pService.save(p);
        
        String sql = "select count(*) as jumlah from m_produk where kode_produk='p-000'";
        Connection connection = ds.getConnection();
        ResultSet rs = connection.createStatement().executeQuery(sql);
        
        Assert.assertTrue(rs.next());
        Assert.assertEquals(1L, rs.getLong("jumlah"));
        connection.close();
    }
    
    @Test
    public void testFindProdukByKode(){
        String kode = "produk-003";
        
        Produk p = pService.findByKode(kode);
        Assert.assertNotNull(p);
        Assert.assertEquals("Produk C", p.getNamaProduk());
    }
    
    @Test
    public void testFindProdukById(){
        Optional<Produk> p = pService.findProdukById("p-002");
        Assert.assertNotNull(p);
//        Assert.assertEquals("Produk B", p.getNamaProduk());
    }
    
    @Test
    public void testDelete(){
//        Produk p = pService.findProdukById("p-003");
//        Assert.assertNotNull(p);
//        pService.delete(p);
//        Produk p2 = pService.findProdukById("p-003");
//        Assert.assertNull(p2);
    }
    
    @Test
    public void testCountAllProduk(){
        Long jumlahRow = pService.countAllProduk();
        Assert.assertEquals(5L, jumlahRow.longValue());
    }
    
}