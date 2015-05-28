;Name:   Inderpreet Pabla

; This was done in Eclispe's Clojure plugin.

; How to install the plugin
; Open Eclsipe -> Help -> Eclispe Marketplace -> In Find type "Clojure" -> Install Counterclockwise 0.31.1.SRABLE001 

; To make a Clojure Project (Once done installing Counterclockwise)
; File -> New -> Project -> Clojure -> Clojure Project 

; To start chess game type -     (start initialBoard)
; To quit type             -     quit


(ns clojureChess.core)

; cooX, cooY: allows to access size 2 vector (ex: [1 2] [4 4])
(defn cooX [[x y]] x)
(defn cooY [[x y]] y)

; vecP, vecX, vexY: allows to access size 3 vector  (ex: ["r" 1 2] ["K" 4 4])
(defn vecP [[p x y]] p)
(defn vecX [[p x y]] x)
(defn vecY [[p x y]] y)

; getsCoords: finds all possible coordinates on the board from the initial coordinate given a direction 
(defn getCoords [x y dir]
    ; moves for North, North West, North East
    (if (= dir "N")
    (if (<= y 8) (into [[x y]] (getCoords x (+ y 1) "N")))      
    (if (= dir "NW")
    (if (and (<= y 8) (>= x 1)) (into [[x y]] (getCoords (- x 1) (+ y 1) "NW")))  
    (if (= dir "NE")
    (if (and (<= y 8) (<= x 8)) (into [[x y]] (getCoords (+ x 1) (+ y 1) "NE")))
    
    ; moves for South, South West, South East
    (if (= dir "S")
    (if (>= y 1) (into [[x y]] (getCoords x (- y 1) "S")))  
    (if (= dir "SW")
    (if (and (>= y 1) (>= x 1)) (into [[x y]] (getCoords (- x 1) (- y 1) "SW")))
    (if (= dir "SE")
    (if (and (>= y 1) (<= x 8)) (into [[x y]] (getCoords (+ x 1) (- y 1) "SE")))
    
    ; moves for West, East
    (if (= dir "W")
    (if (>= x 1) (into [[x y]] (getCoords (- x 1) y "W")))  
    (if (= dir "E")
    (if (<= x 8) (into [[x y]] (getCoords (+ x 1) y "E")))
    
    ; moves for North North East, North East East, North North West, North West West
    (if (= dir "NNE")
    (if (and (<= (+ x 1) 8) (<= (+ y 2) 8)) [[x y] [(+ x 1) (+ y 2)]] [[x y]] ) 
    (if (= dir "NEE")
    (if (and (<= (+ x 2) 8) (<= (+ y 1) 8)) [[x y] [(+ x 2) (+ y 1)]] [[x y]] )
    (if (= dir "NNW")
    (if (and (>= (- x 1) 1) (<= (+ y 2) 8)) [[x y] [(- x 1) (+ y 2)]] [[x y]] ) 
    (if (= dir "NWW")
    (if (and (>= (- x 2) 1) (<= (+ y 1) 8)) [[x y] [(- x 2) (+ y 1)]] [[x y]] )
    
    ; moves for South South East, South East East, South South West, South West West
    (if (= dir "SSE")
    (if (and (<= (+ x 1) 8) (>= (- y 2) 1)) [[x y] [(+ x 1) (- y 2)]] [[x y]] ) 
    (if (= dir "SEE")
    (if (and (<= (+ x 2) 8) (>= (- y 1) 1)) [[x y] [(+ x 2) (- y 1)]] [[x y]] )
    (if (= dir "SSW")
    (if (and (>= (- x 1) 1) (>= (- y 2) 1)) [[x y] [(- x 1) (- y 2)]] [[x y]] ) 
    (if (= dir "SWW")
    (if (and (>= (- x 2) 1) (>= (- y 1) 1)) [[x y] [(- x 2) (- y 1)]] [[x y]] )
    
   )))))))))))))))))


