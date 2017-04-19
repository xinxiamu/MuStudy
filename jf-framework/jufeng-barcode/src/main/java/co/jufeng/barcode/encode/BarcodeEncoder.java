package co.jufeng.barcode.encode;

public interface BarcodeEncoder {
   
	public BarSet[] encode(String text) throws InvalidAtributeException;
	
	public String computeCheckSum(String text) throws InvalidAtributeException;
	
}