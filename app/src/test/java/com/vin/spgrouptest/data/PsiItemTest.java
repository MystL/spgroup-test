package com.vin.spgrouptest.data;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class PsiItemTest extends TestCase{

    public void testInit_null(){
        try{
            new PsiItem(null,null,null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit_nullTimestamp(){
        try{
            new PsiItem(null,null, TestModels.getReadingsMap());
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit_invalidTimestampString(){
        try{
            new PsiItem("asdad", "asdasd", TestModels.getReadingsMap());
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit_validTimestampString(){
        PsiItem psiItem = new PsiItem("2018-04-19T13:00:00+08:00","2018-04-19T13:00:00+08:00", TestModels.getReadingsMap());
        assertNotNull(psiItem);
    }

    public void testInitValidity_allValid(){
        PsiItem psiItem = new PsiItem("2018-04-19T13:00:00+08:00","2018-04-19T13:00:00+08:00", TestModels.getReadingsMap());
        assertTrue(psiItem.isValid());
    }

}