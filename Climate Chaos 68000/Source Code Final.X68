*-----------------------------------------------------------
* Title      : Climate Chaos
* Written by : Shanke Kennedy C00263504
* Date       : March 2022
* Description: Climate Chaos is a resource management game based around the UN Goal 13 objective 
*              to take urgent action to combat climate change and its impacts
*-----------------------------------------------------------
*-------------------------------------------------------
* STARTING MEMORY ADDRESS FOR THE PROGRAMME $30000
*-------------------------------------------------------  
  ORG    $30000
*-------------------------------------------------------
*EQUATE VALUES FOR PROGRAM
*-------------------------------------------------------

;Compare Values for keys
ENTERKEY    EQU 13        ;ASCII value for the enter key

;Risk Addresses for the risk values for Iniative Events
LOWRISK     EQU $3600
MEDRISK     EQU $3610
HIGHRISK    EQU $3620

EVENTCASH   EQU $3630   ;Location to store cash amount for tempatation event

;Addresses for variables
TREEABSORB  EQU $3000      ;Locaion for amount of carbon tree removes
TREENUM     EQU $3020      ;Location of number of trees
CASHADD     EQU $2020      ;Location of cash amount
GPOWERADD   EQU $3030      ;Location of green energy location
CPOWERADD   EQU $3040      ;Location of carbon energy location
TOTALPOW    EQU $3050      ;Total power location
CARBONADD   EQU $2010      ;Carbon in ppm
POWERLOSE   EQU $3050      ;Game ending amount of power
COINCREASE  EQU $2030      ;Amount co2 is increasing per game cycle
CARBONWIN   EQU $2050   ;Address of value of carbon amount at which player wins
CARBONLOSE  EQU $2040   ;Address of value of carbon amount at which player loses
CASHWIN    EQU $2060   ;Address of value of cash amount at which player loses
CASHLOSE
PCASHADD    EQU $2900   ;Address for personal cash of player

COMILE1     EQU $2070   ;Addresses for the milestone amounts for Good Event 1 to trigger
COMILE1OCC  EQU $20B0   ;Address for variable to check if event has occured (event flag)
COMILE2     EQU $2080   ;Addresses for the milestone amounts for Good Event 2 to trigger
COMILE2OCC  EQU $20C0   ;Address for variable to check if event has occured (event flag)
COMILE3     EQU $2090   ;Addresses for the milestone amounts for Good Event 3 to trigger
COMILE3OCC  EQU $20D0   ;Address for variable to check if event has occured (event flag)
COMILE4     EQU $20A0   ;Addresses for the milestone amounts for Good Event 4 to trigger
COMILE4OCC  EQU $20E0   ;Address for variable to check if event has occured (event flag)

;Values
TIP         EQU  475     ;Tipping point value in ppm, game end condition
CPERGW      EQU  5       ;CO2 produced per GW of non-green power
PPMFACTOR   EQU  15000   ;Factor that establishes how much ppm increases per CO2 being produced
GLOERG      EQU  11000   ;Global energy needs    (Bad event roll when below this value)
GLOERGC     EQU  8000    ;Critical energy level  (Game Lose roll when below this value)
COLUMNS     EQU  20480   ;Decimal value equates to hex 5000 or 80 columns when 4f is stored as higher order byte for printing earth loop
ROWS        EQU  30      ;Value of number of rows to be looped through with printing earth function
ZERO        EQU  48      ;ASCII character value for 0
EIGHT       EQU  56      ;ASCII character value for 8

PRICEA      EQU 100000   ;Prices set for low risk options
PRICEB      EQU 50000    ;Prices set for medium risk options
PRICEC      EQU 25000    ;Prices set for low risk options

;Flag addresses for hedge betting
RSK         EQU  $6530   ;To store the risk needed for the dice rolls
BETFLAG     EQU  $6540  ;Bet flag for if bet  has been placed
BETWLFLAG   EQU  $6560  ;Bet flag for if bet has been won/lost

;Maze game start/end positions addresses
MAZEST1     EQU $7500
MAZEEN1     EQU $7502
MAZEST2     EQU $7504
MAZEEN2     EQU $7506
MAZEST3     EQU $7508
MAZEEN3     EQU $750A
MAZEST4     EQU $750C
MAZEEN4     EQU $750E
MAZEST5     EQU $7510
MAZEEN5     EQU $7512

START:                      

*-------------------------------------------------------
* SETTING STARTING VALUES FOR GAME
*-------------------------------------------------------   
    ;Set up initial variables   
    ;Global Variables
    MOVE.L #415, CARBONADD      ;Carbon in atmosphere
    MOVE.L #100000, CASHADD       ;Amount of cash at start of game
    
    ;Power values
    MOVE.L #10000, CPOWERADD        ;Value of Carbon Power at game start
    MOVE.L #2000, GPOWERADD         ;Value of Green Power at game start
    
    ;Tree variables
    MOVE.L #5, TREEABSORB        ;Amount one million trees reduces amount
    MOVE.L #0, TREENUM              ;Number of trees
    
    ;Calculate CO2 increase at start (Non green energy in GWS x CPERGW)
    MOVE.L CPOWERADD, D2
    MULU #CPERGW,D2   
    MOVE.L D2, COINCREASE 
    
    ;Victory and lose variables
    MOVE.L #475, CARBONLOSE    
    MOVE.L #0, CARBONWIN       ;Carbon value that is game over and player wins in tonnes
    MOVE.L #0,   CASHLOSE       ;Cash at which game over and player loses
    MOVE.L #500000, CASHWIN      ;Cash at which game over and player wins
    MOVE.L #25000, PCASHADD      ;Address for personal cash
    
    ;Set risk levels for dice rolls
    MOVE.L #2, LOWRISK
    MOVE.L #4, MEDRISK
    MOVE.L #5, HIGHRISK
    ;Milestone for good events chance
    MOVE.L #40000, COMILE1          ;Set value for milestone of C02 Increase at which good event 1 can trigger
    MOVE.L #30000, COMILE2          ;Set value for milestone of C02 Increase at which good event 2 can trigger
    MOVE.L #20000, COMILE3          ;Set value for milestone of C02 Increase at which good event 3 can trigger
    MOVE.L #10000, COMILE4          ;Set value for milestone of C02 Increase at which good event 4 can trigger
    MOVE.B #0, COMILE1OCC           ;Event flag value for Good Event 1 , Set to 0 for has not occured, 1 for occured 
    MOVE.B #0, COMILE2OCC           ;Event flag value for Good Event 2 , Set to 0 for has not occured, 1 for occured 
    MOVE.B #0, COMILE3OCC           ;Event flag value for Good Event 3 , Set to 0 for has not occured, 1 for occured 
    MOVE.B #0, COMILE4OCC           ;Event flag value for Good Event 4 , Set to 0 for has not occured, 1 for occured 

    ;Set risk and betflag and reducted odds flag for hedge betting and dice rolls to 0
    MOVE.L #0, RSK                  ;For storing risk or win/lose value for dice rolls, set to 0 at start      
    MOVE.L #0, BETFLAG              ;Event flag to indicate if a bet has been placed 1 for bet placed, 0 for no bet, set to 0 at start      
 
    ;Set eventcash to 0
    MOVE.L #0, EVENTCASH            ;Set event cash for tempatation event to 0 at start
    
    ;Color codes for printing earth subroutine
    MOVE.L #$00FF0000, GREEN
    MOVE.L #$0000FF00, BLUE
    MOVE.L #$0000FFFF, YELLOW
    MOVE.L #$00CC3299, PURPLE
    MOVE.L #$000000FF, RED
    MOVE.L #$0000A5FF, ORANGE
    MOVE.L #$00FFFF00, LBLUE
  
    ;Maze mini game, start positions(column num stored in high byte, rows stored in low byte)
    MOVE.W #$321A, MAZEST1
    MOVE.W #$191A, MAZEEN1
    MOVE.W #$191A, MAZEST2
    MOVE.W #$460A, MAZEEN2
    MOVE.W #$451A, MAZEST3
    MOVE.W #$191A, MAZEEN3
    MOVE.W #$281D, MAZEST4
    MOVE.W #$321A, MAZEEN4
    MOVE.W #$1806, MAZEST5
    MOVE.W #$321A, MAZEEN5
    MOVE.L #0,COUNTER    ;Set counter for games played to 0 (once reachs five, will reset to 0)
    
*-------------------------------------------------------
*START OF GAME CODE
*-------------------------------------------------------
    
    BSR ECHOOFF               ;Branch to echooff subroutine
    BSR TITLE                 ;Prints title and starts game
    BSR PREAMBLETEXT          ;Print preamble text subroutine

MAIN:
    BSR CLEARSCREEN           ;Clears screen
    BSR MAINHUD                   ;Prints Hud
    BSR OPTION1               ;Prints first player main player choice menu
    BRA MENUCHOICEA

*-------------------------------------------------------
*MENU CHOICE INVESTMENT/MISSION
*-------------------------------------------------------
MENUCHOICEA:                  ;Reads input for player choice between buying investment options or going on a cash raising mission
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ INVESTMENUCHOICE
    CMP #2, D5
    BEQ MISSION
    CMP #101, D5
    BEQ ROOM101               ;Room 101 cheat/debugging room
    BSR INVALIDINPUT
    BRA MENUCHOICEA           ;if 1 or 2 not presses loop back and get user input again    
INVESTMENUCHOICE:             ;Menu choice between type of investment
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR             ;Move cursor back to start  
    BSR FONTGOLD
    LEA OPTIONSB, A1          ;Print text in gold
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA OPTIONSBA, A1          ;Print text in white
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5           ;Retrieve read in number from memory, address set in NUMINPUT subroutine
    CMP #1, D5
    BEQ TREEOPTS               ;Branch for Treeplanting options
    CMP #2, D5
    BEQ ENERGYOPTS             ;Branch for Green Energy investment options
    CMP #3, D5 
    BEQ CUTOPTS               ;Branch for Carbon cutting options
    CMP #4, D5
    BEQ MAIN
    BSR INVALIDINPUT
    BRA INVESTMENUCHOICE      ;Branch back to top if no valid option selected
*-------------------------------------------------------
*MENU CHOICE TREEPLANTING INIATIVES AND RESULTS
*-------------------------------------------------------
TREEOPTS:                 ;Main Tree Options menu
    BSR CLEARSCREEN       ;Format and print main tree planting options
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTGREEN
    LEA TREEOPTIONS, A1
    BSR PRINTSTRING 
    BSR FONTWHITE
    LEA TREEOPTIONSB, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA TREEOPTIONSB1, A1          ;Print low risk option in green
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA TREEOPTIONSB2, A1          ;Print med risk option in gold
    BSR PRINTSTRING
    BSR FONTRED
    LEA TREEOPTIONSB3, A1          ;Print risk risk option in red
    BSR PRINTSTRING
    BSR FONTWHITE  
    LEA TREEOPTIONSB4, A1          ;Print back to menu white
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ TREEOPT1
    CMP #2, D5
    BEQ TREEOPT2
    CMP #3, D5
    BEQ TREEOPT3
    CMP #4, D5         ;Option 4 back to menu
    BRA MAIN
    BSR INVALIDINPUT
    BRA INVESTMENUCHOICE

TREEOPT1:                 ;Tree options 1 menu
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEA, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA LRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #1, D1       ;Set risk to low value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L LOWRISK, D1
    CMP D1, D4    ;Compare low risk vs D4(dice roll outcome stored here)
    BGE TREEOPT1SUC     ;Branch to successful outcome
    BRA TREEOPT1FAIL    ;Else branch to fail outcome 

TREEOPT1SUC:             ;Tree options 1 success event
    ;Add the 1000 thousand trees
    MOVE.L $3020, D6
    ADD #1000, D6
    MOVE.L D6, $3020
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEA, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    LEA FOREST, A1      ;Print the forest art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA TREEOPTA, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to add cash
    BRA UPDATE          ;Update loop
    
TREEOPT1FAIL:          ;Tree options 1 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEA, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING
    BSR FONTBROWN
    LEA FOREST, A1       ;Print volcano art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA TREEOPTAN, A1    ;Load address of failure text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINLO         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
       
TREEOPT2:               ;Tree options 2 menu
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEB, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA MRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #2, D1       ;Set risk to medium value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L MEDRISK, D1
    CMP D1, D4    ;Compare medium risk vs D4
    BGE TREEOPT2SUC     ;Branch to successful outcome
    BRA TREEOPT2FAIL    ;Else branch to fail outcome  
    
TREEOPT2SUC:            ;Tree options 2 success event
    ;Add the 1000 thousand trees
    MOVE.L $3020, D6
    ADD #1000, D6
    MOVE.L D6, $3020
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEB, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    LEA VOLCANOS, A1      ;Print the forest art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA TREEOPTB, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
TREEOPT2FAIL:          ;Tree options 2 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEB, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    LEA VOLCANO, A1      ;Print the forest art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA TREEOPTBN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINMID         ;If it is Branch to Betlose to add cash
    BRA UPDATE          ;Update loop
    
TREEOPT3:
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEC, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA HRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #3, D1       ;Set risk to high value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L HIGHRISK, D1
    CMP D1, D4    ;Compare high risk vs D4
    BGE TREEOPT3SUC     ;Branch to successful outcome 
    BRA TREEOPT3FAIL    ;Else branch to fail outcome  
    
TREEOPT3SUC:            ;Tree options 3 success event
    ;Add the 1000 thousand trees
    MOVE.L $3020, D6
    ADD #1000, D6
    MOVE.L D6, $3020
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    LEA FORESTG, A1      ;Print the forest art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA TREEOPTC, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
TREEOPT3FAIL:           ;Tree options 3 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    BSR FONTGOLD
    LEA WHISKEY, A1      ;Print the whiskey art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA TREEOPTCN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINHI         ;If it is Branch to Betlose to add cash
    BRA UPDATE          ;Update loop

*-------------------------------------------------------
*MENU CHOICE ENERGY INIATIVES
*-------------------------------------------------------
ENERGYOPTS:               ;Energy options menu
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start  
    BSR FONTLBLUE
    LEA ENERGYOPTION, A1
    BSR PRINTSTRING 
    BSR FONTWHITE
    LEA ENERGYOPTIONB, A1
    BSR PRINTSTRING 
    BSR FONTGREEN
    LEA ENERGYOPTION1, A1          ;Print low risk option in green
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA ENERGYOPTION2, A1          ;Print med risk option in gold
    BSR PRINTSTRING
    BSR FONTRED
    LEA ENERGYOPTION3, A1          ;Print high risk option in red
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA ENERGYOPTION4, A1          ;Print unknown risk option in purple
    BSR PRINTSTRING
    BSR FONTWHITE  
    LEA ENERGYOPTION5, A1          ;Print back to menu white
    BSR PRINTSTRING              
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ ENERGYOPT1
    CMP #2, D5
    BEQ ENERGYOPT2
    CMP #3, D5
    BEQ ENERGYOPT3
    CMP #4, D5
    BEQ ENERGYOPT4
    CMP #5, D5         ;Option 5 back to menu
    BRA MAIN
    BSR INVALIDINPUT
    BRA INVESTMENUCHOICE
    
ENERGYOPT1:               ;Energy options 1 menu
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEA, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA LRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #1, D1       ;Set risk to low value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L LOWRISK, D1
    CMP D1, D4    ;Compare low risk vs D4
    BGE ENERGYOPT1SUC     ;Branch to successful outcome
    BRA ENERGYOPT1FAIL    ;Else branch to fail outcome 

ENERGYOPT1SUC:            ;Energy options 1 success event
    ;Add the 500 green power
    MOVE.L GPOWERADD, D6
    ADD #1000, D6
    MOVE.L D6, GPOWERADD
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEA, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    BSR FONTYELLOW
    LEA SUN, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE
    LEA ENERGYOPTA, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
ENERGYOPT1FAIL:           ;Energy options 1 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEA, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    BSR FONTLBLUE
    LEA SUN, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA ENERGYOPTAN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE         ;Print newline
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINLO         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop

ENERGYOPT2:               ;Energy options 2 menu
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEB, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA MRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #2, D1       ;Set risk to med value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L MEDRISK, D1
    CMP D1, D4    ;Compare medium risk vs D4
    BGE ENERGYOPT2SUC     ;Branch to successful outcome
    BRA ENERGYOPT2FAIL    ;Else branch to fail outcome 
    
ENERGYOPT2SUC:             ;Energy options 2 success event
    ;Add the 1000 green power
    MOVE.L GPOWERADD, D6
    ADD #1000, D6
    MOVE.L D6, GPOWERADD
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEB, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1     ;Print success banner
    BSR PRINTSTRING 
    BSR FONTGOLD
    LEA WMILL, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA ENERGYOPTB, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE         ;Print newline
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
ENERGYOPT2FAIL:         ;Energy options 2 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEB, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    BSR FONTLBLUE
    LEA STORM, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA ENERGYOPTBN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINMID        ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop

ENERGYOPT3:             ;Energy options 3 menu
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEC, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA HRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #3, D1       ;Set risk to high value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L HIGHRISK, D1
    CMP D1, D4    ;Compare high risk vs D4
    BGE ENERGYOPT3SUC     ;Branch to successful outcome
    BRA ENERGYOPT3FAIL    ;Else branch to fail outcome 
    
ENERGYOPT3SUC:            ;Energy options 3 success event
    ;Add the 500 green power
    MOVE.L GPOWERADD, D6
    ADD #1000, D6
    MOVE.L D6, GPOWERADD
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    BSR FONTLBLUE
    LEA NUCPLANT, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA ENERGYOPTC, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
ENERGYOPT3FAIL:         ;Energy options 3 fail event
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    SUB.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    LEA NUCPLANT, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA ENERGYOPTCN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE         ;Print newline
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINHI         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
ENERGYOPT4:             ;Energy options 4 event
    MOVE.L CASHADD, D6    ;Move cash to D6
    MOVE.L #PRICEB, D7
    CMP.L D7, D6     ;Check if have sufficient cash
    BLT INFUNDS
    ;Subtract Cash
    MOVE.L CASHADD, D6
    MOVE.L #50000, D7
    SUB.L D7,D6  
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTPURPLE
    LEA QUESTEXT, A1
    BSR PRINTSTRING 
    BSR FONTGREEN
    LEA ATOM, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA ENERGYOPTD, A1
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE         ;Print newline
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    BRA UPDATE          ;Update loop

*-------------------------------------------------------
*MENU CHOICE TREEPLANTING INIATIVES
*-------------------------------------------------------
CUTOPTS:                ;Cutting options main menu
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING 
    BSR FONTWHITE
    LEA CUTOPTIONSB, A1
    BSR PRINTSTRING 
    BSR FONTGREEN
    LEA CUTOPTIONS1, A1          ;Print low risk option in green
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA CUTOPTIONS2, A1          ;Print med risk option in gold
    BSR PRINTSTRING
    BSR FONTRED
    LEA CUTOPTIONS3, A1          ;Print high risk option in red
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTIONS4, A1          ;Print high risk option in red
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT1
    CMP #2, D5
    BEQ CUTOPT2
    CMP #3, D5
    BEQ CUTOPT3
    CMP #4, D5          ;Option 4 back to menu
    BEQ MAIN
    BSR INVALIDINPUT
    BRA INVESTMENUCHOICE

CUTOPT1:                         ;Cutting option 1 menu
    ;Check if doing this will drop power levels below world needs, warn user and ask for confirmation if true
    MOVE.L CPOWERADD, D2
    MOVE.L GPOWERADD, D3
    ADD.L D2,D3
    SUB.L #1000, D3
    CMP.L #GLOERGC, D3   ;Check critcal power condition
    BLT CUTOPT1CRIT  
    CMP.L #GLOERG, D3    ;Check subcritical power condition
    BLT CUTOPT1CON         ;If cutting will put global power below world needs branch to CUTOPT1CON to ask user to confirm
CUTOPT1RET:                ;Returns here if user selects yes
    MOVE.L CPOWERADD, D6    ;Move carbon to D6
    MOVE.L #1000, D7
    CMP.L D7, D6     ;Check if have sufficient carbon energy production
    BLT INCPOWER
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA LRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #1, D1       ;Set risk to low value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L LOWRISK, D1
    CMP D1, D4    ;Compare low risk vs D4
    BGE CUTOPT1SUC     ;Branch to successful outcome
    BRA CUTOPT1FAIL    ;Else branch to fail outcome 

CUTOPT1CON:            ;Ask for user confirmation
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQ, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT1RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT1CON      ;If invalid input ask user again
    
CUTOPT1CRIT:            ;Ask for user confirmation
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA LIGHT , A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQC, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT1RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT1CRIT      ;If invalid input ask user again
    
CUTOPT1SUC:              ;Cutting options 1 success event
    ;Subtract 500 carbon power
    MOVE.L CPOWERADD, D6
    SUB.L #1000, D6
    MOVE.L D6, CPOWERADD
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    BSR FONTGREEN
    LEA ARROW, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTA, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
CUTOPT1FAIL:            ;Cutting options 1 fail event
    ;Add 250 carbon power
    MOVE.L CPOWERADD, D6
    ADD.L #250, D6
    MOVE.L D6, CPOWERADD
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR FONTYELLOW
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    BSR FONTGOLD
    LEA EURO, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA CUTOPTAN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINLO         ;If it is Branch to Betlose to add cash
    BRA UPDATE          ;Update loop

CUTOPT2:
    ;Check if doing this will drop power levels below world needs, warn user and ask for confirmation if true
    MOVE.L CPOWERADD, D2
    MOVE.L GPOWERADD, D3
    ADD.L D2,D3
    SUB.L #1500, D3
    CMP.L #GLOERGC, D3   ;Check critcal power condition
    BLT CUTOPT2CRIT  
    CMP.L #GLOERG, D3    ;Check subcritical power condition
    BLT CUTOPT2CON         ;If cutting will put global power below world needs branch to CUTOPT1CON to ask user to confirm

