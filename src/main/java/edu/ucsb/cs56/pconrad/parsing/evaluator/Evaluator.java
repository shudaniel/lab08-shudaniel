package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

public class Evaluator {

    public static int evaluate(final int left,
                               final Operator operator,
                               final int right)
    throws EvaluatorException {
        if (operator instanceof Plus) {
            return left + right;
        } else if (operator instanceof Minus) {
            return left - right;
        } else if (operator instanceof Times) {
            return left * right;
        } else if (operator instanceof Div) {
            if (right == 0) {
                throw new EvaluatorException("Division by zero");
            } else {
                return left / right;
            }
        } else if (operator instanceof Comparator){
            if(evaluateComparator(left, (Comparator) operator,right)){
                return 1;
            }
            else{
                return 0;
            }
        } else if (operator instanceof Exponent){
            if(left == 0 && right == 0){
                throw new EvaluatorException("0**0 is undefined");
            }

            return (int)Math.pow(left,right);
        } else {
            throw new EvaluatorException("Unknown Operator Type");
        }
    } // evaluate

    public static int evaluate(final AST expression)
    throws EvaluatorException {
        if (expression instanceof Literal) {
            return ((Literal)expression).getValue();
        } else if (expression instanceof Binop) {
            final Binop binop = (Binop)expression;
            return evaluate(evaluate(binop.getLeft()),
                            binop.getOperator(),
                            evaluate(binop.getRight()));
        } else if (expression instanceof UnaryMinus) {
            return -evaluate(((UnaryMinus)expression).getNested());
        } else {
            throw new EvaluatorException("Unknown Expression Type");
        }
    } // evaluate

    public static boolean evaluateComparator(final int left, final Comparator comparator, final int right){
        if(comparator instanceof LessThan){
            return left < right;
        } else if (comparator instanceof LessThanEqualTo){
            return left <= right;
        } else if (comparator instanceof GreaterThan){
            return left > right;
        } else if (comparator instanceof GreaterThanEqualTo){
            return left >= right;
        } else if (comparator instanceof Equals){
            return left == right;
        } else {
            return left != right;
        }
    }
}
