package org.zfz.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.zfz.util.Util;
import org.zfz.view.ServerView;

public class PlayServer extends Thread {
	private ServerView serverView;

	private int xPos = 964, yPos = 570; //定义中心坐标

	private int searchLeftMaxX = 200, searchMaxX = 1664,searchMaxY= 970;//搜索最大范围坐标

	private int moveMaxX= 500; //X轴步进
	private int moveMaxY = 200; //Y轴步进

	private int[] topTip = {1746,206,1850,206}; //上沿坐标
	private int[] bottomTip = {1746,224,1850,224}; //下沿坐标

	private int[] playPos = {1797,360,1897,380};  //游戏坐标位置
	private int[] czPos = {15,353,70,438};  //铲子数坐标

	private int[] playMaxLeftPos = {222,318};  //最大左上坐标

	private int[] yzmArea = {750,298,1143,540};//验证码范围
	private int[] selAPos = {732,630,876,680};//选项A位置
	private int[] selBPos = {1042,630,1220,680};//选项B位置
	private int[] selCPos = {732,747,876,794};//选项C位置
	private int[] selDPos = {1042,747,1220,794};//选项D位置

	private int[]yzmPos = {700,500}; //判定验证码坐标
	private static int[]confirmBtnPos = {890,630};//确认或别人矿产提示按钮位置


	private static Set resPix = new HashSet(); //资源颜色集合

	private static Map<String,String> resPixMap = new HashMap<>(); //资源颜色对应矿产种类

	private Map<String,Integer> resTotal = new HashMap<>(); //资源总数

	private boolean isRight = true;
	private int[] oldLeftRgbs = new int[1];

	private boolean isCheckCzRes = true;

	private String defaultPath = Util.getDefaultPath();
	public PlayServer(ServerView serverView) {
		super();
		this.serverView = serverView;
	}

