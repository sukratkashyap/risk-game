/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.Constants;
import core.Continent;
import core.ContinentIndex;
import core.Country;
import core.CountryIndex;
import core.DataFactory;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description Test for checking the DataFactory class map creation
 */
public class DataFactoryTest {

    private Map<CountryIndex, Country> countryMap;
    private Map<ContinentIndex, Continent> continentMap;

    public DataFactoryTest() {
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

    @Test
    public void testCountryCreation() {
        countryMap = DataFactory.GetCountryMap();
        for (Map.Entry<CountryIndex, Country> country : countryMap.entrySet()) {
            assertNotNull(country.getValue());
        }
        assertEquals(countryMap.size(), Constants.NUM_COUNTRIES);
    }

    @Test
    public void testContinentCreation() {
        continentMap = DataFactory.GetContinentMap();
        for (Map.Entry<ContinentIndex, Continent> continent : continentMap.entrySet()) {
            assertNotNull(continent.getValue());
        }
        assertEquals(continentMap.size(), Constants.NUM_CONTINENTS);
    }

    @Test
    public void testCountryMapUnModifiable() {
        countryMap = DataFactory.GetCountryMap();
        try {
            countryMap.put(CountryIndex.Peru, null);
            throw new AssertionError("Country Map is modifiable");
        } catch (UnsupportedOperationException ex) {

        }
    }

    @Test
    public void testContinentMapUnModifiable() {
        continentMap = DataFactory.GetContinentMap();
        try {
            continentMap.put(ContinentIndex.Asia, null);
            throw new AssertionError("Continent Map is modifiable");
        } catch (UnsupportedOperationException ex) {

        }
    }
}
