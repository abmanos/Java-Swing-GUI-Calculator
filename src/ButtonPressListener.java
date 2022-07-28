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
                gui.getCalculation().setText("");
                break;
            case "CE":
                gui.getCalculation().setText("");
                gui.setPrevNum(0);
                gui.setPrevOperation(null);
                break;
            case "+":
                multipleOperationHandler();
                gui.setPrevOperation(Operations.PLUS);
                gui.setPrevNum(Double.parseDouble(gui.getCalculation().getText()));
                gui.getCalculation().setText("");
                break;
            case "-":
                multipleOperationHandler();
                gui.setPrevOperation(Operations.MINUS);
                gui.setPrevNum(Double.parseDouble(gui.getCalculation().getText()));
                gui.getCalculation().setText("");
                break;
            case "*":
                multipleOperationHandler();
                gui.setPrevOperation(Operations.MULTIPLY);
                gui.setPrevNum(Double.parseDouble(gui.getCalculation().getText()));
                gui.getCalculation().setText("");
                break;
            case "/":
                multipleOperationHandler();
                gui.setPrevOperation(Operations.DIVIDE);
                gui.setPrevNum(Double.parseDouble(gui.getCalculation().getText()));
                gui.getCalculation().setText("");
                break;
            case "=":
                double answer;
                switch (gui.getPrevOperation()){
                    case PLUS:
                        answer = gui.getPrevNum() + Double.parseDouble(gui.getCalculation().getText());
                        gui.getCalculation().setText((answer+""));
                        break;
                    case MINUS:
                        answer = gui.getPrevNum() - Double.parseDouble(gui.getCalculation().getText());
                        gui.getCalculation().setText((answer+""));
                        break;
                    case MULTIPLY:
                        answer = gui.getPrevNum() * Double.parseDouble(gui.getCalculation().getText());
                        gui.getCalculation().setText((answer+""));
                        break;
                    case DIVIDE:
                        answer = gui.getPrevNum() / Double.parseDouble(gui.getCalculation().getText());
                        gui.getCalculation().setText((answer+""));
                        break;
                }
                break;

        }

    }

    public void multipleOperationHandler() {
        if(gui.getPrevOperation() != null){
            switch (gui.getPrevOperation()) {
                case PLUS:
                    gui.setPrevNum(gui.getPrevNum() + Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case MINUS:
                    gui.setPrevNum(gui.getPrevNum() - Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case MULTIPLY:
                    gui.setPrevNum(gui.getPrevNum() * Double.parseDouble(gui.getCalculation().getText()));
                    break;
                case DIVIDE:
                    gui.setPrevNum(gui.getPrevNum() / Double.parseDouble(gui.getCalculation().getText()));
                    break;
            }
        }
    }
}
