
print('\nTo start your Restlet component, run: "sincerity start component"\n\n')

// Let's clear out this file so that we don't get the message again
new java.io.FileWriter(sincerity.container.getLibrariesFile('installers', 'restlet.js')).close()
