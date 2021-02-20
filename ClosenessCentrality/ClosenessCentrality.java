import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//Mirage Mohammad
//300080185

public class ClosenessCentrality {

    public static final double Dicconnect_Distance = 1891;	// this means if you do not have any connection between two
                                                           // you can use total number of nodes.
     private PriorityQueue<Node> pq; 
     private Set<Integer> settled;
     private int V;
     private int dist[];
     private Graph graph;
     private Map<Integer, Double>  centralityMap;
     
     
    /**
     * we do not have any static or predefined parameters in Closeness Centrality formula but
     * you can add based on your code if you need it (like size, number of edges)
     */

    ClosenessCentrality()
    {
        
    
    }

    
    public void dijkstra(int src) 
    { 
       // this.adj = adj; 
  
        for (int i = 1; i < V+1; i++) 
        {
        	dist[i] = Integer.MAX_VALUE; 
        }
            
  
        // Add source node to the priority queue 
        pq.add(new Node(src, 0));
  
        // Distance to the source is 0 
        dist[src] = 0; 
        while (settled.size() != V) 
        { 
  
            // remove the minimum distance node  
            // from the priority queue
        	if (!pq.isEmpty())
        	{
        		int u = pq.remove().node;
        		
        	
            
  
            // adding the node whose distance is 
            // finalized 
               settled.add(u); 
  
               e_Neighbours(u); 
            }
        } 
    } 
    
    
    private void e_Neighbours(int u)
    {
    	int edgeDistance = -1; 
        int newDistance = -1; 
        
        Integer uInteger = new Integer(u);
  
        // All the neighbors of v 
     //   for (int i = 0; i < adj.get(u).size(); i++) { 
          for(int i= 0; i< graph.getGraphEdges().get(uInteger).size();i++)
          {
        	  //Node v = adj.get(u).get(i); 
              int v = graph.getGraphEdges().get(uInteger).get(i);
        	  
            // If current node hasn't already been processed 
               if (!settled.contains(v)) 
               { 
                  //edgeDistance = v.cost;
            	    edgeDistance = 1;
	                newDistance = dist[u] + edgeDistance; 
	  
	                // If new distance is cheaper in cost 
	                // Mirage Commented
	                if (v<dist.length)
	                {
	                	if (newDistance < dist[v]) 
	    	                dist[v] = newDistance; 
	                	
	                	  pq.add(new Node(v, dist[v]));
	                	
	                }
	               
	  
	                // Add the current node to the queue 
	              
	                
	                
               } 
        } 
    	
	}


	/**
     * Computes the ClosenessCentrality (CC) of each node in a graph.
     *
     * @param graph the Graph to compute CC for
     * @return returns a Map<Integer, Double> mapping each node to its ClosenessCentrality CC
     *
     */

    public Map<Integer, Double> computeClosenessCentrality(Graph graph)
    {
        V = graph.nodes.size();
        this.graph = graph;
        dist = new int[V+1];
     //   settled = new HashSet<Integer>();   
        pq = new PriorityQueue<Node>(V, new Node());
        double closeness = 0.0;
        double total = 0;
        Map<Integer, List<Integer>> adj;
        centralityMap = new HashMap<Integer, Double>();
        Integer Intnd;
        Double Dclose;
        adj = graph.getGraphEdges();
        
       for(Integer nd: graph.nodes)
       {     
	       // int src = 1;	        
	       
    	   settled = new HashSet<Integer>();         
	        
	        dijkstra(nd.intValue());
	        
			/*
			 * System.out.println("The shorted path from node :" ); for (int i = 1; i <
			 * dist.length; i++) System.out.println(nd.intValue() + " to " + i + " is " +
			 * dist[i]);
			 */
	        
	        
	        
	        total = 0;
	        for (int i = 1; i < dist.length; i++) 
	        {
	        	total = total + dist[i];
	        }
	        
	        closeness = (double)(V-1)/total;
	        Dclose = new Double(closeness);
	        
	        
	        if(!centralityMap.containsKey(nd))
	        {
	        	 centralityMap.put(nd, Dclose);
	        }
	   
	       
        
       }     
        
        return centralityMap;
    }
    
    class Node implements Comparator<Node> { 
        public int node; 
        public int cost; 
      
        public Node() 
        { 
        } 
      
        public Node(int node, int cost) 
        { 
            this.node = node; 
            this.cost = cost; 
        } 
      
        @Override
        public int compare(Node node1, Node node2) 
        { 
            if (node1.cost < node2.cost) 
                return -1; 
            if (node1.cost > node2.cost) 
                return 1; 
            return 0; 
        } 
    } 
    
    
}
