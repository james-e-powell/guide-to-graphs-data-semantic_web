  n=18
  g = new TinkerGraph()
   
  // total number of vertices
  max = Math.pow((n+1),2)
   
  // generate the vertices
  (1..max).each { g.addVertex() }
     
  // generate the edges
  g.V.each {
    id = Integer.parseInt(it.id)
 
    right = id + 1
    if (((right % (n + 1)) > 0) && (right <= max)) {
      g.addEdge(it, g.v(right), '')
    }
 
    down = id + n + 1
    if (down < max) {
      g.addEdge(it, g.v(down), '')
    }
  }
g.saveGraphML('/home/jepowell/lattice.xml')
