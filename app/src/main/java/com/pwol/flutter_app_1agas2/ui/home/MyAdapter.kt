package com.pwol.flutter_app_1agas2.ui.home

import android.graphics.drawable.Drawable
import com.pwol.flutter_app_1agas2.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pwol.flutter_app_1agas2.database.services.Service
import com.squareup.picasso.Picasso


class MyAdapter(myDataLists: List<Service>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var myDataLists: List<Service>
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.service_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val md: Service = myDataLists[i]

        Picasso.get().load(R.drawable.file_clip).into(viewHolder.priority_image);

        viewHolder.service_id.setText(md.id.toString());
        viewHolder.contract_text.setText("Contrato: ${md.contract}")

        Picasso.get().load(R.drawable.comerciovector).into(viewHolder.type_image);

        viewHolder.ClienttextView.setText("Cliente: ${md.name} ${md.lastname}")
        viewHolder.DepartMunitextView.setText("${md.departament} ${md.municipality}")
        viewHolder.DirectionTextView.setText("${md.direction}")
        viewHolder.BookingTextView.setText("Agendado: ${md.booking}")
        if(md.deadline != null && md.deadline.length> 0){
            viewHolder.StateTextView.setText("Finalizado:  ${md.deadline}")
        }else{
            viewHolder.StateTextView.setText("Estado: Pendiente")
        }
        if(md.closed == true && md.uploaded == false){
            viewHolder.CurseTextView.setText("No Sincronizado")
        }else if (md.closed == false && md.uploaded == false){
            viewHolder.CurseTextView.setText("Operaciones en curso")
        }else{
            viewHolder.CurseTextView.setText("Sincronizado")
        }



    }

    override fun getItemCount(): Int {
        return myDataLists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priority_image: ImageView
        val service_id: TextView
        val contract_text: TextView
        val type_image: ImageView

        val ClienttextView: TextView
        val DepartMunitextView: TextView
        val DirectionTextView: TextView
        val BookingTextView: TextView
        val StateTextView: TextView
        val CurseTextView: TextView

        init {
            priority_image= itemView.findViewById(R.id.priority_image)
            service_id= itemView.findViewById(R.id.service_id);
            contract_text = itemView.findViewById(R.id.contract_text)
            type_image= itemView.findViewById(R.id.type_image)

            ClienttextView = itemView.findViewById(R.id.ClienttextView)
            DepartMunitextView = itemView.findViewById(R.id.DepartMunitextView)
            DirectionTextView = itemView.findViewById(R.id.DirectionTextView)
            BookingTextView = itemView.findViewById(R.id.BookingTextView)
            StateTextView = itemView.findViewById(R.id.StateTextView)
            CurseTextView = itemView.findViewById(R.id.CurseTextView)


        }
    }

    init {
        this.myDataLists = myDataLists
    }
}
