package hellodesignpatterns.reentrant;

public interface ReentrantService<T> {
	T bindThread(int layer, int no, T parentData);

	void restoreThread(int layer, int no, T data, T parentData);
}
