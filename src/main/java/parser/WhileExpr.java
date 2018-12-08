package parser;

public class WhileExpr extends Expr {
    private Expr condition, thenDo;

    public WhileExpr(Expr condition, Expr thenDo) {
        this.condition = condition;
        this.thenDo = thenDo;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        Val val = null;
        while (condition.eval(env).checkBool().getBool() && thenDo != null)
            val = thenDo.eval(env);
        return val;
    }
}
