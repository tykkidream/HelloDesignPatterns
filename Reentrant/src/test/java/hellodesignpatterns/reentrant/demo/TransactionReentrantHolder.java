package hellodesignpatterns.reentrant.demo;

import hellodesignpatterns.reentrant.ReentrantHolder;
import hellodesignpatterns.reentrant.ReentrantService;

public class TransactionReentrantHolder extends ReentrantHolder {
	private static final ThreadLocal<ReentrantHolder> reentrantHolder = new ThreadLocal<>();

	public TransactionReentrantHolder(ReentrantService reentrantService) {
		super(reentrantHolder, reentrantService);
	}
}
