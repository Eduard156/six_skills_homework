import sbt.*

object Dependencies {
  lazy val gatling: Seq[ModuleID] = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts",
    "io.gatling"            % "gatling-test-framework",
  ).map(_ % "3.11.5" % Test)

  lazy val gatlingPicatinny: Seq[ModuleID] = Seq("org.galaxio" %% "gatling-picatinny" % "1.0.1")
  lazy val janino: Seq[ModuleID]           = Seq("org.codehaus.janino" % "janino" % "3.1.12")
  lazy val amqpPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-amqp-plugin" % "0.15.1")
  lazy val kafkaPlugin: Seq[ModuleID]      = Seq("org.galaxio" %% "gatling-kafka-plugin" % "0.15.1")
  lazy val kafkaSerializer: Seq[ModuleID]  = Seq("io.confluent" % "kafka-avro-serializer" % "8.1.1")
  lazy val avro4s: Seq[ModuleID]           = Seq("com.sksamuel.avro4s" %% "avro4s-core" % "4.1.2")
  lazy val jdbcPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-jdbc-plugin" % "0.14.2")
  lazy val postgresJdbc: Seq[ModuleID]     = Seq("org.postgresql" % "postgresql" % "42.7.10")
  lazy val gson: Seq[ModuleID]             = Seq("com.google.code.gson" % "gson" % "2.13.2")
}
