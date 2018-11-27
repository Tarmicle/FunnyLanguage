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

    // Applico la Closure (ovvero faccio eseguire la funzione)
    Val apply(List<Val> argVals) throws InterpreterException {
        //TODO: sto barando TANTISSIMO sui parms(1o argomento) e argsval (3o argomento)!
        Frame newFrame = new Frame(new ArrayList<>(), funExpr.locals(), new ArrayList<>());
        Env environment = new Env(newFrame, env);
        return funExpr.code().eval(environment);
        //return funExpr.code().eval(new Env(new Frame(funExpr.params(), funExpr.locals(), argVals), env));
    }
}

