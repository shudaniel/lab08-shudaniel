package edu.ucsb.cs56.pconrad.parsing.syntax;

public class LessThanEqualTo extends OperatorScaffold implements Comparator {
    // begin constants
    public static final LessThanEqualTo LESS_THAN_EQUAL_TO = new LessThanEqualTo();
    // end constants

    public LessThanEqualTo() {
        super("<=");
    }
} // LessThanEqualTo
