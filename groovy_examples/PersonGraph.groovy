import com.tinkerpop.frames.FramedGraphFactory
import com.tinkerpop.frames.FramedGraph
import com.tinkerpop.frames.modules.AbstractModule
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule


import com.tinkerpop.frames.Property
import com.tinkerpop.frames.VertexFrame
import com.tinkerpop.frames.Adjacency
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy
import com.tinkerpop.frames.annotations.gremlin.GremlinParam

TinkerGraph graph = TinkerGraphFactory.createTinkerGraph(); //This graph is pre-populated.
FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule()); //(1) Factories should be reused for performance and memory conservation.

FramedGraph framedGraph = factory.create(graph); //Frame the graph.

Person p = framedGraph.addVertex(null, Person.class);


p.setName("James")

Person person = framedGraph.getVertex(1, Person.class);
println person.getName(); // equals "marko"

for (Vertex vertex : framedGraph.getVertices()) {
  println(vertex)
  println(vertex.map())
}
