CODE TEST
===

Marvel exercise for Android Developer

How is made
===

* The application is build based on Clean Architecture and MVP (Model-View-Presenter)
all the data sources are mapped between api and application domains.
* The pattern repository is used to access the data
* Dagger2 for dependency injection with a Component per view
* Programing with clean code in mind, the project use checkstyle to guarantee a
correct code
* There are three important class AbstractActivity, AbstractFragment and
 AbstractAdapterView these classes manage all the lifecycle binding between
 view and presenter, also do the view injections and dependency injection.

Test
===

A few test for coverage can be found in the project

### UNIT TEST

Can be found in app/src/test/java mockito is used for class mock

### UI TEST

Can be found in app/src/androidTest/java
