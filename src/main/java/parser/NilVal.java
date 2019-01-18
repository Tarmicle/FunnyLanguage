package parser;

public class NilVal extends Val {
    static NilVal nilVal = new NilVal();

    public static Val instance() {
        return nilVal;
    }

    private NilVal() {
    }
}
