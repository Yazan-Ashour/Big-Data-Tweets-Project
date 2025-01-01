ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "big-data-project"
  )
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.8.1"
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.6"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0" classifier "models"
libraryDependencies += "com.lihaoyi" %% "requests" % "0.8.0"
