package hellodesignpatterns.reentrant;

public interface ReentrantService<T> {
	T bindThread(int layer, int no);

	void restoreThread(T data);
}
