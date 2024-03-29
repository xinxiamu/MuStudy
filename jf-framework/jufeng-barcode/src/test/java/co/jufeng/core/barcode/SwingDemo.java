package co.jufeng.core.barcode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import co.jufeng.barcode.JBarcode;
import co.jufeng.barcode.JBarcodeComponent;
import co.jufeng.barcode.encode.BarcodeEncoder;
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
import co.jufeng.barcode.paint.BarcodePainter;
import co.jufeng.barcode.paint.BaseLineTextPainter;
import co.jufeng.barcode.paint.EAN13TextPainter;
import co.jufeng.barcode.paint.EAN8TextPainter;
import co.jufeng.barcode.paint.HeightCodedPainter;
import co.jufeng.barcode.paint.TextPainter;
import co.jufeng.barcode.paint.UPCATextPainter;
import co.jufeng.barcode.paint.UPCETextPainter;
import co.jufeng.barcode.paint.WideRatioCodedPainter;
import co.jufeng.barcode.paint.WidthCodedPainter;

public class SwingDemo extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JBarcodeComponent jbc;
    private JLabel jLabel2;
    private JLabel jLabel1;
    private JButton jButton2;
    private JButton jButton4;
    private JButton jButton3;
    private JTextField typeText;
    private JTextField jTextField3;
    private JLabel jLabel3;
    private JTextField jTextField2;
    private JButton jButton1;
    private JTextField jTextField1;
    private JBarcode jBarcode_IL;
    
    int index = 0;

    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        SwingDemo inst = new SwingDemo();
        inst.setVisible(true);
    }
    
    public SwingDemo() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            {
                BarcodePainter bp = WidthCodedPainter.getInstance();
                BarcodeEncoder be = EAN13Encoder.getInstance();
                TextPainter txtpainter = EAN13TextPainter.getInstance();
                jBarcode_IL = new JBarcode(be, bp, txtpainter);
                jBarcode_IL.setBarHeight(17);
            }
            setSize(400, 300);
            {
                
                {
                    getContentPane().setLayout(null);
                    this.setTitle("JBarcode - Swing Demo");
                    jbc = new JBarcodeComponent(jBarcode_IL);
                    getContentPane().add(jbc);
                    jbc.setBounds(21, 7, 357, 154);
                    jbc.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
                    jbc.setText("789563251045");
                    jbc.setBarHeight(17);
                    jbc.setCheckSum("4");
                    jbc.setXDimension(0.264583333);
                    jbc.setWideRatio(2.0);
                }
                {
                    jTextField1 = new JTextField();
                    getContentPane().add(jTextField1);
                    jTextField1.setBounds(21, 189, 147, 28);
                    jTextField1.setText("789563251045");
                }
                {
                    jButton1 = new JButton();
                    getContentPane().add(jButton1);
                    jButton1.setText("Ok");
                    jButton1.setBounds(175, 189, 56, 28);
                    jButton1.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            try {
                                jbc.setText(jTextField1.getText());
                            } catch (InvalidAtributeException exc) {
                                JOptionPane.showMessageDialog(null, exc.getMessage(), "Falha", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                }
                {
                    jTextField2 = new JTextField();
                    getContentPane().add(jTextField2);
                    jTextField2.setText("17");
                    jTextField2.setBounds(273, 189, 49, 28);
                }
                {
                    jButton2 = new JButton();
                    getContentPane().add(jButton2);
                    jButton2.setText("Set");
                    jButton2.setBounds(294, 224, 63, 28);
                    jButton2.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            jbc.setBarHeight(Double.parseDouble(jTextField2.getText()));
                            try {
                                jbc.setXDimension(Double.parseDouble(jTextField3.getText()));
                            } catch (NumberFormatException exc) {
                                exc.printStackTrace();
                            } catch (InvalidAtributeException exc) {
                                exc.printStackTrace();
                            }
                        }
                    });
                }
                {
                    jLabel1 = new JLabel();
                    getContentPane().add(jLabel1);
                    jLabel1.setText("Text");
                    jLabel1.setBounds(21, 168, 63, 28);
                }
                {
                    jLabel2 = new JLabel();
                    getContentPane().add(jLabel2);
                    jLabel2.setText("Height");
                    jLabel2.setBounds(273, 168, 63, 28);
                }
                {
                    jLabel3 = new JLabel();
                    getContentPane().add(jLabel3);
                    jLabel3.setText("Width");
                    jLabel3.setBounds(329, 168, 63, 28);
                }
                {
                    jTextField3 = new JTextField();
                    getContentPane().add(jTextField3);
                    jTextField3.setText("0.264583333");
                    jTextField3.setBounds(329, 189, 49, 28);
                }
                {
                    typeText = new JTextField();
                    getContentPane().add(typeText);
                    typeText.setBounds(70, 224, 112, 28);
                    typeText.setEditable(false);
                    typeText.setText(jBarcode_IL.toString());
                }
                {
                    jButton3 = new JButton();
                    getContentPane().add(jButton3);
                    jButton3.setText("<");
                    jButton3.setBounds(21, 224, 49, 28);
                    jButton3.setEnabled(false);
                    jButton3.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            if(jButton3.isEnabled()){
                                setEncoder(--index);
                            }
                        }
                    });   
                }
                {
                    jButton4 = new JButton();
                    getContentPane().add(jButton4);
                    jButton4.setText(">");
                    jButton4.setBounds(182, 224, 49, 28);
                    jButton4.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            if(jButton4.isEnabled()){
                                setEncoder(++index);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
     */
    protected void processWindowEvent(WindowEvent e) {
       super.processWindowEvent(e);
       if(e.getID() == WindowEvent.WINDOW_CLOSING){
           System.exit(0);
       }
    }
    
    void setEncoder(int val){
        switch (val) {
        case 0:
            setTextToSize(12);
            jbc.setEncoder(EAN13Encoder.getInstance());
            jbc.setTextPainter(EAN13TextPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            jButton3.setEnabled(false);
            break;
            
        case 1:
            jbc.setEncoder(UPCAEncoder.getInstance());
            jbc.setTextPainter(UPCATextPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            setTextToSize(11);
            jButton3.setEnabled(true);
            break;
            
        case 2:
            jbc.setEncoder(EAN8Encoder.getInstance());
            jbc.setTextPainter(EAN8TextPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            setTextToSize(7);
            break;
            
        case 3:
            jbc.setEncoder(UPCEEncoder.getInstance());
            jbc.setTextPainter(UPCETextPainter.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            setTextToSize(7);
            break;
            
        case 4:
            jbc.setEncoder(CodabarEncoder.getInstance());
            jbc.setPainter(WideRatioCodedPainter.getInstance());
            jbc.setTextPainter(BaseLineTextPainter.getInstance());
            jbc.setShowText(true);
            break;
        
        case 5:
            jbc.setEncoder(Code11Encoder.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setTextPainter(BaseLineTextPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            break;
            
        case 6:
            jbc.setEncoder(Code39Encoder.getInstance());
            jbc.setPainter(WideRatioCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(false);
            jbc.setShowCheckDigit(false);
            break;
            
        case 7:
            jbc.setEncoder(Code39ExtEncoder.getInstance());
            jbc.setPainter(WideRatioCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(false);
            jbc.setShowCheckDigit(false);
            break;
            
        case 8:
            jbc.setEncoder(Code93Encoder.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(false);
            break;
            
        case 9:
            jbc.setEncoder(Code93ExtEncoder.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(false);
            break;
            
        case 10:
        	jbc.setEncoder(Code128Encoder.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(false);
            break;
            
        case 11:
        	jbc.setEncoder(MSIPlesseyEncoder.getInstance());
            jbc.setPainter(WidthCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            break;
            
        case 12:
        	jbc.setEncoder(Standard2of5Encoder.getInstance());
            jbc.setPainter(WideRatioCodedPainter.getInstance());
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(false);
            break;
            
        case 13:
            jbc.setEncoder(Interleaved2of5Encoder.getInstance());
            jbc.setPainter(WideRatioCodedPainter.getInstance());
            jbc.setBarHeight(17);
            jbc.setShowText(true);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(true);
            jTextField2.setText("17");
            jButton4.setEnabled(true);
            break;
            
        case 14:
            jbc.setEncoder(PostNetEncoder.getInstance());
            jbc.setPainter(HeightCodedPainter.getInstance());
            jbc.setBarHeight(6);
            jbc.setShowText(false);
            jbc.setCheckDigit(true);
            jbc.setShowCheckDigit(false);
            jTextField2.setText("6");
            jButton4.setEnabled(false);
            break;

        default:
            break;
        }
        typeText.setText(jBarcode_IL.toString());
        jTextField1.setText(jbc.getText());
    }
    
    void setTextToSize(int size){
        String text = jbc.getText();
        try {
            if(text.length() > size){
                jbc.setText(text.substring(0, size));
            } else {
                String result = text;
                while(result.length() < size){
                    result += "0";
                }
                jbc.setText(result);
            }
        } catch (InvalidAtributeException exc) {
        }
    }

}
