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
	
	@Test
	public void testEqualLiterals() {
		assertThat(new Literal(3), is(equalTo(new Literal(3))));
	}
	
	@Test
    public void testUnequalLiterals1() {
		assertThat(new Literal(3), not(equalTo(new Literal(4))));
    }

	@Test
    public void testUnequalLiterals2() {
		assertThat(new Literal(3), not(equalTo(new Times())));
    }

	
	@Test
    public void testLiteralHashCode() {
		assertThat(new Literal(5).hashCode(), is(equalTo(5)));
    }

	@Test
    public void testLiteralToString() {
		assertThat(new Literal(6).toString(), is(equalTo("6")));
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
    public void testOperatorScaffoldHashCode() {
		assertThat(new Plus().hashCode(), is(equalTo((int)('+'))));
    }

	@Test
    public void testOperatorScaffoldToString() {
		assertThat(new Minus().toString(), is(equalTo("-")));
    }

	@Test
	public void testEqualUnaryMinus() {
		assertThat(new UnaryMinus(new Literal(6)), is(equalTo(new UnaryMinus(new Literal(6)))));
	}

	@Test
	public void testUnequalUnaryMinus1() {
		assertThat(new UnaryMinus(new Literal(6)), not(equalTo(new UnaryMinus(new Literal(7)))));
	}

	@Test
	public void testUnequalUnaryMinus2() {
		assertThat(new UnaryMinus(new Literal(6)), not(equalTo(new Minus())));
	}
	
	@Test
    public void testUnaryMinusHashCode() {
		assertEquals(new UnaryMinus(new Literal(5)).hashCode(), -(new Literal(5).hashCode()) );
    }

	@Test
    public void testUnaryMinusToString() {
		assertThat(new UnaryMinus(new Literal(5)).toString(), is(equalTo("-5")));
    }


	
}
