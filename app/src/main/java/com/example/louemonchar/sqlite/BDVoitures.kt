
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.louemonchar.http.Auto


class BDVoitures(private val context: Context) :
    SQLiteOpenHelper(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $NOM_TABLE (" +

                    "$COL_CODE TEXT PRIMARY KEY NOT NULL , " +
                    "$COL_PROPRIETAIRE_CODE TEXT NOT NULL, " +
                    "$COL_MARQUE TEXT NOT NULL, " +
                    "$COL_TRANSMISSION TEXT NOT NULL, " +
                    "$COL_MODELE TEXT NOT NULL, " +
                    "$COL_ANNEE INTEGER NOT NULL, " +
                    "$COL_IMAGE TEXT NOT NULL, " +
                    "$COL_ETAT TEXT NOT NULL, " +
                    "$COL_PRIX INTEGER NOT NULL, " +
                    "$COL_LOCATION TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $NOM_TABLE")
        onCreate(db)
    }
    fun insererVoiture (voiture: Auto) :Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_CODE,voiture.code)
        contentValues.put(COL_PROPRIETAIRE_CODE,voiture.code_propriétaire)
        contentValues.put(COL_MARQUE,voiture.marque)
        contentValues.put(COL_TRANSMISSION,voiture.transmission)
        contentValues.put(COL_MODELE,voiture.modèle)
        contentValues.put(COL_ANNEE,voiture.année)
        contentValues.put(COL_IMAGE,voiture.image)
        contentValues.put(COL_ETAT,voiture.etat)
        contentValues.put(COL_PRIX,voiture.prix)
        contentValues.put(COL_LOCATION,voiture.location)

        val succes = db.insert(NOM_TABLE, null,contentValues)
        db.close()
        return succes
    }

    fun getLesVoitureFavoris(): List<Auto> {
        val voitureFavoris = mutableListOf<Auto>()
        val db = readableDatabase
        val cursor = db.query(NOM_TABLE, null, null, null, null, null, null)

        cursor?.use {
            val nomColonne = cursor.columnNames
            while (it.moveToNext()) {
                val values = ContentValues()
                for (colonne_nom in nomColonne) {
                    val columnIndex = cursor.getColumnIndex(colonne_nom)
                    when (cursor.getType(columnIndex)) {
                        Cursor.FIELD_TYPE_NULL -> values.putNull(colonne_nom)
                        Cursor.FIELD_TYPE_INTEGER -> values.put(colonne_nom, cursor.getInt(columnIndex))
                        Cursor.FIELD_TYPE_FLOAT -> values.put(colonne_nom, cursor.getFloat(columnIndex))
                        Cursor.FIELD_TYPE_STRING -> values.put(colonne_nom, cursor.getString(columnIndex))
                        Cursor.FIELD_TYPE_BLOB -> values.put(colonne_nom, cursor.getBlob(columnIndex))
                    }
                }
                val code = values.getAsString(COL_CODE)
                val code_proprio = values.getAsString(COL_PROPRIETAIRE_CODE)
                val marque = values.getAsString(COL_MARQUE)
                val transmission = values.getAsString(COL_TRANSMISSION)
                val modele = values.getAsString(COL_MODELE)
                val prix = values.getAsInteger(COL_PRIX)
                val location = values.getAsString(COL_LOCATION)
                val etat = values.getAsString(COL_ETAT)
                val img = values.getAsString(COL_IMAGE)
                val annee = values.getAsInteger(COL_ANNEE)

                val favoris = Auto(code, code_proprio, marque, transmission, modele, annee, img, etat, prix, location)
                voitureFavoris.add(favoris)
            }
        }
        cursor?.close()
        db.close()
        return voitureFavoris
    }



    fun effacerModele(marque: String, modele: String) {
        writableDatabase.use { db ->
            val whereClause = "$COL_MARQUE = ? AND $COL_MODELE = ?"
            val whereArgs = arrayOf(marque, modele)
            db.delete(NOM_TABLE, whereClause, whereArgs)
        }
    }



    companion object {
        // Noms de tables et colonnes
    private const val NOM_BASE_DE_DONNEES = "voitureFavoris"
    private const val VERSION_BASE_DE_DONNEES = 1

        const val NOM_TABLE = "voiture_favorite"
        const val ID = "id"
        const val COL_CODE = "code"
        const val COL_PROPRIETAIRE_CODE = "code_proprietaire"
        const val COL_MARQUE = "marque"
        const val COL_TRANSMISSION = "transmission"
        const val COL_MODELE = "modele"
        const val COL_ANNEE = "annee"
        const val COL_IMAGE = "image"
        const val COL_ETAT = "etat"
        const val COL_PRIX = "prix"
        const val COL_LOCATION = "location"
    }
}


