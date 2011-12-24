
importClass(
	org.restlet.Application,
	org.restlet.routing.Router,
	org.restlet.routing.Template,
	org.restlet.resource.Directory,
	java.io.File)

// The application
var app = new Application(component.context.createChildContext())
app.name = 'Skeleton'
app.description = 'The example application for the Restlet skeleton'
app.author = 'Three Crickets'
app.owner = 'Free Software'
	
// Attach to hosts
component.defaultHost.attach('/', app)

// Inbound root
var router = new Router(app.context)
app.inboundRoot = router

// Restlets
executeAll('restlets')
