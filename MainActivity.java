package com.example.tictactoe;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    TextView resultText;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, restartBtn;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int flag = 0;
    int count = 0;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeStatusBarAndTextColor();
        init();
        buttons = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
    }

    private void changeStatusBarAndTextColor() {
        Window window = getWindow();

        // Clear FLAG_TRANSLUCENT_STATUS flag
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // Add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // Set the status bar color
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

        // Set the text color of status bar
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        restartBtn = findViewById(R.id.restartBtn);
        restartBtn.setVisibility(View.INVISIBLE);

        resultText = findViewById(R.id.resultText);
    }

    public void checkBtn(View view){
        Button currentBtn = (Button) view;

        if (currentBtn.getText().toString().isEmpty()) {
            count++;

            if (flag == 0) {
                currentBtn.setText("X");
                flag = 1;
            } else {
                currentBtn.setText("O");
                flag = 0;
            }

            if (count > 4) {
               gameWinner();
            }
        }
    }

    private void gameWinner(){
        b1 = btn1.getText().toString();
        b2 = btn2.getText().toString();
        b3 = btn3.getText().toString();
        b4 = btn4.getText().toString();
        b5 = btn5.getText().toString();
        b6 = btn6.getText().toString();
        b7 = btn7.getText().toString();
        b8 = btn8.getText().toString();
        b9 = btn9.getText().toString();

        if (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) {
            result(b1);
            changeBtnColor(btn1,btn2,btn3);
            restart();
        } else if (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) {
            result(b4);
            changeBtnColor(btn4,btn5,btn6);
            restart();
        } else if (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) {
            result(b7);
            changeBtnColor(btn7,btn8,btn9);
            restart();
        } else if (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) {
            result(b1);
            changeBtnColor(btn1,btn4,btn7);
            restart();
        } else if (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) {
            result(b2);
            changeBtnColor(btn2,btn5,btn8);
            restart();
        } else if (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty()) {
            result(b3);
            changeBtnColor(btn3,btn6,btn9);
            restart();
        } else if (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) {
            result(b1);
            changeBtnColor(btn1,btn5,btn9);
            restart();
        } else if (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) {
            result(b3);
            changeBtnColor(btn3,btn5,btn7);
            restart();
        }else if (!b1.isEmpty() && !b2.isEmpty() && !b3.isEmpty() && !b4.isEmpty() && !b5.isEmpty() && !b6.isEmpty() && !b7.isEmpty() && !b8.isEmpty() && !b9.isEmpty()){
            resultText.setText(R.string.game_over);
            resultText.setTextColor(Color.RED);
            restart();
        }
    }

    private void restart(){
        restartBtn.setVisibility(View.VISIBLE);
        restartBtn.setOnClickListener(view -> reset());
    }

    private void result(String winner){
        if (winner.equals("O") || winner.equals("X")) {
            resultText.setText(String.format("%s%s", getString(R.string.winner), winner));
            resultText.setTextColor(Color.GREEN);

            for (Button button : buttons) {
                button.setClickable(false);
            }
        }
    }

    private void changeBtnColor(Button b1, Button b2, Button b3){

        ColorStateList colorStateListGreen = ColorStateList.valueOf(Color.GREEN);

        b1.setBackgroundTintList(colorStateListGreen);
        b2.setBackgroundTintList(colorStateListGreen);
        b3.setBackgroundTintList(colorStateListGreen);

    }

    private void resetBtnColor(Button button) {
        ColorStateList colorStateListDefault = ContextCompat.getColorStateList(this, R.color.btnColor);
        button.setBackgroundTintList(colorStateListDefault);
    }

    private void reset() {
        count = 0;
        flag = 0;
        resultText.setText("");
        restartBtn.setVisibility(View.INVISIBLE);

        for (Button button : buttons) {
            button.setText("");
            resetBtnColor(button);
            button.setClickable(true);
        }
    }

}