CUTOPT2RET:
    MOVE.L CPOWERADD, D6    ;Move cash to D6
    MOVE.L #1500, D7
    CMP.L D7, D6     ;Check if have sufficient cpower
    BLT INCPOWER
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA MRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #2, D1       ;Set risk to medium value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L MEDRISK, D1
    CMP D1, D4    ;Compare medium risk vs D4
    BGE CUTOPT2SUC     ;Branch to successful outcome
    BRA CUTOPT2FAIL    ;Else branch to fail outcome 
CUTOPT2CON:
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQ, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT2RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT2CON
CUTOPT2CRIT:            ;Ask for user confirmation
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA LIGHT , A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQC, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT2RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT2CRIT      ;If invalid input ask user again
CUTOPT2SUC:                   ;Cutting options 2 success event
    ;Subtract the 1000 carbon power
    MOVE.L CPOWERADD, D6
    SUB.L #1500, D6
    MOVE.L D6, CPOWERADD
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #50000, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    BSR FONTLBLUE
    LEA OILRIGB, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTB, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
CUTOPT2FAIL:            ;Cutting options 2 fail event
    ;Subtract Cash
    MOVE.L CPOWERADD, D6
    MOVE.L #250, D7
    ADD.L D7,D6  
    MOVE.L D6, CPOWERADD
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #PRICEC, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    BSR FONTLBLUE
    LEA OILRIGA, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA CUTOPTBN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINMID         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop


CUTOPT3:
    ;Check if doing this will drop power levels below world needs, warn user and ask for confirmation if true
    MOVE.L CPOWERADD, D2
    MOVE.L GPOWERADD, D3
    ADD.L D2,D3
    SUB.L #2000, D3
    CMP.L #GLOERG, D3
    BLT CUTOPT3CON CMP.L #GLOERGC, D3   ;Check critcal power condition
    BLT CUTOPT3CRIT  
    CMP.L #GLOERG, D3    ;Check subcritical power condition
    BLT CUTOPT3CON         ;If cutting will put global power below world needs branch to CUTOPT1CON to ask user to confirm
CUTOPT3RET:
    MOVE.L CPOWERADD, D6    ;Move cash to D6
    MOVE.L #2000, D7
    CMP.L D7, D6     ;Check if have sufficient cpower
    BLT INCPOWER
    BSR CLEARSCREEN
    BSR FONTGOLD
    BSR PRINTBORDER
    MOVE.L #0,D1
    MOVE.B #11, D0
    TRAP #15
    LEA RISK, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA SHAM, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA HRISK, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #0,D1        ;Clear D1
    MOVE.L RSK, D1      ;Move value of risk to D1
    MOVE.L #3, D1       ;Set risk to high value for comparison with hedge betting
    MOVE.L D1, RSK
    BSR DICECHOICE
    MOVE.L HIGHRISK, D1
    CMP D1, D4    ;Compare medium risk vs D4
    BGE CUTOPT3SUC     ;Branch to successful outcome
    BRA CUTOPT3FAIL    ;Else branch to fail outcome 
CUTOPT3CON:
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQ, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT3RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT3CON
CUTOPT3CRIT:            ;Ask for user confirmation
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA CUTOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA LIGHT , A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTQC, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CUTOPT3RET
    CMP #2, D5
    BEQ CUTOPTS
    BSR INVALIDINPUT
    BRA CUTOPT3CRIT      ;If invalid input ask user again
CUTOPT3SUC:               ;Cutting options 3 success event
    ;Sub the 2000 carbon power
    MOVE.L CPOWERADD, D6
    SUB.L #2000, D6
    MOVE.L D6, CPOWERADD
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #100000, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA SUCTEXT, A1
    BSR PRINTSTRING 
    BSR FONTYELLOW
    LEA JUDGE, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA CUTOPTC, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETLOSE         ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
    
CUTOPT3FAIL:            ;Cutting options 3 fail event
    ;Add the 2000 carbon power
    MOVE.L CPOWERADD, D6
    ADD.L #500, D6
    MOVE.L D6, CPOWERADD
    BSR CLEARSCREEN     ;Clear screen
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA FAILTEXT, A1
    BSR PRINTSTRING 
    LEA GAVEL, A1      ;Print the sun art
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE         ;Print newline
    LEA CUTOPTCN, A1    ;Load address of success text
    BSR PRINTSTRING     ;Print success text
    BSR NEWLINE
    BSR ENTERTOCONTINUE ;Ask user to press enter before moving on
    MOVE.L #0,D1        ;Clear D1
    MOVE.L BETFLAG,D1   ;Check if betflag is set
    CMP #1,D1     
    BEQ BETWINHI        ;If it is Branch to Betlose to subtract cash
    BRA UPDATE          ;Update loop
   
*-------------------------------------------------------
*INVALID INPUT OR INSUFFICIENT RESOURCES VALIDATION
*-------------------------------------------------------   
INVALIDINPUT:
    BSR GETCURSOR     ;Store cursor before print
    LEA INVALIDCLR, A1   ;First clear in case it was already printed
    BSR PRINTSTRING  
    BSR SETCURSOR    ;Set cursor back to start
    BSR GETCURSOR
    LEA INVALID, A1 
    BSR PRINTSTRING
    SUB.L #1, D1
    BSR SETCURSOR    ;Set cursor back to start
    RTS
;Method skips back to main if player has insufficient funds
INFUNDS:
    LEA NOFUNDS, A1
    BSR PRINTSTRING
    BSR ENTERTOCONTINUE
    BRA MAIN
;Method to skip back to main if insufficient carbon power remains
INCPOWER:
    LEA NOCPOWER, A1
    BSR PRINTSTRING
    BSR ENTERTOCONTINUE
    BRA MAIN
;Method to skip back to bet page if insufficient personal cash remains
INPFUND:
    LEA NOPFUNDS, A1
    BSR PRINTSTRING
    BSR ENTERTOCONTINUE
    BRA DICECHOICE

*-------------------------------------------------------
;INPUT SUBROUTINES
*-------------------------------------------------------   
CHARINPUT:             ;reads a single input character and stores at $2000
    MOVE.B #5, D0       
    MOVE.L #0, D1      ;Make sure D1 is totally clear
    TRAP #15
    MOVE.B D1, $2000   
    RTS
    
NUMINPUT:             ;reads a single input number and stores at $1900
    MOVE.B #4, D0
    MOVE.L #0, D1     ;Make sure D1 is totally clear
    TRAP #15
    MOVE.B D1, $1900
    RTS
*-------------------------------------------------------
;TITLE SCREEN SUBROUTINE
*-------------------------------------------------------   
TITLE:                    ;print title screen
    LEA BORDER, A1          ;print border
    MOVE.B #14, D0
    TRAP #15
    MOVE.L #$0004, D1
    BSR SETCURSOR
    BSR FONTGREEN          ;Set font green
    LEA STARTCREDITS, A1  
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR WAIT              ;wait for 2 seconds
    LEA PRESSSTART, A1       ;Prints press start to enter
    BSR PRINTSTRING
    BSR FONTWHITE
TITLELOOP:                ;loops until enter key presses
    BSR CHARINPUT
    CMP #ENTERKEY, D1
    BNE TITLELOOP
    BSR NEWLINE          ;Print new line
    RTS
*-------------------------------------------------------
;Start of game story text and cutscene in future maybe
*------------------------------------------------------- 
PREAMBLETEXT:
    BSR CLEARSCREEN      ;Clearscreen
    LEA GREEN, A3        ;Set font of first color for printearth subroutine
    LEA BLUE, A4        ;Set font of second color for printearth subroutine
    LEA EARTHC, A6      ;Put reference to the correct earth pick in A6
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    LEA PALEBLUEDOT, A1  ;Print text under art
    BSR PRINTSTRING
    BSR ENTERTOCONTINUE
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start            
    BSR FONTGOLD
    LEA MAIL,A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA STYTEXT,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start      
    BSR FONTGREEN
    LEA BUILDING, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA STYTEXT2, A1
    BSR PRINTSTRING  
    BSR NEWLINE      
    BSR ENTERTOCONTINUE  ;Enter to continue
    BRA MAIN

*-------------------------------------------------------
;Missions/Minigames
*------------------------------------------------------- 
MISSION:
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA MISOPTIONS, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA MISOPTIONS1, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ MAZEGAME         ;Maze Game
    CMP #2, D5           
    BEQ MAIN             ;Back to maain
    BSR INVALIDINPUT
    BRA MISSION
*-------------------------------------------------------
;PRIMARY SET OF ACTIONS
*------------------------------------------------------- 
OPTION1:
    ;Print desk and player actions
    BSR PRINTDESK
    LEA OPTIONSA, A1
    BSR PRINTSTRING
    RTS  

OPTION2:
    ;Print player actions
    LEA OPTIONSB, A1
    BSR PRINTSTRING
    RTS 
*-------------------------------------------------------
;GENERAL SUBROUTINES
*------------------------------------------------------- 
;Method to print newline
NEWLINE:
    LEA NLINE, A1
    BSR PRINTSTRING
    RTS  
;Method to print a tab
PRINTTAB:
    LEA TAB, A1
    BSR PRINTSTRING
    RTS 
;Method to print a backspace
PRINTBACK:
    LEA BACKSPACE, A1
    BSR PRINTSTRING
    RTS 
;Method to print String Trap Code 14 Subroutine
PRINTSTRING:
    MOVE.B #14, D0
    TRAP #15
    RTS
;Method to print String and Num Trap Code 17 Subroutine
PRINTSTRINGNUM:
    MOVE.B #17, D0
    TRAP #15
    RTS 
;Method to print number Trap Code 3 Subroutine
PRINTNUM:
    MOVE.B #3, D0
    TRAP #15
    RTS 
;Method to print char Trap Code 6 Subroutine
PRINTCHAR
    MOVE.B #6, D0
    TRAP #15
    RTS 
;Method to switch to font blue    
FONTBLUE:
    MOVE.L #$00FF0000, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font green
FONTGREEN:
    MOVE.L #$0000FF00, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font#99FF00
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font light green
FONTLGREEN:
    MOVE.L #$0000FF99, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font brown
FONTBROWN:
    MOVE.L #$0013458B, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font brown
FONTLBLUE:
    MOVE.L #$00FFFF00, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing fontE7D8CD
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font red
FONTRED:
    MOVE.L #$000000FF, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font gold
FONTGOLD:
    MOVE.L #$0000DFFF, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font yellow
FONTYELLOW:
    MOVE.L #$0000FFFF, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font purple
FONTPURPLE:
    MOVE.L #$00CC3299, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font white
FONTWHITE: 
    MOVE.L #$00FFFFFF, D1
    MOVE.L #0 ,D2       ;Clear d2 to avoid changing font
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch to font underlined
FONTUNDERLINE: 
    MOVE.L #4 ,D2       ;0100 in binary
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to switch font color stored in A3
FONTSETA:
    MOVE.L (A3),D1
    MOVE.B #21, D0
    TRAP #15
    RTS
FONTSETB:
    MOVE.L (A4),D1
    MOVE.B #21, D0
    TRAP #15
    RTS
;Method to return cursor position to D1
GETCURSOR:
    MOVE.L #$000000FF,D1
    MOVE.B #11, D0
    TRAP #15
    RTS
;Method to set cursor to position stored in D1
SETCURSOR:
    MOVE.B #11, D0
    TRAP #15
    RTS
;Method to read character at location stored in d1, i'm storing as given in trap 11, column in higher byte, row in lower byte and converting
READCHAR:
    MOVE.B #22, D0
    BSR SWAPBYTES    ;Need to convert cursor position to row in 16 bit higher order bit and column in 16 bit lower order bit, why did they design it like this!!!
    TRAP #15
    RTS
SWAPBYTES:  ;Swaps the higher and lower order words of value in d1, also uses d2 so keep clear
    MOVE.B D1,D2        ;Move low order byte to D2
    LSL.L #$08, D2      ;Need to swap row to 16 bit higher byte and column 16 bit lower byte for Trap 22, so left shift low byte 8 bits twice
    LSL.L #$08, D2
    LSR.W #$08, D1       ;Move D3 value 8 bits to right
    MOVE.B D1, D2       ;Finally add back lower order byte
    MOVE.L D2,D1        ;Move final value back to D3 for end of subroutine
    RTS 
;Method to print a border
PRINTBORDER:
    LEA BORDER, A1
    BSR PRINTSTRING 
    RTS 

WAIT:                    ;Waits the amount of time specified in D1 in 1/100ths of seconds
    MOVE.L #0,D1    ;Clear D1
    MOVE.W   #200,D1     
    MOVE.B   #23,D0
    TRAP     #15
    RTS

;Method to set a custom wait period
CWAIT:
    MOVE.B   #23,D0
    TRAP     #15
    RTS   
ECHOOFF:                ;Turns keyboard echo off
    CLR D1
    MOVE.B #12, D0
    MOVE.B #0, D1
    TRAP #15
    RTS
    
CLEARSCREEN:           ;Clear screen function
    MOVE.B  #11,D0      
    MOVE.W  #$FF00,D1
    TRAP    #15
    RTS

PRINTDESK:  ;Subroutine to print the desk
    BSR NEWLINE
    LEA DESK,A1
    BSR PRINTSTRING
    BSR NEWLINE 
    RTS 
*-------------------------------------------------------
;SUBROUTINE TO PRINT IMAGE IN DIFFERENT COLOURS (Image at A6, Colour 1 at A4, Colour 2 at A3)
*-------------------------------------------------------  
;Print and colour a picture
PRINTEARTH:             ;Method which loops through every row,column location on screen, if character is 0 reprints it blue, if character 8 reprints it green
    BSR CLEARSCREEN
    LEA BORDER ,A1         ;Print border
    MOVE.B #14, D0
    TRAP #15
    MOVE.L #0, D1       ;Set cursor back to start
    BSR SETCURSOR
    LEA (A6),A1      ;Print the string currently in mem location stored in A6
    BSR PRINTSTRING
EARTHPL:
    MOVE.L #0,D1        ;Set D1 to 0 to set cursor back to beginning of screen
    MOVE.L #0, D6       ;Set d6 to count columns
    MOVE.L #0, D7       ;Set d7 to count rows
LEARTH:
    BSR SETCURSOR
    BSR READCHAR
    ADD.W #$100, D6      ;Increment columns
    CMP #ZERO, D1       ;Compare zero char to d1, if equal set cursor blue and print char in D1
    BEQ ZEROCHAR
    CMP #EIGHT, D1      ;Compare eight char to d1, if equal set cursor blue
    BEQ EIGHTCHAR
    CMP #COLUMNS, D6    ;CMP column to high byte of D6
    BEQ RESETCOLS       
    MOVE.L #0, D1       ;Clear D1
    MOVE.W D6, D1       ;Move higher order byte, d1
    MOVE.B D7, D1       ;Move lower order byte, D1
    BRA LEARTH          ;Branch back to loop
    
ZEROCHAR:               ;Method for coloring the letters of the earth ascii art prob ineffiecient?Must be an easier way
    MOVE.B D1, D3     ;Save value of input character in D3 NOT D2, font problems!!!
    MOVE.L #0, D2     ;Clear d2 to avoid problems with font changing
    BSR FONTSETA       ;First font
    MOVE.B D3, D1     ;Now return for printing
    BSR PRINTCHAR
    BSR FONTWHITE       ;Set font back to white 
    MOVE.L #0, D1       ;Clear D1
    MOVE.W D6, D1       ;Move higher order byte, d1
    MOVE.B D7, D1       ;Move lower order byte, D1
    BRA LEARTH 
EIGHTCHAR:            ;Method for coloring the letters of the earth ascii art
    MOVE.B D1, D3
    MOVE.L #0, D2     ;Clear d2 to avoid problems with font changing
    BSR FONTSETB       ;Second font
    MOVE.B D3, D1
    BSR PRINTCHAR
    BSR FONTWHITE       ;Set font back to white 
    MOVE.L #0, D1       ;Clear D1
    MOVE.W D6, D1       ;Move higher order byte, d1
    MOVE.B D7, D1       ;Move lower order byte, D1
    BRA LEARTH   
RESETCOLS:              ;Method to reset columns to 0 when 79 is reached to loop through screen
    MOVE.L #0, D6
    CMP #ROWS, D7   
    BEQ ENDROW          ;End subroutine
    ADD.B #1, D7      ;Increment rows
    MOVE.L #0, D1       ;Clear D1
    MOVE.W D6, D1       ;Move higher order byte, d1
    MOVE.B D7, D1       ;Move lower order byte, D1
    BRA LEARTH 
ENDROW:
    RTS
*-------------------------------------------------------
;RANDOM NUMBER DICE ROLLING SUBROUTINES
*------------------------------------------------------- 
;RANDOM_NUMBER taken from ASM notes
;Method to generate random now between 1 and 100 and store it in D1
RANDOM_NUMBER:     
    MOVE.B #8, D0 #Loads D1 with Time in 100's of Seconds since midnight (6 Bits)
    TRAP #15  #Place time in D1
    AND.L #$5FFFFF, D1 # AND 6 Bits to prevent any overflow
    DIVU #100, D1 #Divide by 100, 1000, 10000 depending on Number range required
    SWAP D1 #SWAP Higher Order Word and Lower Order Word in D1
    ADDQ.W #1, D1 #Add 1 to D1.W so number is at least 1
    MOVE.W D1, D2 #Extract the number from D1.W
    CLR.L D1 # Clear contents of D1
    MOVE.W D2, D1 #Move the generated number to D1
    RTS
;Method to generate a dice roll from random number, will end as one of 6 dice values
ROLLDICE:    
    BSR RANDOM_NUMBER
    CMP #16, D1
    BLE DONE
    CMP #33, D1
    BLE DTWO
    CMP #50, D1
    BLE DTHREE
    CMP #66, D1
    BLE DFOUR
    CMP #83, D1
    BLE DFIVE
    BRA DSIX        
;Load Dice 1 string
DONE:
    MOVE.L #1,D1
    LEA ONED, A1
    RTS
;Load Dice 2 string
DTWO:
    MOVE.L #2,D1
    LEA TWOD, A1 
    RTS
;Load Dice 3 string
DTHREE:
    MOVE.L #3,D1 
    LEA THREED, A1
    RTS
;Load Dice 4 string
DFOUR:
    MOVE.L #4,D1 
    LEA FOURD, A1
    RTS
;Load Dice 5 string
DFIVE:
    MOVE.L #5,D1 
    LEA FIVED, A1
    RTS
;Load Dice 6 string
DSIX:  
    MOVE.L #6,D1
    LEA SIXD,A1 
    RTS
;Method to ask player to press enter to continue
ENTERTOCONTINUE:
    LEA PRESSENTER, A1       ;Prints press start to enter
    BSR PRINTSTRING
ENTERLOOP:                ;loops until enter key presses
    BSR CHARINPUT
    CMP #ENTERKEY, D1
    BNE ENTERLOOP
    RTS
EARTHPLAY:
    BSR CLEARSCREEN
BR:
    LEA BLUE,A3
    LEA GREEN, A4
    LEA EARTHC, A6      ;Put reference to the correct image pick in A6
    BSR PRINTEARTH
    LEA YELLOW, A3
    LEA PURPLE, A4
    BSR EARTHPL
    LEA RED, A3
    LEA BLUE, A4
    BSR EARTHPL
    BRA BR
*-------------------------------------------------------
;GAME WIN AND LOSE EVENTS
*-------------------------------------------------------
GAMELOSE:    ;Lose condition from carbon ppm over tipping point
    BSR CLEARSCREEN
    BSR FONTRED
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA GAMEOV, A1
    BSR PRINTSTRING
    LEA GAMEOV2A, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    LEA GREEN, A3        ;Set font of first color for printearth subroutine
    LEA BLUE, A4        ;Set font of second color for printearth subroutine
    LEA EARTHC, A6      ;Put reference to the correct image pick in A6
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    LEA GAMEOV2B,A1
    BSR PRINTSTRING
    BSR ENTERTOCONTINUE
    LEA ORANGE, A3
    LEA RED, A4
    LEA EARTHC, A6      ;Put reference to the correct image pick in A6
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    BSR FONTRED
    LEA GAMEOV2C, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA END

    
GAMEWIN:   ;Win subroutine for getting C02 increase to 0
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA GAMEOVWIN, A1
    BSR PRINTSTRING
    LEA GAMEOV1, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    LEA GREEN, A3        ;Set font of first color for printearth subroutine
    LEA BLUE, A4        ;Set font of second color for printearth subroutine
    LEA EARTHC, A6      ;Put reference to the correct image pick in A6
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    LEA GAMEOV1A,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA PLAYAGAIN
;Method for personal cash win, kinda secret ending
GAMEWINC:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA ROCKWIN, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA ELON, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA ROCK,A1
    BSR PRINTSTRING
    LEA ROCK1, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ ROCKETGO          ;Rocket go animation game ending
    CMP #2, D5
    BEQ MAIN
    BRA GAMEWINC 
ROCKETGO:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA ROCKWIN, A1
    BSR PRINTSTRING
    LEA GREEN, A3        ;Set font of first color for printearth subroutine
    LEA BLUE, A4        ;Set font of second color for printearth subroutine
    LEA EARTHD, A6      ;Put reference to the correct earth pick in A6
    BSR PRINTEARTH    
    BSR ROCKETANI
;Method to print ending text
ROCKETEND:    
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    BSR FONTWHITE
    LEA ROCK2, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA PLAYAGAIN
*-------------------------------------------------------
;PLAY AGAIN SUBROUTINE, UPS DIFFICULTY ON REPLAY
*-------------------------------------------------------
;Method to ask player if they wish to play on a harder difficulty
PLAYAGAIN:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR NEWLINE
    BSR NEWLINE
    LEA VIC,A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    BSR NEWLINE
    BSR NEWLINE
    LEA DIE, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    BSR NEWLINE
    BSR NEWLINE
    LEA AGAIN, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ DIFFICULTYUP
    CMP #2, D5
    BEQ END
    BRA PLAYAGAIN
