package co.jufeng.barcode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;

import co.jufeng.barcode.encode.BarcodeEncoder;
import co.jufeng.barcode.encode.InvalidAtributeException;
import co.jufeng.barcode.paint.BarcodePainter;
import co.jufeng.barcode.paint.TextPainter;

public class JBarcodeComponent extends JComponent implements PropertyChangeListener{

    private static final long serialVersionUID = 1L;
    
    private JBarcode jbarcode;
    private BufferedImage img;
    private String text;
    private String checkSum;
    
    public JBarcodeComponent(JBarcode jbarcode){
        this(jbarcode, "");
    }
    
    public JBarcodeComponent(JBarcode jbarcode, String text){
        this.jbarcode = jbarcode;
        addPropertyChangeListener(this);
        if(text != null){
            try {
                setText(text);
            } catch (InvalidAtributeException exc) {}
        }
        this.setBackground(Color.white);
    }
    
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        if(img != null){
            int x = (getWidth() - img.getWidth())/2;
            int y = (getHeight() -img.getHeight())/2;            
            g.drawImage(img, x, y, this);
        }
        super.paint(g);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) throws InvalidAtributeException {
        if(text.equals(getText())){
            return;
        }
        String old = this.text;
        this.text = text;
        this.checkSum = jbarcode.calcCheckSum(text);
        firePropertyChange("Text", old, text);
        invalidate();
        repaint();
    }


    public double getBarHeight() {
        return jbarcode.getBarHeight();
    }

    public BarcodeEncoder getEncoder() {
        return jbarcode.getEncoder();
    }

    public double getXDimension() {
        return jbarcode.getXDimension();
    }

    public BarcodePainter getPainter() {
        return jbarcode.getPainter();
    }
    public double getWideRatio() {
        return jbarcode.getWideRatio();
    }

    public void setBarHeight(double barHeight) {
        if(getBarHeight() == barHeight){
            return;
        }
        double old = getBarHeight();
        jbarcode.setBarHeight(barHeight);
        firePropertyChange("BarHeigth", old, barHeight);
        invalidate();
        repaint();
    }

    public void setEncoder(BarcodeEncoder bcencoder) {
        if(getEncoder().equals(bcencoder)){
            return;
        }
        Object old = getEncoder();
        jbarcode.setEncoder(bcencoder);
        firePropertyChange("Encoder", old, bcencoder);
        invalidate();
        repaint();
    }

    public void setXDimension(double xdim) throws InvalidAtributeException {
        if(getXDimension() == xdim){
            return;
        }
        double old = getXDimension();
        jbarcode.setXDimension(xdim);
        firePropertyChange("xDimension", old, xdim);
        invalidate();
        repaint();
    }

    public void setPainter(BarcodePainter bcpainter) {
        if(getPainter().equals(bcpainter)){
            return;
        }
        Object old = getPainter();
        jbarcode.setPainter(bcpainter);
        firePropertyChange("Painter", old, bcpainter);
        invalidate();
        repaint();
    }

    public void setWideRatio(double wideRatio) throws InvalidAtributeException {
        if(getWideRatio() == wideRatio){
            return;
        }
        double old = getWideRatio();
        jbarcode.setWideRatio(wideRatio);
        firePropertyChange("wideRatio", old, wideRatio);
        invalidate();
        repaint();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            if(text != null && !"".equals(text) && jbarcode != null){
                img = jbarcode.createBarcode(text);
            }
        } catch (InvalidAtributeException exc) {
        }
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        firePropertyChange("checkSum", this.checkSum, this.checkSum);
        invalidate();
        repaint();
    }

    public TextPainter getTextPainter() {
        return jbarcode.getTextPainter();
    }

    public boolean isCheckDigit() {
        return jbarcode.isCheckDigit();
    }

    public boolean isShowCheckDigit() {
        return jbarcode.isShowCheckDigit();
    }

    public boolean isShowText() {
        return jbarcode.isShowText();
    }

    public void setCheckDigit(boolean checkDigit) {
        if(checkDigit == isCheckDigit()){
            return;
        }
        boolean old = isCheckDigit();
        jbarcode.setCheckDigit(checkDigit);
        firePropertyChange("checkDigit", old, checkDigit);
        invalidate();
        repaint();
    }

    public void setShowCheckDigit(boolean showCheckDigit) {
        if(showCheckDigit == isShowCheckDigit()){
            return;
        }
        boolean old = isShowCheckDigit();
        jbarcode.setShowCheckDigit(showCheckDigit);
        firePropertyChange("showCheckDigit", old, showCheckDigit);
        invalidate();
        repaint();
    }

    public void setShowText(boolean showText) {
        if(showText == isShowText()){
            return;
        }
        boolean old = isShowText();
        jbarcode.setShowText(showText);
        firePropertyChange("showText", old, showText);
        invalidate();
        repaint();
    }

    public void setTextPainter(TextPainter textpainter) {
        if(getTextPainter().equals(textpainter)){
            return;
        }
        Object old = getTextPainter();
        jbarcode.setTextPainter(textpainter);
        firePropertyChange("textPainter", old, textpainter);
        invalidate();
        repaint();
        jbarcode.setTextPainter(textpainter);
    }
    
}