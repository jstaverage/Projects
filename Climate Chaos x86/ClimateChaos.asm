;-----------------------------------------------------------------------------------------
;-----------------------------------CLIMATE CHAOS-----------------------------------------
;-----------------------------------------------------------------------------------------
;-------------------------------------------------------
; DATA Section
;-------------------------------------------------------
section .data


;--------------------Strings----------------------------

;Border String
border                  db '*************************************************************************************',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*                                                                                   *',$0A, $0D
                        db '*************************************************************************************'
len_border              equ $-border

;Start Credits String
    startcredits        db     $09,'   /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\',$0D,$0A
                        db     $09,'     /$$$$$$  /$$ /$$                           /$$  ',$0D,$0A
                        db     $09,'    /$$__  $$| $$|__/                          | $$ ',$0D,$0A
                        db     $09,'   | $$  \__/| $$ /$$ /$$$$$$/$$$$   /$$$$$$  /$$$$$$    /$$$$$$',$0D,$0A
                        db     $09,'   | $$      | $$| $$| $$_  $$_  $$ |____  $$|_  $$_/   /$$__  $$',$0D,$0A
                        db     $09,'   | $$      | $$| $$| $$ \ $$ \ $$  /$$$$$$$  | $$    | $$$$$$$$',$0D,$0A
                        db     $09,'   | $$    $$| $$| $$| $$ | $$ | $$ /$$__  $$  | $$ /$$| $$_____/',$0D,$0A
                        db     $09,'   |  $$$$$$/| $$| $$| $$ | $$ | $$|  $$$$$$$  |  $$$$/|  $$$$$$$',$0D,$0A
                        db     $09,'    \______/ |__/|__/|__/ |__/ |__/ \_______/   \___/   \_______/',$0D,$0A
                        db     $09,'     /$$$$$$  /$$                                     /$$ /$$ /$$',$0D,$0A
                        db     $09,'    /$$__  $$| $$                                    | $$| $$| $$',$0D,$0A
                        db     $09,'   | $$  \__/| $$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$| $$| $$| $$',$0D,$0A
                        db     $09,'   | $$      | $$__  $$ |____  $$ /$$__  $$ /$$_____/| $$| $$| $$',$0D,$0A
                        db     $09,'   | $$      | $$  \ $$  /$$$$$$$| $$  \ $$|  $$$$$$ |__/|__/|__/',$0D,$0A
                        db     $09,'   | $$    $$| $$  | $$ /$$__  $$| $$  | $$ \____  $$    ',$0D,$0A
                        db     $09,'   |  $$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$/ /$$$$$$$/ /$$ /$$ /$$',$0D,$0A
                        db     $09,'    \______/ |__/  |__/ \_______/ \______/ |_______/ |__/|__/|__/',$0D,$0A
                        db     $09,'   \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/'
len_startcredits        equ $-startcredits

;Press spacebar to start string
pressstart              db      $09,$09,'   Press Spacebar if you wish to save the planet!'
len_pressstart          equ     $-pressstart 

;Earth image String
earth                   db $0A,$0D,$09,$09,'                 000000000000',$0A, $0D
                        db $09,$09,'             0088880000000000000000',$0A, $0D
                        db $09,$09,'         0000008888888888880000000008880',$0A, $0D
                        db $09,$09,'       00000000008888888888800000000008880',$0A, $0D
                        db $09,$09,'     000000000088888888888000000000000088000',$0A, $0D
                        db $09,$09,'    000000000888888888880088888800000000000000',$0A, $0D
                        db $09,$09,'   00000000008888888888888888888880000000000000',$0A, $0D
                        db $09,$09,'  0000000000008888888888888888800000000000000000',$0A, $0D
                        db $09,$09,' 000000000000088888888888880000000000000000000000',$0A, $0D
                        db $09,$09,' 000000000000088888888888800000000000000000000000',$0A, $0D
                        db $09,$09,' 000000000000008888000000000000000000000000000000',$0A, $0D
                        db $09,$09,' 000000000000000088888800080000000000000000000000',$0A, $0D
                        db $09,$09,' 000000000000000000088888800000000000000000000000',$0A, $0D
                        db $09,$09,' 000000000000000000000008888888888000000000000000',$0A, $0D
                        db $09,$09,'  0000000000000000000000008888888888888000000000',$0A, $0D
                        db $09,$09,'   00000000000000000000000888888888888800000000',$0A, $0D
                        db $09,$09,'    000000000000000000000088888888888800000000',$0A, $0D
                        db $09,$09,'     0000000000000000000000888888888880000000',$0A, $0D
                        db $09,$09,'        0000000000000000000888888888000000',$0A, $0D
                        db $09,$09,'          0000000000000000008888888000000 ',$0A, $0D
                        db $09,$09,'              000000000000888888800000  ',$0A, $0D
                        db $09,$09,'                   00000000000000  ',$0A, $0D
len_earth    equ $-earth

;Pale blue dot text string
palebluedot             db $09,'Earth. Thats here. Thats home. Thats us. On it everyone you love,',$0A, $0D
                        db $09,'everyone you know, every human being who ever was, lived out their ',$0A, $0D
                        db $09,'lives.The aggregate of our joy and suffering and every saint and sinner',$0A, $0D
                        db $09,' lived here - on a mote of dust suspended in a sunbeam - Carl Sagan',$0A, $0D,$0A, $0D,0

len_palebluedot         equ $-palebluedot

;Mailbox image string
;Ref: https://www.asciiart.eu/miscellaneous/mailbox
mail                    db $0A, $0D,$09,$09,$09,$09,'       ..--""|',$0A, $0D
                        db $09,$09,$09,$09,'       |     |',$0A, $0D
                        db $09,$09,$09,$09,'       | .---.',$0A, $0D
                        db $09,$09,$09,$09,' (\-.--| |---------.',$0A, $0D                             
                        db $09,$09,$09,$09,'/ \) \ | |          \',$0A, $0D
                        db $09,$09,$09,$09,'|:.  | | |           |',$0A, $0D
                        db $09,$09,$09,$09,'|:.  | |o|           |',$0A, $0D
                        db $09,$09,$09,$09,'|:.  | `"`           |',$0A, $0D
                        db $09,$09,$09,$09,'|:.  |_ __  __ _  __ /',$0A, $0D
                        db $09,$09,$09,$09,'`""""`""|=`|"""""""`',$0A, $0D
                        db $09,$09,$09,$09,'        |=_|',$0A, $0D
                        db $09,$09,$09,$09,'        |= |',$0A, $0D,0
len_mail                equ $-mail

;Story text string                   
stytext                 db $0A, $0D,'+***********************************************************************************+',$0A, $0D
                        db '|                                 You have mail!                                    |',$0A, $0D
                        db '+***********************************************************************************+',$0A, $0D,$0A, $0D
                        db $09,'Due to human greed and apathy the climate is on the brink of crisis. All ',$0A, $0D
                        db $09,'around the world, reckless consumption pushes us closer to the edge of',$0A, $0D
                        db $09,'disaster. World leaders gather to discuss how to meet Goal 13 or at least',$0A, $0D
                        db $09,'how they can appear to be doing something.',$0A, $0D,$0A, $0D
                        db $09,'They decide to form a special world agency with powers to address the',$0A, $0D
                        db $09,'problem. It is to be headed by the most qualified candidate in the world,',$0A, $0D
                        db $09,'a person with genius intellect, magnetic charisma and inpeccable ethics.',$0A, $0D
                        db $09,'Unfortunatly, due to a computer error, the invitation was sent to',$0A, $0D
                        db $09,'you instead...God help us',$0A, $0D,0
len_stytext             equ $-stytext

;Building image string
;Ref: https://www.asciiart.eu/buildings-and-places/cities
building                db $0A, $0D,$0A, $0D
                        db $09,'                     \  |  /         ___________                 ',$0A, $0D
                        db $09,'      ____________  \ \_# /         |  ___      |       _________',$0A, $0D
                        db $09,'     |      _     |   \\#           | |`v |     |      |  __  _  |',$0A, $0D                             
                        db $09,'     |  _  |_|    |    \#  //       |  --- ___  |      | |__||_| |',$0A, $0D
                        db $09,'     | |_|        |    \# |+ ++|  | |  |~~~~~~| |      |  __  _  |',$0A, $0D
                        db $09,'     |            |    \# |+ ++|  | |  |~~~~~~| |      | |__||_| |',$0A, $0D
                        db $09,'   ~~|    (~~~~~) |~~~~~#~| H  |_ |~|  | |||| | |~~~~~~|         |',$0A, $0D
                        db $09,'     ~~~~~~~~~~~~~________/  /_____ |  | |||| | |      | ||||||| |',$0A, $0D
                        db $09,'                                    ~~~~~~~~~~~~~      | ||||||| |',$0A, $0D,0  
len_building            equ $-building

;Story text 2 string
stytext2                db $0A, $0D,'+***********************************************************************************+',$0A, $0D
                        db '|                               Your quest begins!                                  |',$0A, $0D
                        db '+***********************************************************************************+',$0A, $0D,$0A, $0D
                        db $09,'You arrive for your first day in office. The taxi drops you off at the',$0A, $0D
                        db $09,'address  on your appointment letter. You are in a rather rundown looking',$0A, $0D
                        db $09,'industrial park. You wonder if the address is wrong but then a door opens',$0A, $0D
                        db $09,'and someone beckons you in.',$0A, $0D,$0A, $0D
                        db $09,'The person explains they are to be your secretary and are there to help',$0A, $0D
                        db $09,'you with your difficult task. They explain the world government has ',$0A, $0D
                        db $09,'given you a shoestring budget so you must manage your meagre',$0A, $0D
                        db $09,'resources carefully because unless you are Jeff Bezos with a rocket',$0A, $0D
                        db $09,' to escape, this planet is the only home we have.',$0A, $0D,$0A, $0D
                        db $09,'They insist you start work straight away and lead you to your',$0A, $0D
                        db $09,'new office to explain the worrying facts...',$0A, $0D,0
len_stytext2            equ $-stytext2

;----------Strings for Main Screen----------------------
;Box String for main hud
box                     db '*+---------------------------------------------------------------------------------+',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*|                                                                                 |',$0A, $0D
                        db '*+---------------------------------------------------------------------------------+',0
len_box                 equ $-box

agcname                 db $09,$09,$09,'WORLD CLIMATE ACTION AGENCY(WCAA)',$0A, $0D
                        db $09,$09,$09,$09,' ANNUAL REPORT ',$0A, $0D,0
len_agcname             equ $-agcname

cshamount               db $09,'Current Funds: ',0
len_cshamount           equ $-cshamount

pcash                   db $09,$09,$09,' Current Personal Funds: ',0
len_pcash               equ $-pcash

currentgas              db $09,'Current CO2 Concentration: ',0
len_currentgas          equ $-currentgas

tippingpoint            db $09,$09,'Tipping Point: ',0
len_tippingpoint        equ $-tippingpoint

pm                      db ' ppm',0
len_pm                  equ $-pm

annincrease             db $09,'Annual Increase CO2: ',0
len_annincrease         equ $-annincrease

millton                 db ' Million tonnes',0
len_millton             equ $-millton

carbonerg               db $09,'Current Non-Green Energy Production: ',0
len_carbonerg           equ $-carbonerg

gloenergy               db $09,'   Global Power Needs: ',0 
len_gloenergy           equ $-gloenergy

gws                     db ' GWs',0 
len_gws                 equ $-gws

greenerg                db $09,'Current Green Energy Production:: ',0 
len_greenerg            equ $-greenerg

treeplan                db $09,'Current Trees Planted: ',0 
len_treeplan            equ $-treeplan

treeab                  db $09,'Trees Absorbing: ',0
len_treeab              equ $-treeab

treenm                  db ',000 trees ',0 
len_treenm              equ $-treenm

cton                    db ',000,000 Tonnes of Carbon per year ',0 
len_cton                equ $-cton

;Desk image string
desk                    db     $09,':::::::=========::::::.---------------.:::::::::::::::::::::::::::::::',$0D,$0A
                        db     $09,':::=============::::::| .-----------. |:::::::::::::::::::::::::::::',$0D,$0A
                        db     $09,'::::==========::::::::| |  CLIMATE  | |:::::::::::::::::::::::::::::::',$0D,$0A
                        db     $09,':::::::========== ::::| |  URGETNT! | |:::::::::::::::::::::::::::::',$0D,$0A
                        db     $09,':::===========::::::::| |___________| |::::::(():::::::::::::::::::::',$0D,$0A
                        db     $09,'""""============""""""|___________oo__|"")""""(""""""""""""""""""""""',$0D,$0A
                        db     $09,'  ===========            ___)___(___,o  (   .---._',$0D,$0A
                        db     $09,'     ===========        |___________| 8  \  |TEA|_)    .+-------+.',$0D,$0A
                        db     $09,'  ===========                     o8o8    ) |___|    .  |_______| `.',$0D,$0A
len_desk                equ $-desk

;Available actions string(main hud)
optionsa                db    '*                         Available Actions                      ',$0D,$0A
                        db    '*    You sit down at your desk and your secretary lays out the dire facts before ',$0D,$0A
                        db    '*    you. They explain you must manage the meagre budget given to you wisely to ',$0D,$0A
                        db    '*    save the day.No pressure! At least they give you some nice green tea...',$0D,$0A
                        db    '*    What would you like to do?',$0D,$0A,$0A
                        db    $09,$09,'1. View Energy Investment Projects                       ',$0D,$0A
                        db    $09,$09,'2. Try raise some Cash                                   ',$0D,$0A , 0
len_optionsa            equ $-optionsa

;Options strings (investmenu)
optionsb                db    $0A, $0D,$0A, $0D
                        db    '*+********************************************************************************+*',$0A, $0D
                        db    '*|                           WORLD CLIMATE ACTION AGENCY                          |*',$0A, $0D
                        db    '*+********************************************************************************+*',$0A, $0D
                        db    '*|                             Available WCAA Iniatives                           |*',$0A, $0D
                        db    '*+********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_optionsb            equ $-optionsb

optionsba               db    $09,'Your secretary has prepared a list of iniatives that can be undertaken',$0D,$0A
                        db    $09,'this calendar year.',$0D,$0A,$0D,$0A
                        db    $09,'Each project will take a year to complete and have its ',$0D,$0A
                        db    $09,'risks and costs. Choice wisely! ',$0D,$0A
                        db    $09,'Your secretary has started sweating profusely...',$0D,$0A
                        db    $0D,$0A,$0D,$0A
                        db    $09,$09,'1. Invest in a tree planting iniative to offset Carbon  '
                        db    $0D,$0A,$0D,$0A
                        db    $09,$09,'2. Invest in zero carbon energy production   '
                        db    $0D,$0A,$0D,$0A
                        db    $09,$09,'3. Reduce carbon producing energy production   '
                        db    $0D,$0A,$0D,$0A
                        db    $09,$09,'4. Return to Menu'   
                        db    $0D,$0A , 0
len_optionsba           equ $-optionsba

;Tree options strings (treeopts)
treeoptions             db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                             Tree Planting Iniatives                             |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_treeoptions         equ $-treeoptions

treeoptionsb            db    $0D,$0A,$0D,$0A,$09,'Your secretary presents you with a list of Tree',$0D,$0A
                        db    $09,'Tree Planting Iniatives. What would you like to pick? ',$0D,$0A,$0D,$0A,0
len_treeoptionsb        equ $-treeoptionsb

treeoptionsb1           db    $09,'1. Buy land and plant a million trees in an area with a',$0D,$0A
                        db    $09,'   seemingly suitable climate.  (Low Risk)       Cost: 100000'
                        db    $0D,$0A,$0D,$0A,0
len_treeoptionsb1       equ $-treeoptionsb1

treeoptionsb2           db    $09,'2. Buy land and plant a million trees in an area with the cheapest',$0D,$0A
                        db    $09,'   land prices.       (Medium Risk)              Cost:  50000',$0D,$0A
                        db    $0D,$0A,$0D,$0A,0
len_treeoptionsb2       equ $-treeoptionsb2

treeoptionsb3           db    $09,'3. Partner with a vicious dictator and plant a million trees',$0D,$0A
                        db    $09,'   on land ceased from people fighting for freedom in his    ',$0D,$0A
                        db    $09,'   country. The press release will say terrorists...         ',$0D,$0A
                        db    $09,'                      (High Risk)                Cost:  25000',$0D,$0A,$0D,$0A,0
len_treeoptionsb3       equ $-treeoptionsb3

treeoptionsb4           db    $09,'4. Retreat to menu in disappointment...          Cost:  Pride',$0D,$0A,0
len_treeoptionsb4       equ $-treeoptionsb4

treeopta                db    $0D,$0A
                        db    $09,'The iniative is a success, with over a million trees planted in  ',$0D,$0A
                        db    $09,'this scenic location over the course of the year. They stretch as',$0D,$0A
                        db    $09,'far as the eye can see. Maybe you should buy that waterfront property',$0D,$0A
                        db    $09,'afterall!',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary looks impressed...',$0D,$0A,$0D,$0A,0
len_treeopta            equ $-treeopta

treeoptbn               db    $0D,$0A
                        db    $09,'Disaster! It turns out while the climate was lovely the      ',$0D,$0A
                        db    $09,'location picked was in the shadow of an active volcano!      ',$0D,$0A,$0D,$0A
                        db    $09,'The advisor who chose the location will be fired. As an      ',$0D,$0A
                        db    $09,'employee of this public body that means they will only       ',$0D,$0A
                        db    $09,'receive 75% of their entitled pension. Truly justice         ',$0D,$0A
                        db    $09,'has been served.           ',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary will only bring you give you cold',$0D,$0A
                        db    $09,'coffee for now..',$0D,$0A,0
len_treeoptbn           equ $-treeoptbn

treeoptb                db    $0D,$0A
                        db    $09,'Success! Despite the harse climate in the region with some   ',$0D,$0A
                        db    $09,'love and care, the trees have adapted and thrived. ',$0D,$0A,$0D,$0A
                        db    $09,'You have saved cash and carbon in one stroke.    ',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary smiles enigmatically.',$0D,$0A,0
len_treeoptb            equ $-treeoptb

treeoptan               db    $0D,$0A
                        db    $09,'Hmmm, it turns out that trees need water to grow and the arid',$0D,$0A
                        db    $09,'area picked for this iniative had none.',$0D,$0A,$0D,$0A
                        db    $09,'On the brightside this has been an opportunity for learning',$0D,$0A
                        db    $09,'for all involved the PR campaign will claim.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary refuses to look you in the eyes...',$0D,$0A,0
len_treeoptan           equ $-treeoptan

treeoptc                db    $0D,$0A
                        db    $09,'Good news, in an unbelievable turn of events, the regime',$0D,$0A
                        db    $09,'has reported that the terrorists who previously owned the',$0D,$0A
                        db    $09,'land gave it to the government willingly!   ',$0D,$0A,$0D,$0A
                        db    $09,'They also have agreed to tend to the trees for FREE with the',$0D,$0A
                        db    $09,'regime volunteering to supply armed guards to keep them safe',$0D,$0A
                        db    $09,'while they work. Truly we have made the world a better place.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary smiles dubiously...                           ',$0D,$0A,$0D,$0A,0
len_treeoptc            equ $-treeoptc

treeoptcn               db    $0D,$0A
                        db    $09,'In an unexpected and unforeseeable turn of events, we have      ',$0D,$0A
                        db    $09,'learned that dictators are not nice people.   ',$0D,$0A,$0D,$0A
                        db    $09,'The regime has pocketed the cash and refuses to plant the trees',$0D,$0A
                        db    $09,'promised. In unrelated news, they have bought a rather lovely superyacht...',$0D,$0A,$0D,$0A,0
len_treeoptcn           equ $-treeoptcn

;Energy options strings (energyopts)
energyoptions           db    '*+*********************************************************************************+*',$0A, $0D
                        db    '*|                           Zero Carbon Energy Iniatives                          |*',$0A, $0D
                        db    '*+*********************************************************************************+*',0 
len_energyoptions       equ $-energyoptions

energyoptionsb          db    $0A,$0D,$09,'Your secretary presents you with a list of Green Energy Initiatives. ',$0D,$0A
                        db    $09,'Which would you like to pick? ',$0D,$0A,$0D,$0A,0
len_energyoptionsb      equ $-energyoptionsb

energyoptions1          db    $09,'1. Buy land in a desert regions and build a massive solar plant',$0D,$0A
                        db    $09,'   capable of producing 1000GW.  (Low Risk)        Cost: 100000',$0D,$0A,$0D,$0A,0
len_energyoptions1      equ $-energyoptions1

energyoptions2          db    $09,'2. Build a massive offshore wind farm in the storm wracked   ',$0D,$0A
                        db    $09,'   waters off ship grave reef, not sure where the name came  ',$0D,$0A
                        db    $09,'   from but your advisors assure you it is quite windy. The  ',$0D,$0A
                        db    $09,'   the rights to build there are cheap too.         Prod:1000GW',$0D,$0A
                        db    $09,'                                 (Medium Risk),,   Cost:  50000'
                        db    $0D,$0A,$0D,$0A,0
len_energyoptions2      equ $-energyoptions2

energyoptions3          db    $09,'3. Former Soviet Republics have decommissioned nuclear       ',$0D,$0A
                        db    $09,'   available for sales at amazing prices, the seller claims  ',$0D,$0A
                        db    $09,'   that similar reactors have an ALMOST perfect safety record',$0D,$0A
                        db    $09,'   so what could be the downsides from such a great deal     ',$0D,$0A
                        db    $09,'   Prod:1000GW                   (High Risk)       Cost:  25000',$0D,$0A,$0D,$0A,0
len_energyoptions3      equ $-energyoptions3

energyoptions4          db    $09,'4. Some world class scientists have a proposal to fund what   ',$0D,$0A
                        db    $09,'   they claim is the future of green energy production,      ',$0D,$0A
                        db    $09,'   FUSION! They are currently projecting it will be ready to ',$0D,$0A 
                        db    $09,'   save the world and produce all the green energy the planet',$0D,$0A
                        db    $09,'   needs in ten years with current funding but they say with ',$0D,$0A
                        db    $09,'   additional funds it could be ready much sooner            ',$0D,$0A
                        db    $09,'                                 (Unknown Risk)    Cost:  50000',$0D,$0A,$0D,$0A,0
len_energyoptions4      equ $-energyoptions4

energyoptions5          db    $09,'5. Retreat to menu in disappointment...            Cost:  Pride',$0D,$0A,0
len_energyoptions5      equ $-energyoptions5

energyopta              db    $0A, $0D
                        db    $09,'The solar power plant is up and running producing clean green',$0D,$0A
                        db    $09,'energy. The sun shines on your efforts.',$0D,$0A
                        db    $09,'Your secretary gives you a complementary muffin...',$0D,$0A,$0D,$0A,0
len_energyopta          equ $-energyopta

energyoptan             db    $0A, $0D
                        db    $09,'Oh dear, the newspapers are having a field day, it turns out that',$0D,$0A
                        db    $09,'the antartic is technically the largest desert in the world!',$0D,$0A,$0D,$0A
                        db    $09,'Due to an unfortunate miscommunication, the new solar plant',$0D,$0A
                        db    $09,'was built in THAT desert. On the upside, the penguins are cute.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary has developed 100 new grey hairs...',$0D,$0A,0
len_energyoptan         equ $-energyoptan

energyoptb              db    $0A, $0D
                        db    $09,'The new windfarm construction has been completed despite ',$0D,$0A
                        db    $09,'lively weather. A success of human ingenuity over mother ',$0D,$0A
                        db    $09,'nature.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary offers you a rare pat on the back...  ',$0D,$0A,0
len_energyoptb          equ $-energyoptb

energyoptbn             db    $0A, $0D
                        db    $09,'Oh dear, after only three days of operation a large storm ',$0D,$0A
                        db    $09,'destoys the new power plant. The irony of wind destroying ',$0D,$0A
                        db    $09,'your brand new wind farm is lost on you.      ',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary has suddenly started updating their ',$0D,$0A
                        db    $09,'CV for some reason...',$0D,$0A,0
len_energyoptbn         equ $-energyoptbn

energyoptc              db    $0A, $0D
                        db    $09,'Your plan to buy and put into operation an obsolete Soviet ',$0D,$0A
                        db    $09,'nuclear power plant has alarmed World leaders to such an   ',$0D,$0A
                        db    $09,'extent that they fund the building of a new solar farm if  ',$0D,$0A
                        db    $09,'you agree to desist. ',$0D,$0A,$0D,$0A
                        db    $09,'You reluctantly agree, what were they so worried about?',$0D,$0A
                        db    $09,'Your secretary has started using smaller words when talking',$0D,$0A
                        db    $09,'to you  which is helpful...                      Prod: 500GW ',$0D,$0A,0
len_energyoptc          equ $-energyoptc

energyoptcn             db    $0A, $0D,$0A, $0D,$0A, $0D
                        db    $09,'Your plan while foolproof has somehow backfired with the ',$0D,$0A
                        db    $09,'alarmed world officials blocking your plan and the former ',$0D,$0A
                        db    $09,'Soviet Republic refusing to give back the funds. ',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary refuses to allow you to use scissors...',$0D,$0A,0
len_energyoptcn         equ $-energyoptcn

energyoptd              db    $09,'Wonderful news, the scientists have made a huge breakthrough ',$0D,$0A
                        db    $09,'in the production of fusion power. ',$0D,$0A,$0D,$0A
                        db    $09,'They report that they now expect that they can produce endless',$0D,$0A
                        db    $09,'green energy in TEN years, down from the ten years predicted  ',$0D,$0A
                        db    $09,'before the expensive research. They just ask for more funds ',$0D,$0A,0
                        db    $09,'to reduce this time further...',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary has started building a survival bunker...     ',$0D,$0A,0
len_energyoptd              equ $-energyoptd

;Cut options strings (cutopts)
cutoptions              db    '*+*********************************************************************************+*',$0A, $0D
                        db    '*|                       Reduce Non-Green Energy Iniatives                         |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_cutoptions          equ $-cutoptions

cutoptionsb             db    $09,'Your secretary presents you with a list of Iniatives to reduce. ',$0D,$0A
                        db    $09,'CO2 production. Which would you like to pick? ',$0D,$0A,$0D,$0A,$0D,$0A,0
len_cutoptionsb         equ $-cutoptionsb
                        
cutoptionsb1            db    $09,'1. Introduce higher Carbon taxes on non-green energy         ',$0D,$0A
                        db    $09,'   energy production.-1000GW    (Low Risk)      Gain Cash: 25000',$0D,$0A,$0D,$0A,0
len_cutoptionsb1         equ $-cutoptionsb1
                        
cutoptionsb2            db    $09,'2. Introduce new safety regul ations that make some old       ',$0D,$0A
                        db    $09,'   powerplants unviable. Bonus is that the fines you can     ',$0D,$0A
                        db    $09,'   levy might make some extra cash!                          ',$0D,$0A
                        db    '                      -1500GW       (Medium Risk)   Gain Cash: 50000',$0D,$0A,$0D,$0A,0
len_cutoptionsb2         equ $-cutoptionsb2
                        
cutoptionsb3            db    $09,'3. Manufacturer fake documents claiming that a top executive ',$0D,$0A
                        db    $09,'   of a major oil company lost shares to you in a drunken    ',$0D,$0A
                        db    $09,'   poker game. Use this share claim to shutdown one of their ',$0D,$0A
                        db    $09,'   energy production divisions                               ',$0D,$0A
                        db    '                      -2000GW       (High Risk)    Gain Cash: 100000',$0D,$0A,$0D,$0A,0
len_cutoptionsb3        equ $-cutoptionsb3
                        
cutoptionsb4            db    $09,'4. Retreat to menu in disappointment...              Cost: Pride',$0D,$0A,0
len_cutoptionsb4        equ $-cutoptionsb4

cutopta                 db    $0A, $0D
                        db    $09,'The new tax while unpopular with corporations is a great     ',$0D,$0A
                        db    $09,'success making green energy more competitive.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary offers you a hot chocolate...',$0D,$0A,$0D,$0A,0
len_cutopta             equ $-cutopta

cutoptan                db    $0A, $0D
                        db    $09,'The tax has had mixed results, the raised price just made    ',$0D,$0A
                        db    $09,'producing non-green power more profitable so more non-green  ',$0D,$0A
                        db    $09,'power is now being produced.   ',$0D,$0A,$0D,$0A
                        db    $09,'You do get some more taxes on the bright side?',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary glowers in disapproval...    ',$0D,$0A,0
len_cutoptan            equ $-cutoptan

cutoptb                 db    $0A, $0D
                        db    $09,'The fines for violations of the new safety regulations roll  ',$0D,$0A
                        db    $09,'in and many old power plants have been forced to shut. ',$0D,$0A,$0D,$0A
                        db    $09,'We may succeed after all.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary whistles merrily...    ',$0D,$0A,0
