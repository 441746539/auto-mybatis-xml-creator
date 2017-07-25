package my.framework.util;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class StringBufferUtil {

    public static void changeLine(StringBuffer sb){
        sb.append(System.getProperty("line.separator"));
    }
}
