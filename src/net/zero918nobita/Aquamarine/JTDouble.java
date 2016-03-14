package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/13.
 */
public class JTDouble extends JTCode {
    private double value; // 数値

    public JTDouble(Double dbl) {
        value = dbl.doubleValue();
    }

    public JTDouble(double d) {
        value = d;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    /** 足し算 */
    @Override
    public JTCode add(JTCode code) throws Exception {
        JTCode result;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTDouble(value + i.getValue());
            return result;
        } else if (code.getClass() == JTDouble.class) {
            JTDouble dbl = (JTDouble) code;
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
            result = new JTDouble(value - i.getValue());
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
    public JTCode multiply(JTCode code) throws Exception {
        JTCode result;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTDouble(value * i.getValue());
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
    public JTCode divide(JTCode code) throws Exception {
        JTCode result;
        if (code.getClass() == JTInt.class) {
            JTInt i = (JTInt)code;
            result = new JTDouble(value / i.getValue());
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
