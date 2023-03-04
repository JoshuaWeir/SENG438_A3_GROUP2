**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#: 2      |     |
| -------------- | --- |
| Robert |     |
|   Joshua              |     |
|       Jana         |     |
|           Ernest     |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

The goal of this lab is to become famailiar with automated unit testing and using different techniques to analysis the test coverage. We will accomplish this by using JUnit in Eclispe to test the SUT with use cases. We will use the coverage tool EclEmma to analyze the Statement, Branch, and Method coverage of the test vases in relation to the SUT.

The SUT is a modified version of the JFreeChart framework. In Assignment 2, test cases were written for two of the classes withing the framework: Range and DataUtilities. We will supplement and modified these tests to meet the required coverage criteria. Because this lab focuses on White-Box testing, we will use the Javadoc specifications and source code when developing our tests and comparing the expected and actual output values.

# 2 Manual data-flow coverage calculations for DataUtilities.calculateColumnTotal() and Range.intersect() methods

<strong>DataUtilites.calculateColumnTotal()</strong>

Data Flow Graph is in repository.<br>

Def-Use Sets: <br>

DU-Pairs: <br>

Pairs Covered per Test Case: <br>

DU-Pair Coverage: <br> <br>

<strong>Range.intersect()</strong>

Data Flow Graph is in repository.<br>

Def-Use Sets: <br>
du(1,b0) = { [1,2], [1,3] } <br>
du(1, b1) = { [1,2], [1,3] }

DU-Pairs: <br>
du(1, 2, b0) = { [1,2] } <br>
du(1, 3, b0) = { [1,3] } <br>
du(1, 2, b1) = { [1,2] } <br>
du(1, 3, b1) = { [1,3] } <br>

Pairs Covered per Test Case: <br>
intersectLowerBound – du(1, 2, b0), du(1, 2, b1) <br>
intersectUpperBound – du(1, 3, b0), du(1, 3, b1) <br>
intersectLowerHalf – du(1, 2, b0), du(1, 2, b1) <br>
intersectUpperHalf – du(1, 3, b0), du(1, 3, b1) <br>
noIntersectLower – du(1, 2, b0), du(1, 2, b1) <br>
noIntersectUpper – du(1, 3, b0), du(1, 3, b1) <br>
oneValueInRange – du(1, 3, b0), du(1, 3, b1) <br>
oneValueNotInRange – du(1, 3, b0), du(1, 3, b1) <br>
completeIntersectOutside – du(1, 2, b0), du(1, 2, b1) <br>
completeIntersectInside – du(1, 3, b0), du(1, 3, b1) <br>

DU-Pair Coverage: <br> <br>
$CU = CU(b0) + CU(b1) + CU(lower) + CU(upper) = 1 + 1 + 2 + 1 = 5$ <br>
$PU = PU(b0) + PU(b1) + PU(lower) + PU(upper) = 1 + 0 + 1 + 0 = 2$ <br>
$CU_F = 0$ <br>
$PU_F = 0$ <br>
$CU_C = CU_C(b0) + CU_C(b1) + CU_C(lower) + CU_C(upper) = 1 + 1 + 2 + 1 = 5$ <br>
$PUC = PU_C(b0) + PU_C(b1) + PU_C(lower) + PU_C(upper) = 1 + 0 + 1 + 0 = 2 $ <br>

$
Coverage = \frac{CU_C + PU_C}{(CU+PU)-(CU_F+PU_F)}=\frac{5+2}{(5+2)-(0+0)}=1
$

# 3 A detailed description of the testing strategy for the new unit test

To develop our tests, we split the group into to pairs, with each responsiple for the coverages of one class. Ernest and Robert developed tests for Range, and Joshua and Jana developed tests for DataUtilities. In these groups, we used partner programming. Each group followed the same strategy.

To develop tests for each method, the groups first ran the tests from Assignment 2 with EclEmma installed. This provided a lower benchmark of the coverage, and allowed us to see what code wasn't covered. Using EclEmma, we were able to see what source was not covered, as it was highlighted in red. After this diagnostic step, we analyzed the source code to gain an understanding of the data flow and branch decisions of each method. Tests were written with input arguments designed to exploiut the branch decisions and cover the code.

Both teams started with Branch Coverage. We targeted methods with multiple decisions (if-statements and for-loops), and wrote code to test each case (one unit test per case). After 70% Branch Coverage was reached, we then targeted Method Coverage. Both teams found methods that had not previously had tests written for them, and wrote tests for all outcomes based on the source code. Once 60% method coverage was achieved, we worked on Instruction Coverage. After targetting Branch and Method Coverage, most of the code was tested, but we were able to isolate non-tested instructions using the red-highlight feature in EclEmma.

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

