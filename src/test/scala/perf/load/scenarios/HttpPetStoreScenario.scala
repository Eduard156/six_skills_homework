package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.load.cases.HttpPetStore
import perf.load.cases.HttpPetStore.testDataFeeder

object HttpPetStoreScenario {
  def apply(): ScenarioBuilder = new HttpPetStoreScenario().scn
}

class HttpPetStoreScenario {

  val scn: ScenarioBuilder = scenario("Http Pet store scenario")
    .feed(testDataFeeder)
    .repeat(2) {
      exec(HttpPetStore.postAddPet)
//        .pause(1)
        .exec(HttpPetStore.getPetById)
//        .pause(2, 4) // имитация thinkTime
        .exec(HttpPetStore.putUpdatePet)
//        .pace(10)
    }
}
