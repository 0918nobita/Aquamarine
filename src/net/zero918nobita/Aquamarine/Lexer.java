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

    /** 次のトークンに進む
     * @return 次のトークンがあればtrue、なければfalse
     */
    public boolean advance() {
        try {
            int c = reader.read();
            if (c < 0) return false;
            if (Character.isDigit((char)c)) {
                // 数字が続く限り読み込む
            } else {
                throw new Exception("数字ではありません");
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
