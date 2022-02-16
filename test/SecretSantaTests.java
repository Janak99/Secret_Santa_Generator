/**
 * Testing class SecretSantaTests to test the main solution and helper methods of Secret
 * Santa class.
 */

 /** Imports for packages and java in-built libs. */
package test;
import net.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

/**
 * Testing class holding test cases for the SecretSanta class.
 */
public class SecretSantaTests {

    /**
     * Test for function readNames
     * @throws IOException
     */
    @Test
    public void testReadNames() throws IOException {
        /** Test bad file/ */
        assertThrows(NoSuchFileException.class, ()->{
            SecretSanta.readNames("invalidFile.txt");
        });

        /**
         * The following commented out code was intended to test the readnames function
         * with test input files, however, I ran into issues with the filepath
         * when invoking the function statically.
         */

        // /** Test input files */
        // String file1 = "../src/net/test1.txt"; //size 5
        // String file2 = "../src/net/test2.txt"; //size 10 
        // String file3 = "../src/net/test3.txt"; //size 215
        // /** Expected names from first two text files */
        // List<String> input1 = Arrays.asList("John","Akhil","Sid","Pranav","Abhi");
        // List<String> input2 = Arrays.asList("John","Akhil","Sid","Pranav","Abhi","Ansh Shah","Matthew Banfield","Jason Mamoa","Timothy Chalamet","Zendaya");
        // List<String> names = SecretSanta.readNames("./input.txt");
        // List<String> names2 = SecretSanta.readNames(file2);
        // List<String> names3 = SecretSanta.readNames(file3);
        // /** Test small set of names */
        // assertEquals(names, input1);
        // assertEquals(names.size(), 5);
        // /** Slightly larger text file */
        // assertEquals(names2, input2);
        // assertEquals(names2.size(), 10);
        // /**Very large number of names */
        // assertEquals(names3.size(), 215);

    }

    /**
     * Test for function populatePlayers
     */
    @Test
    public void testPopulatePlayers() {
        List<Player> test = new ArrayList<Player>();
        List<String> input1 = Arrays.asList("John","Akhil","Sid","Pranav","Abhi");
        List<String> input2 = Arrays.asList("John Maj","Akhil Bhasin","Sid Komaragiri","Pranav Rao","Abhi Agarwal","Ansh Shah","Matthew Banfield","Jason Mamoa","Timothy Chalamet","Zendaya Queen");

        /** Test with just first names. */
        test = SecretSanta.populatePlayers(input1, 5);
        assertEquals(test.size(), 5);
        /** Check if first name in first input is John. */
        assertTrue(test.get(0).getName() == "John");
        /** Test with both first and last names. */
        test = SecretSanta.populatePlayers(input2, 10);
        assertEquals(test.size(), 10);
        /** Check if last name in second input is Zendaya Queen. */
        assertTrue(test.get(test.size()-1).getName() == "Zendaya Queen");
    }

    /**
     * Test for function everyoneMatched
     */
    @Test
    public void testEveryoneMatched() {
        /** Create simple pair of two people. */
        Player p1 = new Player("John");
        Player p2 = new Player("Heli");

        /** Manually match the pair. */
        p1.setReceiver(p2);
        p1.setSender(p2);
        p2.setReceiver(p1);
        p2.setSender(p1);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        /** Require all to be matched. */
        assertTrue(SecretSanta.everyoneMatched(players));
    }

    /**
     * Test for function resetPlayerMatches
     */
    @Test
    public void testResetPlayerMatches() {
        /** Create simple pair of two people. */
        Player p1 = new Player("John");
        Player p2 = new Player("Heli");

        /** Manually match the pair. */
        p1.setReceiver(p2);
        p1.setSender(p2);
        p2.setReceiver(p1);
        p2.setSender(p1);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        /** Require all to be matched. */
        assertTrue(SecretSanta.everyoneMatched(players));

        /** Remove matches */
        SecretSanta.resetPlayerMatches(players);

        /** Require false for everyone matched */
        assertFalse(SecretSanta.everyoneMatched(players));
    }

    /**
     * Test for function validMatches
     */
    @Test
    public void testValidMatches(){
        List<String> input2 = Arrays.asList("John Maj","Akhil Bhasin","Sid Komaragiri","Pranav Rao","Abhi Agarwal","Ansh Shah","Matthew Banfield","Jason Mamoa","Timothy Chalamet","Zendaya Queen");

        List<Player> players = SecretSanta.populatePlayers(input2, 10);
        /** Assign matches. */
        SecretSanta.assignPlayers(players);
        /** Everyone should be matched. */
        assertTrue(SecretSanta.everyoneMatched(players));

        /** Set one player to receive from themselves. */
        players.get(2).setReceiver(players.get(2));

        /** Matches should be invalid. */
        assertFalse(SecretSanta.validMatches(players));
    }

    /** Test for function assignPlayers */
    @Test
    public void testAssignPlayers() {
        List<String> input2 = Arrays.asList("John Maj","Akhil Bhasin","Sid Komaragiri","Pranav Rao","Abhi Agarwal","Ansh Shah","Matthew Banfield","Jason Mamoa","Timothy Chalamet","Zendaya Queen");

        List<Player> players = SecretSanta.populatePlayers(input2, 10);
        /** Assign matches. */
        SecretSanta.assignPlayers(players);
        /** Everyone should be matched. */
        assertTrue(SecretSanta.everyoneMatched(players));
        /** Matches should all be valid. */
        assertTrue(SecretSanta.validMatches(players));
    }
    
}