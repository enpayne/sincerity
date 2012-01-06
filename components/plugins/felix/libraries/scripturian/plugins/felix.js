
var MAIN_CLASS = 'org.apache.felix.main.Main'

importClass(java.lang.System)

function getCommands() {
	return ['felix', 'gogo']
}

function run(command) {
	switch (String(command.name)) {
		case 'felix':
		case 'gogo':
			gogo(command)
			break
	}
}

function gogo(command) {
	System.setProperty('sincerity.cache', command.sincerity.container.cacheFile)
	System.setProperty('felix.config.properties', command.sincerity.container.getConfigurationFile('felix.conf').toURL())
	
	var mainArguments = [MAIN_CLASS]
	var arguments = command.arguments
	for (var i in arguments) {
		mainArguments.push(arguments[i])
	}
	command.sincerity.run('delegate:main', mainArguments)
}
