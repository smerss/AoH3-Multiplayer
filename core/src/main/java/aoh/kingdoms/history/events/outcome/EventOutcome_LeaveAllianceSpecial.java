// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_LeaveAllianceSpecial extends EventOutcome
{
    public int value;
    
    public EventOutcome_LeaveAllianceSpecial(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (this.value < Game.alliancesSpecialSize) {
                Game.alliancesSpecial.get(this.value).removeCiv(iCivID);
                Game.getCiv(iCivID).removeInAllianceSpecial(this.value);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("LeaveAlliance") + ": ";
    }
    
    @Override
    public String getStringRight() {
        if (this.value < Game.alliancesSpecialSize) {
            return Game.lang.get(Game.alliancesSpecial.get(this.value).Name_Alliance);
        }
        return "--";
    }
    
    @Override
    public int getImage() {
        return Images.alliance;
    }
}