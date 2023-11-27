package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanushop.R;
import com.example.nanushop.verActivity;

import java.util.ArrayList;

import Entidad.Productos;

public class ListaProdutosAdacter extends RecyclerView.Adapter <ListaProdutosAdacter.ProductosViewHolder> {
    ArrayList<Productos> listaProductos;
    ArrayList<Productos> listaOriginal;



    public ListaProdutosAdacter(ArrayList<Productos> listaproductos) {
        this.listaProductos = listaproductos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaproductos);
        Log.d("DEBUG", "ListaProdutosAdacter - Cantidad de elementos: " + listaproductos.size());
    }
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("DEBUG", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_productos,null,false);
return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        Log.d("DEBUG", "onBindViewHolder called for position: " + position);

        // Obtén los datos del objeto Productos en la posición actual
        Productos productoActual = listaProductos.get(position);

        // Establece los textos en los TextViews
        holder.texProducto.setText(productoActual.getNombre_producto());
        holder.textDescProducto.setText(productoActual.getDes_producto());
        holder.textValor.setText(String.valueOf(productoActual.getValor_producto()));
        holder.texDiponible.setText(productoActual.getDisponible());
        // Obtén el nombre de la imagen del objeto Productos
        String nombreImagen = productoActual.getImg();

        // Obtén el ID del recurso de la imagen usando el nombre
        int resId = holder.itemView.getContext().getResources().getIdentifier(
                nombreImagen, "drawable", holder.itemView.getContext().getPackageName());

        // Establece la imagen dinámicamente
        holder.imageView.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
       return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView texProducto, textDescProducto, textValor , texDiponible;
        ImageView imageView;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            texProducto = itemView.findViewById(R.id.texProducto);
            texDiponible = itemView.findViewById(R.id.texDiponible);
            textDescProducto = itemView.findViewById(R.id.textDescProducto);
            textValor = itemView.findViewById(R.id.textValor);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, verActivity.class);
                    intent.putExtra("ID",listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
