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