package com.felipemdrs.roomaula63.home.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipemdrs.roomaula63.R
import com.felipemdrs.roomaula63.home.model.CivilizationModel

class CivilizationViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val txtName = view.findViewById<TextView>(R.id.txtCivName)

    fun bind(model: CivilizationModel) {
        txtName.text = model.name
    }
}
