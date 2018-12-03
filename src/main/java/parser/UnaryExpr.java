package parser;

public class UnaryExpr extends Expr {
    Expr expr;

    public UnaryExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Val eval(Env env) {
        return null;
    }

    //private final Token.TYPE operator;
    //private final Expr expr;
}
