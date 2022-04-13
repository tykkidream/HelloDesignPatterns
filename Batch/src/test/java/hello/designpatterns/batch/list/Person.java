package hello.designpatterns.batch.list;

public class Person {
	private Long id;

	private String name;

	private Person relation;

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

	public Person getRelation() {
		return relation;
	}

	public void setRelation(Person relation) {
		if (relation != null) {
			this.relation = relation;
		}
	}
}