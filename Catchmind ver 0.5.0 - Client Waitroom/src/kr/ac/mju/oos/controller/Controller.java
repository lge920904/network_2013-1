package kr.ac.mju.oos.controller;

import kr.ac.mju.oos.uility.Tool;

public abstract class Controller {
	protected FrontController frontController;
	protected Tool tool;
	protected boolean initFlag = false;

	public abstract boolean sendData(String sendString);

	public void init(FrontController frontController, Tool tool) {
		this.frontController = frontController;
		this.tool = tool;
		this.initFlag = true;
	}
}
