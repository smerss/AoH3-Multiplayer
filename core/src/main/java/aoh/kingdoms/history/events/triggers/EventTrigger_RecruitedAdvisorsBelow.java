// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_RecruitedAdvisorsBelow extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_RecruitedAdvisorsBelow(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).eventsData3.getRecruitedAdvisors() < this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("HiredAdvisors") + " < ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 1);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).eventsData3.getRecruitedAdvisors(), 1) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
