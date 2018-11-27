package parser;

import java.util.List;

public class FunExpr extends Expr {
    private final List<String> params;
    private final List<String> locals;
    Expr optSequence;

    public FunExpr(List<String> params, List<String> locals, Expr optSequence) {

        this.params = params;
        this.locals = locals;
        this.optSequence = optSequence;
    }

    // Valutare una funzione non significa eseguire il corpo della funzione ma solo preparare una closure.
    @Override
    public Val eval(Env env) {
        return new ClosureVal(env, this);
    }

    public Expr code() {
        return optSequence;
    }

    public List<String> params() {
        return params;
    }

    public List<String> locals() {
        return locals;
    }
}
