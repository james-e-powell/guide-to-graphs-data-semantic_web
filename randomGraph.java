import java.net.*;
import java.io.*;
import org.xml.sax.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.generate.*;
import org.jgrapht.ext.*;


public final class randomGraph {


public static void main (String args[]) {
 RandomGraphGenerator<String, DefaultEdge> randomGenerator = new RandomGraphGenerator<String, DefaultEdge>(35, 95);      
 Graph<String, DefaultEdge> randomGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

 VertexFactory<String> vertexFactory = new VertexFactory<String>()
        {
            int n = 0;
            @Override
            public String createVertex()
            {
                String s = String.valueOf(n);
                n++;
                return s;
            }
        };

  randomGenerator.generateGraph(randomGraph, vertexFactory, null);
  System.out.println(randomGraph.toString());
  try {
    DOTExporter de = new DOTExporter();
    PrintWriter pf = new PrintWriter("random.dot");
    de.export(pf,randomGraph);

    GraphMLExporter gm = new GraphMLExporter();
    PrintWriter gf = new PrintWriter("random.graphml");
    gm.export(gf,randomGraph);
  } catch (Exception e) { System.out.println(e); }

  Graph<String, DefaultEdge> scaleFreeGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
  ScaleFreeGraphGenerator sfg = new ScaleFreeGraphGenerator(100);
  sfg.generateGraph(scaleFreeGraph, vertexFactory, null);
  System.out.println(scaleFreeGraph.toString());
  try {
    DOTExporter de = new DOTExporter();
    PrintWriter sf = new PrintWriter("sf.dot");
    de.export(sf,scaleFreeGraph);

    GraphMLExporter gm = new GraphMLExporter();
    PrintWriter gf = new PrintWriter("sf.graphml");
    gm.export(gf,scaleFreeGraph);

    MatrixExporter me = new MatrixExporter();
    PrintWriter mf = new PrintWriter("mf.txt");
    me.exportAdjacencyMatrix(mf,(UndirectedGraph)scaleFreeGraph);

  } catch (Exception e) { System.out.println(e); }
}

}
