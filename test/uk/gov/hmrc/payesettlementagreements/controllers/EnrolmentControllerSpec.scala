/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.payesettlementagreements.controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.Future


class EnrolmentControllerSpec extends UnitSpec {

  "Enrolment controller" must {
    "return ACCEPTED" when {
      "the enrol endpoint is called with a valid json payload" in {
        postCall(Json.parse("""{"name":"test"}""")) { result =>
          status(result) shouldBe ACCEPTED
        }
      }
    }

    "return BAD REQUEST" when {
      "the enrol endpoint receives an invalid payload" in {
        postCall(Json.parse("""{"test":"test"}""")) { result =>
          status(result) shouldBe BAD_REQUEST
        }
      }
    }
  }

  private def postCall(payload: JsValue)(handler: Future[Result] => Any) = {
    val request: FakeRequest[JsValue] = FakeRequest("POST", "").withBody(payload)
    val result: Future[Result] = new EnrolmentController().enrol.apply(request)

    handler(result)
  }
}