import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import com.example.louemonchar.contactPropietaire.IContratVueContact
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class VueContact : Fragment(), IContratVueContact.Vue {

    private lateinit var presentateurContact: IContratVueContact.Presentateur
    private val sourceDeVoitures = SourceDeVoituresBidon()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentateurContact = PresentateurContact(this, sourceDeVoitures)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val marqueVoiture = arguments?.getString("marque")
        val modeleVoiture = arguments?.getString("modele")
        marqueVoiture?.let { marque ->
            modeleVoiture?.let { modele ->
                presentateurContact.recupererDetailsProprietaire(marque, modele)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

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

    override fun recevoirDetailsProprietaire(marque: String, modele: String) {
        presentateurContact.recupererDetailsProprietaire(marque, modele)
    }

    private fun effectuerAppel() {
        val telephoneTextView = view?.findViewById<TextView>(R.id.telephone)
        val telephone = telephoneTextView?.text.toString()
        val appelIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telephone"))
        startActivity(appelIntent)
    }

    override fun afficherDetailsProprietaire(proprietaire: ProprietaireModele?) {
        view?.apply {
            findViewById<TextView>(R.id.nomProprietaire).text = proprietaire?.nom ?: "N/A"
            findViewById<TextView>(R.id.marqueVoiture).text = proprietaire?.marque ?: "N/A"
            findViewById<TextView>(R.id.email).text = proprietaire?.email ?: "N/A"
            findViewById<TextView>(R.id.telephone).text = proprietaire?.telephone ?: "N/A"
            findViewById<TextView>(R.id.horaireTravail).text = proprietaire?.horaireTravail ?: "N/A"
        }

        // Appel de la fonction pour afficher l'image du propriétaire en fonction de la marque de la voiture
        if (proprietaire != null) {
            afficherImageProprietaire(proprietaire.marque)
        }
    }

    private fun afficherImageProprietaire(marque: String) {
        val imageView = view?.findViewById<ImageView>(R.id.proprietaireImage)
        when (marque) {
            "Tesla" -> imageView?.setImageResource(R.drawable.elon_musk)
            "Toyota" -> imageView?.setImageResource(R.drawable.robert_downey_jr)
            "Honda" -> imageView?.setImageResource(R.drawable.keanu_reeves)
            "Hyundai" -> imageView?.setImageResource(R.drawable.dwayne_johnson)
            else -> imageView?.setImageResource(R.drawable.elon_musk) // Image par défaut
        }
    }

    companion object {
        private const val PERMISSION_CALL_PHONE_CODE = 100
    }
}
