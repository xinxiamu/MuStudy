package co.jufeng.barcode;

import co.jufeng.barcode.encode.CodabarEncoder;
import co.jufeng.barcode.encode.Code11Encoder;
import co.jufeng.barcode.encode.Code128Encoder;
import co.jufeng.barcode.encode.Code39Encoder;
import co.jufeng.barcode.encode.Code39ExtEncoder;
import co.jufeng.barcode.encode.Code93Encoder;
import co.jufeng.barcode.encode.Code93ExtEncoder;
import co.jufeng.barcode.encode.EAN13Encoder;
import co.jufeng.barcode.encode.EAN8Encoder;
import co.jufeng.barcode.encode.Interleaved2of5Encoder;
import co.jufeng.barcode.encode.InvalidAtributeException;
import co.jufeng.barcode.encode.MSIPlesseyEncoder;
import co.jufeng.barcode.encode.PostNetEncoder;
import co.jufeng.barcode.encode.Standard2of5Encoder;
import co.jufeng.barcode.encode.UPCAEncoder;
import co.jufeng.barcode.encode.UPCEEncoder;
import co.jufeng.barcode.paint.BaseLineTextPainter;
import co.jufeng.barcode.paint.EAN13TextPainter;
import co.jufeng.barcode.paint.EAN8TextPainter;
import co.jufeng.barcode.paint.HeightCodedPainter;
import co.jufeng.barcode.paint.UPCATextPainter;
import co.jufeng.barcode.paint.UPCETextPainter;
import co.jufeng.barcode.paint.WideRatioCodedPainter;
import co.jufeng.barcode.paint.WidthCodedPainter;

public class JBarcodeFactory {
	
	private static JBarcodeFactory instance;
	
	private JBarcodeFactory(){	
	}
	
	public static JBarcodeFactory getInstance(){
		if(instance == null){
			instance = new JBarcodeFactory();
		}
		return instance;
	}
	
	public JBarcode createEAN13(){
		JBarcode jbc = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}
	
	public JBarcode createEAN8(){
		JBarcode jbc = new JBarcode(EAN8Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN8TextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}

	public JBarcode createUPCA(){
		JBarcode jbc = new JBarcode(UPCAEncoder.getInstance(), WidthCodedPainter.getInstance(), UPCATextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}
	
	public JBarcode createUPCE(){
		JBarcode jbc = new JBarcode(UPCEEncoder.getInstance(), WidthCodedPainter.getInstance(), UPCETextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}
	
	public JBarcode createCodabar(){
		JBarcode jbc = new JBarcode(CodabarEncoder.getInstance(), WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(false);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createCode11(){
		JBarcode jbc = new JBarcode(Code11Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}
	
	public JBarcode createCode39(){
		JBarcode jbc = new JBarcode(Code39Encoder.getInstance(), WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(false);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createCode39Extended(){
		JBarcode jbc = new JBarcode(Code39ExtEncoder.getInstance(), WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(false);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createCode93(){
		JBarcode jbc = new JBarcode(Code93Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createCode93Extended(){
		JBarcode jbc = new JBarcode(Code93ExtEncoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createCode128(){
		JBarcode jbc = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createMSIPlessey(){
		JBarcode jbc = new JBarcode(MSIPlesseyEncoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(true);
		return jbc;
	}
	
	public JBarcode createStandard2of5(){
		JBarcode jbc = new JBarcode(Standard2of5Encoder.getInstance(), WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createInterleaved2of5(){
		JBarcode jbc = new JBarcode(Interleaved2of5Encoder.getInstance(), WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(17);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(true);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
	public JBarcode createPostNet(){
		JBarcode jbc = new JBarcode(PostNetEncoder.getInstance(), HeightCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		jbc.setBarHeight(6);
		try {
			jbc.setXDimension(0.264583333);
		} catch (InvalidAtributeException e) {}
		jbc.setShowText(false);
        jbc.setCheckDigit(true);
        jbc.setShowCheckDigit(false);
		return jbc;
	}
	
}
