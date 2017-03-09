import com.thinkaurelius.titan.core.TitanFactory
import com.tinkerpop.frames.FramedGraphFactory
import com.tinkerpop.frames.FramedGraph
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule
import com.tinkerpop.blueprints.Graph
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.Edge

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import java.io.File.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Set;
import java.util.HashSet;

TinkerGraph graph = new TinkerGraph();
// graph = TitanFactory.open("/Users/jepowell/graph-data")
// graph = TitanFactory.open("/usr/local/titan-server-0.4.2/bin/cassandra")
// graph = TitanFactory.open("cassandra")

graph.createKeyIndex("name",Vertex.class);

FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule()); 
FramedGraph framedGraph = factory.create(graph);

Author p = framedGraph.addVertex(null, Author.class);
p.setName("James")
p.setUri("123123")

Author p2 = framedGraph.addVertex(null, Author.class);
p2.setName("Matthew")
p2.setUri("456456")

// Edge e = framedGraph.addEdge(null, (Vertex) p, (Vertex) p2, "coauthors")
Edge e = framedGraph.addEdge(null, framedGraph.getVertex(1), framedGraph.getVertex(0), "coauthors")


for (Vertex vertex : framedGraph.getVertices()) {
  println(vertex)
  println(vertex.map())
}

for (Edge edge : framedGraph.getEdges()) {
  println(edge)
}

graph.saveGraphML("test.graphml")
