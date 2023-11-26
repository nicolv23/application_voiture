import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.louemonchar.R
import com.example.louemonchar.contactPropietaire.IContratVueContact
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class VueContact : Fragment(), IContratVueContact.Vue {

    private lateinit var presentateurContact: IContratVueContact.Presentateur
    lateinit var boutonRetour: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        boutonRetour = view.findViewById(R.id.btnRetour)
        boutonRetour.setOnClickListener { presentateurContact.retour() }
        val marqueVoiture = "Tesla"
        val modeleVoiture = "Tesla Modele X"

        // Instanciation du présentateur avec la vue et la source de données
        presentateurContact = PresentateurContact(this, SourceDeVoituresBidon())

        presentateurContact.recupererDetailsProprietaire(marqueVoiture, modeleVoiture)

        val boutonAppeler = view.findViewById<Button>(R.id.appelerProprietaire)
        boutonAppeler.setOnClickListener {
            // Vérification de la permission CALL_PHONE
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Demander la permission si elle n'est pas accordée
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISSION_CALL_PHONE_CODE
                )
            } else {
                // Si la permission est déjà accordée, simuler l'appel
                effectuerAppel()
            }
        }

        return view
    }

    private fun effectuerAppel() {
        val telephoneTextView = view?.findViewById<TextView>(R.id.telephone)
        val telephone = telephoneTextView?.text.toString()
        val appelIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telephone"))
        startActivity(appelIntent)
    }

    override fun afficherDetailsProprietaire(proprietaire: ProprietaireModele?) {
        view?.apply {
            findViewById<TextView>(R.id.marqueVoiture).text = proprietaire?.marque ?: "N/A"
            findViewById<TextView>(R.id.email).text = proprietaire?.email ?: "N/A"
            findViewById<TextView>(R.id.telephone).text = proprietaire?.telephone ?: "N/A"
            findViewById<TextView>(R.id.horaireTravail).text = proprietaire?.horaireTravail ?: "N/A"
            findViewById<TextView>(R.id.nomProprietaire).text = proprietaire?.nom ?: "N/A"
        }
    }
    override fun retour(){
        Navigation.findNavController(requireView()).navigate(R.id.action_contactVue_to_marquesAuto)
    }

    companion object {
        private const val PERMISSION_CALL_PHONE_CODE = 100
    }
}
