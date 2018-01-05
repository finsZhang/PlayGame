package org.zfz.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/5/26
 * Time: 15:23
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class RotateImage {
    private static int[] yzmColor = {255, 253, 240};

    //旋转图片
    public static BufferedImage getCorrectImage(BufferedImage playImage) throws IOException {
        int w = playImage.getWidth();
        int h = playImage.getHeight();
        double angel = getRotateAngel(playImage);

        AffineTransform affineTransform = new AffineTransform();
        double sin = Math.abs(Math.sin(angel)), cos = Math.abs(Math.cos(angel));

        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        affineTransform.setToRotation(angel, w / 2, h / 2);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, null);

        Graphics2D dg = rotatedImage.createGraphics();
        Color color = new Color(yzmColor[0], yzmColor[1], yzmColor[2]);
        dg.setColor(color);
        dg.setBackground(color);
        dg.fillRect(0, 0, rotatedImage.getWidth(), rotatedImage.getHeight());//填充整个屏幕
        dg.dispose();
        affineTransformOp.filter(playImage, rotatedImage);

        return rotatedImage;
    }

    //计算旋转角度
    public static double getRotateAngel(BufferedImage playImage) {
        int maxX = playImage.getWidth();
        int maxY = playImage.getHeight();
        int[] leftPos = new int[2];
        int[] rightPos = new int[2];
        for (int x = 1; x < maxX; x++) {
            for (int y = 1; y < maxY; y++) {
                Color color = new Color(playImage.getRGB(x, y));
                if (!getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(yzmColor[0], yzmColor[1], yzmColor[2]))) {
                    leftPos[0] = x;
                    leftPos[1] = y;
                    x = maxX;
                    break;
                }
            }
        }

        for (int x = maxX - 1; x > 0; x--) {
            for (int y = 1; y < maxY; y++) {
                Color color = new Color(playImage.getRGB(x, y));
                if (!getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(yzmColor[0], yzmColor[1], yzmColor[2]))) {
                    rightPos[0] = x;
                    rightPos[1] = y;
                    x = 0;
                    break;
                }
            }
        }
        boolean isSub = false;
        if (leftPos[1] < rightPos[1]) {//需要顺转
            isSub = true;
            for (int y = maxY - 1; y > 0; y--) {
                for (int x = maxX - 1; x > 0; x--) {
                    Color color = new Color(playImage.getRGB(x, y));
                    if (!getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(yzmColor[0], yzmColor[1], yzmColor[2]))) {
                        rightPos[0] = x;
                        rightPos[1] = y;
                        y = 0;
                        break;
                    }
                }
            }
        }

        int angel = Util.angle(leftPos[0], leftPos[1], rightPos[0], rightPos[1]);
        if (isSub) {
            return Math.toRadians(360 - angel);
        } else {
            return Math.toRadians(angel);
        }
    }

    private static String getRbg(int r, int g, int b) {
        return r + "~" + g + "~" + b;
    }

}