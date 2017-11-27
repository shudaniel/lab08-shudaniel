package edu.ucsb.cs56.pconrad.parsing.syntax;

public class Literal implements AST {
    // begin instance variables
    private final int value;
    // end instance variables


    public int getValue() {
        return value;
    }

    public Literal(final int value) {
        this.value = value;
    }

    public boolean equals(final Object other) {
        return (other instanceof Literal &&
                ((Literal)other).value == value);
    }

    /**
       hashCode of literal is the value of that literal
    */
    public int hashCode() {
        return value;
    }

    /**
       convert to a String (the string representation of the literal)
    */
    public String toString() {
        return Integer.toString(value);
    }
} // Literal
