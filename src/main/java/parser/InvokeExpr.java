package parser;

public class InvokeExpr extends Expr {

    private final Expr expr;
    private final ExprList args;

    InvokeExpr(Expr expr, ExprList args) {
        this.expr = expr;
        this.args = args;
    }

    public Val eval(Env env) throws InterpreterException {
        // return expr.eval(env).checkClosure().apply(args.eval(env));
        if (args==null)
            return (expr.eval(env)).checkClosure().apply(null);
        return (expr.eval(env)).checkClosure().apply(args.eval(env));
    }

}