	/**
	 * 初始化坐标配置，颜色配置
	 * */
	private void init(){
		//中心坐标
		if(!"".equals(serverView.getCenterXtf().getText())){
			xPos = Integer.parseInt(serverView.getCenterXtf().getText());
		}
		if(!"".equals(serverView.getCenterYtf().getText())){
			yPos = Integer.parseInt(serverView.getCenterYtf().getText());
		}
		//X轴移动最大距离
		if(!"".equals(serverView.getMoveXtf().getText())){
			moveMaxX = Integer.parseInt(serverView.getMoveXtf().getText());
		}
		//Y轴移动最大距离
		if(!"".equals(serverView.getMoveYtf().getText())){
			moveMaxY = Integer.parseInt(serverView.getMoveYtf().getText());
		}
		//搜索最大坐标
		if(!"".equals(serverView.getSearchMaxXtf().getText())){
			searchMaxX = Integer.parseInt(serverView.getSearchMaxXtf().getText());
		}
		if(!"".equals(serverView.getSearchMaxYtf().getText())){
			searchMaxY = Integer.parseInt(serverView.getSearchMaxYtf().getText());
		}
		//上沿提示坐标
		if(!"".equals(serverView.getTopLeftXtf().getText())){
			topTip[0] = Integer.parseInt(serverView.getTopLeftXtf().getText());
		}
		if(!"".equals(serverView.getTopLeftYtf().getText())){
			topTip[1] = Integer.parseInt(serverView.getTopLeftYtf().getText());
		}

		if(!"".equals(serverView.getTopRightXtf().getText())){
			topTip[2] = Integer.parseInt(serverView.getTopRightXtf().getText());
		}
		if(!"".equals(serverView.getTopRightYtf().getText())){
			topTip[3] = Integer.parseInt(serverView.getTopRightYtf().getText());
		}

		//下沿提示坐标
		if(!"".equals(serverView.getBottomLeftXtf().getText())){
			bottomTip[0] = Integer.parseInt(serverView.getBottomLeftXtf().getText());
		}
		if(!"".equals(serverView.getBottomLeftYtf().getText())){
			bottomTip[1] = Integer.parseInt(serverView.getBottomLeftYtf().getText());
		}

		if(!"".equals(serverView.getBottomRightXtf().getText())){
			bottomTip[2] = Integer.parseInt(serverView.getBottomRightXtf().getText());
		}
		if(!"".equals(serverView.getBottomRightYtf().getText())){
			bottomTip[3] = Integer.parseInt(serverView.getBottomRightYtf().getText());
		}
		//游戏坐标位置
		if(!"".equals(serverView.getPosTopLeftXtf().getText())){
			playPos[0] = Integer.parseInt(serverView.getPosTopLeftXtf().getText());
		}
		if(!"".equals(serverView.getPosTopLeftYtf().getText())){
			playPos[1] = Integer.parseInt(serverView.getPosTopLeftYtf().getText());
		}

		if(!"".equals(serverView.getPosBottomRightXtf().getText())){
			playPos[2] = Integer.parseInt(serverView.getPosBottomRightXtf().getText());
		}
		if(!"".equals(serverView.getPosBottomRightYtf().getText())){
			playPos[3] = Integer.parseInt(serverView.getPosBottomRightYtf().getText());
		}
		//左上最大位置
		if(!"".equals(serverView.getMaxLeftPosXtf().getText())){
			playMaxLeftPos[0] = Integer.parseInt(serverView.getMaxLeftPosXtf().getText());
		}
		if(!"".equals(serverView.getMaxLeftPosYtf().getText())){
			playMaxLeftPos[1] = Integer.parseInt(serverView.getMaxLeftPosYtf().getText());
		}
		//左下最大X值
		if(!"".equals(serverView.getSearchLeftPosXtf().getText())){
			searchLeftMaxX = Integer.parseInt(serverView.getSearchLeftPosXtf().getText());
		}
		//铲子数位置
		if(!"".equals(serverView.getCzTopLeftXtf().getText())){
			czPos[0] = Integer.parseInt(serverView.getCzTopLeftXtf().getText());
		}
		if(!"".equals(serverView.getCzTopLeftYtf().getText())){
			czPos[1] = Integer.parseInt(serverView.getCzTopLeftYtf().getText());
		}

		if(!"".equals(serverView.getCzBottomRightXtf().getText())){
			czPos[2] = Integer.parseInt(serverView.getCzBottomRightXtf().getText());
		}
		if(!"".equals(serverView.getCzBottomRightYtf().getText())){
			czPos[3] = Integer.parseInt(serverView.getCzBottomRightYtf().getText());
		}
		//验证码坐标
		if(!"".equals(serverView.getYzmAreaLeftXtf().getText())){
			yzmArea[0] = Integer.parseInt(serverView.getYzmAreaLeftXtf().getText());
		}
		if(!"".equals(serverView.getYzmAreaLeftYtf().getText())){
			yzmArea[1] = Integer.parseInt(serverView.getYzmAreaLeftYtf().getText());
		}

		if(!"".equals(serverView.getYzmAreaRightXtf().getText())){
			yzmArea[2] = Integer.parseInt(serverView.getYzmAreaRightXtf().getText());
		}
		if(!"".equals(serverView.getYzmAreaRightYtf().getText())){
			yzmArea[3] = Integer.parseInt(serverView.getYzmAreaRightYtf().getText());
		}
		//选项A坐标
		if(!"".equals(serverView.getSelAPosLeftXtf().getText())){
			selAPos[0] = Integer.parseInt(serverView.getSelAPosLeftXtf().getText());
		}
		if(!"".equals(serverView.getSelAPosLeftYtf().getText())){
			selAPos[1] = Integer.parseInt(serverView.getSelAPosLeftYtf().getText());
		}
		if(!"".equals(serverView.getSelAPosRightXtf().getText())){
			selAPos[2] = Integer.parseInt(serverView.getSelAPosRightXtf().getText());
		}
		if(!"".equals(serverView.getSelAPosRightYtf().getText())){
			selAPos[3] = Integer.parseInt(serverView.getSelAPosRightYtf().getText());
		}
		//选项B坐标
		if(!"".equals(serverView.getSelBPosLeftXtf().getText())){
			selBPos[0] = Integer.parseInt(serverView.getSelBPosLeftXtf().getText());
		}
		if(!"".equals(serverView.getSelBPosLeftYtf().getText())){
			selBPos[1] = Integer.parseInt(serverView.getSelBPosLeftYtf().getText());
		}
		if(!"".equals(serverView.getSelBPosRightXtf().getText())){
			selBPos[2] = Integer.parseInt(serverView.getSelBPosRightXtf().getText());
		}
		if(!"".equals(serverView.getSelBPosRightYtf().getText())){
			selBPos[3] = Integer.parseInt(serverView.getSelBPosRightYtf().getText());
		}
		//选项C坐标
		if(!"".equals(serverView.getSelCPosLeftXtf().getText())){
			selCPos[0] = Integer.parseInt(serverView.getSelCPosLeftXtf().getText());
		}
		if(!"".equals(serverView.getSelCPosLeftYtf().getText())){
			selCPos[1] = Integer.parseInt(serverView.getSelCPosLeftYtf().getText());
		}
		if(!"".equals(serverView.getSelCPosRightXtf().getText())){
			selCPos[2] = Integer.parseInt(serverView.getSelCPosRightXtf().getText());
		}
		if(!"".equals(serverView.getSelCPosRightYtf().getText())){
			selCPos[3] = Integer.parseInt(serverView.getSelCPosRightYtf().getText());
		}
		//选项D坐标
		if(!"".equals(serverView.getSelDPosLeftXtf().getText())){
			selDPos[0] = Integer.parseInt(serverView.getSelDPosLeftXtf().getText());
		}
		if(!"".equals(serverView.getSelDPosLeftYtf().getText())){
			selDPos[1] = Integer.parseInt(serverView.getSelDPosLeftYtf().getText());
		}
		if(!"".equals(serverView.getSelDPosRightXtf().getText())){
			selDPos[2] = Integer.parseInt(serverView.getSelDPosRightXtf().getText());
		}
		if(!"".equals(serverView.getSelDPosRightYtf().getText())){
			selDPos[3] = Integer.parseInt(serverView.getSelDPosRightYtf().getText());
		}
		//验证颜色坐标
		if(!"".equals(serverView.getYzmPosXtf().getText())){
			yzmPos[0] = Integer.parseInt(serverView.getYzmPosXtf().getText());
		}
		if(!"".equals(serverView.getYzmPosYtf().getText())){
			yzmPos[1] = Integer.parseInt(serverView.getYzmPosYtf().getText());
		}
		//确认按钮坐标
		if(!"".equals(serverView.getConfirmBtnPosXtf().getText())){
			confirmBtnPos[0] = Integer.parseInt(serverView.getConfirmBtnPosXtf().getText());
		}
		if(!"".equals(serverView.getConfirmBtnPosYtf().getText())){
			confirmBtnPos[1] = Integer.parseInt(serverView.getConfirmBtnPosYtf().getText());
		}
		resTotal.put("钻石",0);
		resTotal.put("金矿",0);
		resTotal.put("银矿",0);
		resTotal.put("铜矿",0);
		resTotal.put("树木",0);

		File file = new File(defaultPath+"resColor.txt");
		if(file.exists()) {
			try {
				FileInputStream fis  = new FileInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis , "UTF-8"));
				String colors = null;
				String[] color;
				while ((colors = reader.readLine()) != null) {
					color = colors.split(",");
					serverView.getJta().append("Red,Green,Blue,矿产:"+colors+"\n");
					resPix.add(getRbg(Integer.parseInt(color[0].trim()),Integer.parseInt(color[1]),Integer.parseInt(color[2])));//金矿
					resPixMap.put(getRbg(Integer.parseInt(color[0]),Integer.parseInt(color[1]),Integer.parseInt(color[2])),color[3]);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据r,g,b计算RGB十六进制
	 * */
	private static String getRbg(int r,int g,int b){
		return r +"~"+ g +"~"+ b;
	}
	/**
	 * 创建机器对象
	 * */
	private  Robot createRobot(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return robot;
	}

	/**
	 * 获取电脑分辨率
	 * */
	private static  Rectangle getScreenRect(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRect = new Rectangle(d);
		return screenRect;
	}

	/**
	 * 按下Alt+TAB键
	 * */
	private static  void changeTab( Robot robot){
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	public void run()  {
		try {
			init();
			Robot robot = createRobot();

			//获取屏幕分辨率
			Rectangle screenRect = getScreenRect();
			//按下Alt+TAB键
			changeTab(robot);
			robot.delay(2000);

			//回到中心点
			robot.mouseMove(xPos, yPos);
			robot.mousePress(KeyEvent.BUTTON1_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			robot.delay(2000);
			BufferedImage playImage = robot.createScreenCapture(screenRect);
			if(isCheckCzRes)  //检测铲子数变化，判定误挖矿产
			   oldLeftRgbs = playImage.getRGB(czPos[0],czPos[1],czPos[2]-czPos[0],czPos[3]-czPos[1],null,0,czPos[2]-czPos[0]);
			int[] oldRgbs = new int[1];

			int moveX;
			int moveY = moveMaxY;

			while (true) {
				synchronized (control) {
					if (suspend) {
						try {
							control.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				Color color = new Color(playImage.getRGB(confirmBtnPos[0], confirmBtnPos[1]));
				if (getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(181, 201, 1))) {  //判定确认弹框
					robot.mouseMove(confirmBtnPos[0], confirmBtnPos[1]);
					robot.mousePress(KeyEvent.BUTTON1_MASK);
					robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				}

				color = new Color(playImage.getRGB(yzmPos[0],yzmPos[1]));
				if(getRbg(color.getRed(),color.getGreen(),color.getBlue()).equals(getRbg(255,253,239))){  //判定验证码弹框
					CheckCode.selecCode(robot,yzmArea,selAPos,selBPos,selCPos,selDPos,confirmBtnPos);
				}
				//挖完一页的矿
				searchCurPage(robot, screenRect);

				if (isRight) {
					moveX = xPos+moveMaxX;
				} else {
					moveX = xPos - moveMaxX;
				}

				robot.mouseMove(moveX, yPos);
				robot.delay(1000);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				robot.delay(3000);
				playImage = robot.createScreenCapture(screenRect);

				Color bottomColor = new Color(playImage.getRGB(bottomTip[0], bottomTip[1]));
				String bottomColorVal = getRbg(bottomColor.getRed(), bottomColor.getGreen(), bottomColor.getBlue());

				Color bottomRightColor = new Color(playImage.getRGB(bottomTip[2], bottomTip[3]));
				String bottomRightColorVal = getRbg(bottomRightColor.getRed(), bottomRightColor.getGreen(), bottomRightColor.getBlue());

				Color topColor = new Color(playImage.getRGB(topTip[0], topTip[1]));
				String topColorVal = getRbg(topColor.getRed(), topColor.getGreen(), topColor.getBlue());
				Color topRightColor = new Color(playImage.getRGB(topTip[2], topTip[3]));
				String topRightColorVal = getRbg(topRightColor.getRed(), topRightColor.getGreen(), topRightColor.getBlue());
				if (bottomColorVal.equals(getRbg(0, 0, 0)) && bottomRightColorVal.equals(getRbg(0, 0, 0))) {
					moveY = -moveMaxY;
				}
				if (topColorVal.equals(getRbg(0, 0, 0)) && topRightColorVal.equals(getRbg(0, 0, 0))) {
					moveY = moveMaxY;
				}
				color = new Color(playImage.getRGB(yzmPos[0],yzmPos[1]));
				if(getRbg(color.getRed(),color.getGreen(),color.getBlue()).equals(getRbg(255,253,239))){ //判定出现验证码
					CheckCode.selecCode(robot,yzmArea,selAPos,selBPos,selCPos,selDPos,confirmBtnPos);
				}
				if (isEqual(playImage, playPos[0], playPos[1], playPos[2]-playPos[0], playPos[3]-playPos[1], oldRgbs)) {  //是否边界
					robot.mouseMove(xPos, yPos + moveY);
					robot.delay(1000);
					robot.mousePress(KeyEvent.BUTTON1_MASK);
					robot.mouseRelease(KeyEvent.BUTTON1_MASK);
					robot.delay(3000);
					playImage = robot.createScreenCapture(screenRect);
					oldRgbs = playImage.getRGB(playPos[0], playPos[1], playPos[2]-playPos[0], playPos[3]-playPos[1], null, 0, playPos[2]-playPos[0]);
					isRight = !isRight;
				} else {
					oldRgbs = playImage.getRGB(playPos[0], playPos[1], playPos[2]-playPos[0], playPos[3]-playPos[1], null, 0, playPos[2]-playPos[0]);
				}
				if(isCheckCzRes) {
					if (isEqual(playImage, czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], oldLeftRgbs)) {//没挖矿
					} else {
						robot.delay(8000);
						playImage = robot.createScreenCapture(screenRect);
						oldLeftRgbs = playImage.getRGB(czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], null, 0, czPos[2]-czPos[0]);
					}
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static boolean isEqual(BufferedImage image,int startX,int endX,int width,int height,int[]oldRgbs){
		int[]rgbs = image.getRGB(startX,endX,width,height,null,0,width);
		if (rgbs.length != oldRgbs.length) {
			return false;
		} else {
			for (int i = 0; i < rgbs.length; i++) {
				if (rgbs[i] != oldRgbs[i]) {
					return false;
				}
			}
			return true;
		}
	}
	private static boolean isContainColor(int pix){
		Color color = new Color(pix);
		String colorVal = getRbg(color.getRed(),color.getGreen(),color.getBlue());
		if(resPix.contains(colorVal)){
			return true;
		}else{
			return false;
		}
	}

	private int[] searchRes(BufferedImage playImage,int[]startPox) {
		int[]pox = null;
			for (int i = startPox[0]+1; i < searchMaxX; i++) {
				for (int j = startPox[1]+1; j < searchMaxY; j++) {
					int pix = playImage.getRGB(i, j);
				if (isContainColor(pix)) {
					Color color = new Color(pix);
					String colorVal = getRbg(color.getRed(),color.getGreen(),color.getBlue());
					if(resPixMap.containsKey(colorVal)&&resTotal.containsKey(resPixMap.get(colorVal))) {
						resTotal.put(resPixMap.get(colorVal), resTotal.get(resPixMap.get(colorVal)).intValue() + 1);
						serverView.getJta().append("恭喜：挖到第" + resTotal.get(resPixMap.get(colorVal)).intValue()  + "个" + resPixMap.get(colorVal) + "\n");
					}

					pox = new int[2];
					pox[0]=i;
					pox[1]=j;
					i = searchMaxX;
					break;
				}
			}
		}
		return pox;
	}

	private int[] searchLeftRes(BufferedImage playImage,int[]startPox) {
		int[]pox = null;
			for (int i = startPox[0]-1; i >searchLeftMaxX; i--) {
				for (int j = startPox[1]+1; j < searchMaxY; j++) {
					int pix = playImage.getRGB(i, j);
					if (isContainColor(pix)) {
						Color color = new Color(pix);
						String colorVal = getRbg(color.getRed(),color.getGreen(),color.getBlue());
						if(resPixMap.containsKey(colorVal)&&resTotal.containsKey(resPixMap.get(colorVal))) {
							resTotal.put(resPixMap.get(colorVal), resTotal.get(resPixMap.get(colorVal)).intValue() + 1);
							serverView.getJta().append("恭喜：挖到第" + resTotal.get(resPixMap.get(colorVal)).intValue() + "个" + resPixMap.get(colorVal) + "\n");
						}

						pox = new int[2];
						pox[0]=i;
						pox[1]=j;
						i = searchLeftMaxX;

						break;
				}
			}
		}
		return pox;
	}

	private void searchCurPage(Robot robot,Rectangle screenRect) throws InterruptedException {
		BufferedImage playImage;
		double diffX;
		double diffY;
		int[]startPox = new int[2];
		startPox[0]= xPos;
		startPox[1]= yPos;
		while(true) {
			playImage = robot.createScreenCapture(screenRect);
			Color color = new Color(playImage.getRGB(confirmBtnPos[0], confirmBtnPos[1]));
			if (getRbg(color.getRed(), color.getGreen(), color.getBlue()).equals(getRbg(181, 201, 1))) {
				robot.mouseMove(confirmBtnPos[0], confirmBtnPos[1]);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
			}
			color = new Color(playImage.getRGB(yzmPos[0],yzmPos[1]));
			if(getRbg(color.getRed(),color.getGreen(),color.getBlue()).equals(getRbg(255,253,239))){
				CheckCode.selecCode(robot,yzmArea,selAPos,selBPos,selCPos,selDPos,confirmBtnPos);
			}
			if(isRight) {
				int[] pox = searchRes(playImage, startPox);
				if (pox == null) {
					break;
				}
				diffX = pox[0] - xPos;
				diffY = pox[1] - yPos;

				robot.mouseMove(pox[0], pox[1]);
				Thread.sleep(1000);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				Thread.sleep(8000);

				if(isCheckCzRes) {
					playImage = robot.createScreenCapture(screenRect);
					oldLeftRgbs = playImage.getRGB(czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], null, 0, czPos[2]-czPos[0]);
				}
				//移动到原位
				int moveXPos = (int) (xPos - (diffX-20) * 0.72);
				int moveYPos = (int) (yPos - diffY * 0.7);
				if (moveXPos < playMaxLeftPos[0]) {
					moveXPos = playMaxLeftPos[0];
				}
				if (moveYPos < playMaxLeftPos[1]) {
					moveYPos = playMaxLeftPos[1];
				}
				robot.mouseMove(moveXPos, moveYPos);
				Thread.sleep(1000);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				Thread.sleep(3000);
			}else{
				int[] pox = searchLeftRes(playImage, startPox);
				if (pox == null) {
					break;
				}
				diffX = xPos - pox[0] ;
				diffY = pox[1] - yPos  ;

				robot.mouseMove(pox[0], pox[1]);
				Thread.sleep(1000);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				Thread.sleep(8000);
				if(isCheckCzRes) {
					playImage = robot.createScreenCapture(screenRect);
					oldLeftRgbs = playImage.getRGB(czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], null, 0, czPos[2]-czPos[0]);
				}
				//移动到原位
				int moveXPos = (int) (xPos + (diffX-20) * 0.72);
				int moveYPos = (int) (yPos - diffY * 0.7);
				if (moveYPos < playMaxLeftPos[1]) {
					moveYPos = playMaxLeftPos[1];
				}
				robot.mouseMove(moveXPos, moveYPos);
				Thread.sleep(1000);
				robot.mousePress(KeyEvent.BUTTON1_MASK);
				robot.mouseRelease(KeyEvent.BUTTON1_MASK);
				Thread.sleep(3000);
			}
			if(isCheckCzRes) {
				if (isEqual(playImage, czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], oldLeftRgbs)) {//没挖矿
				} else {
					robot.delay(8000);
					playImage = robot.createScreenCapture(screenRect);
					oldLeftRgbs = playImage.getRGB(czPos[0], czPos[1], czPos[2]-czPos[0], czPos[3]-czPos[1], null, 0, czPos[2]-czPos[0]);
				}
			}
		}
	}

	private boolean suspend = false;

	private String control = ""; // 只是需要一个对象而已，这个对象没有实际意义

	public void setSuspend(boolean suspend) {
		if (!suspend) {
			synchronized (control) {
				control.notifyAll();
			}
		}
		this.suspend = suspend;
	}

	public boolean isSuspend() {
		return this.suspend;
	}

}