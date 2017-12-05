package edu.ucsb.cs56.pconrad.parsing.syntax;

public class Equals extends OperatorScaffold implements Comparator {
    // begin constants
    public static final Equals EQUALS = new Equals();
    // end constants

    public Equals() {
        super("==");
    }
} // Equals
