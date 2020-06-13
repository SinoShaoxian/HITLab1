package P3.src;
import java.util.LinkedList;

public class Person {
	private String name;
	public LinkedList<Person> friends;
	private boolean visited;
	
	public Person(String name) {
		this.name = name;
		friends = new LinkedList<>();
		visited = false;
	}
	
	public String getName() {
		return name;
	}

	public void addFriend(Person person) {
		friends.add(person);
	}
		
	public LinkedList<Person> getFriends() {
		return friends;
	}
	
	public void visit() {
		visited = true;
	}
	
	public boolean getVisit() {
		return visited;
	}
	
	public void resetVisit() {
		visited = false;
	}
	
	public String toString(){
		return name;
	}
}