len_cutoptb             equ $-cutoptb

cutoptbn                db    $0A, $0D
                        db    $09,'The non-green energy companies implementing your new safety  ',$0D,$0A
                        db    $09,'regulations has been a huge PR win for them. Workers and investors',$0D,$0A
                        db    $09,'flock to work in the industry and power production has risen wildly.',$0D,$0A,$0D,$0A
                        db    $09,'On the bright side fines rolls in filling your cash reserves.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary now wears a pernament frown...  '             ,$0D,$0A,0
len_cutoptbn            equ $-cutoptbn

cutoptc                 db    $0A, $0D
                        db    $09,'Luck is with you, turns out the judge reviewing the case ',$0D,$0A
                        db    $09,'was a complete idiot and believes the ridiculous lie. ',$0D,$0A
                        db    $09,'You gain control of one of their energy plants and shut it down. ',$0D,$0A
                        db    $09,'Who says crime doesnt pay. ',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary admires your ruthlessness...',$0D,$0A,0
len_cutoptc             equ $-cutoptc

cutoptcn                db    $0A, $0D
                        db    $09,'The judge reviewing your case dies of laugther but not before',$0D,$0A
                        db    $09,'throwing the case out of court. ',$0D,$0A,$0D,$0A
                        db    $09,'Now you are stuck with the legal costs with nothing to show for it.',$0D,$0A,$0D,$0A
                        db    $09,'Your secretary kicks you in the shin, that was rude...',$0D,$0A,0
len_cutoptcn            equ $-cutoptcn

cutoptq                 db    $0A, $0D, $0A, $0D, $0A, $0D, $0A, $0D, $0A, $0D
                        db    $09,'WARNING:',$0D,$0A
                        db    $09,'Cutting energy production below global needs may lead to dire',$0D,$0A
                        db    $09,'consequences. The further under production the more dire the outcomes',$0D,$0A
                        db    $09,'may be. Are you sure you wish to proceed:',$0D,$0A,$0D,$0A

                        db    $09,'  1. Yes',$0D,$0A
                        db    $09,'  2. No',$0D,$0A,0
len_cutoptq             equ $-cutoptq

cutoptqc                db    $09,'WARNING:',$0D,$0A
                        db    $09,'Power Production worldwide is reaching critically low levels.',$0D,$0A
                        db    $09,'Brownouts are now common all over the world with even critical',$0D,$0A
                        db    $09,'infrastructure like hospitals and food refrigeration often',$0D,$0A
                        db    $09,'losing power for parts of the day.',$0D,$0A,$0D,$0A
                        db    $09,'Major countries have starting squabbling over power use',$0D,$0A
                        db    $09,'cutting interconnecting power lines between countries and',$0D,$0A
                        db    $09,'hordeing their power for themselves.',$0D,$0A
                        db    $09,'Cutting energy production any further may have longlasting',$0D,$0A
                        db    $09,'and worldwide consequences.',$0D,$0A
                        db    $09,'Are you sure you wish to proceed:',$0D,$0A

                        db    $09,'  1. Yes',$0D,$0A
                        db    $09,'  2. No',$0D,$0A,0
len_cutoptqc            equ $-cutoptqc

;String for infunds subroutine
nofunds                 db    $0D,$0A,$09,'You have insufficient funds for this project. Your secretary  ',$0D,$0A
                        db    $09,'rolls their eyes behind your back...                          ',$0D,$0A,0
len_nofunds             equ $-nofunds

;String for             
nopfunds                db    $0D,$0A,$0D,$0A,$09,'You have insufficient funds for this bet. Your secretary  ',$0D,$0A
                        db    $09,'would roll their eyes if they knew about it...',$0D,$0A,0
len_nopfunds            equ $-nopfunds

;String for                      
nocpower                db    $0D,$0A,$0D,$0A,$09,'There is insufficient carbon power left to cut. Your  ',$0D,$0A
                        db    $09,'secretary signs irritably...                          ',$0D,$0A,0
len_nocpower            equ $-nocpower

;Strings for risk screen
risk                    db    '*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                       Risk                                      |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_risk                equ $-risk
                        
lrisk                   db    $09,'All ventures in life come with an element of risk. For now you have',$0A, $0D
                        db    $09,'chosen the safe path. But even the safe path has an element of danger',$0A, $0D,$0A, $0D
                        db    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
                        db    $09,'between 2 and 6',$0A, $0D,0
len_lrisk               equ $-lrisk
                        
mrisk                   db    $09,'All ventures in life come with an element of risk. You have',$0A, $0D
                        db    $09,'chosen the middle path, not too hot, not too cold',$0A, $0D,$0A, $0D
                        db    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
                        db    $09,'between 4 and 6',$0A, $0D,0
len_mrisk               equ $-mrisk
                        
hrisk                   db    $09,'All ventures in life come with an element of risk. You have',$0A, $0D
                        db    $09,'chosen shot for the stars, nothing ventured nothing gained!',$0A, $0D,$0A, $0D
                        db    $09,'For this venture to have luck on its side you need to roll',$0A, $0D
                        db    $09,' between 5 and 6',$0A, $0D,0
len_hrisk               equ $-hrisk

;Shamrock image for risk screen
;Ref: https://www.asciiart.eu/holiday-and-events/luck 
sham                    db $09,$09,'                    ***          ***                    ',$0A, $0D  
                        db $09,$09,'                 ***....**     **...***                 ',$0A, $0D  
                        db $09,$09,'                **........** **.......**                ',$0A, $0D  
                        db $09,$09,'         ***    **..........*.........**    ***         ',$0A, $0D 
                        db $09,$09,'      **.....**  **..................**  **.....**      ',$0A, $0D 
                        db $09,$09,'    **.........**  **..............**  **.........**    ',$0A, $0D 
                        db $09,$09,'   *..............*   *..........*   *..............*   ',$0A, $0D 
                        db $09,$09,'    **..............*   *......*   *..............**    ',$0A, $0D 
                        db $09,$09,'      **..............** *....* **..............**      ',$0A, $0D 
                        db $09,$09,'        *......................................*        ',$0A, $0D 
                        db $09,$09,'      **..............**........**..............**      ',$0A, $0D 
                        db $09,$09,'    **..............*    *....*....*..............**    ',$0A, $0D 
                        db $09,$09,'   *..............*    *........* ...*..............*   ',$0A, $0D 
                        db $09,$09,'    **.........**    *............* ...**.........**    ',$0A, $0D
                        db $09,$09,'      **.....**   **...............**....**.....**      ',$0A, $0D  
                        db $09,$09,'         ***    **...................**.....***         ',$0A, $0D  
                        db $09,$09,'              **...........*...........**....*          ',$0A, $0D  
                        db $09,$09,'               **.........* *.........** *......*..*..* ',$0A, $0D   
                        db $09,$09,'                 *......**   **......*     *........*   ',$0A, $0D  
                        db $09,$09,'                   **  *       * **           *...*     ',$0A, $0D ,0
len_sham                equ $-sham

;Strings for dice screen text
dtext                   db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                        All ventures depend partly on luck!                      |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_dtext               equ $-dtext

dtext2                  db    $0D,$0A
                        db    $09,'Ready to test your luck?',$0D,$0A,$0A
                        db    $09,$09,'1. Roll Dice                       ',$0D,$0A
                        db    $09,$09,'2. Hedge your bets                      ',$0D,$0A
                        db    $09,$09,'3. Selflessly invest 25000 of your personal cash to improve',$0D,$0A
                        db    $09,$09,'   the projects odds.(Successful roll needed reduced by 1)',$0D,$0A,0
len_dtext2              equ $-dtext2
                        
dtext3                  db    $09,'       Rolling...                      ',$0D,$0A,0
len_dtext3              equ $-dtext3

dtext4                  db    $09,'You rolled a ',0
len_dtext4              equ $-dtext4
            
gtext                   db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                Hedge your Bets!                                 |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_gtext               equ $-gtext
                         
gtext1                  db    $0D,$0A
                        db    $09,'A little bet on the side is harmless fun right? Best not mention',$0D,$0A
                        db    $09,'to anyone that your are betting on this though...',$0D,$0A,$0D,$0A
                        db    $09,'Spice up your life and bet that this venture will fail...',$0D,$0A,$0D,$0A
                        db    $09,'1. Bet personal cash 25000 that venture will fail at ',0
len_gtext1              equ $-gtext1

gtext2                  db    $0D,$0A,$09,'2. Thats highly unethical, insider betting! Return to dice',$0D,$0A,0
len_gtext2              equ $-gtext2

;Strings to display hedge bets winnings based on risk
odds1                   db    '1.5 to 1 odds',0
len_odds1               equ $-odds1

odds2                   db    '2 to 1 odds',0
len_odds2               equ $-odds2

odds3                   db    '3 to 1 odds',0
len_odds3               equ $-odds3

;Dice images for rolling 'animation'
rolld                   db $09,$09,$09,$09,'     _______     ',$0A, $0D 
                        db $09,$09,$09,$09,'    /O     /\    ',$0A, $0D 
                        db $09,$09,$09,$09,'   /   O  /O \   ',$0A, $0D 
                        db $09,$09,$09,$09,'((/_____O/    \  ',$0A, $0D 
                        db $09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
                        db $09,$09,$09,$09,'   \O    O\ O/   ',$0A, $0D 
                        db $09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
                        db $09,$09,$09,$09,'  ((             ',$0A, $0D,0
len_rolld               equ $-rolld
                         
rolld1                  db $09,$09,$09,$09,'     _______     ',$0A, $0D 
                        db $09,$09,$09,$09,'    /      /\    ',$0A, $0D 
                        db $09,$09,$09,$09,'   /  O   /O \   ',$0A, $0D 
                        db $09,$09,$09,$09,'((/_____ /    \  ',$0A, $0D 
                        db $09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
                        db $09,$09,$09,$09,'   \  O  O\ O/   ',$0A, $0D 
                        db $09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
                        db $09,$09,$09,$09,'  ((             ',$0A, $0D,0
len_rolld1              equ $-rolld1
                        
rolld2                  db $09,$09,$09,$09,'     _______     ',$0A, $0D 
                        db $09,$09,$09,$09,'    /O     /\    ',$0A, $0D 
                        db $09,$09,$09,$09,'   /      /  \   ',$0A, $0D 
                        db $09,$09,$09,$09,'((/_____O/ O  \  ',$0A, $0D 
                        db $09,$09,$09,$09,'  \O    O\    /  ',$0A, $0D 
                        db $09,$09,$09,$09,'   \      \  /   ',$0A, $0D 
                        db $09,$09,$09,$09,'    \O____O\/ )) ',$0A, $0D 
                        db $09,$09,$09,$09,'  ((             ',$0A, $0D,0
len_rolld2              equ $-rolld2
                
;Dice images for roll results
;Ref:  https://www.asciiart.eu/miscellaneous/dice                                                                
sixd	                db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'| o o o |',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D 
                        db $09,$09,$09,$09,'| o o o |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0
len_sixd                equ $-sixd
                
fived	                db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'| o   o |',$0A, $0D 
                        db $09,$09,$09,$09,'|   o   |',$0A, $0D 
                        db $09,$09,$09,$09,'| o   o |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0
len_fived               equ $-fived
                                                          
fourd	                db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'| o   o |',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D 
                        db $09,$09,$09,$09,'| o   o |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0 
len_fourd               equ $-fourd
                                                           
threed                  db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D 
                        db $09,$09,$09,$09,'| o o o |',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0 
len_threed              equ $-threed
                                          
twod	                db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'|   o   |',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D 
                        db $09,$09,$09,$09,'|   o   |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0
len_twod                equ $-twod

oned	                db $09,$09,$09,$09,' _______ ',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D 
                        db $09,$09,$09,$09,'|   o   |',$0A, $0D 
                        db $09,$09,$09,$09,'|       |',$0A, $0D
                        db $09,$09,$09,$09,' ------- ',$0A, $0D,0
len_oned                equ $-oned

die                     db $09,$09,$09,$09,'  .-------.  ',$0A, $0D 
                        db $09,$09,$09,$09,' /   o   /| ',$0A, $0D 
                        db $09,$09,$09,$09,'/_______/o| ',$0A, $0D 
                        db $09,$09,$09,$09,'| o o o | | ',$0A, $0D 
                        db $09,$09,$09,$09,'|       |o/ ',$0A, $0D 
                        db $09,$09,$09,$09,'| o o o |/  ',$0A, $0D 
                        db $09,$09,$09,$09,' -------    ',$0A, $0D,0
len_die                 equ $-die

;---------Banners for success and failurs screens-------
;Success text string
suctext                 db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                    SUCCESS!!!                                   |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_suctext             equ $-suctext

;Mysterious text string
questext                db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                        ???                                      |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_questext            equ $-questext

;Fail text string                          
failtext                db    $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                 DIRE FAILURE!!!                                 |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_failtext            equ $-failtext

;Good event strings
gevent                  db    $0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                  Good News!                                     |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_gevent              equ $-gevent

geventa                 db    $09,'World leaders are amazed by your progress. Greenhouse gas',$0A, $0D
                        db    $09,'emissions have dropped to their lowest levels since the',$0A, $0D
                        db    $09,'1990s.',$0A, $0D
                        db    $09,'The prime minister of a major country is so impressed he comes',$0A, $0D
                        db    $09,'to visit your office.',$0A, $0D
                        db    $09,'After toasting to your success a few too many times, he drunkenly',$0A, $0D
                        db    $09,'pledges a generous sum to your cause.',$0A, $0D
                        db    $09,'Your secretary is no longer embarressed to be seen with you...',0
len_geventa             equ $-geventa

geventb                 db    $09,'Congradulations are in order. For the first time in many  ',$0A, $0D
                        db    $09,'decades, north pole ice sheets have slowed their shrinking.',$0A, $0D
                        db    $09,'The event makes world news with many praising your efforts.',$0A, $0D,$0A, $0D
                        db    $09,'The head of the polar bear appreciation society is so happy',$0A, $0D
                        db    $09,'he sells his polar bear minature collection and donates the',$0A, $0D
                        db    $09,'proceeds to your cause!',$0A, $0D
                        db    $09,'Your secretary gives you an awkward high five...',0
len_geventb             equ $-geventb

geventc                 db    $09,'More good news has come into your office. With the global',$0A, $0D
                        db    $09,'temperature increases finally coming under control',$0A, $0D
                        db    $09,'wildfire incidents are at a ten year low.',$0A, $0D,$0A, $0D
                        db    $09,'Fire departments around the world donate the money',$0A, $0D
                        db    $09,'they are saving from not having to control the wildfires to',$0A, $0D
                        db    $09,'your cause in thanks!',$0A, $0D
                        db    $09,'Your secretary blows a kiss in your direction...',0
len_geventc             equ $-geventc

geventd                 db    $09,'Your progress is causing global celebrations. People',$0A, $0D
                        db    $09,'around the world begin to believe that this problem can',$0A, $0D
                        db    $09,'be solved.',$0A, $0D,$0A, $0D
                        db    $09,'Global incidents of extreme weather events like hurricanes',$0A, $0D
                        db    $09,'are at a 30 year low. Countries with land near sea level',$0A, $0D
                        db    $09,'donate the money they had set aside for flood defence to your cause.',$0A, $0D,$0A, $0D
                        db    $09,'Your secretary buys you a voucher for a spa treatment...',0
len_geventd                 equ $-geventd

;Strings for tempations event
ethics                  db    '*+********************************************************************************+*',$0A,$0D
                        db    '*|                                 Opportunity?                                   |*',$0A,$0D
                        db    '*+********************************************************************************+*+',$0A,$0D,$0A,$0D,0 
len_ethics                 equ $-ethics

ethics1                 db    $09,'An interesting opportunity presents itself. With great power',$0D,$0A
                        db    $09,'comes great opportunity. Only you know that this cash payment ',$0D,$0A
                        db    $09,'is coming in so if it was diverted to your personal account',$0D,$0A
                        db    $09,'surely nobody would find out?',$0D,$0A
                        db    $09,'You can use it for the betterment of the world more freely if',$0D,$0A
                        db    $09,'you had full control of the money perhaps???',$0D,$0A,$0D,$0A
                        db    $09,'   1. Yes absolutely, I will use it for the greater good...probably',$0D,$0A
                        db    $09,'   2. No of course not, that would be highly unethical',0
len_ethics1                 equ $-ethics1

;Strings for bad events (caused by low power generation)
peventf                 db    $0A, $0D,'*+********************************************************************************+*',$0A, $0D
                        db    '*|                                   Bad News!                                    |*',$0A, $0D
                        db    '*+********************************************************************************+*',$0A, $0D,$0A, $0D,0
len_peventf             equ $-peventf
                        
peventfa                db    $09,'The lack of worldwide power production is causing chaos around the',$0A, $0D
                        db    $09,'globe. Brown outs are becoming common and the disruption to food',$0A, $0D
                        db    $09,'distribution networks has caused riots in many countries.',$0A, $0D,$0A, $0D
                        db    $09,'Some governments ignore your mandate and bring additional non-green',$0A, $0D
                        db    $09,'powerplants back online.',$0A, $0D,$0A, $0D
                        db    $09,'Your secretary paces restlessly...',$0A, $0D,0
len_peventfa            equ $-peventfa

;Strings for game over events
gameov                  db    $0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D, $0D,$0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                    Game Over!                                   |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_gameov              equ $-gameov

gameovalt               db    $0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                   Game Over!                                    |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_gameovalt           equ $-gameovalt

gameovwin               db     $0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D,$0A, $0D, $0D,$0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                   Victory !                                     |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,$0A, $0D,0 
len_gameovwin           equ $-gameovwin

gameov1                 db    $09,'With greenhouse gas levels finally under control, you have ensured',$0A, $0D
                        db    $09,'life not just for humans but for all other species with which',$0A, $0D
                        db    $09,'we share this planet.',$0A, $0D,0
len_gameov1             equ $-gameov1
                        
gameov1a                db    $09,'We have just one home which we must share with our fellow',$0A, $0D
                        db    $09,'species. Your actions may have helped us succeed today but it is',$0A, $0D
                        db    $09,'our collective responsibility to leave it in good order for others',$0A, $0D
                        db    $09,'to have the same opportunity in the future...',$0A, $0D,0
len_gameov1a            equ $-gameov1a

gameov2a                db    $09,'The tipping point has been been reached, now events spiral slowly',$0A, $0D
                        db    $09,'out of control.',$0A,$0D,0
len_gameov2a            equ $-gameov2a
                        
gameov2b                db    $09,'The greenland ice sheet collapses, the onrush of fresh water raising',$0A, $0D
                        db    $09,'sea levels and changing global ocean currents forever.',$0A, $0D,$0A, $0D
                        db    $09,'The global disruption to weather patterns accelerates warming melting',$0A, $0D
                        db    $09,' permafrosts releasing uncontrollable amounts of extra greenhouse ',$0A, $0D
                        db    $09,'gases into the atmosphere..',$0A, $0D,0
len_gameov2b            equ $-gameov2b
            
gameov2c                db    $09,'We failed to act decisively when we had the chance and now live in ',$0A, $0D
                        db    $09,'a world with an uncertain future wracked by wildfires and unprecedented',$0A, $0D
                        db    $09,'weather patterns.',$0A, $0D,$0A, $0D
                        db    $09,'On the bright side, you do save money with not having to buy jumpers...',$0A, $0D,0
len_gameov2c            equ $-gameov2c

gameov3                 db    $09,'Your mishandling of the worlds power needs have caused chaos',$0A, $0D
                        db    $09,'around the world.',$0A, $0D,$0A, $0D
                        db    $09,'This disorder sparked old rivalries as countries began to squabble',$0A, $0D
                        db    $09,'over the sparse resources. Finally this escalated into war',$0A, $0D
                        db    $09,'and nuclear exchanges between the major power.',$0A, $0D,0
len_gameov3             equ $-gameov3

gameov3a                db    $09,'On the plus side this has solved global warming by causing',$0A, $0D
                        db    $09,'a nuclear winter sparking a mini ice age. So in a way you have',$0A, $0D
                        db    $09,'succeeded in your mandate.',$0A, $0D,0
len_gameov3a            equ $-gameov3a

gameov3b                db    $09,'This comforts you as you fight off some mutant raiders',$0A, $0D
                        db    $09,'who are attacking your survival bunker',$0A, $0D,$0A, $0D
                        db    $09,'Your secretary, now a mutant zombie groans loudly...',$0A, $0D,0
len_gameov3b            equ $-gameov3b

;Strings for winning game conditions
rockwin                 db    '*+*********************************************************************************+*',$0A, $0D
                        db    '*|                                   Victory ???                                   |*',$0A, $0D
                        db    '*+*********************************************************************************+*',$0A, $0D,0
len_rockwin             equ $-rockwin

rock                    db    $09,'The earth may not be saved but you might be. Your shrewd and',$0A, $0D
                        db    $09,'totally ethical financial decisions have paid off for you.',$0A, $0D,$0A, $0D
                        db    $09,'You now have enough cash to buy yourself a ticket on billionaires',$0A, $0D
                        db    $09,'Jeflon Mezos rocket and escape this mess.',$0A, $0D
                        db    $09,'Apparently Mars is lovely this time of year and if anything its.',$0A, $0D
                        db    $09,'atmosphere needs MORE greenhouse gases which we have proved experts',$0A, $0D
                        db    $09,'in accomodating...',$0A, $0D,$0A, $0D,0
len_rock                equ $-rock
                        
rock1                   db    $09,'1. Yes screw Earth, I can learn to speak Martian...',$0D,$0A
                        db    $09,'2. No I will use my wealth to try fix the planet we have...',$0D,$0A,0
len_rock1               equ $-rock1

again                   db    $09,'Congradulations, whether by luck, wit or ruthlessness you',$0A, $0D
                        db    $09,'have prevailed against the odds',$0A, $0D,$0A, $0D
                        db    $09,'Do you wish to really test your skill and play again on a',$0A, $0D
                        db    $09,'harder difficulty?',$0A, $0D,$0A, $0D
                        db    $09,'   1. Yes i am ready for any challenge... (Play again)',$0D,$0A
                        db    $09,'   2. No, i wish to rest...                 (End Game)',$0D,$0A,0
len_again               equ $-again

rock2                   db   $09,'You boldly break the bounds of earths problems and boldly go',$0A, $0D
                        db    $09,'where no man has gone before.',$0A, $0D
                        db    $09,'We will make our new home a paradise on Earth...err Mars.',$0A, $0D
                        db    $09,'It sounds like it has a lot of potential.',$0A, $0D,$0A, $0D
                        db    $09,'Just reading the brochure, wait...Mars has no oxygen?',0
len_rock2               equ $-rock2

;Strings for mission options

misoptions              db    '*+*********************************************************************************+*',$0A,$0D
                        db    '*|                              Available Missions                                 |*',$0A,$0D
                        db    '*+*********************************************************************************+*',$0A,$0D,$0A,$0D,0 
len_misoptions          equ $-misoptions

misoptions1             db    $09,'Your secretary presents you with a list of possible cash raising ',$0D,$0A
                        db    $09,'activities. What would you like to pick? ',$0D,$0A, $0D,$0A
                        db    $09,'1. Try raise some cash from rich donors       ',$0D,$0A
                        ;db    $09,'2. Earth Play       ',$0D,$0A
                        db    $09,'2. Retreat to menu in disgrace...                      ',$0D,$0A ,0
len_misoptions1         equ $-misoptions1

;Rocket image for secretish win outcome
;Ref: https://www.asciiart.eu/space/spaceships
elon                    db $09,$09,$09,$09,'      ___      ',$0A, $0D  
                        db $09,$09,$09,$09,'     /___\     ',$0A, $0D  
                        db $09,$09,$09,$09,'    |=   =|    ',$0A, $0D  
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'    |     |    ',$0A, $0D 
                        db $09,$09,$09,$09,'   /|##!##|\   ',$0A, $0D 
                        db $09,$09,$09,$09,'  / |##!##| \  ',$0A, $0D
                        db $09,$09,$09,$09,' /  |##!##|  \ ',$0A, $0D  
                        db $09,$09,$09,$09,'|  /       \  |',$0A, $0D  
                        db $09,$09,$09,$09,'| /         \ |',$0A, $0D  
                        db $09,$09,$09,$09,'|/           \|',$0A, $0D,0  
len_elon                equ $-elon

;Rocket images for rocket leaving earth

earthd                  db $09,'               000000000000',$0A, $0D
                        db $09,'           0088880000000000000000',$0A, $0D
                        db $09,'       0000008888888888880000000008880',$0A, $0D
                        db $09,'     00000000008888888888800000000008880',$0A, $0D
                        db $09,'   000000000088888888888000000000000088000',$0A, $0D
                        db $09,'  000000000888888888880088888800000000000000',$0A, $0D
                        db $09,' 00000000008888888888888888888880000000000000',$0A, $0D
                        db $09,'0000000000008888888888888888800000000000000000',$0A, $0D
                        db $09,'0000000000008888888888888000000000000000000000',$0A, $0D
                        db $09,'00000000000088888888888800000000000000000000000',$0A, $0D
                        db $09,'00000000000008888000000000000000000000000000000',$0A, $0D
                        db $09,'00000000000000088888800080000000000000000000000',$0A, $0D
                        db $09,'00000000000000000088888800000000000000000000000',$0A, $0D
                        db $09,'00000000000000000000008888888888000000000000000',$0A, $0D
                        db $09,'0000000000000000000000008888888888888000000000',$0A, $0D
                        db $09,' 00000000000000000000000888888888888800000000',$0A, $0D
                        db $09,'  000000000000000000000088888888888800000000',$0A, $0D
                        db $09,'   0000000000000000000000888888888880000000',$0A, $0D
                        db $09,'      0000000000000000000888888888000000',$0A, $0D
                        db $09,'        0000000000000000008888888000000 ',$0A, $0D
                        db $09,'            000000000000888888800000  ',$0A, $0D
                        db $09,'                 00000000000000  ',$0A, $0D
                        db     '                                                                                                              ',0
len_earthd              equ $-earthd

rocket1                 db '|\________ ',0
len_rocket1             equ $-rocket1

rocket2                 db '| ________)',0
len_rocket2             equ $-rocket2

rocket3                 db '|/         ',0
len_rocket3             equ $-rocket3

exhaust1                db ' - -\ \',0
len_exhaust1            equ $-exhaust1

exhaust2                db '- - = =',0
len_exhaust2            equ $-exhaust2

exhaust3                db ' - -/ /',0
len_exhaust3            equ $-exhaust3

brocket1                db '           ',0
len_brocket1            equ $-brocket1

brocket2                db '           ',0
len_brocket2            equ $-brocket2

brocket3                db '           ',0
len_brocket3            equ $-brocket3

bexhaust1               db '       ',0
len_bexhaust1           equ $-bexhaust1

bexhaust2               db '       ',0
len_bexhaust2           equ $-bexhaust2

bexhaust3               db '       ',0
len_bexhaust3           equ $-bexhaust3

;Strings for Play again Screen
vic                 db $09,$09,'____   ____.__        __                       ',$0A, $0D 
                    db $09,$09,'\   \ /   /|__| _____/  |_  ___________ ___.__.',$0A, $0D 
                    db $09,$09,' \   Y   / |  |/ ___\   __\/  _ \_  __ <   |  |',$0A, $0D 
                    db $09,$09,'  \     /  |  \  \___|  | (  <_> )  | \/\___  |',$0A, $0D 
                    db $09,$09,'   \___/   |__|\___  >__|  \____/|__|   / ____|',$0A, $0D 
                    db $09,$09,'                   \/                   \/     ',$0A, $0D,0
len_vic             equ $-vic

;Strings for Room101 cheat room
r101                db $09,$09,'   ______                        __  _____  __  ',$0A, $0D 
                    db $09,$09,'   | ___ \                      /  ||  _  |/  | ',$0A, $0D 
                    db $09,$09,'   | |_/ /___   ___  _ __ ___   `| || |/  |`| | ',$0A, $0D 
                    db $09,$09,'   |    // _ \ / _ \|  _ ` _ \   | ||  /| | | | ',$0A, $0D 
                    db $09,$09,'   | |\ \ (_) | (_) | | | | | | _| |\ |_/ /_| |_',$0A, $0D 
                    db $09,$09,'   \_| \_\___/ \___/|_| |_| |_| \___/\___/ \___/',$0A, $0D,0
len_r101            equ $-r101

