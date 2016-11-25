

doctor(1).
doctor(2).
doctor(3).
doctor(4).

doctor('50').


nurse(5).
nurse(6).
nurse(7).
nurse(8).


medical(X):-
    doctor(X);
    nurse(X).

multipleJobs(X):-
    doctor(X),
    nurse(X).