import java.net.*;
import java.io.*;
import org.xml.sax.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.generate.*;
import org.jgrapht.ext.*;


public final class regularGraph {


public static void main (String args[]) {
 CompleteGraphGenerator cg = new CompleteGraphGenerator(10);

 Graph<String, DefaultEdge> regularGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

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

  cg.generateGraph(regularGraph, vertexFactory, null);
  System.out.println(regularGraph.toString());
  try {
    DOTExporter de = new DOTExporter();
    PrintWriter pf = new PrintWriter("complete.dot");
    de.export(pf,regularGraph);

    GraphMLExporter gm = new GraphMLExporter();
    PrintWriter gf = new PrintWriter("complete.graphml");
    gm.export(gf,regularGraph);
  } catch (Exception e) { System.out.println(e); }

}

}
