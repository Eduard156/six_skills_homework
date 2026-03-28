package config

import org.galaxio.gatling.config.SimulationConfig._

object Config {

  case class RunConfig(
      baseUrl: String,
      scenario: String,
  )

  def configStand: RunConfig = {
    val standName = Option(System.getProperty("standName"))
      .getOrElse(getStringParam("standName"))

    val scenarioName = getStringParam("scenario")

    standName match {

      case "test" =>
        RunConfig(
          baseUrl = s"https://reqres.in.$standName",
          scenario = scenarioName,
        )

      case "dev" =>
        RunConfig(
          baseUrl = s"https://reqres.in.$standName",
          scenario = scenarioName,
        )

      case "in" =>
        RunConfig(
          baseUrl = s"https://reqres.$standName",
          scenario = scenarioName,
        )

      case other =>
        throw new IllegalArgumentException(s"Unknown stand: $other. Supported stands: 'test', 'dev'")

    }
  }

}
