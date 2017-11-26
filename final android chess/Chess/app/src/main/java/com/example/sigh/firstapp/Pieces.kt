package com.example.sigh.firstapp

import android.widget.ImageButton

class lRook() {
    fun checklRook(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "lRook") {
            if (rule.forRook()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.rlt6)
                return true
            } else return false

        } else return false
    }
}

class lKnight() {

    fun checklKnight(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {

        if (board[id1] == "lKnight") {
            if (rule.forKnight()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.nlt6)
                return true
            } else return false
        } else return false
    }


}

class lBishop() {

    fun checklBishop(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {

        if (board[id1] == "lBishop") {
            if (rule.forBishop()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.blt6)
                return true
            } else return false

        } else return false
    }
}

class lQueen() {
    fun checklQueen(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {

        if (board[id1] == "lQueen") {
            if (rule.forQueen()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.qlt6)
                return true
            } else return false
        } else return false

    }
}

class lKing() {
    fun checklKing(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int, id2: Int, rlRookNeverMoved: Boolean, llRookNeverMoved: Boolean, lKingNeverMoved: Boolean): Boolean {
        if (board[id1] == "lKing") {
            if (rule.forKing()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.klt6)
                return true
            } else if (lKingNeverMoved) {
                if (id2 == 7 && rlRookNeverMoved && board[6] == " " && board[7] == " ") {
                    btn1.setImageDrawable(null)
                    btn2.setImageResource(R.drawable.klt6)
                    return true
                } else if (id2 == 3 && llRookNeverMoved && board[2] == " " && board[3] == " " && board[4] == " ") {
                    btn1.setImageDrawable(null)
                    btn2.setImageResource(R.drawable.klt6)
                    return true
                } else return false
            } else return false
        } else return false
    }
}

class lPawn() {
    fun checklPawn(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "lPawn") {
            var result = rule.forLPawn()
            if (result == 1) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.plt6)
                return true
            } else if (result == 2) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.qlt6)
                board[id1] = "lQueen"
                return true
            } else return false
        } else return false
    }

}

class dRook() {
    fun checkdRook(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "dRook") {
            if (rule.forRook()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.rdt6)
                return true
            } else return false

        } else return false
    }

}

class dKnight() {
    fun checkdKnight(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "dKnight") {
            if (rule.forKnight()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.ndt6)
                return true
            } else return false

        } else return false
    }

}

class dBishop() {
    fun checkdBishop(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "dBishop") {
            if (rule.forBishop()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.bdt6)
                return true
            } else return false
        } else return false
    }

}

class dQueen() {
    fun checkdQueen(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "dQueen") {
            if (rule.forQueen()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.qdt6)
                return true
            } else return false
        } else return false
    }

}

class dKing() {
    fun checkdKing(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int, id2: Int, rdRookNeverMoved: Boolean, ldRookNeverMoved: Boolean, dKingNeverMoved: Boolean): Boolean {
        if (board[id1] == "dKing") {
            if (rule.forKing()) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.kdt6)
                return true
            } else if (dKingNeverMoved) {
                if (id2 == 58 && rdRookNeverMoved && board[59] == " " && board[58] == " ") {
                    btn1.setImageDrawable(null)
                    btn2.setImageResource(R.drawable.kdt6)
                    return true
                } else if (id2 == 62 && ldRookNeverMoved && board[61] == " " && board[62] == " " && board[63] == " ") {
                    btn1.setImageDrawable(null)
                    btn2.setImageResource(R.drawable.kdt6)
                    return true
                } else return false
            } else return false
        } else return false
    }
}

class dPawn() {
    fun checkdPawn(board: Array<String>, rule: Rules, btn1: ImageButton, btn2: ImageButton, id1: Int): Boolean {
        if (board[id1] == "dPawn") {
            var result = rule.forDPawn()
            if (result == 1) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.pdt6)
                return true
            } else if (result == 2) {
                btn1.setImageDrawable(null)
                btn2.setImageResource(R.drawable.qdt6)
                board[id1] = "dQueen"
                return true
            } else return false
        } else return false
    }
}

