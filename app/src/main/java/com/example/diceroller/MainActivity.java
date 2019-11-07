package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public int User_Score = 0;
    List<String> Questions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Questions();
    }


    public void on_button_click(View view) {

        if (view.getId() == R.id.Lucky_Button) {
            roll_the_dice(1);
        }

        if (view.getId() == R.id.DiceBreaker) {
            roll_the_dice(2);
        }

        if (view.getId() == R.id.Add_Question) {
            Intent intent = new Intent(this, Add_question.class);
            startActivityForResult(intent, 1);
        }

        if (view.getId() == R.id.Finish) {
            Intent intent = new Intent(this, share.class);
            String My_Score = ("My Score: " + User_Score);
            intent.putExtra(EXTRA_MESSAGE, My_Score);
            startActivityForResult(intent, 2);
        }
    }

    public void roll_the_dice(int value) {
        TextView tv = this.findViewById(R.id.Number);
        TextView Input = this.findViewById(R.id.Input_Box);
        TextView Text = this.findViewById(R.id.Message_Box);

        if (value == 1) {
            if (Input.length() <= 0) {
                Text.setText("Enter A Number");
            } else {
                int min = 1;
                int max = 6;

                Random random = new Random();

                int number = (random.nextInt(max - min + 1) + min);

                tv.setText(Integer.toString(number));
                Score(number);
            }

        }

        if (value == 2) {

            int min = 0;
            int max = (Questions.size() -1);

            Random random = new Random();

            int number = (random.nextInt(max - min + 1) + min);

            Display_Question(number);

        }
    }

    public void Score(int Guess_Value) {

        TextView Input = this.findViewById(R.id.Input_Box);
        TextView Points = this.findViewById(R.id.Point_Text);
        TextView Text = this.findViewById(R.id.Message_Box);

        int Value = Integer.parseInt( Input.getText().toString() );

        if (Value ==  Guess_Value) {
            User_Score ++;
            Points.setText("Points: "+Integer.toString(User_Score));
            Text.setText("Correct");
        } else {
            Text.setText("Incorrect");
        }

    }

    public void Questions() {
        Questions.add("If you could go anywhere in the world, where would you go?");
        Questions.add("If you were stranded on a desert island, what three things would you want to take with you?");
        Questions.add("If you could eat only one food for the rest of your life, what would that be?");
        Questions.add("If you won a million dollars, what is the first thing you would buy?");
        Questions.add("If you could spend the day with one fictional character, who would it be?");
        Questions.add("If you found a magic lantern and a genie gave you three wishes, what would you wish?");
    }

    public void Display_Question(int Roll) {
        TextView Text = this.findViewById(R.id.Message_Box);
        TextView tv = this.findViewById(R.id.Number);
        tv.setText("");
        Text.setText(Questions.get(Roll));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView Text = this.findViewById(R.id.Message_Box);

        if (resultCode == 0) {
            String Question_Value = data.getStringExtra(EXTRA_MESSAGE);
            Questions.add(Question_Value);
        }
        if (resultCode == 1) {
            Text.setText("No Question Added");
        }
    }
}
