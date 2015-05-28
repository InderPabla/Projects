%Name:   Inderpreet Pabla
% To start chess game type -     "a5:start()."    WITHOUT THE QUOTES
% To quit type             -     "quit"           WITHOUT THE QUOTES
%
% Sample Input
% a5:start()
% b1 NNE
% b8 SSE
% a2 N
% c6 SWW
% a1 N 5 
% a5 SSE
% c2 NW
% quit
% -----------------
% |r - b q k b n r|
% |p p p p p p p p|
% |- - - - - - - -|
% |- - - - - - - -|
% |- - - - - - - -|
% |P P N - - - - -|
% |R P - P P P P P|
% |- - B Q K B N R|
% -----------------
% ---Dead List---
% Total Dead White Pawns:0
% Total Dead Black Pawns:0
% Total Dead White Rooks:0
% Total Dead Black Rooks:0
% Total Dead White Knights:0
% Total Dead Black Knights:1
% Total Dead White Bishops:0
% Total Dead Black Bishops:0
% Dead White Queen:0
% Dead Black Queen:0
% Dead White King:0
% Dead Black King:0
% ok

-module(erlangChess). 
-export([start/0]). % Exporting only start, because it's connected it all other

% initialBoard: vector which conatain 64 size 3 vectors each with type [Type x-coordinate y-coordinate]
initialBoard() -> [["r",1,8],["n",2,8],["b",3,8],["q",4,8],["k",5,8],["b",6,8],["n",7,8],["r",8,8],
				["p",1,7],["p",2,7],["p",3,7],["p",4,7],["p",5,7],["p",6,7],["p",7,7],["p",8,7],
				["-",1,6],["-",2,6],["-",3,6],["-",4,6],["-",5,6],["-",6,6],["-",7,6],["-",8,6],
				["-",1,5],["-",2,5],["-",3,5],["-",4,5],["-",5,5],["-",6,5],["-",7,5],["-",8,5],
				["-",1,4],["-",2,4],["-",3,4],["-",4,4],["-",5,4],["-",6,4],["-",7,4],["-",8,4],
				["-",1,3],["-",2,3],["-",3,3],["-",4,3],["-",5,3],["-",6,3],["-",7,3],["-",8,3],
				["P",1,2],["P",2,2],["P",3,2],["P",4,2],["P",5,2],["P",6,2],["P",7,2],["P",8,2],
				["R",1,1],["N",2,1],["B",3,1],["Q",4,1],["K",5,1],["B",6,1],["N",7,1],["R",8,1]	
			   ].
			   
