package ks.util.matcher;

import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

public class MultipleStringMatcher implements StringMatcher {
    /** The string to match, as a character array. */
    private StringMatcher[] strMatcher;

    /**
     * Constructor that creates a matcher from a String.
     *
     * @param str
     *            the string to match, must not be null
     */
    public MultipleStringMatcher(final String[] str) {
    	strMatcher = new StringMatcher [str.length];
    	for (int i=0; i < str.length; i++) {
    		strMatcher[i] = StringMatcherFactory.INSTANCE.stringMatcher(str[i]);
    	}
    	
    }

    public int isMatch(final char[] buffer, int pos, final int bufferStart, final int bufferEnd) {
    	for (int i = 0; i < strMatcher.length; i++) {
    		int len = strMatcher[i].isMatch(buffer, pos, bufferStart, bufferEnd);
    		if (len > 0) {
    			return len;
    		}
    	}
        return 0;
    }
}