package net.zero918nobita.Aquamarine;

public class JTString extends JTCode {
    private String str;

    public JTString(String string) {
        str = string;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public JTCode add(JTCode code) throws Exception {
        if (code.getClass() != JTString.class) {
            throw new Exception("文字列ではありません");
        }
        JTString s = (JTString)code;
        return new JTString(new StringBuilder().append(str).append(s.str).toString());
    }

    @Override
    public JTCode multiply(JTCode code) throws Exception {
        StringBuilder bui = new StringBuilder();
        if (code.getClass() != JTInt.class) {
            throw new Exception("文字列に掛けられるのは整数型のみです");
        }
        JTInt count = (JTInt)code;
        for (int i = 0; i < count.getValue(); i++) {
            bui.append(str);
        }
        return new JTString(bui.toString());
    }
}
