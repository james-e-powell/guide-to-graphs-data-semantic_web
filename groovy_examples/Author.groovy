import com.tinkerpop.frames.Property
import com.tinkerpop.frames.VertexFrame
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy
import com.tinkerpop.frames.annotations.gremlin.GremlinParam
// import com.tinkerpop.frames.Adjacency

public interface Author {
  @Property("name")
  public String getName();

  @Property("name")
  public String setName(final String name);

  @Property("uri")
  public String getUri();
 
  @Property("uri")
  public String setUri(final String uri);

}

