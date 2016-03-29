package net.zero918nobita.Aquamarine;

public class JTBool extends JTCode {
    private boolean p;

    public static JTBool True; // 真を表すオブジェクト
    public static JTBool False; // 偽を表すオブジェクト

    static {
        True = new JTBool(true);
        False = new JTBool(false);
    }

    /** コンストラクタで渡された真偽値を保持する
     */
    private JTBool(boolean b) {
        p = b;
    }

    @Override
    public String toString() {
        return Boolean.toString(p);
    }

    /** 保持している真偽値を返す
     */
    public boolean isTrue() {
        return p;
    }
}
