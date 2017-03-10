# Test Plan

**Author**: Team 09

## 1 Testing Strategy

### 1.1 Overall strategy

Project is going to use combination of Unit Testing, System testing and Regression testing to test the app.
We are going to automate the Unit testing using AndroidJUnitRunner. This will cover the Unit testing needs of the app. We will make sure that there is 100% test coverage of the app. We will use the Android Studio feature to get the percentage of test coverage data.
We will be automating the User Interface testing and intra-app integration of components within the app, using Espresso testing framework. This will cover the functional UI testing within the app.
Some system testing will be manual to test some of the application features, like End to End testing.
Since most of the tests cases are automated, running these test cases before every build acts as Regression Testing as well.

Following team members will be responsible for the test activities as described under their names.

| Name  | Testing activities| 
|---|---|
|Joseph M. Honea|Unit Testing, System Testing, UI functional testing |
|Indika Pathirage|Unit Testing, System Testing, UI functional testing |
|Rajendra Ugrani|Unit Testing, System Testing, UI functional testing |
    
### 1.2 Test Selection

Since all of the team members are responsible for over all design and implementation of the project, all the testing (Unit, System, Integration etc.) will be mostly white box. Each team member is responsible for writing the automation testing of their own module, making sure that there is a 100% test coverage.
    
### 1.3 Adequacy Criterion

For Unit testing, we will use Android Studio feature to get the test coverage, and to make sure that there is complete test coverage.
We will be cross reviewing the test cases of each team member, to make sure that the test cases are of top quality.
The User Interface Testing using the Espresso Testing framework will be written to cover every UI field to cover complete functional testing of the UI.

### 1.4 Bug Tracking

Team will be using Trello for raising bugs and enhancements, and to track any activities/discussions associated with it. Since Trello offers Kanban baord, it is easier to track the progress.
If an external person has to raise a bug or enhancement request, they can do so by raising GitHub issue. 

### 1.5 Technology

Project will use JUnit (AndroidJUnitRunner) for Unit Testing and Espresso testing framework for functional/UI testing, noth of which are nicely integrated with Android Studio.
Android Studio also offers test coverage feature, which will be utilized for detecting test coverage.

## 2 Test Cases

