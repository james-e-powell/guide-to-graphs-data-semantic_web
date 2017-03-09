import com.thinkaurelius.titan.core.TitanFactory
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.oupls.jung.GraphJung

import edu.uci.ics.jung.graph.Graph
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.algorithms.scoring.*
import java.util.List

   g = TitanFactory.open("recommender")
   g.loadGraphSON("recommender.json")

   scottRead = g.V.has('name','Scott')[1].out('read').toList()
   Iterator<Vertex> fI = g.V.has('name','Scott')[1].out('friend').out('read')

   System.out.println("Recommendations for Scott ... ");
   for (Vertex book : fI) {
     if (!(scottRead.contains(book))) {
       System.out.println(book.title)
     }
   }

   GraphJung bookGraph = new GraphJung(g)
   System.out.println("Computing degree centrality...")

   DegreeScorer degreeRanker = new DegreeScorer(bookGraph);
   for (java.lang.Object nodeVal : bookGraph.getVertices()) {
     Double nodeScore = degreeRanker.getVertexScore(nodeVal)
     if (nodeVal.title) {
       System.out.println("Degree Centrality for\t" + nodeVal.title + "\t" + nodeScore);
     }
   }

