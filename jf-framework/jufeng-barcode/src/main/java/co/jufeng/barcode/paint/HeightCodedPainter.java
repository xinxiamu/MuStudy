package co.jufeng.barcode.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import co.jufeng.barcode.encode.BarSet;

public class HeightCodedPainter implements BarcodePainter {
    
    private static BarcodePainter instance; 
    
    private HeightCodedPainter(){
        
    }
    
    public static BarcodePainter getInstance(){
        if(instance == null){
            instance = new HeightCodedPainter();
        }
        return instance;
    }

    public BufferedImage paint(BarSet[] barcode, int barWidth, int barHeight, double wideRatio) {
        int width = 0;
        for (int i = 0; i < barcode.length; i++) {
            width += barcode[i].length();
        }
        width = (width*barWidth*2) - barWidth;
        
        BufferedImage result = new BufferedImage(width, barHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = result.createGraphics();
        
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.WHITE);
        int x = 0;
        for (int i = 0; i < barcode.length; i++) {
            for (int j = 0; j < barcode[i].length(); j++) {
                if(!barcode[i].get(j)){
                    g2d.fillRect(x, 0, barWidth, barHeight/2);
                }                
                x += barWidth;
                g2d.fillRect(x,0, barWidth, barHeight);
                x += barWidth;
            }
        }        
        return result;
    }

}
