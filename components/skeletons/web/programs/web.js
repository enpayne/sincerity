
importClass(
	org.eclipse.jetty.server.Server,
	org.eclipse.jetty.server.handler.HandlerList,
	org.eclipse.jetty.server.handler.ResourceHandler)

var port = 8080
var root = 'web'

//
// Logging
//

try {
sincerity.run('logging:logging')
} catch(x) {}

//
// Server
//

var server = new Server(port)

// The handlers
var handlers = new HandlerList()
server.handler = handlers

//
// Resources (static web)
//

var resource = new ResourceHandler()
resource.resourceBase = sincerity.container.getFile(root)
resource.directoriesListed = true
handlers.addHandler(resource)

//
// Start server
//

server.start()
sincerity.out.println('Started web server on port ' + port)
server.join()
