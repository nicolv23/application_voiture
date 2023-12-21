package com.example.louemonchar

import BDVoitures
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match

class FavorisFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var voitureFavorisAdapter: VoitureFavorisAdapter
    private lateinit var bd: BDVoitures


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favoris, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        bd = BDVoitures(requireContext())
        val favoriteCars = bd.getLesVoitureFavoris()

        voitureFavorisAdapter = VoitureFavorisAdapter(favoriteCars)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = voitureFavorisAdapter

        return view
    }

    override fun onResume() {
        super.onResume()
        voitureFavorisAdapter.miseAjourData(bd.getLesVoitureFavoris())
    }
}