package com.vin.spgrouptest.marshaller;

import com.vin.spgrouptest.data.PsiReading;

import junit.framework.TestCase;

public class PsiReadingJsonMarshallerTest extends TestCase {

    private PsiReadingJsonMarshaller marshaller;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        marshaller = new PsiReadingJsonMarshaller();
    }

    public void testFromJson_Null() {
        try {
            marshaller.fromJson(null);
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testToJson_null() {
        try {
            marshaller.toJson(null);
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testFromJson() {

        String jsonString = "{\"west\":13,\"national\":13,\"east\":7,\"central\":4,\"south\":11,\"north\":9}";
        PsiReading expected = new PsiReading(13, 13, 7, 4, 11, 9);

        assertEquals(expected, marshaller.fromJson(jsonString));
    }

    public void testToJson() {
        PsiReading psiReading = new PsiReading(13, 13, 7, 4, 11, 9);
        String expected = "{\"west\":13,\"national\":13,\"east\":7,\"central\":4,\"south\":11,\"north\":9}";

        assertEquals(expected, marshaller.toJson(psiReading));
    }

    public void testRoundTrip() {
        PsiReading psiReading = new PsiReading(13, 13, 7, 4, 11, 9);

        assertEquals(psiReading, marshaller.fromJson(marshaller.toJson(psiReading)));
    }

}