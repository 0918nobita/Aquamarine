package net.zero918nobita.Aquamarine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Created by 0918nobita on 2016/03/12.
 */
public class Aquamarine {
    // 変数を記録しておくHashtable
    public static Hashtable Globals = new Hashtable();

    public static boolean hasSymbol(JTSymbol sym) { // 変数が存在するかどうか
        return Globals.contains(sym);
    }

    public static JTCode getSymbolValue(JTSymbol sym) { // 変数の値を取り出す
        return (JTCode)Globals.get(sym);
    }

    public static void set(JTSymbol sym, JTCode code) { // 変数に値をセットする
        Globals.put(sym, code);
    }

    static void usage() {
        System.out.println("usage: java Aquamarine [source_filename]");
    }

    public static void main(String[] args) {
        boolean interactive = false; // 標準入力から読み込んでいるときはtrue

        if (args.length >= 2) {
            // 引数の数が不正
            usage();
            return;
        }

        try {
            BufferedReader in;

            if (args.length == 0) {
                // 引数がないときには、標準入力から読み込む
                in = new BufferedReader(new InputStreamReader(System.in));
                interactive = true;
            } else {
                // 引数で指定されたファイルから読み込む
                in = new BufferedReader(new FileReader(args[0]));
            }

            long start = System.nanoTime();
            Lexer lex = new Lexer(in);
            Parser parser = new Parser();
            while (true) {
                if (interactive) {
                    // 標準入力から読み込んでいるときはプロンプトを表示
                    System.out.print("Aquamarine: ");
                }

                JTCode code = parser.parse(lex);

                if (code == null) break;
                System.out.println("解析終了: " + code.run().toString());

            }
            long end = System.nanoTime();
            System.out.println("処理時間：" + (end - start) + "ms");
            in.close();
        } catch(FileNotFoundException e) {
            if (args.length > 0) {
                System.out.println("can't open file '" + args[0] + "'");
            } else {
                System.out.println("can't open file");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
