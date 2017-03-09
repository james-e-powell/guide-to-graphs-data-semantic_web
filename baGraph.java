import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.stream.file.*;

public class baGraph {

  public static void main (String args[]) {

    Graph graph = new SingleGraph("Barabàsi-Albert");
     // Between 1 and 3 new links per node added.
     Generator gen = new BarabasiAlbertGenerator(3);
     // Generate 100 nodes:
     gen.addSink(graph); 
     gen.begin();
     for(int i=0; i<100; i++) {
                gen.nextEvents();
     }
     gen.end();

    System.out.println(graph.toString());

    try { 
      FileSinkGraphML fsg = new FileSinkGraphML();
      graph.write(fsg, "ab.graphml");
      fsg.flush();
    } catch (Exception e) {}
  }
}