; initialBoard: vector which conatain 64 size 3 vectors each with type [Type x-coordinate y-coordinate]
(def initialBoard [["r" 1 8] ["n" 2 8] ["b" 3 8] ["q" 4 8] ["k" 5 8] ["b" 6 8] ["n" 7 8] ["r" 8 8]
                   ["p" 1 7] ["p" 2 7] ["p" 3 7] ["p" 4 7] ["p" 5 7] ["p" 6 7] ["p" 7 7] ["p" 8 7]
                   ["-" 1 6] ["-" 2 6] ["-" 3 6] ["-" 4 6] ["-" 5 6] ["-" 6 6] ["-" 7 6] ["-" 8 6]
                   ["-" 1 5] ["-" 2 5] ["-" 3 5] ["-" 4 5] ["-" 5 5] ["-" 6 5] ["-" 7 5] ["-" 8 5]
                   ["-" 1 4] ["-" 2 4] ["-" 3 4] ["-" 4 4] ["-" 5 4] ["-" 6 4] ["-" 7 4] ["-" 8 4]
                   ["-" 1 3] ["-" 2 3] ["-" 3 3] ["-" 4 3] ["-" 5 3] ["-" 6 3] ["-" 7 3] ["-" 8 3]
                   ["P" 1 2] ["P" 2 2] ["P" 3 2] ["P" 4 2] ["P" 5 2] ["P" 6 2] ["P" 7 2] ["P" 8 2]
                   ["R" 1 1] ["N" 2 1] ["B" 3 1] ["Q" 4 1] ["K" 5 1] ["B" 6 1] ["N" 7 1] ["R" 8 1]])

; showBoard: prints out the board and dead pieces
(defn showBoard [[[c1 _ _]  [c2 _ _]  [c3 _ _]  [c4 _ _]  [c5 _ _]  [c6 _ _]  [c7 _ _]  [c8 _ _]
                   [c9 _ _]  [c10 _ _] [c11 _ _] [c12 _ _] [c13 _ _] [c14 _ _] [c15 _ _] [c16 _ _]
                   [c17 _ _] [c18 _ _] [c19 _ _] [c20 _ _] [c21 _ _] [c22 _ _] [c23 _ _] [c24 _ _]
                   [c25 _ _] [c26 _ _] [c27 _ _] [c28 _ _] [c29 _ _] [c30 _ _] [c31 _ _] [c32 _ _]
                   [c33 _ _] [c34 _ _] [c35 _ _] [c36 _ _] [c37 _ _] [c38 _ _] [c39 _ _] [c40 _ _]
                   [c41 _ _] [c42 _ _] [c43 _ _] [c44 _ _] [c45 _ _] [c46 _ _] [c47 _ _] [c48 _ _]
                   [c49 _ _] [c50 _ _] [c51 _ _] [c52 _ _] [c53 _ _] [c54 _ _] [c55 _ _] [c56 _ _]
                   [c57 _ _] [c58 _ _] [c59 _ _] [c60 _ _] [c61 _ _] [c62 _ _] [c63 _ _] [c64 _ _]
                  ] [a b c d e f g h i j k l]] (
                     ;prints board
                     println " --------------------------\n"
                               "|"c1  "" c2  "" c3  "" c4  "" c5  "" c6  "" c7  "" c8"|"  "\n"
                               "|"c9  "" c10 "" c11 "" c12 "" c13 "" c14 "" c15 "" c16"|" "\n"
                               "|"c17 "" c18 "" c19 "" c20 "" c21 "" c22 "" c23 "" c24"|" "\n"
                               "|"c25 "" c26 "" c27 "" c28 "" c29 "" c30 "" c31 "" c32"|" "\n"
                               "|"c33 "" c34 "" c35 "" c36 "" c37 "" c38 "" c39 "" c40"|" "\n" 
                               "|"c41 "" c42 "" c43 "" c44 "" c45 "" c46 "" c47 "" c48"|" "\n" 
                               "|"c49 "" c50 "" c51 "" c52 "" c53 "" c54 "" c55 "" c56"|" "\n" 
                               "|"c57 "" c58 "" c59 "" c60 "" c61 "" c62 "" c63 "" c64"|" "\n" 
                               "--------------------------\n"
                               ;prints dead pieces
                                "---Dead List---\n"
                                "Total Dead White Pawns:" a "\n"
                                "Total Dead Black Pawns:" b "\n"
                                "Total Dead White Rooks:" c "\n"
                                "Total Dead Black Rooks:" d "\n"
                                "Total Dead White Knights:" e "\n"
                                "Total Dead Black Knights:" f "\n"
                                "Total Dead White Bishops:" g "\n" 
                                "Total Dead Black Bishops:" h "\n"
                                "Dead White Queen:" i "\n"
                                "Dead Black Queen:" j "\n"
                                "Dead White King:" k "\n"
                                "Dead Black King:" l "\n"
                     ))

; isUp: returns true of false if the the given string is in uppercase
(defn isUp [p] (= p (.toUpperCase p)))

; findNum: calulates the index of the sie 3 vector in board. (ex: [8 1] = 64. [1 1] = 57, [8 8] = 1)
(defn findNum [x y board] (if (and (= (vecX (first board)) x) (= (vecY (first board)) y))
                            1 (+ (findNum x y (rest board)) 1)))

