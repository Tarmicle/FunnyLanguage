package parser;

import tokenizer.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BinaryExpr extends Expr {
    private Expr leftExpr;
    private Expr rightExpr;
    private Token.TYPE operator;
    private int SCALE = 100;

    @Override
    public Val eval(Env env) throws InterpreterException {
        // Left-to-right evaluation
        //TODO: E se fosse una stringa?
        BigDecimal leftDecimal;
        BigDecimal rightDecimal;

        switch (operator) {
            case PLUS:
                if (leftExpr.eval(env).isNan())
                    return new StringVal(leftExpr.eval(env).toString() + rightExpr.eval(env).toString());
                return leftExpr.eval(env).sum(rightExpr.eval(env));
            case MINUS:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                return new NumVal(leftDecimal.subtract(rightDecimal));
            case ABSTERISC:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                return new NumVal(leftDecimal.multiply(rightDecimal));
            case PERCENT:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                try {
                    BigDecimal divisionResult = new BigDecimal(leftDecimal.divide(rightDecimal).intValue());
                    return new NumVal(leftDecimal.subtract(divisionResult.multiply(rightDecimal)));
                } catch (ArithmeticException e) {
                    BigDecimal divisionResult = new BigDecimal(leftDecimal.divide(rightDecimal, SCALE, RoundingMode.HALF_DOWN).intValue());
                    return new NumVal(leftDecimal.subtract(divisionResult.multiply(rightDecimal)));
                }
            case DIVIDE:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                try {
                    return new NumVal(leftDecimal.divide(rightDecimal));
                } catch (ArithmeticException e) {
                    return new NumVal(leftDecimal.divide(rightDecimal, 100, RoundingMode.HALF_DOWN));
                }
            case MINOR:
                if (rightExpr.eval(env).checkNum().getBigDecimal().compareTo(leftExpr.eval(env).checkNum().getBigDecimal()) > 0)
                    return new BoolVal(Token.TYPE.TRUE);
                else return new BoolVal(Token.TYPE.FALSE);
            case MAJOR_EQUAL:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                // >= Ritorna -1 se il rightExpr È più piccolo di left Expr
                if (isLeftEqualMajor(leftDecimal, rightDecimal))
                    return new BoolVal(Token.TYPE.TRUE);
                else return new BoolVal(Token.TYPE.FALSE);
            case MAJOR:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                // >= Ritorna -1 se il rightExpr È più piccolo di left Expr
                if (isLeftMajor(leftDecimal, rightDecimal))
                    return new BoolVal(Token.TYPE.TRUE);
                else return new BoolVal(Token.TYPE.FALSE);
            case EQUAL_EQUAL:
                leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
                rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
                // ==
                if (leftDecimal.compareTo(rightDecimal) == 0)
                    return new BoolVal(Token.TYPE.TRUE);
                else return new BoolVal(Token.TYPE.FALSE);
        }

        return null;
    }

    public BinaryExpr(Expr leftExpr, Expr rightExpr, Token.TYPE operator) {
        this.rightExpr = rightExpr;
        this.leftExpr = leftExpr;
        this.operator = operator;
    }

    // >= Ritorna true se
    public static boolean isLeftEqualMajor(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) >= 0;
    }

    public static boolean isLeftMajor(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }

    /*private final Token.TYPE oper;
    private final Expr lexexp;
    private final Expr rexexp;*/
}
