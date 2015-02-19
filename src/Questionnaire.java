public class Questionnaire {
    
    String user = "";
        
    Questions q1 = new Questions("q", 1, "What's your favourite color?", "Red", "Blue", "Green", "Other");
    Questions q2 = new Questions("q", 2, "What's your favourite car?", "BMW", "Mercedes", "Porsche", "Audi");
    Questions q3 = new Questions("q", 3, "What's your favourite fruit?", "Apple", "Banana", "Pear", "Orange");
    Questions q4 = new Questions("q", 4, "What's your favourite channel?", "BBC", "ITV", "Channel 4", "Channel 5");
    Questions q5 = new Questions("q", 5, "What's your favourite language?", "Java", "Python", "C#", "PHP");
    Questions q6 = new Questions("t", 6, "What is the symbol for equals to in Java?", "=", "==", "===", "!=");

    Questions[] questions={q1, q2, q3, q4, q5, q6};
    
    public Questionnaire(){}
  
}

