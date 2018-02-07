/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * CharacterTest - Handles JUnit Tests for Character.java
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 22Jan18    Kevin          Create Initial Tests
 * 02Feb18    Kevin          Updated Tests to Include Param Checking and EXP 
 *                           Getters / Setters.
*/

package character;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {
    
    public CharacterTest() {
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
     * Test of getInstance method, of class Character.
     */
    @Test
    public void testGetInstance() {
        System.out.println("\n***getInstance Test***");
        System.out.println("\tTesting that getInstance does not create multiple instances");
        Character expResult = Character.getInstance();
        Character result = Character.getInstance();
        assertEquals("Two instances of Character did not match each other.", expResult, result);
    }

    /**
     * Test of set & getHp method, of class Character.
     */
    @Test
    public void testSetGetHp() {
        System.out.println("\n***setHp & getHp Test***");
        System.out.println("\t Testing that HP is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 6;
        instance.setHp(expResult);
        int result = instance.getHp();
        assertEquals("HP Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getHp method, of class Character with values outside parameters.
     */
    @Test
    public void testMaxParamSetGetHp() {
        System.out.println("\n***setHp & getHp Max Param Test***");
        System.out.println("\t Testing that HP is set and received correctly "
                + "when given values outside of the max");
        Character instance = Character.getInstance();
        int expResult = instance.maxHp;
        instance.setHp(999999);
        int result = instance.getHp();
        assertEquals("HP Max Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getHp method, of class Character with values outside parameters.
     */
    @Test
    public void testMinParamSetGetHp() {
        System.out.println("\n***setHp & getHp Min Param Test***");
        System.out.println("\t Testing that HP is set and received correctly "
                + "when given values outside of the min");
        Character instance = Character.getInstance();
        int expResult = instance.minHp;
        instance.setHp(-999999);
        int result = instance.getHp();
        assertEquals("HP Min Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getDefense method, of class Character.
     */
    @Test
    public void testSetGetDefense() {
        System.out.println("\n***setDefense & getDefense Test***");
        System.out.println("\t Testing that Defense is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 4;
        instance.setDefense(expResult);
        int result = instance.getDefense();
        assertEquals("Defense Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getDefense method, of class Character with values outside parameters.
     */
    @Test
    public void testMaxParamSetGetDef() {
        System.out.println("\n***setDef & getDef Max Param Test***");
        System.out.println("\t Testing that Defense is set and received correctly "
                + "when given values outside of the max");
        Character instance = Character.getInstance();
        int expResult = instance.maxDef;
        instance.setDefense(999999);
        int result = instance.getDefense();
        assertEquals("Def Max Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getDef method, of class Character with values outside parameters.
     */
    @Test
    public void testMinParamSetGetDef() {
        System.out.println("\n***setDef & getDef Min Param Test***");
        System.out.println("\t Testing that Defense is set and received correctly "
                + "when given values outside of the min");
        Character instance = Character.getInstance();
        int expResult = instance.minDef;
        instance.setDefense(-999999);
        int result = instance.getDefense();
        assertEquals("Defense Min Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getAttack method, of class Character.
     */
    @Test
    public void testSetGetAttack() {
        System.out.println("\n***setAttack & getAttack Test***");
        System.out.println("\t Testing that Attack is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 5;
        instance.setAttack(expResult);
        int result = instance.getAttack();
        assertEquals("Attack Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getAttack method, of class Character with values outside parameters.
     */
    @Test
    public void testMaxParamSetGetAtt() {
        System.out.println("\n***setAtt & getAtt Max Param Test***");
        System.out.println("\t Testing that Attack is set and received correctly "
                + "when given values outside of the max");
        Character instance = Character.getInstance();
        int expResult = instance.maxAtt;
        instance.setAttack(999999);
        int result = instance.getAttack();
        assertEquals("Att Max Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getAttack method, of class Character with values outside parameters.
     */
    @Test
    public void testMinParamSetGetAtt() {
        System.out.println("\n***setAtt & getAtt Min Param Test***");
        System.out.println("\t Testing that Attack is set and received correctly "
                + "when given values outside of the min");
        Character instance = Character.getInstance();
        int expResult = instance.minAtt;
        instance.setAttack(-999999);
        int result = instance.getAttack();
        assertEquals("Attack Min Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getExp method, of class Character.
     */
    @Test
    public void testSetGetExp() {
        System.out.println("\n***setExp & getExp Test***");
        System.out.println("\t Testing that Exp is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 7;
        instance.setExp(expResult);
        int result = instance.getExp();
        assertEquals("Attack Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getDefense method, of class Character with values outside parameters.
     */
    @Test
    public void testMaxParamSetGetExp() {
        System.out.println("\n***setExp & getExp Max Param Test***");
        System.out.println("\t Testing that Attack is set and received correctly "
                + "when given values outside of the max");
        Character instance = Character.getInstance();
        int expResult = instance.maxExp;
        instance.setExp(999999);
        int result = instance.getExp();
        assertEquals("Exp Max Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set & getDef method, of class Character with values outside parameters.
     */
    @Test
    public void testMinParamSetGetExp() {
        System.out.println("\n***setExp & getExp Min Param Test***");
        System.out.println("\t Testing that Exp is set and received correctly "
                + "when given values outside of the min");
        Character instance = Character.getInstance();
        int expResult = instance.minExp;
        instance.setExp(-999999);
        int result = instance.getExp();
        assertEquals("Exp Min Param Setter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getUsername method, of class Character.
     */
    @Test
    public void testSetGetUsername() {
        System.out.println("\n***setUsername & getUsername Test***");
        System.out.println("\t Testing that Username is set and received correctly");
        Character instance = Character.getInstance();
        String expResult = "DapperOctopus";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals("Username Setter or Getter did not work.", expResult, result);
    }
}
