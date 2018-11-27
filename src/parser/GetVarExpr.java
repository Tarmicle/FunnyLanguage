package parser;

public class GetVarExpr extends Expr {
    private String variable;

    public GetVarExpr(String value) {
        variable = value;
    }

    @Override
    public Val eval(Env env) {
        return null;
    }
}
