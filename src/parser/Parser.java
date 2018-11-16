package parser;

import tokenizer.*;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    Tokenizer tokenizer;
    Token token;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void compile() throws IOException, StringNotClosedException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        program();
        if (tokenizer.nextToken().type != Token.TYPE.EOS) {
            System.out.println("Compiler error");
        }
    }

    public void program() throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        FunExpr fun = function(null);
    }

    public FunExpr function(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        token = tokenizer.assertAndNext("{");
        ArrayList<String> params = optParams();
        ArrayList<String> locals = optLocals();
        ArrayList<String> temps = new ArrayList<>(params);

        temps.addAll(locals);
        optSequence(new Scope(temps, scope));
        //return new FunExpr(params, locals, code);
        return null;
    }

    private ArrayList<String> optParams() throws StringNotClosedException, IOException, CommentNotClosedException, TokenizerException, InvalidSymbolException {
        ArrayList<String> ids = new ArrayList<>();
        if (token.value.equals("(")) {
            token = tokenizer.nextToken();
            ids.addAll(optIds());
        }
        return ids;
    }

    private ArrayList<String> optLocals() throws StringNotClosedException, IOException, CommentNotClosedException {
        return optIds();
    }

    private void optSequence(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        if (token.type == Token.TYPE.LAMBDA) {
            token = tokenizer.nextToken();
            sequence(scope);
        } else throw new InvalidSymbolException("->", token.value);
    }

    private Expr sequence(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException {
        return optAssignement(scope);
    }

    private Expr optAssignement(Scope scope) throws IOException, StringNotClosedException, CommentNotClosedException, InvalidSymbolException {
        return assignement(scope);
    }

    private ArrayList<String> optIds() throws StringNotClosedException, IOException, CommentNotClosedException {
        ArrayList<String> ids = new ArrayList<>();
        while (token.type == Token.TYPE.VARIABLE) {
            ids.add((String) token.value);
            token = tokenizer.nextToken();
        }
        return ids;
    }


    private Expr assignement(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException {
        //scope.checkIfScope();
        return logicalOr(scope);
    }

    private Expr logicalOr(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return logicalAnd(scope);
    }

    private Expr logicalAnd(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return equality(scope);
    }

    private Expr equality(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return comparison(scope);
    }

    private Expr comparison(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return add(scope);
    }

    private Expr add(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return unary(scope);
    }

    private Expr unary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return postfix(scope);
    }

    private Expr postfix(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        return primary(scope);
    }

    private Expr primary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        if (token.type == Token.TYPE.PRINT || token.type == Token.TYPE.PRINTLN)
            return print(scope);
        else if (token.type == Token.TYPE.STRING)
            return string(scope);
        throw new InvalidSymbolException("TYPE", token.value);
    }

    private StringVal string(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        String rtn = token.value.toString();
        token = tokenizer.nextToken();
        return new StringVal(rtn);
    }

    private PrintExpr print(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException {
        if (token.type == Token.TYPE.PRINT || token.type == Token.TYPE.PRINTLN) {
            token = tokenizer.nextToken();
            return new PrintExpr(Token.TYPE.PRINT, args(scope));
        } else throw new InvalidSymbolException("print or println", token.value);
    }

    private Expr args(Scope scope) throws InvalidSymbolException, StringNotClosedException, IOException, CommentNotClosedException {
        if (token.type == Token.TYPE.ROUND_BRACKET_OPEN) {
            token = tokenizer.nextToken();

            Expr expr =  sequence(scope);
            // SE non vi è una virgola o un'altra espressione...

            // Consumo la parentesi
            token = tokenizer.nextToken();
            return expr;
        } else throw new InvalidSymbolException("(", token.value);
    }


/*
    private GetVarExpr getId(Scope scope) throws IOException{
        String id = token().name();
        scope.checkInScope(id);
        next();
        return new GetVarExpr(id);
    }



    private Expr equality(Scope scope) throws IOException{
        Expr expr = comparison(scope);
        if(!isEqualityOp()){
            return expr;
        }
        Type oper = type();
        next();
        return new BinaryExpr(oprt, expr, comparison(scope));
    }*/
}
