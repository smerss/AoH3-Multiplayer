// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.map.map.Map_Data;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class Init_SelectMap extends Menu
{
    public Init_SelectMap() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        for (int i = 0; i < Game.map.lMaps.size(); ++i) {
            menuElements.add(new ButtonGame2(Game.map.lMaps.get(i).Folder, 0, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
                int id = 0;
                
                @Override
                public void actionElement() {
                    Game.map.setActiveMapID(this.id);
                    Game.mapBG.iMapExtraScale = (float)Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.ExtraMapScale;
                    Game.mapBG.iMapScale = (int)(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.DefaultMapScale * Game.mapBG.iMapExtraScale);
                    Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU);
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        this.initMenu(new MenuTitle(Game.lang.get("SelectMapType"), 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY * 2), menuElements, true, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(0.047058824f, 0.047058824f, 0.047058824f, 1.0f));
        Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}