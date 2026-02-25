package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import org.galaxio.gatling.amqp.Predef._
import perf.load.cases._

object AmqpScenario {
  def apply(): ScenarioBuilder = new AmqpScenario().scn
}

class AmqpScenario {

  val scn: ScenarioBuilder = scenario("Amqp Scenario")
    .exec(AmqpActions.publishMessage)

}
