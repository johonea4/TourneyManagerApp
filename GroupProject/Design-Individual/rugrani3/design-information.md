#Design Information

1. The tournament is organized as a single elimination tournament with third place playoff. 
    
    To realize this functionality, I added the "winnerPlayerUserName" attribute in the "Match" class. 
    After a match ends, the manager calls the declareWinner operation to set this attribute, hence eliminating the other player.
    The Tournament when generating the Match class, keeps track of the matches using the "MatchID" attribute. Hence designating the last three matches appropriately. Only the last three matches will be awarded.


2. The application has two modes: tournament  manager and  tournament player.You can assume that the mode is selected when the application starts, with no authentication
involved.
    
    To achieve this functionality, I added the AppMode class with mode attribute. This is global variable available across the app.
    

3. The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.
    
    To achieve this functionality, I added a relationship between Manager and Player to Add and Remove players into the system.
    The Tournament has a startTournament operation which will be invoked by the Manager class. This will generate the Match objects.
    The Tournament class has a displayPrizesAndProfits method which will be invoked by the Manager class.
    
    
4. The tournament players use the system to (1) view the match list and (2) view the total player prizes .
   
    To realize this, I have added a relationship between Player class and Tournament, where a Player can invoke showMatchList operation and showTotalForPlayers operation.
 

5. The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).
    
    To achieve this, I have added a class "Database" which is a entity responsible for database operation. It takes the data and stores it to the database.
    Tournament class will invoke recordTournamentPlayers and recordTournamentStatus operation to persist the data.


6. A player in the system is characterized by the following information:
a. Name
b. Unique alphanumeric  username
c. Numeric  phone number
d. A deck choice, from a list of deck options
    
    To achieve this, I have added these attributes to the Player class namely, player name, username, deck choice and phone number.


7. The tournament manager can add a player to and remove a player from the system.
  
  To achieve this functionality, I added a relationship between Manager and Player to Add and Remove players into the system.
  

8. There can only be one ongoing tournament at any given time.
    
    To realize this, I have added status attribute the Tournament class. The system will make sure that there will be one ongoing tournament using this attribute.
    

9. To  start a tournament:
a. The tournament manager will enter the house cut.
b. The tournament manager will enter the entry price.
c. The tournament manager will enter all player usernames.  For simplicity, let’s
assume that there will be either 8 or 16 players in a tournament .
d. When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the  TourneyCalc app that you developed for Assignment 4. 
The tournament manager will then be able to double check the information and start the tournament.

    To realize this functionality, I have added houseCut, entryPrice, playerUserNameList attributes. The Manager class will invoke the respective setter methods for these attributes.
    The operation showTotalForPlayers and displayPrizesAndProfits will display the total for players and the total prizes and profits.
    The Tournament class will use the playerUserNameList to get the user name of each player and then access the Player class for actual names of the players.
        

10. When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.

    For this, the app will use showTotalForPlayers operation on the Tournament class.
    
    
11. When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches. 

    For this functionality, system will use the Match class to gather all the matches for a tournament. Each Match has player1UserName, player2UserName, status and winnerPlayerUserName which it will tap into for display purpose. It will also access the Player class for actual player names using the username.
    
    
12. When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
a. Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
b. End an ongoing match and specify a result for it.
c. End the tournament. If the tournament is ended early, money is refunded.

    For this functionality, a) Manager class will use startMatch operation on the Match class.
    b) Manager class will use endMatch operation on the Match class.
    c) I have added endTournament and refundMoney() in the Tournament class.
      
        
13. After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database .
    
    For this functionality, I have added recordPrizes in the Database class.


14. After a tournament is completed, the house profit will also be recorded in the underlying database.
    
    For this functionality, I have added recordHouseProfit in the Database class.
    

15. When there is no ongoing tournament, the tournament manager can:
a. View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
b. View the list of past house profits in chronological order and the total.

    To achieve this functionality, I have added showTotalForPlayers operation to view the totals for every player in the system as a list sorted by total.
    I have added showIndividualPrizes operation to view a list of the player’s individual prizes by selecting the player from the list.
    I have added getPastHouseProfits operation to the Database class to view the list of past house profits in chronological order and the total.
    
    
16. The User Interface (UI) must be intuitive and responsive.
    
    This requirement is not considered because it does not affect the design directly.
