import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class VoitureUiEntiteTests {

    data class VoitureUiModele(
        val modèle: String = "Toyota Corolla",
        val année: Int = 2022,
        val passagers: String = "6 passagers",
        val propriétaire: String = "John Doe",
        val location: Date = SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15"),
        val imageRes: Int = 0
    )

    @Test
    fun vérifierModèleParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals("Toyota Corolla", voiture.modèle)
    }

    @Test
    fun vérifierAnnéeParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals(2022, voiture.année)
    }

    @Test
    fun vérifierPassagersParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals("6 passagers", voiture.passagers)
    }

    @Test
    fun vérifierPropriétaireParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals("John Doe", voiture.propriétaire)
    }

    @Test
    fun vérifierLocationParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals(SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15"), voiture.location)
    }

    @Test
    fun vérifierImageResParDéfaut() {
        val voiture = VoitureUiModele()
        assertEquals(0, voiture.imageRes)
    }
}
