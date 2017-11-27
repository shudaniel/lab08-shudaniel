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

public class DefaultASTFactoryTest {

    ASTFactory af = DefaultASTFactory.DEFAULT;

    AST ast_1_plus_2;
    AST ast_1_plus_3;
    AST ast_2_plus_1;
    AST ast_1_plus_2_again;
    AST ast_3_times_4;

    @Before public void initialize() {
        ast_1_plus_2 = af.makePlusNode( af.makeLiteral(1), af.makeLiteral(2));
        ast_1_plus_3 = af.makePlusNode( af.makeLiteral(1), af.makeLiteral(3) );
        ast_2_plus_1 = af.makePlusNode( af.makeLiteral(2), af.makeLiteral(1) );
        ast_1_plus_2_again = af.makePlusNode( af.makeLiteral(1), af.makeLiteral(2));
        ast_3_times_4 = af.makeTimesNode( af.makeLiteral(3), af.makeLiteral(4));
    }

    @Test
    public void testEqualASTs_deepEqual() {
        assertThat(ast_1_plus_2, is(equalTo(ast_1_plus_2_again)));
    }

    @Test
    public void testEqualASTs_selfEqual() {
        assertThat(ast_1_plus_2, is(equalTo(ast_1_plus_2)));
    }


    @Test
    public void testUnequalASTs_1() {
        assertThat(ast_1_plus_2, not(equalTo(ast_3_times_4)));
    }

    @Test
    public void testUnequalASTs_3() {
        assertThat(ast_1_plus_2, not(equalTo(ast_1_plus_3)));
    }

    @Test
    public void testUnequalASTs_4() {
        assertThat(ast_1_plus_2, not(equalTo(ast_2_plus_1)));
    }


    @Test
    public void testUnequalASTs_2() {
        assertThat(ast_1_plus_2, not(equalTo(af.makeLiteral(3))));
    }


    @Test
    public void testASTs_hashCode() {
        AST ast_1234_plus_5678 = af.makePlusNode( af.makeLiteral(1234), af.makeLiteral(5678));
        assertEquals(ast_1234_plus_5678.hashCode(), 1234 ^ (int)('+') ^ 5678);
    }

    @Test
    public void testASTs_toString_1() {
        assertThat(ast_1_plus_2.toString(), is(equalTo("(1 + 2)")));
    }

    @Test
    public void testASTs_toString_2() {
        AST ast = af.makeDivNode(ast_1_plus_2, af.makeMinusNode(ast_1_plus_2_again, ast_3_times_4));
        assertThat(ast.toString(), is(equalTo("((1 + 2) / ((1 + 2) - (3 * 4)))")));
    }

}
