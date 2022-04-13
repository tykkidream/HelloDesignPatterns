package hello.designpatterns.batch.list;

public class Person {
	private Long id;

	private String name;

	public Person() {

	}

	public Person(Long id, String name) {
		setId(id);
		setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null) {
			this.id = id;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}
}