package parser;

public class WhileExpr extends Expr {
    private Expr condition, thenDo;
    boolean negate;

    public WhileExpr(Expr condition, Expr thenDo, boolean negate) {
        this.condition = condition;
        this.thenDo = thenDo;
        this.negate = negate;
    }

    boolean checkCondition(Env env) throws InterpreterException {
        if (negate) return !(condition.eval(env).checkBool().getBool());
        return condition.eval(env).checkBool().getBool();
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        Val val = null;
        while (checkCondition(env) && thenDo != null)
            val = thenDo.eval(env);
        return val;
    }
}
