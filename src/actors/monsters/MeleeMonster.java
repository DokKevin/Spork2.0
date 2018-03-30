/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * MeleeMonster - Extends Monster type, indicates whether a monster is of melee
 *                 type
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 30Mar18    Kevin          Initial MeleeMonster Created
*/

package actors.monsters;

public abstract class MeleeMonster extends Monster {
    @Override
    public boolean isRanged(){
        return false;
    }
    
    @Override
    public boolean isMelee(){
        return true;
    }
}
