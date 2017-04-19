package co.jufeng.string;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"rawtypes"})
public final class CharRange implements Serializable {
    private static final long serialVersionUID = 8270183163158333422L;
    private final char start;
    private final char end;
    private final boolean negated;
    private transient String iToString;
    public static CharRange is(char ch) {
        return new CharRange(ch, ch, false);
    }

    public static CharRange isNot(char ch) {
        return new CharRange(ch, ch, true);
    }

    public static CharRange isIn(char start, char end) {
        return new CharRange(start, end, false);
    }

    public static CharRange isNotIn(char start, char end) {
        return new CharRange(start, end, true);
    }

    public CharRange(char ch) {
        this(ch, ch, false);
    }

    public CharRange(char ch, boolean negated) {
        this(ch, ch, negated);
    }

    public CharRange(char start, char end) {
        this(start, end, false);
    }

    public CharRange(char start, char end, boolean negated) {
        super();
        if (start > end) {
            char temp = start;
            start = end;
            end = temp;
        }
        
        this.start = start;
        this.end = end;
        this.negated = negated;
    }

    public char getStart() {
        return this.start;
    }

    public char getEnd() {
        return this.end;
    }

    public boolean isNegated() {
        return negated;
    }

    public boolean contains(char ch) {
        return (ch >= start && ch <= end) != negated;
    }

    public boolean contains(CharRange range) {
        if (range == null) {
            throw new IllegalArgumentException("The Range must not be null");
        }
        if (negated) {
            if (range.negated) {
                return start >= range.start && end <= range.end;
            }
            return range.end < start || range.start > end;
        }
        if (range.negated) {
            return start == 0 && end == Character.MAX_VALUE;
        }
        return start <= range.start && end >= range.end;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CharRange == false) {
            return false;
        }
        CharRange other = (CharRange) obj;
        return start == other.start && end == other.end && negated == other.negated;
    }

    public int hashCode() {
        return 83 + start + 7 * end + (negated ? 1 : 0);
    }
    
    public String toString() {
        if (iToString == null) {
            StrBuilder buf = new StrBuilder(4);
            if (isNegated()) {
                buf.append('^');
            }
            buf.append(start);
            if (start != end) {
                buf.append('-');
                buf.append(end);
            }
            iToString = buf.toString();
        }
        return iToString;
    }

    public Iterator iterator() {
        return new CharacterIterator(this);
    }

    private static class CharacterIterator implements Iterator {
        private char current;
        private final CharRange range;
        private boolean hasNext;

        private CharacterIterator(CharRange r) {
            range = r;
            hasNext = true;

            if (range.negated) {
                if (range.start == 0) {
                    if (range.end == Character.MAX_VALUE) {
                        // This range is an empty set
                        hasNext = false;
                    } else {
                        current = (char) (range.end + 1);
                    }
                } else {
                    current = 0;
                }
            } else {
                current = range.start;
            }
        }

        private void prepareNext() {
            if (range.negated) {
                if (current == Character.MAX_VALUE) {
                    hasNext = false;
                } else if (current + 1 == range.start) {
                    if (range.end == Character.MAX_VALUE) {
                        hasNext = false;
                    } else {
                        current = (char) (range.end + 1);
                    }
                } else {
                    current = (char) (current + 1);
                }
            } else if (current < range.end) {
                current = (char) (current + 1);
            } else {
                hasNext = false;
            }
        }

        public boolean hasNext() {
            return hasNext;
        }

        public Object next() {
            if (hasNext == false) {
                throw new NoSuchElementException();
            }
            char cur = current;
            prepareNext();
            return new Character(cur);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
