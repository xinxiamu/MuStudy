package co.jufeng.barcode.encode;

public class EAN13Encoder extends EANEncoder {
	
	private static EAN13Encoder instance;
    
    protected BarSet [] DIGIT_PARITY = new BarSet[]{
		new BarSet("000000"), new BarSet("001011"), new BarSet("001101"),
		new BarSet("001110"), new BarSet("010011"), new BarSet("011001"),
		new BarSet("011100"), new BarSet("010101"), new BarSet("010110"),
		new BarSet("011010")
	};
    
    
    protected EAN13Encoder(){
        
    }
    
    public static EAN13Encoder getInstance(){
        if(instance == null){
            instance = new EAN13Encoder();
        }
        return instance;
    }	
	
	public BarSet[] encode(String texto) throws InvalidAtributeException {
		if( texto.length() < 12 || texto.length() > 13  ){
			throw new InvalidAtributeException("[EAN13] Invalid text length (" + texto.length() + ").");
		}
		
		BarSet [] result = new BarSet[15];
		try{		
            int primeiro = charToInt(texto.charAt(0));
			//tres barras iniciais
			result[0] = LEFT_GUARD;
			//gera a primeira parte do codigo
			for (int i = 1; i <= 6; i++) {
				int atual = charToInt(texto.charAt(i));
				if( DIGIT_PARITY[primeiro].get(i-1) ){			
					result[i] = CODES[atual].reverse();
				} else {
					result[i] = CODES[atual].xorTrue();
				}
			}
			//barras centrais que dividem o codigo
			result[7] = CENTER_GUARD;
			for (int i = 7; i <= 12; i++) {
				result[i + 1] = CODES[charToInt(texto.charAt(i))];
			}
			//barras centrais que dividem o codigo
			result[14] = RIGTH_GUARD;
		} catch( NumberFormatException nfexc ){
			throw new InvalidAtributeException("[EAN13] Only numbers suported.");
		}
		return result;
	}
    
    public String toString(){
        return "EAN 13";
    }
}
