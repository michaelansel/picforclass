package model;

import java.util.*;
import model.semantics.Operation;
import util.reflection.Reflection;
import util.resources.*;

public class Parser
{
    private List<Operation> myPossibleOperations;


    public Parser ()
    {
        ResourceManager resources = ResourceManager.getInstance();
        resources.addResourcesFromFile("syntax");
        myPossibleOperations = new ArrayList<Operation>();
        for (String key : resources)
        {
            myPossibleOperations.add(
                (Operation)Reflection.createInstance(resources.getString(key)));
        }
    }
    
    public Operation makeExpression (String infix)
    {
        Collections.shuffle(myPossibleOperations);
        System.out.println(myPossibleOperations.get(0).getClass().getCanonicalName());
        return myPossibleOperations.get(0);
    }
}
