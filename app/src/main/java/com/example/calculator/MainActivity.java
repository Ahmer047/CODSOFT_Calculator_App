package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txt_result, txt_input;
    MaterialButton b_dot, b_c, b_equal, b_zero, b_one, b_two, b_three, b_pls,
            b_four, b_five, b_six, b_min, b_seven, b_eight, b_nine, b_multi,
            b_ac, b_lb, b_rb, b_div;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_result = findViewById(R.id.result_txt);
        txt_input = findViewById(R.id.input_txt);

        assignId(b_c, R.id.b_c);
        assignId(b_zero, R.id.b_zero);
        assignId(b_dot, R.id.b_dot);
        assignId(b_equal, R.id.b_equal);

        assignId(b_one, R.id.b_one);
        assignId(b_two, R.id.b_two);
        assignId(b_three, R.id.b_three);
        assignId(b_pls, R.id.b_plus);

        assignId(b_four, R.id.b_four);
        assignId(b_five, R.id.b_five);
        assignId(b_six, R.id.b_six);
        assignId(b_min, R.id.b_mins);

        assignId(b_seven, R.id.b_seven);
        assignId(b_eight, R.id.b_eight);
        assignId(b_nine, R.id.b_nine);
        assignId(b_multi, R.id.b_multi);

        assignId(b_ac, R.id.b_ac);
        assignId(b_lb, R.id.b_lb);
        assignId(b_rb, R.id.b_rb);
        assignId(b_div, R.id.b_div);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = txt_input.getText().toString();

        if(buttonText.equals("AC")){
            txt_input.setText("");
            txt_result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            txt_input.setText(txt_result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        txt_input.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            txt_result.setText(finalResult);
        }
    }

    private String getResult(String dataToCalculate) {
        try{
            org.mozilla.javascript.Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable, dataToCalculate, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}
