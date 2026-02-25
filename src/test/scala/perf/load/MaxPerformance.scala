package perf.load

import io.gatling.core.Predef._
import org.galaxio.gatling.amqp.Predef._
import org.galaxio.gatling.config.SimulationConfig._
import org.galaxio.gatling.kafka.Predef._
import org.galaxio.gatling.jdbc.Predef._
import perf.load.scenarios._

class MaxPerformance extends Simulation {

  setUp(
    HttpScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    JdbcScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    AmqpScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    KafkaScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
  ).protocols(
    httpProtocol,
    jdbcProtocol,
    amqpProtocol,
    kafkaProtocol,
    // общая длительность теста
  ).maxDuration(testDuration)

}
