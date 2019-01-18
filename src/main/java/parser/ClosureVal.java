package parser;


import java.util.ArrayList;
import java.util.List;

public class ClosureVal extends Val {
    Env env;
    FunExpr funExpr;

    public ClosureVal(Env env, FunExpr funExpr) {
        this.env = env;
        this.funExpr = funExpr;
    }

    @Override
    public Val eval(Env env) {
        return this;
    }

    @Override
    public ClosureVal checkClosure() throws InterpreterException {
        return this;
    }

    // Applico la Closure (ovvero faccio eseguire la funzione)
    Val apply(List<Val> argVals) throws InterpreterException {
        Frame newFrame;
        if (argVals == null) newFrame = new Frame(funExpr.params(), funExpr.locals(), new ArrayList<>());
        else newFrame = new Frame(funExpr.params(), funExpr.locals(), argVals);
        Env environment = new Env(newFrame, env);
        Expr code = funExpr.code();
        try {
            return code.eval(environment);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InterpreterException();
        }
        //return funExpr.code().eval(new Env(new Frame(funExpr.params(), funExpr.locals(), argVals), env));
    }

    @Override
    public String toString() {
        // È più semplice da testare
        return "@ClosureVal";
    }
}

