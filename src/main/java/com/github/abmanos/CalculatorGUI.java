package com.github.abmanos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorGUI {

    JFrame frame = new JFrame("Java Swing Calculator");
    JPanel grid = new JPanel();
    int centX = (int) getScreenSize().getWidth();
    int centY = (int) getScreenSize().getHeight();
    int defaultWidth = (int) (centX * 0.5);
    int defaultHeight = (int) (centY * 0.8);
    JFormattedTextField history = new JFormattedTextField();
    JFormattedTextField calculation = new JFormattedTextField();
    ArrayList<JButton> allButtons = new ArrayList<JButton>();
    Operations prevOperation;
    Font defaultTextFont = new Font(Font.MONOSPACED, Font.PLAIN, 85);
    Font defaultButtonFont = new Font(Font.MONOSPACED, Font.PLAIN, 35);
    String[] fontList = new String[]{Font.DIALOG, Font.SERIF, Font.MONOSPACED};
    int font = -1;
    boolean isLightMode = false;
    double currentAns;
    boolean hasDecimal = false;

    public CalculatorGUI(){}
    public void setup() {
        frame.setBackground(new Color(26, 26, 33));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        frame.setAlwaysOnTop(true);
        frame.setLocation((centX/2) - defaultWidth/2, (centY/2) - defaultHeight/2);
        grid.setLayout(new GridLayout(3,1,0,1));
        grid.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        frame.add(grid);
        setupTextAreas();
        setupButtons();
        frame.pack();
        frame.setVisible(true);
    }
    private void setupButtons(){
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(6,4,2,2));
        String[] buttonText = {"☼", "C", "CE","<-",
                               "Font", "RNG", "%", "+",
                               "7", "8", "9", "-",
                               "4", "5", "6", "*",
                               "1", "2", "3", "/",
                               ".", "0", "+/-", "="};
        for(String text : buttonText){
            allButtons.add(new JButton(text));
        }
        buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for(JButton button : allButtons){
            button.setFont(defaultButtonFont);
            button.setForeground(new Color(255,255,255));
            button.setBackground(new Color(33, 34, 41));
            button.addActionListener(new ButtonPressListener(this, button.getText()));
            buttons.add(button);
        }
        grid.add(buttons);
    }

    private void setupTextAreas(){
        history.setForeground(new Color(255,255,255));
        history.setBackground(new Color(42, 42, 54));
        history.setFont(defaultTextFont);
        history.setEditable(false);
        history.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        calculation.setForeground(new Color(255,255,255));
        calculation.setBackground(new Color(42, 42, 54));
        calculation.setFont(defaultTextFont);
        calculation.setEditable(false);
        calculation.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        grid.add(history);
        grid.add(calculation);

    }

    /*
     * Getters & Setters
     */
    private Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public Operations getPrevOperation() {
        return prevOperation;
    }

    public void setPrevOperation(Operations prevOperation) {
        this.prevOperation = prevOperation;
    }

    public double getCurrentAns() {
        return currentAns;
    }

    public void setCurrentAns(double currentAns) {
        this.currentAns = currentAns;
    }


}
