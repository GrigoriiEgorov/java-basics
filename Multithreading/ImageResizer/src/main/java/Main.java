import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static int newWidth = 300;
    private static int amountCPU = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String srcFolder = "/home/user/Desktop/src";
        String dstFolder = "/home/user/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int stepDivide = 0;

        for (int i = 0; i < amountCPU; i++) {
            int lengthArray;

            if (i == (amountCPU - 1)) {
                lengthArray = files.length / amountCPU + files.length % amountCPU;
            } else {
                lengthArray = files.length / amountCPU;
            }

            File[] files1 = new File[lengthArray];

            System.arraycopy(files, stepDivide, files1, 0, files1.length);
            ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
            new Thread(resizer).start();
            stepDivide += files1.length;
        }
    }
}
