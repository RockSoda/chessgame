package com.example.sigh.firstapp

class Bishop(var theBoard: kotlin.Array<Array<String>>, var id11: Int, var id12: Int, var id21: Int, var id22: Int) {
    fun checkSlant(): Boolean{
        var tmp1 = -1
        var tmp2 = -1
        if(id21>id11){
            if(id22<id12){
                tmp1 = id21-1
                tmp2 = id22+1
                while(tmp1 != id11 && tmp2 != id12){
                    if(!theBoard[tmp1][tmp2].equals(" ")){
                        return false
                    }
                    tmp1 -= 1
                    tmp2 += 1
                }
            }else if(id22>id12){
                tmp1 = id21-1
                tmp2 = id22-1
                while(tmp1 != id11 && tmp2 != id12){
                    if(!theBoard[tmp1][tmp2].equals(" ")){
                        return false
                    }
                    tmp1 -= 1
                    tmp2 -= 1
                }
            }
        }else if(id21<id11){
            if(id22<id12){
                tmp1 = id21+1
                tmp2 = id22+1
                while(tmp1 != id11 && tmp2 != id12){
                    if(!theBoard[tmp1][tmp2].equals(" ")){
                        return false
                    }
                    tmp1 += 1
                    tmp2 += 1
                }
            }else if(id22>id12){
                tmp1 = id21+1
                tmp2 = id22-1
                while(tmp1 != id11 && tmp2 != id12){
                    if(!theBoard[tmp1][tmp2].equals(" ")){
                        return false
                    }
                    tmp1 += 1
                    tmp2 -= 1
                }
            }
        }
        return true
    }

    fun forBishop(): Boolean{
        if(id11+id12 == id21+id22 || id11-id12 == id21-id22){
            return checkSlant()
        }

        return false
    }


}