% showBoard: prints out the board and dead pieces			   
showBoard([[C1,1,8],[C2,2,8],[C3,3,8],[C4,4,8],[C5,5,8],[C6,6,8],[C7,7,8],[C8,8,8],
			[C9,1,7],[C10,2,7],[C11,3,7],[C12,4,7],[C13,5,7],[C14,6,7],[C15,7,7],[C16,8,7],
			[C17,1,6],[C18,2,6],[C19,3,6],[C20,4,6],[C21,5,6],[C22,6,6],[C23,7,6],[C24,8,6],
			[C25,1,5],[C26,2,5],[C27,3,5],[C28,4,5],[C29,5,5],[C30,6,5],[C31,7,5],[C32,8,5],
			[C33,1,4],[C34,2,4],[C35,3,4],[C36,4,4],[C37,5,4],[C38,6,4],[C39,7,4],[C40,8,4],
			[C41,1,3],[C42,2,3],[C43,3,3],[C44,4,3],[C45,5,3],[C46,6,3],[C47,7,3],[C48,8,3],
			[C49,1,2],[C50,2,2],[C51,3,2],[C52,4,2],[C53,5,2],[C54,6,2],[C55,7,2],[C56,8,2],
			[C57,1,1],[C58,2,1],[C59,3,1],[C60,4,1],[C61,5,1],[C62,6,1],[C63,7,1],[C64,8,1]	
		   ], [A,B,C,D,E,F,G,H,I,J,K,L]) ->    
		   io:fwrite(
                "-----------------\n" ++		   
		        "|" ++ C1  ++" "++  C2  ++" "++  C3  ++" "++  C4  ++" "++  C5  ++" "++  C6  ++" "++  C7  ++" "++  C8  ++ "|\n" ++
			    "|" ++ C9  ++" "++  C10 ++" "++  C11 ++" "++  C12 ++" "++  C13 ++" "++  C14 ++" "++  C15 ++" "++  C16 ++ "|\n" ++
			    "|" ++ C17 ++" "++  C18 ++" "++  C19 ++" "++  C20 ++" "++  C21 ++" "++  C22 ++" "++  C23 ++" "++  C24 ++ "|\n" ++
			    "|" ++ C25 ++" "++  C26 ++" "++  C27 ++" "++  C28 ++" "++  C29 ++" "++  C30 ++" "++  C31 ++" "++  C32 ++ "|\n" ++
			    "|" ++ C33 ++" "++  C34 ++" "++  C35 ++" "++  C36 ++" "++  C37 ++" "++  C38 ++" "++  C39 ++" "++  C40 ++ "|\n" ++
			    "|" ++ C41 ++" "++  C42 ++" "++  C43 ++" "++  C44 ++" "++  C45 ++" "++  C46 ++" "++  C47 ++" "++  C48 ++ "|\n" ++
			    "|" ++ C49 ++" "++  C50 ++" "++  C51 ++" "++  C52 ++" "++  C53 ++" "++  C54 ++" "++  C55 ++" "++  C56 ++ "|\n" ++
			    "|" ++ C57 ++" "++  C58 ++" "++  C59 ++" "++  C60 ++" "++  C61 ++" "++  C62 ++" "++  C63 ++" "++  C64 ++ "|\n" ++
				"-----------------\n" ++
				"---Dead List---\n" ++
			    "Total Dead White Pawns:" ++ lists:flatten(io_lib:format("~p", [A])) ++ "\n" ++
			    "Total Dead Black Pawns:" ++ lists:flatten(io_lib:format("~p", [B])) ++ "\n" ++
			    "Total Dead White Rooks:" ++ lists:flatten(io_lib:format("~p", [C])) ++ "\n" ++
			    "Total Dead Black Rooks:" ++ lists:flatten(io_lib:format("~p", [D])) ++ "\n" ++
			    "Total Dead White Knights:" ++ lists:flatten(io_lib:format("~p", [E])) ++ "\n" ++
			    "Total Dead Black Knights:" ++ lists:flatten(io_lib:format("~p", [F])) ++ "\n" ++
			    "Total Dead White Bishops:" ++ lists:flatten(io_lib:format("~p", [G])) ++ "\n" ++ 
			    "Total Dead Black Bishops:" ++ lists:flatten(io_lib:format("~p", [H])) ++ "\n" ++
			    "Dead White Queen:" ++ lists:flatten(io_lib:format("~p", [I])) ++ "\n" ++
			    "Dead Black Queen:" ++ lists:flatten(io_lib:format("~p", [J])) ++ "\n" ++
			    "Dead White King:" ++ lists:flatten(io_lib:format("~p", [K])) ++ "\n" ++
			    "Dead Black King:" ++ lists:flatten(io_lib:format("~p", [L])) ++ "\n"
				).

% countPiece: counts the number of each type of piece on the board				
countPiece (P1, [[P2,_,_]|Rest]) -> countPieceHelper(P1==P2,P1,Rest).
countPieceHelper (true,_,[])->1;
countPieceHelper (false,_,[])->0;
countPieceHelper (false,P1,Rest)-> 0 + countPiece(P1,Rest);
countPieceHelper (true,P1,Rest)-> 1 + countPiece(P1,Rest).

% getKilled: gets all the kills pieces from the board
getKilled (Board)->[8 - (countPiece ("P", Board)),
					8 - (countPiece ("p", Board)),
					2 - (countPiece ("R", Board)),
					2 - (countPiece ("r", Board)),
					2 - (countPiece ("N", Board)),
					2 - (countPiece ("n", Board)),
					2 - (countPiece ("B", Board)),
					2 - (countPiece ("b", Board)),
					1 - (countPiece ("Q", Board)),
					1 - (countPiece ("q", Board)),
					1 - (countPiece ("K", Board)),
					1 - (countPiece ("k", Board))].			   

