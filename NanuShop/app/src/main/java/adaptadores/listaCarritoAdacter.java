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
import com.example.nanushop.SacarActivity;
import com.example.nanushop.verActivity;

import java.util.ArrayList;

import Entidad.CARRITO;
import Entidad.Productos;


public class listaCarritoAdacter extends RecyclerView.Adapter <listaCarritoAdacter.CarritoViewHolder>  {
    ArrayList<CARRITO> listaCARRITO;
    ArrayList<CARRITO> listaOriginal;
    public listaCarritoAdacter(ArrayList<CARRITO> listaCARRITO) {
        this.listaCARRITO = listaCARRITO;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaCARRITO);
        Log.d("DEBUG", "ListaProdutosAdacter - Cantidad de elementos: " + listaCARRITO.size());
    }
    @NonNull
    @Override
    public listaCarritoAdacter.CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("DEBUG", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_carrito,null,false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaCarritoAdacter.CarritoViewHolder holder, int position) {
        Log.d("DEBUG", "onBindViewHolder called for position: " + position);

        // Obtén los datos del objeto Productos en la posición actual
        CARRITO productoActual = listaCARRITO.get(position);

        // Establece los textos en los TextViews
        holder.texProducto.setText(productoActual.getNombre_producto());
        holder.textDescProducto.setText(productoActual.getDes_producto());
        holder.textValor.setText(String.valueOf(productoActual.getValor_producto()));

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
        return listaCARRITO.size();
    }

    public class CarritoViewHolder extends RecyclerView.ViewHolder {
        TextView texProducto, textDescProducto, textValor ;
        ImageView imageView;
        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);

            texProducto = itemView.findViewById(R.id.texProducto);
            textDescProducto = itemView.findViewById(R.id.textDescProducto);
            textValor = itemView.findViewById(R.id.textValor);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SacarActivity.class);
                    intent.putExtra("ID",listaCARRITO.get(getAdapterPosition()).getID_producto());
                    context.startActivity(intent);
                }
            });

        }
    }
}
