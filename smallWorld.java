import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.WattsStrogatzGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.stream.file.*;

public class smallWorld {

  public static void main (String args[]) {
    Graph graph = new SingleGraph("This is a small world!");
    Generator gen = new WattsStrogatzGenerator(20, 2, 0.5);
 
    gen.addSink(graph);
    gen.begin();
    while(gen.nextEvents()) {}
    gen.end();
    System.out.println(graph.toString());

    try { 
      FileSinkGraphML fsg = new FileSinkGraphML();
      graph.write(fsg, "small.graphml");
    } catch (Exception e) {}
  }
}
