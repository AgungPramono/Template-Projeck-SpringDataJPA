/*=========================================================================
 ** Copyright (C) 2016 Agung Pramono.
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 **      http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 ** 
 **  JSONConverterTest.java
 ===========================================================================*/

package com.agung.template.springjpa.produktest.utilitytest;

import com.agung.template.springjpa.entity.Produk;
import com.agung.template.springjpa.service.ProdukService;
import com.agung.template.springjpa.utility.JSONConverter;

import java.io.File;
import java.sql.Connection;
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
 * @Created by : agung 
 * @Date       : 09/Aug/2016
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class JSONConverterTest {
    
    @Autowired
    private ProdukService produkService;
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
    public void testConvertToJSON(){
        Produk p = produkService.findByKode("produk-003");
        
        Assert.assertEquals("{\"id\":\"p-003\",\"kodeProduk\":\"produk-003\",\"namaProduk\":\"Produk C\",\"harga\":420000.00}",
                JSONConverter.converToJSON(p));
    }
    
    @Test
    public void testConvertFromJSON(){
        String json = "{\"id\":\"p-003\",\"kodeProduk\":\"produk-003\",\"namaProduk\":\"Produk C\",\"harga\":420000.00}";
        Produk p = JSONConverter.convertFromJSON(json);
        
        Assert.assertEquals("p-003", p.getId());
        Assert.assertEquals("produk-003", p.getKodeProduk());
        Assert.assertEquals("Produk C", p.getNamaProduk());
        
        System.out.println("\n"+p.getId());
        System.out.println(p.getHarga()+"\n");
    }
}
