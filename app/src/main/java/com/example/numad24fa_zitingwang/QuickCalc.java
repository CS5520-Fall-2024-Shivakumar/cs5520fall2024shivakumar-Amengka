package com.example.numad24fa_zitingwang;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuickCalc extends AppCompatActivity implements View.OnClickListener {

    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn;
    Button plusBtn, minusBtn, deleteBtn, equalBtn;
    TextView calculatorTextBar;
    Boolean isDefault = true;
    Boolean resulted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quick_calc);

        calculatorTextBar = findViewById(R.id.calculatorTextBar);
        oneBtn = findViewById(R.id.oneBtn);
        twoBtn = findViewById(R.id.twoBtn);
        threeBtn = findViewById(R.id.threeBtn);
        fourBtn = findViewById(R.id.fourBtn);
        fiveBtn = findViewById(R.id.fiveBtn);
        sixBtn = findViewById(R.id.sixBtn);
        sevenBtn = findViewById(R.id.sevenBtn);
        eightBtn = findViewById(R.id.eightBtn);
        nineBtn = findViewById(R.id.nineBtn);
        zeroBtn = findViewById(R.id.zeroBtn);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        equalBtn = findViewById(R.id.equalBtn);


        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        fourBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);
        zeroBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        equalBtn.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        String curText;
        switch(view.getId()){
            case R.id.oneBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("1");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "1");
                break;
            case R.id.twoBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("2");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "2");
                break;
            case R.id.threeBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("3");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "3");
                break;
            case R.id.fourBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("4");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "4");
                break;
            case R.id.fiveBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("5");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "5");
                break;
            case R.id.sixBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("6");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "6");
                break;
            case R.id.sevenBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("7");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "7");
                break;
            case R.id.eightBtn:
                if (isDefault || resulted){
                    calculatorTextBar.setText("8");
                    isDefault = false;
                    resulted = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "8");
                break;
            case R.id.nineBtn:
                if (isDefault){
                    calculatorTextBar.setText("9");
                    isDefault = false;
                }
                else calculatorTextBar.setText(calculatorTextBar.getText() + "9");
                break;
            case R.id.zeroBtn:
                curText = calculatorTextBar.getText().toString();
                if (curText.isEmpty() || isDefault) break;
                else if (curText.charAt(curText.length() - 1) == '+' || curText.charAt(curText.length() - 1) == '-') break;
                calculatorTextBar.setText(calculatorTextBar.getText() + "0");
                break;
            case R.id.plusBtn:
                curText = calculatorTextBar.getText().toString();
                if (curText.length() == 1 && curText.equals("-")){
                    calculatorTextBar.setText("0");
                    isDefault = true;
                    break;
                }
                if (isDefault) break;
                if (!curText.isEmpty() && curText.charAt(curText.length() - 1) == '+') break;
                else if (!curText.isEmpty() && curText.charAt(curText.length() - 1) == '-') calculatorTextBar.setText(curText.substring(0, curText.length() - 1) + "+");
                else if (!curText.isEmpty()) calculatorTextBar.setText(calculatorTextBar.getText() + "+");

                break;
            case R.id.minusBtn:
                curText = calculatorTextBar.getText().toString();
                if (isDefault){
                    calculatorTextBar.setText("-");
                    isDefault = false;
                    break;
                }
                if (!curText.isEmpty() && curText.charAt(curText.length() - 1) == '-') break;
                else if (!curText.isEmpty() && curText.charAt(curText.length() - 1) == '+') calculatorTextBar.setText(curText.substring(0, curText.length() - 1) + "-");
                else if (!curText.isEmpty()) calculatorTextBar.setText(calculatorTextBar.getText() + "-");
                else calculatorTextBar.setText("-");
                break;
            case R.id.deleteBtn:
                curText = calculatorTextBar.getText().toString();
                if (!curText.isEmpty()) calculatorTextBar.setText(curText.substring(0, curText.length() - 1));
                if (curText.length() == 1){
                    calculatorTextBar.setText("0");
                    isDefault = true;
                }
                break;
            case R.id.equalBtn:
                curText = calculatorTextBar.getText().toString();
                if (curText.charAt(curText.length() - 1) == '+' || curText.charAt(curText.length() - 1) == '-') break;
                LinkedList<Long> numbers = new LinkedList<>();
                LinkedList<Character> operators = new LinkedList<>();
                String cur = "";
                for (Character ch : curText.toCharArray()){
                    if (ch == '+' || ch == '-'){
                        operators.add(ch);
                        if (!cur.isEmpty()){
                            numbers.add(Long.parseLong(cur));
                            cur = "";
                        }
                    }
                    else {
                        cur += ch;
                    }
                }
                if (!cur.isEmpty()) numbers.add(Long.parseLong(cur));

                if (operators.size() < numbers.size()) operators.addFirst('+');

                //Testing if all of the nums and operators are stored correctly
                for (long num: numbers){
                    Log.i("numbers", String.valueOf(num));
                }

                for (Character operator: operators){
                    Log.i("operator", String.valueOf(operator));
                }

                long res = 0;
                int index = 0;
                for (long num: numbers){
                    if (operators.get(index++) == '+') res += num;
                    else res -= num;
                }

                calculatorTextBar.setText(String.valueOf(res));
                resulted = true;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("curTextBar", calculatorTextBar.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorTextBar.setText(savedInstanceState.getString("curTextBar"));
    }
}