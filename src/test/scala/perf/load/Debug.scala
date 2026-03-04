package perf.load

import io.gatling.core.Predef._
import org.galaxio.gatling.config.SimulationConfig._
import perf.load.scenarios._

class Debug extends Simulation {

  setUp(
    HttpSherlockScenario()
      .inject(
        atOnceUsers(4),
      ),
  ).protocols(
    httpProtocol,
  ).maxDuration(
    testDuration,
  )

}
