package uk.co.massimocarli.simpleroom

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import uk.co.massimocarli.simpleroom.db.MyDatabase
import uk.co.massimocarli.simpleroom.db.Product
import uk.co.massimocarli.simpleroom.db.ProductResult
import uk.co.massimocarli.simpleroom.db.UkProduct

class MainActivity : AppCompatActivity() {

    lateinit var db: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "product-db"
        )
            //.allowMainThreadQueries()
            .build()
        // Im memory
//        db = Room.inMemoryDatabaseBuilder(
//            applicationContext,
//            MyDatabase::class.java
//        ).build()
        //ReadDBAsync(db, productOutput).execute(1)
        //RelationAsync(db).execute()
        ViewAsync(db).execute()
    }


    class ReadDBAsync(val db: MyDatabase, val output: TextView) : AsyncTask<Int, Nothing, Product?>() {
        override fun doInBackground(vararg params: Int?): Product? =
            db.getProductDao().findById(params[0] ?: 0)

        override fun onPostExecute(product: Product?) {
            super.onPostExecute(product)
            product?.run {
                output.text = "Product: ${this.name} - ${this.description}"
            }
        }

    }

    class ViewAsync(val db: MyDatabase) : AsyncTask<Unit, Nothing, List<UkProduct>?>() {
        override fun doInBackground(vararg params: Unit?): List<UkProduct>? = db.getProductDao().findAllWithView()

        override fun onPostExecute(productList: List<UkProduct>?) {
            super.onPostExecute(productList)
            productList?.forEach {
                Log.i("FROM VIEW", "$it")
            }
        }
    }

    class RelationAsync(val db: MyDatabase) : AsyncTask<Unit, Nothing, List<ProductResult>?>() {
        override fun doInBackground(vararg params: Unit?): List<ProductResult>? = db.getProductDao().findAll()

        override fun onPostExecute(productList: List<ProductResult>?) {
            super.onPostExecute(productList)
            productList?.forEach {
                Log.i("RELATION", "$it")
            }
        }
    }
}