;Method to increase game difficulty
DIFFICULTYUP:
    ;Set up initial variables   
    ;Global Variables
    
    ;Power values
    MOVE.L #12000, CPOWERADD        ;Value of Carbon Power at game start
    MOVE.L #0, GPOWERADD         ;Value of Green Power at game start
    
    ;Tree variables
    MOVE.L #1, TREEABSORB         ;Amount one million trees reduces amount
    MOVE.L #0, TREENUM              ;Number of trees
    MOVE.L #415, CARBONADD      ;Carbon in atmosphere
    MOVE.L #25000, CASHADD       ;Amount of cash at start of game
    MOVE.L #25000, PCASHADD      ;Amount of personal cash at start
    
    ;Calculate CO2 at start
    MOVE.L CPOWERADD, D2
    MULU #CPERGW,D2   
    MOVE.L D2, COINCREASE 
    MOVE.L #475, CARBONLOSE      ;Carbon value that is game over and player loses in ppm
    MOVE.L #0, CARBONWIN       ;Carbon value that is game over and player wins in tonnes
    MOVE.L #0,   CASHLOSE       ;Cash at which game over and player loses
    MOVE.L #1000000, CASHWIN      ;Cash at which game over and player wins
    
    BRA MAIN
*-------------------------------------------------------
;ROCKET ANIMATION SUBROUTINE FOR SECRET ENDING
*-------------------------------------------------------
 
;Method to animate rocket leaving earth
ROCKETANI:
    MOVE.L #30, D4
    MOVE.L #$330B, D3  ;Save start value in D3
RLOOP:  ;Rocket loop to move the components of the rocket image
    MOVE.L D3,D1    
    BSR SETCURSOR
    LEA ORANGE, A3
    MOVE.L #0, D2  ;Clear d2
    BSR FONTSETA
    LEA EXHAUST1, A1
    BSR PRINTSTRING
    ADD.L #1, D3     ;Add 1 to row number
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA EXHAUST2, A1
    BSR PRINTSTRING
    ADD.L #1, D3   ;Add 1 to row number
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA EXHAUST3, A1
    BSR PRINTSTRING
    ADD.L #$700,D3   ;Add 1 to column number etc
    SUB.L #2, D3
    MOVE.L D3, D1
    BSR SETCURSOR
    BSR FONTGOLD
    LEA ROCKET1, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA ROCKET2, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA ROCKET3, A1
    BSR PRINTSTRING
    SUB #1, D4
    CMP #0, D4
    BEQ ROCKETEND
    MOVE.L #25, D1
    BSR CWAIT
    BRA CLEARROCKET
;Method to delete rocket for reprinting
CLEARROCKET:
    SUB #2, D3
    SUB #$700, D3
    MOVE D3, D1
    BSR SETCURSOR
    LEA ORANGE, A3
    MOVE.L #0, D2  ;Clear d2
    BSR FONTSETA
    LEA BEXHAUST1, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA BEXHAUST2, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA BEXHAUST3, A1
    BSR PRINTSTRING
    ADD.L #$700,D3
    SUB.L #2, D3
    MOVE.L D3, D1
    BSR SETCURSOR
    BSR FONTGOLD
    LEA BROCKET1, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA BROCKET2, A1
    BSR PRINTSTRING
    ADD.L #1, D3
    MOVE.L D3,D1
    BSR SETCURSOR
    LEA BROCKET3, A1
    BSR PRINTSTRING
    SUB #2, D3
    SUB #$600,D3
    BRA RLOOP
*-------------------------------------------------------
;MAIN HUD PRINTING SUBROUTINE
*-------------------------------------------------------
MAINHUD:               ;Method to print the main hud
    BSR CLEARSCREEN
    LEA BOX2, A1
    BSR PRINTSTRING
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTLGREEN     ;Print all resource totals in different colours
    BSR FONTUNDERLINE
    LEA AGCNAME, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA CSHAMOUNT, A1
    MOVE.L CASHADD, D1
    BSR PRINTSTRINGNUM
    LEA PCASH,A1
    MOVE.L PCASHADD,D1
    BSR PRINTSTRINGNUM
    BSR NEWLINE
    BSR FONTBROWN
    LEA CURRENTGAS, A1
    MOVE.L CARBONADD, D1
    BSR PRINTSTRINGNUM
    LEA PM, A1
    BSR PRINTSTRING
    BSR FONTRED
    LEA TIPPINGPOINT, A1
    MOVE.L #TIP, D1
    BSR PRINTSTRINGNUM
    LEA PM, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR FONTBROWN
    LEA ANNINCREASE,A1
    MOVE.L COINCREASE,D1
    BSR PRINTSTRINGNUM
    LEA MILLTON, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA CARBONERG, A1
    MOVE.L CPOWERADD, D1
    BSR PRINTSTRINGNUM
    LEA GWS, A1
    BSR PRINTSTRING
    LEA GLOENERGY, A1
    BSR PRINTSTRING
    BSR NEWLINE
    LEA GREENERG, A1
    MOVE.L GPOWERADD, D1
    BSR PRINTSTRINGNUM
    LEA GWS, A1
    BSR PRINTSTRING
    BSR PRINTTAB
    BSR PRINTTAB
    BSR PRINTTAB
    MOVE.L #GLOERG,D1
    BSR PRINTNUM
    LEA GWS, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTGREEN
    LEA TREEPLAN, A1
    MOVE.L TREENUM, D1
    BSR PRINTSTRINGNUM
    LEA TREENM, A1
    BSR PRINTSTRING
    BSR NEWLINE
    LEA TREEAB, A1
    MOVE.L TREENUM, D2
    MOVE.L TREEABSORB, D1
    MULU D2,D1
    BSR PRINTSTRINGNUM
    LEA CTON, A1
    BSR PRINTSTRING
    BSR FONTWHITE       
    RTS
 
*-------------------------------------------------------
;DICE ROLLING MENU AND SUBROUTINES
*-------------------------------------------------------
DICECHOICE:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA DTEXT, A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA DIE, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA DTEXT2, A1
    BSR PRINTSTRING
    MOVE.L #3,D3    ;Counter for dice roll animation
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ DICEGO
    CMP #2, D5
    BEQ BETMENU
    CMP #3, D5
    BRA REDODDS
    BRA DICECHOICE
 
REDODDS:           ;Subroutine to reduce odds of rolls
    MOVE.L PCASHADD, D1    ;Check if player has the cash
    CMP #25000, PCASHADD
    BGT INPFUND
    MOVE.L PCASHADD, D1
    SUB.L #25000, D1       ;Subtract the cash
    MOVE.L D1, PCASHADD
    MOVE.L #1, LOWRISK
    MOVE.L #3, MEDRISK
    MOVE.L #4, HIGHRISK
    BRA DICEGO
RESODDS:           ;Subroutine to restore odds of rolls 
    MOVE.L #2, LOWRISK
    MOVE.L #4, MEDRISK
    MOVE.L #5, HIGHRISK
    RTS 
DICEGO:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA DTEXT, A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA ROLLD, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA DTEXT3, A1
    BSR PRINTSTRING
    MOVE.L #0,D1    ;Clear D1  
    MOVE.W   #50,D1      ;Wait half second
    MOVE.B   #23,D0
    TRAP     #15
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA DTEXT, A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA ROLLD1, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA DTEXT3, A1
    BSR PRINTSTRING
    MOVE.L #0,D1    ;Clear D1  
    MOVE.W   #50,D1      ;Wait half second
    MOVE.B   #23,D0
    TRAP     #15  
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA DTEXT, A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA ROLLD2, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA DTEXT3, A1
    BSR PRINTSTRING
    SUB.L #1, D3
    CMP #0, D3
    BEQ DICEEND
    MOVE.L #0,D1    ;Clear D1  
    MOVE.W   #50,D1      ;Wait half second
    MOVE.B   #23,D0
    TRAP     #15
    BRA DICEGO
DICEEND:
    BSR CLEARSCREEN
    BSR FONTGREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA DTEXT, A1
    BSR PRINTSTRING
    BSR ROLLDICE
    MOVE.L D1, D4       ;Save dice result
    BSR FONTPURPLE
    BSR PRINTSTRING     ;Print dice
    BSR NEWLINE
    BSR FONTWHITE
    LEA DTEXT4, A1
    MOVE.L D4, D1   ;Move result back to d1
    BSR PRINTSTRINGNUM
    BSR NEWLINE
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    RTS
*-------------------------------------------------------
;BET MENU AND SUBROUTINES
*-------------------------------------------------------   
BETMENU:
    BSR CLEARSCREEN
    BSR FONTPURPLE
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA GTEXT, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA EURO, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA GTEXT2, A1
    BSR PRINTSTRING
    MOVE.L #0, D1 ;clear d1
    MOVE.L RSK,D1 ;get which risk level has been picked
    CMP #1, D1
    BEQ LOWODD
    CMP #2, D1
    BEQ MEDODD
    CMP #3, D1
    BEQ HIGHODD
ODDSRET:
    BSR PRINTSTRING ;Now print correct odds
    LEA GTEXT3, A1
    BSR PRINTSTRING
    BSR NUMINPUT    ;Read in user input
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ BET
    CMP #2, D5
    BEQ DICECHOICE
    BRA BETMENU
LOWODD:
    LEA ODDS1, A1
    BRA ODDSRET
MEDODD:
    LEA ODDS2, A1
    BRA ODDSRET
HIGHODD:
    LEA ODDS3, A1
    BRA ODDSRET
BET:
    MOVE.L PCASHADD, D1
    CMP #25000, D1
    MOVE.L #1, BETFLAG ;Set the betflag to 1
    BGE DICEGO
    BRA INPFUND 
BETLOSE:            ;Subroutine for lose bet
    MOVE.L PCASHADD, D1
    SUB.L #25000, D1
    MOVE.L D1, PCASHADD
    MOVE.L #0,D1
    MOVE.L D1,BETFLAG  ;Reset betflag    
    BRA UPDATE
BETWINLO:          ;Subroutine for win bet high odds
    MOVE.L PCASHADD, D1
    ADD.L #12250, D1
    MOVE.L D1, PCASHADD
    MOVE.L #0,D1
    MOVE.L D1,BETFLAG  ;Reset betflag 
    BRA UPDATE

BETWINMID:         ;Subroutine for win bet med odds
    MOVE.L PCASHADD, D1
    ADD.L #25000, D1
    MOVE.L D1, PCASHADD
    MOVE.L #0,D1
    MOVE.L D1,BETFLAG  ;Reset betflag 
    BRA UPDATE

BETWINHI:          ;Subroutine for win bet low odds
    MOVE.L PCASHADD, D1
    ADD.L #50000, D1
    MOVE.L D1, PCASHADD
    MOVE.L #0,D1
    MOVE.L D1,BETFLAG  ;Reset betflag 
    BRA UPDATE
 
*-------------------------------------------------------
;GAME UPDATE SUBROUTINE
*-------------------------------------------------------   
UPDATE:
    ;Check carbon generation
    
    ;Calculate carbon from power generation
    MOVE.L #0, D2    ;clear d2
    MOVE.L CPOWERADD, D2
    MULU #CPERGW,D2   
    MOVE.L D2, COINCREASE        
    ;Calculate absorption
    MOVE.L TREENUM, D3
    MOVE.L TREEABSORB, D4
    MULU D3,D4
    
    ;Subtract carbon increase and decrease
    SUB.L D4, D2
    MOVE.L D2, COINCREASE ;Put new value back
    
    ;Calculate ppm increase
    MOVE.L #PPMFACTOR, D3 ;Divide ppm factor against coincrease
    DIVU D3, D2          
    MOVE.L CARBONADD, D3
    ADD.W D2,D3           ;Cut off remainder and add whole number
    MOVE.L D3, CARBONADD  ;Move new ppm value back to memory
SLOOP:    
    ;Check for victory or loss conditions
    ;Check for carbon low
    MOVE.L COINCREASE , D2
    MOVE.L CARBONWIN, D3         ;Carbon low enough for win
    CMP.L D3, D2
    BLE GAMEWIN
    ;Check for carbon high
    MOVE.L CARBONADD, D2
    MOVE.L CARBONLOSE, D4        ;Carbon too high
    CMP.L D4, D2
    BGE GAMELOSE
    ;Check for P Cash high
    MOVE.L PCASHADD, D3         ;Cash high enought to win
    MOVE.L CASHWIN, D2
    CMP.L D2, D3
    BGE GAMEWINC
    ;Check for energy too low
    MOVE.L CPOWERADD, D2
    MOVE.L GPOWERADD, D3
    ADD.L D3,D2
    BSR UPDATEPOWER        ;Check for low global power conditions
UPDATERETURN2:            ;Return from bad event checks
    BSR GEVENTCHECK      ;Check for good event conditions
    BSR RESODDS          ;Set dice roll chances back to default
    BRA MAIN
UPDATERETURN:
    RTS
UPDATEPOWER:
    ;Compare for critical global energy level
    CMP #GLOERGC, D2
    BLT PEVENTROLLB
NEXTPOWERCK:     ;Check second condition
    ;Compare for less critical global energy level
    CMP #GLOERG, D2
    BLT PEVENTROLLA
    RTS
*-----------------------------------------------------------
;Code for debugging room
*----------------------------------------------------------- 
ROOM101:
    BSR CLEARSCREEN
    BSR FONTPURPLE
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR NEWLINE
    BSR NEWLINE
    LEA R101,A1
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE
    LEA CHEAT, A1
    BSR PRINTSTRING
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D5
    BEQ CODOWN
    CMP #2, D5
    BEQ PCUP
    CMP #3, D5
    BEQ CODOWN4
    CMP #4, D5
    BEQ CODOWN3
    CMP #5, D5
    BEQ CODOWN2
    CMP #6, D5
    BEQ CODOWN1
    CMP #7, D5
    BEQ CPOW
    CMP #8, D5
    BEQ GPOW
    CMP #9, D5
    BEQ PPMUP
    CMP #10, D5
    BEQ CSH
    CMP #11,D5
    BEQ CEND
    CMP #12,D5
    BRA MAIN
    BRA ROOM101
     
;Method for room 101 debugger, set Carbon to 0
CODOWN:
    MOVE.L COINCREASE, D1
    MOVE.L #0,D1
    MOVE.L D1, COINCREASE
    BRA SLOOP
;Method for room 101 debugger, set Pcash 500000
PCUP:
    MOVE.L PCASHADD, D1
    MOVE.L #500000,D1
    MOVE.L D1, PCASHADD
    BRA SLOOP
;Method for room 101 debugger, set Carbon to 40000
CODOWN4:
    MOVE.L COINCREASE, D1
    MOVE.L #40000,D1
    MOVE.L D1, COINCREASE
    BRA SLOOP
;Method for room 101 debugger, set Carbon to 30000
CODOWN3:
    MOVE.L COINCREASE, D1
    MOVE.L #30000,D1
    MOVE.L D1, COINCREASE
    BRA SLOOP
;Method for room 101 debugger, set Carbon to 20000
CODOWN2:
    MOVE.L COINCREASE, D1
    MOVE.L #20000,D1
    MOVE.L D1, COINCREASE
    BRA SLOOP
 ;Method for room 101 debugger, set Carbon to 10000
CODOWN1:
    MOVE.L COINCREASE, D1
    MOVE.L #10000,D1
    MOVE.L D1, COINCREASE
    BRA SLOOP
 ;Method for room 101 debugger, subtract Cpower 1000
CPOW:
    MOVE.L CPOWERADD, D1
    SUB.L #1000,D1
    MOVE.L D1, CPOWERADD
    BRA SLOOP
 ;Method for room 101 debugger, add Gpower 1000
GPOW:
    MOVE.L GPOWERADD, D1
    ADD.L #1000,D1
    MOVE.L D1, GPOWERADD
    BRA SLOOP
;Method for room 101 debugger, set PPM to 477
PPMUP:
    MOVE.L CARBONADD, D1
    MOVE.L #476,D1
    MOVE.L D1, CARBONADD
    BRA SLOOP
;Method for room 101 debugger, add 100000 PCASH  
CSH:
    MOVE.L CASHADD, D1
    ADD.L #100000,D1
    MOVE.L D1, CASHADD
    BRA SLOOP
;Method for room 101 debugger, trigger crit power ending
CEND:
    BRA PEVENTBFAIL
*-----------------------------------------------------------
;Code for temptation event, steal money from donation
*----------------------------------------------------------- 
TEMPTATION:
    BSR CLEARSCREEN
    BSR FONTPURPLE
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGOLD
    LEA ETHICS, A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA EURO, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR NEWLINE
    BSR FONTWHITE
    LEA ETHICS1, A1
    BSR PRINTSTRING
    MOVE.L #0, D1 ;clear d1
    MOVE.L RSK,D1 ;get which risk level has been picked
    BSR NUMINPUT
    MOVE.B $1900,D5
    CMP #1, D1
    BEQ BADBOY
    CMP #2, D1
    BEQ GOODBOY
    BRA TEMPTATION
       
BADBOY:
    MOVE.L PCASHADD, D1
    MOVE.L EVENTCASH ,D2
    ADD.L D2, D1        ;Cash amount should be in EVENTCASH mem location before call, add to personal cash
    MOVE.L #0, D2       ;Set EVENTCASH back to 0
    MOVE.L D2, EVENTCASH
    MOVE.L D1, PCASHADD
    RTS
GOODBOY:
    MOVE.L CASHADD, D1
    MOVE.L EVENTCASH ,D2
    ADD.L D2, D1
    MOVE.L #0, D2       ;Set EVENTCASH back to 0
    MOVE.L D2, EVENTCASH
    MOVE.L D1, CASHADD
    RTS     

*-----------------------------------------------------------
;Code for random bad events
*-----------------------------------------------------------    
PEVENTROLLA:
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #50, D1
    BLT PEVENTAFAIL      ;if below 50 failed roll negative outcome
PEVENTASUC:
    BRA UPDATERETURN2
PEVENTAFAIL:            ;Bad event roll
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTRED
    LEA PEVENTF, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA RIOT, A1
    BSR PRINTSTRING
    LEA PEVENTFA, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L CPOWERADD, D2
    ADD.L #500, D2
    MOVE.L D2, CPOWERADD
    BRA UPDATERETURN2
PEVENTROLLB:            ;Roll for game ending event
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #41, D1
    BLT PEVENTBFAIL      ;if below 41 failed roll negative outcome, game over!
PEVENTBSUC:
    BRA NEXTPOWERCK
PEVENTBFAIL:            ;Bad event roll
    BSR CLEARSCREEN
    BSR CLEARSCREEN
    BSR FONTRED
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    LEA GAMEOVALT, A1
    BSR PRINTSTRING
    BSR FONTYELLOW
    LEA NUC, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA GAMEOV3, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    LEA GREEN, A3        ;Set font of first color for printearth subroutine
    LEA BLUE, A4        ;Set font of second color for printearth subroutine
    LEA EARTHC, A6      ;Load earth image into A6
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    LEA GAMEOV3A,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    LEA EARTHC, A6
    LEA LBLUE, A3
    LEA GREEN, A4
    BSR PRINTEARTH
    MOVE.L #$0018, D1   ;Move cursor under art
    BSR SETCURSOR
    BSR FONTRED
    LEA GAMEOV3B, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA END
*-----------------------------------------------------------
;Code for random good events
*----------------------------------------------------------- 
GEVENTCHECK:
    BSR GEVENTCHECKA
    BSR GEVENTCHECKB
    BSR GEVENTCHECKC
    BSR GEVENTCHECKD
    RTS  

GEVENTCHECKA:           ;Good event check method, if carbon under milstone
    MOVE.L COINCREASE,D1
    MOVE.L COMILE1, D2
    CMP.L D2,D1
    BLE GEVENTCHECK2A
    RTS
GEVENTCHECK2A:          ;Check if event already occured
    MOVE.L #0,D1        ;Clear D1
    MOVE.B COMILE1OCC, D1
    CMP #0, D1
    BEQ GEVENTROLLA
    RTS 
GEVENTROLLA:
    MOVE.L #0,D1        ;Clear D1      
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #50, D1          ;if above 50 or equal passed roll positive outcome no good event
    BGE GEVENTASUC
    CMP #50, D1
    BLT GEVENTAFAIL      ;if below 50 failed roll positive outcome
    RTS
GEVENTASUC:
    RTS
GEVENTAFAIL:            ;Good event roll, if roll fails this subroutine, is good outcome
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTGREEN
    LEA GEVENT, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA WHISKEY, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA GEVENTA, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #100000, EVENTCASH  ;Add cash to eventcash mem location for temptation event
    BSR TEMPTATION    
    MOVE.L #0,D1            ;Clear D1
    MOVE.B COMILE1OCC,D1   ;Set occured flag to 1
    MOVE.B #1, D1
    MOVE.B D1, COMILE1OCC
    RTS 

GEVENTCHECKB:           ;Good event check method, if carbon under milstone
    MOVE.L COINCREASE,D1
    MOVE.L COMILE2, D2
    CMP.L D2,D1
    BLE GEVENTCHECK2B
    RTS
GEVENTCHECK2B:          ;Check if event already occured
    MOVE.L #0,D1        ;Clear D1
    MOVE.B COMILE2OCC, D1
    CMP #0, D1
    BEQ GEVENTROLLB
    RTS 
GEVENTROLLB:
    MOVE.L #0,D1        ;Clear D1      
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #50, D1          ;if above 50 or equal passed roll positive outcome no good event
    BGE GEVENTBSUC
    CMP #50, D1
    BLT GEVENTBFAIL      ;if below 50 failed roll positive outcome
    RTS
GEVENTBSUC:
    RTS
