package hellodesignpatterns.reentrant.demo;

public class Main {
	public static final TransactionReentrantService service = new TransactionReentrantService();

	public static void main(String[] args) {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务！");

			businessA();
			businessB();
			businessC();

			return null;
		});

	}

	public static void businessA() {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务 A！");

			businessA_A();
		});
	}


	public static void businessA_A() {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务 A_A！");
		});
	}

	public static void businessB() {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务 B！");

			businessB_B();
		});
	}

	public static void businessB_B() {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务 B_B！");
		});
	}

	public static void businessC() {
		TransactionReentrantHolder holder = new TransactionReentrantHolder(service);

		holder.process(() -> {
			System.out.println("执行业务 C！");
		});
	}
}
