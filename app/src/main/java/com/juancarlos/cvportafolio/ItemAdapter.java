package com.juancarlos.cvportafolio;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.juancarlos.cvportafolio.app.AppConfig;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        // Cargar imagen con Picasso (usando una imagen placeholder si no hay URL válida)
        if (item.getImgPath() != null && !item.getImgPath().isEmpty()) {
            String imagen = convertToFullUrl(item.getImgPath());
            holder.image.setContentDescription(item.getTitle());
            Picasso.get()
                    .load(imagen)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(holder.image, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("PICASSO", "Imagen cargada: " + imagen);
                        }
                        @Override
                        public void onError(Exception e) {
                            Log.e("PICASSO", "Error cargando imagen: " + imagen, e);
                        }
                    });
        }

        // Manejar clic en el demo link
        if (item.getDemoLink() != null && !item.getDemoLink().isEmpty()) {
            holder.itemView.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getDemoLink()));
                v.getContext().startActivity(browserIntent);
            });
        }
    }
    private String convertToFullUrl(String relativePath) {
        String cleanPath = relativePath.replaceAll("\\.\\./", "");
        return AppConfig.URL + cleanPath;
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
        }
    }
}
