package net.zero918nobita.Aquamarine;

import java.io.Reader;
import java.io.StringReader;
import junit.framework.TestCase;

/**
 * Created by 0918nobita on 2016/03/11.
 */
public class LexerTest extends TestCase {
    public void testToken() {
        Reader reader = new StringReader("1234 5678");
        Lexer lex = new Lexer(reader);
        while (lex.advance()) {
            assertEquals(lex.token(), TokenType.INT);
        }
    }

    public void testValue() {
        Reader reader = new StringReader("1234 5678");
        Lexer lex = new Lexer(reader);
        if (lex.advance()) {
            assertEquals(lex.value(), new Integer(1234));
        } else {
            fail();
        }
        if (lex.advance()) {
            assertEquals(lex.value(), new Integer(5678));
        } else {
            fail();
        }
    }
}
