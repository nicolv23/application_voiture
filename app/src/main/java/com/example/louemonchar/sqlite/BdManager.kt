
package com.example.louemonchar.sqlite

import BDVoitures
import android.content.Context
import com.example.louemonchar.http.Auto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BdManager {
    private var database: BDVoitures? = null
    internal lateinit var liste: List<Auto>
    fun initialize(context: Context) {
        if (database == null) {
            val sourceDeVoitures = getAllVoiture()
            database = BDVoitures(context.applicationContext)
        }
    }

    private fun getAllVoiture() {
        Api.retrofitService.getAllAuto().enqueue(object: Callback<List<Auto>> {
            override fun onResponse(
                call: Call<List<Auto>>,
                response: Response<List<Auto>>
            ){
                    liste = response.body()!!
            }

            override fun onFailure(call: Call<List<Auto>>, t: Throwable) {

            }
        })
    }

    fun getBD(): BDVoitures {
        if (database == null) {
            throw IllegalStateException("BdManager doit être initialisé pour fonctionner")
        }
        return database!!
    }
}


