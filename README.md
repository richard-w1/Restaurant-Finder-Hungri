# Restaurant Finder app: "Hungri"

This app was created as the final capstone of the Java development program at Merit America bootcamp.

Hungri is a single-page web based application made with React.js and Spring Boot that allows users find local restaurants, create events and invite their friends to vote on popular restaurants to go to. Users can find restaurants by location, and read descriptions and the restaurants profile. They can then create an event based on location and invite friends to join with a unique id. From there, users in the group can engage in a voting poll until the expiration time and date, which the host can set.
As this application is a proof of concept, some of the additional features that could be implemented in the future would include a more comprehensive voting system, and location api.

# How to use

Run java/database/new_schema.sql to set up the database.
java/database/restaurants.sql includes mock data for example restaurants but you can insert your own into the database.
Run java/src/main/java/com/techelevator/Application.java to start the server.
The server should be hosted on http://localhost:3000/
From the splash page you can create your Username and Password, and filter restaurants in the database by location, as well other features.

# Credits

As this was a group project, my contributions include creating the DTO models and DAO Classes for groups, creation of groups, and restaurants, as well as the React functions to filter Restaurants by locations, Create Groups, viewing Existing groups, joining Groups. Majority of the UI design choices were also done by me.
