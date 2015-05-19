Meta:

Narrative:
As a user
I want Podam Factory to be thread-safe
So that I can run multiple factories simultaneously

Scenario: Running one factory should return a filled in POJO
Given I have a Podam Factory
When I invoke Podam
Then The returned POJO should not be null
And The returned POJO should have some fields filled in with data

Scenario: Running multiple factories should lead to different results 
Given I have an executor service with 2 threads and a callable which returns a String
When I invoke the executor service for each thread
And I retrieve the results
Then I should receive 2 distinct results

