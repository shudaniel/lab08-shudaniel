package edu.ucsb.cs56.pconrad.parsing.syntax;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests the code in the package <code>edu.ucsb.cs56.pconrad.parsing.syntax</code>, including
 * methods of the classes that implement the <code>AST</code> interface.
 *
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.AST
 */

public class ASTTest {

	ASTFactory af = DefaultASTFactory.DEFAULT;
	
	@Test
	public void testEqualLiterals() {
		assertThat(af.makeLiteral(3), is(equalTo(af.makeLiteral(3))));
	}
	
	@Test
    public void testUnequalLiterals1() {
		assertThat(af.makeLiteral(3), not(equalTo(af.makeLiteral(4))));
    }

	@Test
    public void testUnequalLiterals2() {
		assertThat(af.makeLiteral(3), not(equalTo(af.makeTimesNode(null,null))));
    }

	
	@Test
    public void testLiteralHashCode() {
		assertThat(af.makeLiteral(5).hashCode(), is(equalTo(5)));
    }

	@Test
    public void testLiteralToString() {
		assertThat(af.makeLiteral(6).toString(), is(equalTo("6")));
    }

	@Test
	public void testEqualOperatorScaffold() {
		assertThat(new Plus(), is(equalTo(new Plus())));
	}
	@Test
	public void testUnequalOperatorScaffold() {
		assertThat(new Plus(), not(equalTo(new Times())));
	}

	@Test
    public void testOperatorEqualsDifferentTypes() {
		assertThat(new Plus(), not(equalTo("stub")));
    }

	@Test
	public void testOperatorScaffoldHashCode() {
		assertThat(new Plus().hashCode(), is(equalTo((int)('+'))));
	}
	
	@Test
    public void testOperatorScaffoldToString() {
		assertThat(new Minus().toString(), is(equalTo("-")));
    }

	@Test
	public void testEqualUnaryMinus() {
		assertThat(af.makeUnaryMinusNode(af.makeLiteral(6)), is(equalTo(af.makeUnaryMinusNode(af.makeLiteral(6)))));
	}

	@Test
	public void testUnequalUnaryMinus1() {
		assertThat(af.makeUnaryMinusNode(af.makeLiteral(6)), not(equalTo(af.makeUnaryMinusNode(af.makeLiteral(7)))));
	}

	@Test
	public void testUnequalUnaryMinus2() {
		assertThat(af.makeUnaryMinusNode(af.makeLiteral(6)), not(equalTo(new Minus())));
	}
	
	@Test
    public void testUnaryMinusHashCode() {
		assertEquals(af.makeUnaryMinusNode(af.makeLiteral(5)).hashCode(), -(af.makeLiteral(5).hashCode()) );
    }

	@Test
    public void testUnaryMinusToString() {
		assertThat(af.makeUnaryMinusNode(af.makeLiteral(5)).toString(), is(equalTo("-5")));
    }


	
}
