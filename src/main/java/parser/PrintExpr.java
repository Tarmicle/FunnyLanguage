package parser;

import tokenizer.Token;

import java.util.ArrayList;

public class PrintExpr extends Expr {
    Token.TYPE print;
    ExprList expression;

    public PrintExpr(Token.TYPE print, ExprList expression) {
        this.print = print;
        this.expression = expression;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        ArrayList<Val> toPrintVals = new ArrayList<>(expression.eval(env));
        toPrintVals.forEach(System.out::print);
        if (print == Token.TYPE.PRINTLN) System.out.println();
        return null;
    }


}
