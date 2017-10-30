# Frequency-Traveler

The FrequentTravelerTracker is a program that keeps track of a traveler’s frequent traveler program memberships.  
Each traveler can belong to any number of frequent traveler programs.

There are two types of frequent traveler programs: frequent flier programs and frequent guest programs. 

A frequent flier program allows travelers to collect frequent flier miles for each trip purchased. 
When a traveler purchases a ticket, their frequent flier program membership will be credited with the number of miles flown or a minimum number (specific to that program), whichever is greater. 
Travelers can also purchase award travel using their miles. The number of miles required depends on the class of ticket, which depends on the airline. 
When they purchase an award ticket, the system will either deduct the appropriate number of miles from their membership or inform them that they do not have enough (telling them how many they would have needed for their purchase).

A frequent guest program membership allows travelers to collect points for each hotel stay. 
The number of points awarded per night depends on the class of hotel (which is specific to that program). 
Travelers can also purchase hotel nights using their points. 
The number of points required depends on the class of hotel (which is specific to the program).
When a traveler purchases award nights, the system will either deduct the appropriate number of points from their membership or inform them that they do not have enough (telling them how many they would have needed).


When the user runs the FrequentTravelerTracker, they can either indicate that they want to add a new traveler or edit an existing traveler. When creating a new traveler, they specify the name of the traveler and which programs they belong to. They can select any number of programs but the system will report an error if they try to add the same program twice. 
Each traveler has a unique member ID generated for each frequent traveler program membership. The same traveler can have the same ID for different programs but there can not be two travelers with the same ID for the same program (i.e., the traveler-program relationship should be uniquely identified by ID).
