package edu.ucsb.cs56.pconrad.parsing.syntax;

public class LessThan extends OperatorScaffold implements Comparator {
    // begin constants
    public static final LessThan LESS_THAN = new LessThan();
    // end constants

    public LessThan() {
        super("<");
    }
} // LessThan
