// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivLegacyPerMonthOver extends EventTrigger_Value
{
    public float value;
    
    public EventTrigger_CivLegacyPerMonthOver(final float value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).fLegacyPerMonth > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("MonthlyLegacy") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacyPerMonth, 100) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.legacy;
    }
}