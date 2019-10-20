package com.example.backtothemoon;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Board extends AppCompatActivity {

    private int [ ] buttons = new int[7];
    private int positionsButtons = 0;
    private int positionQuestion = 0;
    private int score = 0;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_board);
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        if (!(name.equals("")) && name.length() < 8)
        {
            TextView nameScore = findViewById(R.id.textView);
            nameScore.setText(name+":");
        }
        TextView scoreView = findViewById(R.id.textView2);
        scoreView.setText(String.valueOf(score));
        Toast.makeText(this,"Touch the gray rectangle.",Toast.LENGTH_LONG).show();
        populateArrayButtons();
        disableButtons();
    }

    public void populateArrayButtons()
    {
        buttons[0] = R.id.btn19;
        buttons[1] = R.id.btn17;
        buttons[2] = R.id.btn15;
        buttons[3] = R.id.btn11;
        buttons[4] = R.id.btn7;
        buttons[5] = R.id.btn5;
        buttons[6] = R.id.btn3;
    }

    public void disableButtons()
    {
        ImageButton imageButton = findViewById(R.id.btn17);
        imageButton.setEnabled(false);
        imageButton = findViewById(R.id.btn15);
        imageButton.setEnabled(false);
        imageButton = findViewById(R.id.btn11);
        imageButton.setEnabled(false);
        imageButton = findViewById(R.id.btn7);
        imageButton.setEnabled(false);
        imageButton = findViewById(R.id.btn5);
        imageButton.setEnabled(false);
        imageButton = findViewById(R.id.btn3);
        imageButton.setEnabled(false);
    }

    public void touchBtn(View view)
    {
        if (positionsButtons < buttons.length) {
            alertDialogQuestion(view);
        }
    }

    public void alertDialogQuestion(final View view)
    {
        final AlertDialog.Builder popupQuestion = new AlertDialog.Builder(this);
        RadioButton OptionA = new RadioButton(this);
        RadioButton OptionB = new RadioButton(this);
        RadioButton OptionC = new RadioButton(this);
        if (positionsButtons == 0) {
            popupQuestion.setMessage("What is the name of the rocket used for Apollo Missions?");
            OptionA.setText("(A) Saturn V");
            OptionB.setText("(B) Apollo");
            OptionC.setText("(C) Minotaur I");
        }
        else if(positionsButtons == 1)
        {
            popupQuestion.setMessage("Which Apollo Mission did not succeed to land in the moon?");
            OptionC.setText("(C) Apollo 13");
            OptionA.setText("(A) Apollo 15");
            OptionB.setText("(B) Apollo 5");

        }
        else if(positionsButtons == 2)
        {
            popupQuestion.setMessage("What type of fuel the rocket requires?");
            OptionA.setText("(A) Gasoline");
            OptionB.setText("(B) Liquid hydrogen and oxigen");
            OptionC.setText("(C) Alcohol");
        }
        else if(positionsButtons == 3)
        {
            popupQuestion.setMessage("What is the name of the first successful mission that made contact with the moon?");
            OptionA.setText("(A) Luna 10");
            OptionB.setText("(B) Luna 2");
            OptionC.setText("(C) Luna 30");

        }
        else if(positionsButtons == 4)
        {
            popupQuestion.setMessage("Who is the first astronaut to walk on the moon?");
            OptionA.setText("(A) Neil Armstrong");
            OptionB.setText("(B) Buzz Lightyear");
            OptionC.setText("(C) Tony Stark");

        }
        else if (positionsButtons == 5)
        {
            popupQuestion.setMessage("What year was the first land on the moon?");
            OptionA.setText("(A) 2019");
            OptionB.setText("(B) 1985");
            OptionC.setText("(C) 1969");
        }
        else if(positionsButtons == 6)
        {
            popupQuestion.setMessage("What is the name of the next mission to the moon?");
            OptionA.setText("(A) Artemis");
            OptionB.setText("(B) Explorer");
            OptionC.setText("(C) Ultimato");
        }
        final RadioGroup OptionsQuestions = new RadioGroup(this);
        OptionA.setTextColor(Color.BLACK);
        OptionB.setTextColor(Color.BLACK);
        OptionC.setTextColor(Color.BLACK);
        OptionsQuestions.addView(OptionA);
        OptionsQuestions.addView(OptionB);
        OptionsQuestions.addView(OptionC);
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(OptionsQuestions);
        popupQuestion.setView(linearLayout);
        popupQuestion.setCancelable(false);
        popupQuestion.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RadioButton radioOption = linearLayout.findViewById(OptionsQuestions.getCheckedRadioButtonId());
                if (radioOption != null) {
                    String option = radioOption.getText().toString();
                    Log.d("Answer:",option);
                    Log.d("Position:",String.valueOf(positionsButtons));
                    if (positionQuestion == 0) {
                        Log.d("Anser:",option);
                        if (option.equals("(A) Saturn V")) {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        } else {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (A) Saturn V!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (positionQuestion == 1)
                    {
                        if (option.equals("(C) Apollo 13"))
                        {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        }
                        else
                        {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (C) Apollo 13!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (positionQuestion == 2)
                    {
                        if (option.equals("(B) Liquid hydrogen and oxigen"))
                        {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        }
                        else
                        {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (B) Liquid hydrogen and oxigen!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (positionQuestion == 3)
                    {
                        if (option.equals("(B) Luna 2"))
                        {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        }
                        else
                        {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (B) Luna 2!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (positionQuestion == 4)
                    {
                        if (option.equals("(A) Neil Armstrong"))
                        {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        }
                        else
                        {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (A) Luna 2!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (positionQuestion == 5)
                    {
                       if (option.equals("(C) 1969"))
                       {
                           Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                           score++;
                           TextView scoreView = findViewById(R.id.textView2);
                           scoreView.setText(String.valueOf(score));
                       }
                       else
                       {
                           Toast.makeText(Board.this,"Incorrect answer, the correct answer is (C) 1969!",Toast.LENGTH_LONG).show();
                       }
                    }
                    else if (positionQuestion == 6)
                    {
                        if (option.equals("(A) Artemis"))
                        {
                            Toast.makeText(Board.this,"Correct answer!",Toast.LENGTH_LONG).show();
                            score++;
                            TextView scoreView = findViewById(R.id.textView2);
                            scoreView.setText(String.valueOf(score));
                        }
                        else
                        {
                            Toast.makeText(Board.this,"Incorrect answer, the correct answer is (A) Artemis!",Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(Board.this,"FINISH GAME " + name + " " + score + "/7",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Board.this,"Invalid answer!",Toast.LENGTH_LONG).show();
                }
                if (positionsButtons > 0) {
                    ImageButton imageButtonPrevious = findViewById(buttons[positionsButtons - 1]);
                    imageButtonPrevious.setImageResource(R.color.black);
                }
                if (positionsButtons != buttons.length-1) {
                    ImageButton imageButtonNext = findViewById(buttons[positionsButtons + 1]);
                    imageButtonNext.setImageResource(R.drawable.borderimagebutton);
                    imageButtonNext.setEnabled(true);
                    positionsButtons++;
                }
                ImageButton imageButtonCurrent = findViewById(view.getId());
                imageButtonCurrent.setEnabled(false);
                imageButtonCurrent.setBackgroundColor(0);
                imageButtonCurrent.setImageResource(R.drawable.austronaut);
                if (positionsButtons == 3 || positionsButtons == 4)
                {
                    imageButtonCurrent.setRotationY(180);
                }
                positionQuestion++;
            }
        });
        popupQuestion.show();
    }
}
