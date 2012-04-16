package es.vieites.docekatas.javaargs;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

/**
 * This class holds the rules to be follow by the ArgsParser class.
 *
 * User: Alejandro Martínez Vieites
 * Date: 16/04/12
 * Time: 19:05
 */
public class Schema {
    private Map<String, Argument> argumentMap = null;

    public Schema(Map<String, Argument> argumentList) {
        this.argumentMap = argumentList;
    }

    public Map<String, Argument> getArgumentList() {
        return argumentMap;
    }
    
    public Argument getArgument(char a) {
        return argumentMap.get(String.valueOf(a));
    }

    public Argument getArgument(String a) {
        return argumentMap.get(a);
    }

    public boolean contains(String argToken) {
        return argumentMap.containsKey(argToken);
    }

    public boolean contains(char argToken) {
        return argumentMap.containsKey(String.valueOf(argToken));
    }
}
