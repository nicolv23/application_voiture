import com.example.louemonchar.contactPropietaire.IContratVueContact
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

class PresentateurContact(
    private val view: IContratVueContact.Vue,
    private val sourceDonnees: SourceDeVoituresBidon
) : IContratVueContact.Presentateur {

    override fun recupererDetailsProprietaire(marqueVoiture: String, modeleVoiture: String) {
        val proprietaireAuto = sourceDonnees.obtenirProprietaire(marqueVoiture)

        if (proprietaireAuto != null) {
            val modelesVoiture = sourceDonnees.getModelesDeVoiture()[marqueVoiture]
            val modeleExiste = modelesVoiture?.contains(modeleVoiture) ?: false
            if (modeleExiste) {
                val proprietaireModele = ProprietaireModele(
                    proprietaireAuto.nom,
                    proprietaireAuto.email,
                    proprietaireAuto.telephone,
                    proprietaireAuto.horaireTravail,
                    marqueVoiture
                )
                view.afficherDetailsProprietaire(proprietaireModele)
            } else {
                view.afficherDetailsProprietaire(
                    ProprietaireModele(
                        "Tesla",
                        "elonmusk@gmail.com",
                        "+1987654321",
                        "6h - 16h, Lun-Ven",
                        "Elon Musk"
                    )
                )
            }
        }
    }
}
