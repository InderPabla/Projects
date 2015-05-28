//Name:   Inderpreet Pabla

// To quit type- "quit"  WITHOUT THE QUOTES
// Sample Input, 
// Enter Input (Type "quit" to quit): b1 NNE
// Enter Input (Type "quit" to quit): b8 SSE
// Enter Input (Type "quit" to quit): a2 N
// Enter Input (Type "quit" to quit): c6 SWW
// Enter Input (Type "quit" to quit): a1 N 5 
// Enter Input (Type "quit" to quit): a5 SSE
// Enter Input (Type "quit" to quit): c2 NW
// Enter Input (Type "quit" to quit): quit
// r - b q k b n r
// p p p p p p p p
// - - - - - - - -
// - - - - - - - -
// - - - - - - - -
// P P N - - - - -
// R P - P P P P P
// - - B Q K B N R
// ---Dead List---
// Total Dead White Pawns:0
// Total Dead Black Pawns:0
// Total Dead White Rooks:0
// Total Dead Black Rooks:0
// Total Dead White Knights:0
// Total Dead Black Knights:1
// Total Dead White Bishops:0
// Total Dead Black Bishops:0
// Dead White Queen:0
// Dead Black Queen:0
// Dead White King:0
// Dead Black King:0

object ScalaChess {
    
    //main method is automatically called upon program run 
    def main(args: Array[String]): Unit = {
        // initialBoard: 2D array of 8x8. The board is flipped vertically, so a1 represents (0)(0) and h8 represents (7)(7)
        var initialBoard = Array(
                                 Array ("R","N","B","Q","K","B","N","R"),        
              						       Array ("P","P","P","P","P","P","P","P"),		   
              						       Array ("-","-","-","-","-","-","-","-"),
              						       Array ("-","-","-","-","-","-","-","-"),
              						       Array ("-","-","-","-","-","-","-","-"),
              						       Array ("-","-","-","-","-","-","-","-"),
              						       Array ("p","p","p","p","p","p","p","p"),
              						       Array ("r","n","b","q","k","b","n","r"))
         //starts IO                      
         start (initialBoard)  
    }
    
    // start: start called with chess board
    def start (board: Array[Array[String]]){
        print("Enter Input (Type \"quit\" to quit): ")
        val input = readLine() //IO read line
        
        if(!input.equalsIgnoreCase("quit")){
            val split : Array[String] = input.split("\\W+")
            val coords : List[Char] = split(0).toList
            val x : Int = coords.head-'a' + 1
            val y : Int = Integer.parseInt(coords.tail.head+"")
            val dir = split(1)
            var n = 10 
            if(split.length == 3)
                n = Integer.parseInt(split(2))  
            start(move(x,y,dir,n,board)) 
        }
        else{
            showBoard(board)
            showPieces(board)
        }
    }
    
    // showBoard: Prints the 2D board.
    def showBoard(board: Array[Array[String]]){
        
        for (i <- (7 to 0 by -1)){
            for (j <- (0 to 7 by 1)){
                print(board(i)(j) +" ");
            }
            print("\n");
        }
    }
    
    // showPieces: Prints the list of dead pieces from each side.
    def showPieces (board: Array[Array[String]]){
         println("---Dead List---")   
         println("Total Dead White Pawns:"+(8-countPiece("P",board)))
         println("Total Dead Black Pawns:"+(8-countPiece("p",board)))
         println("Total Dead White Rooks:"+(2-countPiece("R",board)))
         println("Total Dead Black Rooks:"+(2-countPiece("r",board)))
         println("Total Dead White Knights:"+(2-countPiece("N",board)))
         println("Total Dead Black Knights:"+(2-countPiece("n",board)))
         println("Total Dead White Bishops:"+(2-countPiece("B",board)))
         println("Total Dead Black Bishops:"+(2-countPiece("b",board)))
         println("Dead White Queen:"+(1-countPiece("Q",board)))
         println("Dead Black Queen:"+(1-countPiece("q",board)))
         println("Dead White King:"+(1-countPiece("K",board)))
         println("Dead Black King:"+(1-countPiece("k",board))) 
    }
    
    // countPiece: Counts the number of each type of piece on the board  
    def countPiece (p:String,board: Array[Array[String]]) : Int = {
         var count : Int = 0
         for (i <- (7 to 0 by -1)){
            for (j <- (0 to 7 by 1)){
                if(board(i)(j).equals(p))
                  count+=1
            }
          }
          return count
    }
    
