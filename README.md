# Food Trucks
## Week 3 Homework for SkillDistillery
### Overview
  This project replicates a food truck registry

  The user is prompted to enter in a series of food trucks including the food trucks name, what they sell, and how well the truck is rated.
  It also registers the food truck with a unique ID.

  This data is then able to be accessed to find out which food trucks are resisted in the system, What the average rating is of all of the food trucks in the system, and it can display all the food trucks with the highest rating.

  There is also a fair amount of text based prompts and error management. 
### Concepts
- Object Oriented Code
- Static Serialization
- Abstraction

### Technologies Used
- Eclipse
- Java
- Git
- OO

### Lessoned Learned
I took this project as an opportunity to create a very robust command line interface. I tried to go above and beyond at writing efficient and sturdy code. The code I wrote is very modular allowing for easy changes if necessary. I can change the number of trucks to take in, the fields a truck has and is prompted for, even the number of functions the menu can preform with maybe 3 extra lines of code for each.

One of the challenges that this brought about was edge cases. If I force the user to enter 5 trucks each with a different rating, then the code could be very simple. I put the burden of usability on the user. If I want the user to do whatever they want, and I check for user errors I have to put in a large amount of guards. This was grossly time consuming and I hope to find an easier way to do this in the future.
