package net.zero918nobita.Aquamarine;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.Hashtable;

/**
 * Created by 0918nobita on 2016/03/09.
 */
public class Lexer {
    private LexerReader reader; // トークンの読み込み元
    private int tok; // advance()の処理中にセットされる
    private Object val; // advance()の処理中にセットされる

    private static Hashtable reserved = new Hashtable(); // 予約語を保持する

    static { // 予約語を登録
        reserved.put("true", new Integer(TokenType.TRUE));
        reserved.put("false", new Integer(TokenType.FALSE));
    }

    public Lexer(Reader r) {
        reader = new LexerReader(r);
    }

    /** 次のトークンに進む
     * @return 次のトークンがあればtrue、なければfalse
     */
    public boolean advance() {
        try {
            skipWhiteSpace();
            int c = reader.read();
            if (c < 0) return false;
            switch (c) {
                case ';':
                case '+':
                case '-':
                case '*':
                case '(':
                case ')':
                case '=':
                    // ;,+,-,*,/ のときには文字コードがそのままトークンの種類を表す
                    tok = c;
                    break;
                case '/':
                    c = reader.read();
                    if (c == '/') {
                        skipLineComment();
                        return advance();
                    } else if (c == '*') {
                        skipComment();
                        return advance();
                    } else {
                        reader.unread();
                        tok = '/';
                    }
                    break;
                case '"':
                    lexString();
                    tok = TokenType.STRING;
                    break;
                default:
                    if (Character.isDigit((char) c)) {
                        reader.unread();
                        lexDigit();
                        if (val.getClass() == Integer.class) {
                            tok = TokenType.INT;
                        }
                        if (val.getClass() == Double.class) {
                            tok = TokenType.DOUBLE;
                        }
                    } else if (Character.isJavaIdentifierStart((char)c)) {
                        reader.unread();
                        lexSymbol();
                    } else {
                        throw new Exception("数字ではありません");
                    }
                    break;
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
        BigDecimal num = new BigDecimal("0");
        boolean point = false; // 小数かどうか
        int decimal_place = 0; // 小数第何位に達しているか
        while (true) {
            int c = reader.read();
            if (c < 0) break;
            if (!Character.isDigit((char)c) && c != '.') { // 数字でも小数点でもないならエラー
                reader.unread();
                break;
            }
            if (c == '.' && point) throw new Exception("文法エラー"); // 2つ以上小数点が存在するのでエラー
            if (c == '.' && !point) point = true; // はじめて小数点が登場したので val に Double を代入するように設定
            if (point && c != '.') {
                decimal_place++;
                num = num.add(new BigDecimal(c-'0').multiply(new BigDecimal("0.1").pow(decimal_place)));
            } else if (c != '.') {
                num = num.multiply(new BigDecimal("10")).add(new BigDecimal(c-'0'));
            }
        }
        if (point) {
            val = new Double(num.doubleValue());
        } else {
            val = new Integer(num.intValue()); // 整数だったのでint型にキャストしてから Integer を代入
        }
    }

    private void lexString() throws Exception {
        StringBuilder buf = new StringBuilder();
        while (true) {
            int c = reader.read();
            if (c < 0) throw new Exception("文字列中でファイルの終わりに到達しました");
            if (c == '"') {
                break;
            } else if (c == '\\') {
                c = reader.read();
                if (c < 0) throw new Exception("文字列中でファイルの終わりに到達しました");
            }
            buf.append((char)c);
        }
        val = buf.toString();
    }

    private void lexSymbol() throws Exception {
        tok = TokenType.SYMBOL;
        StringBuilder buf = new StringBuilder();
        while (true) {
            int c = reader.read();
            if (c < 0) throw new Exception("ファイルの終わりに到達しました");
            if (!Character.isJavaIdentifierPart((char)c)) {
                reader.unread();
                break;
            }
            buf.append((char)c);
        }
        String s = buf.toString();
        val = JTSymbol.intern(s);

        if (reserved.containsKey(s)) {
            tok = ((Integer)reserved.get(s)).intValue();
        }
    }

    /** 空白文字をスキップする
     * @throws Exception
     */
    public void skipWhiteSpace() throws Exception {
        int c = reader.read();
        while ((c != -1) && Character.isWhitespace((char)c)) {
            c = reader.read();
        }
        reader.unread();
    }

    private void skipLineComment() throws Exception {
        int c;
        while ((c = reader.read()) != '\n') {
            if (c < 0) throw new Exception("コメント中にファイルの末端に到達しました");
        }
        reader.unread();
    }

    private void skipComment() throws Exception {
        int c;
        while (true) {
            c = reader.read();
            if (c < 0) throw new Exception("コメント中にファイルの末端に到達しました");
            if (c == '*') {
                c = reader.read();
                if (c == '/') break;
            }
        }
    }
}
