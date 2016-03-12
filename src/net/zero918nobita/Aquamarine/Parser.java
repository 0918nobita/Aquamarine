package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class Parser {
    private Lexer lex;

    public JTCode parse(Lexer lexer) {
        JTCode code = null;
        lex = lexer;
        try {
            code = program();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public JTCode program() throws Exception {
        return expr();
    }

    public JTCode expr() throws Exception {
        JTCode code = null;
        if (lex.advance()) {
            int token = lex.token();
            switch (token) {
                case TokenType.INT:
                    code = new JTInt((Integer) lex.value());
                    break;
                default:
                    throw new Exception("文法エラー");
            }
        }
        return code;
    }
}
