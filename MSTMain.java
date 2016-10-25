import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This program handles finding the least costing path
 * between two nodes in a graph, also called a Minimum
 * Spanning Tree. In this case, the graph is quite simple
 * and the least costing path is easy to find. The graph
 * is:
 * 
 * 			  1
 *      (0)-------(1)
 *       |\ 5    / |
 *     4 |  \  /   | 2
 *       |  /  \ 6 |
 *       |/      \ |
 *      (3)-------(2)
 *           3 
 *          
 * And the resulting MST is:
 * 
 *            1
 *      (0)-------(1)
 *                 |
 *                 | 2
 *                 |
 *                 |
 *      (3)-------(2) 
 *            3
 *  
 * The numbers on the edges represent the arc weights and
 * the numbers in the parentheses are the nodes (numbered
 * in order)
 *          
 * @author Alex Thoennes
 * 
 * October 25, 2016
 *
 */

public class MSTMain 
{
	public static void main(String [] args) throws IOException
	{
		// name of the text file
		String name = "MST.txt";
		
		// create a new minimum spanning tree
		MinimumSpanningTree mst = new MinimumSpanningTree(name, countNodes(name));
		
		// find the shortest path between node 0 and node 3 
		mst.AStar(0, 3);
	}
	
	/**
	 * The way I set up the text file is that the very first line 
	 * contains all the nodes. So you just need to count the number
	 * of elements in that line and you have your node count.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static int countNodes(String file) throws IOException
	{
		// create new buffer
		BufferedReader buffer = new BufferedReader(new FileReader(file));

		// return the first line of the text file split
		// up into an array of individual character
		// character strings with the delimiter ""
		return buffer.readLine().split("").length;
	}
}
