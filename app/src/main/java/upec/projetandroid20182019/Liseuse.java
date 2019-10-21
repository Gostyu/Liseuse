package upec.projetandroid20182019;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Liseuse extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liseuse);
        Intent intent= getIntent();
        String textFilename = intent.getStringExtra("textFile");
        tv=findViewById(R.id.textView2);
        ReaderFiles.getInstance().readATextFile(textFilename,tv);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
