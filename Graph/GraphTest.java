import java.util.*;
import java.io.*;

class Edge
{
   int src, dest;
   int weight;
   public Edge (int l, int r, int w)
   {
       src = l;
       dest = r;
       weight = w;
   }
   public void display ()
   {
       System.out.println ("source: " + src + " destination: " + dest + " cost: " + weight);
   }
}

class Graph
{
    ArrayList<Integer> vertex;
    ArrayList<Edge> edge;
    public Graph (ArrayList<Integer> v, ArrayList<Edge> e)
    {
        vertex = v;
        edge = e;
    }
    public void display ()
    {
        for (int i = 0; i < edge.size (); i++)
        {
            edge.get(i).display ();
        }
    }
    public boolean isNeighbour (Integer u, Integer v)
    {
        for (Edge e : edge)
            if ((e.src == u && e.dest == v) || (e.src == v && e.dest == u))
                return true;
        return false;
    }
    public ArrayList<Integer> getNeighbourVertices (Integer u, ArrayList<Integer> Q)
    {
        ArrayList<Integer> temp = new ArrayList<> ();
        for (Integer v : Q)
            if (isNeighbour (u,v))
                temp.add (v);
        return temp;
    }
    public Integer findMin (ArrayList<Integer> Q, ArrayList<Integer> dist)
    {
        Integer small = dist.get (Q.get (0));
        Integer temp = Q.get (0);
        for (int i = 1; i < Q.size (); i++)
        {
            if (dist.get (Q.get (i)) < small)
            {
                small = dist.get (Q.get (i));
                temp = Q.get (i);
            }
        }
        return temp;
    }
    public Integer length (Integer u, Integer v)
    {
        Integer len = 0;
        for (Edge e : edge)
            if ((e.src == u && e.dest == v) || (e.src == v && e.dest == u))
                len = e.weight;
        return len;
    }
    public ArrayList<Integer> initDist (Integer src)
    {
        ArrayList<Integer> dist = new ArrayList<Integer> ();
        Integer[][] c = new Integer[vertex.size ()][vertex.size ()];
        for (int i = 0; i < vertex.size (); i++)
            for (int j = 0; j < vertex.size (); j++)
                c[i][j] = Integer.MAX_VALUE;
        Edge e;
        for (int i = 0; i < edge.size (); i++)
        {
            e = edge.get (i);
            c[e.src][e.dest] = e.weight;
            c[e.dest][e.src] = e.weight;
        }
        for (int i = 0; i < vertex.size (); i++)
            dist.add (i, c[src][i]);
        return dist;
    }
    ArrayList<Integer> prev = new ArrayList<Integer> ();
    public ArrayList<Integer> dijkstra (Integer src)
    {
        ArrayList<Integer> dist = initDist (src);
        ArrayList<Integer> Q = new ArrayList<Integer> ();
        ArrayList<Integer> neighbour;
        for (int i = 0; i < vertex.size (); i++)
        {
            prev.add (src);
            Q.add (vertex.get (i));  
        }
        dist.remove ((int)src);
        dist.add ((int)src, 0);
        Integer u, alt, v;
        while (!Q.isEmpty ())
        {
            u = findMin (Q, dist);
            for (int i = 0; i < Q.size (); i++)
                if (Q.get (i) == u)
                {
                    Q.remove (i);
                    break;
                }
            neighbour = getNeighbourVertices (u, Q);
            for (int i = 0; i < neighbour.size (); i++)
            {
                v = neighbour.get (i);
                alt = dist.get ((int)u) + length (u, v);
                if (alt < dist.get ((int)v))
                {
                    dist.remove ((int)v);
                    dist.add ((int)v, alt);
                    prev.remove ((int)v);
                    prev.add ((int)v, u);
                }
            }
        }
        return dist;
    }
    public void shortestPath (Integer src, Integer u, ArrayList<Integer> prev)
    {
        ArrayList<Integer> S = new ArrayList<Integer> ();
        while (prev.get ((int)u) != src)
        {
            S.add (0, u);
            u = prev.get ((int)u);
        }
        S.add (0,u);
        S.add (0,src);
        System.out.print("Source: ");
       for (Integer s : S)
           System.out.print (s + " ");
       System.out.println (" : Destination");
    }
}
public class GraphTest
{
    public static ArrayList<Integer> vertexSort (ArrayList<Integer> v)
    {
        Integer[] arr = new Integer[v.size()];
        arr = v.toArray (arr);
        Arrays.sort (arr);
        for (int i = 0; i < arr.length-1; i++)
            for (int j = i+1; j < arr.length; j++)
                if (arr[j] == arr[i])
                    arr[j] = -1;
        ArrayList<Integer> vertex = new ArrayList<> ();
        for (int i = 0; i < arr.length; i++)
            if (arr[i] != -1)
                vertex.add (arr[i]);
        return vertex;
    }
    public static void main (String[] args) throws IOException
    {
        String[] s;
        String s1;
        Graph g;
        ArrayList<Integer> vertex = new ArrayList<> ();
        ArrayList<Edge> edge = new ArrayList<> ();
        try
        (
            DataInputStream ip = new DataInputStream (new BufferedInputStream (new FileInputStream ("dijkstrain2.dat")));
        )
        {
            while ((s1 = ip.readLine ())!= null)
            {
                s = s1.split(",");
                edge.add (new Edge (Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
                vertex.add (Integer.parseInt (s[0]));
                vertex.add (Integer.parseInt (s[1]));
            }
            vertex = vertexSort (vertex);
        }
        g = new Graph (vertex,edge);
        g.display ();
        ArrayList<Integer> dist = g.dijkstra (vertex.get (0));
                System.out.println ("Consider vertex 0 to be source");
        System.out.print ("Cost of shortest path from source to every vertex: ");
        for (int i = 0; i < dist.size (); i++)
            System.out.print (dist.get (i) + " ");
        System.out.println ("\nShortest path from source to every vertex: ");
        for (int i = 0; i < vertex.size (); i++)
            g.shortestPath (vertex.get (0), vertex.get (i), g.prev);
    }
}
