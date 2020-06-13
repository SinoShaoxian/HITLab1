package P3.src;

import java.util.LinkedList;

public class FriendshipGraph {
	private LinkedList<Person> friendshipGraph;

	public FriendshipGraph() {
		friendshipGraph = new LinkedList<>();
	}

	public void addVertex(Person person) {
		friendshipGraph.add(person);
	}
	
	public void addEdge(Person person1, Person person2) {
		person1.addFriend(person2);
		person2.addFriend(person1);
	}
	
	public int getDistance(Person person1, Person person2) {
		int distance = 0; 
		if (person1 == person2) {
			return distance;
		} else {
			LinkedList<Person> queue = new LinkedList<>();
			queue.add(person1);
			person1.visit();
			while (!queue.isEmpty()) {
				Person p = queue.remove();
				LinkedList<Person> friends = p.getFriends();				
				if (friends.contains(person2)) {
					distance += 1;
					return distance;
				} else {
					for (Person friend:friends) {
						if (!friend.getVisit()) {
							queue.add(friend);
							friend.visit();
						}
					}
					distance += 1;
				}
			}
			if (person2.getVisit() == false) {
				distance = -1;
			}
			for (Person person : friendshipGraph) {
				person.resetVisit();
			}
			return distance;
		}
	}
}