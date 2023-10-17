package com.example.crypto.utils

import android.util.Log
import com.example.crypto.CryptoApp
import com.example.crypto.data.remote.CoincapRetrofitClient
import com.example.crypto.data.remote.DataJS
import com.example.crypto.data.remote.EscuelaJSClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateData {
    companion object {

        // ESTE METODO SIRVE PARA OBTENER LOS DATOS ACTUALIZADOS CUANDO SE INSTALA LA APP

        suspend fun getUpdatedData() = coroutineScope {
            launch(Dispatchers.IO) {
                val service = CoincapRetrofitClient.retrofitInstance()
                val response = service.getData()

                val data = response.body()

                val app = CryptoApp()

                app.repository.deleteAssets()

                if (response.isSuccessful) {
                    if (data != null) {

                        for (asset in data.data) {
                            //Log.i("Asset ", asset.id)
                            app.repository.insertAsset(asset)
                        }
                    }
                } else {
                    Log.e("UpdateData Error", "Ocurrió un error al ejecutar getUpdatedData")
                }

                /**
                 * SEGUNDO SERVICIO
                 */

               val serviceEscuelaJS = EscuelaJSClient.retrofitInstance()
                //val responseEscuelaJS = serviceEscuelaJS.getProductData()
                //val responseEscuelaJS = serviceEscuelaJS.getProducts()

                val productCall: Call<List<DataJS>> = serviceEscuelaJS.getProducts()
                Log.i("DATOS", "HELLO")

                productCall.enqueue(object : Callback<List<DataJS?>?> {
                    override fun onResponse(
                        call: Call<List<DataJS?>?>?,
                        response: Response<List<DataJS?>?>
                    ) {
                        if (response.isSuccessful) {
                            val data: List<DataJS> = response.body() as List<DataJS>
                            // Procesa los datos aquí
                            Log.i("DATOS", data.toString())
                        } else {
                            // Maneja el error aquí
                            Log.e("DATOS1", data.toString())
                        }
                    }

                    override fun onFailure(call: Call<List<DataJS?>?>?, t: Throwable?) {
                        // Maneja el error de la solicitud aquí
                        Log.e("DATOS2", data.toString())
                    }
                })





            }
        }
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<DataJS?>?>) {

}
