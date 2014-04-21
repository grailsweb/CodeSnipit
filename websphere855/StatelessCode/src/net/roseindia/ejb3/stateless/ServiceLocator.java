package com.javajazzup.examples.ejb3.stateless;
import java.io.IOException;
import java.util.Hashtable;  
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.*;
import com.javajazzup.examples.ejb3.stateless.*;

public class ServiceLocator {
    static String url = "corbaloc:iiop:localhost:2809";
    static String initial = "com.ibm.websphere.naming.WsnInitialContextFactory";  
//    static String jndi = "ejb/enterprise_app_name/ejb_web_project_name.jar/ejb_name#name.of.remote.impl.interface";
    static String jndi = "ejbremote:com.javajazzup.examples.ejb3.CalculatorRemote" ;

//  calculator = (CalculatorRemote) ic.lookup("example/CalculatorBean/remote");
//  SampleEjbLocal ejb = (SampleEjbLocal) ctx.lookup("ejblocal:com.sample.ejbs.SampleEjbLocal");  

    private static ServiceLocator serviceLocator = null;  
    InitialContext context = null;

    private ServiceLocator() throws NamingException, IOException { 

        Hashtable<String,String> env = new Hashtable<String,String> (); 
        env.put("java.naming.provider.url",  url ); 
        env.put("java.naming.factory.initial",  initial );
        context = new InitialContext(env);
    }

    public synchronized static ServiceLocator getInstance() throws NamingException, IOException {

        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator(); 
        }

        return serviceLocator;
    }  

    public Object getService(String jndiName) throws NamingException {
        return context.lookup(jndiName); 
    }

    public <T>T getRemoteObject(Class<T> remoteInterfaceClass) {
        try {

	 	//calculator = (CalculatorRemote) ic.lookup("example/CalculatorBean/remote");


            return (T)javax.rmi.PortableRemoteObject.narrow( context.lookup(jndi), remoteInterfaceClass);

        } catch (NamingException nexc) {

            nexc.printStackTrace(); 

        }
        return null;
    }

	public static void main(String args[]){
    	String rjndi = "ejbremote:com.javajazzup.examples.ejb3.CalculatorRemote" ;

	try {
   Properties properties = new Properties();
   properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
   properties.setProperty(Context.PROVIDER_URL,"corbaloc:iiop:localhost:2809"); //localhost=the host where your EJB is located, 2809=BOOTSTRAP_ADDRESS port
   Context initCtx = new InitialContext(properties);
   Object homeObject = initCtx.lookup(rjndi);
 System.out.println(homeObject);
	} catch (NamingException nexc) {

            nexc.printStackTrace();

        }

  } 

}
