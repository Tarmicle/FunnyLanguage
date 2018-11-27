package parser;

import java.math.BigDecimal;

public class NumVal extends Val {
    BigDecimal val;

    public NumVal(BigDecimal bigDecimal) {
        val = bigDecimal;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    NumVal times(Val arg) {
        //return new NumVal(num.multiply(arg.checkNum().num))
        return null;
    }

    NumVal checkNum() throws InterpreterException {
        return this;
    }

    public BigDecimal getBigDecimal() {
        return val;
    }

}
