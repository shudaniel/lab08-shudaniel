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
}
