/**
 * This class is a skeleton for each 
 * node in the graph
 * 
 * @author Alex Thoennes
 * 
 * October 25, 2016
 *
 */

public class Node 
{
	String name = "";
	
	// boolean used to determine if this 
	// node has been checked
	boolean processed;
	
	// the values in this array are the arc 
	// weights and the indices are the nodes 
	// that they are associated with
	int [] links;
	
	/**
	 * constructor for node class
	 * 
	 * @param numOfNodes
	 */
	public Node(int numOfNodes) 
	{
		processed = false;
		
		// each node can have a possible link to every node
		links = new int [numOfNodes];
		
		// set all links to 0 and change the value only when you read in the text file
		for (int i = 0; i < links.length; i ++)
		{
			links[i] = 0;
		}
	}
	
	public void setName(int name)
	{
		this.name = String.valueOf(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		/*String weights = "";
		
		for (int i = 0; i < links.length; i ++)
		{
			weights += this.links[i];
		}
		
		return weights;*/
		return getName();
	}
}
