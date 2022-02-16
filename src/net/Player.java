/**
 * Player class holds the Player object which contains various features of an
 * individual player in the game.
 */

 /** Package with SecretSanta class. */
package net;

public class Player {
    /** Variable corresponding to player's name */
    private String name;
    /** Object corresponding to player's gift receiver */
    private Player receiver;
    /** Object corresponding to player's gift sender */
    private Player sender;

    /**
     * Constructor to initialize Player name
     * 
     * @param name      Player's name to be set
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Getter method to get this Player's name
     * 
     * @return          Player's name private member
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method to get this Player's receiver's name
     * 
     * @return          Receiver's name
     */
    public String getReceiverName() {
        return this.receiver.getName();
    }

    /**
     * Getter method to get this Player's receiver's name
     * 
     * @return          Sender's name
     */
    public String getSenderName() {
        return this.sender.getName();
    }

    /**
     * Setter method to set this Player's sender
     * 
     * @param sender    Sender to be set for this Player
     */
    public void setSender(Player sender) {
        this.sender = sender;
    }

    /**
     * Setter method to set this Player's sender
     * 
     * @param sender    Receiver to be set for this Player
     */
    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    /**
     * Checker method to check if this Player has a receiver
     * 
     * @return          Boolean value of whether receiver exists or not
     */
    public boolean hasReceiver() {
        return this.receiver != null;
    }

    /**
     * Checker method to check if this Player has a receiver
     * 
     * @return          Boolean value of whether sender exists or not
     */
    public boolean hasSender() {
        return this.sender != null;
    }

    /**
     * Public method to reset the receiver and sender values of this Player
     */
    public void resetMatches() {
        this.receiver = null;
        this.sender = null;
    }
}