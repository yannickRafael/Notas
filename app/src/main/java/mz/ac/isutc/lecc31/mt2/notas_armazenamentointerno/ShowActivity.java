package mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno.databinding.ActivityShowBinding;

public class ShowActivity extends AppCompatActivity {
    private ActivityShowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        String t = getIntent().getExtras().getString("t");
        String c = getIntent().getExtras().getString("c");

        binding.titleShow.setText(t+"");
        binding.contentShow.setText(c+"");
    }
}