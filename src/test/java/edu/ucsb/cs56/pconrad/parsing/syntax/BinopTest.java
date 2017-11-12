package edu.ucsb.cs56.pconrad.parsing.syntax;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/**
 * Tests the code in the package <code>edu.ucsb.cs56.pconrad.parsing.syntax</code>, including
 * methods of the classes that implement the <code>AST</code> interface.
 *
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.AST
 */

public class BinopTest {

	Binop bo_1_plus_2;
	Binop bo_1_plus_3;
	Binop bo_2_plus_1;
	Binop bo_1_plus_2_again;
	Binop bo_3_times_4;

	@Before public void initialize() {
		bo_1_plus_2 = new Binop( new Literal(1), new Plus(), new Literal(2));
		bo_1_plus_3 = new Binop( new Literal(1), new Plus(), new Literal(3));
		bo_2_plus_1 = new Binop( new Literal(2), new Plus(), new Literal(1));
		bo_1_plus_2_again = new Binop( new Literal(1), new Plus(), new Literal(2));
		bo_3_times_4 = new Binop( new Literal(3), new Times(), new Literal(4));		
	}
	
	@Test
	public void testEqualBinops_deepEqual() {
		assertThat(bo_1_plus_2, is(equalTo(bo_1_plus_2_again)));
	}

	@Test
	public void testEqualBinops_selfEqual() {
		assertThat(bo_1_plus_2, is(equalTo(bo_1_plus_2)));
	}

	
	@Test
    public void testUnequalBinops_1() {
		assertThat(bo_1_plus_2, not(equalTo(bo_3_times_4)));
    }

	@Test
    public void testUnequalBinops_3() {
		assertThat(bo_1_plus_2, not(equalTo(bo_1_plus_3)));
    }

	@Test
    public void testUnequalBinops_4() {
		assertThat(bo_1_plus_2, not(equalTo(bo_2_plus_1)));
    }

	
	@Test
    public void testUnequalBinops_2() {
		assertThat(bo_1_plus_2, not(equalTo(new Literal(3))));
    }

	
	@Test
    public void testBinops_hashCode() {
		Binop bo_1234_plus_5678 = new Binop( new Literal(1234), new Plus(), new Literal(5678));
		assertEquals(bo_1234_plus_5678.hashCode(), 1234 ^ (int)('+') ^ 5678);
    }

	@Test
    public void testBinops_toString_1() {
		assertThat(bo_1_plus_2.toString(), is(equalTo("(1 + 2)")));
    }

	@Test
    public void testBinops_toString_2() {
		Binop bo = new Binop(bo_1_plus_2,new Minus(),bo_3_times_4); 
		assertThat(bo.toString(), is(equalTo("((1 + 2) - (3 * 4))")));
    }

}
