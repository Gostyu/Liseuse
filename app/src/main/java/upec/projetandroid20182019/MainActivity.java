package upec.projetandroid20182019;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;


import static upec.projetandroid20182019.R.id.recyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    TextsAdapter ta;
    List<String> data; //les noms de fichiers
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isExternalStorageReadable()&& isReadExternalStoragePermission())
            printFilenames();
    }
    private void printFilenames(){
        rv=findViewById(recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        data = ReaderFiles.getInstance().getFilenames();
        ta = new TextsAdapter(this,data);
        rv.setAdapter(ta);
        DividerItemDecoration dId = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dId);
    }
    //Vérifie si une carte sd est présente dans le portable
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    /*Vérifie si on a la permission de lire les fichiers
     * dans le portable
     */
    private boolean isReadExternalStoragePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
           printFilenames();

    }
}
