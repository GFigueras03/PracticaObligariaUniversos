package org.gfigueras.practicaobligariauniversos.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.model.entities.Universo


class UniversoAdapter(private val context: Context, private val universos: List<Universo>) :
    RecyclerView.Adapter<UniversoAdapter.UniversoViewHolder>() {

    class UniversoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
    }

    private var lastAnimatedPosition: Int = 0
        get() = field
        set(value) {
            // No es necesario implementar el setter
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_universo, parent, false)
        return UniversoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversoViewHolder, position: Int) {
        val universo = universos[position]

        // Carga la imagen con Glide según el nombre almacenado en la base de datos
        Glide.with(context).load(universo.getImagen()).into(holder.imageView)

        holder.nombreTextView.text = universo.getNombre()

        holder.itemView.setOnClickListener {
            mostrarDialogo(universo.getDescripcion())
        }
    }

    override fun getItemCount(): Int {
        return universos.size
    }

    private fun mostrarDialogo(descripcion: String) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Descripción")
            .setMessage(descripcion)
            .setPositiveButton("Cerrar", null)
            .create()
        dialog.show()
        val drawable = ContextCompat.getDrawable(context, R.drawable.background_dialog)
        dialog.window?.setBackgroundDrawable(drawable)
    }
}
