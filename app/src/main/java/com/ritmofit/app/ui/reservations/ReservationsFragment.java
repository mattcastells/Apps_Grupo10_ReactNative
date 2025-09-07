
package com.ritmofit.app.ui.reservations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ritmofit.app.R;

public class ReservationsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);


    Spinner classSpinner = view.findViewById(R.id.classSpinner);
    Spinner timeSpinner = view.findViewById(R.id.timeSpinner);
    Button bookButton = view.findViewById(R.id.bookButton);
    LinearLayout myReservationsContainer = view.findViewById(R.id.myReservationsContainer);
    TextView noReservationsText = view.findViewById(R.id.noReservationsText);

    // Mostrar mensaje si no hay reservas
    if (myReservationsContainer.getChildCount() == 0) {
        noReservationsText.setVisibility(View.VISIBLE);
    } else {
        noReservationsText.setVisibility(View.GONE);
    }

        // Mock de clases y horarios
        String[] clases = {"Yoga", "Funcional", "Zumba", "Spinning"};
        String[][] horarios = {
                {"Lunes 10:00", "Miércoles 10:00"},
                {"Martes 18:00", "Jueves 18:00"},
                {"Viernes 19:00"},
                {"Sábado 11:00", "Domingo 11:00"}
        };

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, clases);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, horarios[0]);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        classSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> newTimeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, horarios[position]);
                newTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                timeSpinner.setAdapter(newTimeAdapter);
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        bookButton.setOnClickListener(v -> {
            String clase = (String) classSpinner.getSelectedItem();
            String horario = (String) timeSpinner.getSelectedItem();
            if (clase == null || horario == null) {
                Toast.makeText(getContext(), "Selecciona clase y horario", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Reserva realizada para " + clase + " el " + horario, Toast.LENGTH_LONG).show();
                // Crear layout horizontal para reserva y botón eliminar
                LinearLayout reservaLayout = new LinearLayout(getContext());
                reservaLayout.setOrientation(LinearLayout.HORIZONTAL);
                reservaLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

                TextView reservaView = new TextView(getContext());
                reservaView.setText("• " + clase + " - " + horario);
                reservaView.setTextSize(18);
                reservaView.setPadding(0, 12, 16, 12);
                reservaView.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));

                Button deleteButton = new Button(getContext());
                deleteButton.setText("Eliminar");
                deleteButton.setTextSize(16);
                deleteButton.setOnClickListener(btn -> {
                    new AlertDialog.Builder(getContext())
                        .setTitle("Eliminar reserva")
                        .setMessage("¿Estás seguro que deseas eliminar esta reserva?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            myReservationsContainer.removeView(reservaLayout);
                            if (myReservationsContainer.getChildCount() == 0) {
                                noReservationsText.setVisibility(View.VISIBLE);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                });

                reservaLayout.addView(reservaView);
                reservaLayout.addView(deleteButton);
                myReservationsContainer.addView(reservaLayout, 0); // Agrega arriba
                noReservationsText.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
