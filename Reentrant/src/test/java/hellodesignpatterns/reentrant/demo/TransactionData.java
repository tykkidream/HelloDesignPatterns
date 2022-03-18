package hellodesignpatterns.reentrant.demo;

import java.util.UUID;

public class TransactionData {
	public String uuid = UUID.randomUUID().toString().replace("-", "");
}
