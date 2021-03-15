package uz.eldor.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        imageView = findViewById(R.id.image_activity2);
        textView1 = findViewById(R.id.text1_activity2);
        textView2 = findViewById(R.id.text2_activity2);

        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");

        int image = exampleItem.getImageResource();
        String line1 = exampleItem.getText1();
        String line2 = exampleItem.getText2();

        imageView.setImageResource(image);
        textView1.setText(line1);
        textView2.setText(line2);

    }
}