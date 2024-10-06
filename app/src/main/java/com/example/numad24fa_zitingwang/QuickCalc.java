package com.example.numad24fa_zitingwang;

import android.os.Bundle;
import android.os.PersistableBundle;
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

public class QuickCalc extends AppCompatActivity implements View.OnClickListener {

    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn;
    Button plusBtn, minusBtn, deleteBtn, equalBtn;
    TextView calculatorTextBar;

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
        switch(view.getId()){
            case R.id.oneBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "1");
                break;
            case R.id.twoBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "2");
                break;
            case R.id.threeBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "3");
                break;
            case R.id.fourBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "4");
                break;
            case R.id.fiveBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "5");
                break;
            case R.id.sixBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "6");
                break;
            case R.id.sevenBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "7");
                break;
            case R.id.eightBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "8");
                break;
            case R.id.nineBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "9");
                break;
            case R.id.zeroBtn:
                calculatorTextBar.setText(calculatorTextBar.getText() + "0");
                break;
            case R.id.deleteBtn:
                String cur = calculatorTextBar.getText().toString();
                if (!cur.isEmpty()) calculatorTextBar.setText(cur.substring(0, cur.length() - 1));
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