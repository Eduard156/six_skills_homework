package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.HttpSherlock

object HttpSherlockScenario {
  def apply(): ScenarioBuilder = new HttpSherlockScenario().scn
}

class HttpSherlockScenario {

  val scn: ScenarioBuilder = scenario("Http Sherlock Scenario")
    .exec(HttpSherlock.postToken)

}
