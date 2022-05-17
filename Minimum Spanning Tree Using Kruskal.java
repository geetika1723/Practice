// { Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            Solution ob = new Solution();
            
            System.out.println(ob.spanningTree(V, adj));
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution
{
    //Function to find sum of weights of edges of the Minimum Spanning Tree.
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) 
    {
        // Add your code here
        int n=adj.size();
        Edge edge[]=new Edge[V*V];
        for(int i=0;i<edge.length;i++)
        edge[i]=new Edge();
        int k=0;
        for(int i=0;i<adj.size();i++)
        {
            for(int j=0;j<adj.get(i).size();j++)
            {
                int x=i;
                int y=adj.get(i).get(j).get(0);
                edge[k++]=new Edge(x,y,adj.get(i).get(j).get(1));
            }
        }
        int ans=kruskal(edge,V);
        return ans;
    }
    public static int kruskal(Edge arr[],int V)
    {
        Arrays.sort(arr);
        int v=arr.length;
        int parent[]=new int[V];
        int rank[]=new int[V];
        for(int i=0;i<V;i++)
        {
            parent[i]=i;
            rank[i]=0;
        }
        int res=0;
        for(int i=0,s=0;s<V-1;i++)
        {
            Edge e=arr[i];
            int x=find(e.src,parent,rank);
            int y=find(e.des,parent,rank);
            if(x!=y)
            {
                res+=e.wt;
                union(x,y,parent,rank);
                s++;
            }
        }
        return res;
        
    }
    public static int find(int i,int parent[],int rank[])
    {
        if(parent[i]==i)
        return i;
        return  find(parent[i],parent,rank);
    }
    public static void union(int x,int y,int parent[],int rank[])
    {
        int x_rep=find(x,parent,rank),y_rep=find(y,parent,rank);
        if(x_rep==y_rep)
        return;
        if(rank[x_rep]<rank[y_rep])
        {
            parent[x_rep]=y_rep;
        }
        else if(rank[x_rep]>rank[y_rep])
            parent[y_rep]=x_rep;
        else
        {
            parent[y_rep]=x_rep;
            rank[x_rep]++;
        }
    }
}
class Edge implements Comparable<Edge>
{
    int src,des,wt;
    Edge()
    {
        src=Integer.MAX_VALUE;
        des=Integer.MAX_VALUE;
        wt=Integer.MAX_VALUE;
    }
    
    Edge(int s,int d,int w)
    {
        src=s;
        des=d;
        wt=w;
    }
    public int compareTo(Edge e)
    {
        return this.wt-e.wt;
    }
}
