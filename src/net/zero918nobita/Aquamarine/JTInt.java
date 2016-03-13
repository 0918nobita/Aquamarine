package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class JTInt extends JTCode {
    private int value; // 数値

    public JTInt(Integer integer) {
        value = integer.intValue();
    }

    public int getValue() { // 記憶している数値を返す
        return value;
    }

    @Override
    public String toString() { // 数値を文字列に変換したものを返す
        return Integer.toString(value);
    }
}
