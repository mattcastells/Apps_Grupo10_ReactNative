
package com.ritmofit.app.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ritmofit.app.R;

public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE = 1;
    private ImageView profileImage;
    private EditText nameEditText, emailEditText;
    private Button saveButton, logoutButton, changePhotoButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = view.findViewById(R.id.profileImage);
        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        saveButton = view.findViewById(R.id.saveButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        changePhotoButton = view.findViewById(R.id.changePhotoButton);

        // Demo: set default values
        nameEditText.setText("Nombre Apellido");
        emailEditText.setText("usuario@email.com");

        changePhotoButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        saveButton.setOnClickListener(v -> {
            // Guardar cambios (implementar lógica real)
            Toast.makeText(getContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
        });

        logoutButton.setOnClickListener(v -> {
            // Navegar a LoginFragment
            androidx.navigation.Navigation.findNavController(view).navigate(R.id.loginFragment);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }
}
