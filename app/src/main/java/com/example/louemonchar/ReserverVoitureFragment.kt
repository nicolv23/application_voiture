
package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReserverVoitureFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VoitureReserveeAdapter
    private lateinit var viewModel: ReserverVoitureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reserver_voiture, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(ReserverVoitureViewModel::class.java)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = VoitureReserveeAdapter(viewModel.voituresReservees) { position ->
            viewModel.voituresReservees.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.adapter = adapter

        arguments?.let { bundle ->
            val nouvelleVoiture = VoitureReservee(
                bundle.getString("modèle_voitures_reservees", ""),
                bundle.getString("année_voitures_reservees", ""),
                bundle.getString("passagers_voitures_reservees", ""),
                bundle.getString("propriétaire_voitures_reservees", ""),
                bundle.getString("date_de_location_reservees", ""),
                bundle.getString("img_voitures_reservees", "")
            )

            if (!viewModel.voituresReservees.contains(nouvelleVoiture)) {
                viewModel.voituresReservees.add(nouvelleVoiture)
                adapter.notifyItemInserted(viewModel.voituresReservees.size - 1)
            }
        }

        return view
    }
}

