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

    /** 足し算 */
    @Override
    public JTCode add(JTCode code) throws Exception {
        JTCode result;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTInt(value + i.getValue());
            return result;
        } else if (code.getClass() == JTDouble.class) {
            JTDouble dbl = (JTDouble)code;
            result = new JTDouble(value + dbl.getValue());
            return result;
        } else {
            throw new Exception("数値以外のものを足そうとしました");
        }
    }

    /** 引き算 */
    @Override
    public JTCode sub(JTCode code) throws Exception {
        JTCode result;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTInt(value - i.getValue());
            return result;
        } else if (code.getClass() == JTDouble.class) {
            JTDouble dbl = (JTDouble)code;
            result = new JTDouble(value - dbl.getValue());
            return result;
        } else {
            throw new Exception("数値以外のものを引こうとしました");
        }

    }

    /** 掛け算 */
    @Override
    public JTCode multiply(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTInt(value * i.getValue());
            return result;
        } else if (code.getClass() == JTDouble.class) {
            JTDouble dbl = (JTDouble)code;
            result = new JTDouble(value * dbl.getValue());
            return result;
        } else {
            throw new Exception("数値以外のものを掛けようとしました");
        }
    }

    /** 割り算 */
    @Override
    public JTCode divide(JTCode code) throws Exception {
        JTCode result = null;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTInt(value / i.getValue());
            return result;
        } else if (code.getClass() == JTDouble.class) {
            JTDouble dbl = (JTDouble)code;
            result = new JTDouble(value / dbl.getValue());
            return result;
        } else {
            throw new Exception("数値以外のものを割ろうとしました");
        }
    }
}
