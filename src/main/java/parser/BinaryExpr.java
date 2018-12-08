package parser;

import tokenizer.Token;

import java.math.BigDecimal;

public class BinaryExpr extends Expr {
    private Expr leftExpr;
    private Expr rightExpr;
    private Token.TYPE operator;

    @Override
    public Val eval(Env env) throws InterpreterException {
        // Left-to-right evaluation
        //TODO: E se fosse una stringa?
        BigDecimal leftDecimal = leftExpr.eval(env).checkNum().getBigDecimal();
        BigDecimal rightDecimal = rightExpr.eval(env).checkNum().getBigDecimal();
        switch (operator) {
            case PLUS:
                return new NumVal(leftDecimal.add(rightDecimal));
            case MINUS:
                return new NumVal(leftDecimal.subtract(rightDecimal));
            case ABSTERISC:
                return new NumVal(leftDecimal.multiply(rightDecimal));
            case MINOR:
                if (rightExpr.eval(env).checkNum().getBigDecimal().compareTo(leftExpr.eval(env).checkNum().getBigDecimal()) > 0)
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
    /*private final Token.TYPE oper;
    private final Expr lexexp;
    private final Expr rexexp;*/
}
