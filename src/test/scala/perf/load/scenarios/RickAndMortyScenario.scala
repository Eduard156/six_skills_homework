package perf.load.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure._
import perf.load.cases.RickAndMortyCases

object RickAndMortyScenario {
  def apply(): ScenarioBuilder = new RickAndMortyScenario().scn
}

class RickAndMortyScenario {
  val scn: ScenarioBuilder = scenario("Http Rick and Morty scenario")
    .exec(RickAndMortyCases.getCharactersByIds)
    .exec(RickAndMortyCases.validateCharactersIds)
    .foreach("#{characterIds}", "charId") {
      exec(RickAndMortyCases.getCharacterById)
        .exec(RickAndMortyCases.logCharacterName)
    }
    .exec(RickAndMortyCases.randomLocationId)
    .exec(RickAndMortyCases.getLocationById)
}
