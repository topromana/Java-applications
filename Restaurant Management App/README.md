The main objective of this assignment is to implement an application for managing a restaurant. We must transpose real-life objects such as: a waiter, a chef, the administrator of the restaurant, the menu items and the restaurant itself into classes. In this way, we use layered architecture to design the class and to make the application as “user-friendly” as possible, and to look as much alike as a real order application as possible.


We also need to pay attention to all the principles of Object Oriented Programming: abstraction, encapsulation, polymorphism and inheritance, implementing the layered architecture pattern, and implementing some well know design patterns such as observable and composite design pattern, these being the other objectives of the project.


The application was created for two different types of users, each of them having different rights regarding the changes he/ she can make concerning the restaurant.
In the case of the administrator, he has the possibilities of creating a new menu item, wether it is a base or a composite type of product, to update and already existing item, and to delete an item, all of this while having real-time access to a table that refreshes automatically and displays all of the products currently existing in the restaurant’s menu.


In the case of the waiter, his possibilities focus more towards orders. He can create an order, add to it the products that the clients desire, compute the bill and even ask for the bill to be displayed in a .txt file, while all of the orders are displayed, also in real time, in a table that refreshes automatically when a change in the data is detected.
The user first needs to select which is the “area” in which he plans on making some changes, and after choosing the frame corresponding to it he simply fills in all the required fields with the proper informations and selects the desired operation. The contents of that table will be displayed in real time in the UI.


The main data structure used in this project would be: the Array List, with it’s primary purpose  to help us store the lists of Menu Items, resulted after executing certain operations.
Another important data structure is the Hash Map, along with Set. These two structures were used in order to model as realistically as possible the orders. Each order placed by a client will have a key computed based on the fields of the class Order, and it will store at the location provided by that key the set of menu items that the client wished to consume.

