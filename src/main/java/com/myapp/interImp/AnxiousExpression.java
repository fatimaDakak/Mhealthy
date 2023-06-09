//package com.myapp.interImp;
//
//public class AnxiousExpression extends Expression {
//
//    public String interpret(String context) {
//        // Display Lottie animation and tips when the user is anxious
//    	String result = "You are feeling anxious. 😔\n <br>";
//        result += "Here are some tips to help you calm down:\n <br>";
//        result += "1.  <strong>Take deep breaths and focus on your breath\n </strong> <br>";
//        result += "2.  <strong>Practice progressive muscle relaxation</strong>\n <br>";
//        result += "3.  <strong>While listening to this relaxing music,try visualization techniques, like picturing a calming scene </strong>\n <br>";
//        result += "4.  <strong>Practice this breathing technique : </strong>\n <br>";
//        result += "5.  <strong>If you are still anxious and you need help, you can click the emergency button to notify your trusted contacts ! </strong> \n <br>";
//        result +=" <div id='lottie-animation'></div>";
//        // Embed the Lottie animation in the HTML
//        result += "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.10.2/lottie.min.js\" integrity=\"sha512-fTTVSuY9tLP+l/6c6vWz7uAQqd1rq3Q/GyKBN2jOZvJSLC5RjggSdboIFL1ox09/Ezx/AKwcv/xnDeYN9+iDDA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\"></script>\r\n"
//        		+ "\n";
//        result += "<script>\n";
//        result += "var animation = lottie.loadAnimation({\n";
//        result += "container: document.getElementById('lottie-animation'),\n";
//        result += "renderer: 'svg',\n";
//        result += "loop: true,\n";
//        result += "autoplay: true,\n";
//        result += "path: './json/breath.json'\n";
//        result += "});\n";
//        result += "</script>\n";
//        result += "<audio src=\"./asset/relax.mp3\" autoplay loop></audio>\n";
//
//
//        return result;
//    }
//}






package com.myapp.interImp;

import java.util.ArrayList;
import java.util.List;

public class AnxiousExpression extends Expression {

    public List<String> interpret(String context) {
        List<String> tips = new ArrayList<String>();

        // Display Lottie animation and tips when the user is anxious
        tips.add("You are feeling anxious. 😔");
        tips.add("Here are some tips to help you calm down:");
        tips.add("1.  Take deep breaths and focus on your breath");
        tips.add("2.  Practice progressive muscle relaxation");
        tips.add("3.  Listen to relaxing music,try visualization techniques, like picturing a calming scene");
        tips.add("4.  If you are still anxious and you need help, you can click the emergency button to notify your trusted contacts!");

        return tips;
    }
}

