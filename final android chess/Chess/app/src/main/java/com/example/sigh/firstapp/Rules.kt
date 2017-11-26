package com.example.sigh.firstapp


class Rules(var theBoard: kotlin.Array<Array<String>>, var id11: Int, var id12: Int, var id21: Int, var id22: Int){

    fun forLPawn(): Int{
        if((id11 == 6) && ((id21 == 5 || id21 == 4) && id22 == id12 && theBoard[id21][id22].equals(" "))){
            return 1

        }else if((id11-1 == id21 && id12-1 == id22) || (id11-1 == id21 && id12+1 == id22)){
            if(theBoard[id21][id22][0] == 'd'){
                if(id21 == 0){
                    return 2
                }else{
                    return 1
                }
            }
        }else if((id21 == id11-1)&&(id12 == id22)&&theBoard[id21][id22].equals(" ")){
                if(id21 == 0){
                    return 2
                }else{
                    return 1
                }
        }

        return 0

    }

    fun forDPawn(): Int{
        if((id11 == 1) && ((id21 == 2 || id21 == 3) && id22 == id12 && theBoard[id21][id22].equals(" "))){
            return 1
        }else if((id11+1 == id21 && id12-1 == id22) || (id11+1 == id21 && id12+1 == id22)){
            if(theBoard[id21][id22][0] == 'l'){
                if(id21 == 7){
                    return 2
                }else{
                    return 1
                }
            }
        }else if((id21 == id11+1)&&(id12 == id22)&&theBoard[id21][id22].equals(" ")){
            if(id21 == 7){
                return 2
            }else{
                return 1
            }
        }

        return 0
    }

    fun forKnight(): Boolean{
        if(id11-2 == id21){
            if(id12-1 == id22 || id12 +1 == id22){
                return true
            }
        }else if(id11+2 == id21){
            if(id12-1 == id22 || id12 +1 == id22){
                return true
            }
        }else if(id12-2 == id22){
            if(id11-1 == id21 || id11+1 == id21){
                return true
            }
        }else if(id12+2 == id22){
            if(id11-1 == id21 || id11+1 == id21){
                return true
            }
        }
        return false
    }

    fun forKing(): Boolean{
        if((id21 == id11 || id21 == id11-1 || id21 == id11+1) && (id22 == id12 || id22 == id12-1 || id22 == id12+1)){
            return true
        }
        return false
    }

    fun forRook(): Boolean{
        var rook = Rook(theBoard, id11, id12, id21, id22)
        return rook.forRook()
    }

    fun forBishop(): Boolean{
        var bishop = Bishop(theBoard, id11, id12, id21, id22)
        return bishop.forBishop()
    }

    fun forQueen(): Boolean{
        var queen = Queen(theBoard, id11, id12, id21, id22)
        return queen.forQueen()
    }
}
