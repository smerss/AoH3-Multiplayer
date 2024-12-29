// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonArmyNoGeneral2 extends Button
{
    public int iCivID;
    public boolean canAssign;
    
    public ButtonArmyNoGeneral2(final String sName, final int iCivID, final int iPosX, final int iPosY, final boolean canAssign) {
        this.init(sName, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), getButtonHeight(), true, true, false, false);
        this.iCivID = iCivID;
        this.canAssign = canAssign;
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight());
        }
        Game.generalManager.noGeneral.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight());
        ImageManager.getImage(Images.generalFrameBattle).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrameBattle).getWidth(), ImageManager.getImage(Images.generalFrameBattle).getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, this.getWidth(), getStatsHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, this.getWidth(), getStatsHeight(), false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, 1, getStatsHeight());
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + iTranslateY, 1, getStatsHeight());
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.PADDING + iTranslateY, this.getColor(isActive));
    }
    
    public static int getStatsHeight() {
        return CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.generalFrameBattle).getHeight() + getStatsHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoGeneral"), CFG.FONT_BOLD));
        if (this.iCivID < 0) {
            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.rebelsFlag, CFG.PADDING, 0));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.iCivID, CFG.PADDING, 0));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (this.canAssign) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectGeneralToRecruit"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.general, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements, true);
        }
        else {
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
    }
}