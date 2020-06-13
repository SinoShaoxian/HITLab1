package P3.test;

import static org.junit.Assert.*;

import java.awt.font.GraphicAttribute;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import P3.src.FriendshipGraph;
import P3.src.Person;

public class FriendshipGraphTest {
	
	Person A = new Person("A");
	Person B = new Person("B");
	Person C = new Person("C");
	Person D = new Person("D");
	Person E = new Person("E"); 
	Person F = new Person("F");
	FriendshipGraph Graph = new FriendshipGraph();
	
	@Test
	public void addEdge() {
		Graph.addEdge(A, E);
		Graph.addEdge(E, A);
		Graph.addEdge(D, C);
		Graph.addEdge(C, D);
		Graph.addEdge(A, D);
		Graph.addEdge(D, A);
		Graph.addEdge(E, B);
		Graph.addEdge(B, E);
		Graph.addEdge(C, B);
		Graph.addEdge(B, C);
		assertEquals(E, A.friends.get(0));
	}
	@Test
	public void getDistance()
	{
		Graph.addVertex(A);
		Graph.addVertex(B);
		Graph.addVertex(C);
		Graph.addVertex(D);
		Graph.addVertex(E);
	
		Graph.addEdge(A, E);
		Graph.addEdge(E, A);
		Graph.addEdge(D, C);
		Graph.addEdge(C, D);
		Graph.addEdge(A, D);
		Graph.addEdge(D, A);
		Graph.addEdge(E, B);
		Graph.addEdge(B, E);
		Graph.addEdge(C, B);
		Graph.addEdge(B, C);
		assertEquals(1, Graph.getDistance(A, D));
		assertEquals(2, Graph.getDistance(D, B));
		assertEquals(0, Graph.getDistance(D, D));
	}

}
