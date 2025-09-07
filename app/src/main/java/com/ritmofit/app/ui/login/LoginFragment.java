package com.ritmofit.app.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.ritmofit.app.R;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText emailEdit = view.findViewById(R.id.loginEmail);
        EditText passEdit = view.findViewById(R.id.loginPassword);
        Button loginBtn = view.findViewById(R.id.loginButton);
        TextView errorText = view.findViewById(R.id.loginError);
        TextView forgotPass = view.findViewById(R.id.forgotPassword);

        loginBtn.setOnClickListener(v -> {
            String email = emailEdit.getText().toString().trim();
            String pass = passEdit.getText().toString();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                errorText.setText("Completa todos los campos");
                errorText.setVisibility(View.VISIBLE);
            } else if (!email.contains("@") && email.length() < 4) {
                errorText.setText("Usuario o email inválido");
                errorText.setVisibility(View.VISIBLE);
            } else if (pass.length() < 4) {
                errorText.setText("Contraseña muy corta");
                errorText.setVisibility(View.VISIBLE);
            } else {
                errorText.setVisibility(View.GONE);
                // Mock login: navega a Home (reemplazar cuando tengamos el back)
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });

        forgotPass.setOnClickListener(v -> {
            errorText.setText("Funcionalidad no implementada");
            errorText.setVisibility(View.VISIBLE);
        });

        return view;
    }
}
