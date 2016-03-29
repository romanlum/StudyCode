package at.swt6.util.timer;

import java.util.EventListener;

public interface TimerListener extends EventListener {

	void expired(TimerEvent event);
}
