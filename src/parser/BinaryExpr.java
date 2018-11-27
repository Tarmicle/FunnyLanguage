package parser;

import tokenizer.Token;

public class BinaryExpr extends Expr {
    @Override
    public Val eval(Env env) {
        // Left-to-right evaluation
        /*
        Val lval = lexexp.eval(env);
        Val rval = rexexp.eval(env);
        switch(oper){
            case Plus: return lval.plus(rval);
        }
        */
        return null;
    }

    /*private final Token.TYPE oper;
    private final Expr lexexp;
    private final Expr rexexp;*/
}
