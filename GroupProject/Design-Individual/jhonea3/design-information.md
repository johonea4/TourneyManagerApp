Design Information Document
=========================

Requirements:
------------------
1. The tournament is organized as a single elimination tournament with third place playoff.
2. The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.
3. The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.
4. The tournament players use the system to (1) view the match list and (2) view the total player prizes.
5. The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).
6. A player in the system is characterized by the following information:
	1. Name
	2. Unique alphanumeric username
	3. Numeric phone number
	4. A deck choice, from a list of deck options
7. The tournament manager can add a player to and remove a player from the system.
8. There can only be one ongoing tournament at any given time.
9. To start a tournament:
	1. The tournament manager will enter the house cut.
	2. The tournament manager will enter the entry price.
	3. The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.
	4. When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.
10. When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.
11. When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.
12. When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
	1. Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
	2. End an ongoing match and specify a result for it.
	3. End the tournament. If the tournament is ended early, money is refunded.
13. After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database .  
14. After a tournament is completed, the house profit will also be recorded in the underlying database.
15. When there is no ongoing tournament, the tournament manager can:
	1. View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
	2. View the list of past house profits in chronological order and the total.
16. The User Interface (UI) must be intuitive and responsive.

Assumptions:
---------------------
1. "App" refers to the overall application and is represented by the design's entirety.
2. "System" refers to the database of the application. The database is responsible for holding information relating to the players and tournaments.
3. Assumes that the player may wish to view their information as it appears in the system.

Requirements Implementation:
-----------------------------------------
1. This is realized by the creation of the **Match** class and the structure of the **Tournament** class.
2. Created a **Mode** class with the subclasses **ManagerMode** and **PlayerMode**
3. Realized in the methods of the **ManagerMode** class and further explained in this document.
4. Realized in the methods of the **PlayerMode** class and further explained in this document.
5. The **Database** class was created to realize this requirement. The **Database** class is set to be the central point of the application, and contains all information regarding the tournaments and players.
6. The required information was added as members to the **Player** class
7. The methods *addPlayer* and *removePlayer* was added to the **ManagerMode** class. The *addPlayer* method adds a new instance of the **Player** class to the system. The *removePlayer* method removes a player from the system with the specified username.
8. Added the member *running* and *finished* to represent the state of the tournament to the **Tournament** class.
9. Added the methods *enterHousePercentage*, *enterEntryPrice*, *enterPlayers*, *startTournament*, *endTournament*, and *getTournamentInfo* to the **ManagerMode** class to realize this requirement. The **Tournament** class contains an instance of **ToureyInfo** which contains this set of information. From here, the possible winnings and house earnings are calculated.
10. Added the method *getTotals* to the **Mode** class. This operation is common in both modes of operation.
11. Added the method *getMatchList* to the **Mode** class. This operation is common in both modes of operation.
12. Realized by the method added in #11. Also added were the methods *startMatch* and *endMatch*, which allows for the members *running* and *finished* to be set in the **Match** class. The **Tournament** class contains a list of all matches. Each match contains the players involved and the winner of the match (via username). To solve the case of a tournament ending early, the members *startDate*, *endDate*, and *endedEarly* were added to the **Tournament** class.
13.  To realize this, each player contains prize information. When the tournament ends, the winning players have a **Prize** class object added to their prize List. The **Prize** class contains the members *place* and *moneyWon*. Using the player's prize list, the totals can be calculated in a **Total** class object. The **Total** class contains the members *moneyReceived*, *moneyPaid*, and *profit* (Which is calculated)
14. This is realized by the saving of the tournament info in the database.
15. Viewing the totals is realized by the method in #10. The *getPlayerTotals* mehtod was added to the **ManagerMode** class. This requests the prize list of a specified player via username. To realize the house profit requirement, the method *getHouseTotals* was added. The totals returned is an array of size n Tournaments stored. The values of the **Total** class are computed based on the stored tournament info.
16. This requirement is not relevant to the design of the application. 