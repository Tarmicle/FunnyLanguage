package parser;

import tokenizer.Token;

public class PrintExpr extends Expr {
    Token.TYPE print;
    ExprList expression;

    public PrintExpr(Token.TYPE print, ExprList expression) {
        this.print = print;
        this.expression = expression;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        for (Expr expr : expression.eval(env)) {
            System.out.println(expr);
        }
        return null;
    }


}
