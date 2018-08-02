package ks.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;

import ks.util.matcher.MultipleStringMatcher;

public class StringCalculator {
	private final static String LINE_DELIMITER = StringUtils.LF;
	private final static String COMMA =",";
	private final static String CUSTOM_PREFIX = "//";
	private final static String CUSTOM_DELIMITER = "|";
	private final static String[] DEFAULT_DELIMITERS = {LINE_DELIMITER, COMMA}; 
	
	// Check if it has custom delimiter
	private static boolean hasCustomDelimiter(String numbers) {
		return (numbers != null && numbers.startsWith(CUSTOM_PREFIX));
	}
	
	// Get custom delimiter
	private static String[] getDelimiters(String input) {
		String[] retVal = null;
		
		String tmpVal = StringUtils.right(input, input.length()-CUSTOM_PREFIX.length());
		StringTokenizer delST = new StringTokenizer(tmpVal, CUSTOM_DELIMITER);
		retVal = delST.getTokenArray();
		return retVal;
		
	};
	
	// Actual add numbers logic
	private static int add(String numbers, String[] delimiters) throws Exception {
		int retVal = 0;
		StringMatcher sm = new MultipleStringMatcher(delimiters);
		StringTokenizer lineST = new StringTokenizer(numbers, sm);
		String[] numberArray = lineST.getTokenArray();
		List<String> negList = new ArrayList<String>();
		for (int i=0; i < numberArray.length; i++) {
				int val =Integer.parseInt(numberArray[i]);
				
				//Ignore if number > 1000
				if (val <= 1000) {
					//Add to list if number is negative
					if (val < 0) {
						negList.add(numberArray[i]);
					} else {
						retVal += val;
					}	
				}
		}
		if (negList.size() > 0) {
			String tmpMsg = StringUtils.join(negList, ',');
			throw new Exception ("negatives not allowed: " + tmpMsg);
		}		
		return retVal;
	}
	
	// Entry method to add number
	public static int add(String numbers) throws Exception {
		int retVal = 0;
		String tmpString = numbers;
		String[] delimiters = DEFAULT_DELIMITERS;
		if (tmpString != null && !"".equals(tmpString)) {
			//check if there is custom delimiter
			if (hasCustomDelimiter(tmpString)) {
				String[] tmpArray = tmpString.split(LINE_DELIMITER);
				tmpString = tmpArray[1];
				String[] customDelimiters = getDelimiters(tmpArray[0]);
				if (customDelimiters != null) {
					delimiters = customDelimiters;
				}				
			}
			retVal = add(tmpString, delimiters);
		}
		
		return retVal;
	}
	

}
