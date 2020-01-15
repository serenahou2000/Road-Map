import java.util.*;
import java.io.*;

public class RoadMap {

	private Graph graph;
	private Stack<Node> path;
	private int SCALE;
	private int START;
	private int END;
	private int WIDTH;
	private int LENGTH;
	private int INITIAL_BUDGET;
	private int TOLL;
	private int GAIN;

	/* Constructor for building a graph from the input file specified in the parameter;
	 * this graph represents the road map. If the input file does not exist, this 
	 * method should throw a MapException.
	 */
	public RoadMap(String inputFile) throws IOException, GraphException{
		path = new Stack<Node>();
		BufferedReader input;
		String line;
		Iterator<String> iterator;
		Vector<String> vector = new Vector<String>();
		input = new BufferedReader(new FileReader(inputFile));
		line = input.readLine();
		while(line != null){
			vector.add(line);
			line = input.readLine();
		}
		input.close();
		iterator = vector.iterator();
		SCALE = Integer.parseInt(iterator.next());
		START = Integer.parseInt(iterator.next());
		END = Integer.parseInt(iterator.next());
		WIDTH = Integer.parseInt(iterator.next());
		LENGTH = Integer.parseInt(iterator.next());
		INITIAL_BUDGET = Integer.parseInt(iterator.next());
		TOLL = Integer.parseInt(iterator.next());
		GAIN = Integer.parseInt(iterator.next());
		graph = new Graph(LENGTH*WIDTH);

		char[] charArray;
		char charCheck;

		int temp0;
		int temp1;

		for(int i = 0; i <= ((2*LENGTH) - 2); i++){
			charArray = iterator.next().toCharArray();

			for(int j = 0; j <= ((2*WIDTH) - 2); j++){
				charCheck = charArray[j];

				if(i%2==0){
					temp0 = (((WIDTH * i) / 2) + ((j - 1) / 2));
					temp1 = (((WIDTH * i) / 2) + ((j + 1) / 2));

					if(charCheck == '+'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 0);
					}
					else if(charCheck == 'F'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 0);
					}				
					else if(charCheck == 'C'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), -1);
					}
					else if(charCheck == 'T'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 1);
					}	
					else{
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), -2);					
					}
				}

				else{
					temp0 = (((WIDTH * (i - 1)) / 2) + (j / 2));
					temp1 = (((WIDTH * (i + 1)) / 2) + (j / 2));

					if(charCheck == '+'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 0);
					}
					else if(charCheck == 'F'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 0);
					}				
					else if(charCheck == 'C'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), -1);
					}
					else if(charCheck == 'T'){
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), 1);
					}	
					else{
						graph.insertEdge(graph.getNode(temp0), graph.getNode(temp1), -2);					
					}
				}
			}
		}

	}

	// Returns the graph representing the road map.
	public Graph getGraph() {
		return graph;
	}

	// Returns the starting node
	public int getStartingNode() {
		return START;
	}

	// Returns the destination node
	public int getDestinationNode() {
		return START;
	}

	// Returns the initial amount of money available to pay tolls.
	public int getInitialMoney() {
		return INITIAL_BUDGET;
	}

	/* Returns a Java Iterator containing the nodes of a path from the start node to 
	 * the destination node as specified above, if such a path exists. The amount 
	 * specified in initialMoney plus the money earned by passing through the reward 
	 * roads must be enough to pay for all the private roads. If the path does not 
	 * exist, this method returns the value null.
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		int money = initialMoney;

		try{
			Stack paths = new Stack(), sol = new Stack();
			Node currentNode, startingNode, endingNode;
			boolean added;
			Edge edge = null;
			
			startingNode = graph.getNode(start);
			endingNode = graph.getNode(destination);

			sol.push(startingNode);
			startingNode.setMark(true);

			while(!sol.isEmpty()){

				currentNode = (Node) sol.peek();

				if(currentNode == endingNode){
					return sol.iterator();
				}
				else{

					Iterator it = graph.incidentEdges(currentNode);
					added = false;

					while(it.hasNext()){
						edge = (Edge)it.next();
						currentNode = edge.secondEndpoint();

						if(!currentNode.getMark()){
							if(edge.getType() == 1 && money > 0){
								currentNode.setMark(true);
								paths.push(currentNode);
								added = true;
							}

							else if(edge.getType() == 0){
								currentNode.setMark(true);
								paths.push(currentNode);
								added = true;
							}
						}			
					}

					if(added == false){

						if(graph.getEdge((Node)sol.pop(), (Node)sol.peek()).getType() == 1)	{
							money++;
						}


						while( sol.size() > 0 && paths.size() > 0 && !graph.areAdjacent((Node)sol.peek(), (Node)paths.peek()) ){
							Node tmpNode = (Node)sol.pop();
							Edge tmpEdge = graph.getEdge(tmpNode, (Node)sol.peek());
							tmpNode.setMark(false);
							if(tmpEdge.getType() == 1)
								money++;
						}

						if(paths.size() == 0 || sol.size() == 0)
							return null;

					}
					if(graph.getEdge((Node)sol.peek(), (Node)paths.peek()).getType() == 1)
						money--;
					if(graph.getEdge((Node)sol.peek(), (Node)paths.peek()).getType() == -1)
						money++;

					sol.push(paths.pop());
				}

			}
			return null;

		}
		catch(Exception e){
			System.out.println("Error finding path");
			return null;	
		}

	}
}
