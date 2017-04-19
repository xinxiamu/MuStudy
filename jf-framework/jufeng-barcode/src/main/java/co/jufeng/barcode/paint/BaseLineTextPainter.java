package co.jufeng.barcode.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BaseLineTextPainter implements TextPainter {
    
    private static TextPainter instance; 
    
    private BaseLineTextPainter(){
        
    }
    
    public static TextPainter getInstance(){
        if(instance == null){
            instance = new BaseLineTextPainter();
        }
        return instance;
    }

    public void paintText(BufferedImage barcode, String text, int nWidth) {
        Graphics g2d = barcode.getGraphics();
        
        Font f = new Font("monospace", Font.PLAIN, 10*nWidth);
        g2d.setFont(f);
        FontMetrics fm = g2d.getFontMetrics();
        int h = fm.getHeight();
        int center = (barcode.getWidth() - fm.stringWidth(text))/2;
        
        g2d.setColor(Color.WHITE);
        //passa uma linha limpando em cima
        g2d.fillRect(0, 0, barcode.getWidth(),  barcode.getHeight()*1/20);
        //passa uma linha limpando em baixo
        g2d.fillRect(0, barcode.getHeight() - (h*9/10), barcode.getWidth(),  (h*9/10));
        
       //coloca o texto
        g2d.setColor(Color.BLACK);
        //texto primeiro quadrante
        g2d.drawString(text, center, barcode.getHeight() - (h/10));
    }

}
