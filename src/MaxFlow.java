// Java program for implementation of Ford Fulkerson algorithm
import java.util.*;
import java.lang.*;
import java.util.LinkedList;

class MaxFlow
{
    static int[][] graph;
    static int snodes;
    static int sink;
    static int source;
    /* Returns true if there is a path from source 's' to sink
    't' in residual graph. Also fills parent[] to store the
    path */
    boolean bfs(int rGraph[][], int s, int t, int parent[],int nodes)
    {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[nodes];
        for(int i=0; i<nodes; ++i)
            visited[i]=false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<nodes; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }
    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][],int nodes)
    {
        int u, v;
        int s =0;
        int t = nodes-1;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[nodes][nodes];

        for (u = 0; u < nodes; u++)
            for (v = 0; v < nodes; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[nodes];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent,nodes))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }
    void NewGraph(int nodes,Scanner input,MaxFlow m,int source,int sink){
        graph = new int[nodes][nodes];
        Boolean exit=false;
        if(nodes>0){
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    graph[i][j]=0;
                }
            }
        }
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                System.out.print("The capacity : ");
                int capacity = input.nextInt();
                if(source==0){
                    graph[node][lNode] = capacity;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = capacity;
                }
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------- GRAPH VIEW -----------------");
        if(nodes>0){
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    System.out.print(graph[i][j]+" ");
                }
                System.out.println();
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, nodes));

    }
    void AddLink(int nodes,Scanner input,MaxFlow m){
        Boolean exit=false;
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                System.out.print("The capacity : ");
                int capacity = input.nextInt();
                if(source==0){
                    graph[node][lNode] = capacity;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = capacity;
                }
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------- GRAPH VIEW -----------------");
        if(nodes>0){
           for (int i = 0; i < nodes; i++) {
               for (int j = 0; j < nodes; j++) {
                    System.out.print(graph[i][j]+" ");
               }
           System.out.println();
           }
        }
        System.out.println("------------------------------------------");
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, nodes));
    }
    void DeleteLink(int nodes,Scanner input,MaxFlow m){
        Boolean exit=false;
        while(exit==false){
            System.out.println("Do you want to exit (true/false) : ");
            exit = input.nextBoolean();
            if(exit==true){
                break;
            }else{
                System.out.print("Input node : ");
                int node = input.nextInt();
                System.out.print("The linked node : ");
                int lNode = input.nextInt();
                if(source==0){
                    graph[node][lNode] = 0;
                }else if(source==1) {
                    graph[node - 1][lNode - 1] = 0;
                }
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------- GRAPH VIEW -----------------");
        if(nodes>0){
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    System.out.print(graph[i][j]+" ");
                }
                System.out.println();
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("The maximum possible flow is " + m.fordFulkerson(graph, nodes));
    }

    // Driver program to test above functions
    public static void main (String[] args) throws java.lang.Exception
    {
        MaxFlow m = new MaxFlow();
        Scanner input = new Scanner(System.in);
        int choice=0;
        int nodes=0;
        while(choice!=5) {
            System.out.println("1. Input a new Graph\n2. Add a link\n3. Delete a link\n4. Loading other graphs\n5. exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    while(nodes<6) {
                        System.out.println("Input number of nodes");
                        nodes = input.nextInt();
                        if(nodes>=6){
                            System.out.print("Input the source : ");
                            source = input.nextInt();
                            System.out.print("Input the sink : ");
                            sink = input.nextInt();
                            snodes = nodes;
                            m.NewGraph(nodes, input, m, source, sink);
                        }else{
                            System.out.println("At least 6 nodes should be there ");
                            break;
                        }
                    }
                    break;
                case 2:
                    m.AddLink(snodes, input, m);
                    break;
                case 3:
                    m.DeleteLink(snodes,input,m);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}
