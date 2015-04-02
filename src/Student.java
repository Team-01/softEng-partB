/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author greench
 */
public class Student extends User
{
    Boolean fullTime;
    Boolean prevExperience;
    int memberOfTeam;
    String previousSubject;
    int trSH;
    int trIMP;
    int trCF;
    int trCO;
    int trTW;
    int trRI;
    int trPL;
    int trME;
    int trSP;
    String teamRole;
    int testScore;
    int moduleMark;
    int averageMark;    
    
    public Student()
    {
        
    }
    
    public void print()
    {
        String printString;
        printString = "ID=" + ID + ", Name=" + name +
                "\nfullTime=" + fullTime + ", prevExperience=" + prevExperience +
                "\nmemberOfTeam =" + memberOfTeam + ", previousSubject=" + previousSubject +
                "\ntrSH=" + trSH + ", trIMP=" + trIMP + ", trCF=" + trCF +
                "\ntrCO=" + trCO + ", trTW=" + trTW + ", trRI=" + trRI +
                "\ntrPL=" + trPL + ", trME=" + trME + ", trSP=" + trSP +
                "\npersonType=" + teamRole + ", testScore=" + testScore +
                "\nmoduleMark=" + moduleMark + ", averageMark=" + averageMark;
        
        System.out.println(printString);
    }
    
    public void assignTeamRole()
    {
        HashMap<String, Integer> TRScores = new HashMap();
        TRScores.put("SH", trSH);
        TRScores.put("IMP", trIMP);
        TRScores.put("CF", trCF);
        TRScores.put("CO", trCO);
        TRScores.put("TW", trTW);
        TRScores.put("RI", trRI);
        TRScores.put("PL", trPL);
        TRScores.put("ME", trME);
        TRScores.put("SP", trSP);
        
        ArrayList<String> possibleTRs = new ArrayList();
        int curHighScore = -1;
        
        //adds the team role to the possibles list
        //if the current high score is surpassed then the possible
        //team roles list starts again
        for (Map.Entry<String, Integer> ent : TRScores.entrySet())
        {
            String TR = ent.getKey();
            int Score = (int)ent.getValue();
            
            if (Score == curHighScore) possibleTRs.add(TR);
            else if (Score > curHighScore)
            {
                curHighScore = Score;
                possibleTRs.clear();
                possibleTRs.add(TR);
            }
        }
        
        //if (possibleTRs.size() == 1) teamRole = possibleTRs.get(0);
        //pick a random team role from the equal high scores
        Random rand = new Random();
        //System.out.println("possTRs: " + possibleTRs.toString());
        teamRole = possibleTRs.get(rand.nextInt(possibleTRs.size()));
        //System.out.println("teamRole=" + teamRole);
    }
    
    /*
    int trSH;
    int trIMP;
    int trCF;
    int trCO;
    int trTW;
    int trRI;
    int trPL;
    int trME;
    int trSP;
    */
}
