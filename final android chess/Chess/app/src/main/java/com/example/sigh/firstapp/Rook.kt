package com.example.sigh.firstapp

class Rook(var theBoard: kotlin.Array<Array<String>>, var id11: Int, var id12: Int, var id21: Int, var id22: Int) {
    fun checkStraight(): Boolean{
        var tmp = -1
        if(id11 == id21){
            if(id12 < id22){
                tmp = id22-1
                while(tmp != id12){
                    if(!theBoard[id11][tmp].equals(" ")){
                        return false
                    }
                    tmp -= 1
                }
            }else{
                tmp = id22+1
                while(tmp != id12){
                    if(!theBoard[id11][tmp].equals(" ")){
                        return false
                    }
                    tmp += 1
                }
            }
        }else if(id12 == id22){
            if(id11 < id21){
                tmp = id21-1
                while(tmp != id11){
                    if(!theBoard[tmp][id12].equals(" ")){
                        return false
                    }
                    tmp -= 1
                }
            }else{
                tmp = id21+1
                while(tmp != id11){
                    if(!theBoard[tmp][id12].equals(" ")){
                        return false
                    }
                    tmp += 1
                }
            }
        }
        return true
    }
    fun forRook(): Boolean{
        if(id11 == id21 || id12 == id22){
            return checkStraight()
        }
        return false
    }

}
