package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class JTInt extends JTCode {
    private int value; // 数値

    public JTInt(Integer integer) {
        value = integer.intValue();
    }

    public JTInt(int i) {
        value = i;
    }

    public int getValue() { // 記憶している数値を返す
        return value;
    }

    @Override
    public String toString() { // 数値を文字列に変換したものを返す
        return Integer.toString(value);
    }

    @Override
    public JTCode add(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() != JTInt.class) { // codeが数値かチェックする
            throw new Exception("数値以外のものを足そうとしました");
        }
        JTInt i = (JTInt)code;
        result = new JTInt(value + i.getValue()); // 足した値で新しいJTIntのオブジェクトを作成
        return result;
    }

    @Override
    public JTCode sub(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() != JTInt.class) {
            throw new Exception("数値以外のものを引こうとしました");
        }
        JTInt i = (JTInt)code;
        result = new JTInt(value - i.getValue());
        return result;
    }

    @Override
    public JTCode multiply(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() != JTInt.class) {
            throw new Exception("数値外のものを掛けようとしました");
        }
        JTInt i = (JTInt)code;
        result = new JTInt(value * i.getValue());
        return result;
    }

    @Override
    public JTCode divide(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() != JTInt.class) {
            throw new Exception("数値以外のものを割ろうとしました");
        }
        JTInt i = (JTInt)code;
        result = new JTInt(value / i.getValue());
        return result;
    }
}
