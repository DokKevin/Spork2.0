/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * RangedMonster - Extends Monster type, indicates whether a monster is of ranged
 *                 type
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 30Mar18    Kevin          Initial RangedMonster Created
*/

package actors.monsters;

public abstract class RangedMonster extends Monster {
    @Override
    public boolean isRanged(){
        return true;
    }
    
    @Override
    public boolean isMelee(){
        return false;
    }
}
