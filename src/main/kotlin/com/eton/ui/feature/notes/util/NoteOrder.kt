package com.eton.util

/**
 * Within Title, Date, Color: ASCENDING or DESCENDING type
 */
sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}

/**
 * Sort notes based on TITLE, DATE, COLOR
 */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    /**
     * returns the copy of NoteOrder BUT with passed OrderType
     */
    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Title -> Title(orderType = orderType)
            is Date -> Date(orderType = orderType)
            is Color -> Color(orderType = orderType)
            else -> Date(orderType = orderType)
        }
    }
}










