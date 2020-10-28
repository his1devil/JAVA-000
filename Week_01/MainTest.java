import helper.ByteService;
import loader.CustomClassLoader;

/**
 * @Author qiujun
 * @Version @version $Id: MainTest, v0.1 2020-10-20 8:48 下午 qiujun Exp $
 * @Description
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        // 解码
        //ByteService bs = new ByteService();
        //bs.decodeByteClass(
        //    "/Users/xxx/training/JAVA-000/Week_01/Hello.xlass",
        //    "/Users/xxx/training/JAVA-000/Week_01/Hello.class"
        //);

        // invoke helo
        CustomClassLoader loader = new CustomClassLoader();
        loader.call(
            "/Users/xxx/training/JAVA-000/Week_01/",
            "Hello",
            "hello"
        );
    }
}
