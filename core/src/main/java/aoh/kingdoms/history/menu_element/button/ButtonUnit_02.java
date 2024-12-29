// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class ButtonUnit_02 extends MenuElement
{
    public int unitTypeID;
    public int armyID;
    public String sText;
    public int iTextWidth;
    public int iTextHeight;
    public String sRecruitmentTime;
    public int iRecruitmentTimeWidth;
    public int iRecruitmentTimeHeight;
    public String sSpeed;
    public int iSpeedWidth;
    public int iSpeedHeight;
    public String sAttack;
    public int iAttackWidth;
    public String sDefense;
    public int iDefenseWidth;
    public String sCost;
    public int iCostWidth;
    public int iCostHeight;
    public static float iconScale;
    public static int iconAttackDefenseMaxWidth;
    public static int attackIconWidth;
    public static int attackIconHeight;
    public static int defenseIconWidth;
    public static int defenseIconHeight;
    public static int timeIconWidth;
    public static int timeIconHeight;
    public static int goldIconWidth;
    public static int goldIconHeight;
    public static int speedIconWidth;
    public static int speedIconHeight;
    public int maxIconWidth;
    public int statH;
    public static final Color COLOR_STATS;
    
    public ButtonUnit_02(final int unitTypeID, final int armyID, final int iPosX, final int iPosY, int nWidth, final boolean isClickable) {
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.maxIconWidth = 0;
        this.statH = 0;
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.unitTypeID = unitTypeID;
        this.armyID = armyID;
        this.fontID = CFG.FONT_BOLD;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(nWidth);
        this.setHeight(ImageManager.getImage(Images.unitsFrame).getHeight());
        this.setText(ArmyManager.lArmy.get(unitTypeID).get(armyID).Name);
        this.sAttack = "" + ArmyManager.lArmy.get(unitTypeID).get(armyID).getAttack(Game.player.iCivID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sAttack);
        this.iAttackWidth = (int)Renderer.glyphLayout.width;
        this.sDefense = "" + ArmyManager.lArmy.get(unitTypeID).get(armyID).getDefense(Game.player.iCivID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), this.sDefense);
        this.iDefenseWidth = (int)Renderer.glyphLayout.width;
        this.sRecruitmentTime = "" + ArmyManager.getRecruitmentTime(Game.player.iCivID, -1, unitTypeID, armyID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sRecruitmentTime);
        this.iRecruitmentTimeWidth = (int)Renderer.glyphLayout.width;
        this.iRecruitmentTimeHeight = (int)Renderer.glyphLayout.height;
        this.sSpeed = "" + ArmyManager.lArmy.get(unitTypeID).get(armyID).MovementSpeed;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sSpeed);
        this.iSpeedWidth = (int)Renderer.glyphLayout.width;
        this.iSpeedHeight = (int)Renderer.glyphLayout.height;
        this.sCost = "" + ArmyManager.getRecruitmentCost(Game.player.iCivID, -1, unitTypeID, armyID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sCost);
        this.iCostWidth = (int)Renderer.glyphLayout.width;
        this.iCostHeight = (int)Renderer.glyphLayout.height;
        this.statH = (this.getHeight() - CFG.PADDING * 4) / 3;
        ButtonUnit_02.iconScale = Math.min(0.9f, this.getImageScale(Images.attack));
        ButtonUnit_02.attackIconWidth = (int)(ImageManager.getImage(Images.attack).getWidth() * ButtonUnit_02.iconScale);
        ButtonUnit_02.attackIconHeight = (int)(ImageManager.getImage(Images.attack).getHeight() * ButtonUnit_02.iconScale);
        ButtonUnit_02.defenseIconWidth = (int)(ImageManager.getImage(Images.defense).getWidth() * ButtonUnit_02.iconScale);
        ButtonUnit_02.defenseIconHeight = (int)(ImageManager.getImage(Images.defense).getHeight() * ButtonUnit_02.iconScale);
        ButtonUnit_02.timeIconWidth = (int)(ImageManager.getImage(Images.time).getWidth() * ButtonUnit_02.iconScale);
        ButtonUnit_02.timeIconHeight = (int)(ImageManager.getImage(Images.time).getHeight() * ButtonUnit_02.iconScale);
        ButtonUnit_02.goldIconWidth = (int)(ImageManager.getImage(Images.gold).getWidth() * ButtonUnit_02.iconScale);
        ButtonUnit_02.goldIconHeight = (int)(ImageManager.getImage(Images.gold).getHeight() * ButtonUnit_02.iconScale);
        ButtonUnit_02.speedIconWidth = (int)(ImageManager.getImage(Images.movementSpeed).getWidth() * ButtonUnit_02.iconScale);
        ButtonUnit_02.speedIconHeight = (int)(ImageManager.getImage(Images.movementSpeed).getHeight() * ButtonUnit_02.iconScale);
        this.maxIconWidth = Math.max(ButtonUnit_02.attackIconWidth, Math.max(ButtonUnit_02.goldIconWidth, ButtonUnit_02.defenseIconWidth));
        ButtonUnit_02.iconAttackDefenseMaxWidth = Math.max(ButtonUnit_02.attackIconWidth, ButtonUnit_02.defenseIconWidth);
        this.setClickable(isClickable);
        this.setVisible(true);
        nWidth = ImageManager.getImage(Images.unitsFrame).getWidth() - CFG.PADDING * 2;
        int tWMax = 0;
        while (this.iTextWidth > nWidth && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        this.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive || (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY && Game.armyRecruit.unitID == this.unitTypeID && Game.armyRecruit.armyID == this.armyID)) {
            Renderer.drawBoxCornerEmpty(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.4f));
        Images.pix.draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.3f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        oSB.setColor(new Color(ButtonUnit_02.COLOR_STATS.r, ButtonUnit_02.COLOR_STATS.g, ButtonUnit_02.COLOR_STATS.b, 0.4f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getInnerWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.5f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getInnerWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getInnerWidth(), CFG.PADDING, false, true);
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY && Game.armyRecruit.unitID == this.unitTypeID && Game.armyRecruit.armyID == this.armyID) {
            oSB.setColor(new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, 0.15f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight(), false, true);
        }
        oSB.setColor(Color.WHITE);
        if (!this.getClickable()) {
            oSB.setColor(1.0f, 1.0f, 1.0f, 0.45f);
        }
        ArmyManager.armyImages.get(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).ImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrame).getWidth(), ImageManager.getImage(Images.unitsFrame).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.unitsFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (isActive) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrame).getWidth(), ImageManager.getImage(Images.unitsFrame).getHeight(), Colors.COLOR_BOX_ACTIVE);
        }
        else if (this.getIsHovered()) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.unitsFrame).getWidth(), ImageManager.getImage(Images.unitsFrame).getHeight(), Colors.COLOR_BOX_HOVER);
        }
    }
    
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + ImageManager.getImage(Images.unitsFrame).getWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iTextHeight - CFG.PADDING * 2 + iTranslateY, this.getColor(isActive));
        final int centerY = this.getHeight() / 2;
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 3 + this.statH * 2 + iTranslateY, this.getInnerWidth() - CFG.PADDING * 2, this.statH, 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING * 3 + this.statH * 2 + iTranslateY, this.maxIconWidth + CFG.PADDING * 2, this.statH, 1.0f);
        ImageManager.getImage(Images.attack).draw(oSB, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth / 2 - ButtonUnit_02.attackIconWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING + this.statH / 2 - ButtonUnit_02.attackIconHeight / 2 + iTranslateY, ButtonUnit_02.attackIconWidth, ButtonUnit_02.attackIconHeight);
        Renderer.drawText(oSB, CFG.FONT_REGULAR, this.sAttack, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth + (this.getInnerWidth() - CFG.PADDING * 4 - this.maxIconWidth) / 2 - this.iAttackWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH / 2 - this.iCostHeight + iTranslateY, Colors.HOVER_LEFT2);
        ImageManager.getImage(Images.defense).draw(oSB, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth / 2 - ButtonUnit_02.defenseIconWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + this.statH + this.statH / 2 - ButtonUnit_02.defenseIconHeight / 2 + iTranslateY, ButtonUnit_02.defenseIconWidth, ButtonUnit_02.defenseIconHeight);
        Renderer.drawText(oSB, CFG.FONT_REGULAR, this.sDefense, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth + (this.getInnerWidth() - CFG.PADDING * 4 - this.maxIconWidth) / 2 - this.iDefenseWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 3 + this.statH + this.statH / 2 - this.iCostHeight + iTranslateY, Colors.HOVER_LEFT2);
        ImageManager.getImage(Images.gold).draw(oSB, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth / 2 - ButtonUnit_02.goldIconWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 3 + this.statH * 2 + this.statH / 2 - ButtonUnit_02.goldIconHeight / 2 + iTranslateY, ButtonUnit_02.goldIconWidth, ButtonUnit_02.goldIconHeight);
        Renderer.drawText(oSB, CFG.FONT_REGULAR, this.sCost, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + this.maxIconWidth + (this.getInnerWidth() - CFG.PADDING * 4 - this.maxIconWidth) / 2 - this.iCostWidth / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 4 + this.statH * 2 + this.statH / 2 - this.iCostHeight + iTranslateY, Colors.HOVER_LEFT2);
    }
    
    public int getTitleHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
    }
    
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats3(isActive, this.getIsHovered());
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.getText());
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
    }
    
    @Override
    public int getTextHeight() {
        return this.iTextHeight;
    }
    
    @Override
    public String getText() {
        return this.sText;
    }
    
    public int getInnerPosX() {
        return ImageManager.getImage(Images.unitsFrame).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - ImageManager.getImage(Images.unitsFrame).getWidth();
    }
    
    private final float getImageScale(final int iImageID) {
        return (this.statH - CFG.PADDING * 2) / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Attack") + ": ", "" + ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).getAttack(Game.player.iCivID), Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Defense") + ": ", "" + ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).getDefense(Game.player.iCivID), Images.defense, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MovementSpeed") + ": ", "" + ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).MovementSpeed, Images.movementSpeed, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AttackRange") + ": ", "" + ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).AttackRange, Images.attack, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeAbility") + ": ", "" + CFG.getPrecision2(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).SiegeProgress, 100), Images.siege, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BattlePosition") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ((ArmyManager.lUnitsTypes.get(this.unitTypeID).Line == 0 || ArmyManager.lUnitsTypes.get(this.unitTypeID).Line == 1) ? Game.lang.get("FirstLine") : ((ArmyManager.lUnitsTypes.get(this.unitTypeID).Line == 2) ? Game.lang.get("Support") : Game.lang.get("ThirdLine"))), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XDays", ArmyManager.getRecruitmentTime(Game.player.iCivID, -1, this.unitTypeID, this.armyID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XPerMonth", CFG.getPrecision2(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).MaintenanceCost, 100)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.armyMaintenance, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + ArmyManager.getRecruitmentCost(Game.player.iCivID, -1, this.unitTypeID, this.armyID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).RequiredTechID >= 0) {
            try {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + TechnologyTree.lTechnology.get(ArmyManager.lArmy.get(this.unitTypeID).get(this.armyID).RequiredTechID).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        this.menuElementHover = new MenuElement_Hover(nElements) {
            @Override
            public int getMinPosX() {
                return InGame_RecruitArmy.HOVER_POSX;
            }
        };
    }
    
    @Override
    public int getValue1() {
        return this.unitTypeID;
    }
    
    @Override
    public int getValue2() {
        return this.armyID;
    }
    
    static {
        ButtonUnit_02.iconScale = 1.0f;
        ButtonUnit_02.iconAttackDefenseMaxWidth = 0;
        COLOR_STATS = new Color(0.039215688f, 0.05882353f, 0.09803922f, 0.6f);
    }
}
