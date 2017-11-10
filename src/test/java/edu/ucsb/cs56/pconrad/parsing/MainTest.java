package edu.ucsb.cs56.pconrad.parsing;

import edu.ucsb.cs56.pconrad.parsing.Main;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.PrintStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**

 Tests for methods of Main class

 @author P. Conrad
 */ 

public class MainTest {

    @Test public void test_shouldExit_quit() {
		assertEquals(true,Main.shouldExit("quit"));
	}

	@Test public void test_shouldExit_q() {
		assertEquals(true,Main.shouldExit("q"));
	}

	@Test public void test_shouldExit_quit_with_space() {
		assertEquals(true,Main.shouldExit(" quit "));
	}

	@Test public void test_shouldExit_foo() {
		assertEquals(false,Main.shouldExit("foo"));
	}

	@Test public void test_handleInput_q() {
		assertEquals(true,Main.handleInput("q"));
	}

	@Test public void test_handleInput_1() {
		assertEquals(false,Main.handleInput("1"));
	}

	

	@Test public void test_handleInput_div_by_zero() {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream saveOut = System.out;
		System.setOut(new PrintStream(outContent));

		assertEquals(false,Main.handleInput("1/0"));
		assertEquals("Failed to evaluate: Division by zero", outContent.toString().trim());
		System.setOut(saveOut);
	}

	
	public void test_handleInput(boolean expectedReturn,
								 String input,
								 String expectedSystemOut) {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream saveOut = System.out;
		System.setOut(new PrintStream(outContent));

		assertEquals(false,Main.handleInput(input));
		assertEquals(expectedSystemOut, outContent.toString().trim());
		System.setOut(saveOut);
	}

	@Test public void test_handleInput_div_by_0() {
		test_handleInput(false, "1/0", "Failed to evaluate: Division by zero");
	}

	/*	@Test public void test_handleInput_at_sign() {
		assertEquals(false,Main.handleInput("@"));
	}
	*/

	@Test public void test_handleInput_at_sign() {
		test_handleInput(false, "@", "Failed to parse: Expected primary expression; got: ErrorToken(@)");
	}
	
	@Test public void testUselessConstructor() {
		// these tests are fairly useless, but needed to get 100% test coveragey
		Main m = new Main();

	}

	
	/**
	   test the <code>public static void main(String [] args)</code> class
	   of Main with given input for System.in, and return the output from
	   System.out
	 */

	public String testMainWithSystemInAndOut(String system_in, String [] args)
		throws java.io.UnsupportedEncodingException {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayInputStream inContent =
			new ByteArrayInputStream(system_in.getBytes(StandardCharsets.UTF_8.name()));

		PrintStream saveOut = System.out;
		InputStream saveIn = System.in;
		
		System.setOut(new PrintStream(outContent));
		System.setIn(inContent);

		Main.main(args);
		
		String returnValue = outContent.toString();
		System.setOut(saveOut);
		System.setIn(saveIn);		

		return returnValue;
	}
	
	@Test
	public void testMainWith_q() throws java.io.UnsupportedEncodingException  {
		String outContent = testMainWithSystemInAndOut("q\n", new String[0]);
		assertEquals("Enter expressions, or q to quit.", outContent.trim());		
	}
	
	@Test
	public void testMainWith_1_q() throws java.io.UnsupportedEncodingException  {
		String outContent = testMainWithSystemInAndOut("1\nq\n", new String[0]);
		assertEquals("Enter expressions, or q to quit.\n1", outContent.trim());		
	}

	@Test
	public void testMainWith_1_1p1_q() throws java.io.UnsupportedEncodingException  {
		String outContent = testMainWithSystemInAndOut("1\n1+1\nq\n", new String[0]);
		assertEquals("Enter expressions, or q to quit.\n1\n2", outContent.trim());		
	}

	@Test
	public void testMainWith_emptyInput() throws java.io.UnsupportedEncodingException  {
		String outContent = testMainWithSystemInAndOut("", new String[0]);
		assertEquals("Enter expressions, or q to quit.", outContent.trim());		
	}

	
} // EndToEndTest.java

