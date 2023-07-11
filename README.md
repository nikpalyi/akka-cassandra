# Akka Cassandra Persistence plugin

provide with an example project structure and code files to demonstrate how to add Akka Cassandra Persistence plugin, define actor behaviors, domain events, persisted data, and serializers. 

```
poc/
├── src/
│   ├── main/
│   │   ├── scala/
│   │   │   ├── actors/
│   │   │   │   ├── ExampleActor.scala
│   │   │   ├── events/
│   │   │   │   ├── ExampleEvent.scala
│   │   │   ├── serializers/
│   │   │   │   ├── ExampleEventSerializer.scala
│   │   │   │   ├── ExampleStateSerializer.scala
│   │   │   ├── ExampleApp.scala
│   ├── resources/
│   │   ├── application.conf
├── build.sbt

```


To run this example, you need to have the necessary dependencies configured in your build tool (e.g., SBT). Make sure to include the required Akka and Akka Persistence Cassandra dependencies in your build file.

Once you've set up the project structure and added the code files, you can run the ExampleApp to see the POC in action. It creates an instance of ExampleActor, sends some commands, retrieves the current state, and terminates the actor system after a delay.

Please note that you'll need to have a local Cassandra instance running with the default configuration (or update the application.conf file with the appropriate Cassandra connection details) for the Akka Persistence Cassandra plugin to work correctly.

### Todo
include all error handling or production-ready optimizations
modify it according to your specific requirements.
