package perf.load

import io.gatling.core.Predef._
import org.galaxio.gatling.config.SimulationConfig._
import perf.load.scenarios._

class Debug extends Simulation {

  setUp(
    HttpPetStoreScenario().inject(atOnceUsers(10)),
  ).protocols(
    httpProtocol,
  ).assertions(
//    global.responseTime.mean.lt(500),        // среднее < 500 мс
//    global.successfulRequests.percent.gt(99), // успешных > 99 %
//    global.failedRequests.percent.lt(1)
  ).maxDuration(testDuration)

}
