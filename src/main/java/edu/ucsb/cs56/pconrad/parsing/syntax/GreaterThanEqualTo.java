package edu.ucsb.cs56.pconrad.parsing.syntax;

public class GreaterThanEqualTo extends OperatorScaffold implements Comparator {
    // begin constants
    public static final GreaterThanEqualTo GREATER_THAN_EQUAL_TO = new GreaterThanEqualTo();
    // end constants

    public GreaterThanEqualTo() {
        super(">=");
    }
} // GreaterThanEqualTo
