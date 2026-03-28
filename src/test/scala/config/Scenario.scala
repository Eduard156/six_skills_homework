package config

import io.gatling.core.structure.ScenarioBuilder
import perf.load.scenarios._

object Scenario {

  def scn(x: String): ScenarioBuilder = x match {
    case "reqres" => HttpReqresScenario()
    case "smoke"  => HttpReqres2Scenario()
    case _        => HttpReqresScenario()

  }

}
