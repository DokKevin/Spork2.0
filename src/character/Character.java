/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Creates a singleton character and handles the stats for the
 *             player's character created
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Jan18    Kevin          Seperated CharacterGUI and Character Class
 * 31Jan18    Kevin          Determined Parameters and Added Checking to Setters
*/

package character;

public class Character {
    //Instantiates Singleton
    private static Character player = new Character();
    
    //Player's actual stats.
    private int hp;
    private int defense;
    private int attack;
    private int exp;
    private String username;
    private String job; //These are classes
    
    // Player's min and max stats. May change depending on future development.
    // For use in this class' setters and for testing.
    // Made public to accomodate testing without using creating getters.
    // Made final to disallow being set by other classes.
    public final int minHp = 0;
    public final int maxHp = 10;
    public final int minDef = 1;
    public final int maxDef = 10;
    public final int minAtt = 1;
    public final int maxAtt = 10;
    public final int minExp = 0; // These two will be dependant on level
    public final int maxExp = 10; // Will change when development gets to level
    
    //constructor for the Singleton. The stats will vary, so begins with nothing
    // Parameters will change as development continues
    private Character(){
        hp = minHp;
        defense = minDef;
        attack = minAtt;
        exp = minExp;
        username = "";
        job = "Chef"; // TODO: To be changed when we add more class
    }
    
    //singleton function to get instance
    public static Character getInstance(){
        return player;
    }
    
    //all getters and setters
    public int getHp(){
        return hp;
    }

    public int getDefense(){
        return defense;
    }

    public int getAttack(){
        return attack;
    }

    public String getUsername(){
        return username;
    }
    
    public int getExp(){
        return exp;
    }

    //To Change Min and Max values, see variables at top of class.
    public void setHp(int number){
        if(number < minHp){
            hp = minHp;
        } else if (number > maxHp){
            hp = maxHp;
        } else {
            hp = number;
        }
    }

    //To Change Min and Max values, see variables at top of class.
    public void setDefense(int number){
        if(number < minDef){
            defense = minDef;
        } else if (number > maxDef){
            defense = maxDef;
        } else {
            defense = number;
        }
    }

    //To Change Min and Max values, see variables at top of class.
    public void setAttack(int number){
        if(number < minAtt){
            attack = minAtt;
        } else if (number > maxAtt){
            attack = maxAtt;
        } else {
            attack = number;
        }
    }
    
    //To Change Min and Max values, see variables at top of class.
    public void setExp(int number){
        if(number < minExp){
            exp = minExp;
        } else if (number > maxExp){
            exp = maxExp;
        } else {
            exp = number;
        }
    }

    public void setUsername(String name){
        // Only to be called when usernameIsGood method is used.
        username = name;
    }
    
    //TODO: include decrement / increment functions so that combat doesn't have
    //      to rely simply on getters.
}
