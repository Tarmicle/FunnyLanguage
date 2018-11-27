package parser;

public class InvokeExpr extends Expr {

    private final FunExpr expr;
    private final ExprList args;

    InvokeExpr(FunExpr expr, ExprList args) {
        this.expr = expr;
        this.args = args;
    }

    public Val eval(Env env) throws InterpreterException {
       // return expr.eval(env).checkClosure().apply(args.eval(env));
        return ((ClosureVal)expr.eval(env)).apply(null);
    }

}
