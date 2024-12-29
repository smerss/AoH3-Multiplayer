// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_ImageTitle_BG implements MenuElement_HoverElement_Type
{
    private int iImageID;
    private int offsetLeft;
    private int offsetRight;
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_ImageTitle_BG(final int iImageID) {
        this.init(iImageID, 0, CFG.PADDING);
    }
    
    public MenuElement_HoverElement_Type_ImageTitle_BG(final int iImageID, final int offsetLeft) {
        this.init(iImageID, offsetLeft, CFG.PADDING);
    }
    
    public MenuElement_HoverElement_Type_ImageTitle_BG(final int iImageID, final int offsetLeft, final int offsetRight) {
        this.init(iImageID, offsetLeft, offsetRight);
    }
    
    private final void init(final int iImageID, final int offsetLeft, final int offsetRight) {
        this.iImageID = iImageID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iWidth = (int)(ImageManager.getImage(iImageID).getWidth() * this.getImageScale());
        this.iHeight = (int)(ImageManager.getImage(iImageID).getHeight() * this.getImageScale());
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ImageManager.getImage(this.iImageID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING * 2 + CFG.PADDING / 2 + (CFG.TEXT_HEIGHT - this.iHeight) / 2, this.iWidth, this.iHeight);
    }
    
    @Override
    public int getWidth() {
        return this.offsetLeft + this.offsetRight + this.iWidth;
    }
    
    private final float getImageScale() {
        return this.getHeight() / (float)ImageManager.getImage(this.iImageID).getHeight();
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 3 / 4;
    }
}