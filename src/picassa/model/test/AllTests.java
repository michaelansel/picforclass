package picassa.model.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests
{

    public static Test suite ()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(SimpleParserTest.class);
        suite.addTestSuite(SimpleLexerTest.class);
        //$JUnit-END$
        return suite;
    }

}
