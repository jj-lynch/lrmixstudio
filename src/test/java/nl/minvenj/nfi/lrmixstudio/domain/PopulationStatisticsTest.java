/*
 * Copyright (c) 2013, Netherlands Forensic Institute
 * All rights reserved.
 */
package nl.minvenj.nfi.lrmixstudio.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dejong
 */
public class PopulationStatisticsTest {

    public PopulationStatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addStatistic method, of class PopulationStatistics.
     */
    @Test
    public void testAddStatistic() {
        System.out.println("addStatistic");
        final String locusId = "vWA";
        final String allele = "16";
        final BigDecimal probability = new BigDecimal(0.2);
        final PopulationStatistics instance = new PopulationStatistics("");
        instance.addStatistic(locusId, allele, probability);
    }

    @Test
    public void testAddCompoundStatistic() {
        System.out.println("addCompoundStatistic");
        final String locusId = "vWA";
        final String allele = "16";
        final BigDecimal probability = new BigDecimal(0.2);
        final PopulationStatistics instance = new PopulationStatistics("");
        instance.addStatistic(locusId, allele, probability);
        Assert.assertArrayEquals(new String[]{"16"}, instance.getAlleles("vWA").toArray());
        instance.addCompoundStatistic("vWA", "Q", new BigDecimal(0.1));
        Assert.assertArrayEquals(new String[]{"16"}, instance.getAlleles("vWA").toArray());
    }

    /**
     * Test of getProbability method, of class PopulationStatistics.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        final String locusId = "vWA";
        final String allele = "16";
        final BigDecimal probability = new BigDecimal(0.2);
        final PopulationStatistics instance = new PopulationStatistics("");
        instance.addStatistic(locusId, allele, probability);
        assertEquals(new BigDecimal("0.2").doubleValue(), instance.getProbability(locusId, allele).doubleValue(), 0.0);
        assertEquals(0.001f, instance.getProbability("OtherLocus", "1").doubleValue(), 0.000000001f);
    }

    /**
     * Test of getAlleles method, of class PopulationStatistics.
     */
    @Test
    public void testGetAlleles() {
        System.out.println("getAlleles");
        final String id = "Locus";
        final PopulationStatistics instance = new PopulationStatistics("");
        Collection result = instance.getAlleles(id);
        assertNotNull(result);
        assertTrue("Empty population statistics expected to result in empty allele collection", result.isEmpty());

        instance.addStatistic("Locus", "1", new BigDecimal(0.1));
        result = instance.getAlleles("Locus");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertTrue(result.contains("1"));
    }

    /**
     * Tests of the setFileHash method of class PopulationStatistics
     */
    @Test
    public void setFileHash() {
        final PopulationStatistics instance = new PopulationStatistics("");
        instance.setFileHash("dummy hash");
    }

    /**
     * Tests of the getFileHash method of class PopulationStatistics
     */
    @Test
    public void getFileHash() {
        final PopulationStatistics instance = new PopulationStatistics("");
        final String expResult = "dummy hash";
        instance.setFileHash(expResult);
        assertEquals(expResult, instance.getFileHash());
    }

    /**
     * Tests of the toString method of class PopulationStatistics
     */
    @Test
    public void toStringTest() {
        final PopulationStatistics instance = new PopulationStatistics("id");
        instance.setFileHash("hash");
        assertEquals("id hash", instance.toString());
    }
}
