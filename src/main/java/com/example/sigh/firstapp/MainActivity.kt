package com.example.sigh.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.content.Intent


open class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var winner = -1
    var id1 = 0
    var id2 = 0
    var counter = 1
    var ldRookNeverMoved = true
    var rdRookNeverMoved = true
    var llRookNeverMoved = true
    var rlRookNeverMoved = true
    var dKingNeverMoved = true
    var lKingNeverMoved = true

    lateinit var btn1: ImageButton
    lateinit var btn2: ImageButton
    var player = 0
    var board = arrayOf("useless" ,"lRook", "lKnight", "lBishop", "lQueen", "lKing", "lBishop", "lKnight", "lRook",
            "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn",
            " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
            "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn",
            "dRook", "dKnight", "dBishop", "dKing", "dQueen", "dBishop", "dKnight", "dRook")
    protected fun setChessBoard() {
        if(winner == -1) {

            var chessBoard = Pieces(id1, id2, btn1, btn2, player, board, this)
            chessBoard.move()

            if (chessBoard.isValidMovement()) {
                if(id1 == 1){
                    llRookNeverMoved = false
                }else if(id1 == 8){
                    rlRookNeverMoved = false
                }else if(id1 == 5){
                    lKingNeverMoved = false
                }else if(id1 == 64){
                    ldRookNeverMoved = false
                }else if(id1 == 57){
                    rdRookNeverMoved = false
                }else if(id1 == 60){
                    dKingNeverMoved = false
                }
                board[id2] = board[id1]
                board[id1] = " "
                checkWin()
                if (player == 0) {
                    player = 1
                } else if (player == 1) {
                    player = 0
                }
            } else {
                Toast.makeText(this, "Invalid movement", Toast.LENGTH_SHORT).show()
            }
        }

    }

    protected fun onRestart(view: View){
        val i = baseContext.packageManager
                .getLaunchIntentForPackage(baseContext.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

    protected fun checkWin(){
        var isLKing = false
        var isDKing = false
        var restart = findViewById(R.id.restart)
        for(i in 1..board.size-1){
            if(board[i].equals("lKing")){
                isLKing = true
            }else if(board[i].equals("dKing")){
                isDKing = true
            }
        }

        if(!isLKing){
            winner = 1
            Toast.makeText(this, "Winner: Dark", Toast.LENGTH_SHORT).show()
            restart.visibility = View.VISIBLE
        }else if(!isDKing){
            winner = 0
            Toast.makeText(this, "Winner: Light", Toast.LENGTH_SHORT).show()
            restart.visibility = View.VISIBLE
        }
    }
    protected fun onClick(view: View) {
        var btn = view as ImageButton
        var id: Int = 0
        when (btn.id) {
            R.id.btn1 -> id = 1
            R.id.btn2 -> id = 2
            R.id.btn3 -> id = 3
            R.id.btn4 -> id = 4
            R.id.btn5 -> id = 5
            R.id.btn6 -> id = 6
            R.id.btn7 -> id = 7
            R.id.btn8 -> id = 8
            R.id.btn9 -> id = 9
            R.id.btn10 -> id = 10
            R.id.btn11 -> id = 11
            R.id.btn12 -> id = 12
            R.id.btn13 -> id = 13
            R.id.btn14 -> id = 14
            R.id.btn15 -> id = 15
            R.id.btn16 -> id = 16
            R.id.btn17 -> id = 17
            R.id.btn18 -> id = 18
            R.id.btn19 -> id = 19
            R.id.btn20 -> id = 20
            R.id.btn21 -> id = 21
            R.id.btn22 -> id = 22
            R.id.btn23 -> id = 23
            R.id.btn24 -> id = 24
            R.id.btn25 -> id = 25
            R.id.btn26 -> id = 26
            R.id.btn27 -> id = 27
            R.id.btn28 -> id = 28
            R.id.btn29 -> id = 29
            R.id.btn30 -> id = 30
            R.id.btn31 -> id = 31
            R.id.btn32 -> id = 32
            R.id.btn33 -> id = 33
            R.id.btn34 -> id = 34
            R.id.btn35 -> id = 35
            R.id.btn36 -> id = 36
            R.id.btn37 -> id = 37
            R.id.btn38 -> id = 38
            R.id.btn39 -> id = 39
            R.id.btn40 -> id = 40
            R.id.btn41 -> id = 41
            R.id.btn42 -> id = 42
            R.id.btn43 -> id = 43
            R.id.btn44 -> id = 44
            R.id.btn45 -> id = 45
            R.id.btn46 -> id = 46
            R.id.btn47 -> id = 47
            R.id.btn48 -> id = 48
            R.id.btn49 -> id = 49
            R.id.btn50 -> id = 50
            R.id.btn51 -> id = 51
            R.id.btn52 -> id = 52
            R.id.btn53 -> id = 53
            R.id.btn54 -> id = 54
            R.id.btn55 -> id = 55
            R.id.btn56 -> id = 56
            R.id.btn57 -> id = 57
            R.id.btn58 -> id = 58
            R.id.btn59 -> id = 59
            R.id.btn60 -> id = 60
            R.id.btn61 -> id = 61
            R.id.btn62 -> id = 62
            R.id.btn63 -> id = 63
            R.id.btn64 -> id = 64
        }
        if(counter == 1){
            id1 = id
            btn1 = btn
            counter = 2
        }else if(counter == 2){
            id2 = id
            btn2 = btn
            counter = 1
        }

        if(id1 != 0 && id2 != 0){
            setChessBoard()
            id1 = 0
            id2 = 0
        }


    }

}
