package org.zfz.controller;

import org.zfz.view.ServerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandlerServerPause implements ActionListener {
	private ServerView serverView;
	public HandlerServerPause(ServerView serverView) {
		super();
		this.serverView = serverView;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		serverView.getServer().setSuspend(true);
	}
}
