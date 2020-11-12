import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        // Sample Test Case
        final Map<List<String>, Integer> ballots = new HashMap<>();
        ballots.put(Arrays.asList("A", "B", "C"), 4);
        ballots.put(Arrays.asList("B", "C", "A"), 3);
        ballots.put(Arrays.asList("C", "B", "A"), 2);
        
        // If we remove C
        // ballots.put(Arrays.asList("A", "B"), 4);
        // ballots.put(Arrays.asList("B", "A"), 5);
        
        

        // TODO: Determine plurality winner
        System.out.println("The plurality winner is: " + 
        pluralityWinner(ballots).entrySet().iterator().next().getKey());
        
        // TODO: Determine ranked choice winner
        System.out.println("The ranked choice winner is: " + 
        rankedChoiceWinner(ballots));
    }
    
    static Map<String, Integer> pluralityWinner(Map<List<String>, Integer> ballots) {
        Map<String, Integer> candidateFirstPlaceScores = 
            new HashMap<>();
        
        for (Map.Entry<List<String>, Integer> e : ballots.entrySet()) {
            String firstPlaceCandidate = e.getKey().get(0);
            int votes = e.getValue();
            
            if (!candidateFirstPlaceScores.containsKey(firstPlaceCandidate)) {
                candidateFirstPlaceScores.put(firstPlaceCandidate, votes);
            } else {
                int newVoteTally = candidateFirstPlaceScores.get(firstPlaceCandidate) + votes;
                candidateFirstPlaceScores.put(firstPlaceCandidate, newVoteTally);
            }
        }
        
        int maxVotes=0;
        String winner="";
        for (Map.Entry<String, Integer> e : candidateFirstPlaceScores.entrySet()) {
            if (e.getValue() > maxVotes) {
                maxVotes = e.getValue();
                winner = e.getKey();
            }
        }
        
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put(winner, maxVotes);
        return result;
    }
    
    static Map<String, Integer> pluralityLoser(Map<List<String>, Integer> ballots) {
        Map<String, Integer> candidateFirstPlaceScores = 
            new HashMap<>();
        
        for (Map.Entry<List<String>, Integer> e : ballots.entrySet()) {
            String firstPlaceCandidate = e.getKey().get(0);
            int votes = e.getValue();
            
            if (!candidateFirstPlaceScores.containsKey(firstPlaceCandidate)) {
                candidateFirstPlaceScores.put(firstPlaceCandidate, votes);
            } else {
                int newVoteTally = candidateFirstPlaceScores.get(firstPlaceCandidate) + votes;
                candidateFirstPlaceScores.put(firstPlaceCandidate, newVoteTally);
            }
        }
        
        int maxVotes=Integer.MAX_VALUE;
        String winner="";
        for (Map.Entry<String, Integer> e : candidateFirstPlaceScores.entrySet()) {
            if (e.getValue() < maxVotes) {
                maxVotes = e.getValue();
                winner = e.getKey();
            }
        }
        
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put(winner, maxVotes);
        return result;
    }

    static String rankedChoiceWinner(Map<List<String>, Integer> ballots) {
        
        long totalNumberOfVotes = ballots.values().stream().mapToInt(Integer::valueOf).sum();
        
        Map<String, Integer> pluralityWinner = pluralityWinner(ballots);
        int pluralityWinnerVotes = pluralityWinner.entrySet().iterator().next().getValue();
        if (pluralityWinnerVotes > totalNumberOfVotes/2) {
            return pluralityWinner.entrySet().iterator().next().getKey();
        }
        
        Map<String, Integer> pluralityLoser = pluralityLoser(ballots);
        String pluralityLoserKey = pluralityLoser.entrySet().iterator().next().getKey();
        
        return rankedChoiceWinner(pruneBallots(ballots, pluralityLoserKey));
    }    
    
    static Map<List<String>, Integer> pruneBallots(Map<List<String>, Integer> ballots, String toRemove) {
        
        Map<List<String>, Integer> prunedBallots = new HashMap<>();
        
        for (Map.Entry<List<String>, Integer> e : ballots.entrySet()) {
            
            List<String> origBallot = new ArrayList<String>(e.getKey());
            int votes = e.getValue();
            origBallot.remove(toRemove);
            
            if (!prunedBallots.containsKey(origBallot)) {
                prunedBallots.put(origBallot, votes);
            } else {
                int newVoteTally = prunedBallots.get(origBallot) + votes;
                prunedBallots.put(origBallot, newVoteTally);
            }
        }
        
        return prunedBallots;
    }
}
