package upec.projetandroid20182019;

import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderFiles{
        public static ReaderFiles getInstance() {
            return new ReaderFiles();
        }
        private ReaderFiles(){}
        //Donne la liste des fichiers texte dans le dossier DOCUMENTS du portable
        public List<String> getFilenames(){
            List<String> fileNames = new ArrayList<>();
            File directory = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOCUMENTS);
            File [] files = directory.listFiles();
            if(files!=null){
                for(File file : files){
                    if(file.isFile() && (file.getName().endsWith(".txt"))){
                        fileNames.add(file.getName());
                    }
                }
                return fileNames;
            }
            return null;
        }
        //Lit une fichier texte et affiche son contenu dans une textView
        public void readATextFile(String filename, TextView tv){
            File file = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOCUMENTS+"/"+filename);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));
                String line;
                while((line=br.readLine())!=null){
                    tv.setText(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("Lecture de fichiers","fichier non trouvé");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
