package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.ArrayList;

/**
	Implements a finite state automaton (also known as a finite state machine)

	@author Phill Conrad
*/

public class FiniteStateAutomaton {

    private String input = null;
    private int pos = 0;
    private State currState = null;
    private State nextState = null;
    private State startState = null;
    private String accumulatedToken = "";

    private TreeSet<Character> legalChars = new TreeSet<Character>();

    /**
       Provide the input to the machine
       @param input the input that should be tokenized
    */
    public void setInput(String input) {
        this.input = input;
        this.pos = 0;
        resetToStart();
    }

    /**
       Get the remaining input that has not yet been consumed
       @return the remaining input that has not yet been consumed by the FSA
    */

    public String getRemainingInput() {
        if (this.pos >= input.length()) {
            return "";
        }
        return input.substring(pos);
    }

    private class State {
        private int num;
        private TokenMaker tm=null;
        private TreeMap<Character,State> nextState =
            new TreeMap<Character,State>();

        public State(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            String retVal = "";
            for(Map.Entry<Character,State> entry : nextState.entrySet()) {
                if (!retVal.equals(""))
                    retVal += ", ";
                char c = entry.getKey();
                State s = entry.getValue();
                retVal += "'" + c + "'->" + s.num;
            }

            return "" + this.num + (this.tm==null?": ":"*: ") + retVal;
        }


    } // inner class State

    private TreeMap<Integer,State> states =
        new TreeMap<Integer,State>();

    private State getState(int num) {
        State s = states.get(num);
        if (s==null) {
            s = new State(num);
            states.put(num,s);
        }
        return s;
    }

    public void addState(int num) {
        State s = getState(num);
    }

    public void addState(int num, TokenMaker tm) {
        State s = getState(num);
        s.tm = tm;
    }

    public void addTransition(char c, int from, int to) {
        State fromS = getState(from);
        State toS = getState(to);
        fromS.nextState.put(c,toS);
        legalChars.add(c);
    }

    @Override
    public String toString() {
        String retVal = "";
        retVal += "FSA: \n";
        for(Map.Entry<Integer,State> entry : states.entrySet()) {
            int num = entry.getKey();
            State s = entry.getValue();
            retVal += "\t"+ s.toString() + "\n";
        }
        return retVal;
    }


    public void resetToStart() {
        this.startState = states.get(0);
        this.currState = startState;
        this.accumulatedToken = "";
        if (this.currState==null) {
            throw new IllegalStateException("FSA instance does not have a start state (state 0) defined.");
        }
    }

    private char currChar() {
        return this.input.charAt(pos);
    }

    private Token consumeCurrCharAndEmitErrorToken() {
        // No transition for current character.
        // Emit error token, consuming current character
        String s = "" + currChar();
        Token t = new ErrorToken(s);
        this.pos++;
        resetToStart();
        return t;
    }

    private void processStates() {

        // Keep moving through states, building up the string representing the token
        // Until there is no transition you can make

        while (this.nextState != null) {
            this.accumulatedToken += currChar();
            this.currState = this.nextState; // advance the state
            this.pos++;                      // consume the character

            if ( this.pos >= this.input.length() ) {
                // If we've reached the end, don't look at the character
                // There is no transition we can make
                this.nextState = null;
            } else {
                this.nextState = this.currState.nextState.get(currChar());
            }
        }
    }

    private Token tokenForCurrentState() {
        Token t = currState.tm.makeToken(accumulatedToken.trim());
        resetToStart();
        return t;
    }

    private Token errorTokenForAccumulatedInput() {
        Token t = new ErrorToken(accumulatedToken.trim());
        resetToStart();
        return t;
    }

    private Token errorTokenForIllegalChar() {
        Token t = new ErrorToken("" + currChar());
        pos ++;
        resetToStart();
        return t;
    }

    public Token nextToken() {

        if (this.input == null)
            throw new IllegalStateException("must call setInput before calling nextToken");

        if (this.getRemainingInput().equals(""))
            return null;

        this.nextState = this.currState.nextState.get(currChar());

        if (this.nextState == null)
            return this.consumeCurrCharAndEmitErrorToken();

        // Otherwise, we have a transition for this character.
        // As long as we continue to have a next state,
        // add this character to the token, and go to that state

        this.processStates();

        // if this is an accepting state, emit the token
        if (currState.tm != null)
            return tokenForCurrentState();

        // if we have accumulated something non blank, emit an error token
        if (!accumulatedToken.trim().equals(""))
            return errorTokenForAccumulatedInput();

        // if we've encountered an illegal character (thus it wasn't added
        // to the accumulated token)

        if (  this.pos < input.length() ) {
            // assert( !legalChars.contains(currChar()));
            return errorTokenForIllegalChar();
        }

        resetToStart();
        return null;
    }
}
