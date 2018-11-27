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

    public Expr compile() throws IOException, StringNotClosedException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        Expr programm = program();
        if (tokenizer.nextToken().type != Token.TYPE.EOS) {
            System.out.println("Compiler error, ");
            System.out.println("TOKEN: " + token.type);
        }
        return programm;
    }

    public Expr program() throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        FunExpr firstExpression = function(null);
        return new InvokeExpr(firstExpression, null);
    }

    // function ::= "{" optParams optLocals optSequence "}" .
    public FunExpr function(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        token = tokenizer.assertAndNext("{");
        ArrayList<String> params = optParams();
        ArrayList<String> locals = optLocals();
        ArrayList<String> temps = new ArrayList<>(params);

        temps.addAll(locals);

        // DEBUG ONLY
        System.out.print("Temps: ");
        locals.forEach(e -> System.out.print("[" + e + "] "));
        System.out.println();
        System.out.println("#Entering into optSequence");

        return new FunExpr(params, locals, optSequence(new Scope(temps, scope)));
        //return new FunExpr(params, locals, code);
    }


    // optParams ::= ( "(" optIds ")" )? .
    private ArrayList<String> optParams() throws StringNotClosedException, IOException, CommentNotClosedException, TokenizerException, InvalidSymbolException {
        ArrayList<String> ids = new ArrayList<>();
        if (token.value.equals("(")) {
            token = tokenizer.nextToken();
            ids.addAll(optIds());
        }
        return ids;
    }

    // optLocals ::= optIds .
    private ArrayList<String> optLocals() throws StringNotClosedException, IOException, CommentNotClosedException {
        return optIds();
    }

    //optSequence ::= ( "->" sequence )? .
    private Expr optSequence(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        if (token.type == Token.TYPE.LAMBDA) {
            token = tokenizer.nextToken();
            return sequence(scope);
        } else throw new InvalidSymbolException("->", token.value);
    }

    private Expr sequence(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException, TokenizerException {
        SeqExpr seqExpr = new SeqExpr();
        seqExpr.add(optAssignement(scope));
        while (token.type == Token.TYPE.SEMICOLON) {
            token = tokenizer.nextToken();
            if (token.type != Token.TYPE.CURLY_BRACKET_CLOSE)
                seqExpr.add(optAssignement(scope));
        }
        return seqExpr;
    }

    // sequence ::= optAssignment ( ";" optAssignment )* .
    private Expr optAssignement(Scope scope) throws IOException, StringNotClosedException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        return assignement(scope);
    }

    // optIds::= id* .
    private ArrayList<String> optIds() throws StringNotClosedException, IOException, CommentNotClosedException {
        ArrayList<String> ids = new ArrayList<>();
        while (token.type == Token.TYPE.VARIABLE) {
            ids.add((String) token.value);
            System.out.println("Added local id [" + ids.get(ids.size() - 1) + "]");
            token = tokenizer.nextToken();
        }
        return ids;
    }

    //  assignment ::= Id ( "=" | "+=" | "-=" | "*=" | "/=" | "%=" ) assignment
    //	  | logicalOr .
    private Expr assignement(Scope scope) throws IOException, CommentNotClosedException, StringNotClosedException, InvalidSymbolException, TokenizerException {
        //scope.checkIfScope();
        if (token.type == Token.TYPE.VARIABLE) {
            Token tokenbk = token;
            token = tokenizer.nextToken();
            switch (token.type) {
                case EQUAL:
                    token = tokenizer.nextToken();
                    return new SetVarExpr(tokenbk.getStringVal(), assignement(scope));
                default:
                    tokenizer.undoNext();
                    token = tokenbk;
                    break;
            }
        }
        return logicalOr(scope);
    }

    // logicalOr ::= logicalAnd ( "||" logicalOr )? .
    private Expr logicalOr(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {

        Expr expr = logicalAnd(scope);
        return expr;
    }

    // logicalAnd ::= equality ( "&&" logicalAnd )? .
    private Expr logicalAnd(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {

        return equality(scope);
    }

    // equality ::= comparison ( ( "==" | "!=" ) comparison )? .
    private Expr equality(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {

        return comparison(scope);
    }

    // comparison ::= add ( ( "<" | "<=" | ">" | ">=" ) add )? .
    private Expr comparison(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {

        return add(scope);
    }

    // add ::= mult ( ( "+" | "-" ) mult )* .
    private Expr add(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        Expr expr = mult(scope);
        switch (token.type) {
            case PLUS:
                Token.TYPE type = token.type;
                token = tokenizer.nextToken();
                expr = new BinaryExpr(expr, mult(scope), type);
        }
        return expr;
    }

    // mult ::= unary ( ( "*" | "/" | "%" ) unary )* .
    private Expr mult(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {

        return unary(scope);
    }

    // unary ::= ( "+" | "-" | "!" ) unary
    //	| postfix .
    private Expr unary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        System.out.println("unary");
        switch (token.type) {
            case PLUS:
                return unary(scope);
            case MINOR:
                return unary(scope);
            case BANG:
                return unary(scope);
            default:
                return postfix(scope);
        }

    }

    private Expr postfix(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        System.out.println("postfix");
        return primary(scope);
    }

    private Expr primary(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        System.out.println("primary");
        if (token.type == Token.TYPE.PRINT || token.type == Token.TYPE.PRINTLN)
            return print(scope);
        else if (token.type == Token.TYPE.STRING)
            return string(scope);
        else if (token.type == Token.TYPE.NUMBER)
            return num(scope);
        else if (token.type == Token.TYPE.VARIABLE)
            return getId(scope);
        throw new InvalidSymbolException("STRING | NUMBER | VARIABLE", token.type);
    }

    private Expr getId(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        //TODO: Controllare se esiste nello scope
        Expr getVarExpr = new GetVarExpr(token.value.toString());
        token = tokenizer.nextToken();
        return getVarExpr;
    }

    private Expr num(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        NumVal numVal = new NumVal(token.bigDecimal);
        token = tokenizer.nextToken();
        return numVal;
    }

    private StringVal string(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException {
        String rtn = token.value.toString();
        token = tokenizer.nextToken();
        return new StringVal(rtn);
    }

    private PrintExpr print(Scope scope) throws StringNotClosedException, IOException, CommentNotClosedException, InvalidSymbolException, TokenizerException {
        if (token.type == Token.TYPE.PRINT || token.type == Token.TYPE.PRINTLN) {
            // TODO: TENERE PRINT O PRINTLN
            token = tokenizer.nextToken();
            return new PrintExpr(Token.TYPE.PRINT, args(scope));
        } else throw new InvalidSymbolException("print or println", token.value);
    }

    private ExprList args(Scope scope) throws InvalidSymbolException, StringNotClosedException, IOException, CommentNotClosedException, TokenizerException {
        if (token.type == Token.TYPE.ROUND_BRACKET_OPEN) {
            token = tokenizer.nextToken();

            ExprList expressions = new ExprList();
            expressions.add(sequence(scope));
            // SE non vi è una virgola o un'altra espressione...
            while (token.type == Token.TYPE.COMMA) {
                token = tokenizer.nextToken();
                expressions.add(sequence(scope));
            }
            // Consumo la parentesi
            if (token.type == Token.TYPE.ROUND_BRACKET_CLOSE) {
                token = tokenizer.nextToken();
            } else throw new InvalidSymbolException(")", token.value);

            return expressions;
        } else throw new InvalidSymbolException("(", token.value);
    }


/*
    private GetVarExpr getId(Scope scope) throws IOException{
        String id = token().name();
        scope.checkInScope(id);
        next();
        return new GetVarExpr(id);
    }

    // SCOPE: Usato nel set, assignement


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
