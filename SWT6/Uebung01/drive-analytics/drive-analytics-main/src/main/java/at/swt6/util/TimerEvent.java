package at.swt6.util;

import java.util.EventObject;

public class TimerEvent extends EventObject {

	private final int noTicks;
	private final int tickCount;
	
	public TimerEvent(Object arg0, int noTicks, int tickCount) {
		super(arg0);
		this.noTicks = noTicks;
		this.tickCount = tickCount;
		
	}
	
	public int getNoTicks() {
		return noTicks;
	}
	
	public int getTickCount() {
		return tickCount;
	}
	
	
	
}