% getPiece: find the piece on the board given x and y positions					
getPiece (_, _, [[P1,_,_] | []  ]) ->  P1;
getPiece (X, Y, [[P1,X,Y] | _]) ->  P1;
getPiece (X, Y, [[_,_,_] | Rest]) ->  getPiece (X,Y,Rest).

% isCoordValid: returns true of false depending is coordinate equals "-", which represents empty
isCoordValid (_,_,[[P1,_,_]|[]]) -> P1 == "-";
isCoordValid (X,Y,[[P1,X,Y]|_]) -> P1 == "-";
isCoordValid (X,Y,[_|Rest]) -> isCoordValid (X,Y,Rest).

% isCoordEnemy: returns true of false depending on if if the piece on coordinate x y and p are enemies. 
% Does it by checking both pieces uppercase type
isCoordEnemy (P1, X, Y, Board) -> isCoordEnemyHelper (isUp(P1),isUp(getPiece (X,Y,Board))).
isCoordEnemyHelper (false, false) -> false;
isCoordEnemyHelper (true, false) -> true;
isCoordEnemyHelper (false, true) -> true;
isCoordEnemyHelper (true, true) -> false.

% isUp: returns true of false if the the given string is in uppercase										
isUp (P1) ->  P1 == (string:to_upper(P1)).

% findNum: calulates the index of the sie 3 vector in board. (ex: [8,1] = 64. [1,1] = 57, [8,8] = 1)
findNum (_,_,[[_,_,_]|[]]) -> 1;
findNum (X,Y,[[_,X,Y]|_]) -> 1;
findNum (X,Y,[_|Rest]) -> 1 + findNum (X,Y,Rest).

% move: gets new board by applying coordinates, direction, # of places to moves and board 
move ([X,Y],Dir,N,Board) -> moveHelper( isCoordValid(X,Y,Board), 
										moveValues(X,Y,Dir,N,Board), 
										restrictMoves 	(
												getPiece (X, Y, Board), 
												Dir, 
												head(rev(moveValues (X, Y, Dir, N, Board))), 
												Board 	),
										[X,Y], 
										Board ).
moveHelper(true,_,_,_,Board) -> Board;
moveHelper(false,[],_,_,Board) -> Board;
moveHelper(false,_,false,_,Board) -> Board;
moveHelper(false,Moves,true,[X,Y],Board) -> replacePiece (X, Y, (head (rev (Moves) )), Board).

% getsAllValid: gets all valid coordinates given a set of coordinates. (It stops search after it realizes that an obsticle is reached, such as enemy or friendly)
getAllValid ([], _, _) -> [];
getAllValid ([[X,Y]|Rest], Board, P1) -> getAllValidHelper(isCoordValid(X,Y,Board),isCoordEnemy(P1,X,Y,Board),X,Y,Rest,Board,P1). 									
getAllValidHelper(false,false,_,_,_,_,_) -> [];							
getAllValidHelper(false,true,X,Y,_,_,_) -> [[X,Y]];
getAllValidHelper(true,_,X,Y,Rest,Board,P1) -> [[X,Y]] ++ getAllValid (Rest, Board, P1).

% moveValues: all perfect valid moves are returned 
moveValues (X,Y,Dir,N,Board) -> getAllValid ( lists:sublist( tail( getCoords(X,Y,Dir)   )     , (maxMoves(getPiece(X,Y,Board),N))),Board,getPiece(X,Y,Board) ).

% tail: returns tail of list
tail([]) -> [];
tail([_|Rest]) -> Rest.

% head: returns head of list
head ([]) -> [];
head ([Head|_]) -> Head.

% rev: returns reversed list
rev(List) -> lists:reverse(List).

% maxMoves: returns maximum moves allowed by piece
maxMoves ("p", _) -> 1;
maxMoves ("P", _) -> 1;
maxMoves ("k", _) -> 1;
maxMoves ("K", _) -> 1;
maxMoves ("n", _) -> 1;
maxMoves ("N", _) -> 1;
maxMoves (_, N) when N >= 0 -> N;
maxMoves (_,_) -> 0.

