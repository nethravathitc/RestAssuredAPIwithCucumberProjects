Feature: Login functionality

Scenario: Check Landing page login
Given User is on landing page
When User logins using username and password as "jin" and "123"
Then user is logged in
And Home page is shown up with "true"

Scenario: Check Landing page login
Given User is on landing page
When User logins using username and password as "john" and "123"
Then user is logged in
And Home page is shown up with "false"