    // getPiece: find the piece on the board given x and y positions local to the board
    def getPiece(x:Int,y:Int,board: Array[Array[String]]) : String ={
        return board(y-1)(x-1)  //flip x and y, because x = column, y = row
    }
    
    // isCoordEnemy: returns true of false depending on if if the piece on coordinate x y and p are enemies. 
    // Does it by checking both pieces uppercase type
    def isCoordEnemy (p:String, x:Int,y:Int, board: Array[Array[String]]) : Boolean = {
        var eq : Boolean = false
        var p2 = getPiece(x,y,board)
        if (p.equals(p.toUpperCase())){
           if(p2.equals(p2.toUpperCase())){
             eq = false
           }
           else{
             eq = true
           }
        }
        else{
           if(p2.equals(p2.toUpperCase())){
             eq = true
           }
           else{
             eq = false
           }
        }         
        return eq
    }
    
    // isCoordValid: returns true of false depending is coordinate equals "-", which represents empty
    def isCoordValid (x:Int,y:Int,board: Array[Array[String]]) : Boolean = {
        var eq : Boolean = false
        if("-".equals(board(y-1)(x-1)))
          eq = true
        return eq
    }
    
    // replacePiece: replaces piece on x,y with piece on x2,y2, and makes piece on x2,y2 empty since it has just been moved
    def replacePiece (x2:Int,y2:Int,x:Int,y:Int,board: Array[Array[String]]) : Array[Array[String]] = {
        board(y-1)(x-1) = getPiece(x2,y2,board)
        board(y2-1)(x2-1) = "-"
        return board
    }
    
