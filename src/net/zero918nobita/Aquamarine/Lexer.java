package net.zero918nobita.Aquamarine;

import java.io.Reader;

/**
 * Created by 0918nobita on 2016/03/09.
 */
public class Lexer {
    private Reader reader; // トークンの読み込み元
    private int tok; // advance()の処理中にセットされる
    private Object val; // advance()の処理中にセットされる

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
                lexDigit();
                // tok = TokenType.INT;
            } else {
                throw new Exception("数字ではありません");
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** 現在のトークンの種類を返す
     * @return トークンの種類を示す整数値
     */
    public int token() {
        return tok;
    }

    /** 現在のトークンの値を返す
     * @return 現在のトークンの値
     */
    public Object value() {
        return val;
    }

    /** 文字を1文字ずつ読み込んでいって、数字を表す文字が続く限り読み込んでいき、それを数値に変換する
     */
    private void lexDigit() throws Exception {
        int num = 0;
        while (true) {
            int c = reader.read();
            if (c < 0 || !Character.isDigit((char)c)) break;
            num = (num * 10) + (c - '0');
        }
        val = new Integer(num);
    }
}
