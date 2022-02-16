/**
 * SecretSanta Class that holds the main solution to the secret-santa command
 * line tool to generate a list of assignments from a list of players.
 */


/** Imports for packages and java in-built libs. */

package net;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecretSanta {
    /** Constant threshold for max iteration attempts to find unique pairings. */
    private static final Integer RETRY_MAX = 10000;
    /** List of strings to hold the names of secret santa players. */
    static List<String> names;
    static List<Player> players;
    /** 
     * Main method for running program with input arguments.
     * 
     * @param       args            Command line arguments.
     * @throws      IOException     Possible exception thrown to handle invalid inputs.
     */
    public static void main(String[] args) throws IOException {

        /** Try getting and storing names, catch and handle expection if this fails. */
        try {
            names = readNames(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        /** Variable holding the number of players. */
        Integer length = names.size();

        /** Variable holding the list of Player objects for assignments. */
        players = populatePlayers(names, length);

        /** Create assignments for all the players. */
        assignPlayers(players);

        /** Generate output file */
        String outFilename = args[1];
        if(validMatches(players)){
            writeAssignments(players, outFilename);
        }
    }

    /**
     * Helper method that reads an input file using Files library to get the names
     * of players.
     * 
     * @param   inputPath       Path of input file to be read.
     * @return  names           A List containing the names of the players as Strings.
     * @throws  IOException     Throwable exception in case the file path cannot be found.
     */
    public static List<String> readNames(String inputPath) throws IOException {
        /** Variable to be returned. */
        List<String> names;
        try {                                                       /** Try reading from file. */
            names = Files.readAllLines(Paths.get(inputPath));
            return names;
        } 
        catch (IOException e) {                                     /** Try failed, catch and handle exception. */
            System.out.println("The input file you have entered does not exist. Please try a valid path or filename.");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Helper method that writes the generated assignments to an apprpriate output file.
     * 
     * @param   players         List of players and pairings to write to file.
     * @param   outFilename     Name of specified output file.
     * @throws  IOException     Possible excption to be thrown in case invalid file.
     */
    public static void writeAssignments(List<Player> players, String outFilename) throws IOException{
        /** Generate output file with FileWriter. */
        try {
            BufferedWriter oStream = new BufferedWriter(new FileWriter(outFilename, false));
            for(Player player : players) {
                oStream.write(player.getName() + " -> " + player.getReceiverName() + "\n");
            }
            oStream.close();

        } catch (Exception e) {
            System.out.println("There was a problem with your output file. Please try a valid entry.");
            e.printStackTrace();
        }
        
    }

    /**
     * This method fills in a list of player objects for which the secret santa assignments will be done.
     * 
     * @param   names       A list of names as Strings of the players.
     * @param   length      The number of names there are.
     * @return  players     A list of Player objects with their names set.
     */
    public static List<Player> populatePlayers(List<String> names, Integer length) {
        /** Initializing new list with size of players */
        List<Player> players = new ArrayList<Player>(length);

        /** Loop through all names and add corresponding player object to list of players. */
        for(String name : names){
            Player newPlayer = new Player(name);
            players.add(newPlayer);
        }
        return players;
    }

    /**
     * Helper method to assign players with their santa's and recepients.
     * 
     * @param   players     List of players to create secret santa assignments with.
     */
    public static void assignPlayers(List<Player> players) {
        /** This random object will be used to generate random integers for assignments. */
        Random rand = new Random();

        /** Looping as long as all players are matched. */
        while(!everyoneMatched(players)) {
            /** Reset matches when retrying so hasRecepient() works as intended.*/
            resetPlayerMatches(players);

            /** Loop through all players */
            for (int i = 0; i < players.size(); i++) {
                /** Store current player and current interation */
                Player currPlayer = players.get(i);
                Integer iterationCount = 0;

                /** Loop while current player is assigned a receiver and iterations are less than MAX. */
                while(!currPlayer.hasReceiver() && iterationCount < RETRY_MAX) {

                    /** Generating random integer for recepient seleciton. */
                    Integer randInt = rand.nextInt(players.size());

                    if(randInt != i && !players.get(randInt).hasSender()) {     /** Receiver not self AND does not have a sender. */
                        currPlayer.setReceiver(players.get(randInt));

                        players.get(randInt).setSender(currPlayer);
                    } else {                                                    /** Retry the process with new random selection. */
                        iterationCount += 1;
                    }
                }
            }
        }
    }

    /**
     * Checking method to verify if all players are matched a.k.a have a sender and receiver.
     * 
     * @param   players     List of players in the game as Player objects.
     * @return              Boolean value of if everyone is matched.
     */
    public static Boolean everyoneMatched(List<Player> players) {
        /** Loop through players to check for their receivers and senders. */
        for(Player thisPlayer : players) {
            /** Return false if either receiver or sender does not exist. */
            if(!thisPlayer.hasReceiver() || !thisPlayer.hasSender()) return false;
        }
        /** Else return true. */
        return true;
    }

    /**
     * Helper method to reset all the matches of all the players in a list of players.
     * 
     * @param   players     List of players to clear the matches of.
     */
    public static void resetPlayerMatches(List<Player> players) {
        for(Player currPlayer : players) {
            currPlayer.resetMatches();
        }
    }

    /**
     * Helper method to check that all players have valid matches a.k.a aren't matched
     * with themselves.
     * 
     * @param   players     List of players to check validity of.
     * @return              Boolean value of validity of matches.
     */
    public static Boolean validMatches(List<Player> players) {
        for(Player player : players) {
            if(player.hasReceiver()){
                if(player.getName() == player.getReceiverName()) return false;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
