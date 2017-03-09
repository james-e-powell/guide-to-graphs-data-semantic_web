import com.thinkaurelius.titan.core.TitanFactory
import com.tinkerpop.blueprints.Direction
import com.tinkerpop.blueprints.GraphQuery
import com.tinkerpop.blueprints.Vertex

import edu.uci.ics.jung.algorithms.util.IterativeProcess
import edu.uci.ics.jung.graph.SparseMultigraph
import edu.uci.ics.jung.graph.Graph
import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality
import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.algorithms.scoring.*
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;

final Graph<String,String> affilGraph = new SparseMultigraph<String, String>();

List<String> edges = []
Set<String> nodes = []
Map<String, Integer> nodeScores = new HashMap<String, Integer>();
Map<String, String> nodeLabels = new HashMap<String, String>();

g = TitanFactory.open("/nfsmnt/james/graph-data/grid")

    GraphQuery query = g.query()
    query = query.has("Concept")

    // g.V.has("Title").outE.inV.map()
    final Iterator<Vertex> itty = query.vertices()._().iterator()

    Vertex v
    int counter = 0;
    int halt = 0;

    // while ((itty.hasNext()) && (halt<300)) {
    while (itty.hasNext()) {
      v = itty.next()
      String vUri = v.uuid
      String vName = v.Concept
      println(vUri + " = " + vName)
      if (!(affilGraph.containsVertex((String)vUri))) {
        String v1 = affilGraph.addVertex((String)vUri);
        String aLine = ""
        nodeLabels.put(vUri,vName)
        String aNode='<node id="'+vUri+'" label="'+vName+'" ><viz:color r="200" g="200" b="200" />'
        nodes.add(aNode)
      }

      final Iterator<Vertex> titles = v.getVertices(Direction.IN).iterator()
      while (titles.hasNext()) {
        Vertex v2 = titles.next()
        String linkedUri = v2.uuid
        String linkedName = v2.Title
        println(linkedUri + " = " + linkedName)
        if (!(affilGraph.containsVertex((String)linkedUri))) {
          String lv = affilGraph.addVertex((String)linkedUri);
          nodeLabels.put(linkedUri,linkedName)
          aNode='<node id="'+linkedUri+'" label="'+linkedName+'" ><viz:color r="0" g="200" b="0" />'
          nodes.add(aNode)
        }
        aLine = '<edge id="'+counter+ '" source="'+vUri +'" target="' + linkedUri + '" />'
        edges.add(aLine)
        counter++
        affilGraph.addEdge("Edge"+Integer.toString(counter), linkedUri, vUri);
      }
     counter++
     halt++
    }
    System.out.println("The graph = " + affilGraph.toString())
    System.out.println("computing centrality...")

   BetweennessCentrality ranker = new BetweennessCentrality((Graph<String,String>)affilGraph);
   Double nodeScoreMin=0
   Double nodeScoreMax=0
   Double minSizeRange = 1
   Double maxSizeRange = 15
   for (java.lang.Object nodeVal : affilGraph.getVertices()) {
     Double nodeScore = ranker.getVertexScore(nodeVal)
     System.out.println("BetweennessCentrality for\t" + nodeLabels.get(nodeVal) + "\t" + nodeScore);
     if (nodeScoreMin>nodeScore) { nodeScoreMin = nodeScore }
     if (nodeScoreMax<nodeScore) { nodeScoreMax = nodeScore }
     nodeScores.put(nodeVal,nodeScore)
   }

   DegreeScorer degreeRanker = new DegreeScorer(affilGraph);
   for (java.lang.Object nodeVal : affilGraph.getVertices()) {
     Double nodeScore = degreeRanker.getVertexScore(nodeVal)
     System.out.println("Degree Centrality for\t" + nodeLabels.get(nodeVal) + "\t" + nodeScore);
     if (nodeScoreMin>nodeScore) { nodeScoreMin = nodeScore }
     if (nodeScoreMax<nodeScore) { nodeScoreMax = nodeScore }
     nodeScores.put(nodeVal,nodeScore)
   }

   new File("/nfsmnt/james/grid_trilab.gexf").withWriter { out -> 
      out.writeLine('<?xml version="1.0" encoding="UTF-8"?>')
      out.writeLine('<gexf xmlns="http://www.gephi.org/gexf" xmlns:viz="http://www.gephi.org/gexf/viz">')
      out.writeLine('<graph type="static">')
      out.writeLine('<nodes>')
      for (String node : nodes) { out.writeLine(node) }
      out.writeLine('</nodes>')
      out.writeLine('<edges>')
      for (String edge : edges) { out.writeLine(edge) }
      out.writeLine('</edges>')
      out.writeLine('</graph>')
      out.writeLine('</gexf>')
}

