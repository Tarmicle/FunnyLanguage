package parser;

public class IfExpr extends Expr {
    @Override
    public Val eval(Env env) throws InterpreterException {
        return null;
    }
    //condExpr.eval(env).checkBoolean().bool()^invert?thenExpr.eval(env):isLogical
}
