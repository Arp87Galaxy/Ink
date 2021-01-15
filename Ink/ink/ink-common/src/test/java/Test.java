import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author arpgalaxy
 * @date 2021/1/15
 * @email 13605560342@163.com
 * @description
 */
public class Test {
    public static void main(String[] args) {
        String s = "absh_sda_adajf_sd";
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher= Pattern.compile("_([a-z])").matcher(s);
        while (matcher.find())

            matcher.appendReplacement(stringBuffer,matcher.group(1).toUpperCase());
            matcher.appendTail(stringBuffer);
            System.out.println(matcher.group());
    }
}
