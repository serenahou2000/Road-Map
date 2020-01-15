import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements GraphADT{
	
	private Node[] nodes;
    private ArrayList<Edge>[] edges;
    private int vertex;
	
	/* Creates a graph with n nodes and no edges. This is the constructor for the class. 
	 * The names of the nodes are 0, 1, . . . , n−1.
	 */
	public Graph(int n) {
		vertex = n;
        nodes = new Node[vertex];
        edges = new ArrayList[vertex];
        
        for(int i = 0; i < n; i++){
            nodes[i] = new Node(i);
        }
        
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<Edge>();
        }
	}
	
	/* Returns the node with the specified name. If no node with this name exists, the
	 * method should throw a GraphException.
	 */
	public Node getNode(int name) throws GraphException{
		if(name >= vertex){
            throw new GraphException("There is no node with this name");
        }
        return this.nodes[name];
	}
	
	/* Adds an edge of the given type connecting u and v. This method throws a GraphException 
	 * if either node does not exist or if in the graph there is already an edge connecting the 
	 * given nodes.
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException{
		if(u.getName() > vertex - 1 || v.getName() > vertex - 1){
            throw new GraphException("Could not find node(s)");
        }
		if(u.getName() < vertex){
            for(int i = 0; i < edges[u.getName()].size(); i++){
                if(edges[u.getName()].isEmpty()){
                    break;
                }
                if(edges[u.getName()].get(i).firstEndpoint() == u && edges[u.getName()].get(i).secondEndpoint() == v){// if edge already exists between nodes
                    throw new GraphException("Edge already exists");
                }
            }
        }
       //Add an edge between the nodes
        Edge temp1 = new Edge(u, v, edgeType);
        Edge temp2 = new Edge(v, u, edgeType);
        edges[u.getName()].add(temp1);
        edges[v.getName()].add(temp2);
	}
	
	/* Returns a Java Iterator storing all the edges incident on node u. It returns null if u 
	 * does not have any edges incident on it.
	 */
	public Iterator incidentEdges(Node u) throws GraphException{
		if(u.getName() > vertex - 1 || u.getName() < 0){
            throw new GraphException("Node does not exist");
        }
        return edges[u.getName()].iterator();
	}
	
	/* Returns the edge connecting nodes u and v. This method throws a GraphException if there 
	 * is no edge between u and v.
	 */
	public Edge getEdge(Node u, Node v) throws GraphException{
		if(u.getName() > vertex - 1 || v.getName() > vertex - 1 || u.getName() < 0 || v.getName() < 0){
            throw new GraphException("Could not find node(s)");
        }
        if(!(edges[u.getName()].isEmpty())){
            for(int i = 0; i < edges[u.getName()].size(); i++){
                if(edges[u.getName()].get(0).firstEndpoint() == null){
                    throw new GraphException("No edge exists between these nodes");
                }
                if(edges[u.getName()].get(i).firstEndpoint() == u && edges[u.getName()].get(i).secondEndpoint() == v){
                    return edges[u.getName()].get(i);
                }
            }
        }
        
        throw new GraphException("No edge exists between these nodes");
	}
	
	// Returns true if nodes u and v are adjacent; it returns false otherwise
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		if(u.getName() > vertex - 1 || v.getName() > vertex - 1 || u.getName() < 0 || v.getName() < 0){
            throw new GraphException("Could not find node(s)");
        }
        
        try{
            if(this.getEdge(u, v) != null){
                return true;
            }
        }catch(GraphException e){
            System.out.println(e);
        }
        return false;
	}
}