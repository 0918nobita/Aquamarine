package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class Parser {
    private Lexer lex;
    private int token; // 先読みしたトークン

    /** トークンを先読みする
     */
    private void getToken() {
        if (lex.advance()) {
            token = lex.token();
        } else {
            token = TokenType.EOS; // 次のトークンが存在しない場合は EOS を設定しておく
        }
    }

    public JTCode parse(Lexer lexer) {
        JTCode code = null;
        lex = lexer;
        getToken(); // あらかじめトークンを先読みしておく
        try {
            code = program();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    private JTCode program() throws Exception {
        JTCode code = expr();
        if (code != null) {
            switch (token) {
                case ';': // セミコロン(;)が文の終わりを表す
                    break;
                default:
                    throw new Exception("文法エラーです");
            }
        }
        return code;
    }

    private JTCode expr() throws Exception {
        JTCode code = term();
        switch (token) {
            case '+':
            case '-':
                code = expr2(code);
                break;
        }
        return code;
    }

    private JTBinExpr expr2(JTCode code) throws Exception {
        JTBinExpr result = null;
        while ((token == '+') || (token == '-')) {
            int op = token;
            getToken();
            JTCode code2 = term();
            if (result == null) {
                result = new JTBinExpr(op, code, code2);
            } else {
                result = new JTBinExpr(op, result, code2);
            }
        }
        return result;
    }

    private JTCode term() throws Exception {
        JTCode code = factor();
        switch (token) {
            case '*':
            case '/':
                code = term2(code);
                break;
        }
        return code;
    }

    private JTCode term2(JTCode code) throws Exception {
        JTBinExpr result = null;
        while ((token == '*') || (token == '/')) {
            int op = token;
            getToken();
            JTCode code2 = term();
            if (result == null) {
                result = new JTBinExpr(op, code, code2);
            } else {
                result = new JTBinExpr(op, result, code2);
            }
        }
        return result;
    }

    private JTCode factor() throws Exception {
        JTCode code = null;
        switch (token) {
            case TokenType.EOS:
                break;
            case TokenType.INT:
                code = new JTInt((Integer)lex.value());
                getToken();
                break;
            case TokenType.DOUBLE:
                code = new JTDouble((Double)lex.value());
                getToken();
                break;
            case '-':
                getToken();
                code = new JTMinus(factor());
                break;
            case '(':
                getToken();
                code = expr();
                if (token != ')') throw new Exception("文法エラー: 対応する括弧がありません");
                getToken();
                break;
            case TokenType.SYMBOL:
                JTSymbol sym = (JTSymbol)lex.value();
                getToken();
                if (token == '=') {
                    getToken(); // skip '='
                    code = new JTAssign(sym, expr());
                } else {
                    code = sym;
                }
                break;
            case TokenType.STRING:
                code = new JTString((String)lex.value());
                getToken();
                break;
            case TokenType.TRUE:
                code = JTBool.True;
                getToken();
                break;
            case TokenType.FALSE:
                code = JTBool.False;
                getToken();
                break;
            default:
                throw new Exception("文法エラーです");
        }
        return code;
    }
}
