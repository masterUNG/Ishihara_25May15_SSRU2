package appewtc.masterung.ishihara;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView questionTextView;
    private ImageView ishiharaImageView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;
    private Button answerButton;
    private int radioAnInt, indexAnInt;
    private SSRUmodel objSsrUmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //About Model
        aboutModel();

    }   //onCreate

    private void aboutModel() {

        objSsrUmodel = new SSRUmodel();
        objSsrUmodel.setOnSSRUmodelChangeListener(new SSRUmodel.OnSSRUmodelChangeListener() {
            @Override
            public void onSSRUmodelChangeListener(SSRUmodel ssrUmodel) {

                //Chang View
                changeView(ssrUmodel.getButtonAnInt());

            }   //event
        });

    }   //aboutModel

    private void changeView(int buttonAnInt) {

        //Change Image
        int intDrawabe[] = new int[]{R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04, R.drawable.ishihara_05,
                R.drawable.ishihara_06, R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};
        ishiharaImageView.setImageResource(intDrawabe[buttonAnInt]);

    }   // changeView

    private void radioController() {

        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                soundEffect();

                //Setup radioAnInt
                switch (checkedID) {
                    case R.id.radioButton:
                        radioAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        radioAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        radioAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        radioAnInt = 4;
                        break;
                    default:
                        radioAnInt = 0;
                        break;
                }

            }   // event
        });

    }   // radioController

    private void buttonController() {

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                soundEffect();

                //Check Zero
                checkZero();

            }   // event
        });

    }   // buttonController

    private void checkZero() {

        if (radioAnInt == 0) {

            Toast.makeText(MainActivity.this, "กรุณาตอบคำถาม ด้วยคะ", Toast.LENGTH_SHORT).show();

        } else {

            //Check Times
            checkTimes();

        }

    }   //checkZero

    private void checkTimes() {

        if (indexAnInt == 9) {

            //Intent to ShowScore
            intentToShowScore();

        } else {

            //Increase
            indexAnInt += 1;

            //Show Controller Call View
            questionTextView.setText(Integer.toString(indexAnInt + 1) + ". What is this ?" );

            //Show Controller Call Model
            objSsrUmodel.setButtonAnInt(indexAnInt);

        }   // if

    }   //checkTimes

    private void intentToShowScore() {
        Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
        startActivity(objIntent);
        finish();
    }

    private void soundEffect() {

        MediaPlayer myMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
        myMediaPlayer.start();
    }

    private void bindWidget() {

        questionTextView = (TextView) findViewById(R.id.textView2);
        ishiharaImageView = (ImageView) findViewById(R.id.imageView);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        answerButton = (Button) findViewById(R.id.button);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   //Main Class
