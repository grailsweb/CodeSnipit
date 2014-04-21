package com.javajazzup.examples.ejb3.stateless;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
//import com.ibm.websphere.naming.PROPS;

public class test {
    public static void main(String[] args) {
        try {
            // create initial context

	Hashtable env = new Hashtable();
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
	env.put(Context.PROVIDER_URL, "corbaloc:iiop:localhost:2809/NameServiceCellPersistentRoot");
	//env.put(PROPS.NAME_SPACE_ROOT, PROPS.NAME_SPACE_ROOT_CELL_PERSISTENT);
	Context ctx = new InitialContext(env);

            System.out.println(ctx);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