GEVENTBFAIL:            ;Good event roll
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTGREEN
    LEA GEVENT, A1
    BSR PRINTSTRING
    BSR FONTLBLUE
    LEA POLAR, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA GEVENTB, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #100000, EVENTCASH  ;Add cash to eventcash mem location for temptation event
    BSR TEMPTATION  
    MOVE.L #0,D1            ;Clear D1
    MOVE.B COMILE2OCC,D1   ;Set occured flag to 1
    MOVE.B #1, D1
    MOVE.B D1, COMILE2OCC
    RTS 

GEVENTCHECKC:           ;Good event check method, if carbon under milstone
    MOVE.L COINCREASE,D1
    MOVE.L COMILE3, D2
    CMP.L D2,D1
    BLE GEVENTCHECK3A
    RTS
GEVENTCHECK3A:          ;Check if event already occured
    MOVE.L #0,D1        ;Clear D1
    MOVE.B COMILE3OCC, D1
    CMP #0, D1
    BEQ GEVENTROLLC
    RTS 
GEVENTROLLC:
    MOVE.L #0,D1        ;Clear D1      
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #50, D1          ;if above 50 or equal passed roll positive outcome no good event
    BGE GEVENTCSUC
    CMP #50, D1
    BLT GEVENTCFAIL      ;if below 50 failed roll positive outcome
    RTS
GEVENTCSUC:
    RTS
GEVENTCFAIL:            ;Good event roll
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTGREEN
    LEA GEVENT, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA FLAME, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA GEVENTC, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #100000, EVENTCASH  ;Add cash to eventcash mem location for temptation event
    BSR TEMPTATION  
    MOVE.L #0,D1            ;Clear D1
    MOVE.B COMILE3OCC,D1   ;Set occured flag to 1
    MOVE.B #1, D1
    MOVE.B D1, COMILE3OCC
    RTS 
GEVENTCHECKD:           ;Good event check method, if carbon under milstone
    MOVE.L COINCREASE,D1
    MOVE.L COMILE4 ,D2
    CMP.L D2,D1
    BLE GEVENTCHECK2D
    RTS
GEVENTCHECK2D          ;Check if event already occured
    MOVE.L #0,D1        ;Clear D1
    MOVE.B COMILE4OCC, D1
    CMP #0, D1
    BEQ GEVENTROLLD
    RTS 
GEVENTROLLD
    MOVE.L #0,D1        ;Clear D1      
    BSR RANDOM_NUMBER    ;Put random number in D1
    CMP #50, D1          ;if above 50 or equal passed roll positive outcome no good event
    BGE GEVENTDSUC
    CMP #50, D1
    BLT GEVENTDFAIL      ;if below 50 failed roll positive outcome
    RTS
GEVENTDSUC:
    RTS
GEVENTDFAIL:            ;Good event roll
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #$0101, D1 ;MOVE CURSOR BACK TO START
    MOVE.B #11, D0
    TRAP #15
    BSR FONTGREEN
    LEA GEVENT, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA SSUN, A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA GEVENTD,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.L #100000, EVENTCASH  ;Add cash to eventcash mem location for temptation event
    BSR TEMPTATION  
    MOVE.L #0,D1            ;Clear D1
    MOVE.B COMILE4OCC,D1   ;Set occured flag to 1
    MOVE.B #1, D1
    MOVE.B D1, COMILE4OCC
    RTS 
 
*-----------------------------------------------------------
;Code for maze mini game
*-----------------------------------------------------------
MAZEGAME:         ; maze game
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA MAZEINTRO1,A1
    BSR PRINTSTRING
    BSR FONTWHITE
    LEA MAZEINTRO2,A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA MAZEINTRO3,A1
    BSR PRINTSTRING
    BSR FONTGREEN
    LEA MAZEINTRO4,A1
    BSR PRINTSTRING
    BSR FONTRED
    LEA MAZEINTRO5,A1
    BSR PRINTSTRING
    BSR FONTWHITE
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    MOVE.W #$2706, epos   ;Enemy position at start
    MOVE.L #0, D1         ;clear d1
    MOVE.W #1, D1
    MOVE.W D1, EAI       ;Set enemy ai variable to 1, will try up first
    ;Get number of times game played
    LEA MAZEST1, A4
    LEA MAZEEN1, A5
    MOVE.L COUNTER, D1
    CMP #5, D1          ;if 5 games played set counter back to 0
    BEQ COUNTERRESET
ARRAYRET:               ;Method to check counter and advance target and player starting positions to different locations depending on game number
    CMP #0, D1
    BNE ARRAYADVANCE
    MOVE.L COUNTER, D1
    ADD.L #1, D1
    MOVE.L D1, COUNTER   ;Advance counter by one
    ;Set player position and increment address pointer
    
    MOVE.W (A4),D4   ;player position at start, d4 reserved for player position
    MOVE.W (A4),oldpos ;set value of old position to be same as start position
    MOVE.L #30, D7       ;Sets time starting at 30, d7 reserved for seconds
    BSR STARTTIMER      ;Start timer method, gets initial time in secs
    BSR FONTGOLD
    BSR BOARD           ;Print board
    BSR FONTWHITE
    BSR PRINTTARGET
    BSR PRINTTIME       ;Print time
    BRA GAMELOOP
ARRAYADVANCE:
    MOVE.W (A5)+, TARPOS    ;Increment target starting position
    MOVE.W (A4)+, PLAYPOS    ;Incremnet player starting position
    MOVE.W (A5)+, TARPOS    ;Increment target starting position
    MOVE.W (A4)+, PLAYPOS    ;Incremnet player starting position
    SUB.L #1, D1             ;Decrement D1
    BRA ARRAYRET
COUNTERRESET:
    MOVE.L #0, D1
    MOVE.L D1, COUNTER
    RTS
GAMELOOP: 
    BSR GAMEEND         ;Check for gameend    
    BSR PRINTPLAYER     ;Prints player
    BSR TICK            ;Checks if a second has passed
    BSR PRINTPLAYER     ;Prints player again as tick clears board to reprint time
    BSR PRINTENEMY
    BSR ENEMYMOVE
    BSR PRINTENEMY
    MOVE.B #23 ,D0    
    MOVE.L #10,D1       ;Delay rechecking moment to stop multiple input on one keypress
    TRAP #15
    BSR INPUT          ;Check four keys for pressed events
    
STARTTIMER:
    MOVE.B #8, D0        ;Trap 8, gets time in sec since midnight some year
    TRAP #15
    MOVE.L D1, oldtime   ;Store result at mem address oldtime
    RTS       

TICK:
    MOVE.L oldtime, D6     ;Put oldtime value in D6
    MOVE.B #8, D0
    TRAP #15
    MOVE.L D1, currenttime   ;Get current time and store in currenttime
    SUB.L D6, D1             ;Subtract newtime-oldtime
    DIVU #150,D1             ;Divide by 150, trap 8 returns in miliseconds
    CLR D2                   ;Clear d2 to prevent rogue values
    MOVE.B D1,D2             ;Move results from d2 to d1
    CMP #0, D2               ;if result greater than one
    BGT ADVANCETIME          ;call method advance time
    RTS

ADVANCETIME:                 ;overrides oldtime with current time and subtracts seconds
    SUB #1, D7               
    MOVE.L currenttime, oldtime
    BSR PRINTTIME
    RTS
    
PRINTTIME:                  ;Moves cursor to a location and prints current seconds
    MOVE.W #$2001, D1
    MOVE.B #11, D0
    TRAP #15
    LEA DBLANK, A1          ;This was to stop the second number from remaining when got down to single digits, print double blank and then backspace twice
    BSR PRINTSTRING
    MOVE.L D7,D1
    MOVE.B #3, D0
    TRAP #15
    MOVE.W D4, D1           ;Move cursor back to player position
    MOVE.B #11, D0
    TRAP #15
    RTS
       
INPUT:
    
     MOVE.B #19,D0    
     MOVE.L #'A'<<24+'S'<<16+'D'<<8+'W',D1 ; check for keypress (a,s,d,f)
     TRAP   #15
     BTST.L #24,D1     ; test for 'a'
     IF <NE> THEN      ; if 'a'
     BRA LEFT   
     ENDI
     BTST.L #16,D1     ; test for 's'
     IF <NE> THEN      ;if 's'
     BRA DOWN
     ENDI
     BTST.L #8,D1     ; test for 'd'
     IF <NE> THEN      ; if 'd'
     BRA RIGHT   
     ENDI
     BTST.L #0,D1     ; test for 'f'
     IF <NE> THEN      ;if 'f'
     BRA UP
     ENDI
     MOVE.B #19, D0
     MOVE.L #'Q',D1 ;  ;also check for q for quit game
     TRAP #15
     BTST.L #0, D1 ;test for q
     IF <NE> THEN
     BRA MAIN
     ENDI
     BRA GAMELOOP

GAMEEND:                ;subroutine to test for gamewin/lose conditions
    ;CMP #$191A, D4
    ;BEQ MAZEWIN
    CMP #0, D7         ;Check for time ending
    BEQ MAZELOSE
    BSR CURSOR
    RTS
 
CURSOR:                ;subroutine to store cursor location in mem location 3000 for testing, remove in final program
    MOVE.W #$00FF, D1
    MOVE.B #11, D0
    TRAP #15
    MOVE.W D1, $3000
    RTS 
MAZEWIN:               ;Subroutine for maze lose, print text,add cash, wait for enter to continue, branch back to mai
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTGREEN
    LEA MINIAWIN,A1
    BSR PRINTSTRING
    LEA EURO, A1
    BSR PRINTSTRING
    BSR FONTGOLD
    LEA MINIAWIN1, A1
    BSR PRINTSTRING
    ;Add Cash
    MOVE.L CASHADD, D6
    MOVE.L #100000, D7
    ADD.L D7,D6  
    MOVE.L D6, CASHADD
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA UPDATE       
MAZELOSE:           ;Subroutine for maze lose, print text, wait for enter to continue, branch back to main
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA MINIALOSE,A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA WHISKEY,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR FONTWHITE
    LEA MINIALOSE1, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA UPDATE

MAZELOSE2:           ;Subroutine for maze lose, print text, wait for enter to continue, branch back to main
    BSR CLEARSCREEN
    BSR PRINTBORDER
    MOVE.L #0,D1
    BSR SETCURSOR        ;Move cursor back to start 
    BSR FONTRED
    LEA MINIALOSE2,A1
    BSR PRINTSTRING
    BSR FONTPURPLE
    LEA WHISKEY,A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR FONTWHITE
    LEA MINIALOSE21, A1
    BSR PRINTSTRING
    BSR NEWLINE
    BSR ENTERTOCONTINUE
    BRA UPDATE
    
SWAPBYTESB:  ;Swaps the higher and lower order words of value in d3, also uses d2 so keep clear
    MOVE.B D3,D2        ;Move low order byte to D2
    LSL.L #$08, D2      ;Need to swap row to 16 bit higher byte and column 16 bit lower byte for Trap 22, so left shift low byte 8 bits twice
    LSL.L #$08, D2
    LSR.W #$08, D3       ;Move D3 value 8 bits to right
    MOVE.B D3, D2       ;Finally add back lower order byte
    MOVE.L D2,D3        ;Move final value back to D3 for end of subroutine
    RTS
     
UP:
    MOVE.W D4, D3       ;Move player position to d3
    SUB #1, D3          ;Subtract one, for one row up
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1        ;Move to D1 for compare
    MOVE.B #22, D0      ;Call Trap 22 to read char at that location
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2       ;Move the byte representing character to d2
    CMP #88, D2         ;Check for target, capital X
    BEQ MAZEWIN
    CMP #32, D2         ;Compare to decimal 32, ascii code for blank space
    BNE GAMELOOP        ;If not equals blank space, move, otherwise NO
    MOVE.W D4, oldpos   ;save oldpos for old character deletion
    MOVE.W D6, D4       ;If allowed, update player position
    BRA GAMELOOP
DOWN:
    MOVE.W D4,D3
    ADD #1, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22, D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #88, D2         ;Check for target, capital X
    BEQ MAZEWIN
    CMP #32,D2
    BNE GAMELOOP
    MOVE.W D4, oldpos
    MOVE.W D6, D4
    BRA GAMELOOP
LEFT:
    MOVE.W D4, D3
    SUB #$100, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22,D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #88, D2         ;Check for target, capital X
    BEQ MAZEWIN
    CMP #32,D2
    BNE GAMELOOP
    MOVE.W D4, oldpos
    MOVE.W D6, D4
    BRA GAMELOOP
RIGHT:
    MOVE.W D4, D3
    ADD #$100, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22,D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #88, D2         ;Check for target, capital X
    BEQ MAZEWIN
    CMP #32,D2
    BNE GAMELOOP
    MOVE.W D4, oldpos
    MOVE.W D6, D4
    BRA GAMELOOP
ENEMYMOVE:
    MOVE.L #0, D1       ;Clear D1
    MOVE.W EAI, D1      ;Move enemy ai variable into d1
    CMP #1, D1
    BEQ EUP
    CMP #2, D1
    BEQ ERIGHT
    CMP #3, D1
    BEQ EDOWN
    CMP #4, D1
    BEQ ELEFT
    RTS
EUP:
    MOVE.W #1, EAI        ;Make sure enemy ai is at 1 for up
    MOVE.L #0,D3        ;Clear D3
    MOVE.W EPOS, D3       ;Move enemy position to d3
    SUB #1, D3          ;Subtract one, for one row up
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1        ;Move to D1 for compare
    MOVE.B #22, D0      ;Call Trap 22 to read char at that location
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2       ;Move the byte representing character to d2
    CMP #111, D2         ;Compare to decimal 111, ascii code for o
    BEQ MAZELOSE2        ;If equals then a player collision has occured, end game
    CMP #32, D2         ;Compare to decimal 32, ascii code for blank space
    BNE RANDOMMOVEMENT        ;If not equals blank space, move, otherwise pick random direction
    MOVE.W EPOS, OLDEPOS   ;save oldpos for old character deletion
    MOVE.W D6, EPOS       ;If allowed, update enemy position
    RTS    
ERIGHT:
    MOVE.W #2, EAI      ;Make sure enemy ai is at 2 for right
    MOVE.L #0,D3        ;Clear D3
    MOVE.W EPOS, D3
    ADD #$100, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22,D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #111, D2         ;Compare to decimal 111, ascii code for o
    BEQ MAZELOSE2        ;If equals then a player collision has occured, end game
    CMP #32,D2
    BNE RANDOMMOVEMENT
    MOVE.W EPOS, OLDEPOS
    MOVE.W D6, EPOS
    RTS
EDOWN:
    MOVE.W #3, EAI      ;Make sure enemy ai is at 3 for down
    MOVE.L #0,D3        ;Clear D3
    MOVE.W EPOS,D3
    ADD #1, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22, D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #111, D2         ;Compare to decimal 111, ascii code for o
    BEQ MAZELOSE2        ;If equals then a player collision has occured, end game
    CMP #32,D2
    BNE RANDOMMOVEMENT
    MOVE.W EPOS, OLDEPOS
    MOVE.W D6, EPOS
    RTS
ELEFT:
    MOVE.W #4, EAI      ;Make sure enemy ai is at 4 for left
    MOVE.L #0,D3        ;Clear D3
    MOVE.W EPOS, D3
    SUB #$100, D3
    MOVE.L #0,D6        ;Clear D6
    MOVE.W D3, D6       ;Store in D6
    BSR SWAPBYTESB
    MOVE.L D3,D1
    MOVE.B #22,D0
    TRAP #15
    MOVE.L #0,D2        ;Clear D2
    MOVE.B D1, D2
    CMP #111, D2         ;Compare to decimal 111, ascii code for o
    BEQ MAZELOSE2        ;If equals then a player collision has occured, end game
    CMP #32,D2
    BNE RANDOMMOVEMENT
    MOVE.W EPOS, OLDEPOS
    MOVE.W D6, EPOS
    RTS
;Subroutine to randomise enemy movement
RANDOMMOVEMENT:
    BSR RANDOM_NUMBER
    CMP #25, D1
    BLE EUP
    CMP #50, D1
    BLE ERIGHT
    CMP #75, D1
    BLE ELEFT
    BRA EDOWN
  
PRINTPLAYER:
    BSR CLEARPLAYER
    BSR FONTLBLUE
    MOVE.L #0, D1 ;clear d1
    MOVE.W D4,D1   ;Move cursor to player position#$191A
    MOVE.B #11, D0
    TRAP #15
    LEA player, A1
    MOVE.B #14, D0
    TRAP #15
    BSR FONTWHITE
    RTS
PRINTENEMY:
    BSR CLEARENEMY
    BSR FONTRED
    MOVE.L #0, D1 ;clear d1
    MOVE.W EPOS,D1   ;Move cursor to player position#$191A
    MOVE.B #11, D0
    TRAP #15
    LEA enemy, A1
    MOVE.B #14, D0
    TRAP #15
    BSR FONTWHITE
    RTS
PRINTTARGET:
    BSR CLEARPLAYER
    BSR FONTGREEN
    MOVE.L #0,D1     ;Clear d1
    MOVE.W (A5),D1   ;Move cursor to target  position value of mem address A5
    MOVE.B #11, D0
    TRAP #15
    LEA target, A1
    MOVE.B #14, D0
    TRAP #15
    BSR FONTWHITE
    RTS
CLEARPLAYER:
    MOVE.L #0, D1 ;clear d1
    MOVE.W oldpos,D1 
    MOVE.B #11, D0
    TRAP #15
    LEA blank, A1  ;print blank space
    MOVE.B #14, D0
    TRAP #15    
    RTS
CLEARENEMY:
    MOVE.L #0, D1 ;clear d1
    MOVE.W oldepos,D1 
    MOVE.B #11, D0
    TRAP #15
    LEA blank, A1  ;print blank space
    MOVE.B #14, D0
    TRAP #15    
    RTS
BOARD:
    BSR CLEARSCREEN
    MOVE.B #14, D0
    LEA maze, A1
    TRAP #15
    RTS
    
    
END:
    SIMHALT             ; halt simulator


;VARIABLE AND CONSTANTS ;NOTE $0A is for newline, $0D is for carriage return


OPTIONSA:     DC.B    '                     Available Actions                      ',$0D,$0A
              DC.B    '  You sit down at your desk and your secretary lays out the dire facts before ',$0D,$0A
              DC.B    '  you. They explain you must manage the meagre budget given to you wisely to ',$0D,$0A
              DC.B    '  save the day.No pressure! At least they give you some nice green tea...',$0D,$0A
              DC.B    '  What would you like to do?',$0D,$0A,$0A
              DC.B    '           1. View Energy Investment Projects                       ',$0D,$0A
              DC.B    '           2. Try raise some Cash                                   ',$0D,$0A , 0
DTEXT         DC.B    $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                     All ventures depend partly on luck!                     |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
DTEXT2        DC.B    $0D,$0A
              DC.B    $09,'Ready to test your luck?',$0D,$0A,$0A
              DC.B    $09,'       1. Roll Dice                       ',$0D,$0A
              DC.B    $09,'       2. Hedge your bets                      ',$0D,$0A
              DC.B    $09,'       3. Selflessly invest 25000 of your personal cash to improve',$0D,$0A
              DC.B    $09,'          the projects odds.(Successful roll needed reduced by 1)',$0D,$0A,0
DTEXT3        DC.B    $09,'       Rolling...                      ',$0D,$0A,0
DTEXT4        DC.B    $09,'You rolled a ',0

GTEXT         DC.B    $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                              Hedge your Bets!                               |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
GTEXT2        DC.B    $0D,$0A
              DC.B    $09,'A little bet on the side is harmless fun right? Best not mention',$0D,$0A
              DC.B    $09,'to anyone that your are betting on this though...',$0D,$0A,$0D,$0A
              DC.B    $09,'Spice up your life and bet that this venture will fail...',$0D,$0A,$0D,$0A
              DC.B    $09,'1. Bet personal cash 25000 that venture will fail at ',0
GTEXT3        DC.B    $0D,$0A,$09,'2. Thats highly unethical, insider betting! Return to dice',$0D,$0A,0
ODDS1         DC.B    '1.5 to 1 odds',0
ODDS2         DC.B    '2 to 1 odds',0
ODDS3         DC.B    '3 to 1 odds',0

SUCTEXT       DC.B    $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                  SUCCESS!!!                                 |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0  
QUESTEXT       DC.B    $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                      ???                                    |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0   
FAILTEXT      DC.B    $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                               DIRE FAILURE!!!                               |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0                 
OPTIONSB:     DC.B    $0A, $0D,$0A, $0D
              DC.B    '+******************************************************************************+',$0A, $0D
              DC.B    '|                         WORLD CLIMATE ACTION AGENCY                          |',$0A, $0D
              DC.B    '+******************************************************************************+',$0A, $0D
              DC.B    '|                           Available WCAA Iniatives                           |',$0A, $0D
              DC.B    '+******************************************************************************+',$0A, $0D,$0A, $0D,0
OPTIONSBA     DC.B    $09,'Your secretary has prepared a list of iniatives that can be undertaken',$0D,$0A
              DC.B    $09,'this calendar year.',$0D,$0A,$0D,$0A
              DC.B    $09,'Each project will take a year to complete and have its ',$0D,$0A
              DC.B    $09,'risks and costs. Choice wisely! ',$0D,$0A
              DC.B    $09,'Your secretary has started sweating profusely...',$0D,$0A
              DC.B    $0D,$0A,$0D,$0A
              DC.B    $09,$09,'1. Invest in a tree planting iniative to offset Carbon  '
              DC.B    $0D,$0A,$0D,$0A
              DC.B    $09,$09,'2. Invest in zero carbon energy production   '
              DC.B    $0D,$0A,$0D,$0A
              DC.B    $09,$09,'3. Reduce carbon producing energy production   '
              DC.B    $0D,$0A,$0D,$0A
              DC.B    $09,$09,'4. Return to Menu'   
              DC.B    $0D,$0A , 0
