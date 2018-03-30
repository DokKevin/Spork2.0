/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Monster - An interface handling Monsters
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 30Mar18    Kevin          Initial Interface Created
*/

package actors.monsters;

import actors.Actor;

public abstract class Monster extends Actor{
    @Override
    public boolean isPlayer(){
        return false;
    }
    
    @Override
    public boolean isMonster(){
        return true;
    }
    
    public abstract boolean isRanged();
    public abstract boolean isMelee();
}
