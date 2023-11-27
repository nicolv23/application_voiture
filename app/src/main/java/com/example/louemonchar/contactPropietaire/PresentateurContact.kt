import com.example.louemonchar.R
import com.example.louemonchar.contactPropietaire.IContratVueContact
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class PresentateurContact(
    private val view: IContratVueContact.Vue,
    private val sourceDonnees: SourceDeVoituresBidon
) : IContratVueContact.Presentateur {

    override fun recupererDetailsProprietaire(marqueVoiture: String, modeleVoiture: String) {
        val proprietaireAuto = sourceDonnees.obtenirProprietaire(modeleVoiture)

        if (proprietaireAuto != null) {
            view.afficherDetailsProprietaire(proprietaireAuto)
        } else {
            val proprietaireParDefaut = ProprietaireModele(
                "Tesla",
                "Elon Musk",
                "elonmusk@gmail.com",
                "+1987654321",
                "6h - 16h, Lun-Ven",
                R.drawable.elon_musk
            )
            afficherDetailsProprietaire(proprietaireParDefaut)
        }
    }

    private fun afficherDetailsProprietaire(proprietaire: ProprietaireModele?) {
        view.afficherDetailsProprietaire(proprietaire)
    }

    override fun retour() {
        view.retour()
    }
}
