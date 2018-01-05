package org.zfz.model;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.zfz.util.RotateImage;
import org.zfz.util.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/5/26
 * Time: 15:23
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class CheckCode {
    public static String dataPath = Util.getDefaultPath()+"test";
    static {

    }


    private static String getCode(Robot robot, int[]pos) throws IOException, TesseractException {
        Rectangle screenRect = new Rectangle(pos[0], pos[1], pos[2]-pos[0], pos[3]-pos[1]);
        BufferedImage playImage_temp = robot.createScreenCapture(screenRect);

        ImageIO.write(playImage_temp, "jpg", new File(Util.getDefaultPath()+"resCode.jpg"));
        BufferedImage playImage = ImageIO.read( new File(Util.getDefaultPath()+"resCode.jpg"));
        BufferedImage rotatedImage  =  RotateImage.getCorrectImage(playImage);
        ImageIO.write(rotatedImage, "jpg", new File(Util.getDefaultPath()+"destCode.jpg"));

        writeHighQuality(zoomImage(Util.getDefaultPath()+"destCode.jpg",0.3f), Util.getDefaultPath()+"destCodeZoom.jpg");

        BufferedImage zoomImage = ImageIO.read(new File(Util.getDefaultPath()+"destCodeZoom.jpg"));

        return doOCR(zoomImage);
    }

    private static String getSelCode(Robot robot, int[]pos) throws IOException, TesseractException {
        Rectangle screenRect = new Rectangle(pos[0], pos[1], pos[2]-pos[0], pos[3]-pos[1]);
        BufferedImage playImage = robot.createScreenCapture(screenRect);
        ImageIO.write(playImage, "jpg", new File(Util.getDefaultPath()+"selCode.jpg"));
        writeHighQuality(zoomImage(Util.getDefaultPath()+"selCode.jpg",0.5f), Util.getDefaultPath()+"selCodeZoom.jpg");
        BufferedImage zoomImage = ImageIO.read(new File(Util.getDefaultPath()+"selCodeZoom.jpg"));
        return doOCR(zoomImage);
    }
    /**
     * 识别图片
     * */
    private static String doOCR( BufferedImage image ){
        Tesseract instance = new Tesseract();
        //将验证码图片的内容识别为字符串
        instance.setDatapath(dataPath);
        try {
            String result =  instance.doOCR(image);
            if(result != null){
                return result.replaceAll("\n","");
            }
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return null;
    }


    public static void selecCode(Robot robot,int[]yzmArea,int[]selAPos,int[]selBPos,int[]selCPos,int[]selDPos,int[]confirmBtnPos){
        try {
            String checkCode = getCode(robot, yzmArea);
            String selA = getSelCode(robot, selAPos);
            String selB = getSelCode(robot, selBPos);
            String selC = getSelCode(robot, selCPos);
            String selD = getSelCode(robot, selDPos);

            char c;
            int selANum = 0;
            int selBNum = 0;
            int selCNum = 0;
            int selDNum = 0;
            if(checkCode!=null) {
                for (int i = 0; i < checkCode.length(); i++) {
                    c = checkCode.charAt(i);
                    if (selA.indexOf(c) != -1) {
                        selANum++;
                    }
                    if (selB.indexOf(c) != -1) {
                        selBNum++;
                    }
                    if (selC.indexOf(c) != -1) {
                        selCNum++;
                    }
                    if (selD.indexOf(c) != -1) {
                        selDNum++;
                    }
                }
            }
            Map<Integer, int[]> map = new HashMap<>();
            map.put(selANum, selAPos);
            map.put(selBNum, selBPos);
            map.put(selCNum, selCPos);
            map.put(selDNum, selDPos);
            int[] selPos = getSelPos(map);
            robot.mouseMove(selPos[0]+10, selPos[1] +15);
            robot.mousePress(KeyEvent.BUTTON1_MASK);
            robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            robot.delay(1000);

            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(d);
            BufferedImage playImage = robot.createScreenCapture(screenRect);
            Color color = new Color(playImage.getRGB(confirmBtnPos[0], confirmBtnPos[1]));
            if (getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(181, 201, 1))) {
                robot.mouseMove(confirmBtnPos[0], confirmBtnPos[1]);
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

    public static int[] getSelPos(Map<Integer,int[]> map){
        //这里将map.entrySet()转换成list
        java.util.List<Map.Entry<Integer,int[]>> list = new ArrayList<Map.Entry<Integer,int[]>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,int[]>>() {
            //升序排序
            public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
                return o2.getKey()-o1.getKey();
            }
        });
        System.out.println(list.get(0).getKey()+":"+list.get(0).getValue());
        return list.get(0).getValue();

    }

    private static String getRbg(int r,int g,int b){
        return r +"~"+ g +"~"+ b;
    }


    public static BufferedImage zoomImage(String src, float resizeTimes ) {

        BufferedImage result = null;

        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");

            }
            BufferedImage im = ImageIO.read(srcfile);

            /* 原始图像的宽度和高度 */
            int width = im.getWidth();
            int height = im.getHeight();

            //压缩计算

            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);

            /* 新生成结果图片 */
            result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            result.getGraphics().drawImage(
                    im.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);


        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }

        return result;

    }

    public static boolean writeHighQuality(BufferedImage im, String fileFullPath) {
        try {
                /*输出到文件流*/
            FileOutputStream newimage = new FileOutputStream(fileFullPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
                /* 压缩质量 */
            jep.setQuality(0.9f, true);
            encoder.encode(im, jep);
               /*近JPEG编码*/
            newimage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
