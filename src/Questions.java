/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public class Questions {
    
    String QorT;
    private int quesID;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    
    public Questions()
    {
        quesID = 0;
        question = "sample question";
        ans1 = "answer 1";
        ans2 = "answer 2";
        ans3 = "answer 3";
        ans4 = "answer 4";
    }
    
    public Questions(String type, int qID, String q, String a1, String a2, String a3, String a4)
    {
        QorT = type;
        quesID = qID;
        question = q;
        ans1 = a1;
        ans2 = a2;
        ans3 = a3;
        ans4 = a4;
    }
    
    public void setQuestion(int id, String q)
    {
        quesID = id;
        question = q;
    }
    
    public void setAnswers(String a1, String a2, String a3, String a4)
    {
        ans1 = a1;
        ans2 = a2;
        ans3 = a3;
        ans4 = a4;
    }
    
    public int getID()
    {
        return quesID;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public String getAnswers()
    {
        return ans1+"\n"+ans2+"\n"+ans3+"\n"+ans4;
    }
    
    public String getAnswer(int a)
    {
        String ret;
        switch (a) {
            case 1: ret = ans1;break;
            case 2: ret = ans2;break;
            case 3: ret = ans3;break;
            case 4: ret = ans4;break;
            default: ret = "No answer chosen";
        }
        return ret;
    }
   
    
}
