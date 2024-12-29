// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.events.outcome.EventOutcome_Explode;
import aoh.kingdoms.history.mainGame.CharactersManager;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.utilities.FPS;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Write;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Console extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static String sMessage;
    public static final int CONSOLE_LIMIT = 650;
    public static List<String> sConsole;
    
    public InGame_Console(final boolean visible) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = CFG.PADDING * 2;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = CFG.PADDING;
        final int buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int buttonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new Text_StaticBG_Write(Game.lang.get("TypeMessage") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
            @Override
            public String getTextToDraw() {
                return InGame_Console.sMessage + Keyboard.getKeyboardVerticalLine();
            }
            
            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.CONSOLE, InGame_Console.sMessage);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        for (int i = InGame_Console.sConsole.size() - 1; i >= 0; --i) {
            menuElements.add(new Text_StaticBG(InGame_Console.sConsole.get(i), CFG.FONT_REGULAR_SMALL, paddingLeft, buttonX, buttonY, menuWidth - paddingLeft * 2, buttonH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        buttonY = Math.max(buttonY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 4);
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Console"), true, false, Images.title1Red) {
            @Override
            public long getTime() {
                return InGame_Console.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, visible, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Console.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * 4 / 5 * ((CFG.currentTimeMillis - InGame_Console.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Console.lTime = CFG.currentTimeMillis;
        if (!visible) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.keyboard.hideKeyboard();
    }
    
    public static final void addMessage(final String nMess) {
        InGame_Console.sConsole.add(nMess);
        if (InGame_Console.sConsole.size() > 650) {
            InGame_Console.sConsole.remove(0);
        }
    }
    
    public static final void execute(final String nCommand) {
        addMessage("#" + nCommand);
        final String[] tempCommand = nCommand.split("=");
        try {
            if (tempCommand.length > 0) {
                tempCommand[0] = tempCommand[0].toLowerCase();
                if (tempCommand[0].equals("hi") || tempCommand[0].equals("hello")) {
                    addMessage("Hello, Welcome to Age of History 3");
                }
                else if (tempCommand[0].equals("tag") || tempCommand[0].equals("civtag") || tempCommand[0].equals("civ") || tempCommand[0].equals("id")) {
                    if (Game.iActiveProvince >= 0) {
                        addMessage(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName());
                        addMessage("Civ ID: " + Game.getProvince(Game.iActiveProvince).getCivID());
                        addMessage("CivTAG: " + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivTag());
                    }
                }
                else if (tempCommand[0].equals("fps")) {
                    FPS.drawFPS = !FPS.drawFPS;
                }
                else if (tempCommand[0].equals("debug")) {
                    CFG.debugMode = !CFG.debugMode;
                }
                else if (tempCommand[0].equals("callvassals")) {
                    Game.ENABLE_CALL_VASSALS = !Game.ENABLE_CALL_VASSALS;
                }
                else if (tempCommand[0].equals("sandbox")) {
                    Game.SANDBOX = !Game.SANDBOX;
                    Game.menuManager.rebuildInGame();
                    Game.menuManager.rebuildInGame_CourtOptions();
                }
                else if (tempCommand[0].equals("scale")) {
                    addMessage(Game.lang.get("CurrentScale") + ": " + Game.mapScale.getCurrentScale());
                }
                else if (tempCommand[0].equals("hours")) {
                    if (tempCommand.length > 1) {
                        try {
                            int hours = Integer.parseInt(tempCommand[1]);
                            hours = (Game.HOURS_PER_TURN = Math.max(1, Math.min(24, hours)));
                            addMessage(Game.lang.get("HoursPerTurn") + ": " + Game.HOURS_PER_TURN);
                            Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                        }
                        catch (final Exception ex2) {}
                    }
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_KILL_RULER)) {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        RulersManager.deathOfRuler(Game.getProvince(Game.iActiveProvince).getCivID());
                    }
                }
                else if (tempCommand[0].equals("occupybyplayer")) {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() != Game.player.iCivID) {
                        Game.getProvince(Game.iActiveProvince).setOccupiedByCivID(Game.player.iCivID);
                    }
                }
                else if (tempCommand[0].equals("addgeneral") && tempCommand.length > 1 && tempCommand[1] != null) {
                    CharactersManager.loadGeneral(Game.player.iCivID, tempCommand[1], -99, -99);
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_GOLD)) {
                    final Civilization civ = Game.getCiv(Game.player.iCivID);
                    civ.fGold += GameValues.console.CONSOLE_GOLD;
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_GOLD, 10) + " Gold added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_LEGACY)) {
                    final Civilization civ2 = Game.getCiv(Game.player.iCivID);
                    civ2.fLegacy += GameValues.console.CONSOLE_LEGACY;
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_LEGACY, 10) + " Legacy added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_MANPOWER)) {
                    final Civilization civ3 = Game.getCiv(Game.player.iCivID);
                    civ3.fManpower += GameValues.console.CONSOLE_MANPOWER;
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_MANPOWER, 10) + " Manpower added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_DIPLOMACY)) {
                    final Civilization civ4 = Game.getCiv(Game.player.iCivID);
                    civ4.fDiplomacy += GameValues.console.CONSOLE_DIPLOMACY;
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_DIPLOMACY, 10) + " Diplomacy Points added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_ADVANTAGE)) {
                    Game.getCiv(Game.player.iCivID).setAdvantagePoints(Game.getCiv(Game.player.iCivID).getAdvantagePoints() + GameValues.console.CONSOLE_ADVANTAGE);
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2((float)GameValues.console.CONSOLE_ADVANTAGE, 10) + " Advantage points added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_NUKE)) {
                    Game.getCiv(Game.player.iCivID).canBuildNuke = true;
                    Game.getCiv(Game.player.iCivID).setNukes(Game.getCiv(Game.player.iCivID).getNukes() + 1);
                    addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": Atomic Bomb added");
                }
                else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_EXPLODE) && Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    final EventOutcome_Explode explode = new EventOutcome_Explode(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivTag());
                    explode.updateCiv(0, 0);
                    addMessage("explode" + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        InGame_Console.lTime = 0L;
        InGame_Console.sMessage = "";
        InGame_Console.sConsole = new ArrayList<String>();
    }
}
