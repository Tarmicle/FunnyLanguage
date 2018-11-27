package parser;

import java.math.BigDecimal;

public class NumVal extends Expr {
    BigDecimal val;
    public NumVal(BigDecimal bigDecimal) {
        val = bigDecimal;
    }

    NumVal times(Val arg){
        //return new NumVal(num.multiply(arg.checkNum().num))
        return null;
    }

    @Override
    public Val eval(Env env) {
        return null;
    }
}
