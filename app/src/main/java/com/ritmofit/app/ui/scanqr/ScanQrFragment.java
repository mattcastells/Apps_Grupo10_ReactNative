
package com.ritmofit.app.ui.scanqr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ritmofit.app.R;

public class ScanQrFragment extends Fragment {
    private TextView scanResultText;
    private View checkinInfoContainer;
    private TextView checkinClass, checkinTime, checkinLocation;
    private Button confirmCheckinButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan_qr, container, false);
        Button scanQrButton = view.findViewById(R.id.scanQrButton);
        scanResultText = view.findViewById(R.id.scanResultText);
        checkinInfoContainer = view.findViewById(R.id.checkinInfoContainer);
        checkinClass = view.findViewById(R.id.checkinClass);
        checkinTime = view.findViewById(R.id.checkinTime);
        checkinLocation = view.findViewById(R.id.checkinLocation);
        confirmCheckinButton = view.findViewById(R.id.confirmCheckinButton);

        scanQrButton.setOnClickListener(v -> {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setPrompt("Escanea un código QR");
            integrator.setBeepEnabled(true);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();
        });

        confirmCheckinButton.setOnClickListener(v -> {
            scanResultText.setText("¡Check-in realizado con éxito!");
            checkinInfoContainer.setVisibility(View.GONE);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Simular parseo de datos del QR (mock)
                // Ejemplo: "Yoga|Lunes 10:00|Sede Central"
                String qr = result.getContents();
                String[] datos = qr.split("\\|");
                String clase = datos.length > 0 ? datos[0] : "Yoga";
                String horario = datos.length > 1 ? datos[1] : "Lunes 10:00";
                String sede = datos.length > 2 ? datos[2] : "Sede Central";

                checkinClass.setText("Clase: " + clase);
                checkinTime.setText("Horario: " + horario);
                checkinLocation.setText("Sede: " + sede);
                checkinInfoContainer.setVisibility(View.VISIBLE);
                scanResultText.setText("");
            } else {
                scanResultText.setText("Escaneo cancelado");
                checkinInfoContainer.setVisibility(View.GONE);
            }
        }
    }
}
