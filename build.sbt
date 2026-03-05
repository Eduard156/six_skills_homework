import Dependencies.*

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "perf",
        scalaVersion := "2.13.18",
        version      := "0.1.0",
      ),
    ),
    name := "load",
    libraryDependencies ++= gatling,
    libraryDependencies ++= gatlingPicatinny,
    libraryDependencies ++= janino,
    libraryDependencies ++= amqpPlugin,
    libraryDependencies ++= kafkaPlugin,
    resolvers += "confluent" at "https://packages.confluent.io/maven/",
    libraryDependencies ++= kafkaSerializer,
    libraryDependencies ++= avro4s,
    libraryDependencies ++= jdbcPlugin,
    libraryDependencies ++= postgresJdbc,
    libraryDependencies ++= gson,
    libraryDependencies ++= javaFaker,
      scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-Xfatal-warnings",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-language:postfixOps",
    ),
  )
