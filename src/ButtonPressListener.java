import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPressListener implements ActionListener {
    CalculatorGUI gui;
    String text;

    public ButtonPressListener(CalculatorGUI gui, String text){
        this.gui = gui;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int textLength = gui.getCalculation().getText().length();

        switch(text){
            case "9": case "8": case "7":
            case "6": case "5": case "4":
            case "3": case "2": case "1":
            case "0": case "(": case ")":
                gui.getCalculation().setText(gui.getCalculation().getText() + text);
                break;
            case "<-":
                if(textLength <= 1){
                    gui.getCalculation().setText("");
                } else {
                    gui.getCalculation().setText(gui.getCalculation().getText().substring(0, textLength-1));
                }
                break;
            case "C":
                gui.history.setText("");
                gui.getCalculation().setText("");
                break;
            case "CE":
                gui.history.setText("");
                gui.getCalculation().setText("");
                gui.setCurrentAns(0);
                gui.setPrevOperation(null);
                break;
            case "+":
                updateCurrentAnswer();
                gui.history.setText(gui.getCurrentAns()+"+");
                gui.setPrevOperation(Operations.PLUS);
                gui.getCalculation().setText("");
                break;
            case "-":
                updateCurrentAnswer();
                gui.history.setText(gui.getCurrentAns()+"-");
                gui.setPrevOperation(Operations.MINUS);
                gui.getCalculation().setText("");
                break;
            case "*":
                updateCurrentAnswer();
                gui.history.setText(gui.getCurrentAns()+"*");
                gui.setPrevOperation(Operations.MULTIPLY);
                gui.getCalculation().setText("");
                break;
            case "/":
                updateCurrentAnswer();
                gui.history.setText(gui.getCurrentAns()+"/");
                gui.setPrevOperation(Operations.DIVIDE);
                gui.getCalculation().setText("");
                break;
            case "+/-":

                break;
            case "=":
                updateCurrentAnswer();
                gui.getCalculation().setText(gui.getCurrentAns()+"");
        }

    }

    public void updateCurrentAnswer(){
        if(gui.getPrevOperation() == null){
            gui.setCurrentAns(Double.parseDouble(gui.getCalculation().getText()));
        } else {
            switch (gui.getPrevOperation()) {
                case PLUS:
                    gui.setCurrentAns(gui.getCurrentAns() + Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case MINUS:
                    gui.setCurrentAns(gui.getCurrentAns() - Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case MULTIPLY:
                    gui.setCurrentAns(gui.getCurrentAns() * Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case DIVIDE:
                    gui.setCurrentAns(gui.getCurrentAns() / Double.parseDouble(gui.getCalculation().getText()));
                    break;
            }
        }
        System.out.println(gui.getCurrentAns());
        gui.history.setText(gui.getCurrentAns()+"");
    }
}
