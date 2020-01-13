package ir.apptaste.android.utility.mapper

interface Mapper<T, R> {
    fun map(item: T): R
}