<strong>DataUtilities.equal() - equalFirstArgNull(): </strong><br>
The function DataUtilies.equal() takes two double[][] arguments, a and b, and determines if they are equal. Equal is defined by having the same elements in the same order. Analyzing the source code shows that the first condition the method checks is if a is null. This case was not covered in our tests, so it was not covered. By testing this method with the first argument null, we increased the branch coverage of the class.

<strong>DataUtilities.equal() - equalSecArgNull(): </strong><br>
Similar to above, our tests did not cover a null second argument. Analyzing the source code shows that the second condition the method checks is if b is null. At this point, it is determined that a is not null and b is null so they are not equal. This branch was tested by calling the method with a first argument that was not null and a second argument that was null. By testing this method with the second argument null, we increased the branch coverage of the class.

<strong>DataUtilities.clone() - nonNullSource(): </strong><br>
In our original version of tests, we did not have any test methods for the function DataUtilities.clone(). This test case tests a valid, non null argument, which is cloned into a new double[][] object. This test increases method coverage.

<strong>Range.constrain() - valueAboveBoundsTest(): </strong><br>
The function Range.constrain() has a nested if-statement. This means that three conditions must be tested to coverage all possible branches. This test method inserts a value such that the outer and inner if-statements are true. More specifically, the argument, value, is not in the range and is greater than the upper bound. This particular condition returns the upper bound of the range. This test case increases all three coverages, as this method was not tested before.

<strong>Range.constrain() - valueBelowBoundsTest(): </strong><br>
As above, the second inner if-statement condition was not tested. This test method inserts a value such that the outer statement is true and the inner statement is false true. More specifically, the argument, value, is not in the range and is less than the lower bound. This particular condition returns the lower bound of the range. This test case increases all branch and instruction coverages, as it tests conditional code that was previously not tested.

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

<strong> DataUtilities </strong>
| Coverage Type   | Coverage Amount |
|-----------------| ---------------- |
| Instruction     |   90.9% |
| Branch          |   73.4% |
| Method          |   100.0% |

<strong> Range </strong>
| Coverage Type   | Coverage Amount |
|-----------------| ---------------- |
| Instruction     |   88.4%* |
| Branch          |   89.0% |
| Method          |   100.0% |

*Note: Please see Section 6 for explanation

# 6 Pros and Cons of coverage tools used and Metrics you report

<strong>Pros:</strong><br>
Using the EclEmma coverage tool had some pros. The interface is very informative and it is easy to see what code is covered and what code is not covered. The highlighting feature (red, green), as well as the metrics displayed in the Coverage window allow for quick analysis of the tests' coverages. This, combine with the JUnit testing information, makes writing unit tests for coverage simple and quick.

Regarding the metrics we used, the Instruction and Branch coverages were the most useful. These metrics give a detailed picture of what code is covered, as well as what method conditions have been tested. This is especially important when some methods have numerous conditions.

<strong>Cons:</strong><br>
The cons of the EclEmma include some user challenges. Sepcifically, we had issues with the Mocking framework, which we relied on heavliy in our previous unit tests.

The coverage methods we used were mostly effective. However, method testing does not provide a detailed idea of what has been tested within the methods, so it should be used as more of a baseline metric. Additionally, some instructions were unreachable due to the logic in the code (hence the 88.4% instruction coverage of Range), so rpeatedly trying to test these was frsutrating and time-consuming.
infeasible
# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

The biggest difference between requirements-based test generation and coverage-based test generation is how test cases are developed. In Assignment 2, one of our biggest challenges was identifying input partitions and writing test cases for each method. This was challenging, as it is not only difficult to find tests cases, but we also had to manipulate the written tests around the system (like testing around or with exceptions). However, in coverage-based test generation, the Coverage Tool provides tangible metrics and indicators to aid in finding all possible test cases. This made comprehensive testing significantly easier.

Additionally, White-Box testing allows a tester to understand how the code works and to test if it is running properly. Black-Box testing relies on expected and actual outcomes, which limits how deep the testing can be.

# 8 A discussion on how the team work/effort was divided and managed

For this lab, we collaboratively designed our test strategy.

As described above, the coverage test suites were developed in pairs; Ernest and Robert wrote tests for Range, and Joshua and Jana wrote tests for DataUtilities. Additionally, the manual data-flow coverage calculations in Section 2 were done by the same pairs that wrote the tests for each class.

Regarding the lab report, we answered the questions as a group with Joshua acting as scribe.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

The greastest challenge our group faced was familiarizing ourselves with the testing environment and coverage tools. This includes working around mock objects, understanding the functionalities associated with Java annotations, creating meaningful JUnit tests, and using EclEmma with all its features.

# 10 Comments/feedback on the lab itself

This lab was very helpful because it allowed us to gain experinece in anaylzing coverage metrics and designing tests cases to increase these metrics. Additionally, we gained experience in White-box testing.
