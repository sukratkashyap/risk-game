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

    private DataFactory dataFactory;

    public DataFactoryTest() {
        dataFactory = DataFactory.getInstance();
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
    public void TestingCountryMapSizeAndCountryNotNull() {
        dataFactory.GetCountryMap().entrySet().stream().forEach((country) -> {
            assertNotNull(country.getValue());
        });
        assertEquals(dataFactory.GetCountryMap().size(), Constants.NUM_COUNTRIES);
    }

    @Test
    public void TestingContinentMapSizeAndContinentNotNull() {
        dataFactory.GetContinentMap().entrySet().stream().forEach((continent) -> {
            assertNotNull(continent.getValue());
        });
        assertEquals(dataFactory.GetContinentMap().size(), Constants.NUM_CONTINENTS);
    }

    @Test
    public void TestingCountryMapIsUnModifiable() {
        Map<CountryIndex, Country> countryMap = dataFactory.GetCountryMap();
        try {
            countryMap.put(CountryIndex.Peru, null);
            throw new AssertionError("Country Map is modifiable.");
        } catch (UnsupportedOperationException ex) {

        }
        try {
            countryMap.clear();
            throw new AssertionError("Country Map is modifiable.");
        } catch (UnsupportedOperationException ex) {

        }
    }

    @Test
    public void TestingContinentMapIsUnModifiable() {
        Map<ContinentIndex, Continent> continentMap = dataFactory.GetContinentMap();
        try {
            continentMap.put(ContinentIndex.Asia, null);
            throw new AssertionError("Continent Map is modifiable.");
        } catch (UnsupportedOperationException ex) {

        }
        try {
            continentMap.clear();
            throw new AssertionError("Continent Map is modifiable.");
        } catch (UnsupportedOperationException ex) {

        }
    }
}
