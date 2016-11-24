# Prolok

A sample integration and exploration of Prolog and Kotlin.

## Install

Steps needed to get Prolog working with Kotlin:

1. Download the latest stable version of SWI Prolog. 
The architechture (32 / 64 bit) needs to match your JVM's. 

2. In the installation dialog, select JPL to be included. 

3. Include the jpl.jar in your project. It can be found in the installation root/lib.

4. Include the installed root/bin folder in your system's path.


## Utility

#### Consult
Consulting is as easy as ``consult("family.pl")``

#### Query
Creating any Query is done with the query() function. 

``query("descendent_of", X, "ralf")`` will turn into: descendent_of( X, ralf )

By using a helper, one can effectively make it look like Prolog:
 
 ```
 fun descendantOf(one: String, two: String) = query("descendent_of", one, two)
 val t4 = descendantOf(X, "ralf")
 ```

#### Solutions
Getting all solutions can be done like this:

```
val t5 = query("descendent_of", X, Y)
t5.getSolutions(X, Y)
```

It works for any query, from 1 to many arguments. 


#### Compound
To create a Compound with any number of Terms:

``val teacher_of = compound("teacher_of", "aristotle", "alexander")``

