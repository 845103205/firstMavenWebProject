package likou.threadlocaldemo.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 类说明: 图形验证码
 * @author BrandoLv 2021-05-31 15:03
 */
public class GraphicVerificationCode {
    private int width = 120;// 生成验证码图片的宽度
    private int height = 42;// 生成验证码图片的高度
    private String[] fontNames = { "宋体", "楷体", "隶书", "微软雅黑" };
    private Color bgColor = new Color(255, 255, 255);// 定义验证码图片的背景颜色为白色
    private Random random = new Random();
    private String codes = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";//去掉了不易识别的字母, 如:0,O,I,l等
    private String text;// 记录随机字符串

    /**
     * 获取一个随意颜色
     *
     * @return
     */
    private Color randomColor() {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 获取一个随机字体
     *
     * @return
     */
    private Font randomFont() {
        String name = fontNames[random.nextInt(fontNames.length)];
        int style = random.nextInt(4);
        int size = random.nextInt(5) + 24;
        return new Font(name, style, size);
    }

    /**
     * 获取一个随机字符
     *
     * @return
     */
    private char randomChar() {
        return codes.charAt(random.nextInt(codes.length()));
    }

    /**
     * 创建一个空白的BufferedImage对象
     *
     * @return
     */
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(bgColor);// 设置验证码图片的背景颜色
        g2.fillRect(0, 0, width, height);
        return image;
    }

    public BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            g2.setColor(randomColor());
            g2.setFont(randomFont());
            float x = i * width * 1.0f / 4;
            g2.drawString(s, x, height - 15);
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    /**
     * 绘制干扰线
     * @param image
     */
    private void drawLine(BufferedImage image) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < 5; i++) {
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;
            double res = 0;
            while(res < width * 0.5) {
                x1 = random.nextInt(width - 8) + 4;
                y1 = random.nextInt(height - 8) + 4;
                x2 = random.nextInt(width - 8) + 4;
                y2 = random.nextInt(height - 8) + 4;
                res = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
            }
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    public String getText() {
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

}
