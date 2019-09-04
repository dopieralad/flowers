package pl.dopieralad.university.ma.flowers.utils

import android.os.AsyncTask

class Asynchronous(private val action: () -> Unit): AsyncTask<Void, Void, Unit>() {

    init {
        execute()
    }

    override fun doInBackground(vararg params: Void?) = action.invoke()
}
