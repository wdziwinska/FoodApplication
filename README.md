# CookBook App
JavaFx, JDBC, mySQL, Edaman API

The application is written in Java language. For GUI I used JavaFX and database connection is made with JDBC and MySQL. The app uses API: https://api.edamam.com/ 

My application allows the user to find information about different culinary meals like dinners, desserts, or drinks. User can choose the recipe based on name, image, calories, or list of ingredients. The application also enables to save the favourites recipes to local database and delete from local database. User has to have a local MySQL server running on their machine.


<br/>
1. Main page

 ![1](https://user-images.githubusercontent.com/55066543/190878546-9b74cb11-5869-4344-b7fd-8ddb93026ddc.png )

The user can select one of the buttons on the main page depending on what he wants to find.

<br/>

2. Meals page
 
 ![2](https://user-images.githubusercontent.com/55066543/190878540-e37cf263-3d8d-4eef-aacf-45232dbd4bf6.png)

After clicking the "meals" button, the user can enter the main ingredient to be included in the meal, also he can choose:
*	meal type like dinner, breakfast, lunch, ect.
*	diet for example low-sodium, high-protein, balanced, ect.
*	health for example low-sugar, low-potassium, vegan, alcohol-free, ect.
* cuisine type like Italian, American, Mexico, British, ect.
* dish type like salad, sandwiches, soup, starter, ect.
After pressing the "Ok" button, the application goes to the next page.


<br/>
3. Meals page - view with the expanded combobox list

 ![3](https://user-images.githubusercontent.com/55066543/190878532-43eb6900-bf63-4607-8b76-60b7fefea7d6.png width="300" height="300")
 
 
 <br/>
 4. Recipe page

 ![4](https://user-images.githubusercontent.com/55066543/190878530-35d28292-d552-47da-9de3-34f02e9d3aba.png)

On this page, got from API,  is a list of recipes, which meet the user requirements.
The user can add recipe to his favorite list by clicking button “+” or go back to the main page clicking “back” button.


<br/>
5. Drink page

 ![5](https://user-images.githubusercontent.com/55066543/190878529-bc1c926f-19d7-42af-a1d8-9f51eb137d3d.png)

When the user clicks “drinks” button on the main page, he goes on the drink page where he must type main ingredient and also, he can choose:
* dish type like alcohol-coctail, drinks, ect.,
*	cuisine type like Italian, American, Mexico, British, ect.,
*	health for example low-sugar, vegan, dairy-free, ect.,

<br/>

6. Drink page

![6](https://user-images.githubusercontent.com/55066543/190878520-972bcfbb-30d6-4dc7-ae11-6aa762916fa9.png)


<br/>
7. Recipe page

![7](https://user-images.githubusercontent.com/55066543/190878516-3d8c5195-59ed-41ea-a8b1-9a18c23228b6.png)

On this page, got from API,  is a list of drinks, which meet the user requirements. The user can add recipe to his favorite list by clicking button “+” or go back to the main page by clicking the “back” button.

<br/>

8. Dessert page

![8](https://user-images.githubusercontent.com/55066543/190878512-d8974151-c403-4556-b373-3ed18e2c97b2.png)

When the user clicks “desserts” button on the main pagem, he goes on the dessert page where he must type main ingredient and, he can choose:
*	diet for example low-sodium, high-protein, balanced, ect.
*	health for example low-sugar, low-potassium, vegan, alcohol-free, ect.
*	cuisine type like Italian, American, Mexico, British, ect.
After pressing the "Ok" button, the application goes to the next page.

<br/>

9. Recipe page

![9](https://user-images.githubusercontent.com/55066543/190878491-ad636101-3f34-4dac-b040-8ebb3b201a6d.png)

On this page, got from API,  is a list of desserts, which meet the user requirements. The user can add recipe to his favorite list by clicking button “+” or go back to the main page clicking “back” button.


<br/>
10. Main page

![10](https://user-images.githubusercontent.com/55066543/190878485-ffae0c40-917b-4eb7-9c0a-38392ff7d2b2.png)

The user can also choose “favorites” button on the main page.


<br/>
11. Favorite page

![11](https://user-images.githubusercontent.com/55066543/190878482-1dbf9202-db3e-44d3-8825-aa6aa77767cc.png)

On the favorite page, there is a list of recipes that the user has previously added by clicking on the "+" button. Recipes are saved in a local database and displayed after being downloaded from the database. The user can delete the recipe clicking on the “-“ button.

<br/>

12. Favorite page after deleting one of recipe.

 ![12](https://user-images.githubusercontent.com/55066543/190878471-269f8883-648c-4fdd-bdd7-164c8c979a89.png)
 
