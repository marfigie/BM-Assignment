# BM-Assignment
Solution to BM recruitment assignment

Project written using core java8 + Junit4.

Created as MVN project, to start app run:  
>> mvn test

Algorithm works as follows:

Total cash is split between different fund types (Domestic, Foreign, Cash).
The reminder from division is returned as unallocated.

Cash allocated to given fund type is then divided equally between all funds of this given type.
If there is remainder, it is added to first fund of this type.