import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSI2110 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      //  String edgesFilename = "email-dnc.edges";
    	String edgesFilename = "small_graph_2.edges";
        Graph   graph = readGraph(edgesFilename);
        List<Integer> nodes = graph.getGraphNodes();
        Map<Integer, List<Integer>> edges = graph.getGraphEdges();

        System.out.println("Number of nodes in the Graph: " + nodes.size());

        for(Integer node : nodes) {
            System.out.println("Node number: " + node);
            System.out.print("Adjacent Nodes: ");
            if (edges.containsKey(node)) {
                for(Integer edge : edges.get(node)) {
                    System.out.print(edge + " ");
                }
            }
            System.out.println();
            System.out.println("------------------------------------");
        }

    }

    public static Graph readGraph(String edgesFilename) throws FileNotFoundException, IOException {
        System.getProperty("user.dir");
        URL edgesPath = CSI2110.class.getResource(edgesFilename);
        BufferedReader csvReader = new BufferedReader(new FileReader(edgesPath.getFile()));
        String row;
        List<Integer>               nodes = new ArrayList<Integer>();
        Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();

        boolean first = false;
        while ((row = csvReader.readLine()) != null) {
            if (!first) {
                first = true;
                continue;
            }

            String[] data = row.split(",");

            Integer u = Integer.parseInt(data[0]);
            Integer v = Integer.parseInt(data[1]);

            if (!nodes.contains(u)) {
                nodes.add(u);
            }
            if (!nodes.contains(v)) {
                nodes.add(v);
            }

            if (!edges.containsKey(u)) {
                // Create a new list of adjacent nodes for the new node u
                List<Integer> l = new ArrayList<Integer>();
                l.add(v);
                edges.put(u, l);
            } else {
                // don't add duplicate edge between u and v
                if (!edges.get(u).contains(v))
                    edges.get(u).add(v);
            }
        }

        for (Integer node : nodes) {
            if (!edges.containsKey(node)) {
                edges.put(node, new ArrayList<Integer>());
            }
        }

        csvReader.close();
        return new Graph(nodes, edges);
    }
}
