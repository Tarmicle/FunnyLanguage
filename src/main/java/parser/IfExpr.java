package parser;

public class IfExpr extends Expr {
    Expr condition, thenDo, elseDo;

    public IfExpr(Expr condition, Expr thenDo, Expr elseDo) {
        this.condition = condition;
        this.thenDo = thenDo;
        this.elseDo = elseDo;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        if (condition.eval(env).checkBool().getBool())
            return thenDo.eval(env);
        else if (elseDo != null)
            return elseDo.eval(env);
        return null;
    }
    //condExpr.eval(env).checkBoolean().bool()^invert?thenExpr.eval(env):isLogical
}