| Purpose  |   Test case|  Expected |  Actual |  Pass/Fail/Comments |
|---|---|---|---|---|
| Testing Application mode - Manager | <ul><li>Open the app home page</li><li>Click Tournament Manager button</li></ul>  | The page should display only options available for a tournament manager like Start Tournament, Manager Player etc.  |   |   |
| Testing Application mode - Player| <ul><li>Open the app home page</li><li>Click Player button</li></ul> |  The page should display only options available for a player like View Player Totals and View Rounds | |   |
| Authentication test| <ul><li>Open the app home page</li><li>Click Tournament Manager button</li></ul> | The app should not authenticate the user| | |
| Testing Add players | <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Click the Manage player and then Add player button</li></ul> | The Tournament manager should be able to add player info like Name, username, phone number and deck option | | |
| Testing Add players | <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Click the Manage player and then Add player button</li></ul> | The Tournament manager should be able to add a players information and save it to the database | | |
| Testing Remove players | <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Click the Manage player and then select a player, remove the player</li></ul> | The removed player must be deleted from the database | | |
| Testing Manager - Run Tournament - check attributes| <ul><li>Start Manager mode and add several players</li><li>Click on Start Tournament button</li></ul>| The create tournament page should offer the option to enter house cut, entry price and player usernames | | |
| Testing Manager - Run Tournament - confirmation | <ul><li>Start Manager mode and add several players</li><li>Click on Start Tournament button</li></ul>| The manager should be able to double check the prizes and profits on the start tournament page before confirming | | |
| Testing Manager - Run Tournament| <ul><li>Start Manager mode and add several players</li><li>Click on Start Tournament button</li></ul>| The Tournament should get started by generating all the rounds and matches and the manager should be able to view that | | |
| Testing Manager - Single Tournament| <ul><li>Start Manager mode and add several players</li><li>Click on Start Tournament button</li></ul>| If a tournament is already running, Manager should not see the start tournament button again. He should not be able to start another tournament | | |
| Testing Manager - Display Prizes and Profits | <ul><li>Start Manager mode and add several players</li><li>Click on Start Tournament button</li><li>Click on View Prizes and Totals</li></ul>| The app should display all prizes and totals corresponding to the players in the tournament | | |
| Testing player - View the Match List | <ul><li>After the Manager has started the tournament, login as one of the player</li><li>Click on View Rounds and then Round info</li></ul> | The player should be able to view the entire match list of the tournament | | |
| Testing player - Match list details | <ul><li>After the Manager has started the tournament, login as one of the player</li><li>Click on View Rounds and then Round info</li><li>View each match info</li></ul> | Match list must display players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches | | |
| Testing Manager - View Match list| <ul><li>Start Manager mode, add several players</li><li>Click on Start Tournament button</li><li>Click view match list</li></ul>| The manager must be able to view the current match list, ongoing matches, completed matches and its status | | |
| Testing Manager - View Total| <ul><li>Start Manager mode, add several players</li><li>Enter house cut and Entree fee</li><li>View player list</li></ul>| The player list should be sorted according to the total for every player | After creating and ending several tournaments, the View Player Totals activity is started. The players are sorted by their total winnings in descending order. Clicking on a player, the player's information is displayed and a list of the player's prizes is visible. Clicking on a prize displays information for the prize won for a given tournament. | Passed |
| Testing Manager - View Individual prizes| <ul><li>Start Manager mode, add several players</li><li>Enter house cut and Entree fee</li><li>View player list</li>li>Select a player</li></ul>| The app should display players individual prize amount | | |
| Testing Manager - Start Match| <ul><li>Start Manager mode, add several players</li><li>Click on Start Tournament button</li><li>Click view match list, and select a match</li></ul>| Manager must be able to start the match, and change its status to "ongoing" | After starting a tournament, the first round's matches are populated with players. Selecting the first round, a match is started and then the End Match button displays. Going back to the match list, it is visible that the match is running. Going to the round list, the round state shows as running. | Passed |
| Testing Manager - End Match| <ul><li>Start Manager mode, add several players</li><li>Click on Start Tournament button</li><li>Click view match list, and select an ongoing match</li></ul>| Manager must be able to end the match, and specify a winner | After starting a tournament, the first round's matches are populated with players. Selecting the first round, a match is started and ended. A dialog then appears asking for the winner. After selecting the winner, the match is ended. This process is repeated untill all matches have been completed. No issues seen. | Passed |
| Testing Manager - End Tournament| <ul><li>Start Manager mode, add several players</li><li>Click on Start Tournament button</li></ul>| Manager must be able to end this tournament by clicking on End Tournament button | After starting the tournament, the manager activity displays the End tournament button. The manager is able to click this button. | Passed |
| Testing Manager - End Tournament - Refund| <ul><li>Start Manager mode, add several players</li><li>Click on Start Tournament button</li><li>Click on End Tournament button</li></ul>| Once the tournament ends, the refund for each player should be calculated by the app, and should be visible to the Manager | The app displays a message stating the money has been refunded to the players. The refunded amount is visible in the Tournament Totals View | Passed |
| Testing player - View the total player prizes|  <ul><li>After the Manager has started the tournament, login as one of the player</li>li>Click on View Total Players</li></ul> | The player should be able to view the player totals, but it should not display the totals for the current tournament | Tournament is running. Selecting the View Player Totals activity, all players are displayed with their totals. Clicking through the players, totals for the current on going tournament are not visible | Passed |
| Testing player - View all player total|  <ul><li>when there is no ongoing tournament, login as one of the player</li></ul> |  The player mode should display totals for every player in the system as a list sorted by total| Selecting Player Mode when opening the app. A tournament is not running. The Player Mode actitvity displays a single choice to view player totals. The totals are listed and are in descending order. | Passed |
| Testing Single Elimination| <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Start a tournament</li>Select a match<li>End the match by choosing a winner</li></ul> | The lost player should be eliminated from the tournament | After ending all matches for a round, the next round is populated with only the winning players. | Passed |
| Testing Tournament Complete - Third Place Playoff |  <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Complete all the matches which are running running</li></ul>| The Tournament should announce the 1st, 2nd and third winner and their totals | All matches are set to complete. The player totals values are seen as updated in the player totals activity. Clicking through and viewing the prize information for each player shows their place and amount of money won. | Passed |
| Testing Tournament Complete - House Profit |  <ul><li>Open the app home page</li><li>Click Tournament Manager button</li><li>Complete all the matches which are running running</li></ul>| The app should record the house profit corresponding to the numbers entered by the manager while starting the tournament. The value should be displayed by the app and should be stored in the database | All matches for the running tournament set to completed. Notification appeared stating all rounds are complete. On manager screen, clicked End Tournament. The tournament was then visible with recorded values in the Tournament Totals list. | Passed |
| Testing Manager - View past house profits| <ul><li>Start and complete at least three tournaments with minimum 8 players</li></ul>| Manager should be able to view the list of past house profits in chronological order and the total | 6 Tournaments started and completed. One Tournament was ended early. Tournament Totals page displays 6 tournaments in descending order by house profits. One Tournament displays as Refunded | Passed |
