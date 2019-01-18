package parser;

import java.util.ArrayList;
import java.util.List;

public class SeqExpr extends Expr {
    List<Expr> exprList;

    SeqExpr() {
        exprList = new ArrayList<>();
    }
    // args ::= "(" ( sequence ( "," sequence )* )? ")"
    @Override
    public Val eval(Env env) throws InterpreterException {
        Val lastValue = null;
        for (Expr expr : exprList) {
            lastValue = expr.eval(env);
        }
        return lastValue;
    }

    void add(Expr e) {
        exprList.add(e);
    }
}
