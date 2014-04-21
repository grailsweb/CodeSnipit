#!/bin/bash
#
# http://pic.dhe.ibm.com/infocenter/wasinfo/v7r0/index.jsp?topic=/com.ibm.websphere.nd.doc/info/ae/ae/rxml_manageprofiles.html
#

export HOST=$(hostname|cut -f1 -d.)
PROF=Dmgr01
PP=/opt/IBM/WebSphere/AppServer/profiles/$PROF 
if [ ! -d "$PP" ] ; then
	/opt/IBM/WebSphere/AppServer/bin/manageprofiles.sh -create -profileName $PROF -profilePath $PP \
		-templatePath /opt/IBM/WebSphere/AppServer/profileTemplates/management \
		-nodeName ${HOST}CellManager01 -cellName ${HOST}Cell01 -hostName $HOST \
		-serverType DEPLOYMENT_MANAGER -samplesPassword abc123  \
		-isDefault  -startingPort 20000

	# WAS 6
	#/opt/IBM/WebSphere/AppServer/bin/manageprofiles.sh -create -profileName $PROF -profilePath /opt/IBM/WebSphere/AppServer/profiles/$PROF -templatePath /opt/IBM/WebSphere/AppServer/profileTemplates/dmgr -nodeName ${HOST}CellManager01 -cellName ${HOST}Cell01 -hostName $HOST
fi

PROF=AppSrv01
PP="/opt/IBM/WebSphere/AppServer/profiles/$PROF"
if [ ! -d  "$PP" ] ; then
	/opt/IBM/WebSphere/AppServer/bin/manageprofiles.sh -create -profileName $PROF -profilePath $PP \
		-templatePath /opt/IBM/WebSphere/AppServer/profileTemplates/default \
		-nodeName ${HOST}CellManager01 -cellName ${HOST}Cell01 -hostName $HOST \
		-isDefault
		#-startingPort 21000
		#-personalCertDN "cn=testa, ou=Rochester, o=IBM, c=US" -signingCertDN "cn=testc, ou=Rochester, o=IBM, c=US"  -keyStorePassword ap3n9krw
fi
