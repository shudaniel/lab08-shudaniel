package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TokenTest {

    @Test
    public void testAllDivideTokensAreEqual() {
		assertEquals(new DivideToken(), new DivideToken());
    }
		
    @Test
    public void testAllLParenTokensAreEqual() {
		assertEquals(new LParenToken(), new LParenToken());
    }
	
    @Test
    public void testAllLParenTokensArentEqualToDivideTokens() {
		assertFalse(new LParenToken().equals(new DivideToken()));
    }

    @Test
    public void testErrorTokenNotEqualToNull() {
		assertFalse(new ErrorToken("@").equals(null));
    }

    @Test
    public void testErrorTokenHashCode() {
		assertTrue(new ErrorToken("@").hashCode()=="@".hashCode());
    }
	
    @Test
    public void testDivide_toString() {
		assertEquals("DivideToken",new DivideToken().toString());
    }

    @Test
    public void testLParen_toString() {
		assertEquals("LParenToken",new LParenToken().toString());
    }

	@Test
    public void testRParen_toString() {
		assertEquals("RParenToken",new RParenToken().toString());
    }

	@Test
    public void testPlus_toString() {
		assertEquals("PlusToken",new PlusToken().toString());
    }

	@Test
    public void testMinus_toString() {
		assertEquals("MinusToken",new MinusToken().toString());
    }

	@Test
    public void testTimes_toString() {
		assertEquals("TimesToken",new TimesToken().toString());
    }

	@Test
	public void test_DefaultTokenFactory_makeIntToken() {
		assertEquals(new IntToken("3"),DefaultTokenFactory.DEFAULT.makeIntToken(3));
	}

	@Test
	public void test_Token_toString() {
		assertEquals("Token",new Token().toString());
	}

	@Test
	public void test_Token_hashCode() {
		assertEquals(new Token().toString().hashCode(),new Token().hashCode());
	}

	@Test
	public void test_Token_notEquals_null() {
		assertFalse(new Token().equals(null));
	}

	@Test
	public void test_IntToken_3() {
		assertEquals("IntToken(3)",new IntToken("3").toString());
	}

	@Test
	public void test_IntToken_not_equals_null() {
		assertFalse(new IntToken("4").equals(null));
	}

	@Test
	public void test_IntToken3_not_equals_IntToken4() {
		assertFalse(new IntToken("3").equals(new IntToken("4")));
	}

	@Test
	public void test_IntToken3_equals_IntToken3() {
		assertEquals(new IntToken("3"),new IntToken("3"));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test_new_IntToken_badParam() {		
        thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("String passed into IntToken could not be parsed into integer value");
		Token t = new IntToken("blah");
    }

	
}
