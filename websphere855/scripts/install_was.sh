#!/bin/bash
# Script to install the WAS
#


grep -q "^webgroup:" /etc/group || groupadd -g 600 webgroup
grep -q "^webuser:" /etc/passwd || useradd -c "WebSphere User" -g webgroup -m  -e 2020-01-01  webuser

#Install software  WAS, IHS and Plugins
umask 022
cd /home/webuser/work/websphere855/scripts && {
/opt/IBM/InstallationManager/eclipse/tools/imcl -acceptLicense -showProgress input install_WAS85nd.rsp -log /tmp/iwas.log
/opt/IBM/InstallationManager/eclipse/tools/imcl -acceptLicense -showProgress input install_WAS85_ihs.rsp -log /tmp/iihs.log
/opt/IBM/InstallationManager/eclipse/tools/imcl -acceptLicense -showProgress input install_WAS85_plugins.rsp -log /tmp/iplug.log
chown -R webuser:webgroup /opt/IBM/WebSphere/  /opt/IBM/HTTPServer/

}

./create_profile.sh

/opt/IBM/WebSphere/AppServer/bin/ivt.sh dmgr Dmgr01
