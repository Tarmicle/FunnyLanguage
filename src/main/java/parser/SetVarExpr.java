package parser;

import tokenizer.Token;

public class SetVarExpr extends Expr {
    private String id;
    private Expr assignement;
    private Token.TYPE operator;

    public SetVarExpr(String id, Expr assignement, Token.TYPE operator) {
        this.id = id;
        this.assignement = assignement;
        this.operator = operator;
    }

    @Override
    public Val eval(Env env) throws InterpreterException {
        if (operator == Token.TYPE.EQUAL) {
            Val evalResult = assignement.eval(env);
            return env.assign(id, evalResult);
        } else if (operator == Token.TYPE.PLUS) {
            Val evalResult = assignement.eval(env);
            Val value = env.getVal(id);
            return env.assign(id, evalResult.sum(value));
        } else if (operator == Token.TYPE.MINUS) {
            Val evalResult = assignement.eval(env);
            Val value = env.getVal(id);
            return env.assign(id, evalResult.checkNum().subtract(value.checkNum()));
        } else if (operator == Token.TYPE.ABSTERISC) {
            Val evalResult = assignement.eval(env);
            Val value = env.getVal(id);
            return env.assign(id, evalResult.checkNum().times(value.checkNum()));
        } else if (operator == Token.TYPE.DIVIDE) {
            Val evalResult = assignement.eval(env);
            Val value = env.getVal(id);
            return env.assign(id, evalResult.checkNum().subtract(value.checkNum()));
        }
        throw new InterpreterException("EQUAL TYPE NOT DEFINED");
    }
}
