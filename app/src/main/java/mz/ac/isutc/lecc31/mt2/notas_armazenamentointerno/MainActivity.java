package mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String FILE_NAME = "Notas";
    private ArrayList<Nota> notas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        notas = new ArrayList<Nota>();
        setContentView(binding.getRoot());


        binding.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                ArrayList<Nota> n = lerExternamente();
                String t = n.get(position).getTitulo();
                String c = n.get(position).getConteudo();

                i.putExtra("t", t);
                i.putExtra("c", c);
                startActivity(i);
            }
        });

        binding.gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Nota nota = new Nota(
                            binding.titleEt.getText()+"",
                            binding.contentEt.getText()+""
                    );
                    notas.add(nota);
                    gravarExternamente(notas);
                    Toast.makeText(MainActivity.this, "Nota gravada com sucesso", Toast.LENGTH_SHORT).show();
                    Adapter adapter = new Adapter(ler(), MainActivity.this);
                    binding.lista.setAdapter(adapter);
                    binding.titleEt.setText(null);
                    binding.contentEt.setText(null);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Preencha todos campos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }



    public void gravar(ArrayList<Nota> nota){

        String FILENAME = "hello_file";
        File file =getFileStreamPath (FILENAME);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nota);
            oos.close();
            fos.close();
        }catch (IOException exception){
            Toast.makeText(this, "Ocorreu um erro na gravacao", Toast.LENGTH_SHORT).show();
            System.out.println(exception.toString());
        }
    }

    public ArrayList<Nota> ler()  {
        String FILENAME = "hello_file";
        File file =getFileStreamPath (FILENAME);
        ArrayList<Nota> retorno = null;
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            retorno = (ArrayList<Nota>) ois.readObject();
            fis.close();
            ois.close();
        }catch (IOException | ClassNotFoundException exception ){
            Toast.makeText(this, "Ocorreu um erro na leitura", Toast.LENGTH_SHORT).show();
            System.out.println(exception.toString());
        }
        Toast.makeText(this, "Sucesso na leitura", Toast.LENGTH_SHORT).show();
        return retorno;
    }
    
    public void gravarExternamente(ArrayList<Nota> notas){
        try{
            File dir = Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOWNLOADS);
            File arquivo = new File(dir, "teste.obj");
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(notas);
        }catch (IOException exception){
            Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Nota> lerExternamente() {
        ArrayList<Nota> notas = new ArrayList<>();

        try {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Date d = new Date();
            String objName = "Yannick"+d.toString();
            File arquivo = new File(dir, objName+".obj");
            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            notas = (ArrayList<Nota>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException exception) {
            Toast.makeText(this, "Ocorreu um erro na leitura do arquivo", Toast.LENGTH_SHORT).show();
        }

        return notas;
    }


}