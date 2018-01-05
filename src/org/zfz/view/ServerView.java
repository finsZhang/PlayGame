package org.zfz.view;

import javax.swing.*;

import org.zfz.controller.FileHandler;
import org.zfz.controller.HandlerServerClose;
import org.zfz.controller.HandlerServerPause;
import org.zfz.controller.HandlerServerStart;
import org.zfz.model.PlayServer;
import org.zfz.util.Util;
import sun.util.resources.CurrencyNames_es_UY;

import java.awt.*;
import java.io.*;

public class ServerView extends JFrame {
	private static final long serialVersionUID = 1L;
	private PlayServer server;

	// 启动按钮
	private JButton start;
	// 暂停按钮
	private JButton pause;
	// 关闭按钮
	private JButton close;

	// 中心坐标标签
	private JLabel centerLab;
	// 中心坐标文本域
	private JTextField centerXtf,centerYtf;

	// 搜索最大坐标标签
	private JLabel searchMaxLab;
	// 搜索最大坐标文本域
	private JTextField searchMaxXtf,searchMaxYtf;

	// X轴移动距离标签
	private JLabel moveXLab;
	// X轴移动距离文本域
	private JTextField moveXtf;

	// Y轴移动距离标签
	private JLabel moveYLab;
	// Y轴移动距离文本域
	private JTextField moveYtf;

	// 上沿提示标签
	private JLabel topLab;
	// 上沿提示文本域
	private JTextField topLeftXtf,topLeftYtf,topRightXtf,topRightYtf;
	// 下沿提示标签
	private JLabel bottomLab;
	// 下沿提示文本域
	private JTextField bottomLeftXtf,bottomLeftYtf,bottomRightXtf,bottomRightYtf;

	// 游戏坐标标签
	private JLabel playPosLab;
	// 游戏坐标文本域
	private JTextField posTopLeftXtf,posTopLeftYtf,posBottomRightXtf,posBottomRightYtf;

	// 铲镐数标签
	private JLabel czPosLab;
	// 游戏坐标文本域
	private JTextField czTopLeftXtf,czTopLeftYtf,czBottomRightXtf,czBottomRightYtf;

	// 游戏最大左上角坐标标签
	private JLabel maxLeftPosLab;
	// 游戏坐标标签
	private JTextField maxLeftPosXtf,maxLeftPosYtf;

	// 游戏最大左上角坐标标签
	private JLabel searchLeftPosLab;
	// 游戏坐标标签
	private JTextField searchLeftPosXtf;

	// 提示文本域
	private JTextArea jta;

	private JLabel copyLab = new JLabel("版权:张锋周");

	// 图像增强菜单
	private JMenuBar jmb = new JMenuBar();
	private JMenu jmFile = new JMenu("文件");
	private JMenuItem[] jmiFile = { new JMenuItem("保存配置文件"),
			new JMenuItem("加载配置文件") };


	// 验证码位置标签
	private JLabel yzmAreaLab;
	// 验证码位置文本域
	private JTextField yzmAreaLeftXtf,yzmAreaLeftYtf,yzmAreaRightXtf,yzmAreaRightYtf;
	// 选项A位置标签
	private JLabel selAPosLab;
	// 选项A位置文本域
	private JTextField selAPosLeftXtf,selAPosLeftYtf,selAPosRightXtf,selAPosRightYtf;
	// 选项B位置标签
	private JLabel selBPosLab;
	// 选项B位置文本域
	private JTextField selBPosLeftXtf,selBPosLeftYtf,selBPosRightXtf,selBPosRightYtf;
	// 选项C位置标签
	private JLabel selCPosLab;
	// 选项C位置文本域
	private JTextField selCPosLeftXtf,selCPosLeftYtf,selCPosRightXtf,selCPosRightYtf;
	// 选项D位置标签
	private JLabel selDPosLab;
	// 选项D位置文本域
	private JTextField selDPosLeftXtf,selDPosLeftYtf,selDPosRightXtf,selDPosRightYtf;

