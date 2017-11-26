package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TokenTest {

	public static final TokenFactory tf = DefaultTokenFactory.DEFAULT;

    @Test
    public void testAllDivideTokensAreEqual() {
		assertEquals(tf.makeDivideToken(), tf.makeDivideToken());
    }
		
    @Test
    public void testAllLParenTokensAreEqual() {
		assertEquals(tf.makeLParenToken(), tf.makeLParenToken());
    }
	
    @Test
    public void testAllLParenTokensArentEqualToDivideTokens() {
		assertFalse(tf.makeLParenToken().equals(tf.makeDivideToken()));
    }

    @Test
    public void testErrorTokenNotEqualToNull() {
		assertFalse(tf.makeErrorToken("@").equals(null));
    }

    @Test
    public void testErrorTokenHashCode() {
		assertTrue(tf.makeErrorToken("@").hashCode()=="@".hashCode());
    }
	
    @Test
    public void testDivide_toString() {
		assertEquals("DivideToken",tf.makeDivideToken().toString());
    }

    @Test
    public void testLParen_toString() {
		assertEquals("LParenToken",tf.makeLParenToken().toString());
    }

	@Test
    public void testRParen_toString() {
		assertEquals("RParenToken",tf.makeRParenToken().toString());
    }

	@Test
    public void testPlus_toString() {
		assertEquals("PlusToken",tf.makePlusToken().toString());
    }

	@Test
    public void testMinus_toString() {
		assertEquals("MinusToken",tf.makeMinusToken().toString());
    }

	@Test
    public void testTimes_toString() {
		assertEquals("TimesToken",tf.makeTimesToken().toString());
    }

	@Test
	public void test_DefaultTokenFactory_makeIntToken() {
		assertEquals(tf.makeIntToken("3"),DefaultTokenFactory.DEFAULT.makeIntToken(3));
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
		assertEquals("IntToken(3)",tf.makeIntToken("3").toString());
	}

	@Test
	public void test_IntToken_not_equals_null() {
		assertFalse(tf.makeIntToken("4").equals(null));
	}

	@Test
	public void test_IntToken3_not_equals_IntToken4() {
		assertFalse(tf.makeIntToken("3").equals(tf.makeIntToken("4")));
	}

	@Test
	public void test_IntToken3_equals_IntToken3() {
		assertEquals(tf.makeIntToken("3"),tf.makeIntToken("3"));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test_new_IntToken_badParam() {		
        thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("String passed into IntToken could not be parsed into integer value");
		Token t = tf.makeIntToken("blah");
    }

	
}
