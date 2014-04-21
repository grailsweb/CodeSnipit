earLoc='/root/ejb/StatelessCode/example.ear'     #eg. '/root/Desktop/sales.ear'

appName='StatelessSessionBeanExample'                # eg. 'salesWeb' 

cellName=AdminControl.getCell()
print cellName

nodeName=AdminControl.getNode()
print nodeName

appManager=AdminControl.queryNames('cell='+cellName+',node='+nodeName+',type=ApplicationManager,process=server1,*')

print appManager

AdminControl.invoke(appManager , 'stopApplication',appName)

print AdminApp.uninstall(appName)

AdminConfig.save()

print AdminApp.install(earLoc)

AdminConfig.save()

AdminControl.invoke(appManager , 'startApplication',appName) 
