package com.sleepless_entertainment.drowsy.coolcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalcActivity extends Activity {

//    TODO: Handle DivideByZero exception
//    TODO: Allow using multiple expressions
//    TODO: Display function history
//    TODO: Add additional functionality: Parenthesis, Decimal, Backspace, Negative Numbers, Order Of Operations, Square Root, Exponents
//    TODO: End Goal: Graphing Calculator-like functionality

//    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn, multBtn, divBtn, addBtn, subBtn, clrBtn, eqlBtn;
    TextView resultViewer; //TODO: Add second TextView for displaying entire function

    boolean readyToClear = false;
    String firstNum;
    operation operationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        resultViewer = findViewById(R.id.calcResultText);
//        oneBtn = findViewById(R.id.numberOne);
    }

    private enum operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    //region Helper Methods
    private boolean isCleared() {
        return resultViewer.getText().toString().equals("0.0");
    }

    private void addToViewer(String val) {
        if (isCleared() || readyToClear) {
            resultViewer.setText(val);
            readyToClear = false;
        }
        else {
            resultViewer.setText(resultViewer.getText().toString().concat(val).trim());
        }
    }

    private Double roundDec(double val) {
//        Number of zeros represents the number of decimal places to round to
        return (double)Math.round(val * 100000d) / 100000d;
    }

    private String evaluateFunc(String secNum) {
        Double result;
        switch (operationType) {
            case ADD:
                result = Double.parseDouble(firstNum) + Double.parseDouble(secNum);
                break;
            case SUBTRACT:
                result = Double.parseDouble(firstNum) - Double.parseDouble(secNum);
                break;
            case MULTIPLY:
                result = Double.parseDouble(firstNum) * Double.parseDouble(secNum);
                break;
            case DIVIDE:
                result = Double.parseDouble(firstNum) / Double.parseDouble(secNum);
                break;
            default:
                result = 0.0d;
                break;
        }

        if (result - (Math.ceil(result)) < 0) {
//            Result is a decimal
            return String.valueOf(roundDec(result));
        }
        else {
//            Result is not a decimal
            return String.valueOf(result.intValue());
        }
    }

    private void operate(operation type) {

    }
    //endregion


    //region Button Listeners
    public void oneClick(View view) {
        addToViewer("1");
    }

    public void twoClick(View view) {
        addToViewer("2");
    }

    public void threeClick(View view) {
        addToViewer("3");
    }

    public void fourClick(View view) {
        addToViewer("4");
    }

    public void fiveClick(View view) {
        addToViewer("5");
    }

    public void sixClick(View view) {
        addToViewer("6");
    }

    public void sevenClick(View view) {
        addToViewer("7");
    }

    public void eightClick(View view) {
        addToViewer("8");
    }

    public void nineClick(View view) {
        addToViewer("9");
    }

    public void zeroClick(View view) {
        addToViewer("0");
    }

    public void multClick(View view) {
        firstNum = resultViewer.getText().toString();
        resultViewer.setText("0.0");
        operationType = operation.MULTIPLY;
    }

    public void divClick(View view) {
        firstNum = resultViewer.getText().toString();
        resultViewer.setText("0.0");
        operationType = operation.DIVIDE;
    }

    public void addClick(View view) {
        firstNum = resultViewer.getText().toString();
        resultViewer.setText("0.0");
        operationType = operation.ADD;
    }

    public void subClick(View view) {
        firstNum = resultViewer.getText().toString();
        resultViewer.setText("0.0");
        operationType = operation.SUBTRACT;
    }

    public void eqlClick(View view) {
        resultViewer.setText(evaluateFunc(resultViewer.getText().toString()));
        readyToClear = true;
    }

    public void clrClick(View view) {
        firstNum = null;
        operationType = null;
        resultViewer.setText("0.0");
    }
    //endregion
}
