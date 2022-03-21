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

		StringBuilder log = new StringBuilder(">>>>>>>>>>>>>>>>>>>>");

		for (int i = 0; i < reentrantLayer; i++) {
			log.append(">>>>>>>>>>>>>>>>>>>>");
		}

		log.append(" ");
		log.append(transactionData.uuid);
		log.append(" reentrantLayer = ");
		log.append(reentrantLayer);
		log.append(" reentrantNo = ");
		log.append(reentrantNo);
		log.append(" parentUUID = ");
		log.append(uuid);

		System.out.println(log.toString());

		return transactionData;
	}

	@Override
	public void restoreThread(int reentrantLayer, int reentrantNo, TransactionData transactionData, TransactionData parentData) {
		String uuid = "";

		if (parentData != null) {
			uuid = parentData.uuid;
		}

		StringBuilder log = new StringBuilder("<<<<<<<<<<<<<<<<<<<<");

		for (int i = 0; i < reentrantLayer; i++) {
			log.append("<<<<<<<<<<<<<<<<<<<<");
		}

		log.append(" ");
		log.append(transactionData.uuid);
		log.append(" reentrantLayer = ");
		log.append(reentrantLayer);
		log.append(" reentrantNo = ");
		log.append(reentrantNo);
		log.append(" parentUUID = ");
		log.append(uuid);

		System.out.println(log.toString());
	}
}
