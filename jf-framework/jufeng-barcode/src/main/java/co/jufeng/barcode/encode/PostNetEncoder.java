package co.jufeng.barcode.encode;


public class PostNetEncoder implements BarcodeEncoder {
	
	protected final BarSet [] CODES = new BarSet[]{
			new BarSet("11000"), new BarSet("00011"), new BarSet("00101"),
			new BarSet("00110"), new BarSet("01001"), new BarSet("01010"),
			new BarSet("01100"), new BarSet("10001"), new BarSet("10010"),
			new BarSet("10100")
	};
	
	protected final BarSet START_STOP_CHAR = new BarSet("1");
    
    private static PostNetEncoder instance;
    
    
    private PostNetEncoder(){
        
    }
    
    public static PostNetEncoder getInstance(){
        if(instance == null){
            instance = new PostNetEncoder();
        }
        return instance;
    }

	public BarSet[] encode(String texto) throws InvalidAtributeException {
		if( texto.length() < 1 ){
			throw new InvalidAtributeException("[PostNet] Tamanho de texto invalido (" + texto.length() + ").");
		}

		BarSet [] result = new BarSet[texto.length() + 2];
		try{		
			result[0] = START_STOP_CHAR;
			//gera a primeira parte do c�digo
			for (int i = 0; i < texto.length(); i++) {
				int atual = Integer.parseInt(String.valueOf(texto.charAt(i)));
				result[i+1] = CODES[atual];
			}
			result[result.length-1] = START_STOP_CHAR;
		} catch( NumberFormatException nfexc ){
			throw new InvalidAtributeException("[PostNet] O padrao suporta apenas numeros.");
		}
		return result;

	}

	public String computeCheckSum(String texto) throws InvalidAtributeException {
		int check = 0;
		for (int i = 0; i < texto.length(); i++) {
			check += Integer.parseInt(String.valueOf(texto.charAt(i)));
		}
		check = 10 - (check%10);
		return String.valueOf(check);
	}
    
    public String toString(){
        return "PostNet";
    }

}
