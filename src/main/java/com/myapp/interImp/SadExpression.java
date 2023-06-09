package com.myapp.interImp;

import java.util.ArrayList;
import java.util.List;

public class SadExpression extends Expression {
    
    public List<String> interpret(String context) {
        
        List<String> tips = new ArrayList<>();
        tips.add("I'm sorry to hear that you're feeling sad 😔 Here are some tips that might help:");
        tips.add("- Try talking to someone you trust about how you're feeling.");
        tips.add("- It's important to let yourself feel your emotions. Don't try to push them away or ignore them.");
        tips.add("- It's okay to not be okay. Remember that you're not alone.");
        tips.add("- You can use our Notes section as your journal to express exactly what you feel. Click here to access it: <a href=\"notes\">Your journal</a>.");
        
        return tips;
    }
}
