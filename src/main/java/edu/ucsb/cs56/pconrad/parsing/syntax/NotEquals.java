package edu.ucsb.cs56.pconrad.parsing.syntax;

public class NotEquals extends OperatorScaffold implements Comparator {
    // begin constants
    public static final NotEquals NOT_EQUALS = new NotEquals();
    // end constants

    public NotEquals() {
        super("!=");
    }
} // NotEquals
