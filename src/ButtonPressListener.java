import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPressListener implements ActionListener {
    JFormattedTextField calculation;
    String text;
    public ButtonPressListener(JFormattedTextField calculation, String text){
        this.calculation = calculation;
        this.text = text;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int textLength = calculation.getText().length();

        switch(text){
            case "9": case "8": case "7":
            case "6": case "5": case "4":
            case "3": case "2": case "1":
            case "0": case "+": case "-":
            case "*": case "/": case "(":
            case ")":
                calculation.setText(calculation.getText() + text);
                break;
            case "<-":
                if(textLength <= 1){
                    calculation.setText("");
                } else {
                    calculation.setText(calculation.getText().substring(0, textLength-1));
                }
                break;
            case "C": case "CE":
                calculation.setText("");
                break;
            case "=":
                Calculate
        }
    }
}