class Pieces(var id1: Int, var id2: Int, var btn1: ImageButton, var btn2: ImageButton, var player: Int, var board: Array<String>, var ldRookNeverMoved: Boolean, var rdRookNeverMoved: Boolean, var llRookNeverMoved: Boolean, var rlRookNeverMoved: Boolean, var dKingNeverMoved: Boolean, var lKingNeverMoved: Boolean, var previousPlayer: Int) {
    var checkValidity = false
    var id11 = -1
    var id12 = -1
    var id21 = -1
    var id22 = -1

    fun isValidMovement(): Boolean {
        return checkValidity
    }

    private fun setIDs() {
        if (id1 % 8 != 0) {
            id11 = 7 - id1 / 8
            if ((id1 / 8) % 2 == 0) {
                id12 = id1 % 8 - 1
            } else if ((id1 / 8) % 2 == 1) {
                id12 = 8 - id1 % 8
            }
        } else {
            id11 = 7 - (id1 / 8 - 1)
            if ((id1 / 8) % 2 == 0) {
                id12 = 0
            } else if ((id1 / 8) % 2 == 1) {
                id12 = 7
            }
        }

        if (id2 % 8 != 0) {
            id21 = 7 - id2 / 8
            if ((id2 / 8) % 2 == 0) {
                id22 = id2 % 8 - 1
            } else if ((id2 / 8) % 2 == 1) {
                id22 = 8 - id2 % 8
            }
        } else {
            id21 = 7 - (id2 / 8 - 1)
            if ((id2 / 8) % 2 == 0) {
                id22 = 0
            } else if ((id2 / 8) % 2 == 1) {
                id22 = 7
            }
        }
    }

    fun updateBoard(): Array<Array<String>> {
        var changeRows: Boolean = true
        var counter: Int = 64
        var theBoard = Array(8, { arrayOf(" ", " ", " ", " ", " ", " ", " ", " ") })

        for (i in 0..theBoard.size - 1) {
            var rowArray = arrayOf(" ", " ", " ", " ", " ", " ", " ", " ")
            if (changeRows) {
                for (j in 0..7) {
                    rowArray[j] = board[counter]
                    counter--
                    changeRows = false
                }
            } else {
                for (j in 0..7) {
                    rowArray[7 - j] = board[counter]
                    counter--
                    changeRows = true
                }
            }

            theBoard[i] = rowArray
        }

        return theBoard
    }

    fun move() {
        setIDs()
        var rule = Rules(updateBoard(), id11, id12, id21, id22)
        if (board[id1][0] != board[id2][0] && previousPlayer != player) {
            if (player == 0) {
                val rook = lRook()
                val knight = lKnight()
                val bishop = lBishop()
                val queen = lQueen()
                val king = lKing()
                val pawn = lPawn()
                checkValidity = rook.checklRook(board, rule, btn1, btn2, id1) || knight.checklKnight(board, rule, btn1, btn2, id1) || bishop.checklBishop(board, rule, btn1, btn2, id1) || queen.checklQueen(board, rule, btn1, btn2, id1) || king.checklKing(board, rule, btn1, btn2, id1, id2, rlRookNeverMoved, llRookNeverMoved, lKingNeverMoved) || pawn.checklPawn(board, rule, btn1, btn2, id1)

            } else if (player == 1) {
                val rook = dRook()
                val knight = dKnight()
                val bishop = dBishop()
                val queen = dQueen()
                val king = dKing()
                val pawn = dPawn()
                checkValidity = rook.checkdRook(board, rule, btn1, btn2, id1) || knight.checkdKnight(board, rule, btn1, btn2, id1) || bishop.checkdBishop(board, rule, btn1, btn2, id1) || queen.checkdQueen(board, rule, btn1, btn2, id1) || king.checkdKing(board, rule, btn1, btn2, id1, id2, rdRookNeverMoved, ldRookNeverMoved, dKingNeverMoved) || pawn.checkdPawn(board, rule, btn1, btn2, id1)
            }
        }
    }


}
