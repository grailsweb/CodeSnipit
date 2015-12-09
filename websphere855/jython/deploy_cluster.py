appName='StatelessSessionBeanExample'
earLoc='/home/webuser/was85/StatelessCode/example.ear'
cluster="sfgcluster"
node="appserver01Node01"

cellName=AdminControl.getCell()
print cellName
nodeName=AdminControl.getNode()
print nodeName

clustermgr = AdminControl.completeObjectName('cell='+cellName+ ',type=Cluster,name='+cluster +',*')
print clustermgr
AdminControl.invoke(clustermgr , 'stopApplication',appName)
print AdminApp.uninstall(appName)
AdminConfig.save()


appOptions = "[-cluster " + cluster + " -defaultbinding.virtual.host default_host -usedefaultbindings "
if len(appName) != 0:
      appOptions = appOptions + " -appname " + appName
      appOptions = appOptions + "]"
AdminApp.install(earLoc, appOptions)
AdminConfig.save()
AdminControl.invoke(nodeSync, "sync")
print "Start the application"
AdminControl.invoke(cluster , 'startApplication',appName)
