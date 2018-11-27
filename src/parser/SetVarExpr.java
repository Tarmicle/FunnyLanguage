package parser;

import java.util.ArrayList;

public class SetVarExpr extends Expr {
    private String id;
    private Expr assignement;

    public SetVarExpr(String id, Expr assignement) {
        this.id = id;
        this.assignement = assignement;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        Val evalResult = assignement.eval(env);
        return env.add(id, evalResult);
    }
}
