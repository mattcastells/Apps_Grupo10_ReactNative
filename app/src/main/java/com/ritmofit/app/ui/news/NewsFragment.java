
package com.ritmofit.app.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ritmofit.app.R;

public class NewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        LinearLayout newsContainer = view.findViewById(R.id.newsContainer);

    // Mock noticias con fecha, título y cuerpo (Reemplazar cuando tengamos el back)
    String[][] noticias = {
        {"2025-09-07", "¡Nueva clase de Zumba!", "Sumate este viernes a las 19hs en el salón principal."},
        {"2025-09-05", "Cierre por mantenimiento", "El gimnasio permanecerá cerrado el lunes 15/9 por tareas de mantenimiento."},
        {"2025-09-01", "Promo amigos", "Traé un amigo y ambos obtienen un 20% de descuento en la próxima cuota."}
    };
    for (int i = 0; i < noticias.length; i++) {
        String[] noticia = noticias[i];
        // Contenedor visual para cada noticia
        LinearLayout noticiaLayout = new LinearLayout(getContext());
        noticiaLayout.setOrientation(LinearLayout.VERTICAL);
        noticiaLayout.setPadding(0, 24, 0, 24);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 16, 0, 16);
        noticiaLayout.setLayoutParams(params);

        TextView dateView = new TextView(getContext());
        dateView.setText(noticia[0]);
        dateView.setTextSize(14);
    dateView.setTextColor(getResources().getColor(R.color.ritmofit_dark));
        dateView.setPadding(16, 16, 16, 4);
        noticiaLayout.addView(dateView);

        TextView title = new TextView(getContext());
    title.setText(noticia[1]);
    title.setTextSize(20);
    title.setTypeface(null, android.graphics.Typeface.BOLD);
    title.setPadding(16, 0, 16, 4);
    title.setTextColor(getResources().getColor(R.color.ritmofit_orange));
    noticiaLayout.addView(title);

        TextView body = new TextView(getContext());
        body.setText(noticia[2]);
        body.setTextSize(16);
        body.setPadding(16, 0, 16, 16);
        noticiaLayout.addView(body);

    noticiaLayout.setBackgroundColor(getResources().getColor(R.color.ritmofit_lightgray));
    newsContainer.addView(noticiaLayout);

    // Línea separadora entre noticias
    if (i < noticias.length - 1) {
        View divider = new View(getContext());
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 4);
        dividerParams.setMargins(0, 0, 0, 0);
        divider.setLayoutParams(dividerParams);
        divider.setBackgroundColor(getResources().getColor(R.color.ritmofit_orange));
        newsContainer.addView(divider);
    }
    }

        return view;
    }
}
