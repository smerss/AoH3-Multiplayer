// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivEconomyOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivEconomyOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getEconomyTotal() > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Economy") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getEconomyTotal(), 100) + "]";
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_ECONOMY;
    }
}