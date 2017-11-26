package com.example.sigh.firstapp

class Queen(theBoard: kotlin.Array<Array<String>>, id11: Int, id12: Int, id21: Int, id22: Int) {
    var bishop = Bishop(theBoard, id11, id12, id21, id22)
    var rook = Rook(theBoard, id11, id12, id21, id22)

    fun forQueen(): Boolean{

        return bishop.forBishop() or rook.forRook()
    }
}
