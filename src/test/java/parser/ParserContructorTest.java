package parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;
import tokenizer.exceptions.CommentNotClosedException;
import tokenizer.exceptions.StringNotClosedException;
import tokenizer.exceptions.TokenizerException;
import tokenizer.exceptions.UnaspectedTokenException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ParserContructorTest {

    Tokenizer mockTokenizer;
    MockTokenizer answers;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void init() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException {
        // Setup streams
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        mockTokenizer = mock(Tokenizer.class);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void aEqual10Test() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.A_EQUAL_10);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("10", outContent.toString());
    }

    @Test
    void closureTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.SIMPLE_ADD_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("3", outContent.toString());
    }

    @Test
    void simpleSubtractTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.SIMPLE_SUBTRACT_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("1", outContent.toString());
    }

    @Test
    void simpleMultiplicationTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.SIMPLE_MULTIPLICATION_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("42", outContent.toString());
    }

    @Test
    void consecutiveMultiplicationTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.CONSEC_MULTIPLICATION_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("70", outContent.toString());
    }

    @Test
    void ifTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.IF_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("hello", outContent.toString());
    }

    @Test
    void ifElseTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.IF_ELSE_CLOSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("world", outContent.toString());
    }

    @Test
    void minorTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.MINOR);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("true", outContent.toString());
    }

    @Test
    void loopTest() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {
        answers = new MockTokenizer(Code.TEST_WHILE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("12345678910", outContent.toString());
    }

    @Test
    void counterMaker() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException, TokenizerException, UnexpectedSymbolException, InterpreterException {

        answers = new MockTokenizer(Code.COUNTER_MAKER_CLOUSURE);
        when(mockTokenizer.assertAndNext(isA(String.class))).thenAnswer(answers.provide_assertAndNext_Answer());
        when(mockTokenizer.nextToken()).thenAnswer(answers.provide_next_Answer());
        doAnswer(answers.provide_undoNext_Answer()).when(mockTokenizer).undoNext();


        /*
        System.setOut(originalOut);
        System.setErr(originalErr);
        Token t;
        t = mockTokenizer.nextToken();
        while (t.getType() != Token.TYPE.EOS) {
            if (t.getType() == Token.TYPE.NUMBER) System.out.print(t.getBigDecimalVal() + " ");
            else System.out.print(t.getStringVal() + " ");

            t = mockTokenizer.nextToken();
        }
        */

        Expr expr = new Parser(mockTokenizer).compile();
        assertEquals(InvokeExpr.class, expr.getClass());
        expr.eval(null);
        assertEquals("200600", outContent.toString());

    }

  /*  @Test
    void testArgs() throws CommentNotClosedException, StringNotClosedException, UnaspectedTokenException, IOException {
        when(mockTokenizer.assertAndNext("{")).thenThrow(new UnaspectedTokenException());
        assertThrows(UnaspectedTokenException.class, () -> new Parser(mockTokenizer).compile());
    }*/

}