; getPiece: find the piece on the board given x and y positions
(defn getPiece [x y board] (vecP (first (replace board [(- (findNum x y board) 1)]))))

; isCoordValid: returns true of false depending is coordinate equals "-", which represents empty
(defn isCoordValid [x y board] (= (getPiece x y board) "-"))

; removePiece: given board, x and y coordinates, it converts that position to empty "-"
(defn removePiece [x y board] (assoc board (- (findNum x y board) 1) ["-" x y] ))

; replacePiece: replaces piece on x y with piece on x2 y2, and makes piece on x2 y2 empty since it has just been moved
(defn replacePiece [x2 y2 [x y] board] 
                      (removePiece x2 y2 (assoc board (- (findNum x y board) 1) [(getPiece x2 y2 board) x y])))

; isCoordEnemy: returns true of false depending on if if the piece on coordinate x y and p are enemies. 
; Does it by checking both pieces uppercase type
(defn isCoordEnemy [p x y board] (
                                   if (isUp p) 
                                   (if (isUp (getPiece x y board)) false true ) 
                                   (if (isUp (getPiece x y board)) true false ) 
                                 ))

; getsAllValid: gets all valid coordinates given a set of coordinates. (It stops search after it realizes that an obsticle is reached, such as enemy or friendly) 
(defn getAllValid [coords board p] (
                                    if (empty? coords) [] ;then
                                    (
                                      if (isCoordValid (cooX (first coords)) (cooY (first coords)) board) 
                                         (into [[(cooX (first coords)) (cooY (first coords))]] (getAllValid (rest coords) board p)) ;then
                                         (
                                            if (isCoordEnemy p (cooX (first coords)) (cooY (first coords)) board) 
                                              [[(cooX (first coords)) (cooY (first coords))]] ;then
                                              [] ;else
                                         );else
                                    );else 
                                   ); function block 
) ;getAllValid

; maxMoves: returns maximum moves allowed by piece
(defn maxMoves [p n] (if(or (= p "p") (= p "P") (= p "k") (= p "K") (= p "n") (= p "N")) 1
         (if (> n 0) n 0)))

; moveValues: all perfect valid moves are returned 
(defn moveValues [x y dir n board] 
         (getAllValid (into [] (take (maxMoves (getPiece x y board ) n) (rest (getCoords x y dir)))) board (getPiece x y board)))


; pawnCheck: move restrictions for pawn
(defn pawnCheck [p dir [x y] [x2 y2] board] (case p
                                                  "p" ( case dir
                                                        "S" (if  (and (isCoordEnemy "p" x2 y2 board) (not= "-" (getPiece x2 y2 board))) false true)
                                                        "SW" (if  (and (isCoordEnemy "p" x2 y2 board) (not= "-" (getPiece x2 y2 board))) true false)
                                                        "SE" (if  (and (isCoordEnemy "p" x2 y2 board) (not= "-" (getPiece x2 y2 board))) true false)
                                                        false
                                                      )
                                                  "P" ( case dir
                                                        "N" (if  (and (isCoordEnemy "P" x2 y2 board) (not= "-" (getPiece x2 y2 board))) false true)
                                                        "NW" (if  (and (isCoordEnemy "P" x2 y2 board) (not= "-" (getPiece x2 y2 board))) true false)
                                                        "NE" (if  (and (isCoordEnemy "P" x2 y2 board) (not= "-" (getPiece x2 y2 board))) true false)
                                                        false
                                                      )))

; kingAndQueenCheck: move restrictions for king and queen (both move in the same directions)
(defn kingAndQueenCheck [p dir] ( case p
                                  "k" ( case dir
                                         "N" true "S" true "E" true "W" true "NW" true "NE" true "SW" true "SE" true false 
                                      )
                                  "K" ( case dir
                                         "N" true "S" true "E" true "W" true "NW" true "NE" true "SW" true "SE" true false 
                                      )
                                  "q" ( case dir
                                         "N" true "S" true "E" true "W" true "NW" true "NE" true "SW" true "SE" true false 
                                      )
                                  "Q" ( case dir
                                         "N" true "S" true "E" true "W" true "NW" true "NE" true "SW" true "SE" true false 
                                      )
                                ))

; rookCheck: move restrictions for rook
(defn rookCheck [p dir] ( case p
                          "r" ( case dir
                                 "N" true "S" true "E" true "W" true false 
                              )
                          "R" ( case dir
                                 "N" true "S" true "E" true "W" true false 
                              )))

