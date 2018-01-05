package org.zfz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.zfz.view.ServerView;

public class HandlerServerStart implements ActionListener {
	boolean isStart = false;
	private ServerView serverView;

	public HandlerServerStart(ServerView serverView) {
		super();
		this.serverView = serverView;
	}

	public HandlerServerStart() {
		super();
	}

	public void actionPerformed(ActionEvent e) {
		if(isStart){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			serverView.getServer().setSuspend(false);
		}else {
			isStart = true;
			serverView.getServer().start();
		}
	}

}