cheat               db    $09,'I dont know how you found this place but use your ',$0A, $0D
                    db    $09,'powers wisely!!!',$0A, $0D,$0A, $0D
                    db    $09,'   1. Set Carbon in air to 0',$0D,$0A
                    db    $09,'   2. Set Personal cash to 500000',$0D,$0A
                    db    $09,'   3. Set Carbon in air to 40000',$0D,$0A
                    db    $09,'   4. Set Carbon in air to 30000',$0D,$0A
                    db    $09,'   5. Set Carbon in air to 20000',$0D,$0A
                    db    $09,'   6. Set Carbon in air to 10000',$0D,$0A
                    db    $09,'   7. Subtract 1000GW Non-Green Power',$0D,$0A
                    db    $09,'   8. Add 1000GW Green Power',$0D,$0A
                    db    $09,'   9. Set ppm to 476',$0D,$0A
                    db    $09,'   10. Add 100,000 to cash',$0D,$0A
                    db    $09,'   11. Trigger critical power gameend',$0D,$0A
                    db    $09,'   12. Exit',$0D,$0A,0
len_cheat           equ $-cheat

;----Image strings for event success/fail screens-----

;Forest image string for tree options events
;Ref: https://asciiart.website/index.php?art=plants/trees
forest                  db $0A, $0D,$09,$09,$09,$09,' 	 	     ,@@@@@@@,                 ',$0A, $0D
                        db $09,$09,$09,$09,'   ,,,.   ,@@@@@@/@@,  .oo8888o.       ',$0A, $0D
                        db $09,$09,$09,$09,' &%%&%&&%,@@@@@/@@@@@@,8888\88/8o      ',$0A, $0D
                        db $09,$09,$09,$09,',%&\%&&%&&%,@@@\@@@/@@@88\88888/88     ',$0A, $0D
                        db $09,$09,$09,$09,'%&&%&%&/%&&%@@\@@/ /@@@88888\88888     ',$0A, $0D
                        db $09,$09,$09,$09,'%&&%/ %&%%&&@@\ V /@@  `88\8 `/88      ',$0A, $0D
                        db $09,$09,$09,$09,'`&%\ ` /%&     |.|        \  |8        ',$0A, $0D
                        db $09,$09,$09,$09,'    |o|        | |         | |         ',$0A, $0D
                        db $09,$09,$09,$09,'    |.|        | |         | |         ',$0A, $0D
                        db $09,$09,$09,$09,' \\/ ._\//_/__/  ,\_//__\\/.  \_//__/_ ',$0A, $0D,0  
len_forest              equ $-forest

;Ref: https://www.asciiart.eu/vehicles/boats
yacht                   db $0A,$0D,09,$09,$09,'                __/___',$0A, $0D           
                        db $09,$09,$09,'         _____/______|    \o       ',$0A, $0D     
                        db $09,$09,$09,'  _______/_____\_______\___|\___   ',$0A, $0D     
                        db $09,$09,$09,'  \              < < <         |   ',$0A, $0D,0
len_yacht               equ $-yacht
   
yacht2                  db $09,$09,$09,'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ',$0A, $0D,0
len_yacht2              equ $-yacht2

forestg                 db $0A, $0D,$09,$09,$09,$09,' 	 	     ,@@@@@@@,                   ',$0A, $0D
                        db $09,$09,$09,'   ,,,.   ,@@@@@@/@@,  .oo8888o.                 ',$0A, $0D
                        db $09,$09,$09,' &%%&%&&%,@@@@@/@@@@@@,8888\88/8o                ',$0A, $0D
                        db $09,$09,$09,',%&\%&&%&&%,@@@\@@@/@@@88\88888/88               ',$0A, $0D
                        db $09,$09,$09,'%&&%&%&/%&&%@@\@@/ /@@@88888\88888               ',$0A, $0D
                        db $09,$09,$09,'%&&%/ %&%%&&@@\ V /@@  `88\8 `/88                ',$0A, $0D
                        db $09,$09,$09,'`&%\ ` /%&     |.|        \  |8                  ',$0A, $0D
                        db $09,$09,$09,'    |o|        | |         | |        \o/    o      ',$0A, $0D
                        db $09,$09,$09,'    |.|        | |         | |         |   ¬/|\     ',$0A, $0D
                        db $09,$09,$09,' \\/ ._\//_/__/  ,\_//__\\/.  \_//__/__/\___/\_______',$0A, $0D,0
len_forestg             equ $-forestg
           
;Ref: https://ascii.co.uk/art/volcano
volcano                 db $09,$09,'                     ooOOOo                                  ',$0A, $0D
                        db $09,$09,'                   oOOOOOOoooo                               ',$0A, $0D
                        db $09,$09,'                 ooOOOooo  oooo                              ',$0A, $0D
                        db $09,$09,'                /vvv\                                        ',$0A, $0D
                        db $09,$09,'               /  V V\                                       ',$0A, $0D
                        db $09,$09,'              /   V  V\                                      ',$0A, $0D
                        db $09,$09,'             /     V   \          AAAAH!  RUN FOR YOUR LIVES!',$0A, $0D
                        db $09,$09,'            /      VV   \               /                    ',$0A, $0D				
		                db $09,$09,' ____      /        VVV    \   	  o          o              ',$0A, $0D
                        db $09,$09,' /\      /        VVVV     \     /-   o     /-               ',$0A, $0D				
		                db $09,$09,'/  \   /           VVVVVVV   \  /\  -/-    /\                ',$0A, $0D
                        db $09,$09,'                     VVVVVVVVVVVVV   /\                      ',$0A, $0D,0
len_volcano             equ $-volcano

volcanos                db $09,$09,'                                                             ',$0A, $0D
                        db $09,$09,'                                                             ',$0A, $0D
                        db $09,$09,'                                                             ',$0A, $0D
                        db $09,$09,'                /\_/\                                        ',$0A, $0D
                        db $09,$09,'               /     \                                       ',$0A, $0D
                        db $09,$09,'              /       \                                      ',$0A, $0D
                        db $09,$09,'             /         \                                     ',$0A, $0D
                        db $09,$09,'            /           \      __   __    __   __            ',$0A, $0D				
		                db $09,$09,' ____      /             \    /@@\ /@;\  /@;\ /@@\           ',$0A, $0D
                        db $09,$09,' /\      /                \   \;@/ \;@/  \@@/ \@@/           ',$0A, $0D				
		                db $09,$09,'/  \   /                   \   ||   ||    ||   ||                            ',$0A, $0D
                        db $09,$09,'                                                             ',$0A, $0D,0
len_volcanos            equ $-volcanos

;Ref: https://www.asciiart.eu/buildings-and-places/other                        
nucplant                db $09,$09,'          ) ) )                     ) ) )                 ',$0A, $0D
                        db $09,$09,'        ( ( (                      ( ( (                  ',$0A, $0D
                        db $09,$09,'      ) ) )                       ) ) )                   ',$0A, $0D
                        db $09,$09,'   (~~~~~~~~~)                 (~~~~~~~~~)                ',$0A, $0D
                        db $09,$09,'    | POWER |                   | POWER |                 ',$0A, $0D
                        db $09,$09,'    |       |                   |       |                 ',$0A, $0D
                        db $09,$09,'    I      _._                  I       _._               ',$0A, $0D
                        db $09,$09,'    I    /    `\                I     /    `\             ',$0A, $0D
                        db $09,$09,'    I   |   N   |               I    |   N   |			 ',$0A, $0D		
		                db $09,$09,'    f   |   |~~~~~~~~~~~~~~|    f    |    |~~~~~~~~~~~~~~|',$0A, $0D
                        db $09,$09,'  ./    |   ||~~~~~~~~|    |  ./     |    | |~~~~~~~~|   |',$0A, $0D					
		                db $09,$09,'//______|___||__###___|____|//_______|____|_|__###___|___|',$0A, $0D,0  
len_nucplant            equ $-nucplant

oilriga                 db $09,$09,$09,'              /\                    .    ',$0A, $0D
                        db $09,$09,$09,'             /\/\                 .:;:.  ',$0A, $0D
                        db $09,$09,$09,'            _,/\,_              .:;;;;;:.',$0A, $0D
                        db $09,$09,$09,'           ,______,               ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'            ,\/\/,                ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'           \,/\/\,   ...`/:       ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'   :\...    \`.. ,  ,,  /  :      ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'   : \ ,,   ,\ ,,,  ,, /    o     ;:;;;  ',$0A, $0D
                        db $09,$09,$09,'   j _\,,__/__\,,_\_,,/___        :;;;;  ',$0A, $0D	
		                db $09,$09,$09,'     ,___________________,        ;;;:;  ',$0A, $0D
                        db $09,$09,$09,'      ,  ,   ,   ,   ,  ,         ;;:;;  ',$0A, $0D	
		                db $09,$09,$09,'~~~~~~,~~,~~~,~~~,~~~,~~,~~~~~~   ;;;;;  ',$0A, $0D,0 
len_oilriga            equ $-oilriga

;Ref: https://www.asciiart.eu/buildings-and-places/other
oilrigb                 db $09,$09,$09,'              /\                  ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'             /\/\                 ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'            _,/\,_                ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'           ,______,               ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'            ,\/\/,                ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'           \,/\/\,   ...`/:       ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'   :\...    \`.. ,  ,,  /  :      ;;;;;  ',$0A, $0D
                        db $09,$09,$09,'   : \ ,,   ,\ ,,,  ,, /    o     ;:;;;  ',$0A, $0D
                        db $09,$09,$09,'   j _\,,__/__\,,_\_,,/___       ::;;;;: ',$0A, $0D	
		                db $09,$09,$09,'     ,___________________,        :;;::  ',$0A, $0D
                        db $09,$09,$09,'      ,  ,   ,   ,   ,  ,          :;:   ',$0A, $0D	
		                db $09,$09,$09,'~~~~~~,~~,~~~,~~~,~~~,~~,~~~~~~     .    ',$0A, $0D,0
len_oilrigb             equ $-oilrigb
                      
;Ref:    https://asciiart.website/index.php?art=objects/bottles	 	
whiskey                 db $09,$09,$09,$09,'               __    ',$0A, $0D
                        db $09,$09,$09,$09,'              |__|   ',$0A, $0D
                        db $09,$09,$09,$09,'              |  |   ',$0A, $0D
                        db $09,$09,$09,$09,'              |  |   ',$0A, $0D
                        db $09,$09,$09,$09,'              |  |   ',$0A, $0D
                        db $09,$09,$09,$09,'              |  |   ',$0A, $0D
                        db $09,$09,$09,$09,' ,----.      /`-. \  ',$0A, $0D
                        db $09,$09,$09,$09,'|      |    /-._|  \ ',$0A, $0D
                        db $09,$09,$09,$09,'|`---- |   |        |',$0A, $0D	
		                db $09,$09,$09,$09,'\      /   |`-...   |',$0A, $0D
                        db $09,$09,$09,$09,' `.  ,/    |  |  |  |',$0A, $0D	
		                db $09,$09,$09,$09,'   ||      |`XXX |  |',$0A, $0D
                        db $09,$09,$09,$09,' ,-||-.    |`-...|  |',$0A, $0D
                        db $09,$09,$09,$09,'(  ¬¬  )   |        |',$0A, $0D 
                        db $09,$09,$09,$09,' `----      `-....-   ',$0A, $0D,0
len_whiskey             equ $-whiskey

;Ref :   https://www.asciiart.eu/nature/sun
sun                     db $0A,$0D,$0A,$0D,$09,$09,$09,$09,'       ',$0A, $0D
                        db $09,$09,$09,$09,'   .   \_,!,_/   ,     ',$0A, $0D
                        db $09,$09,$09,$09,'    `.,.88888`.,/      ',$0A, $0D
                        db $09,$09,$09,$09,'     /888888888\       ',$0A, $0D
                        db $09,$09,$09,$09,'~ --:8888888888 : -- ~ ',$0A, $0D
                        db $09,$09,$09,$09,'     \888888888/       ',$0A, $0D
                        db $09,$09,$09,$09,'    ,/`.888888/`.      ',$0A, $0D
                        db $09,$09,$09,$09,'   /   / `!` \   `     ',$0A, $0D
                        db $09,$09,$09,$09,'      /   :   \        ',$0A, $0D,0
len_sun                 equ $-sun

;Ref :   https://www.asciiart.eu/buildings-and-places/windmills
wmill                   db $09,$09,$09,$09,'            ,-_                  (`  ).    ',$0A, $0D
                        db $09,$09,$09,$09,'            |-_.-,              (     ).   ',$0A, $0D
                        db $09,$09,$09,$09,'            |-_.-.           _(        .`. ',$0A, $0D
                        db $09,$09,$09,$09,'   _        |-_./        .=(`(      .     )',$0A, $0D
                        db $09,$09,$09,$09,'  /;-,_     |-_.        (     (.__.:-`-_.. ',$0A, $0D
                        db $09,$09,$09,$09,' /-.-;,-,___|.          `(       ) )       ',$0A, $0D
                        db $09,$09,$09,$09,'/;-;-;-;_;_/|\_ _ _ _ _   ` __.:.   )      ',$0A, $0D
                        db $09,$09,$09,$09,'     ( __`|_P_|`-;-;-;,|        `--.       ',$0A, $0D
                        db $09,$09,$09,$09,'      \    |||   `-;-;-.                   ',$0A, $0D
                        db $09,$09,$09,$09,'       \   -_|      \-/                    ',$0A, $0D
                        db $09,$09,$09,$09,'        \ /-_|                             ',$0A, $0D
                        db $09,$09,$09,$09,'         ,.-_|                             ',$0A, $0D
                        db $09,$09,$09,$09,'         |.-_|                             ',$0A, $0D 
                        db $09,$09,$09,$09,'         |  |                              ',$0A, $0D
                        db $09,$09,$09,$09,'         |  |                              ',$0A, $0D
                        db $09,$09,$09,$09,'	        |  |                              ',$0A, $0D,0	
len_wmill               equ $-wmill

;Ref: https://www.asciiart.eu/nature/tornado 
storm                   db $09,$09,$09,$09,'   ((  ,####@@!!$$    ))              ',$0A, $0D  
                        db $09,$09,$09,$09,'       `#####@@!$$`  ))               ',$0A, $0D  
                        db $09,$09,$09,$09,'    ((  .####@!!$:                    ',$0A, $0D  
                        db $09,$09,$09,$09,'   ((  ,####@!!$:   ))                ',$0A, $0D
                        db $09,$09,$09,$09,'       .###@!!$:                      ',$0A, $0D
                        db $09,$09,$09,$09,'       `##@@!$:                       ',$0A, $0D
                        db $09,$09,$09,$09,'        `#@!!$                        ',$0A, $0D
                        db $09,$09,$09,$09,'  !@#    `#@!$:       @#$       \ /   ',$0A, $0D
                        db $09,$09,$09,$09,'   #$     `#@!$:       !@!       X    ',$0A, $0D
                        db $09,$09,$09,$09,'            .@!$:               /|\   ',$0A, $0D  
                        db $09,$09,$09,$09,'        .`\   ,!$: /`.           |    ',$0A, $0D  
                        db $09,$09,$09,$09,'           .\  .!: /.            |    ',$0A, $0D  
                        db $09,$09,$09,$09,'             ,\ : /,             |    ',$0A, $0D
                        db $09,$09,$09,$09,'  -.,-/\\\-.,//.-,/:`\.,-.JrS,.,-=_\\ ',$0A, $0D
                        db $09,$09,$09,$09,', -.,-.\\,-.,//.-,.`-.,_\\-.,.-\,.-// ',$0A, $0D,0
len_storm               equ $-storm

;Ref: https://emojicombos.com/electricity-ascii-art , https://ascii.co.uk/art/science
atom                    db $09,$09,'                                                        ⢀⣠⡴⠂',$0A, $0D
                        db $09,$09,'                                                    ⣀⣤⣾⡿⠋',$0A, $0D
                        db $09,$09,'     __                                        ⢀⣠⣶⣿⣿⠟⠋',$0A, $0D
                        db $09,$09,'     ||                                     ⣠⣶⣿⣿⠟⠋',$0A, $0D
                        db $09,$09,'    ====                                 ⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋',$0A, $0D
                        db $09,$09,'    |  |__                                  ⣠⣴⣿⣿⣿⣿⣿⣿⠟⠉',$0A, $0D
                        db $09,$09,'   |  |-.\                              ⣠⣴⣿⣿⣿⣿⣿⣿⠿⠋',$0A, $0D
                        db $09,$09,'    |__|  \\                          ⣠⣶⣿⣿⣿⣿⣿⣿⡿⠋⠁',$0A, $0D
                        db $09,$09,'     ||   ||                        ⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⣤⣤⣤⡤',$0A, $0D
                        db $09,$09,'   ======__|                              ⢀⣤⣿⣿⣿⣿⣿⣿⠿⠋⠁',$0A, $0D
                        db $09,$09,'  ________||__                         ⢀⣴⣿⣿⣿⣿⠿⠛⠉⠀⠀',$0A, $0D
                        db $09,$09,' /____________\                     ⣠⣴⣿⣿⠿⠛⠉⠀',$0A, $0D
                        db $09,$09,'                                ⠀⣠⣾⡿⠟⠋⠁',$0A, $0D
                        db $09,$09,'                              ⠠⠞⠋⠁',$0A, $0D,0
len_atom                equ $-atom

;Ref: https://ascii.co.uk/art/gavel 
gavel                   db $09,$09,$09,$09,$09,'   ___________                          ',$0A, $0D  
                        db $09,$09,$09,$09,$09,'   \         /                          ',$0A, $0D  
                        db $09,$09,$09,$09,$09,'    )_______(                           ',$0A, $0D  
                        db $09,$09,$09,$09,$09,'    |||||||||_.-._|.---------.|_.-._    ',$0A, $0D
                        db $09,$09,$09,$09,$09,'    |       | | |               | | ..-.',$0A, $0D
                        db $09,$09,$09,$09,$09,'    |       |_| |_             _| |_..-.',$0A, $0D
                        db $09,$09,$09,$09,$09,'    |_______| .-. `.---------.` .-.     ',$0A, $0D
                        db $09,$09,$09,$09,$09,'    )|||||||(                           ',$0A, $0D
                        db $09,$09,$09,$09,$09,'   /_________\                          ',$0A, $0D
                        db $09,$09,$09,$09,$09,'   `.-------.`                          ',$0A, $0D  
                        db $09,$09,$09,$09,$09,' .-------------.                        ',$0A, $0D  
                        db $09,$09,$09,$09,$09,'/_______________\        	           ',$0A, $0D,0 
len_gavel                equ $-gavel

;Ref: https://ascii.co
judge                   db $09,$09,'       ___                                     ',$0A, $0D  
                        db $09,$09,'      /_ _\                                    ',$0A, $0D  
                        db $09,$09,'     ( - - )                                   ',$0A, $0D  
                        db $09,$09,'    __\ _ /__                                  ',$0A, $0D
                        db $09,$09,'   /   \_/   \                     __          ',$0A, $0D
                        db $09,$09,'__/___________\__                  //\\        ',$0A, $0D
                        db $09,$09,'|     JUDGE      |\    ____       (//\\)       ',$0A, $0D
                        db $09,$09,'|   I.L. Ovtree  ||   / ___)      _\__/_       ',$0A, $0D
                        db $09,$09,'|                ||   (.~O=O     /      \      ',$0A, $0D
                        db $09,$09,'|                ||   _\_-/     / /    \ \     ',$0A, $0D  
                        db $09,$09,'|                ||  /  \|/\    \_\     |/     ',$0A, $0D  
                        db $09,$09,'|                ||_/_|____|\__  (/_____|      ',$0A, $0D  
                        db $09,$09,'|                |   WITNESS  |\  | _ _ |      ',$0A, $0D
                        db $09,$09,'|                |            ||__|  |  |_____ ',$0A, $0D
                        db $09,$09,'|________________|____________|/  |__|__|      ',$0A, $0D
                        db $09,$09,'                                 (__/ \__)     ',$0A, $0D,0
len_judge               equ $-judge

;Euro image String
;Ref:  https://www.asciiart.eu/miscellaneous/money
euro                    db $09,$09,$09,'              _.-------._    ',$0A, $0D
                        db $09,$09,$09,'           _-;_.------._ `-_ ',$0A, $0D  
                        db $09,$09,$09,'         _- _-          `-_/ ',$0A, $0D  
                        db $09,$09,$09,'        -  -                 ',$0A, $0D
                        db $09,$09,$09,'    ___/  /______________    ',$0A, $0D
                        db $09,$09,$09,'   /___  .______________/    ',$0A, $0D
                        db $09,$09,$09,'    ___| |_____________      ',$0A, $0D  
                        db $09,$09,$09,'   /___  .____________/      ',$0A, $0D  
                        db $09,$09,$09,'       \  \                  ',$0A, $0D
                        db $09,$09,$09,'        -_ -_             /| ',$0A, $0D
                        db $09,$09,$09,'          -_ -._        _- | ',$0A, $0D
                        db $09,$09,$09,'             -._ `------;_./ ',$0A, $0D
                        db $09,$09,$09,'               `-------;     ',$0A, $0D,0
len_euro                equ $-euro

;Ref: https://ascii.co.uk/art/arrow
arrow                   db $09,$09,$09,$09,'       .    ',$0A, $0D   
                        db $09,$09,$09,$09,'     .:::.  ',$0A, $0D     
                        db $09,$09,$09,$09,'   .:::::::.',$0A, $0D     
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D     
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D     
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D   
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D  
                        db $09,$09,$09,$09,'     |||||  ',$0A, $0D,0 
len_arrow               equ $-arrow

;Ref: https://www.asciiart.eu/people/occupations/police
riot                    db $09,$09,'         /....\          /....\          /....\         ',$0A, $0D   
                        db $09,$09,' .-.    |::::::|    .-. |::::::|    .-. |::::::|        ',$0A, $0D   
                        db $09,$09,' | |    |::::::|    | | |::::::|    | | |::::::|        ',$0A, $0D   
                        db $09,$09,' | |    (`:  : )    | | (`:  : )    | | (`:  : )        ',$0A, $0D   
                        db $09,$09,' | |   _--|__|--__  | |.--|__|--__  | |_--|__|--__      ',$0A, $0D   
                        db $09,$09,' | |  |   ________|_|_|_  ________|_|_|_  ________|_____',$0A, $0D   
                        db $09,$09,' | | /    |            |  |            |  |            |',$0A, $0D   
                        db $09,$09,' | |/  /  |            |  |            |  |            |',$0A, $0D   
                        db $09,$09,' |_| |/|  |            |  |            |  |            |',$0A, $0D   
                        db $09,$09,'(===)| |  |   POLICE   |  |   POLICE   |  |   POLICE   |',$0A, $0D   
                        db $09,$09,'`===   |`-|            |`-|            |`-|            |',$0A, $0D   
                        db $09,$09,' | |   |`-|            |`-|            |`-|            |',$0A, $0D   
                        db $09,$09,' |_|   |  |            |  |            |  |            |',$0A, $0D  
                        db $09,$09,'       |___`-__________- __`-__________- __`-__________-',$0A, $0D,$0A, $0D,0
len_riot                equ $-riot

;Ref: https://www.asciiart.eu/weapons/explosives
nuc                     db $09,$09,'             ____/ (  (    )   )  \___          ',$0A, $0D 
                        db $09,$09,'           /( (  (  )   _    ))  )   )\         ',$0A, $0D 
                        db $09,$09,'         ((     (   )(    )  )   (   )  )       ',$0A, $0D 
                        db $09,$09,'       ((/  ( _(   )   (   _) ) (  () )  )      ',$0A, $0D 
                        db $09,$09,'      ( (  ( (_)   ((    (   )  .((_ ) .  )_    ',$0A, $0D 
                        db $09,$09,'     ( (  )    (      (  )    )   ) . ) (   )   ',$0A, $0D 
                        db $09,$09,'   ( (  ( \ ) (    (_  ( ) ( )  )   ) )  )) ( ) ',$0A, $0D 
                        db $09,$09,'   ( (  ( (  (  )     (_  )  ) )  _)   ) _( ( ) ',$0A, $0D 
                        db $09,$09,'    ((  (   )(    (     _    )   _) _(_ (  (_ ) ',$0A, $0D 
                        db $09,$09,'     (_((__(_(__(( ( ( |  ) ) ) )_))__))_)___)  ',$0A, $0D 
                        db $09,$09,'     ((__)        \\||lll|l||///          \_))  ',$0A, $0D
                        db $09,$09,'              (   /(/ (  )  ) )\   )            ',$0A, $0D  
                        db $09,$09,'            (    ( ( ( | | ) ) )\   )           ',$0A, $0D  
                        db $09,$09,'             (   /(| / ( )) ) ) )) )            ',$0A, $0D  
                        db $09,$09,'           (        |(||(||)||||        )       ',$0A, $0D                    
                        db $09,$09,'           (     //|/l|||)|\\ \     )           ',$0A, $0D  
                        db $09,$09,'           (/ / //  /|//||||\\  \ \  \ _)       ',$0A, $0D ,0 
len_nuc                 equ $-nuc

;Ref: https://www.asciiart.eu/holiday-and-events/luck 

;Ref: https://www.asciiart.eu/animals/bears
polar                   db $09,$09,$09,'  _,-""`""-~`)                             ',$0A, $0D  
                        db $09,$09,$09,'(`~_,=========\                            ',$0A, $0D 
                        db $09,$09,$09,' |---,___.-.__,\                           ',$0A, $0D 
                        db $09,$09,$09,' |        o     \ ___  _,,,,_     _.--.    ',$0A, $0D 
                        db $09,$09,$09,'  \      `^`    /`_.-"~      `~-;`     \   ',$0A, $0D 
                        db $09,$09,$09,'   \_      _  .                  `,     |  ',$0A, $0D 
                        db $09,$09,$09,'     |`-                           \ __/   ',$0A, $0D 
                        db $09,$09,$09,'    /                      ,_       \  ` -.',$0A, $0D  
                        db $09,$09,$09,'   /    .-""~~--.            `"-,   ;_    /',$0A, $0D 
                        db $09,$09,$09,'  |              \               \  | `""` ',$0A, $0D 
                        db $09,$09,$09,'   \__.-- `"-.   /_               |        ',$0A, $0D 
                        db $09,$09,$09,'              `"`  `~~~---..,     |        ',$0A, $0D
                        db $09,$09,$09,'                             \ _.- `-.     ',$0A, $0D  
                        db $09,$09,$09,'                              \       \    ',$0A, $0D  
                        db $09,$09,$09,'                                .     /    ',$0A, $0D  
                        db $09,$09,$09,'                                 `"~"`	  ',$0A, $0D,0
len_polar               equ $-polar

;Ref:        	https://www.asciiart.eu/nature/sun
ssun                    db $09,$09,$09,'              :             ',$0A, $0D  
                        db $09,$09,$09,'     `.       ;        ..   ',$0A, $0D 
                        db $09,$09,$09,'       `.  .-"""-.   ..     ',$0A, $0D 
                        db $09,$09,$09,'         ;.  __   _;.       ',$0A, $0D 
                        db $09,$09,$09,'        /   /_    _`\       ',$0A, $0D 
                        db $09,$09,$09,'       |  _( a (  a  |      ',$0A, $0D 
                        db $09,$09,$09,'  .....| (_)    >    |``````',$0A, $0D 
                        db $09,$09,$09,'        \    \    / /       ',$0A, $0D  
                        db $09,$09,$09,'         `.   `--...        ',$0A, $0D 
                        db $09,$09,$09,'        ./ `-,,,-. `.       ',$0A, $0D 
                        db $09,$09,$09,'      ./      :      `.     ',$0A, $0D
                        db $09,$09,$09,'                            ',$0A, $0D,0
len_ssun                equ $-ssun
 
;https://www.asciiart.eu/miscellaneous/fire-extinguishers
flame                   db $09,$09,'             \/ ,".). .,     \/.  ,               ',$0A, $0D  
                        db $09,$09,'        .  . , .|// / (/ ,.   . ,\(   /           ',$0A, $0D 
                        db $09,$09,'      .    |\/ \|  \\,. ,... ||  \\,  .           ',$0A, $0D 
                        db $09,$09,'      , . .| \_ |\/ |#\_/#| /_|\_ |#\_/,) .,. ,   ',$0A, $0D 
                        db $09,$09,'    #|(,   )  \\\#\ \##/  ||/ \#\  \###/\ \/ \/. ,',$0A, $0D 
                        db $09,$09,'    || ) | \ |/ /#/ |#( \|\\|  |#/ /##(  )|  \\;/ ',$0A, $0D 
                        db $09,$09,'     \ |.\\ |\_/#| /#)|##\ \\_/#| |######\ \\/#|  ',$0A, $0D 
                        db $09,$09,'      \\_/# |#\##/#                    #@#\ /#/   ',$0A, $0D  
                        db $09,$09,'       ##/#  ##\/#@####@@###V###\/#@####@@#Y###   ',$0A, $0D 
                        db $09,$09,'     :Y##@@##@##@@###@@@###@##@@###@@@###@####:   ',$0A, $0D 
                        db $09,$09,'    :;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;:  ',$0A, $0D,0 
