package parser;


import java.util.List;

public class ClosureVal extends  Val{
    @Override
    public Val eval(Env env) {
        return null;
    }
    /*private final Env env;
    private final FunExpr funExpr;

    ClosureVal(Env env, FunExpr funExpr){

    }
    //Vuol dire ingrandire l'ambiente
    Val apply(List<Val> argsVal){
        return funeExpr.code()
                .eval(new Env(new Frame(funeExpr.params(),
                        funExpr.locals(), argsVal),env));
    }*/
}
