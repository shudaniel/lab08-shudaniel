package edu.ucsb.cs56.pconrad.parsing.syntax;

public class GreaterThan extends OperatorScaffold implements Comparator {
    // begin constants
    public static final GreaterThan GREATER_THAN = new GreaterThan();
    // end constants

    public GreaterThan() {
        super(">");
    }
} // GreaterThan
