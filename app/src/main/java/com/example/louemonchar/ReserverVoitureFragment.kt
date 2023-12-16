package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.http.Auto

class ReserverVoitureFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VoitureReserveeAdapter

    private val voituresReservees = mutableListOf<VoitureReservee>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reserver_voiture, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = VoitureReserveeAdapter(voituresReservees) { position ->
            voituresReservees.removeAt(position)
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

            // Ajouter la voiture à la liste si elle n'est pas déjà présente
            if (!voituresReservees.contains(nouvelleVoiture)) {
                voituresReservees.add(nouvelleVoiture)
                adapter.notifyItemInserted(voituresReservees.size - 1)
            }
        }

        return view
    }
}