MISOPTIONS    DC.B    '+*****************************************************************************+',$0A,$0D
              DC.B    '|                            Available Missions                              |',$0A,$0D
              DC.B    '+*****************************************************************************+',$0A,$0D,$0A,$0D,0 
MISOPTIONS1   DC.B    $09,'Your secretary presents you with a list of possible cash raising ',$0D,$0A
              DC.B    $09,'activities. What would you like to pick? ',$0D,$0A, $0D,$0A
              DC.B    $09,'1. Try raise some cash from rich donors       ',$0D,$0A
              ;DC.B    $09,'2. Earth Play       ',$0D,$0A
              DC.B    $09,'2. Retreat to menu in disgrace...                      ',$0D,$0A ,0

ETHICS        DC.B    '+*****************************************************************************+',$0A,$0D
              DC.B    '|                               Opportunity?                                  |',$0A,$0D
              DC.B    '+*****************************************************************************+',$0A,$0D,$0A,$0D,0 
ETHICS1       DC.B    $09,'An interesting opportunity presents itself. With great power',$0D,$0A
              DC.B    $09,'comes great opportunity. Only you know that this cash payment ',$0D,$0A
              DC.B    $09,'is coming in so if it was diverted to your personal account',$0D,$0A
              DC.B    $09,'surely nobody would find out?',$0D,$0A
              DC.B    $09,'You can use it for the betterment of the world more freely if',$0D,$0A
              DC.B    $09,'you had full control of the money perhaps???',$0D,$0A,$0D,$0A
              DC.B    $09,'   1. Yes absolutely, I will use it for the greater good...probably',$0D,$0A
              DC.B    $09,'   2. No of course not, that would be highly unethical',0

RISK          DC.B    '+******************************************************************************+',$0A, $0D
              DC.B    '|                                    Risk                                     |',$0A, $0D
              DC.B    '+******************************************************************************+',$0A, $0D,$0A, $0D,0
LRISK        DC.B    $09,'All ventures in life come with an element of risk. For now you have',$0A, $0D
              DC.B    $09,'chosen the safe path. But even the safe path has an element of danger',$0A, $0D,$0A, $0D
              DC.B    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
               DC.B    $09,'between 2 and 6',$0A, $0D,0
MRISK       DC.B    $09,'All ventures in life come with an element of risk. You have',$0A, $0D
              DC.B    $09,'chosen the middle path, not too hot, not too cold',$0A, $0D,$0A, $0D
              DC.B    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
              DC.B    $09,'between 4 and 6',$0A, $0D,0
HRISK      DC.B    $09,'All ventures in life come with an element of risk. You have',$0A, $0D
              DC.B    $09,'chosen shot for the stars, nothing ventured nothing gained!',$0A, $0D,$0A, $0D
              DC.B    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
              DC.B    $09,' between 5 and 6',$0A, $0D,0
              
TREEOPTIONS   DC.B    $0A, $0D,$0A, $0D,'+******************************************************************************+',$0A, $0D
              DC.B    '|                           Tree Planting Iniatives                           |',$0A, $0D
              DC.B    '+******************************************************************************+',$0A, $0D,$0A, $0D,0
TREEOPTIONSB  DC.B    $0D,$0A,$0D,$0A,$09,'Your secretary presents you with a list of Tree',$0D,$0A
              DC.B    $09,'Tree Planting Iniatives. What would you like to pick? ',$0D,$0A,$0D,$0A,0
TREEOPTIONSB1 DC.B    $09,$09,'1. Buy land and plant a million trees in an area with a',$0D,$0A
              DC.B    $09,$09,'   seemingly suitable climate.  (Low Risk)       Cost: 100000'
              DC.B    $0D,$0A,$0D,$0A,0
TREEOPTIONSB2 DC.B    $09,$09,'2. Buy land and plant a million trees in an area with the cheapest',$0D,$0A
              DC.B    $09,$09,'   land prices.       (Medium Risk)              Cost:  50000',$0D,$0A
              DC.B    $0D,$0A,$0D,$0A,0
TREEOPTIONSB3 DC.B    $09,$09,'3. Partner with a vicious dictator and plant a million trees',$0D,$0A
              DC.B    $09,$09,'   on land ceased from people fighting for freedom in his    ',$0D,$0A
              DC.B    $09,$09,'   country. The press release will say terrorists...         ',$0D,$0A
              DC.B    $09,$09,'                      (High Risk)                Cost:  25000',$0D,$0A,$0D,$0A,0
TREEOPTIONSB4 DC.B    $09,$09,'4. Retreat to menu in disappointment...          Cost:  Pride',$0D,$0A,0

TREEOPTA:     DC.B    $0D,$0A
              DC.B    $09,'The iniative is a success, with over a million trees planted in  ',$0D,$0A
              DC.B    $09,'this scenic location over the course of the year. They stretch as',$0D,$0A
              DC.B    $09,'far as the eye can see. Maybe you should buy that waterfront property',$0D,$0A
              DC.B    $09,'afterall!',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary looks impressed...',$0D,$0A,$0D,$0A,0

TREEOPTBN:    DC.B    $0D,$0A
              DC.B    $09,'Disaster! It turns out while the climate was lovely the      ',$0D,$0A
              DC.B    $09,'location picked was in the shadow of an active volcano!      ',$0D,$0A,$0D,$0A
              DC.B    $09,'The advisor who chose the location will be fired. As an      ',$0D,$0A
              DC.B    $09,'employee of this public body that means they will only       ',$0D,$0A
              DC.B    $09,'receive 75% of their entitled pension. Truly justice         ',$0D,$0A
              DC.B    $09,'has been served.           ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary will only bring you give you cold',$0D,$0A
              DC.B    $09,'coffee for now..',$0D,$0A,0

TREEOPTB:     DC.B    $0D,$0A
              DC.B    $09,'Success! Despite the harse climate in the region with some   ',$0D,$0A
              DC.B    $09,'love and care, the trees have adapted and thrived. ',$0D,$0A,$0D,$0A
              DC.B    $09,'You have saved cash and carbon in one stroke.    ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary smiles enigmatically.',$0D,$0A,0
           
TREEOPTAN:    DC.B    $0D,$0A
              DC.B    $09,'Hmmm, it turns out that trees need water to grow and the arid',$0D,$0A
              DC.B    $09,'area picked for this iniative had none.',$0D,$0A,$0D,$0A
              DC.B    $09,'On the brightside this has been an opportunity for learning',$0D,$0A
              DC.B    $09,'for all involved the PR campaign will claim.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary refuses to look you in the eyes...',$0D,$0A,0

TREEOPTC:     DC.B    $0D,$0A
              DC.B    $09,'Good news, in an unbelievable turn of events, the regime',$0D,$0A
              DC.B    $09,'has reported that the terrorists who previously owned the',$0D,$0A
              DC.B    $09,'land gave it to the government willingly!   ',$0D,$0A,$0D,$0A
              DC.B    $09,'They also have agreed to tend to the trees for FREE with the',$0D,$0A
              DC.B    $09,'regime volunteering to supply armed guards to keep them safe',$0D,$0A
              DC.B    $09,'while they work. Truly we have made the world a better place.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary smiles dubiously...                           ',$0D,$0A,$0D,$0A,0
           
TREEOPTCN:    DC.B    $0D,$0A
              DC.B    $09,'In an unexpected and unforeseeable turn of events, we have      ',$0D,$0A
              DC.B    $09,'learned that dictators are not nice people.   ',$0D,$0A,$0D,$0A
              DC.B    $09,'The regime has pocketed the cash and refuses to plant the trees',$0D,$0A
              DC.B    $09,'promised. Now you are not even sure they were terrorist afterall.      ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary has started drinking whiskey...               ',$0D,$0A,0
ENERGYOPTION  DC.B    '+*****************************************************************************+',$0A, $0D
              DC.B    '|                         Zero Carbon Energy Iniatives                        |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,0 
ENERGYOPTIONB DC.B    $0A,$0D,$09,'Your secretary presents you with a list of Green Energy Initiatives. ',$0D,$0A
              DC.B    $09,'Which would you like to pick? ',$0D,$0A,$0D,$0A,0
ENERGYOPTION1 DC.B    $09,'1. Buy land in a desert regions and build a massive solar plant',$0D,$0A
              DC.B    $09,'   capable of producing 1000GW.  (Low Risk)        Cost: 100000',$0D,$0A,$0D,$0A,0
ENERGYOPTION2 DC.B    $09,'2. Build a massive offshore wind farm in the storm wracked   ',$0D,$0A
              DC.B    $09,'   waters off ship grave reef, not sure where the name came  ',$0D,$0A
              DC.B    $09,'   from but your advisors assure you it is quite windy. The  ',$0D,$0A
              DC.B    $09,'   the rights to build there are cheap too.         Prod:1000GW',$0D,$0A
              DC.B    $09,'                                 (Medium Risk),,   Cost:  50000'
              DC.B    $0D,$0A,$0D,$0A,0
ENERGYOPTION3 DC.B    $09,'3. Former Soviet Republics have decommissioned nuclear       ',$0D,$0A
              DC.B    $09,'   available for sales at amazing prices, the seller claims  ',$0D,$0A
              DC.B    $09,'   that similar reactors have an ALMOST perfect safety record',$0D,$0A
              DC.B    $09,'   so what could be the downsides from such a great deal     ',$0D,$0A
              DC.B    $09,'   Prod:1000GW                   (High Risk)       Cost:  25000',$0D,$0A,$0D,$0A,0
ENERGYOPTION4 DC.B    $09,'4. Some world class scientists have a proposal to fund what   ',$0D,$0A
              DC.B    $09,'   they claim is the future of green energy production,      ',$0D,$0A
              DC.B    $09,'   FUSION! They are currently projecting it will be ready to ',$0D,$0A 
              DC.B    $09,'   save the world and produce all the green energy the planet',$0D,$0A
              DC.B    $09,'   needs in ten years with current funding but they say with ',$0D,$0A
              DC.B    $09,'   additional funds it could be ready much sooner            ',$0D,$0A
              DC.B    $09,'                                 (Unknown Risk)    Cost:  50000',$0D,$0A,$0D,$0A,0
ENERGYOPTION5 DC.B    $09,'5. Retreat to menu in disappointment...            Cost:  Pride',$0D,$0A,0
ENERGYOPTA:   DC.B    $0A, $0D
              DC.B    $09,'The solar power plant is up and running producing clean green',$0D,$0A
              DC.B    $09,'energy. The sun shines on your efforts.',$0D,$0A
              DC.B    $09,'Your secretary gives you a complementary muffin...',$0D,$0A,$0D,$0A,0

ENERGYOPTAN:  DC.B    $0A, $0D
              DC.B    $09,'Oh dear, the newspapers are having a field day, it turns out that',$0D,$0A
              DC.B    $09,'the antartic is technically the largest desert in the world!',$0D,$0A,$0D,$0A
              DC.B    $09,'Due to an unfortunate miscommunication, the new solar plant',$0D,$0A
              DC.B    $09,'was built in THAT desert. The penguins are cute on the upside.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary has developed 100 new grey hairs...',$0D,$0A,0

ENERGYOPTB:   DC.B    $0A, $0D
              DC.B    $09,'The new windfarm construction has been completed despite ',$0D,$0A
              DC.B    $09,'lively weather. A success of human ingenuity over mother ',$0D,$0A
              DC.B    $09,'nature.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary offers you a rare pat on the back...  ',$0D,$0A,0
           
ENERGYOPTBN:  DC.B    $0A, $0D
              DC.B    $09,'Oh dear, after only three days of operation a large storm ',$0D,$0A
              DC.B    $09,'destoys the new power plant. The irony of wind destroying ',$0D,$0A
              DC.B    $09,'your brand new wind farm is lost on you.      ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary has suddenly started updating their ',$0D,$0A
              DC.B    $09,'CV for some reason...',$0D,$0A,0

ENERGYOPTC:   DC.B    $0A, $0D
              DC.B    $09,'Your plan to buy and put into operation an obsolete Soviet ',$0D,$0A
              DC.B    $09,'nuclear power plant has alarmed World leaders to such an   ',$0D,$0A
              DC.B    $09,'extent that they fund the building of a new solar farm if  ',$0D,$0A
              DC.B    $09,'you agree to desist. ',$0D,$0A,$0D,$0A
              DC.B    $09,'You reluctantly agree, what were they so worried about?',$0D,$0A
              DC.B    $09,'Your secretary has started using smaller words when talking',$0D,$0A
              DC.B    $09,'to you  which is helpful...                      Prod: 500GW ',$0D,$0A,0
          
ENERGYOPTCN:  DC.B    $0A, $0D,$0A, $0D,$0A, $0D
              DC.B    $09,'Your plan while foolproof has somehow backfired with the ',$0D,$0A
              DC.B    $09,'alarmed world officials blocking your plan and the former ',$0D,$0A
              DC.B    $09,'Soviet Republic refusing to give back the funds. ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary refuses to allow you to use scissors...',$0D,$0A,0
              
ENERGYOPTD:   DC.B    $0A, $0D
              DC.B    $09,'Wonderful news, the scientists have made a huge breakthrough ',$0D,$0A
              DC.B    $09,'in the production of fusion power. ',$0D,$0A,$0D,$0A
              DC.B    $09,'They report that they now expect that they can produce endless',$0D,$0A
              DC.B    $09,'green energy in TEN years, down from the ten years predicted  ',$0D,$0A
              DC.B    $09,'before the expensive research. They just ask for more funds ',$0D,$0A,0
              DC.B    $09,'to reduce this time further...',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary has started building a survival bunker...     ',$0D,$0A,0
CUTOPTIONS    DC.B    '+*****************************************************************************+',$0A, $0D
              DC.B    '|                     Reduce Non-Green Energy Iniatives                       |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
CUTOPTIONSB   DC.B    $09,'Your secretary presents you with a list of Iniatives to reduce. ',$0D,$0A
              DC.B    $09,'CO2 production. Which would you like to pick? ',$0D,$0A,$0D,$0A,$0D,$0A,0
CUTOPTIONS1   DC.B    $09,'1. Introduce higher Carbon taxes on non-green energy         ',$0D,$0A
              DC.B    $09,'   energy production.-1000GW    (Low Risk)      Gain Cash: 25000',$0D,$0A,$0D,$0A,0
CUTOPTIONS2   DC.B    $09,'2. Introduce new safety regul ations that make some old       ',$0D,$0A
              DC.B    $09,'   powerplants unviable. Bonus is that the fines you can     ',$0D,$0A
              DC.B    $09,'   levy might make some extra cash!                          ',$0D,$0A
              DC.B    '                      -1500GW       (Medium Risk)   Gain Cash: 50000',$0D,$0A,$0D,$0A,0
CUTOPTIONS3   DC.B    $09,'3. Manufacturer fake documents claiming that a top executive ',$0D,$0A
              DC.B    $09,'   of a major oil company lost shares to you in a drunken    ',$0D,$0A
              DC.B    $09,'   poker game. Use this share claim to shutdown one of their ',$0D,$0A
              DC.B    $09,'   energy production divisions                               ',$0D,$0A
              DC.B    '                      -2000GW       (High Risk)    Gain Cash: 100000',$0D,$0A,$0D,$0A,0
CUTOPTIONS4   DC.B    $09,'4. Retreat to menu in disappointment...              Cost: Pride',$0D,$0A,0
CUTOPTA:      DC.B    $0A, $0D
              DC.B    $09,'The new tax while unpopular with corporations is a great     ',$0D,$0A
              DC.B    $09,'success making green energy more competitive.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary offers you a hot chocolate...',$0D,$0A,$0D,$0A,0

CUTOPTAN:     DC.B    $0A, $0D
              DC.B    $09,'The tax has had mixed results, the raised price just made    ',$0D,$0A
              DC.B    $09,'producing non-green power more profitable so more non-green  ',$0D,$0A
              DC.B    $09,'power is now being produced.   ',$0D,$0A,$0D,$0A
              DC.B    $09,'You do get some more taxes on the bright side?',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary glowers in disapproval...    ',$0D,$0A,0
          
CUTOPTB:      DC.B    $0A, $0D
              DC.B    $09,'The fines for violations of the new safety regulations roll  ',$0D,$0A
              DC.B    $09,'in and many old power plants have been forced to shut. ',$0D,$0A,$0D,$0A
              DC.B    $09,'We may succeed after all.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary whistles merrily...    ',$0D,$0A,0
              
CUTOPTBN:     DC.B    $0A, $0D
              DC.B    $09,'The non-green energy companies implementing your new safety  ',$0D,$0A
              DC.B    $09,'regulations has been a huge PR win for them. Workers and investors',$0D,$0A
              DC.B    $09,'flock to work in the industry and power production has risen wildly.',$0D,$0A,$0D,$0A
              DC.B    $09,'On the bright side fines rolls in filling your cash reserves.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary now wears a pernament frown...  '             ,$0D,$0A,0

CUTOPTC:      DC.B    $0A, $0D
              DC.B    $09,'Luck is with you, turns out the judge reviewing the case ',$0D,$0A
              DC.B    $09,'was a complete idiot and believes the ridiculous lie. ',$0D,$0A
              DC.B    $09,'You gain control of one of their energy plants and shut it down. ',$0D,$0A
              DC.B    $09,'Who says crime doesnt pay. ',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary admires your ruthlessness...',$0D,$0A,0
                     
CUTOPTCN:     DC.B    $0A, $0D
              DC.B    $09,'The judge reviewing your case dies of laugther but not before',$0D,$0A
              DC.B    $09,'throwing the case out of court. ',$0D,$0A,$0D,$0A
              DC.B    $09,'Now you are stuck with the legal costs with nothing to show for it.',$0D,$0A,$0D,$0A
              DC.B    $09,'Your secretary kicks you in the shin, that was rude...',$0D,$0A,0

CUTOPTQ:     DC.B    $0A, $0D, $0A, $0D, $0A, $0D, $0A, $0D, $0A, $0D
              DC.B    $09,'WARNING:',$0D,$0A
              DC.B    $09,'Cutting energy production below global needs may lead to dire',$0D,$0A
              DC.B    $09,'consequences. The further under production the more dire the outcomes',$0D,$0A
              DC.B    $09,'may be. Are you sure you wish to proceed:',$0D,$0A,$0D,$0A

              DC.B    $09,'  1. Yes',$0D,$0A
              DC.B    $09,'  2. No',$0D,$0A,0

CUTOPTQC:     DC.B    $09,'WARNING:',$0D,$0A
              DC.B    $09,'Power Production worldwide is reaching critically low levels.',$0D,$0A
              DC.B    $09,'Brownouts are now common all over the world with even critical',$0D,$0A
              DC.B    $09,'infrastructure like hospitals and food refrigeration often',$0D,$0A
              DC.B    $09,'losing power for parts of the day.',$0D,$0A,$0D,$0A
              DC.B    $09,'Major countries have starting squabbling over power use',$0D,$0A
              DC.B    $09,'cutting interconnecting power lines between countries and',$0D,$0A
              DC.B    $09,'hordeing their power for themselves.',$0D,$0A,$0D,$0A
              DC.B    $09,'Cutting energy production any further may have longlasting',$0D,$0A
              DC.B    $09,'and worldwide consequences.',$0D,$0A
              DC.B    $09,'Are you sure you wish to proceed:',$0D,$0A,$0D,$0A

              DC.B    $09,'  1. Yes',$0D,$0A
              DC.B    $09,'  2. No',$0D,$0A,0

NOFUNDS:      DC.B    $0D,$0A,$0D,$0A,$09,'You have insufficient funds for this project. Your secretary  ',$0D,$0A
              DC.B    $09,'rolls their eyes behind your back...                          ',$0D,$0A,0

NOPFUNDS:     DC.B    $0D,$0A,$0D,$0A,$09,'You have insufficient funds for this bet. Your secretary  ',$0D,$0A
              DC.B    $09,'would roll their eyes if they knew about it...',$0D,$0A,0
              
NOCPOWER:     DC.B    $0D,$0A,$0D,$0A,$09,'There is insufficient carbon power left to cut. Your  ',$0D,$0A
              DC.B    $09,'secretary signs irritably...                          ',$0D,$0A,0
       
NLINE:        DC.B    $0D,$0A , 0
TAB:          DC.B    $09,0
GAMEOV        DC.B    $0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D, $0D,$0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                 Game Over!                                  |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
GAMEOVALT     DC.B    $0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                 Game Over!                                  |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
GAMEOVWIN     DC.B     $0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D, $0D,$0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                 Victory !                                   |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0 
GAMEOV1       DC.B    $09,'With greenhouse gas levels finally under control, you have ensured',$0A, $0D
              DC.B    $09,'life not just for humans but for all other species with which',$0A, $0D
              DC.B    $09,'we share this planet.',$0A, $0D,0
GAMEOV1A      DC.B    $09,'We have just one home which we must share with our fellow',$0A, $0D
              DC.B    $09,'species. Your actions may have helped us succeed today but it is',$0A, $0D
              DC.B    $09,'our collective responsibility to leave it in good order for others',$0A, $0D
              DC.B    $09,'to have the same opportunity in the future...',$0A, $0D,0
                          
GAMEOV2A      DC.B    $09,'The tipping point has been been reached, now events spiral slowly',$0A, $0D
              DC.B    $09,'out of control.',$0A,$0D,0
GAMEOV2B      DC.B    $09,'The greenland ice sheet collapses, the onrush of fresh water raising',$0A, $0D
              DC.B    $09,'sea levels and changing global ocean currents forever.',$0A, $0D,$0A, $0D
              DC.B    $09,'The global disruption to weather patterns accelerates warming melting',$0A, $0D
              DC.B    $09,' permafrosts releasing uncontrollable amounts of extra greenhouse ',$0A, $0D
              DC.B    $09,'gases into the atmosphere..',$0A, $0D,0
            
