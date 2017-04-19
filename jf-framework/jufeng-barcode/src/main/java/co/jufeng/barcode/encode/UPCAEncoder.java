package co.jufeng.barcode.encode;


public class UPCAEncoder extends EANEncoder {
    
    private static UPCAEncoder instance;
   
    private UPCAEncoder(){
        
    }
    
    public static UPCAEncoder getInstance(){
        if(instance == null){
            instance = new UPCAEncoder();
        }
        return instance;
    }
	
	
	public BarSet[] encode(String texto) throws InvalidAtributeException {
		if( texto.length() < 11 || texto.length() > 12  ){
			throw new InvalidAtributeException("[UPCA] Invalid text length (" + texto.length() + ").");
		}
		try{
			return EAN13Encoder.getInstance().encode("0" + texto);
		} catch( InvalidAtributeException tiexc ){
			throw new InvalidAtributeException("[UPCA] Only numbers suported.", tiexc);
		}
	}

	public String computeCheckSum(String texto) throws InvalidAtributeException {
		return super.computeCheckSum("0" + texto);
	}
    
    public String convertUPCAtoUPCE(String upca) throws InvalidAtributeException{
        if(upca.length() != 11 && upca.length() != 12){
            throw new InvalidAtributeException("[UPCA] Invalid text length (" + upca.length() + ").");
        }
        StringBuffer result = new StringBuffer();
        result.append(upca.charAt(0));
        if( upca.charAt(0) != '0' && upca.charAt(0) != '1' ){
            throw new InvalidAtributeException("[UPCA] Invalid Number System,  only 0 & 1 are valid (" + upca.charAt(0) + ").");
        } else if( "000".equals(upca.substring(3, 6)) || "100".equals(upca.substring(3, 6)) || "200".equals(upca.substring(3, 6)) ){
            result.append(upca.substring(1, 3));
            result.append(upca.substring(8, 11));
            result.append(upca.charAt(3));
        } else if( "00".equals(upca.substring(4, 6)) ){
            result.append(upca.substring(1, 4));
            result.append(upca.substring(9, 11));
            result.append('3');
        } else if( upca.charAt(5) == '0' ){
            result.append(upca.substring(1, 5));
            result.append(upca.charAt(10));
            result.append('4');
        } else if( upca.charAt(10) >= '5' && upca.charAt(10) <= '9' ){
            result.append(upca.substring(1, 6));
            result.append(upca.charAt(10));
        } else {
            throw new InvalidAtributeException("[UPCA] Invalid code.");
        }
        if(upca.length() == 12){
            result.append(upca.charAt(11));
        }
        return result.toString();
    }
    
    public String toString(){
        return "UPC-A";
    }
}
