import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.louemonchar.R
import com.example.louemonchar.contactPropietaire.IContratVueContact
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class VueContact : Fragment(), IContratVueContact.Vue {

    private lateinit var presentateurContact: IContratVueContact.Presentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val marqueVoiture = "Tesla"
        val modeleVoiture = "Tesla Modele X"

        // Instanciation du présentateur avec la vue et la source de données
        presentateurContact = PresentateurContact(this, SourceDeVoituresBidon())

        presentateurContact.recupererDetailsProprietaire(marqueVoiture, modeleVoiture)

        return view
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
}
