import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * This class handles reading the graph in from
 * the text file as well as the A* (A-star)
 * algorithm and displaying the graph.
 * 
 * @author Alex Thoennes
 * 
 * October 25, 2016
 *
 */

public class MinimumSpanningTree 
{
	// array containing all nodes in the graph
	Node [] nodes;

	/**
	 * This constructor largely deals with reading the text file
	 * and then translating it into my graph. The text file is 
	 * laid out as such:
	 * 
	 * startNode	weight     endNode
	 * startNode    weight	   endNode
	 * startNode    weight     endNode
	 * ...
	 * 
	 * @param fileName
	 * @param numOfNodes
	 * @throws IOException
	 */
	public MinimumSpanningTree(String fileName, int numOfNodes) throws IOException
	{
		// create the nodes array with a size of 
		// however many nodes there are in the graph
		nodes = new Node [numOfNodes];

		// fill the nodes array with new instances of the node class
		for (int i = 0; i < numOfNodes; i ++)
		{
			nodes[i] = new Node(numOfNodes);
		}

		// create a new buffer to read in the lines in the text file
		BufferedReader buffer = new BufferedReader(new FileReader(fileName));
		
		// skip the first line because it
		// contains only the nodes
		buffer.readLine();

		while (buffer.ready())
		{
			// read in the 2nd line
			String line = buffer.readLine();

			// then set up a tokenizer that will separate the string line
			// into the required parts to assign the arc
			StringTokenizer tokens = new StringTokenizer(line, "\t\n");

			if (tokens.hasMoreTokens())
			{	
				// node you start at
				int startNode = Integer.valueOf(tokens.nextToken());
				
				// weight between the two nodes
				int weight = Integer.valueOf(tokens.nextToken());
				
				// node you end at
				int endNode = Integer.valueOf(tokens.nextToken());
				
				// Indices of the link array are the nodes you link to
				nodes[startNode].links[endNode] = weight;
			}
		}

		// close the buffered reader
		buffer.close();
		
		// set each node's name
		establishNames();
		
		// show the graph in it's initial state
		displayGraph(nodes);
		
	}
	
	/**
	 * AStar is an improvement upon Dijkstra's Algorithm. Dijkstra's 
	 * finds the shortest path to all nodes from the start node 
	 * where as this algorithm finds the shortest path between 
	 * two specified nodes
	 * 
	 * @param startNode
	 * @param endNode
	 */
	public void AStar(int startNode, int endNode)
	{
		// node you start traversing at
		Node start = nodes[startNode];
		
		// node you want to find the least expensive
		// path to
		Node end = nodes[endNode];
		
		// also called a working stack, this is a way
		// to know what nodes you have accepted for your
		// answer
		Stack<Node> nodeStack = new Stack<Node>();
		
		// always consider the first node to be in your answer
		nodeStack.push(start);

		// continue as long as you have not reached the end node
		while (start != end)
		{
			// if you have not visited the least
			// expensive node
			if (!nextNode(start).processed)
			{
				// store the next node in a temp variable
				Node temp = nextNode(start);
				
				// push that temp to the stack
				nodeStack.push(temp);
				
				// now set your current node to that temp node
				start = temp;
			}
		}
		
		// minimum spanning tree
		System.out.println("Minimum Spanning Tree (order of nodes): ");
		System.out.println(Arrays.toString(nodeStack.toArray()));
	}
	
	/**
	 * This method takes in the current node you're at
	 * and returns the next node you need to go to based
	 * on the weights coming from your current node
	 * 
	 * @param n
	 * @return nextNode
	 */
	private Node nextNode(Node n)
	{
		// use this boolean to make sure we don't get stuck going back and forth
		// other wise we would continuously travel between node 1 and node 2
		n.processed = true;
		
		// Initialize leastWeight to something that nearly any number
		// in the graph is less than
		int leastWeight = (int) Float.POSITIVE_INFINITY;
		
		// use this to keep track of where the
		// least weighted node is
		int index = 0;
		
		// go through the current nodes links and find the shortest one
		for (int i = 0; i < n.links.length; i ++)
		{
			// compare leastWeight to current weight 
			// you are looking at
			if (n.links[i] < leastWeight && n.links[i] != 0)
			{
				// make sure we haven't visited this node
				if (!nodes[i].processed)
				{
					// if all the conditions were met, we 
					// have a new least weight
					leastWeight = n.links[i];
					
					// index of the node with the least weight
					index = i;
				}
			}
		}
		
		// return the next node
		return nodes[index];
	}
	
	/**
	 * Runs through the nodes array and sets each node name to its
	 * respective index. 
	 * 
	 * ex. nodes = {n, n1, n2, n3, n4, n5};
	 *     
	 *     Here node n's name is 0
	 *     		node n1's name is 1
	 *     		node n2's name is 2
	 *     		node n3's name is 3
	 *     		node n4's name is 4
	 *     		node n5's name is 5
	 * 
	 */
	private void establishNames()
	{
		// iterate over every node
		for (int i = 0; i < nodes.length; i ++)
		{
			// set the current node's name to its index
			nodes[i].setName(i);
		}
	}
	
	/**
	 * Displays the nodes with their names, 
	 * what they link to, and their weights
	 * 
	 * @param nodes
	 */
	private void displayGraph(Node [] nodes)
	{
		for (int i = 0; i < nodes.length; i ++)
		{
			for (int j = 0; j < nodes[i].links.length; j ++)
			{
				System.out.println("Node " + i + " links to node " + j + " with a weight of " + nodes[i].links[j]);
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
