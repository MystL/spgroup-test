package com.vin.spgrouptest.data;

import junit.framework.TestCase;

public class PsiResponsesTest extends TestCase {

    public void testInit_allNull() {
        try {
            PsiResponses psiResponses = new PsiResponses(null, null, null);
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testInit_empty() {
        PsiResponses psiResponses = new PsiResponses(new RegionMetadata[0], new PsiItem[0], new ApiInfo("status"));
        assertFalse(psiResponses.isValid());
    }

    public void testInit_valid() {
        PsiResponses psiResponses = new PsiResponses(TestModels.getRegionMetadatas(), TestModels.getPsiItems(), new ApiInfo("status"));
        assertTrue(psiResponses.isValid());
    }

}