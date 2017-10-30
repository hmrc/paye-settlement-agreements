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

import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.payesettlementagreements.models.{EnrolmentRequest, ReferenceNumber}
import uk.gov.hmrc.payesettlementagreements.services.EnrolmentService
import uk.gov.hmrc.play.test.UnitSpec
import scala.concurrent.Future


class EnrolmentControllerSpec extends UnitSpec with MockitoSugar {

  "Enrolment controller" must {
    "return ACCEPTED" when {
      "the enrol endpoint is called with a valid json payload" in {
        when {
          enrolmentService.enrol(any[EnrolmentRequest])(any[HeaderCarrier])
        } thenReturn {
          Future.successful(Right(ReferenceNumber("XA234282349")))
        }

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

      "the payload is correct but enrolment service returns an error" in {
        when {
          enrolmentService.enrol(any[EnrolmentRequest])(any[HeaderCarrier])
        } thenReturn {
          Future.successful(Left("Error"))
        }

        postCall(Json.parse("""{"name":"test"}""")) { result =>
          status(result) shouldBe BAD_REQUEST
        }
      }
    }
  }

  val enrolmentService = mock[EnrolmentService]
  val enrolmentController = new EnrolmentController(enrolmentService)

  private def postCall(payload: JsValue)(handler: Future[Result] => Any) = {
    val request: FakeRequest[JsValue] = FakeRequest("POST", "").withBody(payload)
    val result: Future[Result] = enrolmentController.enrol.apply(request)

    handler(result)
  }
}
