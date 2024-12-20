ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "bigDataProject"
  )
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.8.1"
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.6"

