package co.jufeng.string;

public class CharSetUtils {

    public CharSetUtils() {
      super();
    }

    public static CharSet evaluateSet(String[] set) {
        if (set == null) {
            return null;
        }
        return new CharSet(set); 
    }

    public static String squeeze(String str, String set) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(set)) {
            return str;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return squeeze(str, strs);
    }

    public static String squeeze(String str, String[] set) {
        if (StringUtil.isEmpty(str) || ArrayUtils.isEmpty(set)) {
            return str;
        }
        CharSet chars = CharSet.getInstance(set);
        StrBuilder buffer = new StrBuilder(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        char lastChar = ' ';
        char ch = ' ';
        for (int i = 0; i < sz; i++) {
            ch = chrs[i];
            if (chars.contains(ch)) {
                if ((ch == lastChar) && (i != 0)) {
                    continue;
                }
            }
            buffer.append(ch);
            lastChar = ch;
        }
        return buffer.toString();
    }

    public static int count(String str, String set) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(set)) {
            return 0;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return count(str, strs);
    }
    
    public static int count(String str, String[] set) {
        if (StringUtil.isEmpty(str) || ArrayUtils.isEmpty(set)) {
            return 0;
        }
        CharSet chars = CharSet.getInstance(set);
        int count = 0;
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        for(int i=0; i<sz; i++) {
            if(chars.contains(chrs[i])) {
                count++;
            }
        }
        return count;
    }

    public static String keep(String str, String set) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0 || StringUtil.isEmpty(set)) {
            return "";
        }
        String[] strs = new String[1];
        strs[0] = set;
        return keep(str, strs);
    }
    
    public static String keep(String str, String[] set) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0 || ArrayUtils.isEmpty(set)) {
            return "";
        }
        return modify(str, set, true);
    }

    public static String delete(String str, String set) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(set)) {
            return str;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return delete(str, strs);
    }
    
    public static String delete(String str, String[] set) {
        if (StringUtil.isEmpty(str) || ArrayUtils.isEmpty(set)) {
            return str;
        }
        return modify(str, set, false);
    }

    private static String modify(String str, String[] set, boolean expect) {
        CharSet chars = CharSet.getInstance(set);
        StrBuilder buffer = new StrBuilder(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        for(int i=0; i<sz; i++) {
            if(chars.contains(chrs[i]) == expect) {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }

    public static String translate(String str, String searchChars, String replaceChars) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        StrBuilder buffer = new StrBuilder(str.length());
        char[] chrs = str.toCharArray();
        char[] withChrs = replaceChars.toCharArray();
        int sz = chrs.length;
        int withMax = replaceChars.length() - 1;
        for(int i=0; i<sz; i++) {
            int idx = searchChars.indexOf(chrs[i]);
            if(idx != -1) {
                if(idx > withMax) {
                    idx = withMax;
                }
                buffer.append(withChrs[idx]);
            } else {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }

}
