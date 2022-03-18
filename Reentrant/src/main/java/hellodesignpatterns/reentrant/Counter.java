package hellodesignpatterns.reentrant;

public class Counter {
	private int count = 0;

	public int incrementAndGet() {
		return count++;
	}
}
