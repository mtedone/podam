Meta:
@tag JVMTypes

Story:
As a user
I want Podam Factory to fill in object graphs for all native types
So that I can count on the framework to fill in basic POJOs

Scenario: Running one factory should return a basic POJO filled with basic types
Given I have a Podam Factory for simple types
When I invoke Podam on a POJO with only basic types
Then The returned simple POJO should not be null
And The returned simple POJO should have its basic types filled with data



