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

    @Override
    Val sum(Val augend) throws InterpreterException {
        try {
            return new NumVal(augend.checkNum().getBigDecimal().add(this.val));
        } catch (InterpreterException e) {
            throw new InterpreterException("Cannot sum to a number something different to another number");
        }
    }

    NumVal times(Val arg) {
        //return new NumVal(num.multiply(arg.checkNum().num))
        return null;
    }

    @Override
    NumVal checkNum() throws InterpreterException {
        return this;
    }

    public BigDecimal getBigDecimal() {
        return val;
    }

}
