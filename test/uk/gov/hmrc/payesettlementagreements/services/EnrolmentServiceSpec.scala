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

package uk.gov.hmrc.payesettlementagreements.services

import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.payesettlementagreements.connectors.EnrolmentConnector
import uk.gov.hmrc.payesettlementagreements.models.{EnrolmentRequest, ReferenceNumber}
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.Future

class EnrolmentServiceSpec extends UnitSpec with MockitoSugar {
  "Enrolment Service" must {
    "return 202 ACCEPTED" when {
      "the connector return a 200" in {
        when {
          enrolmentConnector.enrol(any[EnrolmentRequest])(any[HeaderCarrier])
        } thenReturn {
          Future.successful(Right(ReferenceNumber("Test")))
        }

        val result = enrolmentService.enrol(EnrolmentRequest("Test"))(HeaderCarrier())

        await(result) shouldBe Right(ReferenceNumber("Test"))
      }
    }
  }

  val enrolmentConnector = mock[EnrolmentConnector]
  val enrolmentService = new EnrolmentService(enrolmentConnector)
}
