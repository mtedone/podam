Meta:
@tag Thread-safety

Story: Podam Strategy should be thread safe
As a user
I want Podam Strategy to be thread-safe
So that it can be shared between factories

Scenario: Adding specific implementations for abstract types/interfaces should not lead to original data being lost
Given I have an Abstract Strategy
When I add a specific type for an Abstract type
When I add another specific type for the same Abstract type
When I retrieve the content of the specific type for the given Abstract type
Then The returned type should be the same as the first added
