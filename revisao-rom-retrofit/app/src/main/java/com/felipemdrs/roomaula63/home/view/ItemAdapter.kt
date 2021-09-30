package com.felipemdrs.roomaula63.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipemdrs.roomaula63.R
import com.felipemdrs.roomaula63.home.model.CivilizationModel

class ItemAdapter(private var civilizations: MutableList<CivilizationModel>):
    RecyclerView.Adapter<CivilizationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CivilizationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return CivilizationViewHolder(view)
    }

    override fun onBindViewHolder(holder: CivilizationViewHolder, position: Int) {
        holder.bind(civilizations[position])
    }

    override fun getItemCount() = civilizations.size
}