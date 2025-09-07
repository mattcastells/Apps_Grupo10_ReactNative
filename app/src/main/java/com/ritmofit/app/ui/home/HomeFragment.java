
package com.ritmofit.app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.ritmofit.app.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button goToReservationsButton = view.findViewById(R.id.goToReservationsButton);
        Button goToProfileButton = view.findViewById(R.id.goToProfileButton);

        goToReservationsButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.reservationsFragment);
        });
        goToProfileButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.profileFragment);
        });

        // Puedes agregar lógica para mostrar info dinámica en el futuro

        return view;
    }
}
