#
# https://www.ibm.com/developerworks/community/blogs/timdp/entry/scripting_websphere_jee_application_installation_using_wsadmin_iwd_friendly7?lang=en
#

# import os module so we can get environment variables

    import os

    contextRoot = '/myapp'
    appName = 'myApp'
     

    # get IWD environment variables CELL_NAME and NODE_NAME, or fall back to sensible defaults

    try:

        cell = os.getenv('CELL_NAME')

    except:

        cell = 'CloudBurstCell_1'

    try:

        node = os.getenv('NODE_NAME')

    except:

        node = 'CloudBurstNode_1'


    # install the application, then save the configuration

    AdminApp.install('/path/to/myApp.war', ['-contextroot', contextRoot, '-appname', appName, '-MapWebModToVH', '[[".*", ".*", "default_host"]]'])

    AdminConfig.save()


    # get the ApplicationManager object and use it to start the application

    appMan = AdminControl.queryNames('cell='+cell+',node='+node+',type=ApplicationManager,process=server1,*')
    AdminControl.invoke(appMan, 'startApplication', appName)
