package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.HttpReqres

object HttpReqres2Scenario {
  def apply(): ScenarioBuilder = new HttpReqres2Scenario().scn
}

class HttpReqres2Scenario {

  val scn: ScenarioBuilder = scenario("Http Reqres Scenario")
    .exec(HttpReqres.postCreate)

}
