/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * CharacterTest - Handles JUnit Tests for Character.java
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 22Jan18    Kevin          Create Initial Tests
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
        int expResult = 159;
        instance.setHp(expResult);
        int result = instance.getHp();
        assertEquals("HP Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getDefense method, of class Character.
     */
    @Test
    public void testSetGetDefense() {
        System.out.println("\n***setDefense & getDefense Test***");
        System.out.println("\t Testing that Defense is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 348;
        instance.setDefense(expResult);
        int result = instance.getDefense();
        assertEquals("Defense Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getDefense method, of class Character.
     */
    @Test
    public void testSetGetAttack() {
        System.out.println("\n***setAttack & getAttack Test***");
        System.out.println("\t Testing that Attack is set and received correctly");
        Character instance = Character.getInstance();
        int expResult = 956;
        instance.setAttack(expResult);
        int result = instance.getAttack();
        assertEquals("Attack Setter or Getter did not work.", expResult, result);
    }
    
    /**
     * Test of set and getDefense method, of class Character.
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
