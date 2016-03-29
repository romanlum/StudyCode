package at.swt6.util.timer;

import java.util.EventObject;

public class TimerEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private final int noTicks;
	private final int tickCount;
	
	public TimerEvent(Object source, int noTicks, int tickCount) {
		super(source);
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
