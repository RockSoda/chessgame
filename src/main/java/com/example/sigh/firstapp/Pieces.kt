package com.example.sigh.firstapp

import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton

class Pieces(var id1: Int, var id2: Int, var btn1: ImageButton, var btn2: ImageButton, var player: Int, var board: kotlin.Array<String>, var activity: AppCompatActivity){
    var checkValidity = false
    var id11 = -1
    var id12 = -1
    var id21 = -1
    var id22 = -1

    fun isValidMovement():Boolean{
        return checkValidity
    }

    fun setIDs(){
        if(id1%8 != 0){
            id11 = 7-id1/8
            if((id1/8)%2 == 0){
                id12 = id1%8-1
            }else if((id1/8)%2 == 1){
                id12 = 8-id1%8
            }
        }else{
            id11 = 7-(id1/8-1)
            if((id1/8)%2 == 0){
                id12 = 0
            }else if((id1/8)%2 == 1){
                id12 = 7
            }
        }

        if(id2%8 != 0){
            id21 = 7-id2/8
            if((id2/8)%2 == 0){
                id22 = id2%8-1
            }else if((id2/8)%2 == 1){
                id22 = 8-id2%8
            }
        }else{
            id21 = 7-(id2/8-1)
            if((id2/8)%2 == 0){
                id22 = 0
            }else if((id2/8)%2 == 1){
                id22 = 7
            }
        }
    }

    fun updateBoard(): Array<Array<String>>{
        var changeRows: Boolean = true
        var counter: Int = 64
        var theBoard = Array(8, {arrayOf(" ", " ", " ", " ", " ", " ", " ", " ")})

        for(i in 0..theBoard.size - 1) {
            var rowArray = arrayOf(" ", " ", " ", " ", " ", " ", " ", " ")
            if(changeRows){
                for(j in 0..7) {
                    rowArray[j] = board[counter]
                    counter--
                    changeRows = false
                }
            }else{
                for(j in 0..7) {
                    rowArray[7-j] = board[counter]
                    counter--
                    changeRows = true
                }
            }

            theBoard[i] = rowArray
        }

        return theBoard
    }

    fun move(){
        setIDs()
        var rule = Rules(updateBoard(), id11, id12, id21, id22)
        if(board[id1][0] != board[id2][0]){
            if(player == 0){
                if(board[id1] == "lRook"){
                    if(rule.forRook()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.rlt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "lKnight"){
                    if(rule.forKnight()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.nlt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "lBishop"){
                    if(rule.forBishop()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.blt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "lQueen"){
                    if(rule.forQueen()) {
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.qlt60)
                        checkValidity = true
                    }else checkValidity = false
                }else if(board[id1] == "lKing"){
                    if(rule.forKing()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.klt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "lPawn"){
                    var result = rule.forLPawn()
                    if(result == 1){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.plt60)
                        checkValidity = true
                    }else if(result == 2){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.qlt60)
                        board[id1] = "lQueen"
                        checkValidity = true
                    }else checkValidity = false

                }

            }else if(player == 1){
                if(board[id1] == "dRook"){
                    if(rule.forRook()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.rdt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "dKnight"){
                    if(rule.forKnight()){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.ndt60)
                        checkValidity = true
                    }else checkValidity = false

                }else if(board[id1] == "dBishop"){
                    if(rule.forBishop()) {
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.bdt60)
                        checkValidity = true
                    }else checkValidity = false
                }else if(board[id1] == "dQueen"){
                    if(rule.forQueen()) {
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.qdt60)
                        checkValidity = true
                    }else checkValidity = false
                }else if(board[id1] == "dKing"){
                    if(rule.forKing()) {
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.kdt660)
                        checkValidity = true
                    }else checkValidity = false
                }else if(board[id1] == "dPawn"){
                    var result = rule.forDPawn()
                    if(result == 1){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.pdt60)
                        checkValidity = true
                    }else if(result == 2){
                        btn1.setImageDrawable(null)
                        btn2.setImageResource(R.drawable.qdt60)
                        board[id1] = "dQueen"
                        checkValidity = true
                    }else checkValidity = false

                }
            }
        }
    }



}