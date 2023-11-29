import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.louemonchar.sourceDonnees.ProprietaireModele
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon

interface SourceVoitures {
    fun getModelesDeVoiture(): Map<String, List<String>>
    fun getModelesEnregistres(): Map<String, List<String>>
    fun obtenirProprietaire(modele: String): ProprietaireModele?
}

class BDVoitures(private val context: Context, private val sourceVoitures: SourceDeVoituresBidon) :
    SQLiteOpenHelper(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_MODELES_VOITURE (" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_MARQUE TEXT, " +
                    "$COL_MODELE TEXT)"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_PROPRIETAIRES (" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_NOM TEXT, " +
                    "$COL_TELEPHONE TEXT, " +
                    "$COL_EMAIL TEXT, " +
                    "$COL_HORAIRE_TRAVAIL TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MODELES_VOITURE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROPRIETAIRES")
        onCreate(db)
    }

    fun initialiserBD() {
        val sourceBidon = SourceDeVoituresBidon(context)

        // Insérer les modèles de voiture depuis la source de données bidon dans la base de données
        sourceBidon.getModelesDeVoiture().forEach { (marque, modelesList) ->
            modelesList.forEach { modele ->
                insererModelesDeVoiture(marque, modele)
            }
        }

        // Insérer les propriétaires des modèles enregistrés dans la base de données
        sourceBidon.getModelesEnregistres().forEach { (marque, modelesList) ->
            modelesList.forEach { modele ->
                val proprietaire = sourceBidon.obtenirProprietaire(modele)
                proprietaire?.let {
                    insererProprietaires(it)
                }
            }
        }
    }

    fun insererModelesDeVoiture(marque: String, modele: String) {
        writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(COL_MARQUE, marque)
                put(COL_MODELE, modele)
            }
            db.insert(TABLE_MODELES_VOITURE, null, values)
        }
    }

    fun insererProprietaires(proprietaire: ProprietaireModele) {
        writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(COL_NOM, proprietaire.nom)
                put(COL_TELEPHONE, proprietaire.telephone)
                put(COL_EMAIL, proprietaire.email)
                put(COL_HORAIRE_TRAVAIL, proprietaire.horaireTravail)
            }
            db.insert(TABLE_PROPRIETAIRES, null, values)
        }
    }

    fun effacerModele(marque: String, modele: String) {
        writableDatabase.use { db ->
            val whereClause = "$COL_MARQUE = ? AND $COL_MODELE = ?"
            val whereArgs = arrayOf(marque, modele)
            db.delete(TABLE_MODELES_VOITURE, whereClause, whereArgs)
        }
    }

    companion object {
        private const val NOM_BASE_DE_DONNEES = "LoueMonCharBD"
        private const val VERSION_BASE_DE_DONNEES = 1

        // Noms de tables et colonnes
        const val TABLE_MODELES_VOITURE = "modeles_voiture"
        const val TABLE_PROPRIETAIRES = "proprietaires"
        const val COL_ID = "id"
        const val COL_MARQUE = "marque"
        const val COL_MODELE = "modele"
        const val COL_NOM = "nom"
        const val COL_TELEPHONE = "telephone"
        const val COL_EMAIL = "email"
        const val COL_HORAIRE_TRAVAIL = "horaire_travail"
    }
}
