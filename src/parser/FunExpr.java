package parser;

public class FunExpr extends Expr {
    Expr optSequence;
    public FunExpr(Expr optSequence) {
        this.optSequence = optSequence;
    }

    @Override
    public Val eval(Env env) {
        return optSequence.eval(env);
    }
    /*
     *
     * Val eval(Env env){
     *       // Valutare una function vuol dire creare una closure
     *     return new ClosureVal(env, this);
     * }
     * */

}
