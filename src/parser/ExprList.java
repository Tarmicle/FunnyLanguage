package parser;

import java.util.ArrayList;
import java.util.List;

public class ExprList {
    List<Expr> exprList;

    public ExprList() {
        this.exprList = new ArrayList<>();
    }

    public List<Val> eval(Env env) {
        List<Val> evals = new ArrayList<>();
        for (Expr expr : exprList) {
            evals.add(expr.eval(env));
        }
        return evals;
    }

    void add(Expr expr) {
        exprList.add(expr);
    }
}
