import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ButtonPressListener implements ActionListener {
    CalculatorGUI gui;
    String text;

    public ButtonPressListener(CalculatorGUI gui, String text){
        this.gui = gui;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFormattedTextField calc = gui.calculation;
        JFormattedTextField history = gui.history;
        ArrayList<JButton> buttons = gui.allButtons;
        String calcText = calc.getText();
        int textLength = calcText.length();

        switch(text){
            case "9": case "8": case "7":
            case "6": case "5": case "4":
            case "3": case "2": case "1":
            case "0":
                calc.setText(calcText + text);
                break;
            case "<-":
                if(calcText.charAt((calcText.length()-1)) == '.'){
                    gui.hasDecimal = false;
                }
                if(textLength <= 1){
                    calc.setText("");
                } else {
                    calc.setText(calcText.substring(0, textLength-1));
                }
                break;
            case "Font":
                if(gui.font == 2){
                    gui.font = 0;
                } else {
                    gui.font++;
                }
                Font buttonFont = new Font(gui.fontList[gui.font], Font.PLAIN, 35);
                Font textFont = new Font(gui.fontList[gui.font], Font.PLAIN, 75);
                history.setFont(textFont);
                calc.setFont(textFont);
                for(JButton b : buttons){
                    b.setFont(buttonFont);
                }
                break;
            case "☼":
                if(gui.isLightMode){
                    history.setForeground(new Color(255,255,255));
                    history.setBackground(new Color(42, 42, 54));
                    calc.setForeground(new Color(255,255,255));
                    calc.setBackground(new Color(42, 42, 54));
                    for(JButton button : buttons){
                        button.setForeground(new Color(255,255,255));
                        button.setBackground(new Color(33, 34, 41));
                    }
                    gui.isLightMode = false;
                } else {
                    history.setForeground(new Color(42, 42, 54));
                    history.setBackground(new Color(255,255,255));
                    calc.setForeground(new Color(42, 42, 54));
                    calc.setBackground(new Color(255,255,255));
                    for(JButton button : buttons){
                        button.setForeground(new Color(33, 34, 41));
                        button.setBackground(new Color(255,255,255));
                    }
                    gui.isLightMode = true;
                }
                break;
            case "RNG":
                int RNG = ThreadLocalRandom.current().nextInt(0,10);
                calc.setText(calcText + RNG);
                break;
            case "%":
                double currentCalc = Double.parseDouble(gui.calculation.getText());
                gui.hasDecimal = true;
                calc.setText((currentCalc/100)+"");
                break;
            case "C": case "CE":
                history.setText("");
                calc.setText("");
                gui.setCurrentAns(0);
                gui.setPrevOperation(null);
                gui.hasDecimal = false;
                break;
            case "+":
                updateCurrentAnswer();
                history.setText(gui.getCurrentAns()+"+");
                gui.setPrevOperation(Operations.PLUS);
                calc.setText("");
                break;
            case "-":
                updateCurrentAnswer();
                history.setText(gui.getCurrentAns()+"-");
                gui.setPrevOperation(Operations.MINUS);
                calc.setText("");
                break;
            case "*":
                updateCurrentAnswer();
                history.setText(gui.getCurrentAns()+"*");
                gui.setPrevOperation(Operations.MULTIPLY);
                calc.setText("");
                break;
            case "/":
                updateCurrentAnswer();
                history.setText(gui.getCurrentAns()+"/");
                gui.setPrevOperation(Operations.DIVIDE);
                calc.setText("");
                break;
            case "+/-":
                if(calcText.length() == 0){
                    calc.setText("-");
                    break;
                }
                if(calcText.charAt(0) == '-'){
                    calc.setText(calcText.substring(1));
                } else {
                    calc.setText("-" + calcText);
                }
                break;
            case ".":
                if(!gui.hasDecimal){
                    calc.setText(calcText+".");
                    gui.hasDecimal = true;
                }
                break;
            case "=":
                try {
                    updateCurrentAnswer();
                } catch (Exception exc){
                    calc.setText("");
                    history.setText("Error: Invalid input, try again");
                }
                gui.setPrevOperation(null);
                calc.setText("");
        }

    }

    public void updateCurrentAnswer(){
        gui.hasDecimal = false;
        double currentAns = gui.getCurrentAns();
        double currentCalc = Double.parseDouble(gui.calculation.getText());
        if(gui.getPrevOperation() == null){
            gui.setCurrentAns(currentCalc);
        } else {
            switch (gui.getPrevOperation()) {
                case PLUS:
                    gui.setCurrentAns(currentAns + currentCalc);
                    break;
                case MINUS:
                    gui.setCurrentAns(currentAns - currentCalc);
                    break;
                case MULTIPLY:
                    gui.setCurrentAns(currentAns * currentCalc);
                    break;
                case DIVIDE:
                    gui.setCurrentAns(currentAns / currentCalc);
                    break;
            }
        }
        gui.history.setText(gui.getCurrentAns()+"");
    }
}