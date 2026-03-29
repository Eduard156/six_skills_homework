package perf.load

import com.typesafe.config._
import config.Config.configStand
import config.Scenario.scn
import io.gatling.core.Predef._
import org.galaxio.gatling.config.SimulationConfig._

class Debug extends Simulation {

  private val config = ConfigFactory.load()
  private val env    = System.getProperty("env", "dev")

  private val baseUrl      = config.getString(s"environments.$env.baseUrl")
  private val scenarioName = config.getString(s"environments.$env.scenario")

  println(s"""
             |=== Конфигурация теста ===
             |Окружение: ${configStand.baseUrl}
             |Сценарий: ${configStand.scenario}
             |Длительность: $testDuration
             |========================
    """.stripMargin)

  setUp(
    scn(scenarioName)
      .inject(atOnceUsers(1)),
  ).protocols(
    httpProtocol.baseUrl(baseUrl),
  ).assertions(
    global.responseTime.mean.lt(900),
    global.successfulRequests.percent.gt(99),
  ).maxDuration(
    testDuration,
  )

}
