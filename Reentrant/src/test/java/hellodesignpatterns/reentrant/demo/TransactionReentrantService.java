package hellodesignpatterns.reentrant.demo;

import hellodesignpatterns.reentrant.ReentrantService;

public class TransactionReentrantService implements ReentrantService<TransactionData> {
	@Override
	public TransactionData bindThread(int reentrantLayer, int reentrantNo) {
		TransactionData transactionData = new TransactionData();

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + transactionData.uuid + " reentrantLayer = " + reentrantLayer + " reentrantNo = " + reentrantNo);

		return transactionData;
	}

	@Override
	public void restoreThread(TransactionData data) {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + data.uuid);
	}
}
