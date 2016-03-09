package net.zero918nobita.Aquamarine;

import java.io.Reader;

/**
 * Created by 0918nobita on 2016/03/09.
 */
public class Lexer {
    public Reader reader;

    /** readerはトークンの読み込み元
     */
    public Lexer(Reader r) {
        reader = r;
    }
}
