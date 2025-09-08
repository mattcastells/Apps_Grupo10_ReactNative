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
        LinearLayout classInfoCard = view.findViewById(R.id.classInfoCard);

        if (myReservationsContainer.getChildCount() == 0) {
            noReservationsText.setVisibility(View.VISIBLE);
        } else {
            noReservationsText.setVisibility(View.GONE);
        }

        // Mock de clases y horarios con info extendida
        class ClaseInfo {
            String nombre, profesor, cupos, duracion, sede, ubicacion;
            String[] horarios;
            ClaseInfo(String nombre, String profesor, String cupos, String duracion, String sede, String ubicacion, String[] horarios) {
                this.nombre = nombre; this.profesor = profesor; this.cupos = cupos; this.duracion = duracion; this.sede = sede; this.ubicacion = ubicacion; this.horarios = horarios;
            }
        }
        ClaseInfo[] clasesInfo = new ClaseInfo[] {
            new ClaseInfo("Yoga", "Ana López", "8/20", "60 min", "Central", "Salón 1", new String[]{"Lunes 10:00", "Miércoles 10:00"}),
            new ClaseInfo("Funcional", "Juan Pérez", "15/20", "45 min", "Norte", "Box 2", new String[]{"Martes 18:00", "Jueves 18:00"}),
            new ClaseInfo("Zumba", "Carla Ruiz", "20/20", "50 min", "Oeste", "Salón 2", new String[]{"Viernes 19:00"}),
            new ClaseInfo("Spinning", "Leo Díaz", "5/15", "40 min", "Central", "Salón 3", new String[]{"Sábado 11:00", "Domingo 11:00"})
        };
        String[] clases = new String[clasesInfo.length];
        for (int i = 0; i < clasesInfo.length; i++) clases[i] = clasesInfo[i].nombre;


        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, clases);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, clasesInfo[0].horarios);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        // Mostrar info de la clase seleccionada
        Runnable updateClassInfo = () -> {
            int pos = classSpinner.getSelectedItemPosition();
            ClaseInfo c = clasesInfo[pos];
            classInfoCard.removeAllViews();
            TextView prof = new TextView(getContext());
            prof.setText("Profesor: " + c.profesor);
            prof.setTextSize(16);
            classInfoCard.addView(prof);
            TextView cupos = new TextView(getContext());
            cupos.setText("Cupos: " + c.cupos);
            cupos.setTextSize(16);
            classInfoCard.addView(cupos);
            TextView dur = new TextView(getContext());
            dur.setText("Duración: " + c.duracion);
            dur.setTextSize(16);
            classInfoCard.addView(dur);
            TextView sede = new TextView(getContext());
            sede.setText("Sede: " + c.sede + " - " + c.ubicacion);
            sede.setTextSize(16);
            classInfoCard.addView(sede);
        };
        updateClassInfo.run();

        classSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> newTimeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, clasesInfo[position].horarios);
                newTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                timeSpinner.setAdapter(newTimeAdapter);
                updateClassInfo.run();
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
                // Crear layout para la reserva con fondo blanco y borde naranja
                LinearLayout card = new LinearLayout(getContext());
                card.setOrientation(LinearLayout.HORIZONTAL);
                card.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                card.setBackgroundResource(R.drawable.reservation_card);
                card.setPadding(24, 18, 24, 18);
                card.setElevation(4f);

                TextView reservaView = new TextView(getContext());
                reservaView.setText("• " + clase + " - " + horario);
                reservaView.setTextSize(18);
                reservaView.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));

                Button deleteButton = new Button(getContext());
                deleteButton.setText("Eliminar");
                deleteButton.setTextSize(16);
                deleteButton.setOnClickListener(btn -> {
                    new AlertDialog.Builder(getContext())
                        .setTitle("Eliminar reserva")
                        .setMessage("¿Estás seguro que deseas eliminar esta reserva?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            myReservationsContainer.removeView(card);
                            if (myReservationsContainer.getChildCount() == 0) {
                                noReservationsText.setVisibility(View.VISIBLE);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                });

                card.addView(reservaView);
                card.addView(deleteButton);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) card.getLayoutParams();
                params.setMargins(0, 0, 0, 24);
                card.setLayoutParams(params);
                myReservationsContainer.addView(card, 0);
                noReservationsText.setVisibility(View.GONE);
            }
        });

        return view;
    }
}