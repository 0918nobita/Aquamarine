package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class JTBinExpr extends JTCode {
    private int op; // 演算子の種類(＋,－,＊,／)
    private JTCode code1; // 左側の式
    private JTCode code2; // 右側の式

    public JTBinExpr(int operator, JTCode c1, JTCode c2) {
        op = operator;
        code1 = c1;
        code2 = c2;
    }

    public JTCode run() throws Exception {
        JTCode c1 = code1.run(); // code1を計算する
        JTCode c2 = code2.run(); // code2を計算する
        JTCode result = null;
        switch (op) { // 演算子ごとに処理を振り分ける
            case '+':
                result = c1.add(c2);
                break;
            case '-':
                result = c1.sub(c2);
                break;
            case '*':
                result = c1.multiply(c2);
                break;
            case '/':
                result = c1.devide(c2);
                break;
        }
        return result;
    }
}
