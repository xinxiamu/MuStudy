package co.jufeng.barcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import co.jufeng.barcode.encode.BarSet;
import co.jufeng.barcode.encode.BarcodeEncoder;
import co.jufeng.barcode.encode.InvalidAtributeException;
import co.jufeng.barcode.paint.BarcodePainter;
import co.jufeng.barcode.paint.TextPainter;

public class JBarcode {
    
    public static final double MIN_XDIMENSION = 0.264583333;

	private int barHeight;

	private double wideToNarrow;

    private int xdimension;

	private boolean showText;

	private boolean checkDigit;

	private boolean showCheckDigit;

	private BarcodeEncoder bcencoder;

	private BarcodePainter bcpainter;

    private TextPainter textpainter;
    
    private double module;

    public JBarcode(BarcodeEncoder encoder, BarcodePainter painter, TextPainter textpainter, double xdimension, int barHeight, double wideRatio){
        this.bcencoder = encoder;
        this.bcpainter = painter;
        this.textpainter = textpainter;
        this.barHeight = barHeight;
        this.xdimension = (int)(xdimension/MIN_XDIMENSION);
        this.xdimension = this.xdimension > 0 ? this.xdimension : 1;
        this.module = xdimension;
        this.wideToNarrow = wideRatio;
        this.showCheckDigit = true;
        this.checkDigit = true;
        this.showText = true;
    }

    public JBarcode(BarcodeEncoder encoder, BarcodePainter painter, TextPainter textpainter){
        this(encoder, painter, textpainter, MIN_XDIMENSION, 60, 2.0);
    }
    
    public BufferedImage createBarcode(String code) throws InvalidAtributeException{
        String tmp = new String(code);
        if(isCheckDigit()){
            code = code + bcencoder.computeCheckSum(code);
            if(isShowCheckDigit()){
                tmp = code;
            }
            
        }
        BarSet [] encoded = bcencoder.encode(code);
        BufferedImage img = bcpainter.paint(encoded, 1, barHeight, wideToNarrow);
        if(isShowText()){
            textpainter.paintText(img, tmp, 1);
        }
        
        BufferedImage result = new BufferedImage((int)(img.getWidth()*(module/(MIN_XDIMENSION)))+1, (int)getBarHeight(), BufferedImage.TYPE_INT_RGB);
        AffineTransform at =
            AffineTransform.getScaleInstance((module/(MIN_XDIMENSION)),
                getBarHeight()/img.getHeight());
        Graphics2D g2d = result.createGraphics();
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, result.getWidth(), result.getHeight());
        g2d.drawRenderedImage(img, at);
	
        return result;
    }
    
    public String calcCheckSum(String text) throws InvalidAtributeException{
        return bcencoder.computeCheckSum(text);
    }

    public double getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(double barHeight) {
        this.barHeight = (int)Math.round(barHeight/MIN_XDIMENSION);
    }

    public BarcodeEncoder getEncoder() {
        return bcencoder;
    }

    public void setEncoder(BarcodeEncoder bcencoder) {
        this.bcencoder = bcencoder;
    }

    public BarcodePainter getPainter() {
        return bcpainter;
    }

    public void setPainter(BarcodePainter bcpainter) {
        this.bcpainter = bcpainter;
    }

    public double getWideRatio() {
        return wideToNarrow;
    }

    public void setWideRatio(double wideRatio) throws InvalidAtributeException {
        if(wideRatio < 1){
            throw new InvalidAtributeException("[JBarcode] Invalid wide to narrow ratio value.");
        }    
        this.wideToNarrow = wideRatio;
    }

    public double getXDimension() {
        return module;
    }

    public void setXDimension(double xdimension) throws InvalidAtributeException {
        if(xdimension < 0){
            throw new InvalidAtributeException("[JBarcode] Invalid x-dimention value.");
        }        
        this.xdimension = (int)(xdimension/MIN_XDIMENSION);
        this.xdimension = this.xdimension > 0 ? this.xdimension : 1;
        this.module = xdimension;
    }

    public boolean isCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(boolean checkDigit) {
        this.checkDigit = checkDigit;
    }

    public boolean isShowCheckDigit() {
        return showCheckDigit;
    }

    public void setShowCheckDigit(boolean showCheckDigit) {
        this.showCheckDigit = showCheckDigit;
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public TextPainter getTextPainter() {
        return textpainter;
    }

    public void setTextPainter(TextPainter textpainter) {
        this.textpainter = textpainter;
    }
    
    public String toString(){
        return bcencoder.toString();
    }
}