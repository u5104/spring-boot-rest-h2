# spring-boot-rest-h2

the small app contains 2 REST enpoints

MyEmailGuiController at /gui/email
and
SupportToolsGuiController at /gui/support

/gui/email allows you to create, update and find accounts and customers.
you can also check for a list of available quotas that are supported.

when saving an account with a specific quota from the available list a new price for the account is calculated using the dummy pricing service.

the /gui/support endpoint allows you to find the quota of a particular customer. a customer might have more than 1 account so you get a list of quotas.

there is some exception handling via ControllerErrorAdvice and the database is seeded via DatabaseSeeder with 2 records.

the h2 console is enabled so you can check what your records look like via a web interface.

there is a simple rest endpoint test to be found in MyEmailGuiControllerTest.

enjoy!
