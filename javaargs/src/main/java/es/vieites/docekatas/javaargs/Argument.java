package es.vieites.docekatas.javaargs;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Alejandro Martínez Vieites
 * Date: 24/03/12
 * Time: 22:06
 */
public class Argument {
    private char token;
    private boolean hasValue = false;
    private char listSeparator = '\0';
    private List<String> values = new ArrayList<String>();

    public Argument() {

    }

    public Argument(char token, boolean hasValue, char listSeparator, List<String> values) {
        this.token = token;
        this.hasValue = hasValue;
        this.listSeparator = listSeparator;
        this.values = values;
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    public char getListSeparator() {
        return listSeparator;
    }

    public void setListSeparator(char listSeparator) {
        this.listSeparator = listSeparator;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
