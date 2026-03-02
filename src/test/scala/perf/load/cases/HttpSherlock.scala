package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

object HttpSherlock {

  val credFeeder = csv("credit.csv").queue

  val logger = LoggerFactory.getLogger("AuthLogger")

  val postToken = http("POST /api/token")
    .post("/api/token/")
    .body(
      StringBody(
        s"""
          {
            "username": "#{username}",
            "password": "#{password}"
          }
        """,
      ),
    )
    .asJson
    .check(status.saveAs("responseStatus"))

  val getProfile = http("GET /profile/#{userId}")
    .get("/profile/#{userId}/")
    .header("Authorization", s"Bearer #{access}")
    .check(
      status is 200,
      jsonPath("$.flag").isNull,
      jsonPath("$.is_privileged").is("false"),
    )

}
