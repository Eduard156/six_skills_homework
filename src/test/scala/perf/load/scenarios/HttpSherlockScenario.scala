package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.HttpSherlock
import perf.load.cases.HttpSherlock.{credFeeder, logger}

object HttpSherlockScenario {
  def apply(): ScenarioBuilder = new HttpSherlockScenario().scn
}

class HttpSherlockScenario {

  val scn: ScenarioBuilder = scenario("Http Sherlock Scenario")
    .feed(credFeeder)
    .exec(HttpSherlock.postToken)
    .exec { session =>
      {
        val login  = session("username").as[String]
        val status = session("responseStatus").as[Int]

        if (status == 200)
          logger.info(s"[Success] Username: $login")
        else
          logger.warn(s"[FAIL] Username: $login")
      }
      session
    }
//    .exec(HttpSherlock.getProfile)

}
