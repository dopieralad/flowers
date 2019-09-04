package pl.dopieralad.university.ma.flowers.utils

enum class RequestCode {
    ADD_FLOWER;

    fun toInt() = ordinal
}
