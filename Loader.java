import edu.uci.ics.jung.io.*;
import edu.uci.ics.jung.io.graphml.*;
import java.io.*;
import java.util.*;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.algorithms.scoring.*;

class Vertex{
        // int id;
        String id;
        String type;
        String value;
}

class Edge{
        // int id ;
        String id ;
        String type;
        String value;
}

public class Loader{
        static String src = "rg.graphml";

        public static void main(String[] args) {

             src = args[0];
             try {
                Reader reader = new FileReader(src );
                Transformer<NodeMetadata, Vertex> vtrans = new Transformer<NodeMetadata,Vertex>(){
                        public Vertex transform(NodeMetadata nmd ){
                                Vertex v = new Vertex() ;
                                v.type = nmd.getProperty("type");
                                v.value = nmd.getProperty("value");
                                // v.id = Integer.valueOf( nmd.getId() );
                                v.id =  nmd.getId();
                                return v;
                        }
                };
                Transformer<EdgeMetadata, Edge> etrans = new Transformer<EdgeMetadata,Edge>(){
                        public Edge transform( EdgeMetadata emd ){
                                Edge e = new Edge() ;
                                e.type = emd.getProperty("type");
                                e.value = emd.getProperty("value");
                                // e.id = Integer.valueOf( emd.getId() );
                                e.id = emd.getId();
                                return e;
                        }
                };
                Transformer<HyperEdgeMetadata, Edge> hetrans = new Transformer<HyperEdgeMetadata,Edge>(){

                        public Edge transform( HyperEdgeMetadata emd ){
                                Edge e = new Edge() ;
                                e.type = emd.getProperty("type");
                                e.value = emd.getProperty("value");
                                // e.id = Integer.valueOf( emd.getId() );
                                e.id = emd.getId();
                                return e;
                        }
                };
                Transformer< GraphMetadata , UndirectedSparseGraph<Vertex, Edge>> gtrans = new Transformer<GraphMetadata,UndirectedSparseGraph<Vertex, Edge>>(){
                        public UndirectedSparseGraph<Vertex,Edge> transform( GraphMetadata gmd ){
                                return new UndirectedSparseGraph<Vertex,Edge>();
                        }
                };



                GraphMLReader2< UndirectedSparseGraph<Vertex,Edge> , Vertex , Edge> gmlr =
                        new GraphMLReader2< UndirectedSparseGraph<Vertex,Edge> ,Vertex, Edge>(
                                        reader,
                                        gtrans,
                                        vtrans,
                                        etrans,
                                        hetrans);
                UndirectedSparseGraph<Vertex,Edge> graph = gmlr.readGraph();

        BetweennessCentrality ranker = new BetweennessCentrality((Graph<Vertex, Edge>) graph);
        for (java.lang.Object nodeVal : graph.getVertices()) {
           Double nodeScore = ranker.getVertexScore(nodeVal);
           Vertex currentNode = (Vertex)nodeVal;
           System.out.println("BetweennessCentrality for\t" + currentNode.id + "("+ currentNode.value + ") " + "\t" + nodeScore);
        }

                // return ;
          } catch (Exception e) { System.out.println(e); }

        }
}
