/**
 * Created by yolo on 2020/1/8.
 */
public class TestMath {
    public static void main(String[] args) {
        int a = (int)(Math.random()*(64-1)+1);
        String str = Integer.toString(a);
        System.out.println(str);
        str = str+"0";
        System.out.println(str);
    }
}
