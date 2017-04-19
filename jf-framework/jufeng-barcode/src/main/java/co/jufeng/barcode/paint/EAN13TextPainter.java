package co.jufeng.barcode.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EAN13TextPainter implements TextPainter {
    
    private static TextPainter instance;
    
    private EAN13TextPainter(){
        
    }
    
    public static TextPainter getInstance(){
        if(instance == null){
            instance = new EAN13TextPainter();
        }
        return instance;
    }

    public void paintText(BufferedImage barcode, String text, int nWidth) {
        Graphics g2d = barcode.getGraphics();        
        Font f = new Font("monospace", Font.PLAIN, 11*nWidth);
        g2d.setFont(f);
        FontMetrics fm = g2d.getFontMetrics();
        
        //calc bounds
        int textWidht = (nWidth*42);
        int textHeight = fm.getHeight();
        int textXShift = (textWidht - fm.stringWidth("000000") + (2*nWidth))/2;
        int textYPos = barcode.getHeight() - textHeight/10;
        
        int x = nWidth*13;
        int y = barcode.getHeight() - (textHeight*4/5);
        
        //passa uma linha limpando em cima
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, barcode.getWidth(),  barcode.getHeight()*1/20);
        //passa uma linha limpando em baixo
        g2d.fillRect(0, barcode.getHeight() - textHeight/3, barcode.getWidth(),  textHeight/3);
        
        //limpa o primeiro quadrante
        g2d.fillRect(x, y, textWidht,  textHeight);
        //coloca o texto
        g2d.setColor(Color.BLACK);
        //primeiro caractere
        g2d.drawString(text.substring(0, 1), 10*nWidth-fm.stringWidth("0"), textYPos);
        //texto primeiro quadrante
        g2d.drawString(text.substring(1, 7), x+textXShift, textYPos);
        
        //limpa o segundo quadrante
        x = nWidth*59;  
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, textWidht,  textHeight);
        //texto segundo quadrante
        g2d.setColor(Color.black);
        g2d.drawString(text.substring(7), x+textXShift, textYPos);
    }

}
