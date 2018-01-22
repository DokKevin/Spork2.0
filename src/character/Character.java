/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Creates a singleton character and handles the stats for the
 * player's character created
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Jan18    Kevin          Seperated CharacterGUI and Character Class
*/

package character;

public class Character {
    //Instantiates Singleton
    private static Character player = new Character();
    
    //Player's actual stats. Seperated from the interface items
    private int hp;
    private int defense;
    private int attack;
    private int exp;
    private String username;
    private String job; //These are classes
    
    //constructor for the Singleton. The stats will vary, so begins with nothing
    private Character(){
        hp = 0;
        defense = 0;
        attack = 0;
        exp = 0;
        username = "";
        job = "Warrior"; // TODO: To be changed when we add more class
    }
    
    //singleton function to get instance
    public static Character getInstance(){
        return player;
    }
    
    //all getters and setters
    // TO DO: Determine Parameters for these and check for them in setters.
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

    public void setHp(int number){
        hp = number;
    }

    public void setDefense(int number){
        defense = number;
    }

    public void setAttack(int number){
        attack = number;
    }

    public void setUsername(String name){
        username = name;
    }
}
