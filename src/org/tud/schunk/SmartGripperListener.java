package org.tud.schunk;

import org.tud.schunk.gripper.listener.SchunkGripperGraspingStateListener.GraspingState;

public interface SmartGripperListener {
	void notify(GraspingState state);
}