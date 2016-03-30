///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package game.data;
//
//import game.core.Continent;
//import game.core.ContinentIndex;
//import game.core.Country;
//import game.core.CountryIndex;
//import game.core.Player;
//import game.core.PlayerType;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Ben
// */
//public class GetQueryTest {
//    
//    public GetQueryTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getCountryList method, of class GetQuery.
//     */
//    @Test
//    public void testGetCountryList_0args() {
//        System.out.println("getCountryList");
//        GetQuery instance = new GetQuery();
//        List<Country> expResult = null;
//        List<Country> result = instance.getCountryList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCountryList method, of class GetQuery.
//     */
//    @Test
//    public void testGetCountryList_List() {
//        System.out.println("getCountryList");
//        List<CountryIndex> countryIdList = null;
//        GetQuery instance = new GetQuery();
//        List<Country> expResult = null;
//        List<Country> result = instance.getCountryList(countryIdList);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCountry method, of class GetQuery.
//     */
//    @Test
//    public void testGetCountry() {
//        System.out.println("getCountry");
//        CountryIndex countryId = null;
//        GetQuery instance = new GetQuery();
//        Country expResult = null;
//        Country result = instance.getCountry(countryId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getContinent method, of class GetQuery.
//     */
//    @Test
//    public void testGetContinent() {
//        System.out.println("getContinent");
//        ContinentIndex continentId = null;
//        GetQuery instance = new GetQuery();
//        Continent expResult = null;
//        Continent result = instance.getContinent(continentId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPlayerList method, of class GetQuery.
//     */
//    @Test
//    public void testGetPlayerList_0args() {
//        System.out.println("getPlayerList");
//        GetQuery instance = new GetQuery();
//        List<Player> expResult = null;
//        List<Player> result = instance.getPlayerList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPlayerList method, of class GetQuery.
//     */
//    @Test
//    public void testGetPlayerList_PlayerType() {
//        System.out.println("getPlayerList");
//        PlayerType playerType = null;
//        GetQuery instance = new GetQuery();
//        List<Player> expResult = null;
//        List<Player> result = instance.getPlayerList(playerType);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPlayer method, of class GetQuery.
//     */
//    @Test
//    public void testGetPlayer() {
//        System.out.println("getPlayer");
//        String playerName = "";
//        GetQuery instance = new GetQuery();
//        Player expResult = null;
//        Player result = instance.getPlayer(playerName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOrderedMainPlayerList method, of class GetQuery.
//     */
//    @Test
//    public void testGetOrderedMainPlayerList() {
//        System.out.println("getOrderedMainPlayerList");
//        GetQuery instance = new GetQuery();
//        List<Player> expResult = null;
//        List<Player> result = instance.getOrderedMainPlayerList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCountryNameListByPlayerName method, of class GetQuery.
//     */
//    @Test
//    public void testGetCountryNameListByPlayerName() {
//        System.out.println("getCountryNameListByPlayerName");
//        String playerName = "";
//        GetQuery instance = new GetQuery();
//        List<String> expResult = null;
//        List<String> result = instance.getCountryNameListByPlayerName(playerName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
