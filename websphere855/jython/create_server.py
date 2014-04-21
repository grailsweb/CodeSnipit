import sys, java
from java.util import Properties
from java.io import FileInputStream
from org.python.modules import time
lineSep = java.lang.System.getProperty('line.separator')

def server_create(node, server):
     node = AdminConfig.getid('/Node:' + node + '/')
     attr = [['name', server]]
     print "creating server .."
     AdminConfig.create('Server', node, attr)
     print "server created .."
     AdminConfig.save()
     print "saving config .."


arglen = len(sys.argv)
number_of_argument =2
if (arglen != number_of_argument):
 print "pass 2 arguments .."
 sys.exit(-1)

server = sys.argv[0]
node = sys.argv[1]
server_create(node, server)
