package stringcalc;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import ks.util.StringCalculator;

public class TestStringCalculator {

	@Test
	public void testCalculator() throws Exception {
		 assertEquals("Empty String", StringCalculator.add(""), 0);
		 
		 assertEquals("Simple case", StringCalculator.add("1\n2,3"), 6);
		 
		 assertEquals("Mulitple numbers", StringCalculator.add("1\n2,3,4\n5,6\n7\n8"), 36);
		 
		 assertEquals("One custom delimiter", StringCalculator.add("//;\n1;2"), 3);
		 
		 try {
			 	assertEquals("Negative number", StringCalculator.add("1,-2,3,-4,5"), 0);
			 	Assert.fail( "Negative number: Should have thrown an exception" );
		 } catch (Exception e) {
			 	final String expected = "negatives not allowed: -2,-4";
			 	assertEquals( expected, e.getMessage());
		 }       
		 
		 
		 assertEquals("Ignore > 1000", StringCalculator.add("1,1001,2\n2001,3"), 6);
		
		 assertEquals("Case for 1000", StringCalculator.add("1,1000\n2,3"), 1006);
		 		 
		 assertEquals("Delimiter with length > 1", StringCalculator.add("//***\n1***2***3"), 6);
		 
		 assertEquals("Multiple delimiters", StringCalculator.add("//*|%\n1*2%3"), 6);
		 
		 assertEquals("Multiple delimiters with length > 1", StringCalculator.add("//*#|%!&\n1*#2%!&3"), 6);
		 
		 assertEquals("Multiple delimiters with length > 1 and ignore > 1000", StringCalculator.add("//*#|%!&\n1001*#2%!&3*#4*#5"), 14);

		 try {
			 assertEquals("Multiple delimiters and Negative number", StringCalculator.add("//*#|%!&\n1*#-2%!&3*#-4"), 6);
			 Assert.fail("Multiple delimiters and Negative number: Should have thrown an exception" );
		 } catch (Exception e) {
			 	final String expected = "negatives not allowed: -2,-4";
			 	assertEquals( expected, e.getMessage());
		 }   		 

		 
    
	}

}
