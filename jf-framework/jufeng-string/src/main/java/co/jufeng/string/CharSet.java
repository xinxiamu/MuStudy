package co.jufeng.string;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CharSet implements Serializable {

    private static final long serialVersionUID = 5947847346149275958L;

    public static final CharSet EMPTY = new CharSet((String) null);

    public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");

    public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");

    public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");

    public static final CharSet ASCII_NUMERIC = new CharSet("0-9");

    protected static final Map COMMON = Collections.synchronizedMap(new HashMap());
    
    static {
        COMMON.put(null, EMPTY);
        COMMON.put("", EMPTY);
        COMMON.put("a-zA-Z", ASCII_ALPHA);
        COMMON.put("A-Za-z", ASCII_ALPHA);
        COMMON.put("a-z", ASCII_ALPHA_LOWER);
        COMMON.put("A-Z", ASCII_ALPHA_UPPER);
        COMMON.put("0-9", ASCII_NUMERIC);
    }

    private final Set set = Collections.synchronizedSet(new HashSet());

    public static CharSet getInstance(String setStr) {
        Object set = COMMON.get(setStr);
        if (set != null) {
            return (CharSet) set;
        }
        return new CharSet(setStr);
    }

    public static CharSet getInstance(String[] setStrs) {
        if (setStrs == null) {
            return null;
        }
        return new CharSet(setStrs); 
    }

    protected CharSet(String setStr) {
        super();
        add(setStr);
    }

    protected CharSet(String[] set) {
        super();
        int sz = set.length;
        for (int i = 0; i < sz; i++) {
            add(set[i]);
        }
    }

    protected void add(String str) {
        if (str == null) {
            return;
        }

        int len = str.length();
        int pos = 0;
        while (pos < len) {
            int remainder = (len - pos);
            if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
                // negated range
                set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
                pos += 4;
            } else if (remainder >= 3 && str.charAt(pos + 1) == '-') {
                // range
                set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
                pos += 3;
            } else if (remainder >= 2 && str.charAt(pos) == '^') {
                // negated char
                set.add(CharRange.isNot(str.charAt(pos + 1)));
                pos += 2;
            } else {
                // char
                set.add(CharRange.is(str.charAt(pos)));
                pos += 1;
            }
        }
    }

    public CharRange[] getCharRanges() {
        return (CharRange[]) set.toArray(new CharRange[set.size()]);
    }

    public boolean contains(char ch) {
        for (Iterator it = set.iterator(); it.hasNext();) {
            CharRange range = (CharRange) it.next();
            if (range.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CharSet == false) {
            return false;
        }
        CharSet other = (CharSet) obj;
        return set.equals(other.set);
    }

    public int hashCode() {
        return 89 + set.hashCode();
    }

    public String toString() {
        return set.toString();
    }

}
