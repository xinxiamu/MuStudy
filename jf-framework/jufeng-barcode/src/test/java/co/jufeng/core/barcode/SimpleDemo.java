package co.jufeng.core.barcode;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import co.jufeng.barcode.JBarcode;
import co.jufeng.barcode.encode.CodabarEncoder;
import co.jufeng.barcode.encode.Code128Encoder;
import co.jufeng.barcode.encode.Code39Encoder;
import co.jufeng.barcode.encode.Code93Encoder;
import co.jufeng.barcode.encode.EAN13Encoder;
import co.jufeng.barcode.encode.EAN8Encoder;
import co.jufeng.barcode.encode.Interleaved2of5Encoder;
import co.jufeng.barcode.encode.PostNetEncoder;
import co.jufeng.barcode.encode.Standard2of5Encoder;
import co.jufeng.barcode.encode.UPCAEncoder;
import co.jufeng.barcode.encode.UPCEEncoder;
import co.jufeng.barcode.gif.Gif89Encoder;
import co.jufeng.barcode.paint.BaseLineTextPainter;
import co.jufeng.barcode.paint.EAN13TextPainter;
import co.jufeng.barcode.paint.EAN8TextPainter;
import co.jufeng.barcode.paint.HeightCodedPainter;
import co.jufeng.barcode.paint.UPCATextPainter;
import co.jufeng.barcode.paint.UPCETextPainter;
import co.jufeng.barcode.paint.WideRatioCodedPainter;
import co.jufeng.barcode.paint.WidthCodedPainter;
import co.jufeng.barcode.util.ImageUtil;


public class SimpleDemo extends TestCase{
	
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public SimpleDemo(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SimpleDemo.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSimpleDemo() {
		assertTrue(true);
	}

    public void testBarcode(){
        try{
            //Creates a JBarcode with a EAN13Encoder and a WidthCodedPainter
            JBarcode jbcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
            
            String code = "788515004012";
            BufferedImage img = jbcode.createBarcode(code);
            saveToGIF(img, "EAN13.gif");
            
            //EAN8 Code Example
            jbcode.setEncoder(EAN8Encoder.getInstance());
            jbcode.setTextPainter(EAN8TextPainter.getInstance());
            code = "9788515";        
            img = jbcode.createBarcode(code);
            saveToPNG(img, "EAN8.png");
            
            //UPCA Code Example
            jbcode.setEncoder(UPCAEncoder.getInstance());
            jbcode.setTextPainter(UPCATextPainter.getInstance());
            code = "07567816415";        
            img = jbcode.createBarcode(code);
            saveToPNG(img, "UPCA.png");
            
            //UPCE Code Example
            jbcode.setEncoder(UPCEEncoder.getInstance());
            jbcode.setTextPainter(UPCETextPainter.getInstance());
            code = UPCAEncoder.getInstance().convertUPCAtoUPCE("07567816415");        
            img = jbcode.createBarcode(code);
            saveToPNG(img, "UPCE.png");
            
            //Codabar Code Example
            jbcode.setEncoder(CodabarEncoder.getInstance());
            jbcode.setPainter(WideRatioCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            code = "97885150040-85";
            jbcode.setWideRatio(3.0);
            img = jbcode.createBarcode(code);
            saveToJPEG(img, "Codabar.jpg");
            
            //Code39 Code Example
            jbcode.setEncoder(Code39Encoder.getInstance());
            jbcode.setPainter(WideRatioCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            jbcode.setShowCheckDigit(false);
            code = "JBARCODE-39";
            img = jbcode.createBarcode(code);
            saveToPNG(img, "Code39.png");
            
            //Code93 Code Example
            jbcode.setEncoder(Code93Encoder.getInstance());
            jbcode.setPainter(WidthCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            jbcode.setShowCheckDigit(false);
            code = "JBARCODE-93";
            img = jbcode.createBarcode(code);
            saveToPNG(img, "Code93.png");
            
            //Code128 Code Example
            jbcode.setEncoder(Code128Encoder.getInstance());
            jbcode.setPainter(WidthCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            jbcode.setShowCheckDigit(false);
            code = "JBarcode-128";
            img = jbcode.createBarcode(code);
            saveToPNG(img, "Code128.png");
            
            //Standard 2 of 5 Code Example
            jbcode.setEncoder(Standard2of5Encoder.getInstance());
            jbcode.setPainter(WideRatioCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            jbcode.setShowCheckDigit(true);
            code = "978851500404";
            img = jbcode.createBarcode(code);
            saveToJPEG(img, "Standard2of5.jpg");
            
            //Interleaved 2 of 5 Code Example
            jbcode.setEncoder(Interleaved2of5Encoder.getInstance());
            jbcode.setPainter(WideRatioCodedPainter.getInstance());
            jbcode.setTextPainter(BaseLineTextPainter.getInstance());
            jbcode.setShowCheckDigit(true);
            code = "978851500404";        
            img = jbcode.createBarcode(code);
            saveToPNG(img, "Interleaved2of5.png");
            
            //PostNet Code Example
            jbcode.setEncoder(PostNetEncoder.getInstance());
            jbcode.setPainter(HeightCodedPainter.getInstance());
            jbcode.setBarHeight(6);
            jbcode.setXDimension(0.5291666);
            jbcode.setShowText(false);
            code = "805365961"; 
            img = jbcode.createBarcode(code);
            saveToJPEG(img, "PostNet.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    static void saveToJPEG(BufferedImage img, String fileName){
        saveToFile(img, fileName, ImageUtil.JPEG);
    }
    
    static void saveToPNG(BufferedImage img, String fileName){
       saveToFile(img, fileName, ImageUtil.PNG);
    }
    
    static void saveToGIF(BufferedImage img, String fileName){
        saveToFile(img, fileName, ImageUtil.GIF);
    }     
    
    static void saveToFile(BufferedImage img, String fileName, String format){
    	try{
    		String path = System.getProperty("user.dir") + File.separator + "images" + File.separator;
    		new File(path).mkdirs();
    		FileOutputStream fos = new FileOutputStream(path + fileName);
            ImageUtil.encodeAndWrite(img, format, fos, 96, 96);
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		try {

			Toolkit tk = Toolkit.getDefaultToolkit();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					"gif89out.gif"));

			if (args[0].toUpperCase().endsWith(".JPG"))
				new Gif89Encoder(tk.getImage(args[0])).encode(out);
			else {
				BufferedReader in = new BufferedReader(new FileReader(args[0]));
				Gif89Encoder ge = new Gif89Encoder();

				String line;
				while ((line = in.readLine()) != null)
					ge.addFrame(tk.getImage(line.trim()));
				ge.setLoopCount(0); // let's loop indefinitely
				ge.encode(out);

				in.close();
			}
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		} // must kill VM explicitly (Toolkit thread?)
	}

}
