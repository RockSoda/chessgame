package com.example.sigh.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.analytics.FirebaseAnalytics


open class MainActivity: AppCompatActivity() {

    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
    var myEmail:String? = null
    private var mFirbaseAnalytics : FirebaseAnalytics? = null

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
    var color = Color.TRANSPARENT
    var player = -1
    var activePlayer = 1

    lateinit var btn1: ImageButton
    lateinit var btn2: ImageButton
    var board = arrayOf("useless" ,"lRook", "lKnight", "lBishop", "lQueen", "lKing", "lBishop", "lKnight", "lRook",
            "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn", "lPawn",
            " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
            "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn", "dPawn",
            "dRook", "dKnight", "dBishop", "dKing", "dQueen", "dBishop", "dKnight", "dRook")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirbaseAnalytics = FirebaseAnalytics.getInstance(this)

        myRef.setValue(null)
        var b:Bundle = intent.extras
        myEmail= b.getString("email")

        incommingCalls()
        playOnline()
        visitTable()
        getWinner()


    }

    fun getWinner(){
        myRef.child("Winner")
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try{
                            if(dataSnapshot!!.value.toString().equals("l")){
                                Toast.makeText(applicationContext, "Winner: Light", Toast.LENGTH_SHORT).show()
                                restart.visibility = View.VISIBLE
                                tableLayout.visibility = View.INVISIBLE
                            }else if(dataSnapshot!!.value.toString().equals("d")){
                                Toast.makeText(applicationContext, "Winner: Dark", Toast.LENGTH_SHORT).show()
                                restart.visibility = View.VISIBLE
                                tableLayout.visibility = View.INVISIBLE
                            }
                        }catch (ex:Exception){}
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })
    }

    fun visitTable(){
        myRef.child("Board")
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try{
                            if(dataSnapshot!!.value.toString().equals("Y")){
                                tableLayout.visibility = View.VISIBLE
                                accept.visibility = View.INVISIBLE
                                request.visibility = View.INVISIBLE
                            }
                        }catch (ex:Exception){}
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })
    }

    protected fun setChessBoard() {
        if(winner == -1) {

            var chessBoard = Pieces(id1, id2, btn1, btn2, player, board, ldRookNeverMoved, rdRookNeverMoved, llRookNeverMoved, rlRookNeverMoved, dKingNeverMoved, lKingNeverMoved, activePlayer)
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

                // Castling
                if(id1 == 5 && id2 == 7){
                    var btn8 = findViewById(R.id.btn8) as ImageButton
                    var btn6 = findViewById(R.id.btn6) as ImageButton
                    btn8.setImageDrawable(null)
                    btn6.setImageResource(R.drawable.rlt6)
                    board[8] = " "
                    board[6] = "lRook"
                }else if(id1 == 5 && id2 == 3){
                    var btn1 = findViewById(R.id.btn1) as ImageButton
                    var btn4 = findViewById(R.id.btn4) as ImageButton
                    btn1.setImageDrawable(null)
                    btn4.setImageResource(R.drawable.rlt6)
                    board[1] = " "
                    board[4] = "lRook"
                }else if(id1 == 60 && id2 == 58){
                    var btn57 = findViewById(R.id.btn57) as ImageButton
                    var btn59 = findViewById(R.id.btn59) as ImageButton
                    btn57.setImageDrawable(null)
                    btn59.setImageResource(R.drawable.rdt6)
                    board[57] = " "
                    board[59] = "dRook"
                }else if(id1 == 60 && id2 == 62){
                    var btn64 = findViewById(R.id.btn64) as ImageButton
                    var btn61 = findViewById(R.id.btn61) as ImageButton
                    btn64.setImageDrawable(null)
                    btn61.setImageResource(R.drawable.rdt6)
                    board[64] = " "
                    board[61] = "dRook"
                }

                board[id2] = board[id1]
                board[id1] = " "
                var ids: String = id1.toString() + "," +id2.toString()+","+player.toString()
                myRef.child("PlayOnline").child("move").setValue(ids)
                checkWin()

            }
        }

    }

    fun onRestart(view: View){
        val i = baseContext.packageManager
                .getLaunchIntentForPackage(baseContext.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

    protected fun checkWin(){
        var isLKing = false
        var isDKing = false
        for(i in 1..board.size-1){
            if(board[i].equals("lKing")){
                isLKing = true
            }else if(board[i].equals("dKing")){
                isDKing = true
            }
        }

        if(!isLKing){
            winner = 1
            myRef.child("Winner").setValue("d")
        }else if(!isDKing){
            winner = 0
            myRef.child("Winner").setValue("l")
        }
    }

    fun onClick(view: View) {
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
            val background = btn1.background
            if(background is ColorDrawable){
                color = background.color
            }
            btn1.setBackgroundColor(Color.GRAY)
            counter = 2

        }else if(counter == 2){
            id2 = id
            btn2 = btn
            btn1.setBackgroundColor(color)
            counter = 1
        }

        if(id1 != 0 && id2 != 0){
            setChessBoard()
            id1 = 0
            id2 = 0
        }


    }

    fun buRequestEvent(view: View) {
        var userEmail = etemail.text.toString()
        myRef.child("Users").child(splitString(userEmail)).child("Request").push().setValue(myEmail)
        player = 0
    }

    fun buAcceptEvent(view: View) {
        var userEmail = etemail.text.toString()
        myRef.child("Users").child(splitString(userEmail)).child("Request").push().setValue(myEmail)
        player = 1
        myRef.child("Board").setValue("Y")


    }

    @Suppress("UNCHECKED_CAST", "SENSELESS_COMPARISON")
    fun incommingCalls() {
        myRef.child("Users").child(splitString(myEmail!!)).child("Request")
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try{
                            val td = dataSnapshot!!.value as HashMap<String, Any>
                            if(td != null){
                                val value:String
                                for(key in td.keys){
                                    value = td[key] as String
                                    etemail.setText(value)
                                    accept.visibility = View.VISIBLE


                                    myRef.child("Users").child(splitString(myEmail!!)).child("Request").setValue(true)
                                    break
                                }

                            }

                        }catch (ex:Exception){}
                     }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })
    }

    fun opponentsMove(move: String){
        var btn1: ImageButton = getButton(move.split(",")[0].toInt())
        var btn2: ImageButton = getButton(move.split(",")[1].toInt())

        btn1.setImageDrawable(null)
        when(board[move.split(",")[0].toInt()]){
            "lRook" -> btn2.setImageResource(R.drawable.rlt6)
            "lKnight" -> btn2.setImageResource(R.drawable.nlt6)
            "lQueen"-> btn2.setImageResource(R.drawable.qlt6)
            "lKing"-> btn2.setImageResource(R.drawable.klt6)
            "lBishop"-> btn2.setImageResource(R.drawable.blt6)
            "lPawn"-> btn2.setImageResource(R.drawable.plt6)
            "dRook" -> btn2.setImageResource(R.drawable.rdt6)
            "dKnight" -> btn2.setImageResource(R.drawable.ndt6)
            "dQueen"-> btn2.setImageResource(R.drawable.qdt6)
            "dKing"-> btn2.setImageResource(R.drawable.kdt6)
            "dBishop"-> btn2.setImageResource(R.drawable.bdt6)
            "dPawn"-> btn2.setImageResource(R.drawable.pdt6)
        }


        //opponent castling
        if(move.split(",")[0].toInt() == 5 && move.split(",")[1].toInt() == 7){
            var btn8 = findViewById(R.id.btn8) as ImageButton
            var btn6 = findViewById(R.id.btn6) as ImageButton
            btn8.setImageDrawable(null)
            btn6.setImageResource(R.drawable.rlt6)
            board[8] = " "
            board[6] = "lRook"
        }else if(move.split(",")[0].toInt() == 5 && move.split(",")[1].toInt() == 3){
            var btn1 = findViewById(R.id.btn1) as ImageButton
            var btn4 = findViewById(R.id.btn4) as ImageButton
            btn1.setImageDrawable(null)
            btn4.setImageResource(R.drawable.rlt6)
            board[1] = " "
            board[4] = "lRook"
        }else if(move.split(",")[0].toInt() == 60 && move.split(",")[1].toInt() == 58){
            var btn57 = findViewById(R.id.btn57) as ImageButton
            var btn59 = findViewById(R.id.btn59) as ImageButton
            btn57.setImageDrawable(null)
            btn59.setImageResource(R.drawable.rdt6)
            board[57] = " "
            board[59] = "dRook"
        }else if(move.split(",")[0].toInt() == 60 && move.split(",")[1].toInt() == 62){
            var btn64 = findViewById(R.id.btn64) as ImageButton
            var btn61 = findViewById(R.id.btn61) as ImageButton
            btn64.setImageDrawable(null)
            btn61.setImageResource(R.drawable.rdt6)
            board[64] = " "
            board[61] = "dRook"
        }

            board[move.split(",")[1].toInt()] = board[move.split(",")[0].toInt()]
            board[move.split(",")[0].toInt()] = " "

    }

    fun getButton(id: Int): ImageButton{
        var btn:ImageButton? = null
        when (id) {
            1 -> btn = btn1
            2 -> btn = btn2
            3 -> btn = btn3
            4 -> btn = btn4
            5 -> btn = btn5
            6 -> btn = btn6
            7 -> btn = btn7
            8 -> btn = btn8
            9 -> btn = btn9
            10 -> btn = btn10
            11 -> btn = btn11
            12 -> btn = btn12
            13 -> btn = btn13
            14 -> btn = btn14
            15 -> btn = btn15
            16 -> btn = btn16
            17 -> btn = btn17
            18 -> btn = btn18
            19 -> btn = btn19
            20 -> btn = btn20
            21 -> btn = btn21
            22 -> btn = btn22
            23 -> btn = btn23
            24 -> btn = btn24
            25 -> btn = btn25
            26 -> btn = btn26
            27 -> btn = btn27
            28 -> btn = btn28
            29 -> btn = btn29
            30 -> btn = btn30
            31 -> btn = btn31
            32 -> btn = btn32
            33 -> btn = btn33
            34 -> btn = btn34
            35 -> btn = btn35
            36 -> btn = btn36
            37 -> btn = btn37
            38 -> btn = btn38
            39 -> btn = btn39
            40 -> btn = btn40
            41 -> btn = btn41
            42 -> btn = btn42
            43 -> btn = btn43
            44 -> btn = btn44
            45 -> btn = btn45
            46 -> btn = btn46
            47 -> btn = btn47
            48 -> btn = btn48
            49 -> btn = btn49
            50 -> btn = btn50
            51 -> btn = btn51
            52 -> btn = btn52
            53 -> btn = btn53
            54 -> btn = btn54
            55 -> btn = btn55
            56 -> btn = btn56
            57 -> btn = btn57
            58 -> btn = btn58
            59 -> btn = btn59
            60 -> btn = btn60
            61 -> btn = btn61
            62 -> btn = btn62
            63 -> btn = btn63
            64 -> btn = btn64
        }
        return btn!!
    }
    fun playOnline(){
        myRef.child("PlayOnline").child("move")
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try{
                            val move: String = dataSnapshot?.value.toString()
                            activePlayer = move.split(",")[2].toInt()
                            if(activePlayer != player && player!= -1){
                                opponentsMove(move)
                            }
                        }catch (ex:Exception){}
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })
    }
    fun splitString(str: String): String{
        var split = str.split("@")
        return split[0]
    }
}
