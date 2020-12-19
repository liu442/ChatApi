package com.chat.utils.systemUtil;




import java.text.*;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * uuid生成工具类，主要用一些数据表主键生成
 *
 * @author
 * @date 2019/11/06.
 */
public class UUIDUtils {

    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("000000");
    /**
     * 序列
     */
    private static int seq = 0;

    /**
     * 最大值
     */
    private static final int MAX = 999999;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getStringRandom(8));
    }
    /**
     * 获取一个长度为32的uuid
     *
     * @return
     */
    public static synchronized String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 获取一个uuid
     *
     * @return
     */
    public static synchronized String getToken() {
        return UUID.randomUUID().toString();
    }


    /**
     * 生成序列编号
     * @return
     */
    public static synchronized String getSequenceNo() {
        Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        return sb.toString();
    }



    /**
     * 获取一个长度为16的uuid
     *
     * @return
     */
    public static synchronized String getUUID16() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    //生成随机用户名，数字和字母组成,
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val.toLowerCase();
    }
}