% restrictMoves: restrict moves for pieces depending on direction, returns true or false.
% pawn restrict
restrictMoves ("p", "S", [X2,Y2], Board) -> 
								restrictMoveHelper ("p","S",isCoordEnemy ("p",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("p", "SW", [X2,Y2], Board) -> 
								restrictMoveHelper ("p","SW",isCoordEnemy ("p",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("p", "SE", [X2,Y2], Board) -> 
								restrictMoveHelper ("p","SE",isCoordEnemy ("p",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("p", _, _, _) -> false;
restrictMoves ("P", "N", [X2,Y2], Board) -> 
								restrictMoveHelper ("P","N",isCoordEnemy ("P",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("P", "NW", [X2,Y2], Board) -> 
								restrictMoveHelper ("P","NW",isCoordEnemy ("P",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("P", "NE", [X2,Y2], Board) ->  %io:fwrite(isCoordValid (X2,Y2,Board)),
								restrictMoveHelper ("P","NE",isCoordEnemy ("P",X2,Y2,Board), isCoordValid (X2,Y2,Board));
restrictMoves ("P", _, _, _) -> false;

% rook restrict
restrictMoves ("r",[_], _, _) -> true;
restrictMoves ("r",_, _, _) -> false;
restrictMoves ("R",[_], _, _) -> true;
restrictMoves ("R",_, _, _) -> false;

% bishop restrict
restrictMoves ("b",[_,_], _, _) -> true;
restrictMoves ("b",_, _, _) -> false;
restrictMoves ("B",[_,_], _, _) -> true;
restrictMoves ("B",_, _, _) -> false;

% knight restrict
restrictMoves ("n",[_,_,_], _, _) -> true;
restrictMoves ("n",_, _, _) -> false;
restrictMoves ("N",[_,_,_], _, _) -> true;
restrictMoves ("N",_, _, _) -> false;

% king restrict
restrictMoves ("k",[_,_,_], _, _) -> false;
restrictMoves ("k",_, _, _) -> true;
restrictMoves ("K",[_,_,_], _, _) -> false;
restrictMoves ("K",_, _, _) -> true;

% queen restrict
restrictMoves ("q",[_,_,_], _, _) -> false;
restrictMoves ("q",_, _, _) -> true;
restrictMoves ("Q",[_,_,_], _, _) -> false;
restrictMoves ("Q",_, _, _) -> true;

% all other wrong cases restrict. (will never be called)
restrictMoves (_,_, _,_) -> false.

% restrictMoveHelper: only needed for pawns, due to special case of diagonal move for killing							
restrictMoveHelper ("p","S",true,false) -> false;
restrictMoveHelper ("p","S",_,_) -> true;
restrictMoveHelper ("p","SE",true,false) -> true;
restrictMoveHelper ("p","SE",_,_) -> false;
restrictMoveHelper ("p","SW",true,false) -> true;
restrictMoveHelper ("p","SW",_,_) -> false;

restrictMoveHelper ("P","N",true,false) -> false;
restrictMoveHelper ("P","N",_,_) -> true;
restrictMoveHelper ("P","NE",true,false) -> true;
restrictMoveHelper ("P","NE",_,_) -> false;
restrictMoveHelper ("P","NW",true,false) -> true;
restrictMoveHelper ("P","NW",_,_) -> false.

% removePiece: given board, X and Y coordinates, it converts that position to empty "-"
removePiece (X,Y,Board) -> removePieceHelper(lists:split((findNum (X,Y,Board))-1,Board)).
removePieceHelper ({Ele1,[[_,X,Y] | Rest]}) -> Ele1++[["-",X,Y]]++Rest.

% replacePiece: replaces piece on X Y with piece on X2 Y2, and makes piece on X2 Y2 empty since it has just been moved
replacePiece (X2,Y2,[X,Y],Board) -> removePiece (X2,Y2, replacePieceHelper(X2,Y2,(lists:split((findNum (X,Y,Board))-1,Board)),Board  )).
replacePieceHelper(X2,Y2,{Ele1,[[_,X,Y] | Rest]},Board) -> Ele1++[[getPiece(X2,Y2,Board),X,Y]]++Rest. 

% getsCoords: finds all possible coordinates on the board from the initial coordinate given a direction 
% check for outside bounds area
getCoords (9,_,_) -> [];getCoords (10,_,_) -> [];getCoords (_,9,_) -> [];getCoords (_,10,_) -> [];
getCoords (0,_,_) -> [];getCoords (-1,_,_) -> [];getCoords (_,0,_) -> [];getCoords (_,-1,_) -> [];
getCoords (X,Y,"-") -> [[X,Y]];

% moves for North, North West, North East
getCoords (X,Y,"N") -> [[X,Y]] ++ getCoords (X,Y+1,"N");
getCoords (X,Y,"NW") -> [[X,Y]]++ getCoords (X-1,Y+1,"NW");
getCoords (X,Y,"NE") -> [[X,Y]]++ getCoords (X+1,Y+1,"NE");

% moves for South, South West, South East
getCoords (X,Y,"S") -> [[X,Y]] ++ getCoords (X,Y-1,"S");
getCoords (X,Y,"SW") -> [[X,Y]]++ getCoords (X-1,Y-1,"SW");
getCoords (X,Y,"SE") -> [[X,Y]]++ getCoords (X+1,Y-1,"SE");

% moves for West, East
getCoords (X,Y,"W") -> [[X,Y]] ++ getCoords (X-1,Y,"W");
getCoords (X,Y,"E") -> [[X,Y]] ++ getCoords (X+1,Y,"E");

% moves for North North East, North East East, North North West, North West West
getCoords (X,Y,"NNE") -> [[X,Y]] ++ (getCoords(X+1,Y+2,"-"));
getCoords (X,Y,"NEE") -> [[X,Y]] ++ (getCoords(X+2,Y+1,"-"));
getCoords (X,Y,"NNW") -> [[X,Y]] ++ (getCoords(X-1,Y+2,"-"));
getCoords (X,Y,"NWW") -> [[X,Y]] ++ (getCoords(X-2,Y+1,"-"));

% moves for South South East, South East East, South South West, South West West
getCoords (X,Y,"SSE") -> [[X,Y]] ++ (getCoords(X+1,Y-2,"-"));
getCoords (X,Y,"SEE") -> [[X,Y]] ++ (getCoords(X+2,Y-1,"-"));
getCoords (X,Y,"SSW") -> [[X,Y]] ++ (getCoords(X-1,Y-2,"-"));
getCoords (X,Y,"SWW") -> [[X,Y]] ++ (getCoords(X-2,Y-1,"-")).

% charToNum: converts given characters to cooresponding integers
charToNum($a)->1;charToNum($b)->2;charToNum($c)->3;charToNum($d)->4;
charToNum($e)->5;charToNum($f)->6;charToNum($g)->7;charToNum($h)->8;
charToNum($1)->1;charToNum($2)->2;charToNum($3)->3;charToNum($4)->4;
charToNum($5)->5;charToNum($6)->6;charToNum($7)->7;charToNum($8)->8.

% convertToCoord: converts given characters to [X,Y] coordinates. (ex: a1 = [1,1], e5 = [5,5], h2 = [8,2])
convertToCoord ([X,Y]) -> [charToNum(X),charToNum(Y)].

% toInt: converts string to integer (ex: "2" = 2, "5" = 5)
toInt(String) -> {Number,[]} = string:to_integer(String), Number.

% parser: parses given string array to get new board
parser (2,[X,Y],Board) -> move(convertToCoord(X),Y,10,Board);
parser (3,[X,Y,Z],Board) -> move(convertToCoord(X),Y,toInt(Z),Board);
parser (_,_,Board) -> Board.

% start: initial start to be called. Starts the chess game by calling start2 with board
start() -> start2 (initialBoard()).

% start2: starts game
start2 (Board) -> List = string:tokens( string:strip(io:get_line(""),right,$\n)," " ), 
				startHelper (head(List),List,Board).
				
% startHelper: decides weather to continue or stop				
startHelper("quit",_,Board)-> showBoard(Board,getKilled(Board));
startHelper(_,List,Board)-> start2 (parser(length(List),List,Board)).