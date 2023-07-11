name := "akka-cassandra-persistence-poc"

version := "1.0"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.15",
  "com.typesafe.akka" %% "akka-persistence-typed" % "2.6.15",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "1.0.5"
)
