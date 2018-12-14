package general;

import org.junit.jupiter.api.Test;
import parser.BinaryExpr;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneralTest {
    @Test
    void testIsLeftEqualMajor() {
        assertTrue(BinaryExpr.isLeftEqualMajor(BigDecimal.ZERO, BigDecimal.ZERO));
    }
    @Test
    void testIsLeftEqualMajor1() {
        assertFalse(BinaryExpr.isLeftEqualMajor(BigDecimal.ZERO, BigDecimal.ONE));
    }
    @Test
    void testIsLeftEqualMajor2() {
        assertTrue(BinaryExpr.isLeftEqualMajor(BigDecimal.ONE, BigDecimal.ZERO));
    }
}
