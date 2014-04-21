# Jython script to install a EAR or WAR in WebSphere Application Server
#
 
import sys
 
#Some Global variables
#WebSphere environment related variables
cellName = 'localhostCell01'  
nodeName = 'localhostNode01'
serverName = 'server1'
 
print '\n\n-------------------------------------------------------------------'
print 'This script will install a EAR/WAR on WebSphere Application Server'
print "\nUsing these WebSphere Defaults:"+"\nCell: "+cellName+"\nNode: "+nodeName+"\nServer: "+serverName
print "\nPass the path to the EAR/WAR and web-app's context-root as parameters. File path should be in this format:"
print "\tc:\\\\osgiapps\\\\war-to-install.war (With double back-slashes)"
print "\nPlease edit the variables in the script if these defaults need to be changed."
print '-------------------------------------------------------------------\n\n'
 
 
numberOfArgs = len(sys.argv)
if numberOfArgs < 2:
    print "Usage: installApp.py {file-absolute-path} {context-root}"
 
 
else:
    #Get the absolute path of the WAR file and replace \ with /
    filePath1 = str(sys.argv[0])
    contextRoot = str(sys.argv[1])
    filePath1 = filePath1.replace('\\', '/')
     
    #Get the name of the WAR file:
    strAppToInstall = filePath1[filePath1.rfind("/")+1:len(filePath1)];
    print "Installing ", strAppToInstall, " from ", filePath1;
     
    #Uninstall the app if already deployed.
    appToUninstall = ""
    appsBefore = AdminApp.list().split("\n");
    for iApp in appsBefore:   
        if str(iApp).find(strAppToInstall) >= 0:   
            appToUninstall = iApp;
    if appToUninstall:
        print "Uninstalling app: ", appToUninstall
        appToUninstall = str(appToUninstall).strip();
        AdminApp.uninstall(appToUninstall)
        AdminConfig.save();
     
    #Install the app
    print "Installing App: ", strAppToInstall
    AdminApp.install(filePath1, "-contextroot /"+contextRoot+" -defaultbinding.virtual.host default_host -usedefaultbindings");   
    AdminConfig.save();   
     
    #Start the app   
    apps = AdminApp.list().split("\n");   
    theApp = ""   
    for iApp in apps:
        if str(iApp).find(strAppToInstall) >= 0:
            theApp = iApp;
    print "Starting App: ", theApp
    appManager = AdminControl.queryNames('cell='+cellName+',node='+nodeName+',type=ApplicationManager,process='+serverName+',*')
    AdminControl.invoke(appManager, 'startApplication', theApp)
    print "Application installed and started successfuly!"
