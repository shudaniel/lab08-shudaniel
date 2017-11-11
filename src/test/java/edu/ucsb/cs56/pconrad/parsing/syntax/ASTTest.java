package edu.ucsb.cs56.pconrad.parsing.syntax;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

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
    public void testUnequalLiterals() {
		assertThat(new Literal(3), not(equalTo(new Literal(4))));
    }

	@Test
    public void testHashCode() {
		assertThat(new Literal(5).hashCode(), is(5));
    }

	@Test
    public void testToString() {
		assertThat(new Literal(6).toString(), is("6"));
    }

	
}
