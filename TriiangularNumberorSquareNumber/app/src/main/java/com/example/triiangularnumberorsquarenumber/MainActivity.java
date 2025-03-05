package com.example.triiangularnumberorsquarenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static class NumberProperties {
        int number;

        public boolean numberIsTriangular() {
            int x = 1;
            int triangle = 0;
            while (triangle < number) {
                triangle = triangle + x;
                x++;
            }
            return triangle == number;
        }
        public boolean numberIsSquare(){
            double square_root = Math.sqrt(number);
            if (Math.ceil(square_root) == square_root) // check if the ceil or floor of the number is equal to the number itself
                                                      /* then its a whole number */
                return true;
            else
                return false;
        }
    }

    public void checkForCond(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        int numberFromApp = Integer.parseInt(editText.getText().toString()); //editText -> String -> int
        NumberProperties obj1 = new NumberProperties();
        obj1.number = numberFromApp;

        String msg = "";
        if (!(obj1.numberIsTriangular()) && !(obj1.numberIsSquare())){
            msg = "Number is Neither Triangular nor Square";
        }
        else if (obj1.numberIsTriangular() && obj1.numberIsSquare()){
            msg = "Number is both Triangular and Square";
        }
        else if (obj1.numberIsTriangular()){
            msg = "Number is Triangular";
        }
        else {
            msg = "Number is Square";
        }
        Toast.makeText(this, msg , Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}