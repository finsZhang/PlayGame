package org.zfz.controller;

import org.zfz.view.ServerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandlerServerClose implements ActionListener {
	private ServerView serverView;
	public HandlerServerClose() {
		super();
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
