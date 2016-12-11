package com.im.client.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;



public class ScrollBarUI extends BasicScrollBarUI{

	public ScrollBarUI() {
        super();
        init();
    }

    private void init() {
        this.thumbColor = new Color(188, 224, 238);//new Color(100, 100, 100);
        this.thumbDarkShadowColor = new Color(240, 254, 254);//new Color(50, 50, 50);
        this.thumbLightShadowColor = new Color(240, 254, 254);//new Color(150, 150, 150);
        this.thumbHighlightColor = new Color(188, 224, 238);//new Color(0, 244, 245);//top left é¢œè‰²
        this.trackColor = new Color(240, 254, 254);//new Color(10, 10, 10);
        this.trackHighlightColor = new Color(188, 224, 238);//new Color(10, 10, 10);
    }
    
    @Override
    public Dimension getMaximumSize(JComponent c) {
        return new Dimension(14, 15);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(14, 15);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton jb = new JButton();
        jb.setPreferredSize(new Dimension(14, 15));
        jb.setOpaque(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setBorderPainted(false);
        jb.setIcon(new ImageIcon(ScrollBarUI.class.getResource("/images/up1.png")));
        jb.setPressedIcon(new ImageIcon(ScrollBarUI.class.getResource("/images/up1.png")));
        return jb;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton jb = new JButton();
        jb.setPreferredSize(new Dimension(14, 15));
        jb.setOpaque(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setBorderPainted(false);
        jb.setIcon(new ImageIcon(ScrollBarUI.class.getResource("/images/down1.jpg")));
        jb.setPressedIcon(new ImageIcon(ScrollBarUI.class.getResource("/images/down2.jpg")));
        return jb;
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        Dimension di = super.getMaximumThumbSize();
        return new Dimension(di.width / 2, di.height);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        Dimension di = super.getMinimumThumbSize();
        return new Dimension(di.width / 2, di.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        init();
        //super.paintThumb(g, c, thumbBounds);
        if(thumbBounds.isEmpty() || !scrollbar.isEnabled())	{
    	    return;
    	}

            int w = thumbBounds.width;
            int h = thumbBounds.height;		
            //System.out.println(thumbBounds.x+" "+thumbBounds.y+" "+thumbBounds.width+" "+thumbBounds.height);
    	g.translate(thumbBounds.x, thumbBounds.y);
    	g.setColor(thumbDarkShadowColor);
    	g.drawRect(0, 0, w-1, h-1);    
    	g.setColor(thumbColor);
    	g.fillRect(0, 0, w-1, h-1);
    	Image imagejb_1 = Toolkit.getDefaultToolkit().
	    getImage(ScrollBarUI.class.getResource("/images/¹ö¶¯Ìõ.jpg"));
   
    	g.drawImage(imagejb_1, 1, 0, w, h, null);


    	g.translate(-thumbBounds.x, -thumbBounds.y);
    	g.dispose();

    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        init();
        
        super.paintTrack(g, c, trackBounds);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        init();
        super.paint(g, c);
    }

    @Override
    protected void paintDecreaseHighlight(Graphics g) {
        init();
        super.paintDecreaseHighlight(g);
    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {
        init();
        super.paintIncreaseHighlight(g);
    }
}