len_flame               equ $-flame
                       

;String for warning about cutting energy below world power needs
;Ref:   https://www.asciiart.eu/nature/lightnin
light                   db $09,$09,$09,$09,'   .-=-.-)  ) -. ',$0A, $0D  
                        db $09,$09,$09,$09,'  /   (  .  .   \',$0A, $0D 
                        db $09,$09,$09,$09,'  \ (   ,_) ) \_/',$0A, $0D 
                        db $09,$09,$09,$09,'   (_ , /\  ,_/  ',$0A, $0D 
                        db $09,$09,$09,$09,'      --\ `\--`  ',$0A, $0D 
                        db $09,$09,$09,$09,'        _\ _\    ',$0A, $0D 
                        db $09,$09,$09,$09,'        `\ \     ',$0A, $0D 
                        db $09,$09,$09,$09,'         _\_\    ',$0A, $0D 
                        db $09,$09,$09,$09,'         `\\     ',$0A, $0D 
                        db $09,$09,$09,$09,'           \\    ',$0A, $0D 
                        db $09,$09,$09,$09,'       -. .`\. .-',$0A, $0D,0
len_light               equ $-light

;Strings for testing purposes(not used in main game)
tes                     db      'Test Win C'
len_tes                 equ     $-tes

tes1                     db      'Test Lose C',0
len_tes1                 equ     $-tes1


tes2                     db      'Test Lose C',0
len_tes2                equ     $-tes2

;Strings for continue text (used with continue subroutine)
pressspace              db      $09,$09,$09,'   Press Spacebar to Continue: ',0
len_pressspace          equ     $-pressspace


pressend                db      $09,$09,$09,'   Press Spacebar to End Game: ',0
len_pressend            equ     $-pressend

;Endline String
endl                    db      $0D,$0A,0                    ;Carriage return = $0D, line feed $0A 
len_endl                equ     $-endl

;Tab String
tab                     db      $09,0                        ;Tab = $09
len_tab                 equ     $-tab

;-------Strings for maze mini game-------------

;Maze String
;Ref: https://www.asciiart.eu/art-and-design/mazes
maze                    db '*********************************************************************************',$0A, $0D
                        db '* Time Remaining:                                                               *',$0A, $0D
                        db '*********************************************************************************',$0A, $0D
                        db '*       +-----------------------------+----------------------------------+      *',$0A, $0D                             
                        db '*       |                             |                                  |      *',$0A, $0D
		                db '*       |    +    +    +---------     |     ------------------------+    |      *',$0A, $0D
		                db '*       |    |    |    |              |                             |    |      *',$0A, $0D
		                db '*       |    |    +----+--------------+    +-------------------+    |    |      *',$0A, $0D
		                db '*       |    |                             |                   |    |    |      *',$0A, $0D
	                    db '*       |    +--------------+--------------+----     +---------+    |    |      *',$0A, $0D
		                db '*       |    |              |                        |         |    |    |      *',$0A, $0D
		                db '*       |    +---------     |    +    +---------+    |    +    |    +----+      *',$0A, $0D
		                db '*       |    |              |    |    |         |    |    |    |         |      *',$0A, $0D
		                db '*       |    |     ---------+    |    +----     |    |    |    |    +    |      *',$0A, $0D
		                db '*       |    |                   |    |         |    |    |    |    |    |      *',$0A, $0D
	                    db '*       |    +-------------------+    |     ----+    |    |    |    |    |      *',$0A, $0D
		                db '*       |                             |              |    |    |    |    |      *',$0A, $0D
	                    db '*       :--------------+---------+    +--------------+    |    +----+    |      *',$0A, $0D                                                                    
                        db '*       |              |         |    |                   |    |         |      *',$0A, $0D
                        db '*       |    +    +    |    +    |    |    +--------------+    +----     |      *',$0A, $0D
                        db '*       |    |    |    |    |    |    |    |              |              |      *',$0A, $0D
                        db '*       |    |    |    +    |    |    |    |     ---------+---------+    |      *',$0A, $0D
                        db '*       |    |    |         |    |    |    |                        |    |      *',$0A, $0D
		                db '*       |    |    +---------+----+    |    |    +---------+    +    |    |      *',$0A, $0D
		                db '*       |    |                        |    |    |         |    |    |    |      *',$0A, $0D
		                db '*       |    +---------+--------------+    |    |    +    |    |    |    |      *',$0A, $0D
		                db '*       |    |         |              |    |    |    |    |    |    |    |      *',$0A, $0D
		                db '*       |    +    +    +---------     |    |    +----+    |    +----+    |      *',$0A, $0D
		                db '*       |         |                   |    |              |              |      *',$0A, $0D
		                db '*       +---------+-------------------+    +--------------+--------------+      *',$0A, $0D
                        db '********************************************************************************',$0A, $0D,0
len_maze                equ     $-maze

;Walls array, contains array of wall locations for enemy+player collision detection
walls                   dw $0904, $0A04, $0B04, $0C04, $0D04, $0E04, $0F04, $1004, $1104, $1204, $1304, $1404, $1504, $1604, $1704 
                        dw $1804, $1904, $1A04, $1B04, $1C04, $1D04, $1E04, $1F04, $2004, $2104, $2204, $2304, $2404, $2504, $2604
                        dw $2704, $2804, $2904, $2A04, $2B04, $2C04, $2D04, $2E04, $2F04, $3004, $3104, $3204, $3304, $3404, $3504
                        dw $3604, $3704, $3804, $3904, $3A04, $3B04, $3C04, $3D04, $3E04, $3F04, $4004, $4104, $4204, $4304, $4404 
                        dw $4504, $4604, $4704, $4804, $4904, $4A04, $0905, $2705, $4A05, $0906, $0E06, $1306, $1806, $1906, $1A06 
                        dw $1B06, $1C06, $1D06, $1E06, $1F06, $2006, $2106, $2706, $2D06, $2E06, $2F06, $3006, $3106, $3206, $3306
                        dw $3406, $3506, $3606, $3706, $3806, $3906, $3A06, $3B06, $3C06, $3D06, $3E06, $3F06, $4006, $4106, $4206
                        dw $4306, $4406, $4506, $4A06, $0907, $0E07, $1307, $1807, $2707, $4507, $4A07, $0908, $0E08, $1308, $1408
                        dw $1508, $1608, $1708, $1808, $1908, $1A08, $1B08, $1C08, $1D08, $1E08, $1F08, $2008, $2108, $2208, $2308 
                        dw $2408, $2508, $2608, $2708, $2C08, $2D08, $2E08, $2F08
                        dw $3008, $3108, $3208, $3308, $3408, $3508, $3608, $3708, $3808, $3908, $3A08, $3B08, $3C08, $3D08, $3E08
                        dw $3F08, $4008, $4508, $4A08, $0909, $0E09, $2C09, $4009, $4509, $4A09, $090A, $0E0A, $0F0A, $100A, $110A
                        dw $120A, $130A, $140A, $150A, $160A, $170A, $180A, $190A, $1A0A, $1B0A, $1C0A, $1D0A, $1E0A, $1F0A, $200A
                        dw $210A, $220A, $230A, $240A, $250A, $260A, $270A, $280A, $290A, $2A0A, $2B0A, $2C0A, $2D0A, $2E0A, $2F0A
                        dw $300A, $360A, $370A, $380A, $390A, $3A0A, $3B0A, $3C0A, $3D0A, $3E0A, $3F0A, $400A, $450A, $4A0A, $090B
                        dw $090B, $0E0B, $1D0B, $360B, $400B, $450B, $4A0B, $090C, $0E0C, $0F0C, $100C, $110C, $120C, $130C, $140C
                        dw $150C, $160C, $170C, $1D0C, $220C, $270C, $280C, $290C, $2A0C, $2B0C, $2C0C, $2D0C, $2E0C, $2F0C, $300C
                        dw $310C, $360C, $3B0C, $400C, $450C, $460C, $470C, $480C, $490C, $4A0C, $090D, $0E0D, $1D0D, $220D, $270D
                        dw $310D, $360D, $3B0D, $400D, $4A0D, $090E, $0E0E, $140E, $150E, $160E
                        dw $170E, $180E, $190E, $1A0E, $1B0E, $1C0E, $1D0E, $220E, $270E, $280E, $290E, $2A0E, $2B0E, $310E, $360E
                        dw $3B0E, $400E, $450E, $4A0E, $090F, $0E0F, $220F, $270F, $310F, $360F, $3B0F, $400F, $450F, $4A0F, $0910  
                        dw $0E10, $0F10, $1010, $1110, $1210, $1310, $1410, $1510, $1610, $1710, $1810, $1910, $1A10, $1B10, $1C10 
                        dw $1D10, $1E10, $1F10, $2010, $2110, $2210, $2710, $2D10, $2E10, $2F10, $3010, $3110, $3610, $3B10, $4010 
                        dw $4510, $4A10, $0911, $2711, $3611, $3B11, $4011, $4511, $4A11, $0912, $0A12, $0B12, $0C12, $0D12, $0E12
                        dw $0F12, $1012, $1112, $1212, $1312, $1412, $1512, $1612, $1712, $1812, $1912, $1A12, $1B12, $1C12, $1D12
                        dw $1E12, $1F12, $2012, $2112, $2212, $2712, $2812, $2912, $2A12, $2B12, $2C12, $2D12, $2E12, $2F12, $3012
                        dw $3112, $3212, $3312, $3412, $3512, $3612, $3B12, $4012, $4112, $4212, $4312, $4412, $4A12, $0913, $1813
                        dw $2213, $2713, $3B13, $4013, $4A13, $0914, $0E14, $1314, $1814, $1D14, $2214, $2714, $2C14, $2D14, $2E14
                        dw $2F14, $3014, $3114, $3214, $3314, $3414, $3514, $3614, $3714, $3814, $3914, $3A14, $3B14, $4014, $4114
                        dw $4214, $4314, $4414, $4A14, $0915, $0E15, $1315, $1815, $1D15, $2215, $2715, $2C15, $3B15, $4A15, $0916
                        dw $0E16, $1316, $1816, $1D16, $2216, $2716, $2C16, $3216, $3316, $3416, $3516, $3616, $3716, $3816, $3916
                        dw $3A16, $3B16, $3C16, $3D16, $3E16, $3F16, $4016, $4116, $4216, $4316, $4416, $4516, $4A16, $0917, $0E17
                        dw $1317, $1D17, $2217, $2717, $2C17, $4517, $4A17, $0918, $0E18, $1318, $1418, $1518, $1618, $1718, $1818
                        dw $1918, $1A18, $1B18, $1C18, $1D18, $1E18, $1F18, $2018, $2118, $2218, $2718, $2C18, $3118, $3218, $3318
                        dw $3418, $3518, $3618, $3718, $3818, $3918, $3A18, $3B18, $4018, $4518, $4A18, $0919, $0E19, $2719, $2C19
                        dw $3119, $3B19, $4019, $4519, $4A19, $091A, $0E1A, $0F1A, $101A, $111A, $121A, $131A, $141A, $151A, $161A
                        dw $171A, $181A, $191A, $1A1A, $1B1A, $1C1A, $1D1A, $1E1A, $1F1A, $201A, $211A, $221A, $231A, $241A, $251A
                        dw $261A, $271A, $2C1A, $311A, $361A, $3B1A, $401A, $451A, $4A1A, $091B, $0E1B, $181B, $271B, $2C1B, $311B, $361B, $3B1B
                        dw $401B, $451B, $4A1B, $091C, $0E1C, $131C, $181C, $191C, $1A1C, $1B1C, $1C1C, $1D1C, $1E1C, $1F1C, $201C
                        dw $211C, $271C, $2C1C, $311C, $321C, $331C, $341C, $351C, $361C, $3B1C, $401C, $411C, $421C, $431C, $441C
                        dw $451C, $4A1C, $091D, $131D, $271D, $2C1D, $3B1D, $4A1D, $091E, $0A1E, $0B1E, $0C1E, $0D1E, $0E1E, $0F1E
                        dw $101E, $111E, $121E, $131E, $141E, $151E, $161E, $171E, $181E, $191E, $1A1E, $1B1E, $1C1E, $1D1E, $1E1E
                        dw $1F1E, $201E, $211E, $221E, $231E, $241E, $251E, $261E, $271E, $2C1E, $2D1E, $2E1E, $2F1E, $301E, $311E
                        dw $321E, $331E, $341E, $351E, $361E, $371E, $381E, $391E, $3A1E, $3B1E, $3C1E, $3D1E, $3E1E, $3F1E, $401E
                        dw $411E, $421E, $431E, $441E, $451E, $461E, $471E, $481E, $491E, $4A1E, $3C1F, $271F, $281F, $291F, $2A1F
                        dw $2B1F, $2C1F
len_wall                equ     666  

;Array of different player start locations, cycles every six games
playerstart             dw $341B, $1A1B, $291B, $1A1B, $290D, $2A1E 
len_playerstart         equ     5  

;Array of different target locations, cycles every six games
targetstart             dw $1A1B, $480B, $1A1B, $280D, $341B, $1B07
len_targetstart         equ     5  

;Player symbol string
player                  db 'o',0
len_player              equ     $-player

;Enermy symbol string
enemy                   db 'O',0
len_enemy               equ     $-enemy

;Target symbol string
target                  db 'X',0
len_target              equ     $-target

;Blank symbol string
blank                   db ' ',0
len_blank               equ     $-blank

;Strings for story text of maze minigame
mazeintro1              db $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db '*|                             Green Gallop!                                       |*',$0A, $0D
                        db '*+*********************************************************************************+*',$0A,$0D,$0A,$0D,0
len_mazeintro1          equ     $-mazeintro1

mazeintro2              db $09,'A rich billionaire has invited you to a party to discuss donating',$0A, $0D
                        db $09,'money to help the global effort solving this crisis. Unfortunately ',$0A, $0D
                        db $09,'due to a bad genetic roll of the dice, you have a terrible sense of',$0A, $0D
                        db $09,'direction and get totally lost on the way, arriving three hours late!!!',$0A, $0D
                        db $09,'Here at last you run to the door only for the butler to tell you your',$0A, $0D
                        db $09,'potential cash cow is about to leave. Worse you managed to forget ',$0A, $0D
                        db $09,'your invitation so you push past the startled butler and race ',$0A, $0D
                        db $09,'to find your target. From the butlers shouts he seems pretty',$0A, $0D
                        db $09,'angry so avoiding him is vital!',$0A, $0D,$0A, $0D
                        db $09,'Find your donor before the time runs out while avoiding the angry',$0A, $0D
                        db $09,'butler and you still have a chance at getting paid! Hurry!',$0A, $0D,$0A, $0D,0
len_mazeintro2          equ     $-mazeintro2

mazeintro3              db $09,$09,$09,'Controls',$0A, $0D,$0A, $0D
                        db $09,$09,$09,'A to move left',$0A, $0D
                        db $09,$09,$09,'D to move right',$0A, $0D
                        db $09,$09,$09,'W to move up',$0A, $0D
                        db $09,$09,$09,'S to move down',$0A, $0D
                        db $09,$09,$09,'Press Q to quit at any time',$0A, $0D,$0A,$0D,0
len_mazeintro3          equ     $-mazeintro3

mazeintro4              db $09,$09,$09,'X is the location of your target',$0A, $0D,0
len_mazeintro4          equ     $-mazeintro4

mazeintro5              db $09,$09,$09,'O is the rather scary butler',$0A, $0D,0
len_mazeintro5          equ     $-mazeintro5

;Strings for the win+lose screens for maze mini games
minialose               db $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db '*|                                Game Over!                                       |*',$0A, $0D
                        db '*+*********************************************************************************+*',$0A, $0D,0
len_minialose           equ     $-minialose

minialose1              db $09,'You arrive at the room the butler told you your potential',$0A, $0D
                        db $09,'donor was in completely out of breath. Out of the window you watch ',$0A, $0D
                        db $09,'dismayed to see your potential donor climbing into a rather ',$0A, $0D
                        db $09,'enviromentally unfriendly looking helicopter. On the brightside',$0A, $0D
                        db $09,'all the running means you wont have to go to the gym for a while.',$0A, $0D
                        db $09,' Your secretary is not going to be happy...',$0A, $0D,0
len_minialose1          equ     $-minialose1

minialose2              db $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db '*|                                Game Over!                                       |*',$0A, $0D
                        db '*+*********************************************************************************+*',$0A, $0D,0
len_minialose2          equ     $-minialose2

minialose21             db $09,'The butler catches up to you before you reach your potential',$0A, $0D
                        db $09,'donor. He seems pretty angry and proceeds to throw you',$0A, $0D
                        db $09,'out of the party.',$0A, $0D,$0A, $0D
                        db $09,'You do manage to snag a free bottle of champagne on the way out.',$0A, $0D
                        db $09,'Hopefully that will appease you secretary somewhat...',$0A, $0D,0
len_minialose21         equ     $-minialose21

          
miniawin                db $0A, $0D,$0A, $0D,'*+*********************************************************************************+*',$0A, $0D
                        db '*|                               Success!!                                         |*',$0A, $0D
                        db '*+*********************************************************************************+*',$0A, $0D,0
len_miniawin            equ     $-miniawin

miniawin1               db $09,'You make it just as your cashcow, erm i mean generous philanthropist ',$0A, $0D
                        db $09,'was just leaving the party. After you catch your breath you explain',$0A, $0D
                        db $09,'your great endeavour to save the planet. You must have said the right',$0A, $0D
                        db $09,'thing because he pledges to sell one of his older superyachts as soon',$0A, $0D
                        db $09,'as he can remember where he parked it and make a large donation. His',$0A, $0D
                        db $09,'only condition is to strictly keep the phrases tax writeoff and ',$0A, $0D
                        db $09,'tax evasion off the press releases. What a lovely man...',$0A, $0D,0
len_miniawin1           equ     $-miniawin1



;--Terminal Escapes Strings(decimal 27=ESC)--

;Color escape sequences
terminal_red            db      27,"[31m"                  ;escape string 31: Red Text
len_terminal_red        equ     $-terminal_red

terminal_green          db      27,"[32m"                  ;escape string 32: Green Text
len_terminal_green      equ     $-terminal_green

terminal_yellow         db      27,"[33m"                  ;escape string 33: yellow Text
len_terminal_yellow     equ     $-terminal_yellow

terminal_blue           db      27,"[34m"                  ;escape string 34: blue Text
len_terminal_blue       equ     $-terminal_blue

terminal_magenta        db      27,"[35m"                  ;escape string 35: magenta Text
len_terminal_magenta    equ     $-terminal_magenta

terminal_cyan           db      27,"[36m"                  ;escape string 36: cyan Text
len_terminal_cyan       equ     $-terminal_cyan

terminal_white          db      27,"[37m"                  ;escape string 39: White Text
len_terminal_white      equ     $-terminal_white

ter_bk_black            db      27,"[40m"                  ;escape string 40: Black background
len_ter_bk_black        equ     $-ter_bk_black 


;Reference Escape Sequence Colors
;Color Name 	Foreground 	Background 
;Black 	            30 	        40
;Red 	            31 	        41
;Green 	            32 	        42
;Yellow 	        33 	        43
;Blue 	            34 	        44
;Magenta 	        35 	        45
;Cyan 	            36 	        46
;White 	            37 	        47
;Default 	        39 	        49
;Reset 	            0 	        0

