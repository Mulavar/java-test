package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 以空格为分隔符切割参数
 * @author djh
 */
public class ParamsSpliter {

    public static String[] split(String string) {
        int start = 0;
        int end = 0;
        boolean escape = false;
        boolean space = false;
        boolean inQuote = false;
        List<String> args = new ArrayList<>();
        while (end < string.length()) {
            if (string.charAt(end) == ' ') {
                if (inQuote) {
                    // do nothing
                } else {
                    // record the args
                    args.add(string.substring(start, end));
                    start = end + 1;
                    space = true;
                }
                escape = false;
                end++;
                continue;
            }
            if (string.charAt(end) == '"') {
                if (escape) {
                    // do nothing
                } else {
                    if (inQuote) {
                        inQuote = false;
                    } else {
                        // start a new arg
                        start = end;
                        inQuote = true;
                    }
                }
                escape = false;
                space = false;
                end++;
                continue;
            }
            if (string.charAt(end) == '\\') {
                escape = true;
                space = false;
                end++;
                continue;
            }
            // [a-z=+]
            if (space) {
                start = end;
                space = false;
            }
            end++;
        }

        if (start < string.length()) {
            args.add(string.substring(start, end));
        }
        return args.toArray(new String[0]);
    }
}
