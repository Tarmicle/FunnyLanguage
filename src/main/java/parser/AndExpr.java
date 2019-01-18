package parser;

import tokenizer.Token;

public class AndExpr extends Expr {
    private Expr leftExpr;
    private Expr rightExpr;


    @Override
    public Val eval(Env env) throws InterpreterException {
        if (!leftExpr.eval(env).checkBool().getBool()) return new BoolVal(Token.TYPE.FALSE);
        else return rightExpr.eval(env);
    }

    public AndExpr(Expr leftExpr, Expr rightExpr) {
        this.rightExpr = rightExpr;
        this.leftExpr = leftExpr;
    }
}
