package edu.ucsb.cs56.pconrad.parsing.tokenizer;
import java.util.ArrayList;

/**
   A simple tokenizer based on a Finite State Automaton
   
   @author Phill Conrad
   @see FiniteStateAutomaton
   
*/

public class Tokenizer {
	
	FiniteStateAutomaton fsa;
	
	/**
	   Create a tokenizer that tokenizes the input provided
	   
	   @param input The string to be tokenized

	*/

    public Tokenizer() {
		fsa = makeFSA();
    }
	
    /**
       Creates a specific instance of FiniteStateAutomaton with the
       states and transitions needed to parse the language implemented
       by this arithmetic expression evaluator.
	   
       @return an instance of <code>FiniteStateAutomaton</code> that can be used to tokenize a string
	*/
	
    public static FiniteStateAutomaton makeFSA() {
		
		FiniteStateAutomaton fsa = new FiniteStateAutomaton();
		fsa.addState(0);
		fsa.addState(1, s -> new IntToken(s) );
		fsa.addState(2, s -> new PlusToken() );
		fsa.addState(3, s -> new MinusToken() );
		fsa.addState(4, s -> new TimesToken() );
		fsa.addState(5, s -> new DivideToken() );
		fsa.addState(6, s -> new LParenToken() );
		fsa.addState(7, s -> new RParenToken());
		
		fsa.addTransition(' ',0,0);
		fsa.addTransition('\t',0,0);
		fsa.addTransition('\n',0,0);
		fsa.addTransition('\r',0,0);
		
		for (char c='0'; c<='9'; c++) {
			fsa.addTransition(c,0,1);
			fsa.addTransition(c,1,1);
		}
		fsa.addTransition('+',0,2);
		fsa.addTransition('-',0,3);
		fsa.addTransition('*',0,4);
		fsa.addTransition('/',0,5);
		fsa.addTransition('(',0,6);
		fsa.addTransition(')',0,7);
		
		return fsa;
    }
	
    /**
       Convert the input for this tokenizer into a sequence of tokens
       @return list of tokens
     */
    
    public ArrayList<Token> tokenize (String input) {
		FiniteStateAutomaton fsa = makeFSA();
		fsa.setInput(input);
	    
		ArrayList<Token> tokens = new ArrayList<Token>(); 
		
		Token t = fsa.nextToken();
		while (t != null) {
			tokens.add(t);
			t = fsa.nextToken();
		}
		
		return tokens;
    }
	
    /**
	   
       Convenience method to turn a string into an ArrayList<Token>.
	   
       @param input string
       @return ArrayList of Token instances
    */
    
    public static ArrayList<Token> tokenizeToArrayList (String input) {
		return new Tokenizer().tokenize(input);
    }

    /**
	   
       Convenience method for use in testing that takes input as a string,
       and returns a plain old java array of Token objects
	   
       @param input string
       @return plain old java array of Tokens (i.e. a Token [] instance)
    */
    
    public static Token [] tokenizeToArray (String input) {
		ArrayList<Token> tokens = tokenizeToArrayList(input);
		return tokens.toArray(new Token [tokens.size()]);
    }
	
	/** 
		Return true if <code>-d</code> or <code>-debug</code> is passed as the first 
		command line argument 

		@param args The command line args passed to main
	*/
	
	public static boolean isDebugFlagPassed(String [] args) {
		return (args.length > 0 && (args[0].equals("-d") || args[0].equals("-debug")));
	}
	
	/** main method that can be used for interactive testing of the 
		tokenizer.   Tokenizes hard coded string <code>"2+2"</code> unless
		a command line argument is passed, in which case it parses that.
		
		@param args Command line arguments, where arg[0] is the string to parse, if present
    */
	
    public static void main(String [] args) {

		// FiniteStateAutomaton.debug = true;
		
		String input="2+2"; // default input
		boolean debugFlagPassed = isDebugFlagPassed(args);
		
		if (args.length == 1 && !debugFlagPassed)
			input = args[0]; 

		if (args.length >= 2 && debugFlagPassed)
			input = args[1];

		System.out.println("Use -d or --debug for debugging output from FiniteStateAutomaton class");
		System.out.println("Tokenizing: " + input);
		System.out.println(Tokenizer.tokenizeToArray(input));
    }
    
}
