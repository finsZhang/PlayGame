package org.zfz.controller;

import org.zfz.util.Util;
import org.zfz.view.ServerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/5/26
 * Time: 15:23
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class FileHandler  implements ActionListener {
    private ServerView serverView;
    private JFileChooser jfc = new JFileChooser(Util.getDefaultPath());//默认路径;
    public FileHandler(ServerView serverView) {
        super();
        this.serverView = serverView;
    }

    public void actionPerformed(ActionEvent e) {
        //根据不同的按钮来调用图片处理的公共类
        for (int i = 0; i < serverView.getJmiFile().length; i++)
            if (e.getSource() == serverView.getJmiFile()[i]) {
                if(i==1){
                    loadCfgFile();
                }else{
                    saveCfgFile();
                }
            }
    }

    // 加载配置文件
    public void loadCfgFile() {
        jfc.showOpenDialog(serverView);
        String dir = (jfc.getSelectedFile() != null) ? (jfc.getSelectedFile().getPath()) : null;
        Util.loadCfgFile(serverView,dir);
    }

    // 加载配置文件
    public void saveCfgFile() {
        int option = jfc.showSaveDialog(serverView);
        if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存
            File file = jfc.getSelectedFile();
            try {
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");

                fw.write(serverView.getCenterXtf().getText()+","+serverView.getCenterYtf().getText()+","+serverView.getMoveXtf().getText()+","+serverView.getMoveYtf().getText()+"\r\n");
                fw.write(serverView.getSearchMaxXtf().getText()+","+serverView.getSearchMaxYtf().getText()+"\r\n");
                fw.write(serverView.getTopLeftXtf().getText()+","+serverView.getTopLeftYtf().getText()+","+serverView.getTopRightXtf().getText()+","+serverView.getTopRightYtf().getText()+"\r\n");
                fw.write(serverView.getBottomLeftXtf().getText()+","+serverView.getBottomLeftYtf().getText()+","+serverView.getBottomRightXtf().getText()+","+serverView.getBottomRightYtf().getText()+"\r\n");
                fw.write(serverView.getPosTopLeftXtf().getText()+","+serverView.getPosTopLeftYtf().getText()+","+serverView.getPosBottomRightXtf().getText()+","+serverView.getPosBottomRightYtf().getText()+"\r\n");
                fw.write(serverView.getMaxLeftPosXtf().getText()+","+serverView.getMaxLeftPosYtf().getText()+","+serverView.getSearchLeftPosXtf().getText()+"\r\n");
                fw.write(serverView.getCzTopLeftXtf().getText()+","+serverView.getCzTopLeftYtf().getText()+","+serverView.getCzBottomRightXtf().getText()+","+serverView.getCzBottomRightYtf().getText()+"\r\n");

                fw.write(serverView.getYzmAreaLeftXtf().getText()+","+serverView.getYzmAreaLeftYtf().getText()+","+serverView.getYzmAreaRightXtf().getText()+","+serverView.getYzmAreaRightYtf().getText()+"\r\n");
                fw.write(serverView.getSelAPosLeftXtf().getText()+","+serverView.getSelAPosLeftYtf().getText()+","+serverView.getSelAPosRightXtf().getText()+","+serverView.getSelAPosRightYtf().getText()+"\r\n");
                fw.write(serverView.getSelBPosLeftXtf().getText()+","+serverView.getSelBPosLeftYtf().getText()+","+serverView.getSelBPosRightXtf().getText()+","+serverView.getSelBPosRightYtf().getText()+"\r\n");
                fw.write(serverView.getSelCPosLeftXtf().getText()+","+serverView.getSelCPosLeftYtf().getText()+","+serverView.getSelCPosRightXtf().getText()+","+serverView.getSelCPosRightYtf().getText()+"\r\n");
                fw.write(serverView.getSelDPosLeftXtf().getText()+","+serverView.getSelDPosLeftYtf().getText()+","+serverView.getSelDPosRightXtf().getText()+","+serverView.getSelDPosRightYtf().getText()+"\r\n");
                fw.write(serverView.getYzmPosXtf().getText()+","+serverView.getYzmPosYtf().getText()+"\r\n");
                fw.write(serverView.getConfirmBtnPosXtf().getText()+","+serverView.getConfirmBtnPosYtf().getText());

                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.err.println("IO异常");
                e.printStackTrace();
            }
        }
    }

}