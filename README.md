# crm project

### Decisions

The project is a model of the purposed idea. It does not execute since the HTTP calls aren't implemented.
To validate the project some tests were purposed and they may be executed with the command below:
```shell script
mvn test
```

The criteria to turn a sales qualified lead into a prospect is to pass various validations with different systems:

> The person should exist in the national registry identification external system
and their personal information should match the information stored in our
local database.

> The person does not have any judicial records in the national archives'
external system.

> Our internal prospect qualification system gives a satisfactory score for that
person. This system outputs a random score between 0 and 100. A lead could
be turned into prospect if the score is greater than 60.

Since there are the request to make the first two validations in parallel and the probability of having two services returning the same thing is rather low. More typically we'll have another service providing a different response type and our goal is to merge two (or more) responses.
We used the Mono class with the provided static zip method which lets us combine two or more results. 

Another important point to note is the needed to call subscribeOn before passing the results to the zip method.

However, the subscribeOn method does not subscribe to the Mono.

It specifies what kind of Scheduler to use when the subscribe call happens. Again we're using the elastic scheduler in this example which ensures each subscription happens on a dedicated single thread.

The last step is to call the zip method which combines the given user and item Monos into a new Mono with the type UserWithItem. This is a simple POJO object which wraps a user and item.