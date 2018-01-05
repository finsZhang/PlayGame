package org.zfz.util;

import org.zfz.view.ServerView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/5/26
 * Time: 15:23
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class Util {
    //检查偏转角度
    public static int angle(int x1,int y1,int x2,int y2){
        int angle = 0;
        int x=Math.abs(x1-x2);
        int y=Math.abs(y1-y2);
        double z=Math.sqrt(x*x+y*y);
        angle=Math.round((float)(Math.asin(y/z)/Math.PI*180));//最终角度
        return angle;
    }

    public static void loadCfgFile(ServerView serverView,String dir){
        if (dir != null && !dir.equals("")) {
            File file =  new File(dir);
            if(file.exists()) {
                try {
                    FileInputStream fis  = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis , "UTF-8"));
                    String datas;
                    String[] data;
                    int i=0;
                    while ((datas = reader.readLine()) != null) {
                        data = datas.split(",");
                        if(i==0){ //
                            serverView.getCenterXtf().setText(data[0]);
                            serverView.getCenterYtf().setText(data[1]);
                            serverView.getMoveXtf().setText(data[2]);
                            serverView.getMoveYtf().setText(data[3]);
                        }else if(i==1){
                            serverView.getSearchMaxXtf().setText(data[0]);
                            serverView.getSearchMaxYtf().setText(data[1]);
                        }else if(i==2){
                            serverView.getTopLeftXtf().setText(data[0]);
                            serverView.getTopLeftYtf().setText(data[1]);
                            serverView.getTopRightXtf().setText(data[2]);
                            serverView.getTopRightYtf().setText(data[3]);
                        }else if(i==3){
                            serverView.getBottomLeftXtf().setText(data[0]);
                            serverView.getBottomLeftYtf().setText(data[1]);
                            serverView.getBottomRightXtf().setText(data[2]);
                            serverView.getBottomRightYtf().setText(data[3]);
                        }else if(i==4){
                            serverView.getPosTopLeftXtf().setText(data[0]);
                            serverView.getPosTopLeftYtf().setText(data[1]);
                            serverView.getPosBottomRightXtf().setText(data[2]);
                            serverView.getPosBottomRightYtf().setText(data[3]);
                        }else if(i==5){
                            serverView.getMaxLeftPosXtf().setText(data[0]);
                            serverView.getMaxLeftPosYtf().setText(data[1]);
                            serverView.getSearchLeftPosXtf().setText(data[2]);
                        }else if(i==6){
                            serverView.getCzTopLeftXtf().setText(data[0]);
                            serverView.getCzTopLeftYtf().setText(data[1]);
                            serverView.getCzBottomRightXtf().setText(data[2]);
                            serverView.getCzBottomRightYtf().setText(data[3]);
                        }
                        else if(i==7){
                            serverView.getYzmAreaLeftXtf().setText(data[0]);
                            serverView.getYzmAreaLeftYtf().setText(data[1]);
                            serverView.getYzmAreaRightXtf().setText(data[2]);
                            serverView.getYzmAreaRightYtf().setText(data[3]);
                        }else if(i==8){
                            serverView.getSelAPosLeftXtf().setText(data[0]);
                            serverView.getSelAPosLeftYtf().setText(data[1]);
                            serverView.getSelAPosRightXtf().setText(data[2]);
                            serverView.getSelAPosRightYtf().setText(data[3]);
                        }else if(i==9){
                            serverView.getSelBPosLeftXtf().setText(data[0]);
                            serverView.getSelBPosLeftYtf().setText(data[1]);
                            serverView.getSelBPosRightXtf().setText(data[2]);
                            serverView.getSelBPosRightYtf().setText(data[3]);
                        }else if(i==10){
                            serverView.getSelCPosLeftXtf().setText(data[0]);
                            serverView.getSelCPosLeftYtf().setText(data[1]);
                            serverView.getSelCPosRightXtf().setText(data[2]);
                            serverView.getSelCPosRightYtf().setText(data[3]);
                        }else if(i==11){
                            serverView.getSelDPosLeftXtf().setText(data[0]);
                            serverView.getSelDPosLeftYtf().setText(data[1]);
                            serverView.getSelDPosRightXtf().setText(data[2]);
                            serverView.getSelDPosRightYtf().setText(data[3]);
                        }else if(i==12){
                            serverView.getYzmPosXtf().setText(data[0]);
                            serverView.getYzmPosYtf().setText(data[1]);
                        }else if(i==13){
                            serverView.getConfirmBtnPosXtf().setText(data[0]);
                            serverView.getConfirmBtnPosYtf().setText(data[1]);
                        }

                        i++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getDefaultPath(){
       String defaultPath;
       String path =  System.getProperty("java.class.path");
       if(path.indexOf(";")==-1&&path.lastIndexOf("\\")!=-1){
           defaultPath = path.substring(0,path.lastIndexOf("\\")+1);
       }else{
           defaultPath = "C:\\playGameTemp\\";
       }
       return  defaultPath;
    }
}
