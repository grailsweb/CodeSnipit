package com.javajazzup.examples.ejb3.stateless;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
//import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestEJBClient
{


   /**
    * @param args
    */
   public static void main(String[] args)
   {
      
      Properties props = new Properties();

      props.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.ibm.websphere.naming.WsnInitialContextFactory");

      // HOST_NAME – abc.com, BOOTSTRAP_ADDRESS PORT - 2809
      props.put(javax.naming.Context.PROVIDER_URL,
            "iiop://10.23.45.21:2809");     
     
 //     ITestEJBRemoteInterface loEJB =null;
      Object lobj;
      try
      {
         InitialContext ctx = new InitialContext(props);

         lobj = ctx.lookup("checkName");

/*
         if (lobj instanceof ITestEJBRemoteInterface)
         {
            loEJB = (ITestEJBRemoteInterface) lobj;
         }
         
         String lsName = "Imran";

         // Invoke the Method using bean object ;
         System.out.println("Is "+ lsName + " present in the list:: "+loEJB.checkNames(lsName));
*/

         System.out.println("EJB run successful");
      }
      catch (NamingException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
