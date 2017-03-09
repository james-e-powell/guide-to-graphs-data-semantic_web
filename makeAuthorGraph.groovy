import Author;

g = new TinkerGraph()
Author authorV = new Author()
g.addVertex(authorV)
authorV.setName("James")
authorV.setId("123123")

v1 = g.addVertex(name: 'Graph book', uri: 'urn:uuid:graphBook')
v3 = g.addVertex(name: 'Matthew', uri: 'urn:uuid:matthew')
v4 = g.addVertex(name: 'graphs', uri: 'urn:uuid:cocept:graphs')
v5 = g.addVertex(name: 'rdf', uri: 'urn:uuid:concept:rdf')
e1 = g.addEdge(authorV, v1, 'wrote')
e2 = g.addEdge(v3, v1, 'wrote')
e3 = g.addEdge(v1, v4, 'subject')
e4 = g.addEdge(v1, v5, 'subject')