	// 判定验证码坐标标签
	private JLabel yzmPosLab;
	// 判定验证码坐标文本域
	private JTextField yzmPosXtf,yzmPosYtf;

	// 判定验证码坐标标签
	private JLabel confirmBtnPosLab;
	// 判定验证码坐标文本域
	private JTextField confirmBtnPosXtf,confirmBtnPosYtf;

	public ServerView() {
		server = new PlayServer(this);
		this.setTitle("Server");
		this.setBounds(500, 100, 900, 700);
		this.setLayout(null);

		centerLab = new JLabel("中心坐标:");
		centerLab.setBounds(10, 10, 60, 30);
		this.add(centerLab);
		centerXtf = new JTextField("964");
		centerYtf = new JTextField("570");
		centerXtf.setBounds(70, 10, 50, 30);
		centerYtf.setBounds(130, 10, 50, 30);
		this.add(centerXtf);
		this.add(centerYtf);

		searchMaxLab = new JLabel("搜索最大坐标:");
		searchMaxLab.setBounds(10, 50, 90, 30);
		this.add(searchMaxLab);
		searchMaxXtf = new JTextField("1664");
		searchMaxYtf = new JTextField("970");
		searchMaxXtf.setBounds(110, 50, 50, 30);
		searchMaxYtf.setBounds(170, 50, 50, 30);
		this.add(searchMaxXtf);
		this.add(searchMaxYtf);

		moveXLab = new JLabel("X轴步进:");
		moveXLab.setBounds(190, 10, 50, 30);
		this.add(moveXLab);

		moveXtf = new JTextField("500");
		moveXtf.setBounds(250, 10, 50, 30);
		this.add(moveXtf);

		moveYLab = new JLabel("Y轴步进:");
		moveYLab.setBounds(310, 10, 50, 30);
		this.add(moveYLab);

		moveYtf = new JTextField("200");
		moveYtf.setBounds(370, 10, 50, 30);
		this.add(moveYtf);


		topLab = new JLabel("上沿提示坐标:");
		topLab.setBounds(10, 90, 90, 30);
		this.add(topLab);

		topLeftXtf = new JTextField("1746");
		topLeftYtf = new JTextField("206");
		topLeftXtf.setBounds(110, 90, 50, 30);
		topLeftYtf.setBounds(170, 90, 50, 30);
		this.add(topLeftXtf);
		this.add(topLeftYtf);

		topRightXtf = new JTextField("1850");
		topRightYtf = new JTextField("206");
		topRightXtf.setBounds(230, 90, 50, 30);
		topRightYtf.setBounds(290, 90, 50, 30);
		this.add(topRightXtf);
		this.add(topRightYtf);

		bottomLab = new JLabel("下沿提示坐标:");
		bottomLab.setBounds(10, 130, 90, 30);
		this.add(bottomLab);

		bottomLeftXtf = new JTextField("1746");
		bottomLeftYtf = new JTextField("224");
		bottomLeftXtf.setBounds(110, 130, 50, 30);
		bottomLeftYtf.setBounds(170, 130, 50, 30);
		this.add(bottomLeftXtf);
		this.add(bottomLeftYtf);

		bottomRightXtf = new JTextField("1850");
		bottomRightYtf = new JTextField("224");
		bottomRightXtf.setBounds(230, 130, 50, 30);
		bottomRightYtf.setBounds(290, 130, 50, 30);
		this.add(bottomRightXtf);
		this.add(bottomRightYtf);

		playPosLab = new JLabel("游戏坐标位置:");
		playPosLab.setBounds(10, 170, 90, 30);
		this.add(playPosLab);

		posTopLeftXtf = new JTextField("1797");
		posTopLeftYtf = new JTextField("360");
		posTopLeftXtf.setBounds(110, 170, 50, 30);
		posTopLeftYtf.setBounds(170, 170, 50, 30);
		this.add(posTopLeftXtf);
		this.add(posTopLeftYtf);

		posBottomRightXtf = new JTextField("1897");
		posBottomRightYtf = new JTextField("380");
		posBottomRightXtf.setBounds(230, 170, 50, 30);
		posBottomRightYtf.setBounds(290, 170, 50, 30);

		this.add(posBottomRightXtf);
		this.add(posBottomRightYtf);

		maxLeftPosLab = new JLabel("左上最大位置:");
		maxLeftPosLab.setBounds(10, 210, 100, 30);
		this.add(maxLeftPosLab);
		maxLeftPosXtf = new JTextField("222");
		maxLeftPosYtf = new JTextField("318");
		maxLeftPosXtf.setBounds(110, 210, 50, 30);
		maxLeftPosYtf.setBounds(170, 210, 50, 30);
		this.add(maxLeftPosXtf);
		this.add(maxLeftPosYtf);

		searchLeftPosLab = new JLabel("左下最小X:");
		searchLeftPosLab.setBounds(230, 210, 70, 30);
		this.add(searchLeftPosLab);
		searchLeftPosXtf = new JTextField("200");
		searchLeftPosXtf.setBounds(310, 210, 50, 30);
		this.add(searchLeftPosXtf);

		czPosLab = new JLabel("铲子数位置:");
		czPosLab.setBounds(10, 250, 90, 30);
		this.add(czPosLab);

		czTopLeftXtf = new JTextField("15");
		czTopLeftYtf = new JTextField("353");
		czTopLeftXtf.setBounds(110, 250, 50, 30);
		czTopLeftYtf.setBounds(170, 250, 50, 30);
		this.add(czTopLeftXtf);
		this.add(czTopLeftYtf);

		czBottomRightXtf = new JTextField("70");
		czBottomRightYtf = new JTextField("438");
		czBottomRightXtf.setBounds(230, 250, 50, 30);
		czBottomRightYtf.setBounds(290, 250, 50, 30);
		this.add(czBottomRightXtf);
		this.add(czBottomRightYtf);
		// 文本域
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBounds(450, 10, 420, 590);
		this.add(jsp);

		// 启动按钮
		start = new JButton("启动");
		start.setBounds(370, 70, 60, 30);
		start.addActionListener(new HandlerServerStart(this));
		this.add(start);

		// 暂停按钮
		pause = new JButton("暂停");
		pause.setBounds(370, 140, 60, 30);
		pause.addActionListener(new HandlerServerPause(this));
		this.add(pause);

		// 关闭按钮
		close = new JButton("关闭");
		close.setBounds(370, 200, 60, 30);
		close.addActionListener(new HandlerServerClose());
		this.add(close);

		// 版权
		copyLab.setBounds(390, 590, 130, 50);
        copyLab.setForeground(Color.RED);
        copyLab.setFont(new Font("宋体", Font.BOLD, 20));
		this.add(copyLab);

		//添加图像增强菜单项和监听器
		for (int i = 0; i < jmiFile.length; i++) {
			jmFile.add(jmiFile[i]);
			jmiFile[i].addActionListener(new FileHandler(this));
		}
		// 添加图像增强菜单
		jmb.add(jmFile);
		this.setJMenuBar(jmb);

		yzmAreaLab = new JLabel("验证码坐标:");
		yzmAreaLab.setBounds(10, 290, 90, 30);
		this.add(yzmAreaLab);

		yzmAreaLeftXtf = new JTextField("750");
		yzmAreaLeftYtf = new JTextField("298");
		yzmAreaLeftXtf.setBounds(110, 290, 50, 30);
		yzmAreaLeftYtf.setBounds(170, 290, 50, 30);
		this.add(yzmAreaLeftXtf);
		this.add(yzmAreaLeftYtf);

		yzmAreaRightXtf = new JTextField("1143");
		yzmAreaRightYtf = new JTextField("540");
		yzmAreaRightXtf.setBounds(230, 290, 50, 30);
		yzmAreaRightYtf.setBounds(290, 290, 50, 30);
		this.add(yzmAreaRightXtf);
		this.add(yzmAreaRightYtf);

		selAPosLab = new JLabel("选项A坐标:");
		selAPosLab.setBounds(10, 330, 90, 30);
		this.add(selAPosLab);

		selAPosLeftXtf = new JTextField("732");
		selAPosLeftYtf = new JTextField("630");
		selAPosLeftXtf.setBounds(110, 330, 50, 30);
		selAPosLeftYtf.setBounds(170, 330, 50, 30);
		this.add(selAPosLeftXtf);
		this.add(selAPosLeftYtf);

		selAPosRightXtf = new JTextField("876");
		selAPosRightYtf = new JTextField("680");
		selAPosRightXtf.setBounds(230, 330, 50, 30);
		selAPosRightYtf.setBounds(290, 330, 50, 30);
		this.add(selAPosRightXtf);
		this.add(selAPosRightYtf);

		selBPosLab = new JLabel("选项B坐标:");
		selBPosLab.setBounds(10, 370, 90, 30);
		this.add(selBPosLab);

		selBPosLeftXtf = new JTextField("1042");
		selBPosLeftYtf = new JTextField("630");
		selBPosLeftXtf.setBounds(110, 370, 50, 30);
		selBPosLeftYtf.setBounds(170, 370, 50, 30);
		this.add(selBPosLeftXtf);
		this.add(selBPosLeftYtf);

		selBPosRightXtf = new JTextField("1220");
		selBPosRightYtf = new JTextField("680");
		selBPosRightXtf.setBounds(230, 370, 50, 30);
		selBPosRightYtf.setBounds(290, 370, 50, 30);
		this.add(selBPosRightXtf);
		this.add(selBPosRightYtf);

		selCPosLab = new JLabel("选项C坐标:");
		selCPosLab.setBounds(10, 410, 90, 30);
		this.add(selCPosLab);

		selCPosLeftXtf = new JTextField("732");
		selCPosLeftYtf = new JTextField("747");
		selCPosLeftXtf.setBounds(110, 410, 50, 30);
		selCPosLeftYtf.setBounds(170, 410, 50, 30);
		this.add(selCPosLeftXtf);
		this.add(selCPosLeftYtf);

		selCPosRightXtf = new JTextField("876");
		selCPosRightYtf = new JTextField("794");
		selCPosRightXtf.setBounds(230, 410, 50, 30);
		selCPosRightYtf.setBounds(290, 410, 50, 30);
		this.add(selCPosRightXtf);
		this.add(selCPosRightYtf);

		selDPosLab = new JLabel("选项D坐标:");
		selDPosLab.setBounds(10, 450, 90, 30);
		this.add(selDPosLab);

		selDPosLeftXtf = new JTextField("1042");
		selDPosLeftYtf = new JTextField("747");
		selDPosLeftXtf.setBounds(110, 450, 50, 30);
		selDPosLeftYtf.setBounds(170, 450, 50, 30);
		this.add(selDPosLeftXtf);
		this.add(selDPosLeftYtf);

		selDPosRightXtf = new JTextField("1220");
		selDPosRightYtf = new JTextField("794");
		selDPosRightXtf.setBounds(230, 450, 50, 30);
		selDPosRightYtf.setBounds(290, 450, 50, 30);
		this.add(selDPosRightXtf);
		this.add(selDPosRightYtf);

		yzmPosLab = new JLabel("验证颜色坐标:");
		yzmPosLab.setBounds(10, 490, 90, 30);
		this.add(yzmPosLab);
		yzmPosXtf = new JTextField("700");
		yzmPosYtf = new JTextField("500");
		yzmPosXtf.setBounds(110, 490, 50, 30);
		yzmPosYtf.setBounds(170, 490, 50, 30);
		this.add(yzmPosXtf);
		this.add(yzmPosYtf);

		confirmBtnPosLab = new JLabel("确认按钮坐标:");
		confirmBtnPosLab.setBounds(10, 530, 90, 30);
		this.add(confirmBtnPosLab);
		confirmBtnPosXtf = new JTextField("890");
		confirmBtnPosYtf = new JTextField("630");
		confirmBtnPosXtf.setBounds(110, 530, 50, 30);
		confirmBtnPosYtf.setBounds(170, 530, 50, 30);
		this.add(confirmBtnPosXtf);
		this.add(confirmBtnPosYtf);

		initPos();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initPos(){
		String filePath = Util.getDefaultPath()+"cfg.txt";
		Util.loadCfgFile(this,filePath);
	}


	public PlayServer getServer() {
		return server;
	}

	public void setServer(PlayServer server) {
		this.server = server;
	}


	public JTextField getCenterXtf() {
		return centerXtf;
	}

	public void setCenterXtf(JTextField centerXtf) {
		this.centerXtf = centerXtf;
	}

	public JTextField getCenterYtf() {
		return centerYtf;
	}

	public void setCenterYtf(JTextField centerYtf) {
		this.centerYtf = centerYtf;
	}

	public JTextField getSearchMaxXtf() {
		return searchMaxXtf;
	}

	public void setSearchMaxXtf(JTextField searchMaxXtf) {
		this.searchMaxXtf = searchMaxXtf;
	}

	public JTextField getSearchMaxYtf() {
		return searchMaxYtf;
	}

	public void setSearchMaxYtf(JTextField searchMaxYtf) {
		this.searchMaxYtf = searchMaxYtf;
	}

	public JTextField getMoveYtf() {
		return moveYtf;
	}

	public void setMoveYtf(JTextField moveYtf) {
		this.moveYtf = moveYtf;
	}

	public JTextField getBottomLeftXtf() {
		return bottomLeftXtf;
	}

	public void setBottomLeftXtf(JTextField bottomLeftXtf) {
		this.bottomLeftXtf = bottomLeftXtf;
	}

	public JTextField getBottomLeftYtf() {
		return bottomLeftYtf;
	}

	public void setBottomLeftYtf(JTextField bottomLeftYtf) {
		this.bottomLeftYtf = bottomLeftYtf;
	}

	public JTextField getBottomRightXtf() {
		return bottomRightXtf;
	}

	public void setBottomRightXtf(JTextField bottomRightXtf) {
		this.bottomRightXtf = bottomRightXtf;
	}

	public JTextField getBottomRightYtf() {
		return bottomRightYtf;
	}

	public void setBottomRightYtf(JTextField bottomRightYtf) {
		this.bottomRightYtf = bottomRightYtf;
	}

	public JTextField getTopLeftXtf() {
		return topLeftXtf;
	}

	public void setTopLeftXtf(JTextField topLeftXtf) {
		this.topLeftXtf = topLeftXtf;
	}

	public JTextField getTopLeftYtf() {
		return topLeftYtf;
	}

	public void setTopLeftYtf(JTextField topLeftYtf) {
		this.topLeftYtf = topLeftYtf;
	}

	public JTextField getTopRightXtf() {
		return topRightXtf;
	}

	public void setTopRightXtf(JTextField topRightXtf) {
		this.topRightXtf = topRightXtf;
	}

	public JTextField getTopRightYtf() {
		return topRightYtf;
	}

	public void setTopRightYtf(JTextField topRightYtf) {
		this.topRightYtf = topRightYtf;
	}

	public JTextField getPosTopLeftXtf() {
		return posTopLeftXtf;
	}

	public void setPosTopLeftXtf(JTextField posTopLeftXtf) {
		this.posTopLeftXtf = posTopLeftXtf;
	}

	public JTextField getPosTopLeftYtf() {
		return posTopLeftYtf;
	}

	public void setPosTopLeftYtf(JTextField posTopLeftYtf) {
		this.posTopLeftYtf = posTopLeftYtf;
	}

	public JTextField getPosBottomRightXtf() {
		return posBottomRightXtf;
	}

	public void setPosBottomRightXtf(JTextField posBottomRightXtf) {
		this.posBottomRightXtf = posBottomRightXtf;
	}

	public JTextField getPosBottomRightYtf() {
		return posBottomRightYtf;
	}

	public void setPosBottomRightYtf(JTextField posBottomRightYtf) {
		this.posBottomRightYtf = posBottomRightYtf;
	}

	public JTextField getMaxLeftPosXtf() {
		return maxLeftPosXtf;
	}

	public void setMaxLeftPosXtf(JTextField maxLeftPosXtf) {
		this.maxLeftPosXtf = maxLeftPosXtf;
	}

	public JTextField getMaxLeftPosYtf() {
		return maxLeftPosYtf;
	}

	public void setMaxLeftPosYtf(JTextField maxLeftPosYtf) {
		this.maxLeftPosYtf = maxLeftPosYtf;
	}

	public JTextArea getJta() {
		return jta;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public JTextField getCzTopLeftXtf() {
		return czTopLeftXtf;
	}

	public void setCzTopLeftXtf(JTextField czTopLeftXtf) {
		this.czTopLeftXtf = czTopLeftXtf;
	}

	public JTextField getCzTopLeftYtf() {
		return czTopLeftYtf;
	}

	public void setCzTopLeftYtf(JTextField czTopLeftYtf) {
		this.czTopLeftYtf = czTopLeftYtf;
	}

	public JTextField getCzBottomRightXtf() {
		return czBottomRightXtf;
	}

	public void setCzBottomRightXtf(JTextField czBottomRightXtf) {
		this.czBottomRightXtf = czBottomRightXtf;
	}

	public JTextField getCzBottomRightYtf() {
		return czBottomRightYtf;
	}

	public void setCzBottomRightYtf(JTextField czBottomRightYtf) {
		this.czBottomRightYtf = czBottomRightYtf;
	}

	public JTextField getSearchLeftPosXtf() {
		return searchLeftPosXtf;
	}

	public void setSearchLeftPosXtf(JTextField searchLeftPosXtf) {
		this.searchLeftPosXtf = searchLeftPosXtf;
	}

	public JMenuItem[] getJmiFile() {
		return jmiFile;
	}

	public void setJmiFile(JMenuItem[] jmiFile) {
		this.jmiFile = jmiFile;
	}

	public JTextField getMoveXtf() {
		return moveXtf;
	}

	public void setMoveXtf(JTextField moveXtf) {
		this.moveXtf = moveXtf;
	}


	public JTextField getYzmAreaLeftXtf() {
		return yzmAreaLeftXtf;
	}

	public void setYzmAreaLeftXtf(JTextField yzmAreaLeftXtf) {
		this.yzmAreaLeftXtf = yzmAreaLeftXtf;
	}

	public JTextField getYzmAreaLeftYtf() {
		return yzmAreaLeftYtf;
	}

	public void setYzmAreaLeftYtf(JTextField yzmAreaLeftYtf) {
		this.yzmAreaLeftYtf = yzmAreaLeftYtf;
	}

	public JTextField getYzmAreaRightXtf() {
		return yzmAreaRightXtf;
	}

	public void setYzmAreaRightXtf(JTextField yzmAreaRightXtf) {
		this.yzmAreaRightXtf = yzmAreaRightXtf;
	}

	public JTextField getYzmAreaRightYtf() {
		return yzmAreaRightYtf;
	}

	public void setYzmAreaRightYtf(JTextField yzmAreaRightYtf) {
		this.yzmAreaRightYtf = yzmAreaRightYtf;
	}

	public JTextField getSelAPosLeftXtf() {
		return selAPosLeftXtf;
	}

	public void setSelAPosLeftXtf(JTextField selAPosLeftXtf) {
		this.selAPosLeftXtf = selAPosLeftXtf;
	}

	public JTextField getSelAPosLeftYtf() {
		return selAPosLeftYtf;
	}

	public void setSelAPosLeftYtf(JTextField selAPosLeftYtf) {
		this.selAPosLeftYtf = selAPosLeftYtf;
	}

	public JTextField getSelAPosRightXtf() {
		return selAPosRightXtf;
	}

	public void setSelAPosRightXtf(JTextField selAPosRightXtf) {
		this.selAPosRightXtf = selAPosRightXtf;
	}

	public JTextField getSelAPosRightYtf() {
		return selAPosRightYtf;
	}

	public void setSelAPosRightYtf(JTextField selAPosRightYtf) {
		this.selAPosRightYtf = selAPosRightYtf;
	}

	public JTextField getSelBPosLeftXtf() {
		return selBPosLeftXtf;
	}

	public void setSelBPosLeftXtf(JTextField selBPosLeftXtf) {
		this.selBPosLeftXtf = selBPosLeftXtf;
	}

	public JTextField getSelBPosLeftYtf() {
		return selBPosLeftYtf;
	}

	public void setSelBPosLeftYtf(JTextField selBPosLeftYtf) {
		this.selBPosLeftYtf = selBPosLeftYtf;
	}

	public JTextField getSelBPosRightXtf() {
		return selBPosRightXtf;
	}

	public void setSelBPosRightXtf(JTextField selBPosRightXtf) {
		this.selBPosRightXtf = selBPosRightXtf;
	}

	public JTextField getSelBPosRightYtf() {
		return selBPosRightYtf;
	}

	public void setSelBPosRightYtf(JTextField selBPosRightYtf) {
		this.selBPosRightYtf = selBPosRightYtf;
	}

	public JTextField getSelCPosLeftXtf() {
		return selCPosLeftXtf;
	}

	public void setSelCPosLeftXtf(JTextField selCPosLeftXtf) {
		this.selCPosLeftXtf = selCPosLeftXtf;
	}

	public JTextField getSelCPosLeftYtf() {
		return selCPosLeftYtf;
	}

	public void setSelCPosLeftYtf(JTextField selCPosLeftYtf) {
		this.selCPosLeftYtf = selCPosLeftYtf;
	}

	public JTextField getSelCPosRightXtf() {
		return selCPosRightXtf;
	}

	public void setSelCPosRightXtf(JTextField selCPosRightXtf) {
		this.selCPosRightXtf = selCPosRightXtf;
	}

	public JTextField getSelCPosRightYtf() {
		return selCPosRightYtf;
	}

	public void setSelCPosRightYtf(JTextField selCPosRightYtf) {
		this.selCPosRightYtf = selCPosRightYtf;
	}

	public JTextField getSelDPosLeftXtf() {
		return selDPosLeftXtf;
	}

	public void setSelDPosLeftXtf(JTextField selDPosLeftXtf) {
		this.selDPosLeftXtf = selDPosLeftXtf;
	}

	public JTextField getSelDPosLeftYtf() {
		return selDPosLeftYtf;
	}

	public void setSelDPosLeftYtf(JTextField selDPosLeftYtf) {
		this.selDPosLeftYtf = selDPosLeftYtf;
	}

	public JTextField getSelDPosRightXtf() {
		return selDPosRightXtf;
	}

	public void setSelDPosRightXtf(JTextField selDPosRightXtf) {
		this.selDPosRightXtf = selDPosRightXtf;
	}

	public JTextField getSelDPosRightYtf() {
		return selDPosRightYtf;
	}

	public void setSelDPosRightYtf(JTextField selDPosRightYtf) {
		this.selDPosRightYtf = selDPosRightYtf;
	}

	public JTextField getYzmPosXtf() {
		return yzmPosXtf;
	}

	public void setYzmPosXtf(JTextField yzmPosXtf) {
		this.yzmPosXtf = yzmPosXtf;
	}

	public JTextField getYzmPosYtf() {
		return yzmPosYtf;
	}

	public void setYzmPosYtf(JTextField yzmPosYtf) {
		this.yzmPosYtf = yzmPosYtf;
	}

	public JTextField getConfirmBtnPosXtf() {
		return confirmBtnPosXtf;
	}

	public void setConfirmBtnPosXtf(JTextField confirmBtnPosXtf) {
		this.confirmBtnPosXtf = confirmBtnPosXtf;
	}

	public JTextField getConfirmBtnPosYtf() {
		return confirmBtnPosYtf;
	}

	public void setConfirmBtnPosYtf(JTextField confirmBtnPosYtf) {
		this.confirmBtnPosYtf = confirmBtnPosYtf;
	}
}
