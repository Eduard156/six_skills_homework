package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.HttpReqres

object HttpReqresScenario {
  def apply(): ScenarioBuilder = new HttpReqresScenario().scn
}

class HttpReqresScenario {

  val scn: ScenarioBuilder = scenario("Http Reqres Scenario")
    .exec(HttpReqres.saveRandomUserData)
    .exec(HttpReqres.postCreate)
    .exec(HttpReqres.getSingleUser)

}
