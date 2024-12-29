// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_MilitaryAcademyForGenerals extends EventOutcome
{
    public int value;
    
    public EventOutcome_MilitaryAcademyForGenerals(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setMilitaryAcademyForGeneralsLevel(Math.max(0, Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() + this.value));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("MilitaryAcademyForGenerals") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0) ? "+" : "") + CFG.getPrecision2((float)this.value, 1);
    }
    
    @Override
    public int getImage() {
        return Images.armyMilitaryAcademy;
    }
}
