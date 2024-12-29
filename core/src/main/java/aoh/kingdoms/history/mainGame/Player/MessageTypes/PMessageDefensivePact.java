// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import java.util.List;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDefensivePact;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class PMessageDefensivePact extends PMessage
{
    public PMessageDefensivePact(final int iFromCivID, final int iExpiresTurnID) {
        super(iFromCivID, iExpiresTurnID, 0);
    }
    
    @Override
    public void actionClick() {
        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 30 && InGame_MessageDefensivePact.key.equals(this.key)) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }
        else {
            Game.menuManager.rebuildInGame_MessageDefensivePact(this.key);
        }
    }
    
    @Override
    public void onAccept() {
        DiplomacyManager.acceptDefensivePactOffer(this.fromCivID, Game.player.iCivID);
        DesktopLauncher.SMS("SavunmaOK: " + this.fromCivID + " " + Game.player.iCivID);
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.fromCivID).getCapitalProvinceID(), DiplomacyManager.COLOR_DEFENSIVE_PACT);
    }
    
    @Override
    public void onRefuse() {
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.fromCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
    }
    
    @Override
    public MenuElement_Hover buildElementHover() {
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DefensivePact"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag("", Game.getCiv(this.fromCivID).getCivName(), this.fromCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Message"), "", Images.message, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(this.expiresTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getImageID() {
        return Images.defensivePact;
    }
}
