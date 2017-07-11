package gf.util;

/**
 * Created by Lyonnel Dzotang on 11/07/2017.
 */
public class CoreUtil {

    public static String removeSpace(String s) {
        char[] input = s.toCharArray();
        String result = "";
        for (char c : input) {
            if (c != 160)
                result = result + c;
        }
        return result;
    }
}
