package org.tud.schunk;

import java.util.List;

import org.tud.log.Logger;
import org.tud.log.Logger.LogLevel;
import org.tud.schunk.gripper.SchunkGripper;
import org.tud.schunk.gripper.SchunkGripperAcknowledge.StatusCode;
import org.tud.schunk.gripper.SchunkGripperServerListener;
import org.tud.schunk.gripper.listener.SchunkGripperForceListener;
import org.tud.schunk.gripper.listener.SchunkGripperGraspingStateListener;
import org.tud.schunk.gripper.listener.SchunkGripperSpeedListener;
import org.tud.schunk.gripper.listener.SchunkGripperStatusListener;
import org.tud.schunk.gripper.listener.SchunkGripperSystemStateListener;
import org.tud.schunk.gripper.listener.SchunkGripperWidthListener;


public class GripperMain implements SchunkGripperWidthListener, SchunkGripperForceListener, SchunkGripperGraspingStateListener, SchunkGripperSpeedListener, SchunkGripperSystemStateListener, SchunkGripperServerListener, SchunkGripperStatusListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new GripperMain().start2();
		} catch (InterruptedException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
	}
	
	private void start2() throws InterruptedException {
		Logger.setLevel(LogLevel.TRACE);
		SchunkGripper g = new SchunkGripper();
		g.addServerStateListener(this);
		g.addWidthListener(this);
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		g.connect();
		
		for(int i = 0; i < 10; i++) {
			Thread.sleep(1500);
			g.open(i*10, 200);
			Thread.sleep(1500);
		}
		for(int i = 0; i < 10; i++) {
			Thread.sleep(1500);
			g.open(i*10, 200);
			Thread.sleep(1500);
		}
		g.disconnect();
	}
	private void start() throws InterruptedException {
		Logger.setLevel(LogLevel.INFO);
		SchunkGripper g = new SchunkGripper();
		g.addServerStateListener(this);
		SmartGripper sg = new SmartGripper(g);
		Thread.sleep(5000);

		System.out.println(g.getSystemState());
		g.addWidthListener(this);
		//g.addForceListener(this);
		g.addGraspingListener(this);
		//g.addSpeedListener(this);
		//g.addSystemStateListener(this);

		/*
		System.out.println("Normal Gripper Test");
		g.open(100, 400);
		
		for(int i = 0; i < 10; i++) {
			Thread.sleep(1500);
			g.open(i*10, 200);
			Thread.sleep(1500);
		}
		Thread.sleep(5000);
		*/
		System.out.println("Smart Gripper Test");
		
		for(double i = 1.0; i >= 0.0; i-=0.001) {
			Thread.sleep(10);
			//System.out.println("widthRelative:"+i);
			sg.work(i);
			Thread.sleep(10);
		}
		System.out.println("AUFFAHREN");
		for(double i = 0.1; i <= 1.0; i+=0.001) {
			Thread.sleep(10);
			//System.out.println("widthRelative:"+i);
			sg.work(i);
			Thread.sleep(10);
		}
		
		for(double i = 1.0; i >= 0.0; i-=0.001) {
			Thread.sleep(10);
			//System.out.println("widthRelative:"+i);
			sg.work(i);
			Thread.sleep(10);
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Automatisch generierter Erfassungsblock
			e1.printStackTrace();
		}
		g.disconnect();
		
	}

	@Override
	public void notifyWidth(float width) {
		System.out.println("WIDTH:"+width);
		
	}

	@Override
	public void notifySystemState(List<SystemState> states) {
		for(SystemState s:states) {
			System.out.println("SYSTEM_STATE:"+s.toString());
		}
		
	}

	@Override
	public void notifySpeed(float speed) {
		//System.out.println("SPEED:"+speed);
		
	}

	@Override
	public void notifyGraspring(GraspingState state) {
		System.out.println("GraspingState:"+state.toString());
		
	}

	@Override
	public void notifyForce(float force) {
		System.out.println("FORCE:"+force);
		
	}

	@Override
	public void notify(SchunkGripperServerState state) {
		System.out.println("socket state "+state);
		
	}

	@Override
	public void notifyStatus(StatusCode statusCode) {
		//System.out.println("STATUSCODE: "+statusCode);
		
	}
}
