# 4156-Project

## What's new
com/humanlearning/rentermatch/mapper/MatchMapper.java

## Exact sequence of refactoring operations

Inside IntelliJ, I choose the class that I would like to refactor and then right click on it. Then I choose refactor -> Extract Interface, fill out the name and the package of the new interface. The target destination directory is ‘leave in same source root’. Inside ‘Member to Form interface’, I choose the ‘getMatch’ method. Then click refactor. Then I search the usage of the method and correct them with the new interface class name and new method name. 
![image](https://user-images.githubusercontent.com/90531103/206824025-95cf390d-6492-41bf-ad1b-6cc1c9e0936b.png)


## How To Build, Run and Test

To build this project, run

```bash
$ mvn package
```

To run this project, see configuration as below, use IntelliJ internal running tool to run
RentermatchApplication.java.
![img.png](img.png)

To test this project, click maven->plugin->test.

