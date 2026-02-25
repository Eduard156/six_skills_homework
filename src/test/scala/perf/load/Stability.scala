package perf.load

import io.gatling.core.Predef._
import org.galaxio.gatling.amqp.Predef._
import org.galaxio.gatling.config.SimulationConfig._
import org.galaxio.gatling.kafka.Predef._
import org.galaxio.gatling.jdbc.Predef._
import perf.load.scenarios._

class Stability extends Simulation {

  setUp(
    HttpScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    JdbcScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    AmqpScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    KafkaScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
  ).protocols(
    httpProtocol,
    jdbcProtocol,
    amqpProtocol,
    kafkaProtocol,
    // длительность теста = разгон + полка
  ).maxDuration(testDuration)

}
