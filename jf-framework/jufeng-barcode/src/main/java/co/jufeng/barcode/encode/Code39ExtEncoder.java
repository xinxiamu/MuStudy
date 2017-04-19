package co.jufeng.barcode.encode;

public class Code39ExtEncoder extends Code39Encoder {
    
    protected static String [] CODES_TABLE = new String[]{
        "%U", "$A", "$B", "$C", "$D", "$E", "$F", "$G",
        "$H", "$I", "$J", "$K", "$L", "$M", "$N", "$O",
        "$P", "$Q", "$R", "$S", "$T", "$U", "$V", "$W",
        "$X", "$Y", "$Z", "%A", "%B", "%C", "%D", "%E",
        " ", "/A", "/B", "/C", "/D", "/E", "/F", "/G", 
        "/H", "/I", "/J", "/K", "/L", "-", ".", "/O",
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "/Z", "%F", "%G", "%H", "%I", "%J",
        "%V", "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
        "T", "U", "V", "W", "X", "Y", "Z", "%K", "%L", "%M",
        "%N", "%O", "%W", "+A", "+B", "+C", "+D", "+E",
        "+F", "+G", "+H", "+I", "+J", "+K", "+L", "+M",
        "+N", "+O", "+P", "+Q", "+R", "+S", "+T", "+U",
        "+V", "+W", "+X", "+Y", "+Z", "%P", "%Q", "%R",
        "%S", "%T"  
    };
    
    private static Code39ExtEncoder instance;

    protected Code39ExtEncoder(){
        
    }
    
    public static BarcodeEncoder getInstance(){
        if(instance == null){
            instance = new Code39ExtEncoder();
        }
        return instance;
    }
    
    public BarSet[] encode(String text) throws InvalidAtributeException {
        return super.encode(convertText(text));
    }
    
    public String computeCheckSum(String text) throws InvalidAtributeException {
        return super.computeCheckSum(convertText(text));
    }

    public String toString(){
        return "CODE 39 (EXT)";
    }
    
    protected static String convertText(String str){
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            tmp.append(CODES_TABLE[str.charAt(i)]);
        }
        return tmp.toString();
    }
    
}
