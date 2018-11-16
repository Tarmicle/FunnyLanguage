package parser;

import tokenizer.Token;

public class UnaryExpr extends Expr {
    Expr expr;

    public UnaryExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    void eval() {

    }
    //private final Token.TYPE operator;
    //private final Expr expr;
}
