package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HttpSherlock {

  val postToken = http("POST /api/token")
    .post("/api/token/")
    .body(
      StringBody(
        """
          {
            "username": "sherlock",
            "password": "password1"
          }
        """
      )
    )
    .asJson
    .check(
      status is 200,
      jsonPath("$.user_id").saveAs("userId"),
      jsonPath("$.access").saveAs("access")
    )

  val getProfile = http("GET /profile/#{userId}")
    .get("/profile/#{userId}/")
    .header("Authorization", s"Bearer #{access}")
    .check(
      status is 200,
      jsonPath("$.flag").isNull,
      jsonPath("$.is_privileged").is("false"),
    )

}
