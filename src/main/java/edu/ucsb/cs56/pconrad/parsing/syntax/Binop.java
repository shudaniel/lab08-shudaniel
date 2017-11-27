package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
   AST for a binary operator.   It has a left subtree and a right subtree which are both
   instances of <code>AST</code>,
   and a binary operator at the root that implements <code>Operator</code>.

   @see Operator
   @see AST

 */

public class Binop implements AST {
    // begin instance variables
    private final AST left;
    private final Operator op;
    private final AST right;
    // end instance variables

    /**
       Construct new AST for a binary operator

       @param left left subtree
       @param op binary operator
       @param right right subtree
     */

    public Binop(final AST left,
                 final Operator op,
                 final AST right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public boolean equals(final Object other) {
        if (other instanceof Binop) {
            final Binop otherOp = (Binop)other;
            return (otherOp.op.equals(op) &&
                    otherOp.left.equals(left) &&
                    otherOp.right.equals(right));
        } else {
            return false;
        }
    }

    /**
    	xor of left AST, operator, and right AST
    */

    public int hashCode() {
        return (left.hashCode() ^ op.hashCode() ^ right.hashCode());
    }

    /**
      String representation of expression, with spaces and parens.
      For example: "(3 + 4)" or "((3 + 4) * 5)
     */

    public String toString() {
        return ("(" + left.toString() +
                " " + op.toString() +
                " " + right.toString() +
                ")");
    }

    public AST getLeft() {
        return left;
    }
    public Operator getOperator() {
        return op;
    }
    public AST getRight() {
        return right;
    }

} // Binop