    // getsCoords: finds all possible coordinates on the board from the initial coordinate given a direction
    def getCoords (x:Int,y:Int,dir:String) : List[(Int,Int)] ={
        // moves for North, North West, North East
        if (dir=="N"){
          	if(y>8) return List()
          	else{
              var L : List[(Int,Int)] =  getCoords(x,y+1,dir)
          	  return (x,y)+:L
            }
        } 
        if (dir=="NW"){
            if(y>8 || x<1) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x-1,y+1,dir)
              return (x,y)+:L
            }
        }
        if (dir=="NE"){
            if(y>8 || x>8) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x+1,y+1,dir)
              return (x,y)+:L
            }
        }
        
        // moves for South, South West, South East
        if (dir=="S"){
            if(y<1) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x,y-1,dir)
              return (x,y)+:L
            }
        } 
        if (dir=="SW"){
            if(y<1 || x<1) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x-1,y-1,dir)
              return (x,y)+:L
            }
        }
        if (dir=="SE"){
            if(y<1 || x>8) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x+1,y-1,dir)
              return (x,y)+:L
            }
        }
        
        // moves for West, East
        if (dir=="W"){
            if(x<1) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x-1,y,dir)
              return (x,y)+:L
            }
        }
        if (dir=="E"){
            if(x>8) return List()
            else{
              var L : List[(Int,Int)] =  getCoords(x+1,y,dir)
              return (x,y)+:L
            }
        }
        
        // moves for North North East, North East East, North North West, North West West
        if (dir=="NNE"){
            if(y+2>8 || x+1>8) return List()
            else{
              return List ((x,y),(x+1,y+2))
            }
        }
        if (dir=="NEE"){
            if(y+1>8 || x+2>8) return List()
            else{
              return List ((x,y),(x+2,y+1))
            }
        }
        if (dir=="NNW"){
            if(y+2>8 || x-1<1) return List()
            else{
              return List ((x,y),(x-1,y+2))
            }
        }
        if (dir=="NWW"){
            if(y+1>8 || x-2<1) return List()
            else{
              return List ((x,y),(x-2,y+1))
            }
        }
   
        // moves for South South East, South East East, South South West, South West West
        if (dir=="SSE"){
            if(y-2<1 || x+1>8) return List()
            else{
              return List ((x,y),(x+1,y-2))
            }
        }
        if (dir=="SEE"){
            if(y-1<1 || x+2>8) return List()
            else{
              return List ((x,y),(x+2,y-1))
            }
        }
        if (dir=="SSW"){
            if(y-2<1 || x-1<1) return List()
            else{
              return List ((x,y),(x-1,y-2))
            }
        }
        if (dir=="SWW"){
            if(y-1<1 || x-2<1) return List()
            else{
              return List ((x,y),(x-2,y-1))
            }
        }
        return List() // if no cases match, this should never be called
    }
    
    // getsAllValid: gets all valid coordinates given a set of coordinates. (It stops search after it realizes that an obstacle is reached, such as enemy or friendly)
    def getAllValid(coords: List[(Int,Int)],board: Array[Array[String]],p:String) : List[(Int,Int)] = {
        if(coords == List ())
            return List()
        else{
            
            var x = coords.head._1
            var y = coords.head._2
            if(isCoordValid(x,y,board)){
                var L : List[(Int,Int)] =  getAllValid(coords.tail,board,p) 
                return (x,y)+:L
            }
            else if(isCoordEnemy(p,x,y,board)){
                return List((x,y))
            }
            else{
                return List()  
            }
        }  
    }
    
    // move: gets new board by applying coordinates, direction, # of places to moves and current board 
    def move (x:Int,y:Int,dir:String,n:Int,board: Array[Array[String]]) : Array[Array[String]] = {
      if(!isCoordValid(x,y,board)){
            var listOfMoves : List[(Int,Int)] = moveValues(x,y,dir,n,board)
            
            if(listOfMoves==List ())
                return board
            else if(restrictMoves(getPiece(x,y,board),dir, listOfMoves.reverse.head._1,listOfMoves.reverse.head._2,board)){
                return replacePiece(x,y,listOfMoves.reverse.head._1,listOfMoves.reverse.head._2,board)  
            }
            else
                 return board
                
        }
        else
            return board
    }
    
    // moveValues: all perfect valid moves are returned 
    def moveValues (x:Int,y:Int,dir:String,n:Int,board: Array[Array[String]]) : List[(Int,Int)] = {
         val list = getCoords (x,y,dir)
         var p = getPiece(x,y,board)
         return getAllValid(list.tail,board,p).take(maxMoves(p,n))
    }
    
    // maxMoves: returns maximum moves allowed by piece
    def maxMoves(p:String, n:Int) : Int ={
       if(p.equalsIgnoreCase("p") || p.equalsIgnoreCase("k") || p.equalsIgnoreCase("n"))
           return 1
       else if(n>=0)
         return n
       else
         return 0 
    }
    
    // restrictMoves: restrict moves for pieces depending on direction, returns true or false
    //true = allowed, false = not allowed
    def restrictMoves(p:String,dir:String,x2:Int,y2:Int,board: Array[Array[String]]) : Boolean ={
        // pawn restrict
        if(p.equals("p")){
             if(dir.equals("S")){
                 if(isCoordEnemy("p",x2,y2,board) && !isCoordValid(x2,y2,board))
                    return false
                 else 
                    return true
             }  
             else if (dir.contains("S") && dir.length()==2){
                 if(isCoordEnemy("p",x2,y2,board) && !isCoordValid(x2,y2,board))
                    return true
                 else 
                    return false
             }
             else
               return false
        } 
        if(p.equals("P")){
            if(dir.equals("N")){
                 if(isCoordEnemy("P",x2,y2,board) && !isCoordValid(x2,y2,board))
                    return false
                 else 
                    return true
             }  
             else if (dir.contains("N") && dir.length()==2){
                 if(isCoordEnemy("P",x2,y2,board) && !isCoordValid(x2,y2,board))
                    return true
                 else 
                    return false
             }
             else
               return false   
        }
 
        // rook restrict  
        if(p.equalsIgnoreCase("r")){
              if(dir.length()==1)
                  return true
              else 
                  false
        }
        
        // bishop restrict
        if(p.equalsIgnoreCase("b")){
              if(dir.length()==2)
                  return true
              else 
                  false
        }
        
        // knight restrict
        if(p.equalsIgnoreCase("n")){
              if(dir.length()==3)
                  return true
              else 
                  false
        }
        
        // king and queen restrict
        if(p.equalsIgnoreCase("k") || p.equalsIgnoreCase("q")){
              if(dir.length()==1 | dir.length()==2)
                  return true
              else 
                  false
        }
        
        return false //if no cases match, then return false
    } // restrictMoves function
    
} //A6 object