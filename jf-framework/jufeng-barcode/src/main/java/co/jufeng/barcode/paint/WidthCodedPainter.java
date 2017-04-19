package co.jufeng.barcode.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import co.jufeng.barcode.encode.BarSet;

public class WidthCodedPainter implements BarcodePainter {
    
    private static BarcodePainter instance;
    
    private WidthCodedPainter(){
        
    }
    
    public static BarcodePainter getInstance(){
        if(instance == null){
            instance = new WidthCodedPainter();
        }
        return instance;
    }
    
	public BufferedImage paint(BarSet[] barcode, int barWidth, int barHeight, double wideRatio) {
		int width = 0;
        for (int i = 0; i < barcode.length; i++) {
            width += barcode[i].length();
        }
        width = width*barWidth;
        width += 20*barWidth; //20 x Xdimension is quiet zone width
        
        BufferedImage result = new BufferedImage(width, barHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = result.createGraphics();
        
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, width, barHeight);
        
        int x = 10*barWidth; //10 x Xdimension is leading quiet zone
        for (int i = 0; i < barcode.length; i++) {
            for (int j = 0; j < barcode[i].length(); j++) {
                if(barcode[i].get(j)){
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRect(x, 0, barWidth, barHeight);
                x += barWidth;
            }
        }
		return result;
	}

}
