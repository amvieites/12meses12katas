package es.vieites.docekatas.javaargs;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * User: Alejandro Martínez Vieites
 * Date: 24/03/12
 * Time: 22:46
 */
public class ArgsParserTest {
    @Test
    public void testParseEmptyString() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', false, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));
        parser.parse("");
    }

    @Test
    public void testParseOneFlag() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', false, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-l");
        assertTrue(parser.get("l") != null);
    }

    @Test
    public void testParseOneFlagUnset() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', false, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-x");
        assertTrue(parser.get("x") == null);
    }

    @Test
    public void testParseOneFlagWithValue() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', true, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-l 1");
        assertTrue(String.valueOf(parser.get("l").getToken()).equals("l"));
        assertTrue(parser.get("l").getValues().get(0).equals("1"));
    }

    @Test
    public void testParseOneFlagWithMultipleValues() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', true, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-l 1,2,3");
        assertTrue(String.valueOf(parser.get("l").getToken()).equals("l"));
        assertTrue(parser.get("l").getValues().get(0).equals("1"));
        assertTrue(parser.get("l").getValues().get(1).equals("2"));
        assertTrue(parser.get("l").getValues().get(2).equals("3"));
    }

    @Test
    public void testTwoFlagsWithoutValue() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', false, ',', null));
        args.put("x", new Argument('x', false, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-l -x");
        assertTrue(String.valueOf(parser.get("l").getToken()).equals("l"));
        assertTrue(String.valueOf(parser.get("x").getToken()).equals("x"));
    }

    @Test
    public void testTwoFlagsWithValues() throws Exception {
        Map<String, Argument> args = new HashMap<String, Argument>();
        args.put("l", new Argument('l', true, ',', null));
        args.put("x", new Argument('x', true, ',', null));
        ArgsParser parser = new ArgsParser(new Schema(args));

        parser.parse("-l a,b -x 1");
        assertTrue(String.valueOf(parser.get("l").getToken()).equals("l"));
        assertTrue(parser.get("l").getValues().get(0).equals("a"));
        assertTrue(parser.get("l").getValues().get(1).equals("b"));
        assertTrue(String.valueOf(parser.get("x").getToken()).equals("x"));
        assertTrue(parser.get("x").getValues().get(0).equals("1"));
    }
}
