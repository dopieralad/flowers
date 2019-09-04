package pl.dopieralad.university.ma.flowers.utils

import androidx.lifecycle.*

class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {

    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(value: T) {
        handler(value)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}

fun <T> LiveData<T>.observeOnce(handler: (T) -> Unit) {
    val observer = OneTimeObserver(handler)

    observe(observer, observer)
}
