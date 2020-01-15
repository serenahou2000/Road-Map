
public class Edge {
	private Node endpoint1, endpoint2;
	private int type;

	/*The constructor for the class. The first two parameters are the endpoints of 
	 * the edge. The last parameter is the type of the edge, which for this
	 * project can be either 0 (if the edge represents a public road), 1 (if the edge
	 * represents a private road), or -1 (if the edge represents a reward road).
	 */
	public Edge(Node u, Node v, int type){
		this.endpoint1 = u;
		this.endpoint2 = v;
		this.type = type;
	}
	
	// Returns the first endpoint of the edge
	public Node firstEndpoint() {
		return this.endpoint1;
	}
	
	// Returns the second endpoint of the edge
	public Node secondEndpoint() {
		return this.endpoint2;
	}
	
	// Returns the type of the edge
	public int getType() {
		return this.type;
	}
}
