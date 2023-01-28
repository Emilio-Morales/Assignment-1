Instructions for compiling
---------------------------

To compile, ensure you are in the appropriate directory.

Then, type javac PrimeThreads.java
After hitting enter, type java PrimeThreads




My approach
-------------

What I intended to do was to have the multiple threads finding prime numbers from specific ranges. I created a class that extends threads and in its constructor I would pass the ranges from where I would want that thread to find prime numbers whenever it is started.

I also had this class store the the sum and number of the primes found when it was ran, and provided a get methods so that I could access this information once all the threads were done executing.

I created a loop that would iterate 8 times, each time creating a new thread that searches for prime numbers in a specific range. Following this, I added another loop that would be infinite until all threads were dead. I did this so that my program would not try to access info via the get methods when the threads were still calculating prime numbers.

Once my threads were no longer alive, I then iterated through the ArrayList storing each thread, and called the get methods to update the number of primes and their sums.

After this was done I printed it to the output file that was required.


