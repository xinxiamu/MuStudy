package co.jufeng.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"rawtypes", "unchecked"})
public class StrTokenizer implements ListIterator, Cloneable {

    private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE;
    private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
    static {
        CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
        CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
        CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);

        TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
        TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
        TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
    }

    private char chars[];
    private String tokens[];
    private int tokenPos;

    private StrMatcher delimMatcher = StrMatcher.splitMatcher();
    private StrMatcher quoteMatcher = StrMatcher.noneMatcher();
    private StrMatcher ignoredMatcher = StrMatcher.noneMatcher();
    private StrMatcher trimmerMatcher = StrMatcher.noneMatcher();

    private boolean emptyAsNull = false;
    private boolean ignoreEmptyTokens = true;

    private static StrTokenizer getCSVClone() {
        return (StrTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return getCSVClone();
    }

    public static StrTokenizer getCSVInstance(String input) {
        StrTokenizer tok = getCSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getCSVInstance(char[] input) {
        StrTokenizer tok = getCSVClone();
        tok.reset(input);
        return tok;
    }

    private static StrTokenizer getTSVClone() {
        return (StrTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }


    public static StrTokenizer getTSVInstance() {
        return getTSVClone();
    }

    public static StrTokenizer getTSVInstance(String input) {
        StrTokenizer tok = getTSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getTSVInstance(char[] input) {
        StrTokenizer tok = getTSVClone();
        tok.reset(input);
        return tok;
    }

    public StrTokenizer() {
        super();
        this.chars = null;
    }

    public StrTokenizer(String input) {
        super();
        if (input != null) {
            chars = input.toCharArray();
        } else {
            chars = null;
        }
    }

    public StrTokenizer(String input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StrTokenizer(String input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StrTokenizer(String input, StrMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StrTokenizer(String input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StrTokenizer(String input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    public StrTokenizer(char[] input) {
        super();
        this.chars = input;
    }

    public StrTokenizer(char[] input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StrTokenizer(char[] input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StrTokenizer(char[] input, StrMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StrTokenizer(char[] input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    public int size() {
        checkTokenized();
        return tokens.length;
    }

    public String nextToken() {
        if (hasNext()) {
            return tokens[tokenPos++];
        }
        return null;
    }

    public String previousToken() {
        if (hasPrevious()) {
            return tokens[--tokenPos];
        }
        return null;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) tokens.clone();
    }

    public List getTokenList() {
        checkTokenized();
        List list = new ArrayList(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            list.add(tokens[i]);
        }
        return list;
    }

    public StrTokenizer reset() {
        tokenPos = 0;
        tokens = null;
        return this;
    }

    public StrTokenizer reset(String input) {
        reset();
        if (input != null) {
            this.chars = input.toCharArray();
        } else {
            this.chars = null;
        }
        return this;
    }

    public StrTokenizer reset(char[] input) {
        reset();
        this.chars = input;
        return this;
    }

    public boolean hasNext() {
        checkTokenized();
        return tokenPos < tokens.length;
    }

    public Object next() {
        if (hasNext()) {
            return tokens[tokenPos++];
        }
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return tokenPos;
    }

    public boolean hasPrevious() {
        checkTokenized();
        return tokenPos > 0;
    }

    public Object previous() {
        if (hasPrevious()) {
            return tokens[--tokenPos];
        }
        throw new NoSuchElementException();
    }

    public int previousIndex() {
        return tokenPos - 1;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public void set(Object obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void checkTokenized() {
        if (tokens == null) {
            if (chars == null) {
                // still call tokenize as subclass may do some work
                List split = tokenize(null, 0, 0);
                tokens = (String[]) split.toArray(new String[split.size()]);
            } else {
                List split = tokenize(chars, 0, chars.length);
                tokens = (String[]) split.toArray(new String[split.size()]);
            }
        }
    }

    protected List tokenize(char[] chars, int offset, int count) {
        if (chars == null || count == 0) {
            return Collections.EMPTY_LIST;
        }
        StrBuilder buf = new StrBuilder();
        List tokens = new ArrayList();
        int pos = offset;
        
        // loop around the entire buffer
        while (pos >= 0 && pos < count) {
            // find next token
            pos = readNextToken(chars, pos, count, buf, tokens);
            
            // handle case where end of string is a delimiter
            if (pos >= count) {
                addToken(tokens, "");
            }
        }
        return tokens;
    }

    private void addToken(List list, String tok) {
        if (tok == null || tok.length() == 0) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                tok = null;
            }
        }
        list.add(tok);
    }

    private int readNextToken(char[] chars, int start, int len, StrBuilder workArea, List tokens) {
        // skip all leading whitespace, unless it is the
        // field delimiter or the quote character
        while (start < len) {
            int removeLen = Math.max(
                    getIgnoredMatcher().isMatch(chars, start, start, len),
                    getTrimmerMatcher().isMatch(chars, start, start, len));
            if (removeLen == 0 ||
                getDelimiterMatcher().isMatch(chars, start, start, len) > 0 ||
                getQuoteMatcher().isMatch(chars, start, start, len) > 0) {
                break;
            }
            start += removeLen;
        }
        
        // handle reaching end
        if (start >= len) {
            addToken(tokens, "");
            return -1;
        }
        
        // handle empty token
        int delimLen = getDelimiterMatcher().isMatch(chars, start, start, len);
        if (delimLen > 0) {
            addToken(tokens, "");
            return start + delimLen;
        }
        
        // handle found token
        int quoteLen = getQuoteMatcher().isMatch(chars, start, start, len);
        if (quoteLen > 0) {
            return readWithQuotes(chars, start + quoteLen, len, workArea, tokens, start, quoteLen);
        }
        return readWithQuotes(chars, start, len, workArea, tokens, 0, 0);
    }

    private int readWithQuotes(char[] chars, int start, int len, StrBuilder workArea, 
                               List tokens, int quoteStart, int quoteLen) 
    {
        // Loop until we've found the end of the quoted
        // string or the end of the input
        workArea.clear();
        int pos = start;
        boolean quoting = (quoteLen > 0);
        int trimStart = 0;
        
        while (pos < len) {
            // quoting mode can occur several times throughout a string
            // we must switch between quoting and non-quoting until we
            // encounter a non-quoted delimiter, or end of string
            if (quoting) {
                // In quoting mode
                
                // If we've found a quote character, see if it's
                // followed by a second quote.  If so, then we need
                // to actually put the quote character into the token
                // rather than end the token.
                if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
                    if (isQuote(chars, pos + quoteLen, len, quoteStart, quoteLen)) {
                        // matched pair of quotes, thus an escaped quote
                        workArea.append(chars, pos, quoteLen);
                        pos += (quoteLen * 2);
                        trimStart = workArea.size();
                        continue;
                    }
                    
                    // end of quoting
                    quoting = false;
                    pos += quoteLen;
                    continue;
                }
                
                // copy regular character from inside quotes
                workArea.append(chars[pos++]);
                trimStart = workArea.size();
                
            } else {
                // Not in quoting mode
                
                // check for delimiter, and thus end of token
                int delimLen = getDelimiterMatcher().isMatch(chars, pos, start, len);
                if (delimLen > 0) {
                    // return condition when end of token found
                    addToken(tokens, workArea.substring(0, trimStart));
                    return pos + delimLen;
                }
                
                // check for quote, and thus back into quoting mode
                if (quoteLen > 0) {
                    if (isQuote(chars, pos, len, quoteStart, quoteLen)) {
                        quoting = true;
                        pos += quoteLen;
                        continue;
                    }
                }
                
                // check for ignored (outside quotes), and ignore
                int ignoredLen = getIgnoredMatcher().isMatch(chars, pos, start, len);
                if (ignoredLen > 0) {
                    pos += ignoredLen;
                    continue;
                }
                
                // check for trimmed character
                // don't yet know if its at the end, so copy to workArea
                // use trimStart to keep track of trim at the end
                int trimmedLen = getTrimmerMatcher().isMatch(chars, pos, start, len);
                if (trimmedLen > 0) {
                    workArea.append(chars, pos, trimmedLen);
                    pos += trimmedLen;
                    continue;
                }
                
                // copy regular character from outside quotes
                workArea.append(chars[pos++]);
                trimStart = workArea.size();
            }
        }
        
        // return condition when end of string found
        addToken(tokens, workArea.substring(0, trimStart));
        return -1;
    }

    private boolean isQuote(char[] chars, int pos, int len, int quoteStart, int quoteLen) {
        for (int i = 0; i < quoteLen; i++) {
            if ((pos + i) >= len || chars[pos + i] != chars[quoteStart + i]) {
                return false;
            }
        }
        return true;
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher delim) {
        if (delim == null) {
            this.delimMatcher = StrMatcher.noneMatcher();
        } else {
            this.delimMatcher = delim;
        }
        return this;
    }

    public StrTokenizer setDelimiterChar(char delim) {
        return setDelimiterMatcher(StrMatcher.charMatcher(delim));
    }

    public StrTokenizer setDelimiterString(String delim) {
        return setDelimiterMatcher(StrMatcher.stringMatcher(delim));
    }

    public StrMatcher getQuoteMatcher() {
        return quoteMatcher;
    }

    public StrTokenizer setQuoteMatcher(StrMatcher quote) {
        if (quote != null) {
            this.quoteMatcher = quote;
        }
        return this;
    }

    public StrTokenizer setQuoteChar(char quote) {
        return setQuoteMatcher(StrMatcher.charMatcher(quote));
    }

    public StrMatcher getIgnoredMatcher() {
        return ignoredMatcher;
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher ignored) {
        if (ignored != null) {
            this.ignoredMatcher = ignored;
        }
        return this;
    }

    public StrTokenizer setIgnoredChar(char ignored) {
        return setIgnoredMatcher(StrMatcher.charMatcher(ignored));
    }

    public StrMatcher getTrimmerMatcher() {
        return trimmerMatcher;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher trimmer) {
        if (trimmer != null) {
            this.trimmerMatcher = trimmer;
        }
        return this;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    public boolean isIgnoreEmptyTokens() {
        return ignoreEmptyTokens;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens) {
        this.ignoreEmptyTokens = ignoreEmptyTokens;
        return this;
    }

    public String getContent() {
        if (chars == null) {
            return null;
        }
        return new String(chars);
    }

    public Object clone() {
        try {
            return cloneReset();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    Object cloneReset() throws CloneNotSupportedException {
        // this method exists to enable 100% test coverage
        StrTokenizer cloned = (StrTokenizer) super.clone();
        if (cloned.chars != null) {
            cloned.chars = (char[]) cloned.chars.clone();
        }
        cloned.reset();
        return cloned;
    }

    public String toString() {
        if (tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        return "StrTokenizer" + getTokenList();
    }

}
