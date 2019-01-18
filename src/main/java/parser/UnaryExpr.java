package parser;

import tokenizer.Token;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;

public class UnaryExpr extends Expr {
    private Expr expr;
    private Token.TYPE operator;

    public UnaryExpr(Expr expr, Token.TYPE operator) {
        this.expr = expr;
        this.operator = operator;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        BigDecimal leftDecimal = expr.eval(env).checkNum().getBigDecimal();
        switch (operator) {
            case MINUS:
                return new NumVal(leftDecimal.multiply(new BigDecimal(-1)));
            default:
                // Not supported JET
                throw new InterpreterException();
        }
    }

    //private final Token.TYPE operator;
    //private final Expr expr;
}
