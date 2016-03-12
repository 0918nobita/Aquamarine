package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class JTInt extends JTCode {
    private int value;

    public JTInt(Integer integer) {
        value = integer.intValue();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
