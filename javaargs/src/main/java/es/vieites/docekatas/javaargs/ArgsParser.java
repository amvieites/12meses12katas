package es.vieites.docekatas.javaargs;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * This class contains the Schema to be used parsing a String of arguments.
 *
 * Created by IntelliJ IDEA.
 * User: Alejandro Martínez Vieites
 * Date: 16/04/12
 * Time: 19:58
 */
public class ArgsParser {

    private Schema schema = null;

    private Map<String, Argument> result = new HashMap<String, Argument>();

    /**
     * Unique constructor for initialize the parser rules.
     * @param schema rules
     */
    public ArgsParser(Schema schema) {
        this.schema = schema;
    }

    /**
     * Parses a single line of text.
     *
     * @param args line of arguments
     */
    public void parse(String args) {
        if (schema == null) {
            throw new IllegalStateException("No schema defined!");
        }
        // Get as many String as arguments in the IN
        String[] argsWithoutDash = StringUtils.split(args, "-");

        for (int i = 0; i < argsWithoutDash.length; i++) {
            if (argsWithoutDash != null && argsWithoutDash.length > 0) {
                if (!argsWithoutDash[i].isEmpty() && schema.contains(argsWithoutDash[i].charAt(0))) {
                    Argument arg = parseSingleArg(argsWithoutDash[i]);
                    result.put(String.valueOf(arg.getToken()), arg);
                }
            }
        }

    }

    /**
     * Builds one argument and values.
     * @param argPart
     * @return the argument object
     */
    private Argument parseSingleArg(String argPart) {
        Argument returnArg = new Argument();
        // After the split, will get the argument token and the value(s)
        String[] parts = argPart.split(" ");
        if (parts != null && parts.length > 0) {
            char argChar = argPart.charAt(0);
            if (schema.contains(argChar)) {
                Argument arg = schema.getArgument(argChar);
                if (arg.isHasValue()) {
                    List<String> values = null;
                    values = new ArrayList<String>();
                    if (parts.length > 1) {
                        List<String> list = Arrays.asList(parts[1].split(String.valueOf(arg.getListSeparator())));
                        values.addAll(list);
                    } else {
                        throw new IllegalArgumentException("The argument " + String.valueOf(argPart.charAt(0)) + " needs a value");
                    }
                    returnArg.setValues(values);
                }
                returnArg.setHasValue(arg.isHasValue());
                returnArg.setToken(argChar);
            } else {
                throw new IllegalArgumentException("The argument " + String.valueOf(argPart.charAt(0)) + "does not exist");
            }
        }
        return returnArg;
    }

    /**
     * Gets one parsed argument if present.
     *
     * @param arg argument token
     * @return the argument object (if available)
     */
    public Argument get(String arg) {
        return result.get(arg);
    }

}
