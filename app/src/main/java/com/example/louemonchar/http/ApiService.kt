import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle
import com.example.louemonchar.presentation.enregistrervoiture.EnregistrerVoitureModele
import com.example.louemonchar.presentation.voituresdisponibles.VoituresDisponiblesModèle
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "https://4aa68fb2-459c-4f9c-b1d9-9c512ca251a8.mock.pstmn.io/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiService {

    @GET("voiture")
    fun getAllAuto(): Call<List<Auto>>

  //  @POST("enregister_voiture")
  //  fun enregistrerVoirure(@Body nouvVoiture: EnregistrerVoitureModele.nouvelleVoiture): Call<EnregistrerVoitureModele.nouvelleVoiture>
}

object Api{
    val retrofitService: ApiService by lazy{ retrofit.create(ApiService::class.java)}

}