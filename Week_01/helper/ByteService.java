package helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Author antaifeng
 * @Version @version $Id: ByteService, v0.1 2020-10-20 8:37 下午 antaifeng Exp $
 * @Description
 */
public class ByteService {

    public void decodeByteClass(String rawFile, String outputFile) {
        try (InputStream inputStream = new FileInputStream(new File(rawFile));
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int ch = 0;
            while (-1 != (ch = inputStream.read())) {
                ch = 255 - ch;
                byteArrayOutputStream.write(ch);
            }

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(byteArrayOutputStream.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
