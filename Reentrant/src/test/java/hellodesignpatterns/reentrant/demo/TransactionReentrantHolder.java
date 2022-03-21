package hellodesignpatterns.reentrant.demo;

import hellodesignpatterns.reentrant.ReentrantHolder;
import hellodesignpatterns.reentrant.ReentrantService;

public class TransactionReentrantHolder extends ReentrantHolder<TransactionData> {
	private static final ThreadLocal<ReentrantHolder<TransactionData>> reentrantHolder = new ThreadLocal<>();

	public TransactionReentrantHolder(ReentrantService<TransactionData> reentrantService) {
		super(reentrantHolder, reentrantService);
	}
}
