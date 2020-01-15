
public class Node {
	
	private int name;
	private boolean mark;

	/* Creates a node with the given name. The name of a node is an integer value
	 * between 0 and n − 1, where n is the number of nodes in the graph.
	 */
	public Node(int name){
		this.name = name;
	}
	
	// Marks the node with the specified value, either true or false
	public void setMark(boolean mark){
		this.mark = mark;
	}
	
	// Returns the value with which the node has been marked
	public boolean getMark() {
		return this.mark;
	}
	
	// Returns the name of the vertex
	public int getName() {
		return this.name;
	}
}
