package co.jufeng.barcode.encode;

public class BarSet extends java.util.BitSet {
	
	private static final long serialVersionUID = 1L;
    
    private int length;

	
	public BarSet(int nbits){
		super(nbits);
		this.length = nbits;		
	}
	
	public BarSet(String bits) {
		super(bits.length());
		for( int i = 0; i < bits.length(); i++ ){
			if( bits.charAt(i) == '1' ){
				this.set(i);
			} else if( bits.charAt(i) != '0' ){
				throw new RuntimeException("Invalid Bit value: " + bits.charAt(i));
			}
		}
		length = bits.length();
	}
	
	public BarSet reverse(){
		BarSet result = new BarSet(this.length());
		for( int i = 0; i < this.length(); i++ ){
			if( this.get(i) ){
				result.set(result.length() - (i + 1));
			}
		}
		return result;
	}
	
	public BarSet xorTrue(){
		BarSet result = new BarSet(this.length());
		for( int i = 0; i < result.length(); i++ ){
			result.set(i);
		}
		result.xor(this);
		return result; 
	}
	
	public int length(){
		return length;
	}
	
	public String toString(){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if(get(i)){
				result.append(1);
			} else {
				result.append(0);
			}
		}
		return result.toString();
	}

}
