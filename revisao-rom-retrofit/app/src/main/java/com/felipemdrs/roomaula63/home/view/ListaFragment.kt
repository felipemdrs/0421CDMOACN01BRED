package com.felipemdrs.roomaula63.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipemdrs.roomaula63.R
import com.felipemdrs.roomaula63.home.model.CivilizationModel
import com.felipemdrs.roomaula63.home.viewmodel.CivilizationViewModel

class ListaFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _listaAdapter: ItemAdapter
    private var _listaDeCivilizacoes = mutableListOf<CivilizationModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        criarRecyclerView()

        val viewModel = ViewModelProvider(this,
            CivilizationViewModel.CivilizationViewModelFactory(_view.context))[CivilizationViewModel::class.java]

        viewModel.obterLista().observe(viewLifecycleOwner, {
            exibirResultados(it)
        })
    }

    private fun exibirResultados(lista: List<CivilizationModel>?) {
        lista?.let { _listaDeCivilizacoes.addAll(it) }
        _listaAdapter.notifyDataSetChanged()
    }

    private fun criarRecyclerView() {
        val lista = _view.findViewById<RecyclerView>(R.id.lista)
        val manager = LinearLayoutManager(_view.context)

        _listaDeCivilizacoes = mutableListOf()
        _listaAdapter = ItemAdapter(_listaDeCivilizacoes)

        lista.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _listaAdapter
        }
    }
}