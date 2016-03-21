package at.swt6.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class Timer {
	
	private int noTicks = 1;
	
	private final AtomicInteger tickInterval = new AtomicInteger(1000);
	private final AtomicInteger tickCount = new AtomicInteger(0);
	private final AtomicBoolean stopTimer = new AtomicBoolean(false);
	private final AtomicReference<Thread> tickerThread = 
			new AtomicReference<>(null);
	
	private final Vector<TimerListener> listener= new Vector<>();
	
	private final PropertyChangeSupport propertyChangeSupport =
			new PropertyChangeSupport(this);
	
	public boolean isRunning(){
		return tickerThread.get() != null;
	}
	
	public void stop() {
		stopTimer.set(true);
	}
	
	public void reset() {
		if(isRunning()) {
			throw new IllegalStateException("Cannot reset while timer is running");
		}
		
		tickCount.set(0);
	}
	
	public int getTickCount() {
		return tickCount.get();
	}
	
	public int getInterval() {
		return tickInterval.get();
	}
	
	public void setInterval(int interval) {
		java.util.logging.Logger.getLogger("test").info("set intervale " +interval);
		int oldValue = tickInterval.get();
		if(interval != oldValue) {
			tickInterval.set(interval);
			firePropertyChange("interval", oldValue, interval);
		}
	}
	
	public int getNoTicks() {
		return noTicks;
	}
	
	public void setNoTicks(int noTicks) {
		int oldValue = this.noTicks;
		if(oldValue != noTicks )
		{
			this.noTicks = noTicks;
			firePropertyChange("noTicks", oldValue, noTicks);
		}


	}
	
	public void addTimerListener(TimerListener listener) {
		this.listener.add(listener);
	}
	
	public void removeTimerListener(TimerListener listener) {
		this.listener.remove(listener);
	}
	
	public void fireEvent(TimerEvent e) {
		for(TimerListener l: (Vector<TimerListener>)listener.clone()) { //clone wegen race condition
			l.expired(e);
		}
	}
	
	public void start() {
		
		if(isRunning()) {
			throw new IllegalStateException("cannot start: timer already running");
		}
		
		final int ticks=noTicks;
		tickerThread.set(new Thread(()->{
			tickCount.set(0);
			while(!stopTimer.get() && tickCount.get() < ticks) {
				try {
					Thread.sleep(tickInterval.get());
				} catch (InterruptedException e) {
				}

				if(!stopTimer.get()) {
					fireEvent(new TimerEvent(this,tickCount.incrementAndGet(),ticks));
				}
				
			}
			stopTimer.set(false);
			tickerThread.set(null);
		}));
		
		tickerThread.get().start();
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		this.propertyChangeSupport.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l) {
		this.propertyChangeSupport.removePropertyChangeListener(l);
	}
	
	private void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		
		this.propertyChangeSupport.firePropertyChange(propertyName,oldValue,newValue);
		
	}
	
}
