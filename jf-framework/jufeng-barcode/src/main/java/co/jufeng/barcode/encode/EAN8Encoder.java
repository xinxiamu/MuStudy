package co.jufeng.barcode.encode;

public class EAN8Encoder extends EANEncoder {
    
    private static EAN8Encoder instance;

   
    private EAN8Encoder() {
        
    }
    
    public static EAN8Encoder getInstance(){
        if(instance == null){
            instance = new EAN8Encoder();
        }
        return instance;
    }

	public BarSet[] encode(String texto) throws InvalidAtributeException {
		if( texto.length() != 8  ){
			throw new InvalidAtributeException("[EAN8] Invalid text length (" + texto.length() + ").");
		}
		
		BarSet [] result = new BarSet[11];
		try{		
            //tres barras iniciais
			result[0] = LEFT_GUARD;
			//gera a primeira parte do codigo
			for (int i = 0; i < 4; i++) {
				int atual = charToInt(texto.charAt(i));
				result[i + 1] = CODES[atual].xorTrue();
			}
			//barras centrais que dividem o codigo
			result[5] = CENTER_GUARD;
			for (int i = 4; i < 8; i++) {
				int atual = charToInt(texto.charAt(i));
				result[i + 2] = CODES[atual];
			}
			//barras centrais que dividem o codigo
			result[10] = RIGTH_GUARD;
		} catch( NumberFormatException nfexc ){
			throw new InvalidAtributeException("[EAN8] Only numbers suported.");
		}
		return result;
	}
    
    public String toString(){
        return "EAN 8";
    }

}
