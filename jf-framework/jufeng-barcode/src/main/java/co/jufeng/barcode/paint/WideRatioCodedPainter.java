package co.jufeng.barcode.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import co.jufeng.barcode.encode.BarSet;

public class WideRatioCodedPainter implements BarcodePainter {
    
    private static BarcodePainter instance; 
    
    private WideRatioCodedPainter(){
        
    }
    
    public static BarcodePainter getInstance(){
        if(instance == null){
            instance = new WideRatioCodedPainter();
        }
        return instance;
    }
    
    public BufferedImage paint(BarSet[] barcode, int barWidth, int barHeight, double wideRatio) {
        int width = 20*barWidth; //20 x Xdimension is quiet zone width
        int wideWidth = (int)Math.round(barWidth*wideRatio);
        for (int i = 0; i < barcode.length; i++) {
            for(int j = 0; j < barcode[i].length(); j++){
                if(barcode[i].get(j)){
                    width += wideWidth;
                } else {
                    width += barWidth;
                }
            }
        }
        
        BufferedImage result = new BufferedImage(width, barHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = result.createGraphics();
        
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, width, barHeight);
        
        int x = 10*barWidth; //10 x Xdimension is leading quiet zone 
        int start = 0;
        int stop = barcode.length;
        boolean flag = true;
        
        for (int i = start; i < stop; i++) {
            for (int j = 0; j < barcode[i].length(); j++) {
                width = (barcode[i].get(j) ? wideWidth : barWidth);
                
                g2d.fillRect(x, 0, width, barHeight);
                
                //Change color
                if(flag){
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                flag = !flag;
                x += width;
            }
        }
        return result;
    }

}
