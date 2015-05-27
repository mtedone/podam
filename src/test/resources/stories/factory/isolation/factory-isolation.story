Meta:
@tag Factory_isolation

Story: It should be possible to run multiple Podam factory instances independently
As a user
I want to run independent instances of Podam
So that I can run multiple factories simultaneously

Scenario: Running one factory should return a filled in POJO
Given I have a single Podam Factory
When I invoke only one instance of Podam
Then The returned POJO should not be null
And The returned POJO should have some fields filled in with data

Scenario: Running multiple factories should lead to different results 
Given I have an executor service with 2 threads and a callable which returns a String
When I invoke the executor service for each thread
And I retrieve the results
Then I should receive 2 distinct results