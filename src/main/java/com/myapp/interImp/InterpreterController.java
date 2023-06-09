package com.myapp.interImp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterController {
    private Expression expression;

    public List<String> interpret(String context) {
        List<String> results = new ArrayList<>();
        String feeling = extractFeeling(context);
        switch (feeling != null ? feeling.toLowerCase() : "") {
            case "happy":
                expression = new HappyExpression();
                results.addAll(expression.interpret(context));
                break;
            case "sad":
                expression = new SadExpression();
                results.addAll(expression.interpret(context));
                break;
            case "anxious":
                expression =  new AnxiousExpression();
                results.addAll(expression.interpret(context));
                break;
            default:
                results.add("I'm sorry, I don't understand how you're feeling. Would you paraphrase it?");
        }
        return results;
    }

    public String extractFeeling(String context) {
        //patterns 
    	Pattern happyPattern = Pattern.compile("(?i)happy|(?i)I'm feeling happy|(?i)I feel happy|(?i)I'm in a happy mood|(?i)My mood is happy|(?i)I'm in a good mood|(?i)(?i)I'm feeling joyful|(?i)I feel joyful|(?i)I'm happy",Pattern.CASE_INSENSITIVE);
        Pattern sadPattern = Pattern.compile("(?i)sad|(?i)I'm feeling sad|(?i)I feel sad|(?i)I feel bad|(?i)I'm in a sad mood|(?i)My mood is sad|(?i)I feel down|(?i)I feel blue|(?i)I feel sadness|(?i)I'm sad", Pattern.CASE_INSENSITIVE);
        Pattern anxiousPattern = Pattern.compile("(?i)anxious|(?i)I'm feeling anxious|(?i)I have anxiety|(?i)I feel anxious|(?i)I'm in an anxious mood|(?i)My mood is anxious|(?i)I'm feeling worried|(?i)I feel nervous|(?i)I'm anxious", Pattern.CASE_INSENSITIVE);

    	// Try to match the user's input against the regular expressions
        Matcher happyMatcher = happyPattern.matcher(context);
        Matcher sadMatcher = sadPattern.matcher(context);
        Matcher anxiousMatcher = anxiousPattern.matcher(context);

        if (happyMatcher.find()) {
            return "happy";
        } else if (sadMatcher.find()) {
            return "sad";
        } else if (anxiousMatcher.find()) {
            return "anxious";
        } else {
            return null;
        }
    }
}
