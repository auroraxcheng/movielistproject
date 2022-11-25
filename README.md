# My Personal Project

## Animated films tracker  
Application that keeps a log of all the films someone has watched 
and wants to watch in two different lists, whilst also returning data analysis 
about the genre and running time of all the media consumed. 

**Who will use it?**
- anyone who wants to keep track of the movies they've watched
- anyone who has a list of films they want to watch that they often forget about
- anyone curious about the statistics of the media they consume 
- anyone interested in anime and films 

*This project is of interest to me* because I enjoy watching anime and 
movies in my free time. I think that having a tracker that logs everything 
I've already watched and movies I've been meaning to watch 
would be useful as someone who can be forgetful sometimes. Additionally, 
being able to see the total hours spent and what genre I watch the most of
would be interesting and help me learn more about my personal taste. 

## User Stories 
- As a user, I want to be able to add movies I've already watched to a list 
- As a user, I want to be able to add movies I want to watch to a list
- As a user, I want to be able to remove movies from either lists 
- As a user, I want to be able to move movies from the *to watch list* to the 
*already watched* list
- As a user, I want to be able to see the number of movies in each list 
- As a user, I want to be able to see the total running time of the movies in each list
- As a user, I want to be able to save my towatch and already watched lists to file 
- As a user, I want to be able to start the application and have to option to load my towatch and alreadywatched lists from before 

## Instrutions for grader
- You can generate first required event related to adding Xs to a Y by clicking add movie button
-  You can generate second required event related to adding Xs to a Y by clicking Animated movies only button
- You can add movies to the list by entering movie name into the first text box, 
movie genre in the second text box, and movie duration into third text box 
- you can locate the visual componenet by clicking the animated movies only button, a popup image should appear
- you can save the state of the application by clicking save workspace 
- you can reload the state of the application by clicking the load workspace button 

## Phase 4: task 3 
- I don't think I would have refactored anything in my model 
package as I think that it's organized in a way that makes sense
- However, I might have refactored my MoviePanel GUI class and split it into smaller subclasses 
to increase cohesion and make the code more readable. (ie. helper class to make the buttons and helper methods for the duplicate code regarding the button designs)