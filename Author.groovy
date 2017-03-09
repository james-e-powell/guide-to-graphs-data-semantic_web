import com.tinkerpop.frames.Property
import com.tinkerpop.frames.VertexFrame
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy
import com.tinkerpop.frames.annotations.gremlin.GremlinParam

public class Author implements VertexFrame {

    @Property("frame")
    public void setFrame(final String frameClass) { self.frame = frame; }

    @Property("frame")
    public String getFrame() { return self; }

    @Property("name")
    public String getName() { return self.name; }

    @Property("name")
    public void setName(final String name) { self.name = name; }

    @Property("authorId")
    public String getAuthorId() { return self.authorId; }

    @Property("authorId")
    public String setAuthorId( final String authorId ) { self.authorId = authorId; }


}
