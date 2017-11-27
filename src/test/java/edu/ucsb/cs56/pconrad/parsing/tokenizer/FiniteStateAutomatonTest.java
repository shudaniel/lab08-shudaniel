package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.rules.ExpectedException;


public class FiniteStateAutomatonTest {

    FiniteStateAutomaton fsa;

    @Before
    public void setup() {
        fsa = new FiniteStateAutomaton();
        fsa.addState(0);
        fsa.addTransition(' ',0,0);
        for (char c='0'; c<='2'; c++) {
            fsa.addTransition(c,0,1);
            fsa.addTransition(c,1,1);
        }
        fsa.addTransition('+',0,2);
    }

    @Test
    public void test_toString() {
        String expected =
            "FSA: \n" +
            "\t0: ' '->0, '+'->2, '0'->1, '1'->1, '2'->1\n" +
            "\t1: '0'->1, '1'->1, '2'->1\n" +
            "\t2: \n";
        assertThat(fsa.toString(), is(equalTo(expected)));
    }

    @Test
    public void test_toString_with_tokenmakers() {
        fsa.addState(1,s->new IntToken(s));
        fsa.addState(2,s->new PlusToken());
        String expected =
            "FSA: \n" +
            "\t0: ' '->0, '+'->2, '0'->1, '1'->1, '2'->1\n" +
            "\t1*: '0'->1, '1'->1, '2'->1\n" +
            "\t2*: \n";
        assertThat(fsa.toString(), is(equalTo(expected)));
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_resetToStartWithNoStartState() {
        FiniteStateAutomaton fsa = new FiniteStateAutomaton();
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("FSA instance does not have a start state (state 0) defined.");
        fsa.resetToStart();
    }

    @Test
    public void test_must_call_setInput_before_calling_nextToken() {
        FiniteStateAutomaton fsa = new FiniteStateAutomaton();
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("must call setInput before calling nextToken");
        fsa.nextToken();
    }

    @Test
    public void test_must_have_a_start_state_before_calling_nextToken() {
        FiniteStateAutomaton fsa = new FiniteStateAutomaton();
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("FSA instance does not have a start state (state 0) defined.");
        fsa.setInput(" ");
        fsa.nextToken();
    }


    @Test
    public void test_gettingErrorTokenForEndStateBeingNotAnAcceptingState() {
        fsa.addState(1,s->new IntToken(s));
        fsa.addState(2,s->new PlusToken());
        fsa.addState(3);
        fsa.addTransition('@',0,3);
        fsa.setInput("@");
        Token t = fsa.nextToken();
        assertThat(t,is(instanceOf(ErrorToken.class)));
    }

    @Test
    public void test_gettingErrorTokenForIllegalChar() {
        fsa.setInput("@");
        Token t = fsa.nextToken();
        assertThat(t,is(instanceOf(ErrorToken.class)));
        ErrorToken et = (ErrorToken) t;
        assertThat(et.getValue(),is(equalTo("@")));
    }


}
