package hellodesignpatterns.reentrant.demo;

import hellodesignpatterns.reentrant.ReentrantService;

public class TransactionReentrantService implements ReentrantService<TransactionData> {
	@Override
	public TransactionData bindThread(int reentrantLayer, int reentrantNo, TransactionData parentData) {
		TransactionData transactionData = new TransactionData();

		String uuid = "";

		if (parentData != null) {
			uuid = parentData.uuid;
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + transactionData.uuid + " reentrantLayer = " + reentrantLayer + " reentrantNo = " + reentrantNo + " parentUUID = " + uuid);

		return transactionData;
	}

	@Override
	public void restoreThread(int reentrantLayer, int reentrantNo, TransactionData transactionData, TransactionData parentData) {
		String uuid = "";

		if (parentData != null) {
			uuid = parentData.uuid;
		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  " + transactionData.uuid + " reentrantLayer = " + reentrantLayer + " reentrantNo = " + reentrantNo + " parentUUID = " + uuid);
	}
}
