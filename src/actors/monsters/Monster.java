/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Monster - An interface handling Monsters
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 30Mar18    Kevin          Initial Interface Created
 * 31Mar18    Kevin          Monsters can't move until player does first
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
    
    @Override
    protected void setStats(){
        canMove = false;
    }
    
    public abstract boolean isRanged();
    public abstract boolean isMelee();
}
