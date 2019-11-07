package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Add_question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Intent intent = getIntent();
    }

    public void on_button_click(View view) {
        TextView Text = this.findViewById(R.id.Message_View);

        if (view.getId() == R.id.Add) {
            EditText Input = this.findViewById(R.id.Question_Input);
            if (Input.length() <= 0) {
                Text.setText("Enter A Question");
            } else {

                Intent intent = new Intent(this, MainActivity.class);
                String message = Input.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);

                setResult(0, intent);
                finish();
            }
        }

        if (view.getId() == R.id.Cancel) {
            Intent intent = new Intent(this, MainActivity.class);
            setResult(1, intent);
            finish();
        }
    }
}
