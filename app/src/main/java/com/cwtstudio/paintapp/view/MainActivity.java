package com.cwtstudio.paintapp.view;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.cwtstudio.paintapp.databinding.ActivityMainBinding;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int current_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        current_color = Color.BLACK;
        binding.signatureView.setPenColor(current_color);
        //update pensize text view
        binding.penSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.txtPenSize.setText(progress + " dp");
                binding.signatureView.setPenSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.btnDelete.setOnClickListener(v -> {
            binding.signatureView.clearCanvas();
        });

        binding.btnErase.setOnClickListener(v -> {
            binding.signatureView.setPenColor(binding.signatureView.getBackgroundColor());
        });

        binding.btnPicker.setOnClickListener(v -> {
            pickColor();
        });
        binding.btnDraw.setOnClickListener(v -> {
            binding.signatureView.setPenColor(current_color);
        });

    }

    private void pickColor() {
        new AmbilWarnaDialog(this, current_color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                current_color = color;
                binding.signatureView.setPenColor(color);
            }
        }).show();
    }

}