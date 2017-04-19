package co.jufeng.barcode.encode;


public abstract class EANEncoder implements BarcodeEncoder {

	protected static final BarSet [] CODES = new BarSet[]{
			new BarSet("1110010"), new BarSet("1100110"), new BarSet("1101100"),
			new BarSet("1000010"), new BarSet("1011100"), new BarSet("1001110"),
			new BarSet("1010000"), new BarSet("1000100"), new BarSet("1001000"),
			new BarSet("1110100")
	};
		
	protected static final BarSet LEFT_GUARD = new BarSet("101"); 
	
	protected static final BarSet CENTER_GUARD = new BarSet("01010");
	
	protected static final BarSet RIGTH_GUARD = new BarSet("101");
    
    public String computeCheckSum(String text) throws InvalidAtributeException{
		
		int sum = 0;
		boolean odd = true;
		for(int charPos = text.length() - 1; charPos >= 0; charPos--) {
			if( odd ){
				sum += 3*charToInt(text.charAt(charPos));
			} else {
				sum += charToInt(text.charAt(charPos));
			}
			odd = !odd; 
		}

		int result = sum%10;
		if( result == 0 ){
			return "0";
		} else{
			return Integer.toString(10 - result);
		}
	}
    
    protected static int charToInt(char val) throws NumberFormatException{
    	if(!Character.isDigit(val)){
    		throw new NumberFormatException("Invalid number");
    	}
        return val - '0';
    }

}