GAMEOV2C      DC.B    $09,'We failed to act decisively when we had the chance and now live in ',$0A, $0D
              DC.B    $09,'a world with an uncertain future wracked by wildfires and unprecedented',$0A, $0D
              DC.B    $09,'weather patterns.',$0A, $0D,$0A, $0D
              DC.B    $09,'On the bright side, you do save money with not having to buy jumpers...',$0A, $0D,0

GAMEOV3:      DC.B    $09,'Your mishandling of the worlds power needs have caused chaos',$0A, $0D
              DC.B    $09,'around the world.',$0A, $0D,$0A, $0D
              DC.B    $09,'This disorder sparked old rivalries as countries began to squabble',$0A, $0D
              DC.B    $09,'over the sparse resources. Finally this escalated into war',$0A, $0D
              DC.B    $09,'and nuclear exchanges between the major power.',$0A, $0D,0

GAMEOV3A:     DC.B    $09,'On the plus side this has solved global warming by causing',$0A, $0D
              DC.B    $09,'a nuclear winter sparking a mini ice age. So in a way you have',$0A, $0D
              DC.B    $09,'succeeded in your mandate.',$0A, $0D,0
GAMEOV3B:      DC.B    $09,'This comforts you as you fight off some mutant raiders',$0A, $0D
              DC.B    $09,'who are attacking your survival bunker',$0A, $0D,$0A, $0D
              DC.B    $09,'Your secretary, now a mutant zombie groans loudly...',$0A, $0D,0
ROCKWIN       DC.B     '+*****************************************************************************+',$0A, $0D
              DC.B    '|                                Victory ???                                   |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,0 
ROCK          DC.B    $09,'The earth may not be saved but you might be. Your shrewd and',$0A, $0D
              DC.B    $09,'totally ethical financial decisions have paid off for you.',$0A, $0D,$0A, $0D
              DC.B    $09,'You now have enough cash to buy yourself a ticket on billionaires',$0A, $0D
              DC.B    $09,'Jeflon Mezos rocket and escape this mess.',$0A, $0D,$0A, $0D
              DC.B    $09,'Apparently Mars is lovely this time of year and if anything its.',$0A, $0D
              DC.B    $09,'atmosphere needs MORE greenhouse gases which we have proved experts',$0A, $0D
              DC.B    $09,'in accomodating...',$0A, $0D,$0A, $0D,0
ROCK1         DC.B    $09,'1. Yes screw Earth, I can learn to speak Martian...',$0D,$0A
              DC.B    $09,'2. No I will use my wealth to try fix the planet we have...',$0D,$0A,0

AGAIN         DC.B    $09,'Congradulations, whether by luck, wit or ruthlessness you',$0A, $0D
              DC.B    $09,'have prevailed against the odds',$0A, $0D,$0A, $0D
              DC.B    $09,'Do you wish to really test your skill and play again on a',$0A, $0D
              DC.B    $09,'harder difficulty?',$0A, $0D,$0A, $0D
              DC.B    $09,'   1. Yes i am ready for any challenge... (Play again)',$0D,$0A
              DC.B    $09,'   2. No, i wish to rest...                 (End Game)',$0D,$0A,0

ROCK2         DC.B   $09,'You boldly break the bounds of earths problems and boldly go',$0A, $0D
              DC.B    $09,'where no man has gone before.',$0A, $0D
              DC.B    $09,'We will make our new home a paradise on Earth...err Mars.',$0A, $0D
              DC.B    $09,'It sounds like it has a lot of potential.',$0A, $0D,$0A, $0D
              DC.B    $09,'Just reading the brochure, wait...Mars has no oxygen?',0
              
GEVENT       DC.B    $0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                Good News!                                   |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0
GEVENTA       DC.B    $09,'World leaders are amazed by your progress. Greenhouse gas',$0A, $0D
              DC.B    $09,'emissions have dropped to their lowest levels since the',$0A, $0D
              DC.B    $09,'1990s.',$0A, $0D
              DC.B    $09,'The prime minister of a major country is so impressed he comes',$0A, $0D
              DC.B    $09,'to visit your office.',$0A, $0D
              DC.B    $09,'After toasting to your success a few too many times, he drunkenly',$0A, $0D
              DC.B    $09,'pledges a generous sum to your cause.',$0A, $0D
              DC.B    $09,'Your secretary is no longer embarressed to be seen with you...',0

GEVENTB       DC.B    $09,'Congradulations are in order. For the first time in many  ',$0A, $0D
              DC.B    $09,'decades, north pole ice sheets have slowed their shrinking.',$0A, $0D
              DC.B    $09,'The event makes world news with many praising your efforts.',$0A, $0D,$0A, $0D
              DC.B    $09,'The head of the polar bear appreciation society is so happy',$0A, $0D
              DC.B    $09,'he sells his polar bear minature collection and donates the',$0A, $0D
              DC.B    $09,'proceeds to your cause!',$0A, $0D
              DC.B    $09,'Your secretary gives you an awkward high five...',0

GEVENTC       DC.B    $09,'More good news has come into your office. With the global',$0A, $0D
              DC.B    $09,'temperature increases finally coming under control',$0A, $0D
              DC.B    $09,'wildfire incidents are at a ten year low.',$0A, $0D,$0A, $0D
              DC.B    $09,'Fire departments around the world donate the money',$0A, $0D
              DC.B    $09,'they are saving from not having to control the wildfires to',$0A, $0D
              DC.B    $09,'your cause in thanks!',$0A, $0D
              DC.B    $09,'Your secretary blows a kiss in your direction...',0

GEVENTD       DC.B    $09,'Your progress is causing global celebrations. People',$0A, $0D
              DC.B    $09,'around the world begin to believe that this problem can',$0A, $0D
              DC.B    $09,'be solved.',$0A, $0D,$0A, $0D
              DC.B    $09,'Global incidents of extreme weather events like hurricanes',$0A, $0D
              DC.B    $09,'are at a 30 year low. Countries with land near sea level',$0A, $0D
              DC.B    $09,'donate the money they had set aside for flood defence to your cause.',$0A, $0D,$0A, $0D
              DC.B    $09,'Your secretary buys you a voucher for a spa treatment...',0
                          
PEVENTF       DC.B    $0A, $0D,'+*****************************************************************************+',$0A, $0D
              DC.B    '|                                 Bad News!                                   |',$0A, $0D
              DC.B    '+*****************************************************************************+',$0A, $0D,$0A, $0D,0
PEVENTFA      DC.B    $09,'The lack of worldwide power production is causing chaos around the',$0A, $0D
              DC.B    $09,'globe. Brown outs are becoming common and the disruption to food',$0A, $0D
              DC.B    $09,'distribution networks has caused riots in many countries.',$0A, $0D,$0A, $0D
              DC.B    $09,'Some governments ignore your mandate and bring additional non-green',$0A, $0D
              DC.B    $09,'powerplants back online.',$0A, $0D,$0A, $0D
              DC.B    $09,'Your secretary paces restlessly...',$0A, $0D,0

INVALID:      DC.B    $09,'Invalid input, please read options carefully and try again',0
INVALIDCLR    DC.B    $09,'                                                            ',0

STARTCREDITS: DC.B     $09,'   /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\',$0D,$0A
              DC.B     $09,'     /$$$$$$  /$$ /$$                           /$$  ',$0D,$0A
              DC.B     $09,'    /$$__  $$| $$|__/                          | $$ ',$0D,$0A
              DC.B     $09,'   | $$  \__/| $$ /$$ /$$$$$$/$$$$   /$$$$$$  /$$$$$$    /$$$$$$',$0D,$0A
              DC.B     $09,'   | $$      | $$| $$| $$_  $$_  $$ |____  $$|_  $$_/   /$$__  $$',$0D,$0A
              DC.B     $09,'   | $$      | $$| $$| $$ \ $$ \ $$  /$$$$$$$  | $$    | $$$$$$$$',$0D,$0A
              DC.B     $09,'   | $$    $$| $$| $$| $$ | $$ | $$ /$$__  $$  | $$ /$$| $$_____/',$0D,$0A
              DC.B     $09,'   |  $$$$$$/| $$| $$| $$ | $$ | $$|  $$$$$$$  |  $$$$/|  $$$$$$$',$0D,$0A
              DC.B     $09,'    \______/ |__/|__/|__/ |__/ |__/ \_______/   \___/   \_______/',$0D,$0A,$0A
              DC.B     $09,'     /$$$$$$  /$$                                     /$$ /$$ /$$',$0D,$0A
              DC.B     $09,'    /$$__  $$| $$                                    | $$| $$| $$',$0D,$0A
              DC.B     $09,'   | $$  \__/| $$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$| $$| $$| $$',$0D,$0A
              DC.B     $09,'   | $$      | $$__  $$ |____  $$ /$$__  $$ /$$_____/| $$| $$| $$',$0D,$0A
              DC.B     $09,'   | $$      | $$  \ $$  /$$$$$$$| $$  \ $$|  $$$$$$ |__/|__/|__/',$0D,$0A
              DC.B     $09,'   | $$    $$| $$  | $$ /$$__  $$| $$  | $$ \____  $$    ',$0D,$0A
              DC.B     $09,'   |  $$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$/ /$$$$$$$/ /$$ /$$ /$$',$0D,$0A
              DC.B     $09,'    \______/ |__/  |__/ \_______/ \______/ |_______/ |__/|__/|__/',$0D,$0A
              DC.B     $09,'   \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/',$0D,$0A,$0A,$0A,$0A,0   

PRESSSTART:   DC.B      $09,$09,$09,'   Press Enter if you wish to save the planet!',0              
PRESSENTER:   DC.B      $09,$09,$09,$09,$09,'Press Enter to Continue: ',0
PRESSEND:   DC.B      $09,$09,$09,$09,$09,'Press Enter to End Game: ',0
  
PREAMBLE     DC.B     'Starting game text goes here',0      

              ;DC.B     $09,'::::==========:::::::::::::::::::::::::::::::::::::::::::::::::::::::',$0D,$0A
DESK:         DC.B     $09,':::::::=========::::::.---------------.:::::::::::::::::::::::::::::::',$0D,$0A
              DC.B     $09,':::=============::::::| .-----------. |:::::::::::::::::::::::::::::',$0D,$0A
              DC.B     $09,'::::==========::::::::| |  CLIMATE  | |:::::::::::::::::::::::::::::::',$0D,$0A
              DC.B     $09,':::::::========== ::::| |  URGETNT! | |:::::::::::::::::::::::::::::',$0D,$0A
              DC.B     $09,':::===========::::::::| |___________| |::::::((;):::::::::::::::::::::',$0D,$0A
              DC.B     $09,'""""============""""""|___________oo__|"")"""";(""""""""""""""""""""""',$0D,$0A
              DC.B     $09,'  ===========            ___)___(___,o  (   .---._',$0D,$0A
              DC.B     $09,'     ===========        |___________| 8  \  |TEA|_)    .+-------+.',$0D,$0A
              DC.B     $09,'  ===========                     o8o8    ) |___|    .  |_______| `.',$0D,$0A,0
              ;DC.B     $09,'  |\`=========== /|   . = --------- --`.   `.       |\ /           \ /|',$0D,$0A,0
              ;DC.B     $09,' | "-----------" |  / ooooooooooooo  oo\   _\_     | "-------------" |',$0D,$0A,0
              ;DC.B     $09,' |______I_N______| /  oooooooooooo[] ooo\  |=|     |_______OUT_______|',$0D,$0A
              ;DC.B     $09,'                   / O O =========  O OO  \ "-"   .-------,',$0D,$0A
              ;DC.B     $09,'                  `""""""""""""""""""""""       /~~~~~~~/',$0D,$0A,0
;Ref: https://www.asciiart.eu/buildings-and-places/furniture/office
BORDER:   dc.b '*******************************************************************************',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*                                                                             *',$0A, $0D
          dc.b '*******************************************************************************',0
           

AGCNAME      DC.B $09,$09,$09,$09,'WORLD CLIMATE ACTION AGENCY(WCAA)',$0A, $0D
             DC.B $09,$09,$09,$09,$09,$09,'ANNUAL REPORT',$0A, $0D,$0A,0
CURRENTGAS   DC.B $09,'Current CO2 Concentration: ',0
TIPPINGPOINT DC.B $09,$09,'Tipping Point: ',0

GLOENERGY    DC.B $09,'Global Power Needs: ',0 
CSHAMOUNT    DC.B $09,'Current Funds: ',0
PCASH        DC.B $09,$09,$09,'Current Personal Funds: ',0
PM           DC.B ' ppm',0
ANNINCREASE  DC.B $09,'Annual Increase CO2: ',0
MILLTON      DC.B ' Million tonnes',0
CARBONERG    DC.B $09,'Current Non-Green Energy Production: ',0
GREENERG     DC.B $09,'Current Green Energy Production:: ',0 
GWS          DC.B ' GWs',0 

TREEPLAN     DC.B $09,'Current Trees Planted: ',0 
TREEAB       DC.B $09,'Trees Absorbing: ',0 
TREENM       DC.B ',000 trees ',0  
CTON         DC.B ',000,000 Tonnes of Carbon per year ',0   

BOX          DC.B '+-----------+',$0A, $0D
             DC.B '|           |',$0A, $0D
             DC.B '+-----------+',$0A, $0D

EARTHPB      DC.B '              ,ggddY"""Ybbgg,,                 ',$0A, $0D
             DC.B '           ,agd888b,_ "Y8, ___`""Ybga,         ',$0A, $0D
             DC.B '   	  ,gdP""88888888baa,.""8b    "888g,      ',$0A, $0D
             DC.B ' 	    ,dP"     ]888888888P   "Y     `888Yb,    ',$0A, $0D
             DC.B '    ,dP"      ,88888888P"  db,       "8P""Yb,  ',$0A, $0D
             DC.B '  ,8"       ,888888888b, d8888a           "8,  ',$0A, $0D
             DC.B ' ,8         d88888888888,88P   8            8, ',$0A, $0D
             DC.B ',8          88888888888888PP                `8,',$0A, $0D
             DC.B 'd           I88888888888P                    `b',$0A, $0D
             DC.B '8           `8"88P""Y8P                       8',$0A, $0D
             DC.B '8            Y 8[  _ "                        8',$0A, $0D
             DC.B '8              "Y8d8b  "Y a                   8',$0A, $0D
             DC.B '8                 `""8d,   __                 8',$0A, $0D
             DC.B 'Y,                    `"8bd888b,             ,P',$0A, $0D
             DC.B '`8,                     ,d8888888baaa       ,8 ',$0A, $0D
             DC.B ' `8,                    888888888888       ,8  ',$0A, $0D
             DC.B '  `8a                   "8888888888I      a8   ',$0A, $0D
             DC.B '   `Yba                  `Y8888888P     adP    ',$0A, $0D
             DC.B '    "Yba                 `888888P    adY"      ',$0A, $0D
             DC.B '      `"Yba,             d8888P" ,adP"         ',$0A, $0D
             DC.B '	     `"Y8baa,      ,d888P,ad8P"              ',$0A, $0D
             DC.B '		      ``""YYba8888P""                    ',$0A, $0D,0
EARTHPA      DC.B $0A, $0D,$09,$09,'               000000000000',$0A, $0D
             DC.B $09,$09,'           00    0000000000000000',$0A, $0D
             DC.B $09,$09,'       000000            000000000   0',$0A, $0D
             DC.B $09,$09,'     0000000000           0000000000   0',$0A, $0D
             DC.B $09,$09,'   0000000000           0000000000000  000',$0A, $0D
             DC.B $09,$09,'  000000000           00      00000000000000',$0A, $0D
             DC.B $09,$09,' 0000000000                     0000000000000',$0A, $0D
             DC.B $09,$09,'000000000000                 00000000000000000',$0A, $0D
             DC.B $09,$09,'000000000000I             000000000000000000000',$0A, $0D
             DC.B $09,$09,'000000000000            00000000000000000000000',$0A, $0D
             DC.B $09,$09,'0000000000000   [000000000000000000000000000000',$0A, $0D
             DC.B $09,$09,'000000000000000      000 0000000000000000000000',$0A, $0D
             DC.B $09,$09,'000000000000000000      00000000000000000000000',$0A, $0D
             DC.B $09,$09,'0000000000000000000000          000000000000000',$0A, $0D
             DC.B $09,$09,'000000000000000000000000             000000000',$0A, $0D
             DC.B $09,$09,' 00000000000000000000000             00000000',$0A, $0D
             DC.B $09,$09,'  0000000000000000000000           I00000000',$0A, $0D
             DC.B $09,$09,'   0000000000000000000000           0000000',$0A, $0D
             DC.B $09,$09,'      0000000000000000000         000000',$0A, $0D
             DC.B $09,$09,'        000000000000000000       000000 ',$0A, $0D
             DC.B $09,$09,'            000000000000       00000  ',$0A, $0D
             DC.B $09,$09,'                 00000000000000  ',$0A, $0D,0

EARTHC       DC.B $0A,$0D,$09,$09,$09,'               000000000000',$0A, $0D
             DC.B $09,$09,$09,'           0088880000000000000000',$0A, $0D
             DC.B $09,$09,$09,'       0000008888888888880000000008880',$0A, $0D
             DC.B $09,$09,$09,'     00000000008888888888800000000008880',$0A, $0D
             DC.B $09,$09,$09,'   000000000088888888888000000000000088000',$0A, $0D
             DC.B $09,$09,$09,'  000000000888888888880088888800000000000000',$0A, $0D
             DC.B $09,$09,$09,' 00000000008888888888888888888880000000000000',$0A, $0D
             DC.B $09,$09,$09,'0000000000008888888888888888800000000000000000',$0A, $0D
             DC.B $09,$09,$09,'0000000000008888888888888000000000000000000000',$0A, $0D
             DC.B $09,$09,$09,'00000000000088888888888800000000000000000000000',$0A, $0D
             DC.B $09,$09,$09,'00000000000008888000000000000000000000000000000',$0A, $0D
             DC.B $09,$09,$09,'00000000000000088888800080000000000000000000000',$0A, $0D
             DC.B $09,$09,$09,'00000000000000000088888800000000000000000000000',$0A, $0D
             DC.B $09,$09,$09,'00000000000000000000008888888888000000000000000',$0A, $0D
             DC.B $09,$09,$09,'0000000000000000000000008888888888888000000000',$0A, $0D
             DC.B $09,$09,$09,' 00000000000000000000000888888888888800000000',$0A, $0D
             DC.B $09,$09,$09,'  000000000000000000000088888888888800000000',$0A, $0D
             DC.B $09,$09,$09,'   0000000000000000000000888888888880000000',$0A, $0D
             DC.B $09,$09,$09,'      0000000000000000000888888888000000',$0A, $0D
             DC.B $09,$09,$09,'        0000000000000000008888888000000 ',$0A, $0D
             DC.B $09,$09,$09,'            000000000000888888800000  ',$0A, $0D
             DC.B $09,$09,$09,'                 00000000000000  ',$0A, $0D,0
EARTHD       DC.B $0A,$0D,$09,'               000000000000',$0A, $0D
             DC.B $09,'           0088880000000000000000',$0A, $0D
             DC.B $09,'       0000008888888888880000000008880',$0A, $0D
             DC.B $09,'     00000000008888888888800000000008880',$0A, $0D
             DC.B $09,'   000000000088888888888000000000000088000',$0A, $0D
             DC.B $09,'  000000000888888888880088888800000000000000',$0A, $0D
             DC.B $09,' 00000000008888888888888888888880000000000000',$0A, $0D
             DC.B $09,'0000000000008888888888888888800000000000000000',$0A, $0D
             DC.B $09,'0000000000008888888888888000000000000000000000',$0A, $0D
             DC.B $09,'00000000000088888888888800000000000000000000000',$0A, $0D
             DC.B $09,'00000000000008888000000000000000000000000000000',$0A, $0D
             DC.B $09,'00000000000000088888800080000000000000000000000',$0A, $0D
             DC.B $09,'00000000000000000088888800000000000000000000000',$0A, $0D
             DC.B $09,'00000000000000000000008888888888000000000000000',$0A, $0D
             DC.B $09,'0000000000000000000000008888888888888000000000',$0A, $0D
             DC.B $09,' 00000000000000000000000888888888888800000000',$0A, $0D
             DC.B $09,'  000000000000000000000088888888888800000000',$0A, $0D
             DC.B $09,'   0000000000000000000000888888888880000000',$0A, $0D
             DC.B $09,'      0000000000000000000888888888000000',$0A, $0D
             DC.B $09,'        0000000000000000008888888000000 ',$0A, $0D
             DC.B $09,'            000000000000888888800000  ',$0A, $0D
             DC.B $09,'                 00000000000000  ',$0A, $0D,0
PALEBLUEDOT  DC.B $09,'Earth. Thats here. Thats home. Thats us. On it everyone you love,',$0A, $0D
             DC.B $09,'everyone you know, every human being who ever was, lived out their ',$0A, $0D
             DC.B $09,'lives.The aggregate of our joy and suffering and every saint and sinner',$0A, $0D
             DC.B $09,' lived here - on a mote of dust suspended in a sunbeam - Carl Sagan',$0A, $0D,$0A, $0D,0
BOX2         DC.B '+-----------------------------------------------------------------------------+',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '|                                                                             |',$0A, $0D
             DC.B '+-----------------------------------------------------------------------------+',0
                         
CASH:         DC.L  $2020

