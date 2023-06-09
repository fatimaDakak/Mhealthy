package com.myapp.interImp;

import java.util.ArrayList;
import java.util.List;

public class HappyExpression extends Expression {
	
    public List<String> interpret(String context) {
        List<String> tips = new ArrayList<String>();
        
        // Add mental health tips for a happy mood to the list
        tips.add("It's great to hear that you're feeling happy!");
        tips.add("Keep doing what you're doing: If what you're currently doing is making you happy, then keep doing it! Continue to pursue your passions, spend time with loved ones, and engage in activities that bring you joy.");
        tips.add("Practice gratitude: Take time to appreciate the good things in your life. Make a habit of writing down a few things you're grateful for each day, and reflect on them often.");
        
        return tips;
    }
}
