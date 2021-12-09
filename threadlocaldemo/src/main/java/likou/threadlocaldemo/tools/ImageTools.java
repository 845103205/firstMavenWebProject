package likou.threadlocaldemo.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 类说明：处理图片分辨率
 * @author 小啊刚 2018-09-14 11:14
 */
public class ImageTools {

    /**全景图最大质量**/
    private static int MAX_QUALITY = 4;

    public static void main(String[] args) throws IOException {
//        File zipFile = new File("C:\\Users\\Administrator\\Desktop\\19test\\imgs.zip");
//        ZipTools.decompressZip(zipFile, "C:\\Users\\Administrator\\Desktop\\19test\\imgs");
        //updateImageResolvingPower("C:\\Users\\Administrator\\Desktop\\19test\\imgs");
        //getPixel("C:\\Users\\Administrator\\Desktop\\krpano-1.19-pr8-win\\imgs.jpg");
        resizeImage("C:\\Users\\Administrator\\Desktop\\19test\\39.jpg", "C:\\Users\\Administrator\\Desktop\\19test\\24.jpg", 1928, 942);
    }


    /**
     * 方法说明: 创建全景图质量,
     * @param imagePath 图片目录, 图片文件名称(不带后缀).
     * @author BrandoLv 2018/9/17 13:30
     */
    public static void createPanoQuality(String imagePath, String imageName, java.util.List uploadFileList) throws Exception {
        try {
            File srcFile = new File(imagePath + "/" + imageName + ".jpg");
            Image srcImg = ImageIO.read(srcFile);

            int width = 1024;
            int height = 512;
            for (int i=1; i<=MAX_QUALITY; i*=2) {
                int curWidth = width * i;
                int curHeight = height * i;
                String resName = imageName + "_" + i + ".jpg";
                BufferedImage buffImg = null;
                buffImg = new BufferedImage(curWidth, curHeight, BufferedImage.TYPE_INT_RGB);
                //使用TYPE_INT_RGB修改的图片会变色
                buffImg.getGraphics().drawImage(srcImg.getScaledInstance(curWidth, curHeight, Image.SCALE_SMOOTH), 0, 0, null);
                ImageIO.write(buffImg, "JPEG", new File(imagePath + "/" + resName));
                uploadFileList.add(resName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法说明：获取图片像素
     * @param filePath 原图片路径
     * @author 小啊刚 2018-09-13 18:20
     */
    public static void getPixel(String filePath) {
        File file = new File(filePath);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth(); // 像素
        int height = bi.getHeight(); // 像素
        System.out.println("width=" + width + ",height=" + height + ".");
    }

    /**
     * 方法说明：自定义图片分辨率
     * @author 小啊刚 2018-09-13 18:20
     * @param srcPath 原图片路径
     * @param desPath 转换大小后图片路径
     * @param width   转换后图片宽度
     * @param height  转换后图片高度
     */
    public static void resizeImage(String srcPath, String desPath, int width, int height) throws IOException {
        File srcFile = new File(srcPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //使用TYPE_INT_RGB修改的图片会变色
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, "JPEG", new File(desPath));
    }

    /**
     * 方法说明：按比例控制图片分辨率
     * @author 小啊刚 2018-09-13 18:20
     * @param srcPath   源图片路径
     * @param desPath   修改大小后图片路径
     * @param scaleSize 图片的修改比例，目标宽度
     */
    public static void resizeImage(String srcPath, String desPath, int scaleSize) throws IOException {
        File srcFile = new File(srcPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(srcFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth(); // 像素
        int height = bi.getHeight(); // 像素
//        float scale=width/scaleSize;
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width / scaleSize, height / scaleSize, BufferedImage.TYPE_INT_RGB);
        //使用TYPE_INT_RGB修改的图片会变色
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(width / scaleSize, height / scaleSize, Image.SCALE_SMOOTH), 0,
                0, null);
        ImageIO.write(buffImg, "JPEG", new File(desPath));
    }

    /**
     * 方法说明: 通过递归得到某一路径下所有的目录及其文件
     * @author 小啊刚 2018/9/14 11:28
     * @param  filePath  文件夹路径
     * @return
     */
    public static ArrayList<String> getFiles(String filePath) {
        ArrayList<String> fileList = new ArrayList();
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
                fileList.add(file.getAbsolutePath());
            } else {
                String picPathStr = file.getAbsolutePath();
                fileList.add(picPathStr);
            }
        }
        return fileList;
    }
}
