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
public class GroupStudents
{
    final int ASCENDING = 0;
    final int DESCENDING = 1;
    
    final String BY_TEAM = "a";
    final String BY_TEAM_ROLE = "b";
    final String BY_TEST_SCORE = "c";
    final String BY_MODULE_MARK = "d";
    final String BY_AVERAGE_MARK = "e";
    
    private String pByVar;
    private int pOrder;
    
    public void GroupStudents()
    {
        
    }
    
    public ArrayList<Student> group(ArrayList<Student> list, String byVar, int order)
    {
        pByVar = byVar;
        pOrder = order;
        calculateGroupOrder(list);
        //pSort(list);
        return list;
    }
    
    public ArrayList<Student> group(ArrayList<Student> list, String byVar)
    {
        pByVar = byVar;
        pOrder = ASCENDING;
        calculateGroupOrder(list);
        pGroup(list);
        return list;
    }
    
    private ArrayList<Student> calculateGroupOrder(ArrayList<Student> list)
    {
        for (Student s : list)
        {
            int groupOrder = 0;
            for (int i = 0; i < pByVar.length(); i++)
            {
                double power = (pByVar.length() - i + 1)*2;
                int importance = (int)Math.pow(10, power);
                String switchChoice = String.valueOf(pByVar.charAt(i));
                //System.out.println(importance);
                
                switch (switchChoice)
                {
                    case BY_TEAM: 
                        groupOrder = (groupOrder + 1) + ((s.memberOfTeam + 1)*importance);
                        //System.out.println(groupOrder);
                        break;
                    
                    case BY_TEAM_ROLE:
                        int teamRoleASCII = 0;
                        for (int charIndex = 0; charIndex < s.teamRole.length(); charIndex++)
                        {
                            char c = s.teamRole.charAt(charIndex);
                            teamRoleASCII = teamRoleASCII + (int)c - 64; //-64 to get A = 1, B = 2 etc
                        }
                        groupOrder = (groupOrder + 1) + (teamRoleASCII*importance);
                        //System.out.println(groupOrder);
                        break;
                     
                    case BY_TEST_SCORE: 
                        groupOrder = (groupOrder + 1) + ((s.testScore + 1)*importance);
                        break;
                    
                    case BY_AVERAGE_MARK:
                        groupOrder = (groupOrder + 1) + (((int)s.averageMark + 1)*importance);
                        break;
                        
                }         
            }
            s.groupOrder = groupOrder;
        }
        return list;
    }
    
    
    private ArrayList<Student> pGroup(ArrayList<Student> list)
    {
        //set to true just to make sure we start the loop, it will exit
        //when a complete run through has occured with no swapping
        boolean swapped=true; 
        Student temp;
        
        while (swapped)
        {
            swapped = false;
            for (int i = 0; i < list.size() - 1; i++)
            {
                Student curStudent = list.get(i);
                Student nextStudent = list.get(i+1);
                int curStudentVal = curStudent.groupOrder;
                int nextStudentVal = nextStudent.groupOrder;
                
                
                if (pOrder == ASCENDING)
                {
                    if (curStudentVal > nextStudentVal)
                    {
                        temp = list.get(i);
                        list.set(i, list.get(i+1));
                        list.set(i+1, temp);
                        swapped = true;
                    }
                }
                else if(pOrder == DESCENDING)
                {
                    if (curStudentVal < nextStudentVal)
                    {
                        temp = list.get(i);
                        list.set(i, list.get(i+1));
                        list.set(i+1, temp);
                        swapped = true;
                    }
                }
                
            }
        }
        return list;
    }
    
}
