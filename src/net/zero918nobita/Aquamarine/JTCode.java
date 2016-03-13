package net.zero918nobita.Aquamarine;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public abstract class JTCode {
    public JTCode run() throws Exception {
        return this;
    }

    public JTCode add(JTCode code) throws Exception {
        throw new Exception("このオブジェクトに演算子'＋'は使えません");
    }

    public JTCode sub(JTCode code) throws Exception {
        throw new Exception("このオブジェクトに演算子'－'は使えません");
    }

    public JTCode multiply(JTCode code) throws Exception {
        throw new Exception("このオブジェクトに演算子'＊'は使えません");
    }

    public JTCode divide(JTCode code) throws Exception {
        throw new Exception("このオブジェクトに演算子'／'は使えません");
    }
}
