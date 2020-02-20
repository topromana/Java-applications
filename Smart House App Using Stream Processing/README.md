The main objective of this program is to use Java 8, along with its new features, in order to implement an application designed for a smart house. We have to use streams, lambda expressions, functional interfaces and all the new things available from this new language features, in order to  take the data received from a set of sensors that were placed all over a house near the doors, or cabinets, or faucets, and  interpret it in order to draw some conclusions regarding that person’s life habits and the activities he/ she does all throughout the day. 


The application was created in order to determine certain life patterns that the owner of the house has. Basically a .txt file is provided that contains the start time and the end time of a certain activity, specifying the date, and the hour, minutes and seconds of the specific time, plus the label of that activity.
Afterwards, that set of data needs to be streamed and processed accordingly to the demands that the application wishes to fulfill.


We used a couple of data structures. One of them is Array List, which helps us store the entire set of data that we streamed, since the activities.txt that was provided to us contains on each line what could be an object of type Monitored Data.
Another data structure that was used throughout this project was the Map, to be more precise, the Hash Map. We chose to use this specific type of data structure due to the fact that when processing the data, and grouping it by distinct days, or distinct activities, we need to store the information that we gather in a structure that will produce some sort of identification key for that computed information, regardless if we are discussing about the amount of activities or their duration.