maze      dc.b '*******************************************************************************',$0A, $0D
          dc.b '* Time Remaining:                                                             *',$0A, $0D
          dc.b '*******************************************************************************',$0A, $0D
          dc.b '*      +-----------------------------+----------------------------------+     *',$0A, $0D                             
          dc.b '*      |                             |                                  |     *',$0A, $0D
		  dc.b '*      |    +    +    +---------     |     ------------------------+    |     *',$0A, $0D
		  dc.b '*      |    |    |    |              |                             |    |     *',$0A, $0D
		  dc.b '*      |    |    +----+--------------+    +-------------------+    |    |     *',$0A, $0D
		  dc.b '*      |    |                             |                   |    |    |     *',$0A, $0D
	      dc.b '*      |    +--------------+--------------+----     +---------+    |    |     *',$0A, $0D
		  dc.b '*      |    |              |                        |         |    |    |     *',$0A, $0D
		  dc.b '*      |    +---------     |    +    +---------+    |    +    |    +----+     *',$0A, $0D
		  dc.b '*      |    |              |    |    |         |    |    |    |         |     *',$0A, $0D
		  dc.b '*      |    |     ---------+    |    +----     |    |    |    |    +    |     *',$0A, $0D
		  dc.b '*      |    |                   |    |         |    |    |    |    |    |     *',$0A, $0D
	      dc.b '*      |    +-------------------+    |     ----+    |    |    |    |    |     *',$0A, $0D
		  dc.b '*      |                             |              |    |    |    |    |     *',$0A, $0D
	      dc.b '*      :--------------+---------+    +--------------+    |    +----+    |     *',$0A, $0D                                                                    
          dc.b '*      |              |         |    |                   |    |         |     *',$0A, $0D
          dc.b '*      |    +    +    |    +    |    |    +--------------+    +----     |     *',$0A, $0D
          dc.b '*      |    |    |    |    |    |    |    |              |              |     *',$0A, $0D
          dc.b '*      |    |    |    +    |    |    |    |     ---------+---------+    |     *',$0A, $0D
          dc.b '*      |    |    |         |    |    |    |                        |    |     *',$0A, $0D
		  dc.b '*      |    |    +---------+----+    |    |    +---------+    +    |    |     *',$0A, $0D
		  dc.b '*      |    |                        |    |    |         |    |    |    |     *',$0A, $0D
		  dc.b '*      |    +---------+--------------+    |    |    +    |    |    |    |     *',$0A, $0D
		  dc.b '*      |    |         |              |    |    |    |    |    |    |    |     *',$0A, $0D
		  dc.b '*      |    +    +    +---------     |    |    +----+    |    +----+    |     *',$0A, $0D
		  dc.b '*      |         |                   |    |              |              |     *',$0A, $0D
		  dc.b '*      +---------+-------------------+    +--------------+--------------+     *',$0A, $0D
          dc.b '*******************************************************************************',$0A, $0D,0
;Ref: https://www.asciiart.eu/art-and-design/mazes
MAIL      dc.b $0A, $0D,$09,$09,$09,$09,$09,'       ..--""|',$0A, $0D
          dc.b $09,$09,$09,$09,$09,'       |     |',$0A, $0D
          dc.b $09,$09,$09,$09,$09,'       | .---.',$0A, $0D
          dc.b $09,$09,$09,$09,$09,' (\-.--| |---------.',$0A, $0D                             
          dc.b $09,$09,$09,$09,$09,'/ \) \ | |          \',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'|:.  | | |           |',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'|:.  | |o|           |',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'|:.  | `"`           |',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'|:.  |_ __  __ _  __ /',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'`""""`""|=`|"""""""`',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'        |=_|',$0A, $0D
		  dc.b $09,$09,$09,$09,$09,'        |= |',$0A, $0D,0
;Ref: https://www.asciiart.eu/miscellaneous/mailbox
STYTEXT   dc.b $0A, $0D,'+******************************************************************************+',$0A, $0D
          dc.b '|                               You have mail!                                 |',$0A, $0D
          dc.b '+******************************************************************************+',$0A, $0D,$0A, $0D
          dc.b $09,'Due to human greed and apathy the climate is on the brink of crisis. All ',$0A, $0D
          dc.b $09,'around the world, reckless consumption pushes us closer to the edge of',$0A, $0D
          dc.b $09,'disaster. World leaders gather to discuss how to meet Goal 13 or at least',$0A, $0D
          dc.b $09,'how they can appear to be doing something.',$0A, $0D,$0A, $0D
          dc.b $09,'They decide to form a special world agency with powers to address the',$0A, $0D
          dc.b $09,'problem. It is to be headed by the most qualified candidate in the world,',$0A, $0D
          dc.b $09,'a person with genius intellect, magnetic charisma and inpeccable ethics.',$0A, $0D
          dc.b $09,'Unfortunatly, due to a computer error, the invitation was sent to',$0A, $0D
          dc.b $09,'you instead...God help us',$0A, $0D,0
BUILDING  dc.b $0A, $0D,$0A, $0D
          dc.b $09,'                     \  |  /         ___________                 ',$0A, $0D
          dc.b $09,'      ____________  \ \_# /         |  ___      |       _________',$0A, $0D
          dc.b $09,'     |      _     |   \\#           | |`v |     |      |  __  _  |',$0A, $0D                             
          dc.b $09,'     |  _  |_|    |    \#  //       |  --- ___  |      | |__||_| |',$0A, $0D
		  dc.b $09,'     | |_|        |    \# |+ ++|  | |  |~~~~~~| |      |  __  _  |',$0A, $0D
		  dc.b $09,'     |            |    \# |+ ++|  | |  |~~~~~~| |      | |__||_| |',$0A, $0D
		  dc.b $09,'   ~~|    (~~~~~) |~~~~~#~| H  |_ |~|  | |||| | |~~~~~~|         |',$0A, $0D
		  dc.b $09,'     ~~~~~~~~~~~~~________/  /_____ |  | |||| | |      | ||||||| |',$0A, $0D
          dc.b $09,'                                    ~~~~~~~~~~~~~      | ||||||| |',$0A, $0D,0  
;Ref: https://www.asciiart.eu/buildings-and-places/cities

FOREST    dc.b $0A, $0D,$09,$09,$09,$09,' 	 	     ,@@@@@@@,                 ',$0A, $0D
          dc.b $09,$09,$09,$09,'   ,,,.   ,@@@@@@/@@,  .oo8888o.       ',$0A, $0D
          dc.b $09,$09,$09,$09,' &%%&%&&%,@@@@@/@@@@@@,8888\88/8o      ',$0A, $0D
          dc.b $09,$09,$09,$09,',%&\%&&%&&%,@@@\@@@/@@@88\88888/88     ',$0A, $0D
          dc.b $09,$09,$09,$09,'%&&%&%&/%&&%@@\@@/ /@@@88888\88888     ',$0A, $0D
          dc.b $09,$09,$09,$09,'%&&%/ %&%%&&@@\ V /@@  `88\8 `/88      ',$0A, $0D
          dc.b $09,$09,$09,$09,'`&%\ ` /%&     |.|        \  |8        ',$0A, $0D
          dc.b $09,$09,$09,$09,'    |o|        | |         | |         ',$0A, $0D
          dc.b $09,$09,$09,$09,'    |.|        | |         | |         ',$0A, $0D
          dc.b $09,$09,$09,$09,' \\/ ._\//_/__/  ,\_//__\\/.  \_//__/_ ',$0A, $0D,0  
;Ref: https://asciiart.website/index.php?art=plants/trees
FORESTG    dc.b $0A, $0D,$09,$09,$09,$09,' 	 	     ,@@@@@@@,                   ',$0A, $0D
          dc.b $09,$09,$09,'   ,,,.   ,@@@@@@/@@,  .oo8888o.                 ',$0A, $0D
          dc.b $09,$09,$09,' &%%&%&&%,@@@@@/@@@@@@,8888\88/8o                ',$0A, $0D
          dc.b $09,$09,$09,',%&\%&&%&&%,@@@\@@@/@@@88\88888/88               ',$0A, $0D
          dc.b $09,$09,$09,'%&&%&%&/%&&%@@\@@/ /@@@88888\88888               ',$0A, $0D
          dc.b $09,$09,$09,'%&&%/ %&%%&&@@\ V /@@  `88\8 `/88                ',$0A, $0D
          dc.b $09,$09,$09,'`&%\ ` /%&     |.|        \  |8                  ',$0A, $0D
          dc.b $09,$09,$09,'    |o|        | |         | |        \o/   o      ',$0A, $0D
          dc.b $09,$09,$09,'    |.|        | |         | |         |  -¬||     ',$0A, $0D
          dc.b $09,$09,$09,' \\/ ._\//_/__/  ,\_//__\\/.  \_//__/__/\___/\_______',$0A, $0D,0           

VOLCANO  dc.b $09,$09,'                     ooOOOo                                  ',$0A, $0D
         dc.b $09,$09,'                   oOOOOOOoooo                               ',$0A, $0D
         dc.b $09,$09,'                 ooOOOooo  oooo                              ',$0A, $0D
         dc.b $09,$09,'                /vvv\                                        ',$0A, $0D
         dc.b $09,$09,'               /  V V\                                       ',$0A, $0D
         dc.b $09,$09,'              /   V  V\                                      ',$0A, $0D
         dc.b $09,$09,'             /     V   \          AAAAH!  RUN FOR YOUR LIVES!',$0A, $0D
         dc.b $09,$09,'            /      VV   \               /                    ',$0A, $0D				
		 dc.b $09,$09,' ____      /        VVV    \   	  o          o              ',$0A, $0D
         dc.b $09,$09,' /\      /        VVVV     \     /-   o     /-               ',$0A, $0D				
		 dc.b $09,$09,'/  \   /           VVVVVVV   \  /\  -/-    /\                ',$0A, $0D
         dc.b $09,$09,'                     VVVVVVVVVVVVV   /\                      ',$0A, $0D,0
;Ref: https://ascii.co.uk/art/volcano	
VOLCANOS dc.b $09,$09,'                                                             ',$0A, $0D
         dc.b $09,$09,'                                                             ',$0A, $0D
         dc.b $09,$09,'                                                             ',$0A, $0D
         dc.b $09,$09,'                /\_/\                                        ',$0A, $0D
         dc.b $09,$09,'               /     \                                       ',$0A, $0D
         dc.b $09,$09,'              /       \                                      ',$0A, $0D
         dc.b $09,$09,'             /         \                                     ',$0A, $0D
         dc.b $09,$09,'            /           \      __   __    __   __            ',$0A, $0D				
		 dc.b $09,$09,' ____      /             \    /@@\ /@;\  /@;\ /@@\           ',$0A, $0D
         dc.b $09,$09,' /\      /                \   \;@/ \;@/  \@@/ \@@/           ',$0A, $0D				
		 dc.b $09,$09,'/  \   /                   \   ||   ||    ||   ||                            ',$0A, $0D
         dc.b $09,$09,'                                                             ',$0A, $0D,0
NUCPLANT dc.b $09,$09,'          ) ) )                     ) ) )                 ',$0A, $0D
         dc.b $09,$09,'        ( ( (                      ( ( (                  ',$0A, $0D
         dc.b $09,$09,'      ) ) )                       ) ) )                   ',$0A, $0D
         dc.b $09,$09,'   (~~~~~~~~~)                 (~~~~~~~~~)                ',$0A, $0D
         dc.b $09,$09,'    | POWER |                   | POWER |                 ',$0A, $0D
         dc.b $09,$09,'    |       |                   |       |                 ',$0A, $0D
         dc.b $09,$09,'    I      _._                  I       _._               ',$0A, $0D
         dc.b $09,$09,'    I    /    `\                I     /    `\             ',$0A, $0D
         dc.b $09,$09,'    I   |   N   |               I    |   N   |			 ',$0A, $0D		
		 dc.b $09,$09,'    f   |   |~~~~~~~~~~~~~~|    f    |    |~~~~~~~~~~~~~~|',$0A, $0D
         dc.b $09,$09,'  ./    |   ||~~~~~~~~|    |  ./     |    | |~~~~~~~~|   |',$0A, $0D					
		 dc.b $09,$09,'//______|___||__###___|____|//_______|____|_|__###___|___|',$0A, $0D,0  
;Ref: https://www.asciiart.eu/buildings-and-places/other 
OILRIGA  dc.b $09,$09,$09,$09,'              /\                    .    ',$0A, $0D
         dc.b $09,$09,$09,$09,'             /\/\                 .:;:.  ',$0A, $0D
         dc.b $09,$09,$09,$09,'            _,/\,_              .:;;;;;:.',$0A, $0D
         dc.b $09,$09,$09,$09,'           ,______,               ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'            ,\/\/,                ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'           \,/\/\,   ...`/:       ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   :\...    \`.. ,  ,,  /  :      ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   : \ ,,   ,\ ,,,  ,, /    o     ;:;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   j _\,,__/__\,,_\_,,/___        :;;;;  ',$0A, $0D	
		 dc.b $09,$09,$09,$09,'     ,___________________,        ;;;:;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'      ,  ,   ,   ,   ,  ,         ;;:;;  ',$0A, $0D	
		 dc.b $09,$09,$09,$09,'~~~~~~,~~,~~~,~~~,~~~,~~,~~~~~~   ;;;;;  ',$0A, $0D,0    
OILRIGB  dc.b $09,$09,$09,$09,'              /\                  ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'             /\/\                 ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'            _,/\,_                ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'           ,______,               ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'            ,\/\/,                ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'           \,/\/\,   ...`/:       ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   :\...    \`.. ,  ,,  /  :      ;;;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   : \ ,,   ,\ ,,,  ,, /    o     ;:;;;  ',$0A, $0D
         dc.b $09,$09,$09,$09,'   j _\,,__/__\,,_\_,,/___       ::;;;;: ',$0A, $0D	
		 dc.b $09,$09,$09,$09,'     ,___________________,        :;;::  ',$0A, $0D
         dc.b $09,$09,$09,$09,'      ,  ,   ,   ,   ,  ,          :;:   ',$0A, $0D	
		 dc.b $09,$09,$09,$09,'~~~~~~,~~,~~~,~~~,~~~,~~,~~~~~~     .    ',$0A, $0D,0 
;Ref: https://www.asciiart.eu/buildings-and-places/other	 	
WHISKEY  dc.b $09,$09,$09,$09,'               __    ',$0A, $0D
         dc.b $09,$09,$09,$09,'              |__|   ',$0A, $0D
         dc.b $09,$09,$09,$09,'              |  |   ',$0A, $0D
         dc.b $09,$09,$09,$09,'              |  |   ',$0A, $0D
         dc.b $09,$09,$09,$09,'              |  |   ',$0A, $0D
         dc.b $09,$09,$09,$09,'              |  |   ',$0A, $0D
         dc.b $09,$09,$09,$09,' ,----.      /`-. \  ',$0A, $0D
         dc.b $09,$09,$09,$09,'(      )    /-._|  \ ',$0A, $0D
         dc.b $09,$09,$09,$09,'|`---- |   |        |',$0A, $0D	
		 dc.b $09,$09,$09,$09,'\      /   |`-...   |',$0A, $0D
         dc.b $09,$09,$09,$09,' `.  ,;    |; |  |  |',$0A, $0D	
		 dc.b $09,$09,$09,$09,'   ||      |`XXX |  |',$0A, $0D
         dc.b $09,$09,$09,$09,' ,-||-.    |`-...|  |',$0A, $0D
         dc.b $09,$09,$09,$09,'(  ''   )   |        |',$0A, $0D 
         dc.b $09,$09,$09,$09,' `----      `-....-   ',$0A, $0D,0    
;Ref:    https://asciiart.website/index.php?art=objects/bottles
SUN      dc.b $0A,$0D,$0A,$0D,$09,$09,$09,$09,$09,'	     ;   :   ;        ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   .   \_,!,_/   ,     ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    `.,.88888`.,/      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'     /888888888\       ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'~ --:8888888888 : -- ~ ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'     \888888888/       ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    ,/`.888888/`.      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   /   / `!` \   `     ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'      ;   :   ;        ',$0A, $0D,0
;Ref :   https://www.asciiart.eu/nature/sun
WMILL    dc.b $09,$09,$09,$09,'            ,-_                  (`  ).    ',$0A, $0D
         dc.b $09,$09,$09,$09,'            |-_.-,              (     ).   ',$0A, $0D
         dc.b $09,$09,$09,$09,'            |-_.-.           _(        .`. ',$0A, $0D
         dc.b $09,$09,$09,$09,'   _        |-_./        .=(`(      .     )',$0A, $0D
         dc.b $09,$09,$09,$09,'  /;-,_     |-_.        (     (.__.:-`-_.. ',$0A, $0D
         dc.b $09,$09,$09,$09,' /-.-;,-,___|.          `(       ) )       ',$0A, $0D
         dc.b $09,$09,$09,$09,'/;-;-;-;_;_/|\_ _ _ _ _   ` __.:.   )      ',$0A, $0D
         dc.b $09,$09,$09,$09,'     ( __`|_P_|`-;-;-;,|        `--.       ',$0A, $0D
         dc.b $09,$09,$09,$09,'      \    |||   `-;-;-.                   ',$0A, $0D
         dc.b $09,$09,$09,$09,'       \   -_|      \-/                    ',$0A, $0D
         dc.b $09,$09,$09,$09,'        \ /-_|                             ',$0A, $0D
         dc.b $09,$09,$09,$09,'         ,.-_|                             ',$0A, $0D
         dc.b $09,$09,$09,$09,'         |.-_|                             ',$0A, $0D 
         dc.b $09,$09,$09,$09,'         |  |                              ',$0A, $0D
         dc.b $09,$09,$09,$09,'         |  |                              ',$0A, $0D
         dc.b $09,$09,$09,$09,'	        |  |                              ',$0A, $0D,0	
