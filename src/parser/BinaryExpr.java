package parser;

import tokenizer.Token;

public class BinaryExpr extends Expr {
    @Override
    void eval() {
        // Left-to-right evaluation
        /*
        Val lval = lexexp.eval(env);
        Val rval = rexexp.eval(env);
        switch(oper){
            case Plus: return lval.plus(rval);
        }
        */
    }
    /*private final Token.TYPE oper;
    private final Expr lexexp;
    private final Expr rexexp;*/
}
