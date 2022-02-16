/**
 * Testing class PlayerTests to test the methods of the Player class.
 */

/** Imports for packages and java in-built libs. */
package test;
import org.junit.Test;
import static org.junit.Assert.*;
import net.*;


public class PlayerTests {

    /**
     * Testing the Player constructor.
     */
    @Test
    public void testConstructor() {
        Player test = new Player("SomeName");
        assertTrue(test.getName() == "SomeName");
    }

    /**
     * Testing getName
     */
    @Test
    public void testGetName() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
    }

    /**
     * Testing getReceiverName
     */
    @Test
    public void testGetReceiverName() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be receiver. */
        Player rec = new Player("Other");
        test.setReceiver(rec);
        /** Receiver name should now be other. */
        assertTrue(test.getReceiverName() == "Other");
    }   

    /**
     * Testing getSenderName
     */
    @Test
    public void testGetSenderName() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be sender. */
        Player send = new Player("Sender");
        test.setSender(send);
        /** Sender name should now be other. */
        assertTrue(test.getSenderName() == "Sender");
    }


    /**
     * Testing setSender
     */
    @Test
    public void testSetSender() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be sender. */
        Player send = new Player("Sender");
        test.setSender(send);
        /** Sender name should now be other. */
        assertTrue(test.getSenderName() == "Sender");
    }

    /**
     * Testing setReceiver
     */
    @Test
    public void testSetReceiver() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be receiver. */
        Player rec = new Player("Rec");
        test.setReceiver(rec);
        /** Receiver name should now be other. */
        assertTrue(test.getReceiverName() == "Rec");
    }

    /**
     * Testing hasReceiver
     */
    @Test
    public void testHasReceiver() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be receiver. */
        Player rec = new Player("Rec");
        test.setReceiver(rec);
        /** Receiver should exist. */
        assertTrue(test.hasReceiver());
    }

    /**
     * Testing hasSender
     */
    @Test
    public void testHasSender() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be sender. */
        Player send = new Player("Sender");
        test.setSender(send);
        /** Sender should now exist. */
        assertTrue(test.hasSender());
    }

    /**
     * Testing resetMatches
     */
    @Test
    public void testResetMatches() {
        Player test = new Player("Janak");
        assertTrue(test.getName() == "Janak");
        /** New player to be sender. */
        Player send = new Player("Sender");
        test.setSender(send);
        /** Sender name should now be other */
        assertTrue(test.hasSender());
        /** New player to be receiver. */
        Player rec = new Player("Rec");
        test.setReceiver(rec);
        /** Receiver should now exist */
        assertTrue(test.hasReceiver());

        /** Reset matches. */
        test.resetMatches();

        /** Receiver and sender should now be null */
        assertFalse(test.hasReceiver());
        assertFalse(test.hasSender());
    }
}