;Ref :   https://www.asciiart.eu/buildings-and-places/windmills
STORM    dc.b $09,$09,$09,$09,$09,'   ((  ,####@@!!$$    ))              ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'       `#####@@!$$`  ))               ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    ((  .####@!!$:                    ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'   ((  ,####@!!$:   ))                ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'       .###@!!$:                      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'       `##@@!$:                       ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'        `#@!!$                        ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'  !@#    `#@!$:       @#$       \ /   ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   #$     `#@!$:       !@!       X    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'            .@!$:               /|\   ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'        .`\   ,!$: /`.           |    ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'           .\  .!: /.            |    ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'             ,\ : /,             |    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'  -.,-/\\\-.,//.-,/:`\.,-.JrS,.,-=_\\ ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,', -.,-.\\,-.,//.-,.`-.,_\\-.,.-\,.-// ',$0A, $0D,0 
;Ref: https://www.asciiart.eu/nature/tornado 
ATOM      
         dc.b $09,$09,$09,'                    ¦¦                 ',$0A, $0D  
         dc.b $09,$09,$09,'                 ¦¦    ¦¦                 ',$0A, $0D  
         dc.b $09,$09,$09,'  ¦¦¦¦¦        ¦¦         ¦¦    ¦¦¦¦   ',$0A, $0D
         dc.b $09,$09,$09,' ¦¦     ::  ¦¦¦¦¦          ¦¦::     ¦¦  ',$0A, $0D
         dc.b $09,$09,$09,'  ¦¦          ¦¦¦¦      ¦¦¦¦          ¦¦   ',$0A, $0D
         dc.b $09,$09,$09,'  ¦¦        ¦¦    ¦¦  ¦¦    ¦¦        ¦¦   ',$0A, $0D
         dc.b $09,$09,$09,'    ¦       ¦¦              ¦¦      ¦¦     ',$0A, $0D  
         dc.b $09,$09,$09,'     ¦¦     ¦¦¦¦    ¦¦     ¦¦¦¦   ¦¦       ',$0A, $0D  
         dc.b $09,$09,$09,'         ¦¦ ¦¦    ¦¦¦¦¦¦    ¦¦¦            ',$0A, $0D
         dc.b $09,$09,$09,'     ¦¦¦¦   ¦¦¦¦    ¦¦     ¦¦¦¦   ¦¦       ',$0A, $0D    
         dc.b $09,$09,$09,'    ¦       ¦¦              ¦¦      ¦¦     ',$0A, $0D  
         dc.b $09,$09,$09,'  ¦¦      ::¦¦    ¦¦¦¦  ¦¦  ¦¦       ¦¦    ',$0A, $0D
         dc.b $09,$09,$09,' ¦¦    ::     ¦¦¦¦        ¦¦  ¦¦     ¦¦   ',$0A, $0D
         dc.b $09,$09,$09,'  ¦¦¦¦¦        ¦¦         ¦¦    ¦¦¦¦¦   ',$0A, $0D
         dc.b $09,$09,$09,'                 ¦¦    ¦¦                ',$0A, $0D
         dc.b $09,$09,$09,'                    ¦¦                     ',$0A, $0D,0  
;Ref: https://textart.sh/topic/atom 
GAVEL    dc.b $09,$09,$09,$09,$09,'   ___________                          ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'   \         /                          ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    )_______(                           ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    |||||||||_.-._|.---------.|_.-._    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    |       | | |               | | ..-.',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    |       |_| |_             _| |_..-.',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    |_______| .-. `.---------.` .-.     ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'    )|||||||(                           ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   /_________\                          ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   `.-------.`                          ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,' .-------------.                        ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'/_______________\        	           ',$0A, $0D,0 
;Ref: https://ascii.co.uk/art/gavel   
JUDGE    dc.b $09,$09,$09,$09,$09,'       ___                                     ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'      /_ _\                                    ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'     ( - - )                                   ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    __\ _ /__                                  ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'   /   \_/   \                     __          ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'__/___________\__                  //\\        ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|     JUDGE      |\    ____       (//\\)       ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|   I.L. Ovtree  ||   / ___)      _\__/_       ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|                ||   (.~O=O     /      \      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|                ||   _\_-/     / /    \ \     ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'|                ||  /  \|/\    \_\     |/     ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'|                ||_/_|____|\__  (/_____|      ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'|                |   WITNESS  |\  | _ _ |      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|                |            ||__|  |  |_____ ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'|________________|____________|/  |__|__|      ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'                                 (__/ \__)     ',$0A, $0D,0
;Ref: https://ascii.co.uk/aart/law 
EURO     dc.b $09,$09,$09,$09,$09,'           _.-------._    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'        _-;_.------._ `-_ ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'      _- _-          `-_/ ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'     -  -                 ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,' ___/  /______________    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'/___  .______________/    ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,' ___| |_____________      ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'/___  .____________/      ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    \  \                  ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'     -_ -_             /| ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'       -_ -._        _- | ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'          -._ `------;_./ ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'            `-------;     ',$0A, $0D,0 	
;Ref:  https://www.asciiart.eu/miscellaneous/money
ARROW    dc.b $09,$09,$09,$09,$09,$09,$09,'     .    ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   .:;:.  ',$0A, $0D     
         dc.b $09,$09,$09,$09,$09,$09,$09,' .:;;;;;:.',$0A, $0D     
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D     
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;:;;;  ',$0A, $0D     
         dc.b $09,$09,$09,$09,$09,$09,$09,'   :;;;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;:;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;:;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D   
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,$09,'   ;;;;;  ',$0A, $0D,0 
;Ref: https://ascii.co.uk/art/arrow

RIOT     dc.b $09,$09,$09,'         /....\          /....\          /....\         ',$0A, $0D   
         dc.b $09,$09,$09,' .-.    |::::::|    .-. |::::::|    .-. |::::::|        ',$0A, $0D   
         dc.b $09,$09,$09,' | |    |::::::|    | | |::::::|    | | |::::::|        ',$0A, $0D   
         dc.b $09,$09,$09,' | |    (`:  : )    | | (`:  : )    | | (`:  : )        ',$0A, $0D   
         dc.b $09,$09,$09,' | |   _--|__|--__  | |.--|__|--__  | |_--|__|--__      ',$0A, $0D   
         dc.b $09,$09,$09,' | |  |   ________|_|_|_  ________|_|_|_  ________|_____',$0A, $0D   
         dc.b $09,$09,$09,' | | /    |            |  |            |  |            |',$0A, $0D   
         dc.b $09,$09,$09,' | |/  /  |            |  |            |  |            |',$0A, $0D   
         dc.b $09,$09,$09,' |_| |/|  |            |  |            |  |            |',$0A, $0D   
         dc.b $09,$09,$09,'(===)| |  |   POLICE   |  |   POLICE   |  |   POLICE   |',$0A, $0D   
         dc.b $09,$09,$09,'`===   |`-|            |`-|            |`-|            |',$0A, $0D   
         dc.b $09,$09,$09,' | |   |`-|            |`-|            |`-|            |',$0A, $0D   
         dc.b $09,$09,$09,' |_|   |  |            |  |            |  |            |',$0A, $0D  
         dc.b $09,$09,$09,'       |___`-__________- __`-__________- __`-__________-',$0A, $0D,$0A, $0D,0
;Ref: https://www.asciiart.eu/people/occupations/police   
NUC      dc.b $09,$09,$09,'          ____/ (  (    )   )  \___          ',$0A, $0D 
         dc.b $09,$09,$09,'        /( (  (  )   _    ))  )   )\         ',$0A, $0D 
         dc.b $09,$09,$09,'      ((     (   )(    )  )   (   )  )       ',$0A, $0D 
         dc.b $09,$09,$09,'    ((/  ( _(   )   (   _) ) (  () )  )      ',$0A, $0D 
         dc.b $09,$09,$09,'   ( (  ( (_)   ((    (   )  .((_ ) .  )_    ',$0A, $0D 
         dc.b $09,$09,$09,'  ( (  )    (      (  )    )   ) . ) (   )   ',$0A, $0D 
         dc.b $09,$09,$09,'( (  ( \ ) (    (_  ( ) ( )  )   ) )  )) ( ) ',$0A, $0D 
         dc.b $09,$09,$09,'( (  ( (  (  )     (_  )  ) )  _)   ) _( ( ) ',$0A, $0D 
         dc.b $09,$09,$09,' ((  (   )(    (     _    )   _) _(_ (  (_ ) ',$0A, $0D 
         dc.b $09,$09,$09,'  (_((__(_(__(( ( ( |  ) ) ) )_))__))_)___)  ',$0A, $0D 
         dc.b $09,$09,$09,'  ((__)        \\||lll|l||///          \_))  ',$0A, $0D
         dc.b $09,$09,$09,'           (   /(/ (  )  ) )\   )            ',$0A, $0D  
         dc.b $09,$09,$09,'         (    ( ( ( | | ) ) )\   )           ',$0A, $0D  
         dc.b $09,$09,$09,'          (   /(| / ( )) ) ) )) )            ',$0A, $0D  
         dc.b $09,$09,$09,'        (        |(||(||)||||        )       ',$0A, $0D                    
         dc.b $09,$09,$09,'        (     //|/l|||)|\\ \     )           ',$0A, $0D  
         dc.b $09,$09,$09,'        (/ / //  /|//||||\\  \ \  \ _)       ',$0A, $0D ,0 
;Ref: https://www.asciiart.eu/weapons/explosives
         
ROLLD    dc.b $09,$09,$09,$09,$09,$09,'     _______     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    /O     /\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   /   O  /O \   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'((/_____O/    \  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   \O    O\ O/   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  ((             ',$0A, $0D,0 
ROLLD1   dc.b $09,$09,$09,$09,$09,$09,'     _______     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    /      /\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   /  O   /O \   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'((/_____ /    \  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   \  O  O\ O/   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  ((             ',$0A, $0D,0
ROLLD2   dc.b $09,$09,$09,$09,$09,$09,'     _______     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    /O     /\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   /      /  \   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'((/_____O/ O  \  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   \      \  /   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  ((             ',$0A, $0D,0 
 
                                                  
SIXD	 dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o o o |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o o o |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0 

FIVED	 dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o   o |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|   o   |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o   o |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0 
                                           
FOURD	 dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o   o |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o   o |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0 
                                            
THREED   dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o o o |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0 
                                          
TWOD	 dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|   o   |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|   o   |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0  

ONED	 dc.b $09,$09,$09,$09,$09,$09,$09,' _______ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|   o   |',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,$09,' ------- ',$0A, $0D,0 		 
		 
DIE      dc.b $09,$09,$09,$09,$09,$09,$09,'  .-------.  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,' /   o   /| ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'/_______/o| ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o o o | | ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'|       |o/ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,'| o o o |/  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,$09,' -------    ',$0A, $0D,0  
;Ref:  https://www.asciiart.eu/miscellaneous/dice
SHAM     dc.b $09,$09,'                    ***          ***                    ',$0A, $0D  
         dc.b $09,$09,'                 ***....**     **...***                 ',$0A, $0D  
         dc.b $09,$09,'                **........** **.......**                ',$0A, $0D  
         dc.b $09,$09,'         ***    **..........*.........**    ***         ',$0A, $0D 
         dc.b $09,$09,'      **.....**  **..................**  **.....**      ',$0A, $0D 
         dc.b $09,$09,'    **.........**  **..............**  **.........**    ',$0A, $0D 
         dc.b $09,$09,'   *..............*   *..........*   *..............*   ',$0A, $0D 
         dc.b $09,$09,'    **..............*   *......*   *..............**    ',$0A, $0D 
         dc.b $09,$09,'      **..............** *....* **..............**      ',$0A, $0D 
         dc.b $09,$09,'        *......................................*        ',$0A, $0D 
         dc.b $09,$09,'      **..............**........**..............**      ',$0A, $0D 
         dc.b $09,$09,'    **..............*    *....*....*..............**    ',$0A, $0D 
         dc.b $09,$09,'   *..............*    *........* ...*..............*   ',$0A, $0D 
         dc.b $09,$09,'    **.........**    *............* ...**.........**    ',$0A, $0D
         dc.b $09,$09,'      **.....**   **...............**....**.....**      ',$0A, $0D  
         dc.b $09,$09,'         ***    **...................**.....***         ',$0A, $0D  
         dc.b $09,$09,'              **...........*...........**....*          ',$0A, $0D  
         dc.b $09,$09,'               **.........* *.........** *......*..*..* ',$0A, $0D   
         dc.b $09,$09,'                 *......**   **......*     *........*   ',$0A, $0D  
         dc.b $09,$09,'                   **  *       * **           *...*     ',$0A, $0D ,0
;Ref: https://www.asciiart.eu/holiday-and-events/luck 
POLAR    dc.b $09,$09,$09,'  _,-""`""-~`)                             ',$0A, $0D  
         dc.b $09,$09,$09,'(`~_,=========\                            ',$0A, $0D 
         dc.b $09,$09,$09,' |---,___.-.__,\                           ',$0A, $0D 
         dc.b $09,$09,$09,' |        o     \ ___  _,,,,_     _.--.    ',$0A, $0D 
         dc.b $09,$09,$09,'  \      `^`    /`_.-"~      `~-;`     \   ',$0A, $0D 
         dc.b $09,$09,$09,'   \_      _  .                  `,     |  ',$0A, $0D 
         dc.b $09,$09,$09,'     |`-                           \ __/   ',$0A, $0D 
         dc.b $09,$09,$09,'    /                      ,_       \  ` -.',$0A, $0D  
         dc.b $09,$09,$09,'   /    .-""~~--.            `"-,   ;_    /',$0A, $0D 
         dc.b $09,$09,$09,'  |              \               \  | `""` ',$0A, $0D 
         dc.b $09,$09,$09,'   \__.-- `"-.   /_               |        ',$0A, $0D 
         dc.b $09,$09,$09,'              `"`  `~~~---..,     |        ',$0A, $0D
         dc.b $09,$09,$09,'                             \ _.- `-.     ',$0A, $0D  
         dc.b $09,$09,$09,'                              \       \    ',$0A, $0D  
         dc.b $09,$09,$09,'                                .     /    ',$0A, $0D  
         dc.b $09,$09,$09,'                                 `"~"`	  ',$0A, $0D,0
;Ref: https://www.asciiart.eu/animals/bears
SSUN     dc.b $09,$09,$09,$09,$09,'            :             ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'   `.       ;        ..   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'     `.  .-"""-.   ..     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'       ;.  __   _;.       ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'      /   /_    _`\       ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'     |  _( a (  a  |      ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'.....| (_)    >    |``````',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'      \    \    / /       ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'       `.   `--...        ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'      ./ `-,,,-. `.       ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'    ./      :      `.     ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,'                          ',$0A, $0D,0
;Ref:        	https://www.asciiart.eu/nature/sun 

FLAME    dc.b $09,$09,$09,$09,$09,'          \/ ,".). .,     \/.  ,               ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'     .  . , .|// / (/ ,.   . ,\(   /           ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'   .    |\/ \|  \\,. ,... ||  \\,  .           ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'   , . .| \_ |\/ |#\_/#| /_|\_ |#\_/,) .,. ,   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,' #|(,   )  \\\#\ \##/  ||/ \#\  \###/\ \/ \/. ,',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,' || ) | \ |/ /#/ |#( \|\\|  |#/ /##(  )|  \\;/ ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'  \ |.\\ |\_/#| /#)|##\ \\_/#| |######\ \\/#|  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'   \\_/# |#\##/#                    #@#\ /#/   ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'    ##/#  ##\/#@####@@###V###\/#@####@@#Y###   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'  :Y##@@##@##@@###@@@###@##@@###@@@###@####:   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,' :;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:  ',$0A, $0D,0 
;https://www.asciiart.eu/miscellaneous/fire-extinguishers
ROCKET1      DC.B '|\________ ',0
ROCKET2      DC.B '| ________)',0
ROCKET3      DC.B '|/         ',0
EXHAUST1      DC.B ' - -\ \',0
EXHAUST2     DC.B '- - = =',0
EXHAUST3     DC.B ' - -/ /',0
BROCKET1      DC.B '           ',0
BROCKET2      DC.B '           ',0
BROCKET3      DC.B '           ',0
BEXHAUST1      DC.B '       ',0
BEXHAUST2     DC.B '       ',0
BEXHAUST3     DC.B '       ',0

ELON     dc.b $09,$09,$09,$09,$09,$09,'      ___      ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'     /___\     ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'    |=   =|    ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'    |     |    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'   /|##!##|\   ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,$09,'  / |##!##| \  ',$0A, $0D
         dc.b $09,$09,$09,$09,$09,$09,' /  |##!##|  \ ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'|  /       \  |',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'| /         \ |',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,$09,'|/           \|',$0A, $0D,0  
;Ref: https://www.asciiart.eu/space/spaceships
         
VIC      dc.b $09,$09,$09,'____   ____.__        __                       ',$0A, $0D 
         dc.b $09,$09,$09,'\   \ /   /|__| _____/  |_  ___________ ___.__.',$0A, $0D 
         dc.b $09,$09,$09,' \   Y   / |  |/ ___\   __\/  _ \_  __ <   |  |',$0A, $0D 
         dc.b $09,$09,$09,'  \     /  |  \  \___|  | (  <_> )  | \/\___  |',$0A, $0D 
         dc.b $09,$09,$09,'   \___/   |__|\___  >__|  \____/|__|   / ____|',$0A, $0D 
         dc.b $09,$09,$09,'                   \/                   \/     ',$0A, $0D,0
R101      dc.b $09,$09,$09,$09,'______                        __  _____  __  ',$0A, $0D 
         dc.b $09,$09,$09,$09,'| ___ \                      /  ||  _  |/  | ',$0A, $0D 
         dc.b $09,$09,$09,$09,'| |_/ /___   ___  _ __ ___   `| || |/  |`| | ',$0A, $0D 
         dc.b $09,$09,$09,$09,'|    // _ \ / _ \|  _ ` _ \   | ||  /| | | | ',$0A, $0D 
         dc.b $09,$09,$09,$09,'| |\ \ (_) | (_) | | | | | | _| |\ |_/ /_| |_',$0A, $0D 
         dc.b $09,$09,$09,$09,'\_| \_\___/ \___/|_| |_| |_| \___/\___/ \___/',$0A, $0D,0

LIGHT    dc.b $09,$09,$09,$09,$09,' .-=-.-)  ) -. ',$0A, $0D  
         dc.b $09,$09,$09,$09,$09,'/   (  .  .   \',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'\ (   ,_) ) \_/',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,' (_ , /\  ,_/  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'    --\ `\--`  ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'      _\ _\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'      `\ \     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'       _\_\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'       `\\     ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'         \\    ',$0A, $0D 
         dc.b $09,$09,$09,$09,$09,'     -. .`\. .-',$0A, $0D,0
;Ref:   https://www.asciiart.eu/nature/lightning

CHEAT         DC.B    $09,'I dont know how you found this place but use your ',$0A, $0D
              DC.B    $09,'powers wisely!!!',$0A, $0D,$0A, $0D
              DC.B    $09,'   1. Set Carbon in air to 0',$0D,$0A
              DC.B    $09,'   2. Set Personal cash to 500000',$0D,$0A
              DC.B    $09,'   3. Set Carbon in air to 40000',$0D,$0A
              DC.B    $09,'   4. Set Carbon in air to 30000',$0D,$0A
              DC.B    $09,'   5. Set Carbon in air to 20000',$0D,$0A
              DC.B    $09,'   6. Set Carbon in air to 10000',$0D,$0A
              DC.B    $09,'   7. Subtract 1000GW Non-Green Power',$0D,$0A
              DC.B    $09,'   8. Add 1000GW Green Power',$0D,$0A
              DC.B    $09,'   9. Set ppm to 476',$0D,$0A
              DC.B    $09,'   10. Add 100,000 to cash',$0D,$0A
              DC.B    $09,'   11. Trigger critical power gameend',$0D,$0A
              DC.B    $09,'   12. Exit',$0D,$0A,0

  
STYTEXT2   dc.b $0A, $0D,'+*****************************************************************************+',$0A, $0D
          dc.b '|                            Your quest begins!                               |',$0A, $0D
          dc.b '+*****************************************************************************+',$0A, $0D,$0A, $0D
          dc.b $09,'You arrive for your first day in office. The taxi drops you off at the',$0A, $0D
          dc.b $09,'address  on your appointment letter. You are in a rather rundown looking',$0A, $0D
          dc.b $09,'industrial park. You wonder if the address is wrong but then a door opens',$0A, $0D
          dc.b $09,'and someone beckons you in.',$0A, $0D,$0A, $0D
          dc.b $09,'The person explains they are to be your secretary and are there to help',$0A, $0D
          dc.b $09,'you with your difficult task. They explain the world government has ',$0A, $0D
          dc.b $09,'given you a shoestring budget so you must manage your meagre',$0A, $0D
          dc.b $09,'resources carefully because unless you are Jeff Bezos with a rocket',$0A, $0D
          dc.b $09,' to escape, this planet is the only home we have.',$0A, $0D,$0A, $0D
          dc.b $09,'They insist you start work straight away and lead you to your',$0A, $0D
          dc.b $09,'new office to explain the worrying facts...',$0A, $0D,0

player    dc.b 'o',0
ENEMY     dc.b 'O',0
target    dc.b 'X',0
teststring dc.b '00008888',0
blank     dc.b ' ',0

dblank    dc.b '  ',$08,$08,0
backspace dc.b $08,0

mazeintro1 dc.b $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
           dc.b '|                           Green Gallop!                                     |',$0A, $0D
           dc.b '+*****************************************************************************+',$0A,$0D,$0A,$0D,0
mazeintro2 dc.b $09,'A rich billionaire has invited you to a party to discuss donating',$0A, $0D
           dc.b $09,'money to help the global effort solving this crisis. Unfortunately ',$0A, $0D
           dc.b $09,'due to a bad genetic roll of the dice, you have a terrible sense of',$0A, $0D
           dc.b $09,'direction and get totally lost on the way, arriving three hours late!!!',$0A, $0D
           dc.b $09,'Here at last you run to the door only for the butler to tell you your',$0A, $0D
           dc.b $09,'potential cash cow is about to leave. Worse you managed to forget ',$0A, $0D
           dc.b $09,'your invitation so you push past the startled butler and race ',$0A, $0D
           dc.b $09,'to find your target. From the butlers shouts he seems pretty',$0A, $0D
           dc.b $09,'angry so avoiding him is vital!',$0A, $0D,$0A, $0D
           dc.b $09,'Find your donor before the time runs out while avoiding the angry',$0A, $0D
           dc.b $09,'butler and you still have a chance at getting paid! Hurry!',$0A, $0D,$0A, $0D,0
mazeintro3 dc.b $09,$09,$09,'Controls',$0A, $0D,$0A, $0D
           dc.b $09,$09,$09,'A to move left',$0A, $0D
           dc.b $09,$09,$09,'D to move right',$0A, $0D
           dc.b $09,$09,$09,'W to move up',$0A, $0D
           dc.b $09,$09,$09,'S to move down',$0A, $0D
           dc.b $09,$09,$09,'Press Q to quit at any time',$0A, $0D,$0A,$0D,0
mazeintro4 dc.b $09,$09,$09,'X is the location of your target',$0A, $0D,0
mazeintro5 dc.b $09,$09,$09,'O is the rather scary butler',$0A, $0D,0


 
OLDTIME dc.l $6000           ;Old time storage
CURRENTTIME dc.l $6010       ;Current time storage
OLDPOS dc.w $6020  ;Old player position for clearing
OLDEPOS dc.w $6500 ;Old enemy position for clearing 

COUNTER dc.l $6040          ;Counter for number of maze game played
TARPOS  dc.w $4500          ;Target position address for maze minigame
PLAYPOS  dc.w $4520         ;Player position address for maze minigame
EPOS  dc.w $4530            ;Enemy position address for maze minigame
EAI   dc.w  $4420           ;Ai variable for enemy, move in one direction until hits something then roll a random number to determine next direction

;Colour address locations
GREEN     dc.l $7000
BLUE      dc.l $7010
YELLOW      dc.l $7020
PURPLE      dc.l $7030
RED      dc.l $7040
ORANGE      dc.l $7050
LBLUE      dc.l $7060

MINIALOSE  dc.b $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
          dc.b '|                              Game Over!                                     |',$0A, $0D
          dc.b '+*****************************************************************************+',$0A, $0D,0
MINIALOSE1 dc.b $09,'You arrive at the room the butler told you your potential',$0A, $0D
          dc.b $09,'donor was in completely out of breath. Out of the window you watch ',$0A, $0D
          dc.b $09,'dismayed to see your potential donor climbing into a rather ',$0A, $0D
          dc.b $09,'enviromentally unfriendly looking helicopter. On the brightside',$0A, $0D
          dc.b $09,'all the running means you wont have to go to the gym for a while.',$0A, $0D
          dc.b $09,' Your secretary is not going to be happy...',$0A, $0D,0

MINIALOSE2 dc.b $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
          dc.b '|                              Game Over!                                     |',$0A, $0D
          dc.b '+*****************************************************************************+',$0A, $0D,0
MINIALOSE21 dc.b $09,'The butler catches up to you before you reach your potential',$0A, $0D
          dc.b $09,'donor. He seems pretty angry and proceeds to throw you',$0A, $0D
          dc.b $09,'out of the party.',$0A, $0D,$0A, $0D
          dc.b $09,'You do manage to snag a free bottle of champagne on the way out.',$0A, $0D
          dc.b $09,'Hopefully that will appease you secretary somewhat...',$0A, $0D,0
          
MINIAWIN   dc.b $0A, $0D,$0A, $0D,'+*****************************************************************************+',$0A, $0D
          dc.b '|                             Success!!                                        |',$0A, $0D
          dc.b '+*****************************************************************************+',$0A, $0D,0
MINIAWIN1 dc.b $09,'You make it just as your cashcow, erm i mean generous philanthropist ',$0A, $0D
          dc.b $09,'was just leaving the party. After you catch your breath you explain',$0A, $0D
          dc.b $09,'your great endeavour to save the planet. You must have said the right',$0A, $0D
          dc.b $09,'thing because he pledges to sell one of his older superyachts as soon',$0A, $0D
          dc.b $09,'as he can remember where he parked it and make a large donation. His',$0A, $0D
          dc.b $09,'only condition is to strictly keep the phrases tax writeoff and ',$0A, $0D
          dc.b $09,'tax evasion off the press releases. What a lovely man...',$0A, $0D,0




    END    START        ; last line of source














*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
