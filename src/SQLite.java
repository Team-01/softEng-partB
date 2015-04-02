import java.sql.*;
import java.util.ArrayList;

public class SQLite
{
    public SQLite()
    {
        selectQuestions();
        selectSettings();
        selectStudents();
    }
    
    Connection c = null;
    Statement stmt = null;
    
    ArrayList<String> settingsID = new ArrayList<String>();
    ArrayList<String> settingsAdminPassword = new ArrayList<String>();
    ArrayList<Integer> settingsColorR = new ArrayList<Integer>();
    ArrayList<Integer> settingsColorG = new ArrayList<Integer>();
    ArrayList<Integer> settingsColorB = new ArrayList<Integer>();
    
    
    ArrayList<String> questionsID = new ArrayList<String>();
    ArrayList<String> questionsNumber = new ArrayList<String>();
    ArrayList<String> questionsQuestion = new ArrayList<String>();
    ArrayList<String> questionsQuesOrTest = new ArrayList<String>();
    
    ArrayList<String> studentsID = new ArrayList<String>();
    ArrayList<String> studentsStuID = new ArrayList<String>();
    ArrayList<String> studentsStuName = new ArrayList<String>();
    ArrayList<String> studentsStuStudyType = new ArrayList<String>();
    ArrayList<String> studentsStuEmail = new ArrayList<String>();
    ArrayList<String> studentsMemberOfTeam = new ArrayList<String>();
    ArrayList<String> studentsPreviousSubject = new ArrayList<String>();
    ArrayList<String> studentsTrSH = new ArrayList<String>();
    ArrayList<String> studentsTrIMP = new ArrayList<String>();
    ArrayList<String> studentsTrCF = new ArrayList<String>();
    ArrayList<String> studentsTrCO = new ArrayList<String>();
    ArrayList<String> studentsTrTW = new ArrayList<String>();
    ArrayList<String> studentsTrRI = new ArrayList<String>();
    ArrayList<String> studentsTrPL = new ArrayList<String>();
    ArrayList<String> studentsTrME = new ArrayList<String>();
    ArrayList<String> studentsTrSP = new ArrayList<String>();
    ArrayList<String> studentsTeamRole = new ArrayList<String>();
    ArrayList<String> studentsTestScore = new ArrayList<String>();
    ArrayList<String> studentsModuleMark = new ArrayList<String>();
    ArrayList<String> studentsAverageMark = new ArrayList<String>();
   
    public void refresh()
    {
        selectStudents();
        selectQuestions();
        selectSettings();
    }
       
    public void modify(String sql)
    {
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:src/resources/SE.db");
          //System.out.println("Opened database successfully");
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          stmt.executeUpdate(sql);

          stmt.close();
          c.commit();
          c.close();
        }
          catch ( Exception e ) 
        {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    
    public ResultSet selectStart(String sql)
    {
      ResultSet rs = null;
      try 
      {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:src/resources/SE.db");
      c.setAutoCommit(false);
      //System.out.println("Opened database successfully");

      stmt = c.createStatement();
      rs = stmt.executeQuery(sql);
      
      } 
      catch ( Exception e ) 
      {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
      return rs;
    }

    
    public void selectEnd(ResultSet rs)
    {
        try {
        rs.close();
        stmt.close();
        c.close();
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
 
    public void selectSettings()
    {
        if (settingsID.size() > 0) 
        {
            settingsID.clear();
            settingsAdminPassword.clear();
            settingsColorR.clear();
            settingsColorG.clear();
            settingsColorB.clear();
        }
        
        ResultSet results = selectStart("SELECT * FROM settings;");
        try
        {
            while (results.next())
            {
                settingsID.add(results.getString("ID").toString());
                settingsAdminPassword.add(results.getString("adminPassword").toString());
                settingsColorR.add(results.getInt("colorR"));
                settingsColorG.add(results.getInt("colorG"));
                settingsColorB.add(results.getInt("colorB"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error with 'settings' table select");
        }
        selectEnd(results);
    }
    
    public void selectQuestions()
    {
        if (questionsID.size() > 0) 
        {
            questionsID.clear();
            questionsNumber.clear();
            questionsQuestion.clear();
            //questionsQuesOrTest.clear();
        }
        
        ResultSet results = selectStart("SELECT * FROM questions ORDER BY ID;");
        try
        {
            while (results.next())
            {
                questionsID.add(results.getString("ID"));
                questionsNumber.add(results.getString("number"));
                questionsQuestion.add(results.getString("question"));
                //questionsQuesOrTest.add(results.getString("quesOrTest"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error with 'questions' table select");
        }
        selectEnd(results);
    }
    
    
    public void selectStudents()
    {
        if (studentsID.size() > 0) 
        {
            studentsID.clear();
            studentsStuID.clear();
            studentsStuName.clear();
            studentsStuStudyType.clear();
            studentsStuEmail.clear();
            studentsMemberOfTeam.clear();
            studentsPreviousSubject.clear();
            studentsTrSH.clear();
            studentsTrIMP.clear();
            studentsTrCF.clear();
            studentsTrCO.clear();
            studentsTrTW.clear();
            studentsTrRI.clear();
            studentsTrPL.clear();
            studentsTrME.clear();
            studentsTrSP.clear();
            studentsTeamRole.clear();
            studentsTestScore.clear();
            studentsModuleMark.clear();
            studentsAverageMark.clear();
        }
        
        ResultSet results = selectStart("SELECT * FROM students ORDER BY ID;");
        try
        {
            while (results.next())
            {
                studentsID.add(results.getString("ID"));
                studentsStuID.add(results.getString("stuID"));
                studentsStuName.add(results.getString("stuName"));
                studentsStuStudyType.add(results.getString("stuStudyType"));
                studentsStuEmail.add(results.getString("stuEmail"));
                studentsMemberOfTeam.add(results.getString("memberOfTeam"));
                studentsPreviousSubject.add(results.getString("previousSubject"));
                studentsTrSH.add(results.getString("trSH"));
                studentsTrIMP.add(results.getString("trIMP"));
                studentsTrCF.add(results.getString("trCF"));
                studentsTrCO.add(results.getString("trCO"));
                studentsTrTW.add(results.getString("trTW"));
                studentsTrRI.add(results.getString("trRI"));
                studentsTrPL.add(results.getString("trPL"));
                studentsTrME.add(results.getString("trME"));
                studentsTrSP.add(results.getString("trSP"));
                studentsTeamRole.add(results.getString("teamRole"));
                studentsTestScore.add(results.getString("testScore"));
                studentsModuleMark.add(results.getString("moduleMark"));
                studentsAverageMark.add(results.getString("averageMark"));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error with 'students' table select");
        }
        selectEnd(results);
    }
    
}