;ESC Code Sequence 	Description
;ESC[38;2;{r};{g};{b}m 	Set foreground color as RGB.
;ESC[48;2;{r};{g};{b}m 	Set background color as RGB.

;Terminal RGB colors escape sequences
ter_bk_rgbblack         db       27,"[48",59,"2",59,"0",59,"0",59,"0m"   ;Set terminal background to rgb black(000), 59=';' 27='ESC'
len_ter_bk_rgbblack     equ      $-ter_bk_rgbblack

;Cursor Position escape sequences
terminal_escape         db      27,"[0H", 27, "[0G"        ;escape string 0H: Line  0G:Column
len_terminal_escape     equ     $-terminal_escape

cursor_home             db      27,"[H"                    ;escape string H: cursor to home
len_cursor_home         equ     $-cursor_home

cursor_pos              db      27,"[6n",0                 ;escape string 6n: request cursor position
len_cursor_pos          equ     $-cursor_pos

cursor_save             db      27,"[7"                    ;escape string 7: save cursor position
len_cursor_save         equ     $-cursor_save

cursor_restore          db      27,"[8"                    ;escape string 7: restore cursor position
len_cursor_restore      equ     $-cursor_restore


;Effect escape sequences
escape_clear            db      27,"[H",27,"[2J", 27,"[3J" ;escape string H: cursor to home, 2J: clear screen, 3J: Deletes scroll back buffer
len_escape_clear        equ     $-escape_clear

terminal_bold           db      27,"[1m" 	               ;escape string 1m: set terminal to bold mode
len_terminal_bold       equ     $-terminal_bold 

terminal_underline      db      27,"[4m" 	               ;escape string 4m: set terminal to underline mode
len_terminal_underline  equ     $-terminal_underline

terminal_reset          db      27,"[0m" 	               ;escape string 0m: set terminal to reset all modes
len_terminal_reset      equ     $-terminal_reset 

terminal_flashing       db      27,"[5m" 	               ;escape string 5m: set terminal to flashing
len_terminal_flashing   equ     $-terminal_flashing

terminal_no_flash       db      27,"[25m" 	               ;escape string 25m: set terminal to non-flashing
len_terminal_no_flash   equ     $-terminal_no_flash

;Terminal Modes
terminal_no_cursor      db      27,"[",63,"25l"            ;escape string ?251: Turn off cursor
len_terminal_no_cursor  equ     $-terminal_no_cursor

terminal_cursor         db      27,"[",63,"25h"            ;escape string ?25h: Turn on cursor
len_terminal_cursor     equ     $-terminal_cursor

terminal_size           db      27,"[8",59,"32",59,"85t"   ;Resize terminal to 85x32 (59=ascii decimal ;)
len_terminal_size       equ     $-terminal_size


;Label for timeval structure dwords, tv_sec and tv_nsec for nanowait function
 
timespec:
    tv_sec  dd 0
    tv_nsec dd 0

;--------------------Constants----------------------------
;System call numbers
SYS_EXIT                equ     0x1                       ;System call number - exit
SYS_READ                equ     0x3                       ;System call number - read
SYS_WRITE               equ     0x4                       ;System call number  -write
SYS_TIME                equ     13                        ;System call number  - time since midnight
SYS_TIMEDAY             equ     78                        ;System call number -time of day
SYS_IOCTL               equ     54                        ;System call number -IOCTL, to change terminal settings
SYS_CALL                equ     0x80                      ;System call number  - 32bit system call
SYS_NANOSLEEP           equ     162                       ;System call number  -nano sleep

;File descriptors
DESC_OK                 equ     0x0                       ;File descriptor return status ok at system exit
DESC_STDOUT             equ     0x1                       ;File descriptor for std out stream
DESC_STDIN              equ     0x2                       ;File descriptor for std in stream

;ASCII Key values
SPACE_KEY               equ     0x20                      ;ASCII value spacebar key

;Constant game values
TIP                     equ     475                       ;Tipping point value in ppm, game end condition
CPERGW                  equ     5                         ;CO2 produced per GW of non-green power
PPMFACTOR               equ     15000                     ;Factor that establishes how much ppm increases per CO2 being produced
GLOERG                  equ     11000                     ;Global energy needs    (Bad event roll when below this value)
GLOERGC                 equ     8000                      ;Critical energy level  (Game Lose roll when below this value)
COLUMNS                 equ     20480                     ;Decimal value equates to hex 5000 or 80 columns when 4f is stored as higher order byte for printing earth loop
ROWS                    equ     30                        ;Value of number of rows to be looped through with printing earth function
ZERO                    equ     48                        ;ASCII character value for 0
EIGHT                   equ     56                        ;ASCII character value for 8
                        
PRICEA                  equ     100000                    ;Prices set for low risk options
PRICEB                  equ     50000                     ;Prices set for medium risk options
PRICEC                  equ     25000                     ;Prices set for low risk options and reduce odds option

;ioctl values
;; ioctl values

TCGETS			        equ     0x5401                    ;Hex value for TCGETS to get current termios settings
TCSETS			        equ     0x5402                    ;Hex value for TCSETS to set new termios settings
ICANON                  equ     000002                    ;ICANON setting to set terminal to RAW mode
ECHO                    equ     000010                    ;ECHO setting to set terminal echo on/off
VMIN                    equ     00000000b                 ;One byte 0 used to set VMIN to 0
VTIME                   equ     00000001b                 ;1 in binary to set VTIME to 1

;-----------------------------------------------------------------------------------------
;-------------------------------------------------------
; BSS Section
;-------------------------------------------------------
section .bss

;--------------------Variables----------------------------

    escape_custom       resb    10                         ;Reserve memory for escape sequence for cursor_set function
    len_escape_custom   equ     $-escape_custom
    color_custom        resb    5                          ;Reserve memory for escape sequence for font_custom function
    len_color_custom    equ     $-color_custom
    line                resb    3                          ;Memory for line number for cursor_set function
    column              resb    3                          ;Memory for column number for cursor_set function

    color1              resb    2                          ;Memory reserved for color 1 in printcolor function
    color2              resb    2                          ;Memory reserved for color 2 in printcolor function

    key                 resb    1                          ;Memory reserved to detect keypress event
    keys                resb    4                          ;Dword to hold multiple bytes for sys_read2 function
    number              resb    4                          ;Memory reserved for numerical input up to 3 digits

    char                resb    1                          ;Memory reserved for char compare in printcolor function
    
    length              resb    4                          ;Memory reserved to track length of string in printcolor function

    buffer              resb    9                          ;Memory reserved for int_to_string function
    len_buffer          equ     $-buffer

    ;---------Game Variables----------
    EVENTCASH           resb    4                          ;Location to store cash amount for tempatation event
    TREEABSORB          resb    4                          ;Location for amount of carbon tree removes
    TREENUM             resb    4                          ;Location of number of trees
    CASHADD             resb    4                          ;Location of cash amount
    GPOWERADD           resb    4                          ;Location of green energy location
    CPOWERADD           resb    4                          ;Location of carbon energy location
    TOTALPOW            resb    4                          ;Total power location
    CARBONADD           resb    4                          ;Carbon in ppm
    POWERLOSE           resb    4                          ;Game ending amount of power
    COINCREASE          resb    4                          ;Amount co2 is increasing per game cycle
    CARBONWIN           resb    4                          ;Address of value of carbon amount at which player wins
    CARBONLOSE          resb    4                          ;Address of value of carbon amount at which player loses
    CASHWIN             resb    4                          ;Address of value of cash amount at which player loses
    ;CASHLOSE - not implemented, meant for event where if player is below amount, bad event occurs
    PCASHADD            resb    4                          ;Address for personal cash of player

    COMILE1             resb    4                          ;Addresses for the milestone amounts for Good Event 1 to trigger
    COMILE1OCC          resb    4                          ;Address for variable to check if event has occured (event flag)
    COMILE2             resb    4                          ;Addresses for the milestone amounts for Good Event 2 to trigger
    COMILE2OCC          resb    4                          ;Address for variable to check if event has occured (event flag)
    COMILE3             resb    4                          ;Addresses for the milestone amounts for Good Event 3 to trigger
    COMILE3OCC          resb    4                          ;Address for variable to check if event has occured (event flag)
    COMILE4             resb    4                          ;Addresses for the milestone amounts for Good Event 4 to trigger
    COMILE4OCC          resb    4                          ;Address for variable to check if event has occured (event flag)

    ;Risk variable for Iniative events
    LOWRISK             resb    4
    MEDRISK             resb    4
    HIGHRISK            resb    4

    ;Flag addresses for hedge betting
    RSK                 resb    4                          ;To store the risk needed for the dice rolls
    BETFLAG             resb    4                          ;Bet flag for if bet  has been placed
    BETWLFLAG           resb    1                          ;Bet flag for if bet has been won/lost

    ;Dice result
    COUNTER             resb    4                          ;Counter for number of dice rolls in animation
    DRES                resb    4                          ;Variable to store result of dice roll subroutine

    ;Random number result
    random              resb    4                          ;Variable to store results of random number subroutine 32bits
    digits              resb    4                          ;Variable to store number of digits created in int_to_string subroutine

    ;Rocket animation
    rocposition         resb    2                          ;Variable to hold rocket postion

    ;fdset		        resd	32                         ;Variable for sys_select syscall, not implemented

;--------Maze Variables

    ;Termios Structure
    old                 resb    100                        ;100 bytes to hold old termios structure values, used in restoretermios subroutine
    new                 resb    100                        ;100 bytes to hold new termios structure values, used in settermios subroutine

    position            resb    10                         ;Variable to store cursor position for retcursorpos fuction
    playerpos           resb    2                          ;Variable to store word saving player position
    oldplayerpos        resb    2                          ;Variable to store word saving previous player position
    enemypos            resb    2                          ;Variable to store word saving enemy position
    oldenemypos         resb    2                          ;Variable to store word saving previous enemy position
    tarpos              resb    2                          ;Variable to store target position
    gamesplayed         resb    1                          ;Variable to store number of games played to pick player and target starting locations

    ;Timer variables
    currenttime         resb    4                          ;Store currents time for timerfunction and ai movement
    oldtime             resb    4                          ;Stores old time for timerfunction and ai movement
    timervalue          resb    1                          ;Stores the number of seconds since game started

    ;Enemy ai variables
    movecounter         resb    1                          ;Counter before enemy makes a move
    ai                  resb    1                          ;Hold enemy move direction decision 
    allow               resb    1                          ;Flag to set whether ai can move in a direction without collision(aidirection subroutine)
    alloweddir          resb    4                          ;Array to hold valid ai directions(aidirection subroutine)
    dircounter          resb    1                          ;Count number of directions in alloweddir array


;-----------------------------------------------------------------------------------------
;-------------------------------------------------------
; Text Section
;-------------------------------------------------------
section .text

;--------------------Globals----------------------------

global _start                                              ;Linker start

;-----------------------Start---------------------------
_start:
    call                font_white                         ;Sets initial font and background color
    call                terminal_resize                    ;Set terminal size
    call                initialise                         ;Set initial game values
    call                startscreen                        ;Load startscreen
    jmp                 preamble                           ;Load opening credits screen

;Debugging subroutine   
testpoint:
    ;call                terminal_cursor_off
    call                clearscreen
    call                terminal_resize
    call                print_border
    call                cursorhome
    call                endline
    ;call                font_cyan
     call                continue
    ;call                terminal_cursor_on
    jmp                 end

;-------------Iniatilise Starting game values------------
    ;Set up initial variable values
initialise:       
    ;Global Variables
    mov dword           [CARBONADD], 415                   ;Carbon in atmosphere
    add dword           [CASHADD], 100000                  ;Amount of cash at start of game
    
    ;Power values
    mov dword           [CPOWERADD], 10000                 ;Value of Carbon Power at game start
    mov dword           [GPOWERADD], 2000                  ;Value of Green Power at game start
    
    ;Tree variables
    mov dword           [TREEABSORB], 5                    ;Amount one million trees reduces amount
    mov dword           [TREENUM], 0                       ;Number of trees
    
    ;Calculate CO2 increase at start (Non green energy in GWS x CPERGW)
    mov dword           eax, [CPOWERADD]
    imul                eax, CPERGW                        ;imul: Signed multiplication of two operands
    mov dword           [COINCREASE], eax 
    
    ;;Victory and lose variables
    mov dword           [CARBONLOSE], 475    
    mov dword           [CARBONWIN], 0                     ;Carbon value that is game over and player wins in tonnes
    ;mov dword           [CASHLOSE], 0                     ;Cash at which game over and player loses (Not implemented)
    mov dword           [CASHWIN], 500000                  ;Cash at which game over and player wins
    mov dword           [PCASHADD], 25000                  ;Address for personal cash
    
    ;;Set risk levels for dice rolls
    mov dword           [LOWRISK], 2
    mov dword           [MEDRISK], 4
    mov dword           [HIGHRISK], 5
   
    ;;Milestone for good events chance
    mov dword           [COMILE1], 40000                   ;Set value for milestone of C02 Increase at which good event 1 can trigger
    mov dword           [COMILE2], 30000                   ;Set value for milestone of C02 Increase at which good event 2 can trigger
    mov dword           [COMILE3], 20000                   ;Set value for milestone of C02 Increase at which good event 3 can trigger
    mov dword           [COMILE4], 10000                   ;Set value for milestone of C02 Increase at which good event 4 can trigger
    mov dword           [COMILE1OCC], 0                    ;Event flag value for Good Event 1 , Set to 0 for has not occured, 1 for occured 
    mov dword           [COMILE2OCC], 0                    ;Event flag value for Good Event 2 , Set to 0 for has not occured, 1 for occured 
    mov dword           [COMILE3OCC], 0                    ;Event flag value for Good Event 3 , Set to 0 for has not occured, 1 for occured 
    mov dword           [COMILE4OCC], 0                    ;Event flag value for Good Event 4 , Set to 0 for has not occured, 1 for occured 
;
    ;;Set risk and betflag and reducted odds flag for hedge betting and dice rolls to 0
    mov dword           [RSK], 0                           ;For storing risk or win/lose value for dice rolls, set to 0 at start      
    mov dword           [BETFLAG], 0                       ;Event flag to indicate if a bet has been placed 1 for bet placed, 0 for no bet, set to 0 at start      
 ;
    ;Set eventcash to 0
    mov dword           [EVENTCASH], 0                     ;Set event cash for tempatation event to 0 at start

    ;Set maze games played so far to 0
    mov byte            [gamesplayed],0

    ret

;-------------------Start Screen------------------------
startscreen:
    call                clearscreen                        ;Clearscreen
    call                print_border                       ;Print border
    mov word            [line], '05'                           
    mov word            [column], '00'
    call                cursorcustom                       ;Set cursor to line 5                
    call                font_green                         ;Set font green
    mov                 ecx, startcredits
    mov                 edx, len_startcredits
    call                sys_write                          ;Print start credits
    call                font_white                         ;Set font white
    call                endline                            ;Add line
    call                endline
    call                endline
    mov dword           [tv_sec], 2                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait
    call                font_flash                         ;Set terminal font to flashing
    mov                 ecx, pressstart
    mov                 edx, len_pressstart
    call                sys_write                          ;Print press spacebar to start
    call                continue
    jmp                 preamble                           ;Continue to preamble    
      
;------------Spacebar to continue loop-------------------
;Press spacebar to continue loop
continue:
    xor                 ecx, ecx                           ;Clear ecx
    mov                 ecx, key
    call                sys_read                           ;Call read value
    cmp byte            [ecx], $20                         ;Compare entered value to ascii value of space character(Spacebr key pressed)
    jne                 continue                           ;If not spacebar, back to start of loop
    ret                                                    ;Else return

;------------------Opening Cutscene----------------------
preamble:
    call                clearscreen
    call                font_no_flash
    call                print_border
    call                cursorhome
    call                endline
    mov                 edi, earth                         ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '34'                     ;Set color code of color 1 2 bytes
    mov word            [color2], '32'                     ;Set color code of color 2 2 bytes
    call                printcolor
preambleb:
    call                endline
    call                font_white
    mov                 ecx, palebluedot
    mov                 edx, len_palebluedot
    call                sys_write                          ;Print string
    mov dword           [tv_sec], 2                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    call                clearscreen
    call                print_border
    call                cursorhome
    call                endline
    call                font_yellow
    mov                 ecx, mail
    mov                 edx, len_mail
    call                sys_write                          ;Print string     
    call                endline
    call                font_white
    mov                 ecx, stytext
    mov                 edx, len_stytext
    call                sys_write                          ;Print string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue                           ;Call continue to wait for spacebar keypress
    call                clearscreen
    call                print_border
    call                cursorhome
    call                endline
    call                font_green
    mov                 ecx, building
    mov                 edx, len_building
    call                sys_write                          ;Print string
    call                font_white
    mov                 ecx, stytext2
    mov                 edx, len_stytext2
    call                sys_write                          ;Print string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    jmp                 hud


;-------------------Main hud screen---------------------
hud:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                endline
    mov                 ecx, box
    mov                 edx, len_box
    call                sys_write  
    call                cursorhome
    call                endline
    call                font_bold
    call                font_underline
    call                font_cyan
    call                endline
    mov                 ecx, agcname
    mov                 edx, len_agcname
    call                sys_write                          ;Print string
    call                font_reset
    call                font_yellow
    mov                 ecx, cshamount
    mov                 edx, len_cshamount
    call                sys_write                          ;Print string
    xor                 eax, eax
    mov dword           eax, [CASHADD]                     ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write                          ;Print string
    mov                 ecx, pcash
    mov                 edx, len_pcash
    call                sys_write                          ;Print string
    mov                 eax, [PCASHADD]                    ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write                          ;Print string
    call                font_magenta
    call                endline
    mov                 ecx, currentgas
    mov                 edx, len_currentgas
    call                sys_write                          ;Print string
    mov                 eax, [CARBONADD]                   ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, pm
    mov                 edx, len_pm
    call                sys_write
    call                font_red
    mov                 ecx, tippingpoint
    mov                 edx, len_tippingpoint
    call                sys_write                          ;Print string
    mov                 eax, TIP                           ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, pm
    mov                 edx, len_pm
    call                sys_write 
    call                endline
    call                font_magenta
    mov                 ecx, annincrease
    mov                 edx, len_annincrease
    call                sys_write                          ;Print string
    mov                 eax, [COINCREASE]                  ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, millton
    mov                 edx, len_millton
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, carbonerg
    mov                 edx, len_carbonerg
    call                sys_write                          ;Print string
    mov                 eax, [CPOWERADD]                   ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, gws
    mov                 edx, len_gws
    call                sys_write
    mov                 ecx, gloenergy
    mov                 edx, len_gloenergy
    call                sys_write                          ;Print string
    call                endline
    mov                 ecx, greenerg
    mov                 edx, len_greenerg
    call                sys_write                          ;Print string
    mov                 eax, [GPOWERADD]                   ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, gws
    mov                 edx, len_gws
    call                sys_write
    ;call                printtab
    ;call                printtab
    ;call                printtab
    mov word            [line], '09'                           
    mov word            [column], '70'
    call                cursorcustom                       ;Set cursor to column 70
    mov                 eax, GLOERG                        ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write                          ;Print string
    mov                 ecx, gws
    mov                 edx, len_gws
    call                sys_write
    call                endline
    call                endline
    call                font_green
    mov                 ecx, treeplan
    mov                 edx, len_treeplan
    call                sys_write                          ;Print string
    mov                 eax, [TREENUM]                     ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write
    mov                 ecx, treenm
    mov                 edx, len_treenm
    call                sys_write
    call                endline
    mov                 ecx, treeab
    mov                 edx, len_treeab
    call                sys_write                          ;Print string
    mov dword           eax, [TREENUM]                     ;Set eax to value of treenum
    mov                 edx, [TREEABSORB]                  ;Set edx to value of treeabsorb
    imul                eax, edx                           ;Mulitply treenumber by treeabsorption per tree
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write  
    mov                 ecx, cton
    mov                 edx, len_cton
    call                sys_write 
    call                endline
    call                endline
    call                font_white
    mov                 ecx, desk
    mov                 edx, len_desk
    call                sys_write
    mov                 ecx, optionsa
    mov                 edx, len_optionsa
    call                sys_write
    call                printtab
    call                printtab
    mov                 esi, keys
    mov dword           edi, 4                             ;Number of bytes to flush
    call                flush
    mov                 ecx, keys
    call                sys_read2                          ;Read 4 byte key press
    cmp dword           [keys], '1'
    je                  investmenu                         ;If input = 1 jmp investmenu
    cmp dword           [keys], '2'
    je                  missionmenu                        ;If input = 2 jmp missionmenu
    cmp dword           [keys], '101'
    je                  room101                            ;If input = 101 jmp secret cheat menu
    jmp                 hud                                ;If neither jmp to hud

;----------------Invest menu screen---------------------
investmenu:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, optionsb
    mov                 edx, len_optionsb
    call                sys_write                          ;Print options string
    call                font_white
    mov                 ecx, optionsba
    mov                 edx, len_optionsba
    call                sys_write 
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  treeopts                           ;If input = 1 jmp treeopts
    cmp byte            [key], '2'
    je                  energyopts                         ;If input = 1 jmp energyopts
    cmp byte            [key], '3'
    je                  cutopts                            ;If input = 1 jmp cutopts
    cmp byte            [key], '4'
    je                  hud                                ;If input = 4 jmp hud
    jmp                 investmenu                         ;If none jmp to investmenu
    jmp end

;----------Tree investments menu screen------------------
treeopts:  
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, treeoptions
    mov                 edx, len_treeoptions
    call                sys_write                          ;Print options string
    call                font_white
    mov                 ecx, treeoptionsb
    mov                 edx, len_treeoptionsb
    call                sys_write
    call                endline 
    call                font_green
    mov                 ecx, treeoptionsb1
    mov                 edx, len_treeoptionsb1
    call                sys_write
    call                font_yellow
    mov                 ecx, treeoptionsb2
    mov                 edx, len_treeoptionsb2
    call                sys_write 
    call                font_red
    mov                 ecx, treeoptionsb3
    mov                 edx, len_treeoptionsb3
    call                sys_write
    call                font_white
    mov                 ecx, treeoptionsb4
    mov                 edx, len_treeoptionsb4
    call                sys_write
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  treeopt1                           ;If input = 1 jmp treeopt1
    cmp byte            [key], '2'
    je                  treeopt2                           ;If input = 1 jmp treeopt2
    cmp byte            [key], '3'
    je                  treeopt3                           ;If input = 1 jmp treeopt3
    cmp byte            [key], '4'
    je                  hud                                ;If input = 4 jmp hud
    jmp                 treeopts                           ;If none jmp to treeopts

;--------------Tree investment option 1-----------------
treeopt1:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEA                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, lrisk
    mov                 edx, len_lrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 1                           ;Set rsk to 1 for low risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of low risk option
    cmp dword           eax, [LOWRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 treeopt1suc                        ;If greater of equal jmp to tree option 1 success
    Jmp                 treeopt1fail                       ;If roll lower jmp to tree option 1 fail

;------------Tree option 1 success screen---------------
treeopt1suc:
    mov                 eax, TREENUM
    add dword           [eax], 1000                          
    mov                 eax, CASHADD
    sub dword           [eax], PRICEA                       ;Subtract the cash  
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    mov                 ecx, forest
    mov                 edx, len_forest
    call                sys_write                          ;Print forest image string
    call                font_white
    mov                 ecx, treeopta
    mov                 edx, len_treeopta
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Tree option 1 fail screen----------------
treeopt1fail:
    mov                 eax, CASHADD
    sub dword           [eax], PRICEA                       ;Subtract the cash          
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                font_yellow                         
    mov                 ecx, forest
    mov                 edx, len_forest
    call                sys_write                          ;Print forest image string in yellow
    call                font_white
    mov                 ecx, treeoptan
    mov                 edx, len_treeoptan
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinlo                           ;if it was jmp to betwinlo
    jmp                 update                             ;if not jmp to update

;--------------Tree investment option 2-----------------
treeopt2:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEB                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, mrisk
    mov                 edx, len_mrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 2                           ;Set rsk to 1 for mid risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of mid risk option
    cmp dword           eax, [MEDRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 treeopt2suc                        ;If greater of equal jmp to tree option 3 success
    Jmp                 treeopt2fail                       ;If roll lower jmp to tree option 3 fail

;------------Tree option 2 success screen---------------
treeopt2suc:
    mov                 eax, TREENUM
    add dword           [eax], 1000                        ;Add 1000 trees to treenum  
    mov                 eax, CASHADD
    sub dword           [eax], PRICEB                      ;Subtract the cash                        
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    mov                 ecx, volcanos
    mov                 edx, len_volcanos
    call                sys_write                          ;Print volcano image string
    call                font_white
    mov                 ecx, treeoptb
    mov                 edx, len_treeoptb
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Tree option 2 fail screen----------------
treeopt2fail:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    sub dword           [eax], PRICEB                      ;Subtract the cash 
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    mov                 ecx, volcano
    mov                 edx, len_volcano
    call                sys_write                          ;Print forest image string in yellow
    call                font_white
    mov                 ecx, treeoptbn
    mov                 edx, len_treeoptbn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinmid                          ;if it was jmp to betwinmid
    jmp                 update                             ;if not jmp to update
     
;--------------Tree investment option 3-----------------
treeopt3:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEC                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, hrisk
    mov                 edx, len_hrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 3                           ;Set rsk to 1 for high risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of high risk option
    cmp dword           eax, [HIGHRISK]                    ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 treeopt3suc                        ;If greater of equal jmp to tree option 3 success
    Jmp                 treeopt3fail                       ;If roll lower jmp to tree option 3 fail

;------------Tree option 3 success screen---------------
treeopt3suc:
    mov                 eax, TREENUM
    add dword           [eax], 1000                        ;Add 1000 trees to treenum  
    mov                 eax, CASHADD
    sub dword           [eax], PRICEC                      ;Subtract the cash   
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    mov                 ecx, forestg
    mov                 edx, len_forestg
    call                sys_write                          ;Print forestg image string
    call                font_white
    mov                 ecx, treeoptc
    mov                 edx, len_treeoptc
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Tree option 3 fail screen----------------
treeopt3fail:
    mov                 eax, CASHADD
    sub dword           [eax], PRICEC                      ;Subtract the cash
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                endline
    call                font_yellow
    mov                 ecx, yacht
    mov                 edx, len_yacht
    call                sys_write                          ;Print yacht image string in yellow
    call                font_blue
    mov                 ecx, yacht2
    mov                 edx, len_yacht2
    call                sys_write                          ;Print water image string in blue
    call                endline
    call                endline
    call                font_white
    mov                 ecx, treeoptcn
    mov                 edx, len_treeoptcn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinhi                           ;if it was jmp to betwinhi
    jmp                 update                             ;if not jmp to update

;----------Energy investments menu screen---------------
energyopts: 
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, energyoptions
    mov                 edx, len_energyoptions
    call                sys_write                          ;Print options string
    call                font_white
    mov                 ecx, energyoptionsb
    mov                 edx, len_energyoptionsb
    call                sys_write
    call                font_green
    mov                 ecx, energyoptions1
    mov                 edx, len_energyoptions1
    call                sys_write
    call                font_yellow
    mov                 ecx, energyoptions2
    mov                 edx, len_energyoptions2
    call                sys_write 
    call                font_red
    mov                 ecx, energyoptions3
    mov                 edx, len_energyoptions3
    call                sys_write
    call                font_magenta
    mov                 ecx, energyoptions4
    mov                 edx, len_energyoptions4
    call                sys_write
    call                font_white
    mov                 ecx, energyoptions5
    mov                 edx, len_energyoptions5
    call                sys_write
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  energyopt1                         ;If input = 1 jmp energyopt1
    cmp byte            [key], '2'
    je                  energyopt2                         ;If input = 1 jmp energyopt2
    cmp byte            [key], '3'
    je                  energyopt3                         ;If input = 1 jmp energyopt3
    cmp byte            [key], '4'
    je                  energyopt4                         ;If input = 1 jmp energyopt4
    cmp byte            [key], '5'
    je                  hud                                ;If input = 4 jmp hud
    jmp                 energyopts                         ;If none jmp to energyopts

;------------Energy investment option 1-----------------
energyopt1:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEA                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, lrisk
    mov                 edx, len_lrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 1                           ;Set rsk to 1 for low risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of low risk option
    cmp dword           eax, [LOWRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 energyopt1suc                        ;If greater of equal jmp to tree option 1 success
    Jmp                 energyopt1fail                       ;If roll lower jmp to tree option 1 fail

;------------Energy option 1 success screen---------------
energyopt1suc:
    mov                 eax, GPOWERADD              
    add dword           [eax], 500                         ;Add 500 to Green power  
    mov                 eax, CASHADD
    sub dword           [eax], PRICEA                      ;Subtract the cash  
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_yellow
    mov                 ecx, sun
    mov                 edx, len_sun
    call                sys_write                          ;Print sun image string in yellow
    call                font_white
    mov                 ecx, energyopta
    mov                 edx, len_energyopta
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Energy option 1 fail screen----------------
energyopt1fail:
    mov                 eax, CASHADD
    sub dword           [eax], PRICEA                       ;Subtract the cash          
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                font_yellow                         
    mov                 ecx, sun
    mov                 edx, len_sun
    call                sys_write                          ;Print sun image string in yellow
    call                font_white
    call                endline
    mov                 ecx, energyoptan
    mov                 edx, len_energyoptan
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinlo                           ;if it was jmp to betwinlo
    jmp                 update                             ;if not jmp to update

;------------Energy investment option 2-----------------
energyopt2:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEB                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, mrisk
    mov                 edx, len_mrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 2                           ;Set rsk to 1 for mid risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of mid risk option
    cmp dword           eax, [MEDRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 energyopt2suc                      ;If greater of equal jmp to tree option 3 success
    Jmp                 energyopt2fail                     ;If roll lower jmp to tree option 3 fail

;------------Energy option 2 success screen---------------
energyopt2suc:
    mov                 eax, GPOWERADD
    add dword           [eax], 1000                        ;Add 1000 to green power  
    mov                 eax, CASHADD
    sub dword           [eax], PRICEB                      ;Subtract the cash                        
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_yellow
    mov                 ecx, wmill
    mov                 edx, len_wmill
    call                sys_write                          ;Print wind mill image string in yellow
    call                font_white
    mov                 ecx, energyoptb
    mov                 edx, len_energyoptb
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Energy option 2 fail screen----------------
energyopt2fail:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    sub dword           [eax], PRICEB                      ;Subtract the cash 
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                font_blue
    mov                 ecx, storm
    mov                 edx, len_storm
    call                sys_write                          ;Print storm image string in blue
    call                font_white
    mov                 ecx, energyoptbn
    mov                 edx, len_energyoptbn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinmid                          ;if it was jmp to betwinmid
    jmp                 update                             ;if not jmp to update

;------------Energy investment option 3-----------------
energyopt3:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEC                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, hrisk
    mov                 edx, len_hrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 3                           ;Set rsk to 1 for high risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of high risk option
    cmp dword           eax, [HIGHRISK]                    ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 energyopt3suc                        ;If greater of equal jmp to tree option 3 success
    Jmp                 energyopt3fail                       ;If roll lower jmp to tree option 3 fail

;------------Energy option 3 success screen---------------
energyopt3suc:
    mov                 eax, TREENUM
    add dword           [eax], 1000                        ;Add 1000 to green power  
    mov                 eax, CASHADD
    sub dword           [eax], PRICEC                      ;Subtract the cash   
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_cyan
    mov                 ecx, nucplant
    mov                 edx, len_nucplant
    call                sys_write                          ;Print nucplant image string in cyan
    call                font_white
    mov                 ecx, energyoptc
    mov                 edx, len_energyoptc
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Energy option 3 fail screen----------------
energyopt3fail:
    mov                 eax, CASHADD
    sub dword           [eax], PRICEC                      ;Subtract the cash
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                endline
    call                font_cyan
    mov                 ecx, nucplant
    mov                 edx, len_nucplant
    call                sys_write                          ;Print nucplant image string in cyan
    call                endline
    call                endline
    call                font_white
    mov                 ecx, energyoptcn
    mov                 edx, len_energyoptcn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinhi                           ;if it was jmp to betwinhi
    jmp                 update                             ;if not jmp to update

;------------Energy investment option 4-----------------
energyopt4:
    mov                 eax, CASHADD                       ;Move address of cash into eax
    cmp dword           [eax], PRICEB                      ;Compare to price of project
    jl                  infunds                            ;Jump to infunds if less than
    mov                 eax, CASHADD
    sub dword           [eax], PRICEB                      ;Subtract the cash
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_magenta
    mov                 ecx, questext
    mov                 edx, len_questext
    call                sys_write                          ;Print mysterious string in magenta
    call                font_yellow
    mov                 ecx, atom
    mov                 edx, len_atom
    call                sys_write                          ;Print atom image string in yellow
    call                endline
    call                font_white
    mov                 ecx, energyoptd
    mov                 edx, len_energyoptd
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    jmp                 update
;-------Cut carbon investments menu screen--------------
cutopts:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print options string
    call                font_white
    call                endline
    mov                 ecx, cutoptionsb
    mov                 edx, len_cutoptionsb
    call                sys_write
    call                endline 
    call                font_green
    mov                 ecx, cutoptionsb1
    mov                 edx, len_cutoptionsb1
    call                sys_write
    call                font_yellow
    mov                 ecx, cutoptionsb2
    mov                 edx, len_cutoptionsb2
    call                sys_write 
    call                font_red
    mov                 ecx, cutoptionsb3
    mov                 edx, len_cutoptionsb3
    call                sys_write
    call                font_white
    mov                 ecx, cutoptionsb4
    mov                 edx, len_cutoptionsb4
    call                sys_write
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt1                            ;If input = 1 jmp cutopt1
    cmp byte            [key], '2'
    je                  cutopt2                            ;If input = 1 jmp cutopt2
    cmp byte            [key], '3'
    je                  cutopt3                            ;If input = 1 jmp cutopt3
    cmp byte            [key], '4'
    je                  hud                                ;If input = 4 jmp hud
    jmp                 cutopts                            ;If none jmp to cutopts

;---------------Cut investment option 1-----------------
cutopt1:
    mov dword           eax, [CPOWERADD]
    add dword           eax, [GPOWERADD]                   ;Add cpower and gpower
    sub dword           eax, 1000                          ;Subtract 1000
    cmp                 eax, GLOERGC                       ;Compare to critical energy levels
    jl                  cutopt1crit                        ;will put below critical energy, ask user to confirm
    cmp                 eax, GLOERG                        ;Compare to less critical energy levels 
    jl                  cutopt1con                         ;Ask user to confirm
cutopt1ret: 
    mov                 eax, CPOWERADD                     ;Move address of cpoweradd into eax
    cmp dword           [eax], 1000                        ;Compare to level of power than would be cut
    jl                  incpower                           ;Jump to incpower if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, lrisk
    mov                 edx, len_lrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 1                           ;Set rsk to 1 for low risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of low risk option
    cmp dword           eax, [LOWRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 cutopt1suc                         ;If greater of equal jmp to tree option 1 success
    Jmp                 cutopt1fail                        ;If roll lower jmp to tree option 1 fail

;Ask user to confirm they want to cut below less critical world power level
cutopt1con:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_white
    mov                 ecx, cutoptq
    mov                 edx, len_cutoptq
    call                sys_write                          ;Print cut options string in red 
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt1ret                         ;If input = 1 jmp cutopt1ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt1con                         ;If none jmp to cutopt1con

;Ask user to confirm they want to cut below critical world power level
cutopt1crit:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_yellow
    mov                 ecx, light
    mov                 edx, len_light
    call                sys_write                          ;Print light string in yellow
    call                font_white
    mov                 ecx, cutoptqc
    mov                 edx, len_cutoptqc
    call                sys_write                          ;Print cut options string in red 
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt1ret                         ;If input = 1 jmp cutopt1ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt1crit                        ;If none jmp to cutopt1crit

;------------Cut option 1 success screen---------------
cutopt1suc:
    mov                 eax, CPOWERADD              
    sub dword           [eax], 1000                        ;Subtract 1000 Carbon power  
    mov                 eax, CASHADD
    add dword           [eax], PRICEC                      ;Add 25000 cash  
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_yellow
    mov                 ecx, arrow
    mov                 edx, len_arrow
    call                sys_write                          ;Print arrow image string in green
    call                font_white
    mov                 ecx, cutopta
    mov                 edx, len_cutopta
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Cut option 1 fail screen----------------
cutopt1fail:
    mov                 eax, CPOWERADD              
    add dword           [eax], 250                        ;Add 250 Carbon power 
    mov                 eax, CASHADD
    add dword           [eax], PRICEC                      ;Add 25000 cash         
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                font_yellow                         
    mov                 ecx, euro
    mov                 edx, len_euro
    call                sys_write                          ;Print euro image string in yellow
    call                font_white
    call                endline
    mov                 ecx, cutoptan
    mov                 edx, len_cutoptan
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinlo                           ;if it was jmp to betwinlo
    jmp                 update                             ;if not jmp to update

;------------Cut investment option 2-----------------
cutopt2:
    mov dword           eax, [CPOWERADD]
    add dword           eax, [GPOWERADD]                   ;Add cpower and gpower
    sub dword           eax, 1500                          ;Subtract 1500
    cmp                 eax, GLOERGC                       ;Compare to critical energy levels
    jl                  cutopt2crit                        ;will put below critical energy, ask user to confirm
    cmp                 eax, GLOERG                        ;Compare to less critical energy levels 
    jl                  cutopt2con                         ;Ask user to confirm

cutopt2ret:
    mov                 eax, CPOWERADD                     ;Move address of cpoweradd into eax
    cmp dword           [eax], 1500                        ;Compare to level of power than would be cut
    jl                  incpower                           ;Jump to incpower if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, mrisk
    mov                 edx, len_mrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 2                           ;Set rsk to 1 for mid risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of mid risk option
    cmp dword           eax, [MEDRISK]                     ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 cutopt2suc                      ;If greater of equal jmp to tree option 3 success
    Jmp                 cutopt2fail                     ;If roll lower jmp to tree option 3 fail

;Ask user to confirm they want to cut below less critical world power level
cutopt2con:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_white
    mov                 ecx, cutoptq
    mov                 edx, len_cutoptq
    call                sys_write                          ;Print cut options string in red 
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt2ret                         ;If input = 1 jmp cutopt2ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt2con                         ;If none jmp to cutopt2con

;Ask user to confirm they want to cut below critical world power level
cutopt2crit:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_yellow
    mov                 ecx, light
    mov                 edx, len_light
    call                sys_write                          ;Print light string in yellow
    call                font_white
    mov                 ecx, cutoptqc
    mov                 edx, len_cutoptqc
    call                sys_write                          ;Print cut options string in red 
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt2ret                         ;If input = 1 jmp cutopt2ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt2crit                        ;If none jmp to cutopt2crit

;------------Cut option 2 success screen---------------
cutopt2suc:
    mov                 eax, CPOWERADD              
    sub dword           [eax], 1500                        ;Subtract 1500 Carbon power  
    mov                 eax, CASHADD
    add dword           [eax], PRICEB                      ;Add 50000 cash                        
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_cyan
    mov                 ecx, oilrigb
    mov                 edx, len_oilrigb
    call                sys_write                          ;Print oilrig image string in cyan
    call                font_white
    mov                 ecx, cutoptb
    mov                 edx, len_cutoptb
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Cut option 2 fail screen----------------
cutopt2fail:
    mov                 eax, CPOWERADD              
    add dword           [eax], 250                         ;Add 250 Carbon power 
    mov                 eax, CASHADD
    add dword           [eax], PRICEC                      ;Add 25000 cash 
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                font_blue
    mov                 ecx, oilriga
    mov                 edx, len_oilriga
    call                sys_write                          ;Print oilrig image string in blue
    call                font_white
    mov                 ecx, cutoptbn
    mov                 edx, len_cutoptbn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinmid                          ;if it was jmp to betwinmid
    jmp                 update                             ;if not jmp to update

;------------Cut investment option 3-----------------
cutopt3:
    mov dword           eax, [CPOWERADD]
    add dword           eax, [GPOWERADD]                   ;Add cpower and gpower
    sub dword           eax, 2000                          ;Subtract 2000
    cmp                 eax, GLOERGC                       ;Compare to critical energy levels
    jl                  cutopt3crit                        ;will put below critical energy, ask user to confirm
    cmp                 eax, GLOERG                        ;Compare to less critical energy levels 
    jl                  cutopt3con                         ;Ask user to confirm

cutopt3ret:
    mov                 eax, CPOWERADD                     ;Move address of cpoweradd into eax
    cmp dword           [eax], 2000                        ;Compare to level of power than would be cut
    jl                  incpower                           ;Jump to incpower if less than
    call                clearscreen
    call                print_border
    call                font_yellow
    call                cursorhome
    mov                 ecx, risk
    mov                 edx, len_risk
    call                sys_write                          ;Print risk screen strings
    call                font_green
    mov                 ecx, sham
    mov                 edx, len_sham
    call                sys_write                          ;Print shamrock string
    call                font_white
    mov                 ecx, hrisk
    mov                 edx, len_hrisk
    call                sys_write                          ;Print risk screen strings
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           [RSK], 3                           ;Set rsk to 1 for high risk value
    call                dicechoice                         ;Call dice choice to create dice choice menu
    mov dword           eax, [DRES]                        ;Set eax to value of high risk option
    cmp dword           eax, [HIGHRISK]                    ;Compare to value of dice roll
    call                resodds                            ;Call subroutine to restore normal odds values
    jge                 cutopt3suc                         ;If greater of equal jmp to tree option 3 success
    Jmp                 cutopt3fail                        ;If roll lower jmp to tree option 3 fail

;Ask user to confirm they want to cut below less critical world power level
cutopt3con:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_white
    mov                 ecx, cutoptq
    mov                 edx, len_cutoptq
    call                sys_write                          ;Print cut options string in red 
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt3ret                         ;If input = 1 jmp cutopt3ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt3con                         ;If none jmp to cutopt3con

;Ask user to confirm they want to cut below critical world power level
cutopt3crit:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, cutoptions
    mov                 edx, len_cutoptions
    call                sys_write                          ;Print cut options string in red
    call                font_yellow
    mov                 ecx, light
    mov                 edx, len_light
    call                sys_write                          ;Print light string in yellow
    call                font_white
    mov                 ecx, cutoptqc
    mov                 edx, len_cutoptqc
    call                sys_write                          ;Print cut options string in red 
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  cutopt3ret                         ;If input = 1 jmp cutopt3ret
    cmp byte            [key], '2'
    je                  cutopts                            ;If input = 2 jmp cutopts
    jmp                 cutopt3crit                        ;If none jmp to cutopt3crit

;------------Cut option 3 success screen---------------
cutopt3suc:
    mov                 eax, CPOWERADD              
    sub dword           [eax], 2000                        ;Subtract 2000 Carbon power  
    mov                 eax, CASHADD
    add dword           [eax], PRICEA                      ;Add 100000 cash
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, suctext
    mov                 edx, len_suctext
    call                sys_write                          ;Print success string
    call                font_yellow
    mov                 ecx, judge
    mov                 edx, len_judge
    call                sys_write                          ;Print judge image string in yellow
    call                font_white
    mov                 ecx, cutoptc
    mov                 edx, len_cutoptc
    call                sys_write                          ;Print success string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betlose                            ;if it was jmp to betlose
    jmp                 update                             ;if not jmp to update

;--------------Cut option 3 fail screen----------------
cutopt3fail:
    mov                 eax, CPOWERADD              
    add dword           [eax], 2000                        ;Add 2000 Carbon power 
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, failtext
    mov                 edx, len_failtext
    call                sys_write                          ;Print fail string in red
    call                endline
    call                font_cyan
    mov                 ecx, nucplant
    mov                 edx, len_nucplant
    call                sys_write                          ;Print nucplant image string in cyan
    call                endline
    call                endline
    call                font_white
    mov                 ecx, energyoptcn
    mov                 edx, len_energyoptcn
    call                sys_write                          ;Print failure text string
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Call continue to wait for spacebar keypress
    mov dword           eax, [BETFLAG]
    cmp dword           eax, 1                             ;Check if bet flag was set
    je                  betwinhi                           ;if it was jmp to betwinhi
    jmp                 update                             ;if not jmp to update

;---------------Mission menu screen---------------------
missionmenu:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, misoptions
    mov                 edx, len_misoptions
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, misoptions1
    mov                 edx, len_misoptions1
    call                sys_write 
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  mazegame                           ;If input = 1 jmp mazegame
    cmp byte            [key], '2'
    je                  hud                                ;If input = 2 jmp hud
    jmp                 missionmenu                        ;If none jmp to missionmenu

;--------------Update game subroutine-------------------
;Subroutine to recalulate game values after each round and check for special or gameending conditions
update:
    ;Check carbon generation
    mov dword           eax, [CPOWERADD]                   ;Set eax to value of cpoweradd
    imul                eax, CPERGW                        ;imul: Signed multiplication of two operands
    mov dword           [COINCREASE], eax                  ;Mulitply to get amount of co2 generated by carbon based power
      
    ;Calculate absorption
    mov dword           eax, [TREENUM]                     ;Set eax to value of treenum
    mov                 edx, [TREEABSORB]                  ;Set edx to value of treeabsorb
    imul                eax, edx                           ;Mulitply treenumber by treeabsorption per tree

    ;Subtract absorption from carbon generation to get true increase
    sub dword          [COINCREASE], eax

    ;Calculate ppm increase
    mov dword           eax, [COINCREASE]                  ;Place new value of COINCREASE in eax
    mov                 ecx, PPMFACTOR                     ;Put ppmfactor in ecx
    xor                 edx, edx                           ;Clear edx
    div                 ecx                                ;Divide eax/ecx
    add dword           [CARBONADD], eax                   ;Add result without remainder to current CARBONADD value

sloop:    
;Check for victory or loss conditions

    ;Check for carbon low
    mov dword           eax, [COINCREASE]
    mov dword           ebx, [CARBONWIN]
    cmp                 eax, ebx                           ;Carbon low enough for win
    jle                 gamewin

    ;Check for carbon high
    mov dword           eax, [CARBONADD]
    mov dword           ebx, [CARBONLOSE]
    cmp                 eax, ebx                           ;Carbon too high
    jge                 gamelose

    ;Check for personal cash victory
    mov dword           eax, [PCASHADD]
    mov dword           ebx, [CASHWIN]
    cmp                 eax, ebx
    jge                 gamewinc
    
    ;Check for energy too low
    mov dword           eax, [CPOWERADD]
    add dword           eax, [GPOWERADD]
    call                updatepower                        ;Check for low global power conditions

updatereturn2:                                             ;Return from bad event checks
    call                geventcheck                        ;Check for good event conditions
    jmp                 hud                                ;Jump back to hud

updatereturn:
    ret

updatepower:
    ;Compare for critical global energy level
    cmp                 eax, GLOERGC
    jl                  peventrollb
nextpowerck:     
    ;Compare for less critical global energy level
    mov dword           eax, [CPOWERADD]
    add dword           eax, [GPOWERADD]
    cmp                 eax, GLOERG
    jl                  peventrolla
    ret
                               
;-------------Game Win (by carbon) screen---------------
gamewin:
    call                clearscreen
    call                font_green
    call                print_border
    call                cursorhome
    mov                 ecx, gameovwin
    mov                 edx, len_gameovwin
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, gameov1
    mov                 edx, len_gameov1
    call                sys_write
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    call                clearscreen
    call                print_border
    call                cursorhome
    mov                 edi, earth                         ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '34'                     ;Set color code of color 1 2 bytes
    mov word            [color2], '32'                     ;Set color code of color 2 2 bytes
    call                printcolor                
    call                endline
    call                font_white
    mov                 ecx, gameov1a
    mov                 edx, len_gameov1a
    call                sys_write
    call                endline
    mov                 ecx, pressend
    mov                 edx, len_pressend
    call                sys_write
    call                continue
    jmp                 playagain

;-------------Game Lose (by carbon) screen--------------
gamelose:
    call                clearscreen
    call                font_red
    call                print_border
    call                cursorhome
    mov                 ecx, gameov
    mov                 edx, len_gameov
    call                sys_write
    call                font_white
    mov                 ecx, gameov2a
    mov                 edx, len_gameov2a
    call                sys_write
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    call                font_white
    call                clearscreen
    call                font_red
    call                print_border
    call                cursorhome
    mov                 edi, earth                         ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '34'                     ;Set color code of color 1 2 bytes
    mov word            [color2], '32'                     ;Set color code of color 2 2 bytes
    call                printcolor    
    mov dword           [tv_sec], 2                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait 
    call                clearscreen
    call                font_red
    call                print_border
    call                cursorhome
    xor                 edi,edi
    mov                 edi, earth                         ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '33'                     ;Set color code of color 1 2 bytes
    mov word            [color2], '31'                     ;Set color code of color 2 2 bytes
    call                printcolor               
    call                endline
    call                font_white
    mov                 ecx, gameov2c
    mov                 edx, len_gameov2c
    call                sys_write
    call                endline
    mov                 ecx, pressend
    mov                 edx, len_pressend
    call                sys_write
    call                continue
    jmp                 end

;-------------Game Win (by pcash) screen----------------
;Method for personal cash win, kinda secret ending
gamewinc:
    call                clearscreen
    call                font_green
    call                print_border
    call                cursorhome
    mov                 ecx, rockwin
    mov                 edx, len_rockwin
    call                sys_write
    call                font_yellow
    mov                 ecx, elon
    mov                 edx, len_elon
    call                sys_write 
    call                font_white
    mov                 ecx, rock
    mov                 edx, len_rock
    call                sys_write
    mov                 ecx, rock1
    mov                 edx, len_rock1
    call                sys_write
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  rocketgo                           ;If input = 1 Rocket go animation game ending
    cmp byte            [key], '2'
    je                  hud                                ;If input = 2 back to main
    jmp                 gamewinc

rocketgo:
    call                clearscreen
    call                font_green
    call                print_border
    call                cursorhome
    mov                 ecx, rockwin
    mov                 edx, len_rockwin
    call                sys_write
    mov                 edi, earthd                        ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '34'                     ;Set color code of color 1 2 bytes
    mov word            [color2], '32'                     ;Set color code of color 2 2 bytes
    call                printcolor    
    call                rocketani

;Method to print ending text
rocketend:  
    mov word            [line], '25'                           
    mov word            [column], '00'
    call                cursorcustom                       ;Set cursor to line 25, under art
    call                font_white
    mov                 ecx, rock2
    mov                 edx, len_rock2
    call                sys_write
    call                endline
    mov                 ecx, pressend
    mov                 edx, len_pressend
    call                sys_write
    call                continue
    jmp                 playagain


;--ROCKET ANIMATION SUBROUTINE FOR SECRET ENDING--
;Method to animate rocket leaving earth
rocketani:
    mov dword           edi, 30                            ;Edi hold number of time animation repeats
    mov word            [rocposition], 0x390D              ;rocposition holds initial rocket position

rloop:  ;Rocket loop to move the components of the rocket image
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    call                font_yellow
    mov                 ecx, exhaust1
    mov                 edx, len_exhaust1
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, exhaust2
    mov                 edx, len_exhaust2
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, exhaust3
    mov                 edx, len_exhaust3
    call                sys_write
    add word            [rocposition], $700                ;Add 1 to column number
    sub word            [rocposition], 2                   ;Subtract 2 from line number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    call                font_magenta
    mov                 ecx, rocket1
    mov                 edx, len_rocket1
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, rocket2
    mov                 edx, len_rocket2
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, rocket3
    mov                 edx, len_rocket3
    call                sys_write
    sub dword           edi, 1
    cmp dword           edi, 0
    je                  rocketend
    mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 500000000               ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait
    call                clearrocket

;Method to delete rocket for reprinting
clearrocket:
    sub word            [rocposition], 2
    sub word            [rocposition], $700
    call                roc_pos_tostring
    call                font_yellow
    mov                 ecx, bexhaust1
    mov                 edx, len_bexhaust1
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, bexhaust2
    mov                 edx, len_bexhaust2
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, bexhaust3
    mov                 edx, len_bexhaust3
    call                sys_write
    add word            [rocposition], $700                ;Add 1 to column number
    sub word            [rocposition], 2                   ;Subtract 2 from line number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    call                font_magenta
    mov                 ecx, brocket1
    mov                 edx, len_brocket1
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, brocket2
    mov                 edx, len_brocket2
    call                sys_write
    add word            [rocposition], 1                   ;Add 1 to row number
    call                roc_pos_tostring                   ;Call function to convert numerical position to string for set cursor position
    mov                 ecx, brocket3
    mov                 edx, len_brocket3
    call                sys_write
    sub word            [rocposition], $600                ;Add 1 to column number
    sub word            [rocposition], 2  
    jmp                 rloop

roc_pos_tostring:
    xor                 edx, edx
    mov word            dx, [rocposition]                  ;Store cursor position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [rocposition]                  ;Store cursor position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    call                cursorcustom                       ;Call cursor custom to set cursor to player position
    ret
;*-------------------------------------------------------
;PLAY AGAIN SUBROUTINE, UPS DIFFICULTY ON REPLAY
;*-------------------------------------------------------

;-------------------Play Again Screen--------------------
;Method to ask player if they wish to play on a harder difficulty
playagain:
    call                clearscreen
    call                font_green
    call                print_border
    call                cursorhome
    call                endline
    call                endline
    mov                 ecx, vic
    mov                 edx, len_vic
    call                sys_write
    call                font_magenta
    call                endline
    call                endline
    mov                 ecx, die
    mov                 edx, len_die
    call                sys_write
    call                font_magenta
    call                font_yellow
    call                endline
    call                endline
    mov                 ecx, again
    mov                 edx, len_again
    call                sys_write
    call                font_magenta
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  difficultyup                       ;If input = 1 Raise difficulty and start level 2
    cmp byte            [key], '2'
    je                  end                                ;If input = 2 end game
    jmp                 playagain
  
;Method to increase game difficulty
difficultyup:
    ;Set up initial variables   
    ;Global Variables

    ;Carbon at start
    mov dword           [CARBONADD], 415                   ;Carbon in atmosphere
    
    ;Power values
    mov dword           [CPOWERADD], 12000                 ;Value of Carbon Power at game start
    mov dword           [GPOWERADD], 0                     ;Value of Green Power at game start
    
    ;Tree variables
    mov dword           [TREEABSORB], 5                    ;Amount one million trees reduces amount
    mov dword           [TREENUM], 0                       ;Number of trees
    
    ;Cash at start
    mov dword           [CASHADD], 25000                   ;Amount of cash at start of game
    mov dword           [PCASHADD], 25000                  ;Address for personal cash

    ;Calculate CO2 increase at start (Non green energy in GWS x CPERGW)
    mov dword           eax, [CPOWERADD]
    imul                eax, CPERGW                        ;imul: Signed multiplication of two operands
    mov dword           [COINCREASE], eax 
    
    ;;Victory and lose variables
    mov dword           [CARBONLOSE], 475    
    mov dword           [CARBONWIN], 0                     ;Carbon value that is game over and player wins in tonnes
    ;mov dword           [CASHLOSE], 0                     ;Cash at which game over and player loses (Not implemented)
    mov dword           [CASHWIN], 1000000                 ;Cash at which game over and player wins
    jmp                 hud

;*-----------------------------------------------------------
;Code for random good events
;*----------------------------------------------------------- 
geventcheck:
    call                geventchecka
    call                geventcheckb
    call                geventcheckc
    call                geventcheckd
    ret  

;Good event A
geventchecka:           ;Good event check method, if carbon under milestone
    mov dword           eax, [COINCREASE]
    mov dword           ebx, [COMILE1]
    cmp                 eax, ebx
    jle                 geventcheck2a                      ;is under milestone, jmp to subroutine to check if event has already occured
    ret                                                    ;Return if milestone not reached

geventcheck2a:          ;Check if event already occured
    mov dword           eax, [COMILE1OCC]                  ;Move value of flag into eax
    cmp dword           eax, 0
    je                  geventrolla                        ;If flag is 0, event has not already occured so gen random number to give chance of event occuring
    ret                                                    ;Else event has occured so return
geventrolla:
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 50
    jle                 geventafail                        ;If number under or equal to 50, event will not occur
    jmp                 geventasuc                         ;If above 50, event will occur
geventafail:
    ret
geventasuc:            ;Good event roll, if roll fails this subroutine, is good outcome
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, gevent
    mov                 edx, len_gevent
    call                sys_write
    call                font_yellow
    mov                 ecx, whiskey
    mov                 edx, len_whiskey
    call                sys_write
    call                font_white
    mov                 ecx, geventa
    mov                 edx, len_geventa
    call                sys_write
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    mov dword           [EVENTCASH], 100000                ;Add cash to eventcash for temptation event
    call                temptation
    mov dword           [COMILE1OCC], 1                    ;Set occured flag to 1
    ret

;Good event B
geventcheckb:           ;Good event check method, if carbon under milestone
    mov dword           eax, [COINCREASE]
    mov dword           ebx, [COMILE2]
    cmp                 eax, ebx
    jle                 geventcheck2b                      ;is under milestone, jmp to subroutine to check if event has already occured
    ret                                                    ;Return if milestone not reached

geventcheck2b:          ;Check if event already occured
    mov dword           eax, [COMILE2OCC]                  ;Move value of flag into eax
    cmp dword           eax, 0
    je                  geventrollb                        ;If flag is 0, event has not already occured so gen random number to give chance of event occuring
    ret                                                    ;Else event has occured so return
geventrollb:
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 50
    jle                 geventbfail                        ;If number under or equal to 50, event will not occur
    jmp                 geventbsuc                         ;If above 50, event will occur
geventbfail:
    ret
geventbsuc:            ;Good event roll, if roll fails this subroutine, is good outcome
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, gevent
    mov                 edx, len_gevent
    call                sys_write
    call                font_cyan
    mov                 ecx, polar
    mov                 edx, len_polar
    call                sys_write
    call                font_white
    mov                 ecx, geventb
    mov                 edx, len_geventb
    call                sys_write
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    mov dword           [EVENTCASH], 100000                ;Add cash to eventcash for temptation event
    call                temptation
    mov dword           [COMILE2OCC], 1                    ;Set occured flag to 1
    ret

;Good event C
geventcheckc:           ;Good event check method, if carbon under milestone
    mov dword           eax, [COINCREASE]
    mov dword           ebx, [COMILE3]
    cmp                 eax, ebx
    jle                 geventcheck2c                      ;is under milestone, jmp to subroutine to check if event has already occured
    ret                                                    ;Return if milestone not reached

geventcheck2c:          ;Check if event already occured
    mov dword           eax, [COMILE3OCC]                  ;Move value of flag into eax
    cmp dword           eax, 0
    je                  geventrollc                        ;If flag is 0, event has not already occured so gen random number to give chance of event occuring
    ret                                                    ;Else event has occured so return
geventrollc:
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 50
    jle                 geventcfail                        ;If number under or equal to 50, event will not occur
    jmp                 geventcsuc                         ;If above 50, event will occur
geventcfail:
    ret
geventcsuc:            ;Good event roll, if roll fails this subroutine, is good outcome
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, gevent
    mov                 edx, len_gevent
    call                sys_write
    call                font_red
    mov                 ecx, flame
    mov                 edx, len_flame
    call                sys_write
    call                font_white
    call                endline
    call                endline
    mov                 ecx, geventc
    mov                 edx, len_geventc
    call                sys_write
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    mov dword           [EVENTCASH], 100000                ;Add cash to eventcash for temptation event
    call                temptation
    mov dword           [COMILE3OCC], 1                    ;Set occured flag to 1
    ret

;Good event D
geventcheckd:           ;Good event check method, if carbon under milestone
    mov dword           eax, [COINCREASE]
    mov dword           ebx, [COMILE4]
    cmp                 eax, ebx
    jle                 geventcheck2d                      ;is under milestone, jmp to subroutine to check if event has already occured
    ret                                                    ;Return if milestone not reached

geventcheck2d:          ;Check if event already occured
    mov dword           eax, [COMILE4OCC]                  ;Move value of flag into eax
    cmp dword           eax, 0
    je                  geventrolld                        ;If flag is 0, event has not already occured so gen random number to give chance of event occuring
    ret                                                    ;Else event has occured so return
geventrolld:
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 50
    jle                 geventdfail                        ;If number under or equal to 50, event will not occur
    jmp                 geventdsuc                         ;If above 50, event will occur
geventdfail:
    ret
geventdsuc:            ;Good event roll, if roll fails this subroutine, is good outcome
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_green
    mov                 ecx, gevent
    mov                 edx, len_gevent
    call                sys_write
    call                font_yellow
    mov                 ecx, ssun
    mov                 edx, len_ssun
    call                sys_write
    call                endline
    call                endline
    call                sys_write
    call                font_white
    mov                 ecx, geventd
    mov                 edx, len_geventd
    call                sys_write
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    mov dword           [EVENTCASH], 100000                ;Add cash to eventcash for temptation event
    call                temptation
    mov dword           [COMILE4OCC], 1                    ;Set occured flag to 1
    ret

;-----------------------------------------------------------
;Code for temptation event, steal money from donation
;----------------------------------------------------------- 
temptation:
    call                clearscreen
    call                font_magenta
    call                print_border
    call                cursorhome
    call                font_yellow
    call                endline
    mov                 ecx, ethics
    mov                 edx, len_ethics     
    call                sys_write                          ;Print temptation string in yellow
    call                font_green
     mov                ecx, euro
    mov                 edx, len_euro
    call                sys_write                          ;Print euro string in green
    call                endline
    call                endline
    call                font_white
    mov                 ecx, ethics1
    mov                 edx, len_ethics1    
    call                sys_write                          ;Print temptation string in white
    call                endline
    mov word            [line], '30'                           
    mov word            [column], '12'
    call                cursorcustom    
    call                sys_read
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  badboy                             ;If input = 1 jmp badboy
    cmp byte            [key], '2'
    je                  goodboy                            ;If input = 2 jmp goodboy
    jmp                 temptation                         ;If none jmp to temptation
       
badboy:
    mov dword           eax, [PCASHADD]                    ;Move pcashadd value into eax
    mov dword           ebx, [EVENTCASH]                   ;Move eventcash value into ebx (cash should be in here before call)
    add                 eax, ebx                           ;Add cash from ebx to eax
    mov dword           [PCASHADD], eax                    ;Put new value back in PCASHADD
    mov dword           [EVENTCASH],0                      ;Set eventcash to 0
    ret                                                    ;Return
goodboy:
    mov dword           eax, [CASHADD]                     ;Move cashadd value into eax
    mov dword           ebx, [EVENTCASH]                   ;Move eventcash value into ebx (cash should be in here before call)
    add                 eax, ebx                           ;Add cash from ebx to eax
    mov dword           [CASHADD], eax                     ;Put new value back in CASHADD
    mov dword           [EVENTCASH],0                      ;Set eventcash to 0
    ret                                                    ;Return

;*-----------------------------------------------------------
;Code for random bad events
;*-----------------------------------------------------------    
peventrolla:
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 50
    jle                 peventafail                        ;Roll 50 or less bad event a will trigger
peventasuc:
    jmp                 updatereturn2                      ;Roll over and bad event will not trigger
peventafail:            ;Bad event roll
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, peventf
    mov                 edx, len_peventf
    call                sys_write                          ;Print bad event text
    call                font_white
    mov                 ecx, riot
    mov                 edx, len_riot
    call                sys_write                          ;Print bad event a image text
    mov                 ecx, peventfa
    mov                 edx, len_peventfa
    call                sys_write                          ;Print bad event text
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write 
    call                continue                           ;Spacebar to continue                 
    mov                 eax, CPOWERADD                     ;Set eax to point to CPOWERADD
    add dword           [eax], 500                         ;Add 500 to CPOWERADD
    jmp                 updatereturn2                      ;Jump back to updatereturn2

peventrollb:            ;Roll for game ending event
    call                random_number
    mov dword           eax, [random]                      ;Place value of random number in eax
    cmp dword           eax, 41
    jl                  peventafail                        ;Roll 40 or less bad event b will trigger, game over!
peventeventbsuc:
    jmp                 nextpowerck                        ;Jump after first power check to check less critical power condition
peventbfail:            ;Bad event roll, game over!
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_red
    mov                 ecx, gameovalt
    mov                 edx, len_gameovalt
    call                sys_write
    call                font_yellow
    mov                 ecx, nuc
    mov                 edx, len_nuc
    call                sys_write
    call                font_white
    mov                 ecx, gameov3
    mov                 edx, len_gameov3
    call                sys_write
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    call                clearscreen
    call                print_border
    call                cursorhome
    call                endline
    mov                 edi, earth                         ;Pass reference to string image into edi for printcolor function
    mov word            [color1], '34'                     ;Set color code of color 1 (green) 2 bytes
    mov word            [color2], '36'                     ;Set color code of color 2 (cyan) 2 bytes
    call                printcolor
    call                endline
    call                font_yellow
    mov                 ecx, gameov3b
    mov                 edx, len_gameov3b
    call                sys_write
    call                font_white
    call                endline
    mov                 ecx, pressend
    mov                 edx, len_pressend
    call                sys_write  
    call                continue
    jmp                 end                                ;Jmp to end

;-----------------Dice menu screen----------------------
dicechoice:
    call                clearscreen
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, dtext
    mov                 edx, len_dtext
    call                sys_write                          ;Print dice text in yellow
    call                font_magenta
    mov                 ecx, die
    mov                 edx, len_die
    call                sys_write                          ;Print dice image in magenta
    call                endline
    call                endline
    call                font_white
    mov                 ecx, dtext2
    mov                 edx, len_dtext2
    call                sys_write                          ;Print next dice text in white
    mov dword           [COUNTER], 1 ;NB FIX                             ;Move 3 into eax to act as counter for dice roll animation
    call                printtab
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  dicego                             ;If input = 1 jmp dicego
    cmp byte            [key], '2'
    je                  betmenu                            ;If input = 2 jmp betmenu
    cmp byte            [key], '3'
    je                  redodds                            ;If input = 3 jmp redodds
    jmp                 dicechoice                         ;If none jmp back to dicechoice

;-----------------Dice Roll screen----------------------
dicego:
    call                clearscreen 
    call                font_green
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, dtext
    mov                 edx, len_dtext
    call                sys_write                          ;Print dice text in yellow
    call                font_magenta
    mov                 ecx, rolld
    mov                 edx, len_rolld
    call                sys_write                          ;Print dice string in magenta
    call                endline
    call                endline
    call                font_white
    mov                 ecx, dtext3
    mov                 edx, len_dtext3
    call                sys_write                          ;Print rolling dice string in white
    mov dword           [tv_sec], 1                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait 1 secs
    call                clearscreen 
    call                font_green
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, dtext
    mov                 edx, len_dtext
    call                sys_write                          ;Print dice text in yellow
    call                font_magenta
    mov                 ecx, rolld1
    mov                 edx, len_rolld1
    call                sys_write                          ;Print dice string in magenta
    call                endline
    call                endline
    call                font_white
    mov                 ecx, dtext3
    mov                 edx, len_dtext3
    call                sys_write                          ;Print rolling dice string in white
    mov dword           [tv_sec], 1                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait 1 secs
    call                clearscreen 
    call                font_green
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, dtext
    mov                 edx, len_dtext
    call                sys_write                          ;Print dice text in yellow
    call                font_magenta
    mov                 ecx, rolld2
    mov                 edx, len_rolld2
    call                sys_write                          ;Print dice string in magenta
    call                endline
    call                endline
    call                font_white
    mov                 ecx, dtext3
    mov                 edx, len_dtext3
    call                sys_write                          ;Print rolling dice string in white
    dec dword           [COUNTER]                          ;Decrement counter
    cmp dword           [COUNTER], 0                       ;Compare counter to 0
    je                  diceend                            ;If equal jmp to dice end
    mov dword           [tv_sec], 1                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 0                       ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait 1 secs
    jmp                 dicego                             ;Continue loop

;----------------Dice result screen---------------------
diceend:
    call                clearscreen
    call                font_green
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, dtext
    mov                 edx, len_dtext
    call                sys_write                          ;Print dice text in yellow
    call                font_magenta
    call                rolldice
    call                sys_write                          ;Print dice image result, string and length saved in ecx, and edx by roll dice subroutine
    call                endline
    call                font_white
    mov                 ecx, dtext4
    mov                 edx, len_dtext4
    call                sys_write                          ;Print dice text in white
    xor                 eax, eax                           ;Clear eax
    mov dword           eax, [DRES]                        ;Pass dice result number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Length of buffer
    call                sys_write                          ;Print dice result as string
    call                endline
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print press spacebar string
    call                continue                           ;Wait for spacebar key to be pressed
    ret

;---------------Reduce odds subroutine------------------
redodds:
    mov dword           eax, PCASHADD                      ;Move amount of personal cash into eax
    cmp dword           [eax], PRICEC                      ;Compare to price of reducing odds(25000)
    jl                  infunds                            ;Jump to infunds if less than
    sub dword           [eax], PRICEC                      ;If has the funds, subtract the price from personal cash
    mov dword           [LOWRISK], 1                       ;Reduce the odds by 1
    mov dword           [MEDRISK], 3                       ;Reduce the odds by 1
    mov dword           [HIGHRISK], 4                      ;Reduce the odds by 1
    jmp                 dicego                             ;Jmp to dicego

;---------------Restore odds subroutine-----------------
resodds:
    mov dword           [LOWRISK], 2
    mov dword           [MEDRISK], 4
    mov dword           [HIGHRISK], 5                       ;Restore the odds to original
    ret

;*-------------------------------------------------------
;BET MENU AND SUBROUTINES
;*-------------------------------------------------------   

;---------------Hedge Bet menu screen-------------------
betmenu:
    call                clearscreen
    call                font_magenta
    call                print_border
    call                cursorhome
    call                font_yellow
    mov                 ecx, gtext
    mov                 edx, len_gtext
    call                sys_write                          ;Print hedge bet text in yellow
    call                font_green
    mov                 ecx, euro
    mov                 edx, len_euro
    call                sys_write                          ;Print euro image string in green
    call                endline
    call                endline
    call                font_white
    mov                 ecx, gtext1
    mov                 edx, len_gtext1
    call                sys_write                          ;Print hedge bet text in white
    mov dword           eax, [RSK]                         ;Move level of risk from option picked into eax
    cmp dword           eax, 1
    je                  lowodd                             ;If 1 jmp lowodd
    cmp dword           eax, 2
    je                  medodd                             ;If 2 jmp medodd
    jmp                 highodd                            ;If neither must be 3, jmp highodd
;Return point after setting odds string    
oddsret:
    call                sys_write                          ;Print odds string
    mov                 ecx, gtext2
    mov                 edx, len_gtext2
    call                sys_write                          ;Print hedge options text in white
    call                printtab
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read key press
    cmp byte            [key], '1'
    je                  bet                                ;If input = 1 jmp bet
    cmp byte            [key], '2'
    je                  dicechoice                         ;If input = 2 jmp dicechoice
    jmp                 betmenu                            ;If none jmp back to betmenu

;---------------Low odds subroutine----------------
lowodd:
    mov                 ecx, odds1
    mov                 edx, len_odds1                     ;Load correct odds string
    jmp                 oddsret

;---------------Med odds subroutine----------------
medodd:
    mov                 ecx, odds2
    mov                 edx, len_odds2                     ;Load correct odds string
    jmp                 oddsret

;---------------High odds subroutine---------------
highodd:
    mov                 ecx, odds3
    mov                 edx, len_odds3                     ;Load correct odds string
    jmp                 oddsret

;----------------Bet made subroutine---------------
;Check if have personal cash to make bet
bet:
    mov dword           eax, PCASHADD                      ;Move address of personal cash into eax
    cmp dword           [eax], PRICEC                      ;Compare to price of hedge betting(25000)
    mov dword           [BETFLAG], 1                       ;Set bet flag to 1, indicates bet was made
    jge                 betmade                            ;Jump to betmade if greater or equal to
    jmp                 inpfunds                           ;Else jump to ipfunds

;Set bet flag to 1 indicating bet was made
betmade:
    mov dword           [BETFLAG], 1                       ;Set bet flag to 1, indicates bet was made
    jmp                 dicego                             ;jmp to dicego

;----------------Bet lost subroutine---------------
;Subroutine runs when bet was lost
betlose:
    mov dword           eax, PCASHADD
    sub dword           [eax], 25000                       ;Subtract the cash
    mov dword           [BETFLAG], 0                       ;Reset betflag to 0         
    jmp                 update                             ;jump to update

betwinlo:          ;Subroutine for win bet high odds
    mov dword           eax, [PCASHADD]
    add dword           eax, 12250                          
    mov dword           [PCASHADD], eax                    ;Add the cash for low odds bet
    mov dword           [BETFLAG], 0                       ;Reset betflag to 0         
    jmp                 update                             ;jump to update

betwinmid:         ;Subroutine for win bet med odds
    mov dword           eax, [PCASHADD]
    add dword           eax, 25000                          
    mov dword           [PCASHADD], eax                    ;Add the cash for mid odds bet
    mov dword           [BETFLAG], 0                       ;Reset betflag to 0         
    jmp                 update                             ;jump to update

betwinhi:          ;Subroutine for win bet low odds
    mov dword           eax, [PCASHADD]
    add dword           eax, 50000                          
    mov dword           [PCASHADD], eax                    ;Add the cash for low odds bet
    mov dword           [BETFLAG], 0                       ;Reset betflag to 0         
    jmp                 update                             ;jump to update

;----------------Roll dice subroutine-------------------
;Function simulates a rolled dice using a random number between 0-100(not perfectly!)
rolldice:
    call                random_number
    xor                 edx, edx                           ;Clear edx
    mov                 edx, [random]                      ;point edx to random
    cmp                 edx, 16
    jle                 done                               ;if less than or equal to 16 jmp done
    cmp                 edx, 33
    jle                 dtwo                               ;if less than or equal to 33 jmp dtwo
    cmp                 edx, 50
    jle                 dthree                             ;if less than or equal to 50 jmp dthree
    cmp                 edx, 66                            
    jle                 dfour                              ;if less than or equal to 66 jmp dfour
    cmp                 edx, 83
    jle                 dfive                              ;if less than or equal to 83 jmp dfive
    jmp                 dsix                               ;if none than jmp dsix

;------------Set Dice result 1 subroutine---------------
;Set dice 1 string and length and result
done:
    mov                 ecx, oned
    mov                 edx, len_oned
    mov dword           [DRES], 1
    ret

;------------Set Dice result 2 subroutine---------------
;Set dice 2 string and length and result
dtwo:
    mov                 ecx, twod
    mov                 edx, len_twod
    mov                 edx, len_oned
    mov dword           [DRES], 2
    ret

;------------Set Dice result 3 subroutine---------------
;Set dice 3 string and length and result
dthree:
    mov                 ecx, threed
    mov                 edx, len_threed
    mov dword           [DRES], 3
    ret

;------------Set Dice result 4 subroutine---------------
;Set dice 4 string and length and result
dfour:
    mov                 ecx, fourd
    mov                 edx, len_fourd
    mov dword           [DRES], 4
    ret

;------------Set Dice result 5 subroutine---------------
;Set dice 5 string and length and result
dfive:
    mov                 ecx, fived
    mov                 edx, len_fived
    mov dword           [DRES], 5
    ret

;------------Set Dice result 6 subroutine---------------
;Set dice 6 string and length and result
dsix:
    mov                 ecx, sixd
    mov                 edx, len_sixd
    mov dword           [DRES], 6
    ret

;--------------Random number subroutine-----------------
;Function simulates a rolled dice using a random number between 0-100
random_number:
    mov                 eax, SYS_TIME                      ;system time interrupt 13
    xor                 ebx, ebx                           ;passes null to time argument
    int                 SYS_CALL                           ;returns time since midnight to eax
    mov                 [random], eax                      ;move to random number reserved memory
    xor                 edx, edx                           ;reset register to 0
    xor                 eax, eax                           ;reset register to 0
    mov                 eax, [random]                      ;move time to eax
    mov                 ecx, 100                           ;divisor (no range 0-100)
    div                 ecx                                ;perform division
    mov dword           [random], 0                        ;Clear random
    mov dword           [random], edx                      ;store random number(remainder of div stored in edx)
    ret

;-------------Insufficient funds message----------------
;Method skips back to main if player has insufficient funds
infunds:
    mov                 ecx, nofunds
    mov                 edx, len_nofunds
    call                sys_write
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print string
    call                continue                           ;Wait for spacebar key to be pressed
    jmp                 hud                                ;Jump to hud

;-------------Insufficient p funds message----------------
;Method skips back to dicechoice if player has personal insufficient funds
inpfunds:
    mov                 ecx, nopfunds
    mov                 edx, len_nopfunds
    call                sys_write                          ;Print message insufficient personal cash
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print press spacebar string
    call                continue                           ;Wait for spacebar key to be pressed
    jmp                 dicechoice                         ;Jump to dice choice

;----------Insufficient carbon energy message--------------
;Method skips back to hud if game has insufficient carbon power level to make selected cut event option
incpower:
    mov                 ecx, nocpower
    mov                 edx, len_nocpower
    call                sys_write                          ;Print message insufficient carbon energy generation
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write                          ;Print press spacebar string
    call                continue                           ;Wait for spacebar key to be pressed
    jmp                 hud                                ;Jump to hud

;------------------Room 101-----------------------------
;Secret cheat menu for debugging
room101:
    call                clearscreen
    call                font_magenta
    call                print_border
    call                cursorhome
    call                endline
    call                endline
    mov                 ecx, r101
    mov                 edx, len_r101
    call                sys_write
    call                font_white
    call                endline
    call                endline
    call                endline
    mov                 ecx, cheat
    mov                 edx, len_cheat
    call                sys_write
    call                printtab
    mov                 esi, keys
    mov dword           edi, 4                             ;Number of bytes to flush
    call                flush
    mov                 ecx, keys
    call                sys_read2                          ;Read key press of four bytes
    cmp dword           [keys], '1'
    je                  codown                             ;If input = 1
    cmp dword           [keys], '2'
    je                  pcup                               ;If input = 2
    cmp dword           [keys], '3'
    je                  codown4                            ;If input = 3
    cmp dword           [keys], '4'
    je                  codown3                            ;If input = 4
    cmp dword           [keys], '5'
    je                  codown2                            ;If input = 5
    cmp dword           [keys], '6'
    je                  codown1                            ;If input = 6
    cmp dword           [keys], '7'
    je                  cpow                               ;If input = 7
    cmp dword           [keys], '8'
    je                  gpow                               ;If input = 8
    cmp dword           [keys], '9'
    je                  ppmup                              ;If input = 9
    cmp dword           [keys], '10'
    je                  csh                                ;If input = 10
    cmp word            [keys], '11'
    je                  cend                               ;If input = 11
    cmp dword           [keys], '12'
    je                  hud                                ;If input = 12
    jmp                 room101 
   
;Method for room 101 debugger, set Carbon to 0
codown:
    mov dword           [COINCREASE], 0
    jmp                 sloop

;Method for room 101 debugger, set Pcash 500000
pcup:
    mov dword           [PCASHADD], 500000
    jmp                 sloop

;Method for room 101 debugger, set Carbon to 40000
codown4:
    mov dword           [COINCREASE], 40000
    jmp                 sloop

;Method for room 101 debugger, set Carbon to 30000
codown3:
    mov dword           [COINCREASE], 30000
    jmp                 sloop

;Method for room 101 debugger, set Carbon to 20000
codown2:
    mov dword           [COINCREASE], 20000
    jmp                 sloop

 ;Method for room 101 debugger, set Carbon to 10000
codown1:
    mov dword           [COINCREASE], 10000
    jmp                 sloop

 ;Method for room 101 debugger, subtract Cpower 1000
cpow:
    sub dword           [CPOWERADD], 1000                
    jmp                 update

 ;Method for room 101 debugger, add Gpower 1000
gpow:
    add dword           [GPOWERADD], 1000                  
    jmp                 update

;Method for room 101 debugger, set PPM to 477
ppmup:
    mov dword           [CARBONADD], 477                  
    jmp                 sloop

;Method for room 101 debugger, add 100000 CASH  
csh:
    add dword           [CASHADD], 100000
    jmp                 sloop


;Method for room 101 debugger, trigger crit power ending
cend:
    jmp                 peventbfail

;--------------------End Program------------------------
end:
    call sys_exit
;-------------------------------------------------------
;--------------------Functions--------------------------
;-------------------------------------------------------

;-----------------------Write---------------------------
;Write out string, arg1 ebx: file desc, arg2 ecx : string, arg 3 edx: number of bytes
sys_write:
    mov                 ebx, DESC_STDOUT                   ;File descriptor std out  
    mov                 eax, SYS_WRITE                     ;system call number 4
    int                 SYS_CALL                           ;80h, system call 32 bit System
    ret                                                    ;return

;-----------------------Read In-------------------------
;Read in function, arg 1 ebx: file desc, returns number of bytes to eax
sys_read:
    mov                 ebx, DESC_STDIN                    ;File descriptor std in
    mov                 eax, SYS_READ                      ;system call number 3
    mov                 edx, 1                             ;Number of bytes to read
    int                 SYS_CALL                           ;80h, system call 32 bit System
    ret

;-----------------------Read In 2-------------------------
;Read in function for 4 bytes, designed to be used with 4 byte variable keys in ecx
sys_read2:
    mov                 ebx, DESC_STDIN                    ;File descriptor std in
    mov                 eax, SYS_READ                      ;system call number 3
    mov                 edx, 4                             ;Number of bytes to read
    int                 SYS_CALL                           ;80h, system call 32 bit System
.next:
    cmp byte            [ecx+edx], 10                      ;Start at end of keys and find endline
    je                  .end                               ;If endline found jump .end
    dec                 edx                                ;Otherwise decrement to check next byte
    jmp                 .next
.end:
    mov byte            [ecx+edx], 0                       ;Set endline character byte to 0
    ret

;---------------------System exit----------------------
;System exit, arg 1 ebx: file desc return status ok to eax?
sys_exit:
    mov                 ebx, DESC_OK                       ;File descriptor return status ok
    mov                 eax, SYS_EXIT                      ;System call number 1
    int                 SYS_CALL                           ;80h, system call 32 bit System
    ret
;---------------------Print Border----------------------
;Prints Border
print_border:
    mov                 ecx, border
    mov                 edx, len_border
    call                sys_write
    ret

;--------------------Clear Screen-------------------------
;Clear screens and set background to black
clearscreen:
    mov                 ecx, escape_clear
    mov                 edx, len_escape_clear
    call                sys_write
    call                background_rgb_black
    ret

;--------------------Cursor to home-----------------------
;Returns cursor to home position
cursorhome:
    xor                 ecx, ecx
    xor                 edx, edx
    mov                 ecx, cursor_home
    mov                 edx, len_cursor_home
    call                sys_write
    ret

;--------------------Cursor save-------------------------
;Saves cursor position
cursorsave:
    xor                 ecx, ecx
    xor                 edx, edx
    mov                 ecx, cursor_save
    mov                 edx, len_cursor_save
    call                sys_write
    ret

;------------------Cursor restore------------------------
;Restores cursor position to saved position
cursorrestore:
    xor                 ecx, ecx
    xor                 edx, edx
    mov                 ecx, cursor_restore
    mov                 edx, len_cursor_restore
    call                sys_write
    ret

;--------------------Cursor to position-----------------------
;Sets cursor at position (places cursor at line number stored in line and at column number stored in column)
cursorcustom:
    mov                 ebx, escape_custom                 ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx
    xor                 ecx, ecx
    mov word            cx, [line]                         ;Place value of line number in ecx
    mov word            [ebx], cx                          ;Add line number to string (2 digit string)
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'H'                         ;H = line number ascii escape character
    inc                 ebx
    mov byte            [ebx], 27
    inc                 ebx
    mov byte            [ebx], '['
    inc                 ebx
    xor                 ecx, ecx                           ;Clear ecx
    mov word            cx, [column]                       ;Add column numbeer to string (2 digit string)
    mov word            [ebx], cx
    add                 ebx, 2
    mov byte            [ebx], 'G'                         ;G = column number ascii escape character
    mov                 ecx, escape_custom
    mov                 edx, len_escape_custom
    call                sys_write                          ;Write completed escape ascii sequence
    ret                            

;-----------------Cursor left # columns---------------------
;Moves cursor left no of columns stored in column
cursorleft:
    mov                 ebx, escape_custom                 ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx
    mov word            cx, [column]                       ;Place value of column number in ecx
    mov word            [ebx], cx                          ;Add line numbeer to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'D'                         ;D= Column left ascii escape character
    mov                 ecx, escape_custom
    mov                 edx, len_escape_custom
    call                sys_write                          ;Write completed escape ascii sequence
    ret 

;-----------------Cursor right # columns---------------------
;Moves cursor right no of columns stored in column)
cursorright:
    mov                 ebx, escape_custom                 ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx
    mov word            cx, [column]                       ;Place value of column number in ecx
    mov word            [ebx], cx                          ;Add line numbeer to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'C'                         ;C= Column right ascii escape character
    mov                 ecx, escape_custom
    mov                 edx, len_escape_custom
    call                sys_write                          ;Write completed escape ascii sequence
    ret 

;-----------------Cursor up # lines---------------------
;Moves cursor up no of lines stored in line)
cursorup:
    mov                 ebx, escape_custom                 ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx
    mov word            cx, [line]                         ;Place value of column number in ecx
    mov word            [ebx], cx                          ;Add line numbeer to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'A'                         ;A= Line up ascii escape character
    mov                 ecx, escape_custom
    mov                 edx, len_escape_custom
    call                sys_write                          ;Write completed escape ascii sequence
    ret 
;-----------------Cursor down # lines---------------------
;Moves cursor down no of lines stored in line)
cursordown:
    mov                 ebx, escape_custom                 ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx
    mov word            cx, [line]                         ;Place value of column number in ecx
    mov word            [ebx], cx                          ;Add line numbeer to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'B'                         ;B= Line down ascii escape character
    mov                 ecx, escape_custom
    mov                 edx, len_escape_custom
    call                sys_write                          ;Write completed escape ascii sequence
    ret 

;-----------------------Wait-----------------------------
;Waits number of secs and nanoseconds definied by two 32bit values, here stored in label timespec in .data section(set values before call)
waitnano:
    mov                 ebx, timespec                      ;Set ebx to point to first dword                           
    mov                 ecx, 0                             ;Set second arg to 0
    mov                 eax, SYS_NANOSLEEP                 ;Put syscall value for nanosleep in eax
    int                 SYS_CALL
    ret

;------------------------Endline-------------------------
;Adds a single line to terminal
endline:
    mov                 ecx, endl
    mov                 edx, len_endl
    call                sys_write
    ret

;--------------------------Tab--------------------------
;Adds a single tab to terminal
printtab:
    mov                 ecx, tab
    mov                 edx, len_tab
    call                sys_write
    ret   

;-----------------Terminal Font to White-----------------
;Changing terminal font color to green and set background to black
font_white:
    mov                 ecx, terminal_white
    mov                 edx, len_terminal_white
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to Green-----------------
;Changing terminal font color to green and set background to black
font_green:
    mov                 ecx, terminal_green
    mov                 edx, len_terminal_green
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to Yellow-----------------
;Changing terminal font color to yellow and set background to black
font_yellow:
    mov                 ecx, terminal_yellow
    mov                 edx, len_terminal_yellow
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to Blue-----------------
;Changing terminal font color to blue and set background to black
font_blue:
    mov                 ecx, terminal_blue
    mov                 edx, len_terminal_blue
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to Cyan-----------------
;Changing terminal font color to cyan and set background to black
font_cyan:
    mov                 ecx, terminal_cyan
    mov                 edx, len_terminal_cyan
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to magenta-----------------
;Changing terminal font color to magenta and set background to black
font_magenta:
    mov                 ecx, terminal_magenta
    mov                 edx, len_terminal_magenta
    call                sys_write
    call                background_rgb_black
    ret

;-----------------Terminal Font to Red-----------------
;Changing terminal font color to red and set background to black
font_red:
    mov                 ecx, terminal_red
    mov                 edx, len_terminal_red
    call                sys_write
    call                background_rgb_black
    ret

;---------------Terminal Font to Custom-----------------
;Changes terminal to color code stored in variable color1 and set background to black
font_custom1:
    mov                 ebx, color_custom                  ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx                                ;Increment pointer
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [color1]
    mov word            [ebx], dx                          ;Add color code from color 2 to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'm'                         ;D= Column left ascii escape character
    mov                 eax, color_custom
    mov                 ecx, color_custom
    mov                 edx, len_color_custom
    call                sys_write                          ;Write completed escape ascii sequence
    call                background_rgb_black
    ret 

;---------------Terminal Font to Custom-----------------
;Changes terminal to color code stored in variable color2 and set background to black
font_custom2:
    mov                 ebx, color_custom                  ;Place escape_custom pointer in ebx to allow incrementing address
    mov byte            [ebx], 27                          ;Place 27 escape character in first position of string
    inc                 ebx                                ;Increment pointer
    mov byte            [ebx], '['                         ;Continue adding escape characters to string
    inc                 ebx                                ;Increment pointer
    mov word            bp, [color2]
    mov word            [ebx], bp                          ;Add color code from color 2 to string
    add                 ebx, 2                             ;Advance pointer by 2
    mov byte            [ebx], 'm'                         ;D= Column left ascii escape character
    mov                 ecx, color_custom
    mov                 edx, len_color_custom
    call                sys_write                          ;Write completed escape ascii sequence
    call                background_rgb_black
    ret 
    
;-----------------Terminal Font to Bold-----------------
;Changing terminal font to bold
font_bold:
    mov                 ecx, terminal_bold
    mov                 edx, len_terminal_bold
    call                sys_write
    ret

;--------------Terminal Font to Underline---------------
;Changing terminal font to underline
font_underline:
    mov                 ecx, terminal_underline
    mov                 edx, len_terminal_underline
    call                sys_write
    ret

;-----------------Terminal Font to Cyan-----------------
;Changing terminal font color to cyan
font_reset:
    mov                 ecx, terminal_reset
    mov                 edx, len_terminal_reset
    call                sys_write
    ret

;---------------Terminal Font to Flashing-----------------
;Changing terminal font to flashing
font_flash:
    mov                 ecx, terminal_flashing
    mov                 edx, len_terminal_flashing
    call                sys_write
    ret

;------------Terminal Font to non-Flashing-----------------
;Changing terminal font to non flashing
font_no_flash:
    mov                 ecx, terminal_no_flash
    mov                 edx, len_terminal_no_flash
    call                sys_write
    ret  

;------------Terminal Background to Black-------------------
;Changing terminal background to black
background_black:
    mov                 ecx, ter_bk_black
    mov                 edx, len_ter_bk_black
    call                sys_write
    ret  

;----------Terminal Background to RGB Black-----------------
;Changing terminal background to rgb black
background_rgb_black:
    mov                 ecx, ter_bk_rgbblack
    mov                 edx, len_ter_bk_rgbblack
    call                sys_write
    ret  
       
;-----------------Terminal Cursor on-----------------------
;Turns terminal cursor on
terminal_cursor_on:
    mov                 ecx, terminal_cursor
    mov                 edx, len_terminal_cursor
    call                sys_write
    ret

;----------------Terminal Cursor off-----------------------
;Turns terminal cursor off
terminal_cursor_off:
    mov                 ecx, terminal_no_cursor
    mov                 edx, len_terminal_no_cursor
    call                sys_write
    ret   

;---------------Terminal Resize Mode ----------------------
;Set terminal size to size of border of game
terminal_resize:
    mov                 ecx, terminal_size
    mov                 edx, len_terminal_size
    call                sys_write
    ret    

;-----Print String with multiple colored characters--------
;Prints string in multiple colors
printcolor:
    mov dword           [length], 1                        ;Set variable length to 1 to track string index
    mov                 dl, [edi]                          ;Move first byte of string (ref in edi) into dl
    mov                 [char], dl                         ;Store char in variable char
printcolorloop:         
    mov                 ecx, char                          ;Print char
    mov                 edx, 1
    call                sys_write
    mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 1000000                 ;Set number of nanoseconds for wait function
    call                waitnano                           ;Call wait nano to wait
    xor                 eax, eax                           ;CLear eax register
    mov                 eax, [length]                      ;Store length in eax
    mov                 dl, [edi+eax]                      ;Store next char at index, current index + eax(length)
    inc                 eax                                ;Increment eax
    mov                 [length], eax                      ;Store new length in length variable
    mov                 [char], dl                         ;Store next char in dl register
    
    cmp                 dl, '0'                            ;Compare char in dl to 0
    je                  pcolor1                            ;If equal jump to subroutine to change font to color1
pcloop1:
    cmp                 dl, '8'                            ;Compare char in dl to 8
    je                  pcolor2                            ;If equal jump to subroutine to change font to color2
pcloop2:
    cmp                 eax, len_earth+1                   ;Compare length to total length of string + 1 (get last char)
    jl                  printcolorloop                     ;If less jmp to printcolorloop
    ret

pcolor1:
    call                font_custom1
    jmp                 pcloop1                            ;After jump back to pcloop1

pcolor2:
    call                font_custom2
    jmp                 pcloop2                            ;After jump back to pcloop2

;------------Convert integer to string--------------
;Changing terminal font to non flashing
int_to_string:
    add                 esi, 9                             ; 0-9 Bytes    
    mov                 byte [esi], 0                      ; String terminator
    mov                 ebx, 10                            ; Move 10 (divide each digit by 10)
    mov dword           ebp, 0                             ;Set ebp to 0 to count digits of number

.next_digit:
    xor                 edx, edx                           ; clear edx register
    div                 ebx                                ; eax /= 10
    add                 dl, '0'                            ; Convert the remainder to ASCII 
    dec                 esi                                ; Store characters in reverse order
    inc                 ebp                                ;Increment no of digits
    mov                 [esi], dl                          ; Move DL register to ESI
    test                eax, eax                           ; Perform a bitwise AND on two operands                   
    jnz                 .next_digit                        ; Repeat until eax == 0
    mov                 eax, esi                           ; Memory address of first Digit
    mov dword           [digits], ebp                      ;Save number of digits
    ret

;-----------Convert 2 digit integer to string----------
;Converts integers for player position into strings for cursor movement(2 bytes)
maze_to_string:
    add                 esi, 2                             ; Add 2 bytes    
    mov                 byte [esi], 0                      ; Add String terminator
    mov                 ebx, 10                            ; Move 10 (divide each digit by 10)

.next_digit:
    xor                 edx, edx                           ; clear edx register
    div                 ebx                                ; eax /= 10
    add                 dl, '0'                            ; Adds 30 to integer to convert to ASCII 
    dec                 esi                                ; Store characters in reverse order
    mov                 [esi], dl                          ; Move DL register to ESI(pointing to line/column)
    test                eax, eax                           ; Perform a bitwise AND on two operands                   
    jnz                 .next_digit                        ; Repeat until eax == 0
    ret

;*-----------------------------------------------------------
;Code for maze mini game
;*-----------------------------------------------------------
mazegame:
    call                clearscreen
    call                print_border
    call                cursorhome                         ;Move cursor back to start
    call                font_green
    mov                 ecx, mazeintro1
    mov                 edx, len_mazeintro1
    call                sys_write                          ;Print string in green
    call                font_white
    mov                 ecx, mazeintro2
    mov                 edx, len_mazeintro2
    call                sys_write                          ;Print string in green
    call                font_yellow
    mov                 ecx, mazeintro3
    mov                 edx, len_mazeintro3
    call                sys_write                          ;Print string in yellow
    call                font_green
    mov                 ecx, mazeintro4
    mov                 edx, len_mazeintro4
    call                sys_write                          ;Print string in green
    call                font_red
    mov                 ecx, mazeintro5
    mov                 edx, len_mazeintro5
    call                sys_write                          ;Print string in red
    call                font_white
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue                           ;Wait for user to press space to continue
    call                mazeinitilise                      ;Set game values
    call                mazestart                          ;Start game
mazeinitilise:
    call                terminal_cursor_off                ;Turn off terminal cursor
    xor                 eax, eax
    mov byte            al, [gamesplayed]                  ;Move no of games played into eax
    xor                 edx, edx
    mov word            dx, [playerstart+(eax*2)]          ;Get correct player start pos from playerstart array at index (gamesplayed*2)           
    mov word            [oldplayerpos], dx                 ;Set value in oldplayerpos from array
    mov word            [playerpos], dx                    ;Set value in playerpos from array
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [targetstart+(eax*2)]          ;Get correct target start pos from targetstart array at index (gamesplayed*2)
    mov word            [tarpos], dx                       ;Set value in targetpos from array
    cmp byte            al, len_playerstart                ;Check that number of games into more than 5
    je                  .resetcounter                      ;If it is reset counter (only six positions in start position arrays)
    inc                 al                      
    mov byte            [gamesplayed], al                  ;Increment gamesplayed
.continue:
    mov word            [oldenemypos], $1E09
    mov word            [enemypos], $1E09                  ;Set enemy position at start
     ;Set up ai value for first movement
    mov byte            [ai], 2                            ;Value to 1 so a wall collision happens immediately
    ret
.resetcounter:
    mov byte            [gamesplayed], 0                   ;Reset gamesplayed to 0
    jmp                 .continue

mazestart:
    call                clearscreen
    call                font_yellow
    mov                 ecx, maze
    mov                 edx, len_maze
    call                sys_write
    call                font_white
    call                gettermios                         ;Get termios values and save
    call                settermios                         ;Set new termios values
    call                startclock                         ;Start clock
    call                printenemy
    call                printtarget
mazeloop:
    call                endcheck
    call                timer
    call                printplayer
    call                enemymove
    call                printenemy
    jmp                 input
    mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    mov dword           [tv_nsec], 1000000                 ;Set number of nanoseconds for wait function
    call                waitnano    
mazeend:
    call                restoretermios                     ;Restore termios values to values before gamestart
    call                font_white
    call                terminal_cursor_on                 ;Turn terminal cursor back on
    jmp                 update

;------------------------------------------------------------
;------------Functions for maze minigame---------------------
;------------------------------------------------------------
printboard:

;Function to get current termios structure array and save current values in old
gettermios:
    ;return current terminal setting to old termios structure to restore them after game
    mov                 eax, SYS_IOCTL                     ;System call number for IOCTL   
    mov                 ebx, DESC_STDIN                    ;File descriptot STDIN
    mov                 ecx, TCGETS                        ;Command TCGETS
    mov                 edx, old                           ;Set edx to point to old, settings returned to edx
    int                 SYS_CALL
    ret 

;Function to set needed terminal settings
settermios:
    ;return current terminal setting to new termios structure to change values to needed settings
    mov                 eax, SYS_IOCTL                     ;System call number for IOCTL   
    mov                 ebx, DESC_STDIN                    ;File descriptot STDIN
    mov                 ecx, TCGETS                        ;Command TCGETS
    mov                 edx, new                           ;Set edx to point to old, settings returned to edx
    int                 SYS_CALL

    ;termios-c_lflag offset 12 in termios structure array
    ;Last byte of bit flags in termios-c_lflag(local modes) 00111011
    ;e=echo i=icanon                                            e  i                                                     
    and	                byte [new+12], ~(ICANON)           ;Set icanon bit flag to 0
    and                 byte [new+12], ~(ECHO)             ;Set echo bit flag to 0 

    ;;termios-c_cc array termios structure array offset= 16, vmin offset 5 
    ;VMIN: min number of bytes before sys_read will return in non-canonical mode
    and                 byte[new+23],  VMIN                 ;Set vmin character constant to 0
    mov                 byte[new+22],  VTIME               ;Set vmin character constant to 0
       
    ;set settings to new values
    mov                 eax, SYS_IOCTL                     ;System call number for IOCTL  
    mov                 ebx, DESC_STDIN                    ;File descriptot STDIN
    mov                 ecx, TCSETS                        ;Command TCSETS
    mov                 edx, new                           ;New values for termios
    int                 SYS_CALL
    ret

restoretermios:
    ;set settings back to old values
    mov                 eax, SYS_IOCTL                     ;System call number for IOCTL  
    mov                 ebx, DESC_STDIN                    ;File descriptot STDIN
    mov                 ecx, TCSETS                        ;Command TCSETS
    mov                 edx, old                           ;Original values for termios
    int                 SYS_CALL
    ret

;---------------Time Funcion--------------------
;Function to set up starting time values
startclock:
    mov word            [line], '02'
    mov word            [column], '19'
    call                cursorcustom                       ;Set cursor to location for printing time
    mov                 eax, SYS_TIME                      ;system time interrupt 13
    xor                 ebx, ebx                           ;passes null to time argument
    int                 SYS_CALL                           ;returns time since midnight to eax
    mov dword           [oldtime], eax                     ;Stores the current time in oldtime to set intial time value
    mov byte            [timervalue], 30                   ;Store 30 in timer value, length of game time
    xor                 eax, eax
    mov byte            al, [timervalue]                   ;Pass number to eax
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write                          ;Print string
    ret                                                 

timer:
    mov                 eax, SYS_TIME                      ;system time interrupt 13
    xor                 ebx, ebx                           ;passes null to time argument
    int                 SYS_CALL                           ;returns time since midnight to eax
    mov dword           [currenttime], eax                 ;Move current
    sub dword           eax, [oldtime]
    cmp                 eax, 1
    jge                 .tick
    ret
    
.tick:
    call                font_white                         ;Restore font to white
    mov dword           eax, [currenttime]                 ;Move current
    mov dword           [oldtime], eax                     ;Make current time now old time
    mov word            [line], '02'
    mov word            [column], '19'
    call                cursorsave                         ;Save current cursor location
    call                cursorcustom                       ;Set cursor to location for printing time
    mov                 ecx, blank
    mov                 edx, len_blank
    call                sys_write
    call                sys_write                          ;Print two blanks to erase previous time
    call                cursorcustom                       ;Move cursor back to custom locations
    xor                 eax, eax
    mov byte            al, [timervalue]                   ;Pass number to eax
    sub byte            al, 1
    mov byte            [timervalue], al
    mov                 esi, buffer                        ;Set source index pointer to point to buffer
    call                int_to_string                      ;Call int_to_string
    mov                 ecx, eax                           ;Result in eax, pass to ecx to print                 
    mov                 edx, [digits]                      ;Number of digits in number
    call                sys_write                          ;Print string
    call                cursorrestore                      ;Restore cursor to saved position
    ret 

;----------------End Check----------------------
;Function to check for game ending conditions
endcheck:
    xor                 eax, eax
    mov word            ax, word [playerpos]
    cmp word            ax, word [enemypos]                ;Check for enemy/player collision
    je                  mazelose                           ;If equal jmp mazelose
    cmp word            ax, word [tarpos]
    je                  mazewin                            ;Check for player/target collision, if equal jmp mazewin
    xor                 eax, eax
    mov byte            al, [timervalue]
    cmp                 eax, 0                             ;Check for timer reaching 0
    jle                 mazelose1                          ;If less than 0 jmp mazelose1
    ret

;Subroutine for maze lose, print text, wait for enter to continue, branch back to mazeend
mazelose:
    call                clearscreen
    call                font_white
    call                print_border
    call                cursorhome                         ;Move cursor back to start
    call                font_red
    mov                 ecx, minialose
    mov                 edx, len_minialose
    call                sys_write
    call                font_magenta
    mov                 ecx, whiskey
    mov                 edx, len_whiskey
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, minialose1
    mov                 edx, len_minialose1
    call                sys_write
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    jmp                 mazeend

;Subroutine for maze lose, print text, wait for enter to continue, branch back to mazeed
mazelose1:
    call                clearscreen
    call                font_white
    call                print_border
    call                cursorhome                         ;Move cursor back to start
    call                font_red
    mov                 ecx, minialose2
    mov                 edx, len_minialose2
    call                sys_write
    call                font_magenta
    mov                 ecx, whiskey
    mov                 edx, len_whiskey
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, minialose21
    mov                 edx, len_minialose21
    call                sys_write
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    jmp                 mazeend

;Screen for maze win condition, print text,add cash, wait for enter to continue, branch back to mazeend
mazewin:
    call                clearscreen
    call                font_white
    call                print_border
    call                cursorhome                         ;Move cursor back to start
    call                font_green
    mov                 ecx, miniawin
    mov                 edx, len_miniawin
    call                sys_write
    mov                 ecx, euro
    mov                 edx, len_euro
    call                sys_write
    call                endline
    call                font_white
    mov                 ecx, miniawin1
    mov                 edx, len_miniawin1
    call                sys_write
    call                endline
    mov                 ecx, pressspace
    mov                 edx, len_pressspace
    call                sys_write
    call                continue
    ;Add Cash
    add dword           [CASHADD], 100000                  ;Add 100000 cash
    jmp                 mazeend    

;----------------Read Input---------------------
;Function to read user input and move player character
input:
    ;mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    ;mov dword           [tv_nsec], 1000000                 ;Set number of nanoseconds for wait function
    ;call                waitnano                       
    ;mov edi, timespec
    ;mov ecx, fdset
    ;mov ebx, 0
    ;mov	[ecx], ebx
    ;inc ebx
    ;mov edx, 0
	;mov	eax, 142
	;int	0x80
    ;cmp eax,1
	;je	.return
    mov                 edi, 1
    mov                 esi, key
    call                flush                              ;Flush key variable, 1 byte
    mov                 ecx, key                           ;Set ecx to point to key
    call                sys_read                           ;Read one byte
    cmp byte            [key], ('w'|'W')                   
    je                  up                                 ;Compare value in key to ascii w or W, jmp equal to up
    cmp byte            [key], ('s'|'S')
    je                  down                               ;Compare value in key to ascii s or S, jmp equal to down
    cmp byte            [key], ('a'|'A')
    je                  left
    cmp byte            [key], ('d'|'D')                   ;Compare value in key to ascii d or D, jmp equal to right
    je                  right
    cmp byte            [key], ('q'|'Q')
    je                  mazeend                            ;Compare value in key to ascii q or Q, jmp equal to mazeend
.return:
    jmp                 mazeloop                           ;Jmp back to mazeloop

;----------------Wall Collision-----------------
;Function to check for player wall collision(player position in eax before call)
wall_collision:
    xor                 edx, edx
    xor                 ecx, ecx
    xor                 esi, esi
    mov                 edx, len_wall                      ;Move value of number of wall positions to edx
    mov                 esi, 0                             ;Set esi to 0
.next:
    mov word            cx, word [walls+esi]
    cmp word            ax, word [walls+esi]               ;Check player position against current walls array position
    je                  mazeloop                           ;Jmp equal back to mazeloop(movement not made)
    add                 esi, 2                             ;Add 2 to esi, array contains words (2 bytes)
    cmp                 edx, 0                             ;Check for end of array
    sub                 edx, 1                             ;Decrement edx (tracking end of array index)
    jne                 .next
    ret

;-----------------Move Up-----------------------
up:
    ;mov dword          [tv_sec], 0                         ;Set number of seconds for wait function
    ;mov dword          [tv_nsec], 0                        ;Set number of nanoseconds for wait function
    ;call               waitnano                            ;Call wait nano to wait
    xor                 eax, eax                           ;Clear eax
    
    mov word            ax, [playerpos]                    ;Move value of player position to ax
    mov word            [oldplayerpos], ax                 ;Save value of player position to old player position
    sub word            ax, 1                              ;Subtract 1 from ax (line up)
    call                wall_collision                     ;Check for wall collision
.allow:
    mov word            [playerpos], ax                    ;Move new value for player position back to playerpos variable
    jmp                 mazeloop

;----------------Move Down----------------------
down:
    ;mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    ;mov dword           [tv_nsec], 50000000                ;Set number of nanoseconds for wait function
    ;call                waitnano                           ;Call wait nano to wait
    xor                 eax, eax                           ;Clear eax
    mov word            ax, [playerpos]                    ;Move value of player position to ax
    mov word            [oldplayerpos], ax                 ;Save value of player position to old player position
    add word            ax, 1                              ;Add 1 to ax (line down)
    call                wall_collision                     ;Check for wall collision
.allow:
    mov word            [playerpos], ax                    ;Move new value for player position back to playerpos variable
    jmp                 mazeloop    
;-----------------Move Left---------------------
left:
    ;mov dword           [tv_sec], 0                         ;Set number of seconds for wait function
    ;mov dword           [tv_nsec], 50000000                 ;Set number of nanoseconds for wait function
    ;call                waitnano                           ;Call wait nano to wait
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear eax
    mov word            ax, [playerpos]                    ;Move value of player position to ax
    mov word            [oldplayerpos], ax                 ;Save value of player position to old player position
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    sub byte            al, 1                              ;Subtract 1 from al (column left)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    call                wall_collision                     ;Check for wall collision
.allow:
    mov word            [playerpos], ax                    ;Move new value for player position back to playerpos variable
    jmp                 mazeloop

;---------------Move Right----------------------
right:
    ;mov dword           [tv_sec], 0                        Set number of seconds for wait function
    ;mov dword           [tv_nsec], 50000000                ;Set number of nanoseconds for wait function
    ;call                waitnano                           ;Call wait nano to wait
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear eax
    mov word            ax, [playerpos]                    ;Move value of player position to ax
    mov word            [oldplayerpos], ax                 ;Save value of player position to old player position
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    add byte            al, 1                              ;Add 1 from al (column right)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    call                wall_collision                     ;Check for wall collision
.allow:
    mov word            [playerpos], ax                    ;Move new value for player position back to playerpos variable
    jmp                 mazeloop

;----------------Enemy Move---------------------
;Function to control enemy movement
enemymove:
    ;mov dword           [tv_sec], 0                        ;Set number of seconds for wait function
    ;mov dword           [tv_nsec], 1000000                 ;Set number of nanoseconds for wait function
    ;call                waitnano                           ;Wait amount of time to match vtime setting
    ;xor                 edx, edx
    ;mov byte            dl, [movecounter]                 ;
    ;dec                 dl
    ;mov byte            [movecounter], dl
    ;cmp                 edx, 0
    ;je                  .move    
    ;ret
    ;.move:
    ;mov byte            [movecounter], 2
    xor                 edx, edx
    mov byte            dl, [ai]                           ;Move value of ai into edx
    cmp                 dl, 1
    jle                 enemyup                            ;if less than or equal to 1 jmp enemyup
    cmp                 dl, 2
    jle                 enemyright                         ;if less than or equal to 2 jmp enemyright
    cmp                 dl, 3
    jle                 enemydown                          ;if less than or equal to 3 jmp enemydown
    cmp                 dl, 4                            
    jle                 enemyleft                          ;if less than or equal to 4 jmp enemyleft
    ret

;-----------------Move Up-----------------------
enemyup:
    xor                 eax, eax                           ;Clear eax
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov word            [oldenemypos], ax                  ;Save value of enemy position to old enemy position
    sub word            ax, 1                              ;Subtract 1 from ax (line up)
    jmp                 enemy_wall_col                     ;jmp to enemy_wall_col to check for collision
.allow:                                                    ;Will jmp back here if no collision occurs
    mov word            [enemypos], ax                     ;Move new value for enemy position back to enemypos variable
    ret

;----------------Move Down----------------------
enemydown:
    xor                 eax, eax                           ;Clear eax
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov word            [oldenemypos], ax                  ;Save value of enemy position to old enemy position
    add word            ax, 1                              ;Add 1 to ax (line down)
    jmp                 enemy_wall_col                     ;jmp to enemy_wall_col to check for collision 
.allow:                                                    ;Will jmp back here if no collision occurs
    mov word            [enemypos], ax                     ;Move new value for enemy position back to enemypos variable
    ret  

;-----------------Move Left---------------------
enemyleft:
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear edx
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov word            [oldenemypos], ax                  ;Save value of enemy position to old enemy position
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    sub byte            al, 1                              ;Subtract 1 from al (column left)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    jmp                 enemy_wall_col                     ;jmp to enemy_wall_col to check for collision 
.allow:                                                    ;Will jmp back here if no collision occurs
    mov word            [enemypos], ax                     ;Move new value for enemy position back to enemypos variable
    ret

;---------------Move Right----------------------
enemyright:
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear edx
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov word            [oldenemypos], ax                  ;Save value of enemy position to old enemy position
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    add byte            al, 1                              ;Add 1 from al (column right)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    jmp                 enemy_wall_col                     ;jmp to enemy_wall_col to check for collision 
.allow:                                                    ;Will jmp back here if no collision occurs
    mov word            [enemypos], ax                     ;Move new value for enemy position back to enemypos variable
    ret

;----------------Wall Collision-----------------
;Wall collision subroutine for enemy movement
enemy_wall_col:
    xor                 edx, edx
    xor                 ecx, ecx
    xor                 esi, esi
    mov                 ebx, walls
    mov                 edx, len_wall
    mov                 esi, 0
.next:
    mov word            cx, word [walls+esi]
    cmp word            ax, word [walls+esi], 
    je                  enemyai
    add                 esi, 2
    cmp                 edx, 0
    sub                 edx, 1
    jne                 .next
    xor                 edx, edx
    mov byte            dl, [ai]                           ;Move value of ai into edx
    cmp                 dl, 1
    je                  enemyup.allow                      ;if equal to 1(up) jmp enemyup.allow
    cmp                 dl, 2
    je                  enemyright.allow                   ;if equal to 2(right) jmp enemyright.allow
    cmp                 dl, 3
    je                  enemydown.allow                    ;if equal to 3(down) jmp enemydown.allow
    cmp                 dl, 4                            
    je                  enemyleft.allow                    ;if equal to 4(left) jmp enemyleft.allow
    jmp                 mazeloop                           ;Otherwise jmp to mazeloop...Just in case!

 ;---------------Enemy ai----------------------
;Enemy ai, called if a wall collision is detected to pick new movement direction
enemyai:
    call                aidirection                        ;Fill alloweddir array with valid movement directions
    xor                 eax, eax                           ;Clear eax
    call                random_number
    mov                 ebx, [random]                      ;Put random number value in ebx
    xor                 edx, edx
    xor                 ecx, ecx
    mov                 eax, ebx                           ;Copy value to eax
    mov byte            cl, [dircounter]                   ;Set ecx to value of dircounter(tracks no of values in alloweddir array)
    div                 ecx
    mul                 ecx                                
    sub                 ebx, eax                           ;Mod random value by number of indices in alloweddir array
    xor                 ecx, ecx
    mov                 esi, alloweddir                    ;Set esi to point to alloweddir array
    mov byte            cl, [alloweddir+ebx]               ;Get new valid movement direction
    mov byte            [ai], cl                           ;Save new direction in cl
    ret

;------------AI Direction Check-----------------------
;Function that fills alloweddir array with all enemy movements(up,down,right,left) that do not result in a wall collision
aidirection:
    mov                 esi, alloweddir
    mov                 edi, 4
    call                flush                             ;Flush alloweddir, 4 bytes
    mov byte            [dircounter], 0                   ;Set dircounter to 0
    xor                 ebx, ebx
    mov byte            bl, [dircounter]                  ;Set ebx to value of dircounter(ebx tracks index position for alloweddir array)
;Check whether up causes wall collision
    xor                 eax, eax                          ;Clear eax
    mov word            ax, [enemypos]                    ;Move value of enemy position to ax
    sub word            ax, 1                             ;Subtract 1 from ax (line up)
    call                ai_wall_col                       ;Call ai_wall_collision to check if a collision would occur
    cmp byte            [allow], 1                        ;If no collision occurs allow flag will be 1(from ai_wall_col)
    jne                 .down                             ;If collision occurs jmp to .down to check next direction
    mov byte            [alloweddir+ebx], 1               ;If no collision occurs, add direction to alloweddir array(1=value for ai up)
    inc                 ebx                               ;Increment ebx to move to next index of alloweddir
;Check whether down causes wall collision
.down:   
    xor                 eax, eax                          ;Clear eax
    mov word            ax, [enemypos]                    ;Move value of enemy position to ax
    add word            ax, 1                             ;Add 1 to ax (line down)
    call                ai_wall_col                       ;Call ai_wall_collision to check if a collision would occur
    cmp byte            [allow], 1                        ;If no collision occurs allow flag will be 1(from ai_wall_col)
    jne                 .left                             ;If collision occurs jmp to .left to check next direction
    mov byte            [alloweddir+ebx], 3               ;If no collision occurs, add direction to alloweddir array(3=value for ai down)
    inc                 ebx                               ;Increment ebx to move to next index of alloweddir
;Check whether left causes wall collision
.left:
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear edx
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    sub byte            al, 1                              ;Subtract 1 from al (column left)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    call                ai_wall_col                        ;Call ai_wall_collision to check if a collision would occur
    cmp byte            [allow], 1                         ;If no collision occurs allow flag will be 1(from ai_wall_col)
    jne                 .right                             ;If collision occurs jmp to .right to check next direction
    mov byte            [alloweddir+ebx], 4                ;If no collision occurs, add direction to alloweddir array(4=value for ai left)
    inc                 ebx                                ;Increment ebx to move to next index of alloweddir
;Check whether right causes wall collision
.right:
    xor                 eax, eax                           ;Clear eax
    xor                 edx, edx                           ;Clear edx
    mov word            ax, [enemypos]                     ;Move value of enemy position to ax
    mov                 dl, al                             ;Save lower order byte in dl
    shr                 ax, 8                              ;Right shift ax to get higher order byte
    add byte            al, 1                              ;Add 1 from al (column right)
    shl                 ax, 8                              ;Left shift ax to put lower order byte in higher order byte
    mov                 al, dl                             ;Restore lower order byte
    call                ai_wall_col                        ;Call ai_wall_collision to check if a collision would occur
    cmp byte            [allow], 1                         ;If no collision occurs allow flag will be 1(from ai_wall_col)
    jne                 .end                               ;If collision occurs jmp to .end
    mov byte            [alloweddir+ebx], 2                ;If no collision occurs, add direction to alloweddir array(2=value for ai left)
    inc                 ebx                                ;Increment ebx to move to next index of alloweddir
.end:
    mov byte            [dircounter],bl                    ;Move byte of ebx back into dircounter(saves number of valid directions put in array)
    ret

;-------------AI Wall Collision-----------------
;Wall collision subroutine for ai_direction
ai_wall_col:
    mov byte            [allow], 0 
    xor                 edx, edx
    xor                 ecx, ecx
    xor                 esi, esi
    mov                 edx, len_wall                      ;edx tracking number of indexs in walls array
    mov                 esi, 0
.next:
    mov word            cx, word [walls+esi]
    cmp word            ax, word [walls+esi]               ;compare new positions against positions in walls array
    je                  .no                                ;Collision would occur, jmp to end, allow stays at 0
    add                 esi, 2                             ;Add two because walls in filled with 2 byte words
    cmp                 edx, 0
    sub                 edx, 1                             ;Decrement edx
    jne                 .next                              ;Jmp to .next if no collision detected at that position to check next position
    mov byte            [allow], 1                         ;Set allow flag to 1 if no collision occurs  
    ret
.no:
    ret

;------------------Turn Zone--------------------
;Subroutine to check for zones where ai could turn off path into new path in maze with no collision occuring(not implemented yet)
turnzone:
    xor                 edx, edx
    xor                 ecx, ecx
    xor                 esi, esi
    mov                 ebx, walls
    mov                 edx, len_wall
    mov                 esi, 0
.next:
    mov word            cx, word [walls+esi]
    cmp word            ax, word [walls+esi], 
    je                  .collision
    add                 esi, 2
    cmp                 edx, 0
    sub                 edx, 1
    jne                 .next
    ret

.collision:
    ret
;--------------Print Player---------------------
;Print players character at correct position and deletes previous player character
printplayer:
    ;Print blank at old player location
    mov                 esi, line
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush
    mov                 esi, column
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush                              ;Flush values in line and column
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [oldplayerpos]                 ;Store old player position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [oldplayerpos]                 ;Store old player position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    call                cursorcustom                       ;Call cursor custom to set cursor to player position
    mov                 ecx, blank
    mov                 edx, len_blank
    call                sys_write                          ;Print blank character
    ;Now print player in new location
    mov                 esi, line
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush
    mov                 esi, column
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush                              ;Flush values in line and column
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [playerpos]                    ;Store player position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [playerpos]                    ;Store player position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                      ;Call maze_to_string to convert integer to string value
    call                cursorcustom                       ;Call cursor custom to set cursor to player position
    call                font_white                         ;Restore font to white
    mov                 ecx, player
    mov                 edx, len_player
    call                sys_write                          ;Print player character
    ;call                retcursorpos                       ;Debugging
    ret

;--------------Print Enemy---------------------
;Print enemy character at correct position and deletes previous enemy character
printenemy:
    ;Print blank at old enemy location
    mov                 esi, line
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush
    mov                 esi, column
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush                              ;Flush values in line and column
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [oldenemypos]                  ;Store old enemy position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [oldenemypos]                  ;Store old enemy position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    call                cursorcustom                       ;Call cursor custom to set cursor to enemy position
    mov                 ecx, blank
    mov                 edx, len_blank
    call                sys_write                          ;Print blank character

    ;Now print enemy in new location
    mov                 esi, line
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush
    mov                 esi, column
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush                              ;Flush values in line and column
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [enemypos]                     ;Store enemy position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [enemypos]                     ;Store enemy position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    call                cursorsave
    call                cursorcustom                       ;Call cursor custom to set cursor to enemy position
    call                font_red
    mov                 ecx, enemy
    mov                 edx, len_enemy
    call                sys_write                          ;Print enemy character in red
    call                cursorrestore
    ;call                retcursorpos                       ;Debugging
    ret

;--------------Print Target---------------------
;Print target symbol at correct location
printtarget:
    ;Print target at location stored in tarpos
    mov                 esi, line
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush
    mov                 esi, column
    mov dword           edi, 2                             ;Number of bytes to flush
    call                flush                              ;Flush values in line and column
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [tarpos]                       ;Store target position word in bottom half of edx
    xor                 eax, eax                           ;Clear eax
    mov byte            al, dl                             ;Pass line number to eax (for division in maze_to_string function)
    mov                 esi, line                          ;Set source index pointer to point to line
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    xor                 edx, edx                           ;Clear edx
    mov word            dx, [tarpos]                       ;Store target position word in bottom half of edx
    shr                 dx, 8                              ;Right shift dx 8 bits to get higher order byte (column position)
    mov                 eax, edx                           ;Pass column number to eax
    mov                 esi, column                        ;Set source index pointer to point to column
    call                maze_to_string                     ;Call maze_to_string to convert integer to string value
    call                cursorcustom                       ;Call cursor custom to set cursor to enemy position
    call                font_green
    mov                 ecx, target
    mov                 edx, len_target
    call                sys_write                          ;Print target character in green
    ret

;--------------Flush buffer function----------------
;Subroutine to flush a buffer, buffer in esi, no of bytes to flush in edi
flush:
    cmp                 edi, 0
    je                  .end
    dec                 edi
    mov byte            [esi], 0
    inc                 esi
    jmp                 flush
.end:
    ret

;--------------Get cursor pos-------------------
;Returns the current cursor position and prints it out for debugging purposes
retcursorpos:
    mov                 ecx, cursor_pos
    mov                 edx, len_cursor_pos
    call                sys_write                          ;Print escape sequence for return cursor position
    mov                 ecx, position                      ;Set position to store cursor position
    call                sys_read                           ;Call system read to read cursor position
    ret

;-----------------Time of Day-----------------------
timeofday:
    mov                 ebx, timespec                       ;Set ebx to point to first dword                           
    mov                 eax, SYS_TIMEDAY                    ;Put syscall value for timeofat in eax
    int                 SYS_CALL