; bishopCheck: move restrictions for bishop
(defn bishopCheck [p dir] ( case p 
                             "b" ( case dir
                                   "NW" true "NE" true "SW" true "SE" true false 
                                )
                            "B" ( case dir
                                    "NW" true "NE" true "SW" true "SE" true false 
                                )))

; knightCheck: move restrictions for knight
(defn knightCheck [p dir] ( case p
                            "n" ( case dir
                                   "NNW" true "NNE" true "NWW" true "NEE" true "SSW" true "SSE" true "SWW" true "SEE" true false 
                                )
                            "N" ( case dir
                                   "NNW" true "NNE" true "NWW" true "NEE" true "SSW" true "SSE" true "SWW" true "SEE" true false 
                                )))
                            
; restrictMoves: move restrictions for pieces
(defn restrictMoves [p dir [x y] [x2 y2] board] ( case p
                                                  "p" (pawnCheck p dir [x y] [x2 y2] board)
                                                  "P" (pawnCheck p dir [x y] [x2 y2] board)
                                                  "r" (rookCheck p dir)
                                                  "R" (rookCheck p dir)
                                                  "b" (bishopCheck p dir)
                                                  "B" (bishopCheck p dir)
                                                  "n" (knightCheck p dir)
                                                  "N" (knightCheck p dir)
                                                  "k" (kingAndQueenCheck p dir)
                                                  "K" (kingAndQueenCheck p dir)
                                                  "q" (kingAndQueenCheck p dir)
                                                  "Q" (kingAndQueenCheck p dir)
                                                  false))

; move: gets new board by applying coordinates, direction, # of places to moves and board 
(defn move [[x y] dir n board] (
                                  if (isCoordValid x y board) 
                                  board ;then
                                  (
                                 if(empty? (moveValues x y dir n board))
                                   board ;then
                                       (
                                         if (restrictMoves (getPiece x y board) dir [x y] (first (reverse (moveValues x y dir n board))) board )  
                                         (replacePiece x y (first (reverse (moveValues x y dir n board))) board) board
                                       ); else 
                                  ); else
                           )

)

; len: finds length of the array
(defn len [array] (if (empty? array) 0 (+ 1 (len(rest array)))))

; stringToNum: converts given characters to cooresponding integers
(defn stringToNum [charValue] (case charValue
  \a 1 \b 2 \c 3 \d 4 \e 5 \f 6 \g 7 \h 8 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 )) 

; convertToCoord: converts given string to x y coordinates. (ex: a1 = [1 1], e5 = [5 5], h2 = [8 2])
(defn convertToCoord [string]  [(stringToNum (cooX (into [] ( seq (char-array string))))) 
                                (stringToNum (cooY (into [] ( seq (char-array string)))))
                               ])  

; toInt: converts string to integer (ex: "2" = 2, "5" = 5)
(defn toInt [integer] (Integer. (re-find  #"\d+" integer )))

; parse: takes string and uses whitespace split the string to create vectors of string (ex: "a3 NNE 4" = ["a3" "NNE" "4"]) 
(defn parse [string] (clojure.string/split string #"\s+"))

; countPiece: counts the number of each type of piece on the board
(defn countPiece [p board] ( if (empty? board) 0 (if (= p (vecP(first board))) (+ 1 (countPiece p (rest board))) (+ 0 (countPiece p (rest board))) )))

; getKilled: gets all the kills pieces from the board
(defn getKilled [board] [  (- 8 (countPiece "P" board))
                           (- 8 (countPiece "p" board))
                           (- 2 (countPiece "R" board))
                           (- 2 (countPiece "r" board))
                           (- 2 (countPiece "N" board))
                           (- 2 (countPiece "n" board))
                           (- 2 (countPiece "B" board))
                           (- 2 (countPiece "b" board))
                           (- 1 (countPiece "Q" board))
                           (- 1 (countPiece "q" board))
                           (- 1 (countPiece "K" board))
                           (- 1 (countPiece "k" board))
                        ])

; move2: parses the string given intput and passes information on to move modify the board
(defn move2 [inputString board] (if (= 3 (len(parse inputString))) 
     (move (convertToCoord (first (parse inputString))) 
     (first (rest (parse inputString)))
     (toInt (first (rest (rest (parse inputString)))))
     board)
     (if (= 2 (len(parse inputString))) 
     (move (convertToCoord (first (parse inputString))) (first (rest (parse inputString))) 10 board))))


; start: the IO starts by calling the start function with a board (ex: start initialBoard)
(defn start [board]
   (let [input (read-line)]                  
   (if (= input "quit") (showBoard board (getKilled board)) (start (move2 input board)))))