import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.BananaTreeGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.stream.file.*;

public class treeGraph {

  public static void main (String args[]) {
    Graph graph = new SingleGraph("This is a tree!");
    Generator gen = new BananaTreeGenerator();
 
    gen.addSink(graph);
    gen.begin();
    int count = 0;
    while (count < 20) {
      gen.nextEvents();
      count++; 
    }
    gen.end();
    System.out.println(graph.toString());

    try { 
      FileSinkGraphML fsg = new FileSinkGraphML();
      graph.write(fsg, "tree.graphml");
      // graph.write("tree.dgs");
      fsg.flush();
    } catch (Exception e) {}
  }
}
