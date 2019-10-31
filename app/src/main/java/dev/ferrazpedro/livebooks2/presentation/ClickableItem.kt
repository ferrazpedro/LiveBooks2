package dev.ferrazpedro.livebooks2.presentation

interface ClickableItem {

    interface BookListener {
        fun clickableBook(pos: String?)
    }

    interface btnClickListener {
        fun onBtnClick(position: Int)
    }

}