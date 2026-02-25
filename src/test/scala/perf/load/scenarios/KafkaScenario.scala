package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import org.galaxio.gatling.kafka.Predef._
import perf.load.cases._

object KafkaScenario {
  def apply(): ScenarioBuilder = new KafkaScenario().scn
}

class KafkaScenario {

  val scn: ScenarioBuilder = scenario("Kafka Scenario")
    .exec(KafkaActions.sendMessage)

}
