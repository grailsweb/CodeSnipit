<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="fontawasome"/>
		<title>Grails FontAwasome DataTable Sample</title>		
		<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">		
	</head>
	<body>
		
<!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
               <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       Grails DataTables Sample                      
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="/fontawasom/"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Index</a></li>
                        <li class="active">Main</li>
                    </ol>
                </section>          

                <!-- Main content -->
                <section class="content ">
		
		<div id="status" role="complementary">
			<h1>Application Status</h1>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${GroovySystem.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h1>Installed Plugins</h1>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</div>
		
		 
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
	</body>
</html>
